package życie;

import java.io.IOException;

import GUI.aktywaController;
import GUI.aktywaEdytorController;
import GUI.mainGUIController;
import Nazwy.LosoweNazwy;
import javafx.stage.Modality;
import spolka.spolka;
import aktywa.cenyWalut;
import aktywa.waluta;
import aktywa.aktywa;
import kupujacy.inwestor;
import portfel.stackAkcji;
import portfel.stackSurowcow;
import portfel.stackWalut;
import rynek.rynekAkcji;
import rynek.rynekSurowcow;
import rynek.rynekWalut;
import aktywa.cenaWaluty;
import aktywa.surowiec;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import portfel.zlecenie;


public class Main extends Application {


    private BorderPane GUI;
    private Stage primaryStage;
    private DaneRynku daneRynku = new DaneRynku();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        debugRynekWalut();
        initGUI();
        //aktywaController();
        daneRynku.logicLoop();
    }

    public void initGUI(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainGUIController.class.getResource("GUI.fxml"));
            GUI = (BorderPane) loader.load();
            Scene scene = new Scene(GUI);
            mainGUIController cont = loader.getController();
            cont.setDaneRynku(this.daneRynku,this);
            cont.aktywaController();
            primaryStage.setTitle("GJEUDA");

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void debugRynekWalut(){
        for(rynekWalut currentRynek:daneRynku.getRynkiWalutData()){
            for(cenyWalut currentCenyWalut:currentRynek.getListaCen()){
                System.out.print(currentCenyWalut.getWaluta().getNazwa());
                System.out.print("\n");
                //System.out.print(currentWaluta.displayListaKrajow());
                //System.out.print("\n");
                for(cenaWaluty currentCenaWaluty:currentCenyWalut.getWartosc()){
                    System.out.print(currentCenaWaluty.getWaluta().getNazwa());
                    System.out.print(" Kupno:");
                    System.out.print(currentCenaWaluty.getCenaKupna());
                    System.out.print(" Sprzedazy:");
                    System.out.print(currentCenaWaluty.getCenaSprzedazy());
                    System.out.print("\n");

                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void debugSurowiec(){
        for(surowiec currentSurowiec:daneRynku.getSurowiecData()){
            System.out.print(currentSurowiec.getNazwa());
            System.out.print("\n");
            System.out.print(currentSurowiec.getWaluta().getNazwa());
            System.out.print("\n");
            System.out.print(currentSurowiec.getJednostkaHandlowa());
            System.out.print("\n");
            System.out.print(currentSurowiec.getWartosc());
            System.out.print("\n");
            System.out.print(currentSurowiec.getWartoscMaksymalna());
            System.out.print("\n");
            System.out.print(currentSurowiec.getWartoscMinimalna());
            System.out.print("\n");
            System.out.print("\n");
        }
    }

    public void debugZlecenia(){
        System.out.print("ZLECENIA KUPNA: ");
        System.out.print("\n");
        for(zlecenie currentZlecenieKupna:daneRynku.getZlecenia().getZleceniaKupna()){
            System.out.print(currentZlecenieKupna.getZlecacz().getImie());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceKupic().getNazwa());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceSprzedac().getNazwa());
            System.out.print("\n");
        }
        System.out.print("ZLECENIA SPRZEDAZY: ");
        System.out.print("\n");
        for(zlecenie currentZlecenieKupna:daneRynku.getZlecenia().getZleceniaSprzedazy()){
            System.out.print(currentZlecenieKupna.getZlecacz().getImie());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceSprzedac().getNazwa());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceKupic().getNazwa());
            System.out.print("\n");
        }
    }

    public void debugInwestor(){
        for(inwestor currentInwestor:daneRynku.getInwestorData()){
            System.out.print("PESEL:");
            System.out.print(currentInwestor.getPesel());
            System.out.print("\n");
            System.out.print("IMIE:");
            System.out.print(currentInwestor.getImie());
            System.out.print("\n");
            System.out.print("PORTFEL:");
            System.out.print("\n");
            for(stackSurowcow currentSurowiec:currentInwestor.getAssets().getSurowce()){
                System.out.print("surowiec:");
                System.out.print(currentSurowiec.getSurowiec().getNazwa());
                System.out.print(" ilosc:");
                System.out.print(currentSurowiec.getIlosc());
                System.out.print("\n");
            }
            for(stackWalut currentWaluta:currentInwestor.getAssets().getWaluty()){
                System.out.print("waluta:");
                System.out.print(currentWaluta.getWaluta().getNazwa());
                System.out.print(" ilosc:");
                System.out.print(currentWaluta.getIlosc());
                System.out.print("\n");
            }
            for(stackAkcji currentAkcje:currentInwestor.getAssets().getAkcje()){
                System.out.print("akcja:");
                System.out.print(currentAkcje.getAkcja().getNazwa());
                System.out.print(" ilosc:");
                System.out.print(currentAkcje.getIlosc());
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void aktywaController() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(aktywaController.class.getResource("aktywaOverview.fxml"));
            AnchorPane aktywaOverview = (AnchorPane) loader.load();
            aktywaController cont = loader.getController();
            cont.setDaneRynku(this.daneRynku);
            GUI.setCenter(aktywaOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showAktywoEdit() {
        try {
            System.out.print("test");
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(aktywaEdytorController.class.getResource("aktywoEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Aktywo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            aktywaEdytorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDaneRynku(daneRynku);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Main getMain(){return this;}

    public static void main(String[] args) {
        launch(args);
    }
}
