package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import życie.DaneRynku;
import życie.Main;

import java.io.IOException;

//import życie.Main;

public class mainGUIController {

    @FXML
    private AnchorPane aktywa;
    @FXML
    private AnchorPane inwestorzy;
    @FXML
    private AnchorPane rynki;

    DaneRynku daneRynku;
    Main main;
    private boolean okClicked = false;
    //życie.Main main;

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }


    @FXML
    private void handleNewAktywo() {
        main.showAktywoEdit();
    }


    public void setDaneRynku(DaneRynku daneRynku,Main main) {
        this.daneRynku = daneRynku;
        this.main=main;
    }

    public void aktywaController(){

        FXMLLoader loaderAktywa = new FXMLLoader();
        FXMLLoader loaderInwestor = new FXMLLoader();
        FXMLLoader loaderRynki = new FXMLLoader();
        loaderAktywa.setLocation(mainGUIController.class.getResource("aktywaOverview.fxml"));
        loaderInwestor.setLocation(mainGUIController.class.getResource("inwestorOverview.fxml"));
        loaderRynki.setLocation(mainGUIController.class.getResource("rynkiOverview.fxml"));
        try {
            aktywa.getChildren().setAll((Node) loaderAktywa.load());
            inwestorzy.getChildren().setAll((Node) loaderInwestor.load());
            rynki.getChildren().setAll((Node) loaderInwestor.load());

        }
        catch(IOException e){
            System.out.println("Nie udało się załadować zawartści zakładek");
        }
        aktywaController cont1 = loaderAktywa.getController();
        InwestorController cont2 = loaderInwestor.getController();
        RynkiController cont3 = loaderRynki.getController();
        cont1.setDaneRynku(this.daneRynku);
        cont2.setDaneRynku(this.daneRynku);
        cont3.setDaneRynku(this.daneRynku);
    }

}
