

package control;

import module.Dreieck;
import module.Vertex;
import utility.konstanten;

import static control.VertexController.bestimmeAbstandZwischen2Vertices;
/**
 * Diese Klasse beherbergt alle Methoden für die Verwaltung eines
 * Dreiecks. Wie beispielsweise die Berechnung des Oberflaecheninhalts.
 *
 * @author Maximilian Koehlenbeck
 */

public class DreieckController implements konstanten
{
    /**
     * Diese Methode errechnet den Oberflaecheninhalt eines Dreiecks
     *
     * @Precondition Ein Dreieck muss der Methode gegeben werden
     * @Postcondition Der Oberflaecheninhalt wird zurückgegeben
     * @param dreieck
     * @return float oberflaecheninhalt
     */
    public float errechneOberflaecheninhaltEinesDreiecks(Dreieck dreieck)
    {
        Vertex[] vertices=dreieck.getVertecies();
        Vertex vertexX=vertices[NULL];
        Vertex vertexY=vertices[EINS];
        Vertex vertexZ=vertices[ZWEI];
        float abstandXY= bestimmeAbstandZwischen2Vertices(vertexX,vertexY);
        float abstandXZ= bestimmeAbstandZwischen2Vertices(vertexX,vertexZ);
        float abstandYZ= bestimmeAbstandZwischen2Vertices(vertexY,vertexZ);
        //Mithilfe der Heronschen Formel dem Flaecheninhalt auf basis der Laengen der Edges berechnen
        float halbumfangDreieck= (abstandXY+abstandXZ+abstandYZ)/ZWEI_FLOAT;
        float flaecheninhalt= (float) Math.sqrt((halbumfangDreieck)*(halbumfangDreieck-abstandXY)*(halbumfangDreieck-abstandYZ)*(halbumfangDreieck-abstandXZ));
        dreieck.setFlaecheninhalt(flaecheninhalt);
        return flaecheninhalt;
    }
}
