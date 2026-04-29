package control;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import module.Dreieck;
import module.Polyeder;
import module.Vertex;
import resources.XForm;
import java.io.IOException;
import java.util.*;

import static utility.konstanten.*;

/**
 * In dieser Klasse werde ich alle Methoden aufbewahren welche zur handhabung
 * eines Polyeder zählen.
 *
 * @author Maximilian Koehlenbeck
 */
public class PolyederController
{


    /**
     * Diese Methode sortiert die Dreiecke in eines Polyeders nach ihrem Flaecheninhalt und
     * gibt den Flaecheninhalt des groessten sowie des kleinsten Dreiecks aus.
     *
     * @Precondition Polyeder welches nicht aus einer Leeren Liste besteht.
     * @Postcondition Dreiecke sind nach dem Flaecheninhalt sortiert.
     * @param polyeder
     */
    public void sortiereDreieckeNachFlaecheninhalt(Polyeder polyeder)
    {
        List<Dreieck> dreiecke=polyeder.getDreiecke();
        DreieckController dreieckController=new DreieckController();
        for(int i=NULL; i<dreiecke.size(); i++)
        {
            Dreieck dreieck=dreiecke.get(i);
            // Fuer jedes Dreieck den Flaecheninhalt berechnen und zuorndnen
            dreieck.setFlaecheninhalt(dreieckController.errechneOberflaecheninhaltEinesDreiecks(dreieck));

        }
        // Collection sortieren, indem alle Oberflaecheninhalte miteinander verglichen werden.
        dreiecke.sort((d1,d2)-> Double.compare(d1.getFlaecheninhalt(), d2.getFlaecheninhalt()));
        polyeder.setDreiecke(dreiecke);

        System.out.println(AUSGABE_KLEINSTES_DREIECK+dreiecke.getFirst().getFlaecheninhalt()+AUSGABE_GROESSTES_DREIECK+dreiecke.getLast().getFlaecheninhalt());
    }

    /**
     * Diese Methode addiert alle Oberflaecheninhalte der Dreiecke eines Polyeders
     * zusammen und gibt diese aus.
     *
     * @Precondition Polyeder muss gegeben sein und Oberflaecheninhalt darf nicht gleich Null sein.
     * @Postcondition Oberflaecheninhalt wird zurückgegeben
     * @param polyeder
     * @return double oberflaecheninhalt
     */
    public double errechneOberflaecheninhaltFuerPolyeder(Polyeder polyeder)
    {
        List<Dreieck> dreiecke=polyeder.getDreiecke();
        DreieckController dreieckController=new DreieckController();
        double oberflaecheninhalt=NULL;
        for(int i=NULL; i<dreiecke.size(); i++)
        {
            Dreieck dreieck=dreiecke.get(i);
            oberflaecheninhalt=oberflaecheninhalt+dreieck.getFlaecheninhalt();
        }
        System.out.println(AUSGABE_OBERFLAECHENINHALT+oberflaecheninhalt);
        return oberflaecheninhalt;
    }

