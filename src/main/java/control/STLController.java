package control;

import module.Dreieck;
import module.Polyeder;
import module.Vektor3D;
import module.Vertex;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static control.Vektor3DController.createVektor;
import static control.VertexController.createVertex;
import static utility.konstanten.*;

/**
In dieser Klasse werde ich alle Methoden aufbewahren, welche für die Handhabung einer
 STL-Datei verantwortlich sind.

 @author Maximilian Koehlenbeck
 */

public class STLController {


    /**
     * Diese Methode bestimmt, ob eine Datei Binaer, Ascii oder fehlerhaft formatiert ist.
     * Dies ermittelt sie anhand der ersten Zeile, entweder enthält diese das Wort Solid(ASCII formatiert)
     * oder sie enthält gewisse Char Werte(Binaer formatiert). Wenn keine dieser Faelle eintritt gehen wir davon
     * aus, dass die Datei nicht korrekt formatiert ist.
     *
     * @param path
     * @return String mit dem Inhalt, ob die Datei Binaer, Ascii, oder fehlerhaft formatiert ist
     * @throws IOException
     * @Precondition Es muss ein Dateipfad zu einer korrekt formatierten STL Datei gegeben werden.
     * @Postcondition Ein String welcher Ascii, Binaer oder fehlerhaft enthaelt.
     */
    public String isFileBinaryOrAscii(String path) throws IOException {
        File file = new File(path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String firstLine = bufferedReader.readLine();
            if (firstLine.contains(SOLID)) {
                return ASCII;
            }

            // Prüfe auf nicht druckbare Zeichen
            for (char c : firstLine.toCharArray()) {
                if (c < CHAR1 || (c > CHAR2 && c < CHAR3) || c > CHAR4) {
                    return BINARY;
                }
            }

        }
        return DATEI_FEHLERHAFT;
    }


