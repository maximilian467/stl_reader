package view;


import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import resources.XForm;

import static utility.konstanten.AXIS_LENGTH;
import static utility.konstanten.EINS;
/**
 * Diese Klasse ist für das Koordinatensystem verantwortlich.
 * Ich habe mich bei der erstellung der Klasse an einem Tutorial von Oracle orientiert.
 * https://docs.oracle.com/javase/8/javafx/graphics-tutorial/javafx-3d-graphics.htm
 */
public class Koordinatensystem
{

    /**
     * Diese Methode erstellt die 3 Achsen fuer das Koordinatensystem.
     *
     * @Precondition Parameter muessen korrekt uebergeben werden
     * @Postcondition Koordinatensystem wird erstellt
     */
    public void buildAxes(XForm axisGroup, XForm world) {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, EINS, EINS);
        final Box yAxis = new Box(EINS, AXIS_LENGTH, EINS);
        final Box zAxis = new Box(EINS, EINS, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(true);
        world.getChildren().addAll(axisGroup);
    }
}
