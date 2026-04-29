package utility;

/**
 * In diesem Interface speichere ich alle meine Konstanten, die ich im Code benötige.
 * Wahrscheinlich werde ich in der Zukunft noch weite konstanten interfaces implementieren
 * um einen besseren Ueberblick zu haben.
 *
 * @author Maximilian Koehlenbeck
 */
public interface konstanten
{
    int MINUS_EINS=-1;
    int NULL=0;
    int EINS=1;
    int ZWEI=2;
    int DREI=3;
    int VIER=4;
    int FUENF =5;
    int SIEBEN=7;
    int ACHT =8;
    int NEUN=9;
    int ZEHN=10;
    int DREIZEHN=13;
    int ACHTZIG=80;
    int HUNDERT =100;
    int AXIS_LENGTH =250;
    int VIERHUNDERT=400;
    int VIERHUNDERTFUENFZIG=450;
    int SIEBENHUNDERTACHTUNDSECHZIG=768;
    int TAUSENDVIERUNDZWANZIG=1024;
    int FUENFTAUSEND=5000;


    float ZWEI_FLOAT= 2.0F;
    double EINS_DOUBLE= 1.0;
    double EIN_SECHSTEL=1.0/6.0;
    double CONTROL_MULTIPLIER = 1.0;
    double TRACK_SPEED =0.3;
    double MOUSE_SPEED=0.095;
    double ROTATION_SPEED=2.0;
    double SHIFT_MULTIPLIER=1.0;
    double HUNDERTACHTZIG=180.0;

    double CAMERA_NEAR_CLIP=0.1;
    double CAMERA_FAR_CLIP=10000.0;
    double CAMERA_INITIAL_DISTANCE=-450.0;
    double CAMERA_INITIAL_Y_ANGLE=320;
    double CAMERA_INITIAL_X_ANGLE=70.0;
    double ZWANZIG_DOUBLE=20.0;

