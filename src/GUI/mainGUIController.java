package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import życie.DaneRynku;
import życie.Main;

import java.io.IOException;

//import życie.Main;

public class MainGUIController {

    AktywaController aktywaController;
    InwestorController inwestorController;
    RynkiController rynkiController;
    DaneRynku daneRynku;
    Main main;
    @FXML
    private AnchorPane Aktywa;
    @FXML
    private AnchorPane inwestorzy;
    @FXML
    private AnchorPane rynki;
    private boolean okClicked = false;
    //życie.Main main;

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }


    public void refresh() {
        aktywaController.refresh();
        inwestorController.refresh();
        rynkiController.refresh();
    }

    @FXML
    private void handleNewAktywo() {
        main.showAktywoEdit();
    }

    @FXML
    private void handleSave() {
        daneRynku.save();
    }

    @FXML
    private void handleLoad() {
        daneRynku.load();
    }

    public void setDaneRynku(DaneRynku daneRynku, Main main) {
        this.daneRynku = daneRynku;
        this.main = main;
    }

    public void aktywaController() {

        FXMLLoader loaderAktywa = new FXMLLoader();
        FXMLLoader loaderInwestor = new FXMLLoader();
        FXMLLoader loaderRynki = new FXMLLoader();
        loaderAktywa.setLocation(MainGUIController.class.getResource("aktywaOverview.fxml"));
        loaderInwestor.setLocation(MainGUIController.class.getResource("inwestorOverview.fxml"));
        loaderRynki.setLocation(MainGUIController.class.getResource("rynkiOverview.fxml"));
        try {
            Aktywa.getChildren().setAll((Node) loaderAktywa.load());
            inwestorzy.getChildren().setAll((Node) loaderInwestor.load());
            rynki.getChildren().setAll((Node) loaderRynki.load());

        } catch (IOException e) {
            System.out.println("Nie udało się załadować zawartści zakładek");
        }
        aktywaController = loaderAktywa.getController();
        inwestorController = loaderInwestor.getController();
        rynkiController = loaderRynki.getController();
        aktywaController.setDaneRynku(this.daneRynku);
        inwestorController.setDaneRynku(this.daneRynku);
        rynkiController.setDaneRynku(this.daneRynku);
    }

}
