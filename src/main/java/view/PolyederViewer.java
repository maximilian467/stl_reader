package view;

import control.JsonTcpServer;
import control.PolyederController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import module.Polyeder;
import resources.XForm;

import java.io.IOException;

import static utility.konstanten.*;

/**
 * Diese Klasse ist dafuer verantwortlich die JavaFx application zu starten.
 *
 * @author Maximilian Kohelenbeck
 */
public class PolyederViewer extends Application
{
    PolyederController polyederController=new PolyederController();
    KameraController kameraController = new KameraController();
    Koordinatensystem koordinatensystem = new Koordinatensystem();
    MausController mausController = new MausController();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final XForm cameraXForm = new XForm();
    final XForm cameraXForm2 = new XForm();
    final XForm cameraXForm3 = new XForm();
    final Group root = new Group(); // Dies ist jetzt die root für den 3D-Inhalt
    final XForm world = new XForm();
    final XForm axisGroup = new XForm();


    @Override
    /**
     * Diese Methode startet die Scene
     *
     * @Precondition primary Stage muss übergeben sein
     * @Postcondition Die Java FX application wird gestartet
     */
    public void start(Stage primaryStage) throws IOException
    {

        koordinatensystem.buildAxes(axisGroup, world);
        kameraController.buildKamera(root, cameraXForm, cameraXForm2, cameraXForm3, camera);
        Polyeder polyeder = polyederController.createPolyeder(DATEIPFAD);
        Node polyederNode = polyederController.buildPolyeder(polyeder, world);
        root.getChildren().add(world);

        MenuBarController menuController = new MenuBarController();
        MenuBar menuBar = menuController.createMenu(polyeder);

        SubScene subScene = new SubScene(root, TAUSENDVIERUNDZWANZIG, SIEBENHUNDERTACHTUNDSECHZIG, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.GREY); // Der Hintergrund für die 3D-Ansicht
        subScene.setCamera(camera);   // Die 3D-Kamera wird der SubScene zugewiesen

        // Haupt-Layout-Container
        BorderPane rootContainer = new BorderPane();
        rootContainer.setTop(menuBar);
        rootContainer.setCenter(subScene);

        subScene.widthProperty().bind(rootContainer.widthProperty());
        subScene.heightProperty().bind(rootContainer.heightProperty().subtract(menuBar.heightProperty())); // Hoehe der Menuleiste abziehen

        Scene scene = new Scene(rootContainer, TAUSENDVIERUNDZWANZIG, SIEBENHUNDERTACHTUNDSECHZIG, true);

        mausController.handleMouse(subScene.getScene(), root, polyederNode, cameraXForm, cameraXForm2, camera);

        primaryStage.setTitle(TITEL_JAVAFX_SCENE);
        primaryStage.setScene(scene);
        primaryStage.show();

        JsonTcpServer server = new JsonTcpServer(FUENFTAUSEND, polyederNode);
        server.start();

    }
}