package view;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import resources.XForm;

import static utility.konstanten.*;

/**
 * Diese Klasse ist dafür verantwortlich das Drehen der Kamera über die Maus
 * zu steuern. Ich habe mich bei der Klasse an einem Tutorial von Oracle
 * orientiert. <a href="https://docs.oracle.com/javase/8/javafx/graphics-tutorial/javafx-3d-graphics.htm">...</a>
 *
 * @author Maximilian Koehlenbeck
 */
public class MausController
{
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;

    /**
     * Diese Methode ist dafuer zustaendig mithilfe der Mausbewegungen beim druecken der linken Maustaste
     * die Kamera dementsprechend zu verschieben
     * und so die Illusion zu erzeugen man wuerde das Koordinatensystem bzw das Objekt festhalten und sich so
     * darum bewegen.
     *
     * @Precondition Alle Parameter wurden korrekt uebergeben
     * @Postcondition Beim Festhalten der linken Maustaste wird die Kamera entsprechend verschoben
     * @param scene
     * @param root
     * @param cameraXForm
     * @param cameraXForm2
     * @param camera
     */
    public void handleMouse(Scene scene, final Node root, Node polyederNode, XForm cameraXForm, XForm cameraXForm2, PerspectiveCamera camera) {
        scene.setOnMousePressed(me ->
        {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });

        scene.setOnMouseDragged(me ->
        {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);

            double modifier = EINS_DOUBLE;
            double modifierFactor = EINS_DOUBLE;

            if (me.isControlDown())
            {
                modifier = CONTROL_MULTIPLIER;
            }
            if (me.isShiftDown())
            {
                modifier = SHIFT_MULTIPLIER;
            }

            if (me.isPrimaryButtonDown())
            {
                // Objektbewegung je nach Modifier
                if (me.isControlDown())
                {
                    // Verschiebe in X-Richtung
                    polyederNode.setTranslateX(polyederNode.getTranslateX() + mouseDeltaX  * modifier);
                } else if (me.isAltDown())
                {
                    // Verschiebe in Y-Richtung
                    polyederNode.setTranslateY(polyederNode.getTranslateY() + mouseDeltaY * modifier);
                } else if (me.isShiftDown())
                {
                    // Verschiebe in Z-Richtung
                    polyederNode.setTranslateZ(polyederNode.getTranslateZ() + mouseDeltaY * modifier);
                } else
                {
                    // Sonst Kamera Rotaion
                    cameraXForm.getRotateY().setAngle(cameraXForm.getRotateY().getAngle() - mouseDeltaX * modifierFactor * modifier * ROTATION_SPEED);
                    cameraXForm.getRotateX().setAngle(cameraXForm.getRotateX().getAngle() + mouseDeltaY * modifierFactor * modifier * ROTATION_SPEED);
                }
            }
            else if (me.isMiddleButtonDown())
            {
                // Kamera-Tracking
                cameraXForm2.getTranslate().setX(cameraXForm2.getTranslate().getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED*ZWANZIG_DOUBLE);
                cameraXForm2.getTranslate().setY(cameraXForm2.getTranslate().getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED*ZWANZIG_DOUBLE);
            }
        });
        // Zoomen mit Mausrad
        scene.setOnScroll(scrollEvent -> {
            double delta = scrollEvent.getDeltaY();
            double zoomFactor = delta > NULL ? EINS : MINUS_EINS;
            double zoomSpeed = ZWANZIG_DOUBLE;

            double z = camera.getTranslateZ();
            double newZ = z + zoomFactor * zoomSpeed;

            camera.setTranslateZ(newZ);
        });
    }


}
