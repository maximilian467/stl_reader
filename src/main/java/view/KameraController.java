package view;
/**
 * Diese Klasse ist für die Verwaltung und Erstellung der Kamera zustaendig.
 * Ich habe mich bei der erstellung der Klasse an einem Tutorial von Oracle orientiert.
 * https://docs.oracle.com/javase/8/javafx/graphics-tutorial/javafx-3d-graphics.htm
 *
 * @author Maximilian Koehlenbeck
 */

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import resources.XForm;

import static utility.konstanten.*;

public class KameraController
{
    /**
     * Diese Methode erstellt die Kamera
     *
     * @Precondition Es müssen alle Parameter korrekt uebergeben werden
     * @Postcondition Die Kamera wird erstellt
     * @param root
     * @param cameraXForm
     * @param cameraXForm2
     * @param cameraXForm3
     * @param camera
     */
    public void buildKamera(Group root, XForm cameraXForm, XForm cameraXForm2, XForm cameraXForm3, PerspectiveCamera camera)
    {
        root.getChildren().add(cameraXForm);
        cameraXForm.getChildren().add(cameraXForm2);
        cameraXForm2.getChildren().add(cameraXForm3);
        cameraXForm3.getChildren().add(camera);
        cameraXForm3.setRotateZ(HUNDERTACHTZIG);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXForm.getRotateY().setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXForm.getRotateX().setAngle(CAMERA_INITIAL_X_ANGLE);
    }
}