    String KOMMA =",";
    String ABSATZ="\n";
    String SOLID="solid";
    String ASCII="ascii";
    String BINARY="binary";
    String DREIECKEN =" Dreiecken.";
    String DATEI_FEHLERHAFT="Fehlerhaft";
    String FACET_NORMAL="facet normal";
    String LEERTASTE=" ";
    String MILLISEKUNDEN=" millisekunden";
    String GEDAUERT=" gedauert";
    String VERTEX_DOPPELPUNKT="Vertex: ";
    String SEVER_LAEUFT_AUF_PORT="JSON TCP Command Server läuft auf Port ";
    String COMMAND="command";
    String AXIS="axis";
    String VALUE="value";
    String TRANSLATE="translate";
    String ROTATE="rotate";
    String UNBEKANNTER_BEFEHL="Unbekannter Befehl: ";
    String UNGUELLTIGE_ACHSE="Unguellltige Achse: ";
    String X="X";
    String Y="Y";
    String Z="Z";
    String DATEIPFAD="C:\\Users\\maxim\\IdeaProjects\\Entwicklungsarbeit_PROG2\\src\\main\\java\\resources\\nissan_370_Z_Binary.stl";
    String AUSGABE_FEHLERHAFTE_DATEI="Die Datei ist nicht korrekt formatiert";
    String AUSGABE_POLYEDER_ERSTELLT="Ein neues polyeder wurder erfolgreich aus der STL Datei erstellt. Es besteht aus ";
    String AUSGABE_KLEINSTES_DREIECK="Das kleinste Dreieck hat einen Flaecheninhalt von: ";
    String AUSGABE_GROESSTES_DREIECK=" und das groesste Dreieck einen von: ";
    String AUSGABE_OBERFLAECHENINHALT="Das Polyeder hat einen Oberflaecheninhalt von: ";
    String AUSGABE_VOLUMEN="Das Polyeder hat einen Volumen von: ";
    String AUSGABE_DAUER_ASCII_SEQUENTIELL="Die Erstellung und Berechnung hat sequentiell für ASCII insgesammt: ";
    String AUSGABE_DAUER_BINARY_SEQUENTIELL="Die Erstellung und Berechnung hat sequentiell für Binaer insgesammt: ";
    String AUSGABE_DAUER_BINARY_PARALLEL="Die Erstellung und Berechnung hat parallel für Binaer insgesammt:";
    String AUSGABE_DAUER_ASCII_PARALLEL="Die Erstellung und Berechnung hat parallel für ASCII insgesammt:";
    String TITEL_JAVAFX_SCENE="3D Viewer";
    String INFO="Informationen";
    String STEUERUNG_ANZEIGEN="Steuerung anzeigen";
    String POLYEDER_DETAILS="Polyeder-Details";
    String HILFE="Hilfe";
    String STEUERUNG="Steuerung";
    String WIE_TOLL_NISSAN_370_Z ="Auf einer Skala von 1-10 wie toll ist der Nissan 370Z?";
    String STEUERUNG_ERKLAERUNG="""
            Linke Maustaste: Kamera rotieren
            STRG + Linke Maustaste: Polyeder in X verschieben
            ALT + Linke Maustaste: Polyeder in Y verschieben
            SHIFT + Linke Maustaste: Polyeder in Z verschieben
            Mittlere Maustaste: Kamera verschieben
            Mausrad: Zoom
        """;
    String IMPRESSUM_TEXT="Impressum\n\n" +
            "Angaben gemäß § 5 TMG:\n\n" +
            "Max Mustermann\n" +
            "Musterstraße 123\n" +
            "12345 Musterstadt\n" +
            "Deutschland\n\n" +
            "Kontakt:\n" +
            "Telefon: 01234 / 567890\n" +
            "E-Mail: kontakt@mustermann.de\n\n" +
            "Verantwortlich für den Inhalt nach § 18 Abs. 2 MStV:\n" +
            "Max Mustermann\n" +
            "Musterstraße 123\n" +
            "12345 Musterstadt\n\n" +
            // --- Der folgende Teil ist besonders wichtig bei kommerzieller Nutzung ---
            "Streitschlichtung:\n" +
            "Die Europäische Kommission stellt eine Plattform zur Online-Streitbeilegung (OS) bereit: https://ec.europa.eu/consumers/odr.\n" +
            "Unsere E-Mail-Adresse finden Sie oben im Impressum.\n\n" +
            "Wir sind nicht bereit oder verpflichtet, an Streitbeilegungsverfahren vor einer Verbraucherschlichtungsstelle teilzunehmen.\n";
    String ANZAHL_DREIECKE="Anzahl Dreiecke: ";
    String GROESSE_VOLUMEN="Das Polyeder hat ein Volumen von ca. : ";
    String VOLUMEN_EINHEITEN=" Volumeneinheiten";
    String GROESSE_OBERFLAECHE= "Das Polyeder hat einen Oberflaecheninhalt von ca. : ";
    String OBERFLAECHEN_EINHEITEN=" Oberflaecheninheiten";
    String RECHTLICHES="Rechtliches";
    String IMPRESUM="Impressum";
    String NISSAN_370_Z = "Nissan 370Z";
    String JETZT_KAUFEN="Jetzt Kaufen";
    String GEHT_SO="Geht so...";
    String SCHON_OK="Schon Ok, aber nichts besonderes";
    String RICHTIG_GUT ="Richtig geiles Auto, haette ich gerne!";
    String BESTES_AUTO="Ich liebe es!!!!!!!!";
    String MUELL="Richtiges drecks Auto, ab in die Schrottpresse damit";
    String URL_AUTO ="https://www.kleinanzeigen.de/s-anzeige/nissan-370z-roadster-3-7-pack-automatik-pack/3115391172-216-21396";
    String HILFE_TEXT="Diese Anwendung ist dazu da um ein Polyeder anzuzeigen. Falls kein Polyeder engezeigt wird pruefen Sie ihre STL Datei.";
    char CHAR1=0x09;
    char CHAR2=0x0A;
    char CHAR3=0x20;
    char CHAR4=0x7E;
}
