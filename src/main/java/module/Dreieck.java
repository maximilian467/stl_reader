package module;

import java.util.Arrays;

/**
 * Diese Klasse spiegelt ein Dreieck wider. Sie erbt von Polyeder, allerdings definiere
 * ich das Dreieck durch 3 Punkte im Raum nicht durch 3 Edges da dies leichter wird, wenn
 * ich die STL-Datei einlese da mir dort Punkte und keine Edges gegeben werden.
 * Ich verweise auf die Dokumentation für Aufgabenteil 2, dort erlaeutere ich naeher warum ich so
 * vorgegangen bin.
 *
 * @author Maximilian Köhlenbeck
 */
public class Dreieck extends Polyeder
{
    Vertex[] vertecies; // In Mathematisch korrekter reihenfolge notiret
    Vektor3D flaechenNormale;
    double flaecheninhalt;

    public Dreieck(Vektor3D flaechenNormale, Vertex[] vertecies)
    {
        this.flaechenNormale = flaechenNormale;
        this.vertecies = vertecies;
    }

    public Vertex[] getVertecies() {
        return vertecies;
    }

    public void setVertecies(Vertex[] vertecies) {
        this.vertecies = vertecies;
    }

    public Vektor3D getFlaechenNormale() {
        return flaechenNormale;
    }

    public void setFlaechenNormale(Vektor3D flaechenNormale) {
        this.flaechenNormale = flaechenNormale;
    }

    public double getFlaecheninhalt() {
        return flaecheninhalt;
    }

    public void setFlaecheninhalt(double flaecheninhalt) {
        this.flaecheninhalt = flaecheninhalt;
    }

}
