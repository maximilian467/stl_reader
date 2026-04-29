import control.PolyederController;
import control.STLController;
import javafx.application.Application;
import module.Polyeder;
import utility.ZeitManager;
import view.PolyederViewer;
import java.io.IOException;
import static utility.konstanten.*;

/**
 * Diese Klasse ist das Main des Programms. Sie ist fuer alle Operationen verantwortlich
 *
 * @author Maximilian Koehlenbeck
 */
public class Main
{
    /**
     * Diese Methode startet das Programm alle Operationen werden in ihr ausgefuehrt.
     *
     * @Precondition Nichts
     * @Postcondition Das ganze Programm funktioniert
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        // args[0] fuer Ascii
        // args[1] fuer Binary
        //args[2] fuer Nissan 370Z ASCII
        // args[3] fuer Nissan 370Z Binary
        ZeitManager zeitManager = new ZeitManager();
        Polyeder polyeder = new Polyeder();
        PolyederController polyederController = new PolyederController();
        STLController stlController = new STLController();
        PolyederViewer polyederViewer = new PolyederViewer();
        zeitManager.zeitStarten();
        polyeder =polyederController.createPolyeder(args[ZWEI]);
        System.out.println(AUSGABE_DAUER_ASCII_SEQUENTIELL+zeitManager.zeitStoppen()+MILLISEKUNDEN+GEDAUERT);
        System.out.println();
        zeitManager.zeitStarten();
        polyeder = polyederController.createPolyeder(args[DREI]);
        System.out.println(AUSGABE_DAUER_BINARY_SEQUENTIELL+zeitManager.zeitStoppen()+MILLISEKUNDEN+GEDAUERT);
        System.out.println();
        zeitManager.zeitStarten();
        stlController.createPolyederFromBinarySTLParalleleOberflaechenberechnung(args[DREI]);
        System.out.println(AUSGABE_DAUER_BINARY_PARALLEL+zeitManager.zeitStoppen()+MILLISEKUNDEN+GEDAUERT);
        System.out.println();
        zeitManager.zeitStarten();
        stlController.createPolyederFromAsciiSTLParallelOberflaechenberechnung(args[ZWEI]);
        System.out.println(AUSGABE_DAUER_ASCII_PARALLEL+zeitManager.zeitStoppen()+MILLISEKUNDEN+GEDAUERT);



        new Thread(() -> {
            try {
                Application.launch(PolyederViewer.class);
            } catch (IllegalStateException e) {
                System.out.println(e);
            }
        }).start();
    }
}