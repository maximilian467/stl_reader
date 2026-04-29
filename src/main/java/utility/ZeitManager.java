package utility;

/**
 * Diese Klasse enthaelt Methoden welche zum Starten oder stoppen von Zeit verantwortlich sind.
 *
 * @author Maximilian Koehlenbeck
 */
public class ZeitManager
{
    private long zeitSlot1;

    /**
     * Speichert die aktuelle Zeit
     *
     * @Precondition Nichts
     * @Postcondition Zeit wurde gespeichert
     */
    public void zeitStarten()
    {
        zeitSlot1=System.currentTimeMillis();
    }

    /**
     * Diese Methode speichert die aktuelle Zeit und errechnet mit der startzeit die Dauer zwischen
     * den beiden Methodenaufrufen.
     *
     * @Precondition Nichts
     * @Postcondition Zeit zwischen Methoden wurde ausgerechnet und zurückgeben
     * @return
     */
    public long zeitStoppen()
    {
        zeitSlot1=System.currentTimeMillis()-zeitSlot1;
        return zeitSlot1;
    }
}
