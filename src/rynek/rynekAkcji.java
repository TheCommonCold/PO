package rynek;

import aktywa.Akcje;
import aktywa.Waluta;
import indeks.Indeks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RynekAkcji extends Rynek {

    private Waluta walutaRynku;
    private Indeks glownyIndeks = new Indeks("Indeks Glówny");
    private transient ObservableList<Indeks> listaIndeksow = FXCollections.observableArrayList();
    private List<Indeks> listaIndeksowZapis;

    public RynekAkcji(ObservableList<Waluta> walutaData) {
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultRynekAkcjiConstructor(walutaData);

    }

    public RynekAkcji(ObservableList<Waluta> walutaData, String nazwa) {
        setNazwa(nazwa);
        defaultRynekAkcjiConstructor(walutaData);
    }

    public void defaultRynekAkcjiConstructor(ObservableList<Waluta> walutaData) {
        Random generator = new Random();
        setTypRynku("Akcje");
        int ktoraWaluta = generator.nextInt(walutaData.size());
        int i = 0;
        for (Waluta currentWaluta : walutaData) {
            if (i == ktoraWaluta) {
                walutaRynku = currentWaluta;
                break;
            }
            i++;
        }
        addNowyIndeks(glownyIndeks);
    }

    public void addNewAkcja(Akcje akcja) {
        glownyIndeks.addNewSpolka(akcja);
    }

    public ObservableList<Akcje> getListAkcji() {
        return glownyIndeks.getListSpolek();
    }

    public void setListAkcji(ObservableList<Akcje> listAkcji) {
        glownyIndeks.setListSpolek(listAkcji);
    }

    public Waluta getWalutaRynku() {
        return walutaRynku;
    }

    public void setWalutaRynku(Waluta walutaRynku) {
        this.walutaRynku = walutaRynku;
    }

    public void zapis() {
        listaIndeksowZapis = new ArrayList<>(listaIndeksow);
    }

    public void wczyt() {
        listaIndeksow.clear();
        listaIndeksow.addAll(listaIndeksowZapis);
        listaIndeksowZapis = null;
    }

    private void addNowyIndeks(Indeks Indeks) {
        listaIndeksow.add(Indeks);
    }

    public ObservableList<Indeks> getListaIndeksow() {
        return listaIndeksow;
    }

    public void setListaIndeksow(ObservableList<Indeks> listaIndeksow) {
        this.listaIndeksow = listaIndeksow;
    }

}
