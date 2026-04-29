package resources;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * Diese Klasse stellt ein Objekt XForm dar, ich habe mich bei ihr an der Klasse aus dem Tutorial von
 * Oracle orientiert.
 *
 * @author Maximilian Koehlenbeck
 */

public class XForm extends Group
{

    private final Translate translate = new Translate();
    private final Rotate rotateX = new Rotate();
    private final Rotate rotateY = new Rotate();
    private final Rotate rotateZ = new Rotate();

    public XForm()
    {
        super();
        rotateX.setAxis(Rotate.X_AXIS);
        rotateY.setAxis(Rotate.Y_AXIS);
        rotateZ.setAxis(Rotate.Z_AXIS);

        getTransforms().addAll(translate, rotateZ, rotateY, rotateX);
    }

    public Translate getTranslate()
    {
        return translate;
    }

    public Rotate getRotateX()
    {
        return rotateX;
    }

    public Rotate getRotateY()
    {
        return rotateY;
    }

   public void setRotateZ(double angle)
   {
       rotateZ.setAngle(angle);
   }

}