    /**
     * Diese Methode erstellt aus einer Ascii formatierten STL Datei ein Objekt der Klasse Polyeder.
     *
     * @param path
     * @return new Polyeder
     * @throws IOException
     * @Precondition Dateipfad zu einer Ascii formatierten STL Datei
     * @Postcondition Es wird ein Polyeder mit den gesammten Dreiecken aus der Datei zurückgegeben.
     */
    public Polyeder createPolyederFromAsciiSTL(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(new FileReader(file));
        List<Dreieck> dreiecke = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.contains(FACET_NORMAL)) {
                Vektor3D normalenVektor = createVektor(line.substring(DREIZEHN)); //Koordinaten stehen an 13ter Stelle
                scanner.nextLine();//outer loop wird übersprungen
                Vertex vertexX = createVertex(scanner.nextLine().trim().substring(SIEBEN));// Koordinaten stehen an 7ter Stelle
                Vertex vertexY = createVertex(scanner.nextLine().trim().substring(SIEBEN));
                Vertex vertexZ = createVertex(scanner.nextLine().trim().substring(SIEBEN));
                Vertex[] vertecies = {vertexX, vertexY, vertexZ};
                scanner.nextLine();//ENDLOOP ueberspringen
                scanner.nextLine();//ENDFACET ueberspringen

                dreiecke.add(new Dreieck(normalenVektor, vertecies));

            }
        }
        scanner.close();
        Polyeder polyeder = new Polyeder(dreiecke);
        polyederSortierenUndOberflaecheUndVolumenBerechnen(polyeder);
        System.out.println(AUSGABE_POLYEDER_ERSTELLT + dreiecke.size() + DREIECKEN);
        return polyeder;
    }

    /**
     * Diese Methode erzeugt aus einer Binaeren STL Datei ein neues Polyeder
     *
     * @param path
     * @return new Polyeder
     * @throws IOException
     * @Precondition Der Pfad der Datei sollte korrekt angegeben sein
     * @Postcondition Es wird ein Polyeder mit den gesammten Dreiecken aus der Datei zurückgegeben.
     */
    public Polyeder createPolyederFromBinarySTL(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] allBytes = inputstream.readAllBytes(); // Alle bytes abspeichern
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(allBytes));
        List<Dreieck> dreiecke = new ArrayList<>();
        byte[] header = new byte[ACHTZIG];
        dataInputStream.read(header);// Den Header lesen da dort keine relevanten Daten stehen
        byte[] anzahlDreieckeBytes = new byte[VIER];
        dataInputStream.readFully(anzahlDreieckeBytes);// Hier werden die Bytes für die Anzahl der Dreiecke gelesen
        int anzahlDreiecke = ByteBuffer.wrap(anzahlDreieckeBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();// Bytes in für Menschen lesbare Zahl umwandeln
        while (dreiecke.size() < anzahlDreiecke)// Wenn die Laenge der Liste gleich der Dreicke in der Datei ist sind wir fertig mit einlesen
        {
            double[] normalenVektorKoordinaten = new double[DREI];
            for (int i = NULL; i < normalenVektorKoordinaten.length; i++)
            {
                normalenVektorKoordinaten[i] = Float.intBitsToFloat(Integer.reverseBytes(dataInputStream.readInt()));// Koordinaten mit Korrkter IEEE Norm einlesen
                // IEEE Norm wird mit intBitsToFloat abgedeckt.
            }
            Vektor3D normalenVektor = new Vektor3D(normalenVektorKoordinaten);
            Vertex[] vertecies = new Vertex[DREI];
            for (int i = NULL; i < vertecies.length; i++)
            {
                double[] koordinaten = new double[DREI];
                for (int j = NULL; j < koordinaten.length; j++)
                {
                    koordinaten[j] = Float.intBitsToFloat(Integer.reverseBytes(dataInputStream.readInt())); // Das gleiche wie oben
                }
                vertecies[i] = new Vertex(koordinaten);
            }
            dataInputStream.readShort();
            dreiecke.add(new Dreieck(normalenVektor, vertecies));
        }
        Polyeder polyeder = new Polyeder(dreiecke);
        polyederSortierenUndOberflaecheUndVolumenBerechnen(polyeder);
        System.out.println(AUSGABE_POLYEDER_ERSTELLT + dreiecke.size() + DREIECKEN);
        return polyeder;
    }

    /**
     * Diese Methode soll aus einer Binaeren STL Datei ein Polyeder erzeugen und dabei die Brechnung der Oberflaeche
     * parallel ablaufen lassen.
     *
     * @param path
     * @return new Polyeder
     * @throws IOException
     * @Precondition Der Pfad der Datei sollte korrekt angegeben sein
     * @Postcondition Es wird ein Polyeder mit den gesammten Dreiecken aus der Datei zurückgegeben.
     */
    public Polyeder createPolyederFromBinarySTLParalleleOberflaechenberechnung(String path) throws IOException, InterruptedException
    {
        File file = new File(path);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] allBytes = inputstream.readAllBytes();
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(allBytes));
        List<Dreieck> dreiecke = Collections.synchronizedList(new ArrayList<>());
        byte[] header = new byte[ACHTZIG];
        dataInputStream.read(header);
        byte[] anzahlDreieckeBytes = new byte[VIER];
        dataInputStream.readFully(anzahlDreieckeBytes);
        int anzahlDreiecke = ByteBuffer.wrap(anzahlDreieckeBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();

        // Dreiecke einlesen
        for (int d = NULL; d < anzahlDreiecke; d++)
        {
            double[] normalenVektorKoordinaten = new double[DREI];
            for (int i = NULL; i < normalenVektorKoordinaten.length; i++)
            {
                normalenVektorKoordinaten[i] = Float.intBitsToFloat(Integer.reverseBytes(dataInputStream.readInt()));
            }
            Vektor3D normalenVektor = new Vektor3D(normalenVektorKoordinaten);

            Vertex[] vertecies = new Vertex[DREI];
            for (int i = NULL; i < DREI; i++)
            {
                double[] koordinaten = new double[DREI];
                for (int j = NULL; j < DREI; j++)
                {
                    koordinaten[j] = Float.intBitsToFloat(Integer.reverseBytes(dataInputStream.readInt()));
                }
                vertecies[i] = new Vertex(koordinaten);
            }

            dataInputStream.readShort(); // Attribut-Byte-Count ignorieren
            dreiecke.add(new Dreieck(normalenVektor, vertecies));
        }

        // Parallele Oberflächenberechnung
        DreieckController dreieckController = new DreieckController();
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (Dreieck d : dreiecke)
        {
            executor.submit(() -> dreieckController.errechneOberflaecheninhaltEinesDreiecks(d));
        }

        executor.shutdown();
        // Polyeder erzeugen und analysieren
        Polyeder polyeder = new Polyeder(dreiecke);
        polyederSortierenUndOberflaecheUndVolumenBerechnen(polyeder);
        System.out.println(AUSGABE_POLYEDER_ERSTELLT + dreiecke.size() + DREIECKEN);
        return polyeder;
    }



    /**
     * Diese Methode erstellt aus einer Ascii formatierten STL Datei ein Objekt der Klasse Polyeder.
     * Dabei wird die Oberflaechenberechnung parralel durchgeführt.
     *
     * @param path
     * @return new Polyeder
     * @throws IOException
     * @Precondition Dateipfad zu einer Ascii formatierten STL Datei
     * @Postcondition Es wird ein Polyeder mit den gesammten Dreiecken aus der Datei zurückgegeben.
     */
    public Polyeder createPolyederFromAsciiSTLParallelOberflaechenberechnung(String path) throws IOException
    {
        File file = new File(path);
        Scanner scanner = new Scanner(new FileReader(file));
        List<Dreieck> dreiecke = Collections.synchronizedList(new ArrayList<>()); // Thread-safe für parallele Nutzung

        // STL-Datei einlesen (synchron)
        while (scanner.hasNext())
        {
            String line = scanner.nextLine().trim();
            if (line.contains(FACET_NORMAL))
            {
                Vektor3D normalenVektor = createVektor(line.substring(DREIZEHN));
                scanner.nextLine(); // outer loop
                Vertex vertexX = createVertex(scanner.nextLine().trim().substring(SIEBEN));
                Vertex vertexY = createVertex(scanner.nextLine().trim().substring(SIEBEN));
                Vertex vertexZ = createVertex(scanner.nextLine().trim().substring(SIEBEN));
                Vertex[] vertecies = {vertexX, vertexY, vertexZ};
                scanner.nextLine(); // endloop
                scanner.nextLine(); // endfacet
                dreiecke.add(new Dreieck(normalenVektor, vertecies));
            }
        }
        scanner.close();

        // Parallele Berechnung der Dreiecksflaechen
        DreieckController dreieckController = new DreieckController();
        int threadAnzahl = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadAnzahl);

        for (Dreieck i : dreiecke)
        {
            executor.submit(() -> dreieckController.errechneOberflaecheninhaltEinesDreiecks(i));
        }

        executor.shutdown();

        // Polyeder erzeugen und analysieren
        Polyeder polyeder = new Polyeder(dreiecke);
        polyederSortierenUndOberflaecheUndVolumenBerechnen(polyeder);
        System.out.println(AUSGABE_POLYEDER_ERSTELLT + dreiecke.size() + DREIECKEN);
        return polyeder;
    }

    /**
     * Diese Methode dient zur Modularisierung und ist dafür da die Dreiecke eines Polyeders nach der größe zu sortieren
     * sowie das Volumen und den Oberflaecheninhalt zu berechnen.
     *
     * @Precondition Korrektes Polyeder muss uebergeben werden
     * @Postcondition Die Methoden wurden aufgerufen
     * @param polyeder
     */
    public void polyederSortierenUndOberflaecheUndVolumenBerechnen (Polyeder polyeder)
    {
        PolyederController polyederController = new PolyederController();
        polyederController.sortiereDreieckeNachFlaecheninhalt(polyeder);
        polyederController.errechneOberflaecheninhaltFuerPolyeder(polyeder);
        polyederController.berechneVolumenFuerPolyeder(polyeder);
    }


}