    /**
     * Diese Klasse erstellt ein neues polyeder unabhängig von der Formatierung der STL Datei.
     *
     * @Precondition Dateipfad einer korrekt formatierten STL Datei
     * @Postcondition Neues Polyeder wird zurückgegeben
     * @param dateiPfad
     * @return new Polyeder
     */
    public Polyeder createPolyeder(String dateiPfad) throws IOException {
        String formatierung;
        STLController stl=new STLController();
        formatierung=stl.isFileBinaryOrAscii(dateiPfad);
        try {
            if (formatierung == ASCII)
            {
                return stl.createPolyederFromAsciiSTL(dateiPfad);
            }
            if (formatierung == BINARY)
            {
                return stl.createPolyederFromBinarySTL(dateiPfad);
            }
            if(formatierung==DATEI_FEHLERHAFT)
            {System.out.println(AUSGABE_FEHLERHAFTE_DATEI);}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mithilfe der Tetraeder-Zerlegung dem Polyeder in einzelne Tetraeder
     * zerlegen und die Volumina zusammenaddieren. Das Ergebnis muss dann noch
     * mit 1/6 multipliziert werden. Es ist sehr wichtig, dass die Vertices in
     * Mathematische reihenfolge notiert sind damit das Volumen positiv bleibt
     * Formel fuer das Volumen hier:
     * v0*(v1 x v2)
     * Die Vertices werden als Ortsvektoren behandelt.
     * Formel umgeschrieben:
     * x0*(y1*z2-y2*z1)+x1(y2*z0-y0z2)+x2(y0*z1-y1*z0)
     *
     * @Precondition Polyeder in welchem die vertices in Mathematisch posivier Reihenfolge notiert sind.
     * @Postcondition Volumen wird zurückgegeben.
     * @param polyeder
     * @return double volumen
     */
    public double berechneVolumenFuerPolyeder(Polyeder polyeder)
    {
        List<Dreieck> dreiecke=polyeder.getDreiecke();
        double volumen=NULL;
        for (int i=NULL; i<dreiecke.size(); i++)
        {
            Dreieck dreieck=dreiecke.get(i);
            Vertex[] vertices=dreieck.getVertecies();
            Vertex v0=vertices[NULL];
            Vertex v1=vertices[EINS];
            Vertex v2=vertices[ZWEI];
            double[] koordinatenV0=v0.getKoordinaten();
            double[] koordinatenV1=v1.getKoordinaten();
            double[] koordinatenV2=v2.getKoordinaten();
            // Formel: x0*(y1*z2-y2*z1)+x1(y2*z0-y0z2)+x2(y0*z1-y1*z0)
            volumen=volumen + (
                            koordinatenV0[NULL] *
                                    (koordinatenV1[EINS] * koordinatenV2[ZWEI] - koordinatenV2[EINS] * koordinatenV1[ZWEI]) +
                            koordinatenV1[NULL] *
                                    (koordinatenV2[EINS] * koordinatenV0[ZWEI] - koordinatenV0[EINS] * koordinatenV2[ZWEI]) +
                            koordinatenV2[NULL] *
                                    (koordinatenV0[EINS] * koordinatenV1[ZWEI] - koordinatenV1[EINS] * koordinatenV0[ZWEI])
            );

        }
        volumen=volumen*EIN_SECHSTEL;
        System.out.println(AUSGABE_VOLUMEN+volumen);
        return volumen;
    }

    /**
     * Diese Methode erzeugt ein Polyeder, welches sich in einer Java Fx Applikation mithilfe von TriangleMesh anzeigen lässt.
     *
     * @param polyeder
     * @param world
     * @return
     * @Precondition Es muss ein vollständiges Polyeder sowie die word in welcher es angezeigt werden soll übergeben werden.
     * @Postcondition Es wird das Polyeder angezeigt
     */
    public Node buildPolyeder(Polyeder polyeder, XForm world)
    {
        List<Dreieck> dreiecke = polyeder.getDreiecke();
        TriangleMesh triangleMesh = new TriangleMesh();

        Map<String, Integer> vertexIndexMap = new HashMap<>();
        int indexCounter = NULL;

        for (Dreieck dreieck : dreiecke)
        {
            Vertex[] vertecies = dreieck.getVertecies();
            int[] face = new int[DREI];

            for (int i = NULL; i < DREI; i++)
            {
                double[] koordinaten = vertecies[i].getKoordinaten();
                String key = koordinaten[NULL] + KOMMA + koordinaten[EINS] + KOMMA + koordinaten[ZWEI];

                if (!vertexIndexMap.containsKey(key))
                {
                    triangleMesh.getPoints().addAll(
                            (float) koordinaten[NULL],
                            (float) koordinaten[EINS],
                            (float) koordinaten[ZWEI]
                    );
                    vertexIndexMap.put(key, indexCounter++);
                }

                face[i] = vertexIndexMap.get(key);
            }

            // Texturekoordinaten muessen angegeben werden sind hier aber nicht relevant deshalb 0
            triangleMesh.getTexCoords().addAll(NULL, NULL);

            triangleMesh.getFaces().addAll
                    (
                    face[NULL], NULL,
                    face[EINS], NULL,
                    face[ZWEI], NULL
                    );
        }

        MeshView meshView = new MeshView(triangleMesh);
        meshView.setDrawMode(javafx.scene.shape.DrawMode.FILL);
        meshView.setCullFace(javafx.scene.shape.CullFace.BACK);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.LIGHTGRAY); // Farbe der Node
        material.setSpecularColor(Color.WHITE); // Reflection der Node
        meshView.setMaterial(material);

        world.getChildren().add(meshView);

        return meshView;
    }


}
