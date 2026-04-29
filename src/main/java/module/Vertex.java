package module;

import static utility.konstanten.*;

/**
 * Diese Klasse stellt einen einzelnen Punkt im 3D Raum dar, einen sogenannten Vertex.
 *
 * @author Maximilian Koehlenbeck
 */

public class Vertex
{
    private double[] koordinaten;

    public Vertex(double[] koordinaten)
    {
        this.koordinaten = koordinaten;
    }

    public double[] getKoordinaten() {
        return koordinaten;
    }

    public void setKoordinaten(double[] koordinaten) {
        this.koordinaten = koordinaten;
    }

    @Override
    public String toString() {
        return VERTEX_DOPPELPUNKT+koordinaten[NULL]+LEERTASTE+koordinaten[EINS]+LEERTASTE+koordinaten[ZWEI];
    }
}