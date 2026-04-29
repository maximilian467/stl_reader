package view;

import control.PolyederController;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import module.Polyeder;

import static utility.konstanten.*;

/**
 * Diese Klasse ist fuer alle operationen im Zusammenhang mit der MenuBar verantwortlich
 *
 * @author Maximilian Koehlenbeck
 */
public class MenuBarController
{
    /**
     * Diese Methode erzeugt eine MenuBar
     *
     * @Precondition Polyeder muss korrekt uebergeben werden
     * @Postcondition Menubar wird erzeugt
     * @param polyeder
     * @return
     */
    public MenuBar createMenu(Polyeder polyeder) {
        MenuBar menuBar = new MenuBar();

        Menu menuInfo = new Menu(INFO);

        MenuItem steuerungItem = new MenuItem(STEUERUNG_ANZEIGEN);
        steuerungItem.setOnAction(e -> showSteuerungDialog());

        MenuItem polyederInfoItem = new MenuItem(POLYEDER_DETAILS);
        polyederInfoItem.setOnAction(e -> showPolyederDialog(polyeder));

        MenuItem hilfeItem = new MenuItem(HILFE);
        hilfeItem.setOnAction(e -> showHilfeDialog());

        Menu menuRechtliches= new Menu(RECHTLICHES);

        MenuItem impressumItem = new MenuItem(IMPRESUM);
        impressumItem.setOnAction(e->showImpressumDialog());

        MenuItem spendenItem = new MenuItem(NISSAN_370_Z);
        spendenItem.setOnAction(e-> showNissan370ZDialog());

        menuInfo.getItems().addAll(steuerungItem, polyederInfoItem, hilfeItem, spendenItem);
        menuRechtliches.getItems().addAll(impressumItem);
        menuBar.getMenus().addAll(menuInfo, menuRechtliches);

        return menuBar;
    }

    /**
     * Diese Methode zeigt den Dialog fuer die Steuerung an
     *
     * @Precondition Nichts
     * @Postcondition Dialog wird angezeigt
     */
    private void showSteuerungDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(STEUERUNG);
        alert.setHeaderText(STEUERUNG);
        alert.setContentText(STEUERUNG_ERKLAERUNG);
        alert.showAndWait();
    }

    /**
     * Diese Methode zeigt den Dialog fuer die Informationen des Polyeders an
     *
     * @Precondition Korrektes Polyeder muss uebergeben werden
     * @Postcondition Dialog wird angezeigt
     */
    private void showPolyederDialog(Polyeder polyeder)
    {
        PolyederController polyederController = new PolyederController();
        double volumen= polyederController.berechneVolumenFuerPolyeder(polyeder);
        double oberflaecheninhalt=polyederController.errechneOberflaecheninhaltFuerPolyeder(polyeder);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(POLYEDER_DETAILS);
        alert.setHeaderText(POLYEDER_DETAILS);
        alert.setContentText(ANZAHL_DREIECKE + polyeder.getDreiecke().size()+ABSATZ+GROESSE_VOLUMEN+Math.round(volumen)+VOLUMEN_EINHEITEN+ABSATZ+GROESSE_OBERFLAECHE+Math.round(oberflaecheninhalt)+OBERFLAECHEN_EINHEITEN);
        alert.showAndWait();
    }


    /**
     * Diese Methode zeigt den Dialog fuer die Hilfe zum Programm an
     *
     * @Precondition Nichts
     * @Postcondition Dialog wird angezeigt
     */
    private void showHilfeDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(HILFE);
        alert.setHeaderText(HILFE);
        alert.setContentText(HILFE_TEXT);
        alert.showAndWait();
    }

    /**
     * Diese Methode zeigt den Dialog fuer das Impressum an
     *
     * @Precondition Nichts
     * @Postcondition Dialog wird angezeigt
     */
    private void showImpressumDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(IMPRESUM);
        alert.setHeaderText(IMPRESUM);
        alert.setContentText(IMPRESSUM_TEXT);
        alert.showAndWait();
    }

    /**
     * Diese Methode ist fuer den Dialog zur Bewertung des Nissan 370Z verantwortlich
     *
     * @Precondition Nichts
     * @Postcondition Beim Klicken des Buttons wird man auf eine Website weitergeleitet.
     */
    private void showNissan370ZDialog()
    {
        javafx.scene.control.Dialog<Void> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle(NISSAN_370_Z);

        Slider slider = new Slider(NULL,ZEHN,NULL);
        slider.setMajorTickUnit(EINS);
        slider.setMinorTickCount(EINS);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(VIERHUNDERT);

        Label textLabel = new Label(WIE_TOLL_NISSAN_370_Z);
        textLabel.setWrapText(true);
        textLabel.setMaxWidth(Double.MAX_VALUE);


        Label sliderWert= new Label();
        slider.valueProperty().addListener((obs,oldVal,newVal)->
        {
            sliderWert.setText(MUELL);
        });

        slider.valueProperty().addListener((obs, oldVal, newVal) ->
        {
            int sliderWertAktuell = newVal.intValue();
            if(sliderWertAktuell ==NULL) 
            {
                sliderWert.setText(MUELL);
            }
            else if (sliderWertAktuell >= EINS &&sliderWertAktuell<=VIER)
            {
                sliderWert.setText(GEHT_SO);
            }
            else if (sliderWertAktuell >= FUENF && sliderWertAktuell <= SIEBEN)
            {
                sliderWert.setText(SCHON_OK);
            } else if (sliderWertAktuell >= ACHT &&sliderWertAktuell<=NEUN)
            {
                sliderWert.setText(RICHTIG_GUT);
            } else if (sliderWertAktuell == ZEHN)
            {
             sliderWert.setText(BESTES_AUTO);
            }

        });

            Button button = new Button(JETZT_KAUFEN);
        button.setOnAction(e ->
        {
            String URL = URL_AUTO;
        try
        {
            if(java.awt.Desktop.isDesktopSupported())
            {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(URL));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        });

        VBox content = new VBox(ZEHN, textLabel, slider, button,sliderWert);
        dialog.getDialogPane().setContent(content);
        content.setPrefWidth(VIERHUNDERTFUENFZIG);
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.CLOSE);
        dialog.showAndWait();
    }
}