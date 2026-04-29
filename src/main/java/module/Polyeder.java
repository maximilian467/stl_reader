package module;

import java.util.List;

import static utility.konstanten.NULL;

/**
 * Diese Klasse spiegelt ein Polyeder wider. Also einen Körper im Raum ohne "Loecher"
 * Ein Polyeder wird in diesem Fall durch n faces, in diesem Fall Dreiecke, dargestellt.
 *
 * @author Maximilian Koehlenbeck
 */

public class Polyeder
{
    List<Dreieck> dreiecke;

    public Polyeder(List<Dreieck> dreiecke)
    {
        this.dreiecke = dreiecke;
    }

    public Polyeder() {}

    public List<Dreieck> getDreiecke() {
        return dreiecke;
    }

    public void setDreiecke(List<Dreieck> dreiecke) {
        this.dreiecke = dreiecke;
    }
}
