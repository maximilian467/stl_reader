package module;

import java.util.List;

/**
 * Diese Klasse spiegelt eine Fläche im 3D Raum wider. Sie definiert sich durch mindestens 3 Edges im Raum.
 *
 *
 * @author Maximilian Koehlenbeck
 */
public class Face
{
    List<Edge> edges;
    private Vektor3D flaechenNormale; // Zeigt nach außen von der Fläche

    public Face(List<Edge> edges, Vektor3D flaechenNormale) {
        this.edges = edges;
        this.flaechenNormale = flaechenNormale;
    }
}
