package życie;

import GUI.AktywaController;
import GUI.AktywaEdytorController;
import GUI.MainGUIController;
import aktywa.CenaWaluty;
import aktywa.CenyWalut;
import aktywa.Surowiec;
import aktywa.Waluta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kupujacy.Inwestor;
import portfel.StackAkcji;
import portfel.StackSurowcow;
import portfel.StackWalut;
import portfel.Zlecenie;
import rynek.RynekWalut;

import java.io.IOException;


public class Main extends Application {


    MainGUIController cont;
    private BorderPane GUI;
    private Stage primaryStage;
    private DaneRynku daneRynku = new DaneRynku(this);

    public static void main(String[] args) {
        launch(args);
    }

    public void refresh() {
        cont.refresh();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        debugRynekWalut();
        initGUI();
        RynekWalut protoRynek = new RynekWalut(daneRynku.getNazwy().getNazweRynkuWalut());
        daneRynku.addRynkiWalutData(protoRynek);
        daneRynku.addwalutaData(new Waluta(protoRynek, daneRynku.getNazwy(), daneRynku.getNazwy().getNazweWaluty()));
        //AktywaController();
        daneRynku.logicLoop();
    }

    public void initGUI() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGUIController.class.getResource("GUI.fxml"));
            GUI = (BorderPane) loader.load();
            Scene scene = new Scene(GUI);
            scene.getStylesheets().add("CSS/CSS.css");
            cont = loader.getController();
            cont.setDaneRynku(this.daneRynku, this);
            cont.aktywaController();
            primaryStage.setTitle("GJEUDA");

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void debugRynekWalut() {
        for (RynekWalut currentRynek : daneRynku.getRynkiWalutData()) {
            for (CenyWalut currentCenyWalut : currentRynek.getListaCen()) {
                System.out.print(currentCenyWalut.getWaluta().getNazwa());
                System.out.print("\n");
                //System.out.print(currentWaluta.displayListaKrajow());
                //System.out.print("\n");
                for (CenaWaluty currentCenaWaluty : currentCenyWalut.getWartosc()) {
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

    public void debugSurowiec() {
        for (Surowiec currentSurowiec : daneRynku.getSurowiecData()) {
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

    public void debugZlecenia() {
        System.out.print("ZLECENIA KUPNA: ");
        System.out.print("\n");
        for (Zlecenie currentZlecenieKupna : daneRynku.getZlecenia().getZleceniaKupna()) {
            System.out.print(currentZlecenieKupna.getZlecacz().getImie());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceKupic().getNazwa());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceSprzedac().getNazwa());
            System.out.print("\n");
        }
        System.out.print("ZLECENIA SPRZEDAZY: ");
        System.out.print("\n");
        for (Zlecenie currentZlecenieKupna : daneRynku.getZlecenia().getZleceniaSprzedazy()) {
            System.out.print(currentZlecenieKupna.getZlecacz().getImie());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceSprzedac().getNazwa());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceKupic().getNazwa());
            System.out.print("\n");
        }
    }

    public void debugInwestor() {
        for (Inwestor currentInwestor : daneRynku.getInwestorData()) {
            System.out.print("PESEL:");
            System.out.print(currentInwestor.getPesel());
            System.out.print("\n");
            System.out.print("IMIE:");
            System.out.print(currentInwestor.getImie());
            System.out.print("\n");
            System.out.print("PORTFEL:");
            System.out.print("\n");
            for (StackSurowcow currentSurowiec : currentInwestor.getAssets().getSurowce()) {
                System.out.print("Surowiec:");
                System.out.print(currentSurowiec.getSurowiec().getNazwa());
                System.out.print(" ilosc:");
                System.out.print(currentSurowiec.getIlosc());
                System.out.print("\n");
            }
            for (StackWalut currentWaluta : currentInwestor.getAssets().getWaluty()) {
                System.out.print("Waluta:");
                System.out.print(currentWaluta.getWaluta().getNazwa());
                System.out.print(" ilosc:");
                System.out.print(currentWaluta.getIlosc());
                System.out.print("\n");
            }
            for (StackAkcji currentAkcje : currentInwestor.getAssets().getAkcje()) {
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
            loader.setLocation(AktywaController.class.getResource("aktywaOverview.fxml"));
            AnchorPane aktywaOverview = (AnchorPane) loader.load();
            AktywaController cont = loader.getController();
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
            loader.setLocation(AktywaEdytorController.class.getResource("aktywoEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Aktywo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AktywaEdytorController controller = loader.getController();
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

    public Main getMain() {
        return this;
    }
}
