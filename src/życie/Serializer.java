package życie;

import aktywa.Akcje;
import aktywa.Aktywa;
import aktywa.Surowiec;
import aktywa.Waluta;
import javafx.collections.ObservableList;
import kupujacy.FunduszInwestycyjny;
import kupujacy.Inwestor;
import kupujacy.PodmiotKupujacy;
import rynek.Rynek;
import rynek.RynekAkcji;
import rynek.RynekSurowcow;
import rynek.RynekWalut;

import java.io.*;
import java.util.ArrayList;

public class Serializer {
    ObjectOutputStream save;
    DaneRynku daneRynku;

    public Serializer(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
    }

    public void save() {
        try {
            save = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\Public\\Documents\\zapis.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie mozna stworzyc pliku zapisu");
        }
        try {
            for (RynekAkcji currentRynekAkcji : daneRynku.getRynkiAkcjiData()) {
                currentRynekAkcji.zapis();
            }
            for (PodmiotKupujacy currentPodmiot : daneRynku.getPodmiotKupujacyData()) {
                currentPodmiot.getAssets().zapis();
            }
            save.writeObject(new ArrayList<>(daneRynku.getAktywaData()));
            save.writeObject(new ArrayList<>(daneRynku.getAkcjeData()));
            save.writeObject(new ArrayList<>(daneRynku.getWalutaData()));
            save.writeObject(new ArrayList<>(daneRynku.getSurowiecData()));
            save.writeObject(new ArrayList<>(daneRynku.getRynekData()));
            save.writeObject(new ArrayList<>(daneRynku.getRynkiAkcjiData()));
            save.writeObject(new ArrayList<>(daneRynku.getRynkiWalutData()));
            save.writeObject(new ArrayList<>(daneRynku.getRynkiSurowcowData()));
            save.writeObject(new ArrayList<>(daneRynku.getPodmiotKupujacyData()));
            save.writeObject(new ArrayList<>(daneRynku.getInwestorData()));
            save.writeObject(new ArrayList<>(daneRynku.getFunduszInwestycyjnyData()));
            save.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("somthing went wrong");
        }
    }

    public void load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("C:\\Users\\Public\\Documents\\zapis.txt")));
            daneRynku.setAktywaData(((ObservableList<Aktywa>) in.readObject()));
            daneRynku.setAkcjeData(((ObservableList<Akcje>) in.readObject()));
            daneRynku.setWalutaData(((ObservableList<Waluta>) in.readObject()));
            daneRynku.setSurowiecData(((ObservableList<Surowiec>) in.readObject()));
            daneRynku.setRynekData(((ObservableList<Rynek>) in.readObject()));
            daneRynku.setRynkiAkcjiData(((ObservableList<RynekAkcji>) in.readObject()));
            daneRynku.setRynkiWalutData(((ObservableList<RynekWalut>) in.readObject()));
            daneRynku.setRynkiSurowcowData(((ObservableList<RynekSurowcow>) in.readObject()));
            daneRynku.setPodmiotKupujacyData(((ObservableList<PodmiotKupujacy>) in.readObject()));
            daneRynku.setInwestorData(((ObservableList<Inwestor>) in.readObject()));
            daneRynku.setFunduszInwestycyjnyData(((ObservableList<FunduszInwestycyjny>) in.readObject()));
            for (PodmiotKupujacy currentPodmiot : daneRynku.getPodmiotKupujacyData()) {
                currentPodmiot.getAssets().wczyt();
            }
            for (RynekAkcji currentRynekAkcji : daneRynku.getRynkiAkcjiData()) {
                currentRynekAkcji.wczyt();
            }


            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("cos poszlo nietak");
        }
    }

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
    }

}
