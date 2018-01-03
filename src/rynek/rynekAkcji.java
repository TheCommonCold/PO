package rynek;
import aktywa.akcje;
import aktywa.waluta;
import javafx.collections.ObservableList;

import java.util.*;
public class rynekAkcji extends rynek{

    public rynekAkcji(ObservableList<waluta> walutaData){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultRynekAkcjiConstructor(walutaData);

    }

    public rynekAkcji(ObservableList<waluta> walutaData,String nazwa){
        setNazwa(nazwa);
        defaultRynekAkcjiConstructor(walutaData);
    }

    public void defaultRynekAkcjiConstructor (ObservableList<waluta> walutaData){
        Random generator = new Random();
        int ktoraWaluta = generator.nextInt(walutaData.size());
        int i=0;
        for(waluta currentWaluta:walutaData){
            if(i==ktoraWaluta){
                walutaRynku=currentWaluta;
                break;
            }
            i++;
        }
    }

    public void addNewAkcja(akcje akcja){
        listAkcji.add(akcja);
    }

    private waluta walutaRynku;

    public Set<akcje> getListAkcji() {
        return listAkcji;
    }

    public void setListAkcji(Set<akcje> listAkcji) {
        this.listAkcji = listAkcji;
    }

    private Set<akcje> listAkcji = new HashSet<>();

    public waluta getWalutaRynku() {
        return walutaRynku;
    }

    public void setWalutaRynku(waluta walutaRynku) {
        this.walutaRynku = walutaRynku;
    }
}
