package module;

import java.util.List;

/**
 * Diese Klasse spiegelt eine Linie im 3D Raum wider, eigentlich brauche ich diese
 * Klasse für dieses Projekt garnicht. Vielleicht entferne ich sie wieder.
 *
 * @author Maixmilian Koehlenbeck
 */

public class Edge
{
    List<Vertex> vertices;

    public Edge(List<Vertex> vertices)
    {
        this.vertices = vertices;
    }
}
