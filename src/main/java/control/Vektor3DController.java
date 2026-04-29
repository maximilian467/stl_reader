package control;
/**
 * Diese Klasse ist für alle Methoden in bezug auf den Vektor3D verantwortlich
 *
 * @author Maximilian Koehlenbeck
 */

import module.Vektor3D;

import static utility.konstanten.*;
import static utility.konstanten.ZWEI;

public class Vektor3DController
{
    /**
     * Diese Methode erstellt aus einem String mit Zahlenwerten einen 3D-Vektor
     *
     * @Precondition String mit 3 Zahlenwerten mit Leerzeichen getrennt.(Wie in einer STL-Datei)
     * @Postcondition Vektor3D wird zurückgegeben
     * @param line
     * @return new Vektor3D
     */
    public static Vektor3D createVektor(String line)
    {
        String[] koordinaten = line.split(LEERTASTE);
        double[] koordinatenDouble= {
                Double.parseDouble(koordinaten[NULL]),
                Double.parseDouble(koordinaten[EINS]),
                Double.parseDouble(koordinaten[ZWEI])};
        return new Vektor3D(koordinatenDouble);
    }
}
