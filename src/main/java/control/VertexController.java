package control;

import module.Vertex;

import static utility.konstanten.*;

/**
 * Diese Klasse verwaltet Methoden welche fuer die Handhabung eines
 * Vertex oder mehrerer Vertices verantwortlich sind.
 *
 * @author Maximilian Koehlenbeck
 */
public class VertexController
{
    /**
     * Diese Methode bestimmt den Abstand zwischen 2 Vertecies und gibt diesen zurück
     *
     * @Precondition Vertex a und Vertex b muessen gegeben sein
     * @Postcondition Der Abstand wurde berechnet und zurueckgegeben
     * @param a
     * @param b
     * @return float abstand
     */
    public static float bestimmeAbstandZwischen2Vertices(Vertex a, Vertex b)
    {
        double[]koordinatenA=a.getKoordinaten();
        double[]koordinatenB=b.getKoordinaten();
        double[] gerichteteGroeßeAbstand=new double[DREI];

        for(int i=NULL;i<DREI;i++)
        {
            double x=koordinatenA[i];
            double y=koordinatenB[i];
            gerichteteGroeßeAbstand[i]=y-x;
        }
        //Mithilfe von pytagoras den Abstand der Punkte ermitteln
        double abstand= Math.sqrt(Math.pow(gerichteteGroeßeAbstand[NULL],ZWEI)+Math.pow(gerichteteGroeßeAbstand[EINS],ZWEI)+Math.pow(gerichteteGroeßeAbstand[ZWEI],ZWEI));
        return (float) abstand;
    }

    /**
     * Diese Methode erstellt einen Vertex aus einem String mit 3 Zahlenwerten.
     *
     * @Precondition String mit 3 Zahlenwerten mit Leerzeichen getrennt.(Wie in einer STL-Datei)
     * @Postcondition Vertex wird zurückgegeben
     * @param line
     * @return new Vertex
     */
    public static Vertex createVertex(String line)
    {
        String[] koordinaten = line.split(LEERTASTE);
        double[] koordinatenDouble = {
                Double.parseDouble(koordinaten[NULL]),
                Double.parseDouble(koordinaten[EINS]),
                Double.parseDouble(koordinaten[ZWEI])
        };
        return new Vertex(koordinatenDouble);
    }
}
