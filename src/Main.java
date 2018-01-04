import java.io.IOException;

import spolka.spolka;
import GUI.aktywaController;
import aktywa.cenyWalut;
import aktywa.waluta;
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
import życie.DaneRynku;


public class Main extends Application {


    private BorderPane GUI;
    private Stage primaryStage;
    private DaneRynku daneRynku = new DaneRynku();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        daneRynku.addRynkiWalutData(new rynekWalut("Forex"));
        daneRynku.addRynkiSurowiecData(new rynekSurowcow("Rynek1"));
        rynekWalut rynek = new rynekWalut();
        for(rynekWalut currentRynek:daneRynku.getRynkiWalutData())rynek=currentRynek;
        daneRynku.addwalutaData(new waluta(rynek,"Dollar"));
        daneRynku.addwalutaData(new waluta(rynek,"Pesos"));
        daneRynku.addwalutaData(new waluta(rynek,"Schmeckle"));
        rynekSurowcow rynekSurowcow = new rynekSurowcow();
        for(rynekSurowcow currentRynek:daneRynku.getRynkiSurowcowData())rynekSurowcow=currentRynek;
        daneRynku.addsurowiecData(new surowiec(rynekSurowcow,daneRynku.getWalutaData(),"memy"));
        daneRynku.addsurowiecData(new surowiec(rynekSurowcow,daneRynku.getWalutaData(),"Zloto"));
        daneRynku.addRynkiAkcjiData(new rynekAkcji(daneRynku.getWalutaData(),"Rynek1"));
        rynekAkcji rynekAkcji=new rynekAkcji(daneRynku.getWalutaData());
        for(rynekAkcji currentRynek:daneRynku.getRynkiAkcjiData())rynekAkcji=currentRynek;
        daneRynku.addspolkaData(new spolka(rynekAkcji,daneRynku.getInwestorData(),daneRynku.getWalutaData(),daneRynku.getSurowiecData(),daneRynku.getAkcjeData(),daneRynku.getRatioKupujacychDoAktyw(),"Apple"));

        debugRynekWalut();
        initGUI();
        walutaCreator();
        Scanner input = new Scanner(System.in);
//
//        daneRynku.addwalutaData(new waluta("Dollar"));
//        daneRynku.addwalutaData(new waluta("Pesos"));
//       // daneRynku.addwalutaData(new waluta("Schmeckle"));
//
//        daneRynku.addRynkiAkcjiData(new rynekAkcji(daneRynku.getWalutaData(),"Rynek1"));
//
//        daneRynku.addspolkaData(new spolka(daneRynku.getRynkiAkcjiData(),daneRynku.getInwestorData(),daneRynku.getWalutaData(),daneRynku.getSurowiecData(),daneRynku.getAkcjeData(),daneRynku.getRatioKupujacychDoAktyw(),"Apple"));
//
//        daneRynku.addRynkiWalutData(new rynekWalut(daneRynku.getWalutaData(),"Rynek1"));
//
//        //daneRynku.addsurowiecData(new surowiec(daneRynku.getWalutaData(),"zloto"));
//        //daneRynku.addsurowiecData(new surowiec(daneRynku.getWalutaData(),"srebro"));
//        daneRynku.addsurowiecData(new surowiec(daneRynku.getWalutaData(),"memy"));
//
//        daneRynku.addRynkiSurowiecData(new rynekSurowcow(daneRynku.getSurowiecData(),"Rynek1"));
//
//
//        while(input.nextLine().equals("1")){
//            daneRynku.setLiczbaAktyw(daneRynku.getWalutaData().size()+daneRynku.getSurowiecData().size()+daneRynku.getSpolkaData().size());
//            daneRynku.setLiczbaKupujacych(daneRynku.getInwestorData().size());
//            daneRynku.setLiczbaWalut(daneRynku.getWalutaData().size());
//            daneRynku.updateRynkiWalutData();
//            if(daneRynku.getLiczbaAktyw()*daneRynku.getRatioKupujacychDoAktyw()>daneRynku.getLiczbaKupujacych()){
//                for(int i = daneRynku.getLiczbaKupujacych();i<daneRynku.getLiczbaAktyw()*daneRynku.getRatioKupujacychDoAktyw();i++){
//                    daneRynku.addinwestorData(new inwestor(daneRynku.getSurowiecData(),daneRynku.getWalutaData()));
//                }
//            }
//            daneRynku.kupowanie();
//            daneRynku.sprzedawanie();
//            debugSurowiec();
//            debugRynekWalut();
//            debugInwestor();
//            debugZlecenia();
//            daneRynku.wykonajOperacjeKupnaSprzedazy();
//            daneRynku.getZlecenia().resetZlecenia();
//        }
    }

    public void initGUI(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("GUI/GUI.fxml"));
            GUI = (BorderPane) loader.load();
            Scene scene = new Scene(GUI);

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

    public void walutaCreator() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("GUI/aktywaOverview.fxml"));
            AnchorPane aktywaOverview = (AnchorPane) loader.load();
            aktywaController cont = loader.getController();
            cont.setDaneRynku(this.daneRynku);
            GUI.setCenter(aktywaOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
