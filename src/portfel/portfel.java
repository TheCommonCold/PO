package portfel;

import aktywa.aktywa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.*;
public class portfel {
    private ObservableList<stackAkcji> akcje;
    private ObservableList<stackWalut> waluty;
    private ObservableList<stackSurowcow> surowce;

    public portfel(){
        akcje = FXCollections.observableArrayList();
        waluty = FXCollections.observableArrayList();
        surowce = FXCollections.observableArrayList();
    }

    public stackWalut getStackWaluty(aktywa waluta){
        for(stackWalut currentStackWalut:waluty){
            if(currentStackWalut.getWaluta().equals(waluta)){
                return currentStackWalut;
            }
        }
        return null;
    }
    public stackSurowcow getStackSurowcow(aktywa surowiec){
        for(stackSurowcow currentStackSurowcow:surowce){
            if(currentStackSurowcow.getSurowiec().equals(surowiec)){
                return currentStackSurowcow;
            }
        }
        return null;
    }

    public stackAkcji getStackAkcji(aktywa akcja){
        for(stackAkcji currentStackAkcji:akcje){
            if(currentStackAkcji.getAkcja().equals(akcja)){
                return currentStackAkcji;
            }
        }
        return null;
    }

    public void addNowaAkcja(stackAkcji akcje){
        this.akcje.add(akcje);
    }

    public void addNowySurowiec(stackSurowcow surowiec){
        this.surowce.add(surowiec);
    }

    public void addNowaWaluta(stackWalut waluta){
        this.waluty.add(waluta);
    }

    public ObservableList<stackAkcji> getAkcje() {
        return akcje;
    }

    public void setAkcje(ObservableList<stackAkcji> akcje) {
        this.akcje = akcje;
    }

    public ObservableList<stackWalut> getWaluty() {
        return waluty;
    }

    public void setWaluty(ObservableList<stackWalut> waluty) {
        this.waluty = waluty;
    }

    public ObservableList<stackSurowcow> getSurowce() {
        return surowce;
    }

    public void setSurowce(ObservableList<stackSurowcow> surowce) {
        this.surowce = surowce;
    }
}
