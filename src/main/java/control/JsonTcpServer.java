package control;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static utility.konstanten.*;

/**
 * Diese Klasse nimmt die Befehle vom Ubunto client an und setzt diese in
 * rotationen oder verschiebungen des Polyeders um.
 *
 * @author Maximilian Koehlenbeck
 */
public class JsonTcpServer extends Thread
{

    private final int port;
    private final Node polyeder;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonTcpServer(int port, Node polyeder)
    {
        this.port = port;
        this.polyeder = polyeder;
        setDaemon(true);
    }

    @Override
    /**
     * Dies ist die run Methode, sie startet den Thread, welcher auf die Befehle wartet und diese ausfuehrt.
     *
     * @Precondition keine
     * @Postcondition Thread laeuft.
     */
    public void run()
    {
        System.out.println(SEVER_LAEUFT_AUF_PORT + port);
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            while (!isInterrupted())
            {
                Socket client = serverSocket.accept();
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Diese Methode bedient den Client sie liesst die entsprechenden befehle
     * vom client ein.
     *
     * @Precondition Socket muss korrekt angegeben und belegt sein.
     * @Postcondition Client wird bedient
     * @param client
     */
    private void handleClient(Socket client)
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream())))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                handleCommand(line.trim());
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Diese Methode erkennt welcher Befehlt gemeint ist und leitet dementsprechend an
     * die passende Methode weiter oder gibt aus, dass ein Fehler vorliegt.
     *
     * @Precondition Es wird ein String json uebergeben, welcher korrekt formatiert ist.
     * @Postcondition Die passende Methode wird aufgerufen, um das Polyeder zu verschieben oder zu rotieren.
     * @param json
     */
    private void handleCommand(String json)
    {
        try {
            JsonNode root = mapper.readTree(json);
            String command = root.get(COMMAND).asText();
            String axis = root.get(AXIS).asText().toUpperCase();
            double value = root.get(VALUE).asDouble();

            Platform.runLater(() ->
            {
                switch (command.toLowerCase())
                {
                    case TRANSLATE -> applyTranslation(axis, value);
                    case ROTATE -> applyRotation(axis, value);
                    default -> System.err.println(UNBEKANNTER_BEFEHL + command);
                }
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Diese Methode verschiebt das Polyeder auf einer bestimmten Achse.
     *
     * @Precondition Achse und Verschiebungswert wurden uebergeben
     * @Postcondition Polyeder wird korrekt verschoben
     * @param axis
     * @param value
     */
    private void applyTranslation(String axis, double value)
    {
        switch (axis)
        {
            case X -> polyeder.setTranslateX(polyeder.getTranslateX() + value);
            case Y -> polyeder.setTranslateY(polyeder.getTranslateY() + value);
            case Z -> polyeder.setTranslateZ(polyeder.getTranslateZ() + value);
            default -> System.err.println(UNGUELLTIGE_ACHSE + axis);
        }
    }

    /**
     * Diese Methode rotiert das Polyeder auf einer bestimmten Achse
     *
     * @Precondition Achse und Winkel wurden uebergeben
     * @Postcondition Polyeder rotiert sich korrekt
     * @param axis
     * @param angle
     */
    private void applyRotation(String axis, double angle)
    {
        switch (axis)
        {
            case X -> polyeder.getTransforms().add(new Rotate(angle, Rotate.X_AXIS));
            case Y -> polyeder.getTransforms().add(new Rotate(angle, Rotate.Y_AXIS));
            case Z -> polyeder.getTransforms().add(new Rotate(angle, Rotate.Z_AXIS));
            default -> System.err.println(UNGUELLTIGE_ACHSE + axis);
        }
    }
}
