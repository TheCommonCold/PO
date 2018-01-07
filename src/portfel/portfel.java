package portfel;

import aktywa.Aktywa;
import aktywa.JednostkaUczestnictwa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Portfel implements Serializable {
    private transient ObservableList<StackAkcji> akcje;
    private transient ObservableList<StackWalut> waluty;
    private transient ObservableList<StackSurowcow> surowce;
    private transient ObservableList<StackJednostekUczestnictwa> jednostkiUczestnictwa;

    private List<StackAkcji> akcjeZapis;
    private List<StackWalut> walutyZapis;
    private List<StackSurowcow> surowceZapis;
    private List<StackJednostekUczestnictwa> jednostekUczestnictwaZapis;

    public Portfel() {
        akcje = FXCollections.observableArrayList();
        waluty = FXCollections.observableArrayList();
        surowce = FXCollections.observableArrayList();
        jednostkiUczestnictwa = FXCollections.observableArrayList();
    }

    public void zapis() {
        akcjeZapis = new ArrayList<>(akcje);
        walutyZapis = new ArrayList<>(waluty);
        surowceZapis = new ArrayList<>(surowce);
        jednostekUczestnictwaZapis = new ArrayList<>(jednostkiUczestnictwa);
    }

    public void wczyt() {
        akcje.clear();
        waluty.clear();
        surowce.clear();
        jednostkiUczestnictwa.clear();
        akcje.addAll(akcjeZapis);
        waluty.addAll(walutyZapis);
        surowce.addAll(surowceZapis);
        jednostkiUczestnictwa.addAll(jednostekUczestnictwaZapis);
        akcjeZapis = null;
        walutyZapis = null;
        surowceZapis = null;
        jednostekUczestnictwaZapis = null;
    }

    public StackWalut getStackWaluty(Aktywa waluta) {
        for (StackWalut currentStackWalut : waluty) {
            if (currentStackWalut.getWaluta().equals(waluta)) {
                return currentStackWalut;
            }
        }
        return null;
    }

    public StackSurowcow getStackSurowcow(Aktywa surowiec) {
        for (StackSurowcow currentStackSurowcow : surowce) {
            if (currentStackSurowcow.getSurowiec().equals(surowiec)) {
                return currentStackSurowcow;
            }
        }
        return null;
    }

    public StackAkcji getStackAkcji(Aktywa akcja) {
        for (StackAkcji currentStackAkcji : akcje) {
            if (currentStackAkcji.getAkcja().equals(akcja)) {
                return currentStackAkcji;
            }
        }
        return null;
    }

    public StackJednostekUczestnictwa getStackJednostekUczestnictwa(JednostkaUczestnictwa JednostkaUczestnictwa) {
        for (StackJednostekUczestnictwa currentStackJednostek : jednostkiUczestnictwa) {
            if (currentStackJednostek.getJednostkaUczestnictwa().equals(JednostkaUczestnictwa)) {
                return currentStackJednostek;
            }
        }
        return null;
    }

    public void addNowaAkcja(StackAkcji akcje) {
        this.akcje.add(akcje);
    }

    public void addNowySurowiec(StackSurowcow surowiec) {
        this.surowce.add(surowiec);
    }

    public void addNowaWaluta(StackWalut waluta) {
        this.waluty.add(waluta);
    }

    public void addNowaJednostkaUczestnictwa(StackJednostekUczestnictwa jednostkaUczestnictwa) {
        this.jednostkiUczestnictwa.add(jednostkaUczestnictwa);
    }

    public ObservableList<StackAkcji> getAkcje() {
        return akcje;
    }

    public void setAkcje(ObservableList<StackAkcji> akcje) {
        this.akcje = akcje;
    }

    public ObservableList<StackWalut> getWaluty() {
        return waluty;
    }

    public void setWaluty(ObservableList<StackWalut> waluty) {
        this.waluty = waluty;
    }

    public ObservableList<StackSurowcow> getSurowce() {
        return surowce;
    }

    public void setSurowce(ObservableList<StackSurowcow> surowce) {
        this.surowce = surowce;
    }

    public ObservableList<StackJednostekUczestnictwa> getJednostkiUczestnictwa() {
        return jednostkiUczestnictwa;
    }

    public void setJednostkiUczestnictwa(ObservableList<StackJednostekUczestnictwa> jednostkiUczestnictwa) {
        this.jednostkiUczestnictwa = jednostkiUczestnictwa;
    }
}
