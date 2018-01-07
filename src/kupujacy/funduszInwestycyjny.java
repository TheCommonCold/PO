package kupujacy;

import Nazwy.LosoweNazwy;
import aktywa.jednostkaUczestnictwa;
import aktywa.surowiec;
import aktywa.waluta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class funduszInwestycyjny extends podmiotKupujacy {
    private String nazwa;
    private jednostkaUczestnictwa jednostkaUczestnictwa;

    public funduszInwestycyjny(ObservableList<surowiec> surowiecData, ObservableList<waluta> walutaData, LosoweNazwy nazwy){
        setImie(nazwy.getImieInwestora());
        setNazwisko(nazwy.getNazwiskoInwestora());
        setNazwa(nazwy.getNazweFunduszu());
        jednostkaUczestnictwa=new jednostkaUczestnictwa(walutaData,this);
        defaultPodmiotKupujacyConstructor(surowiecData,walutaData,"Fundusz");
    }

    public funduszInwestycyjny(ObservableList<surowiec> surowiecData,ObservableList<waluta> walutaData,String imie,String nazwisko,String nazwa){
        setImie(imie);
        setNazwisko(nazwisko);
        setNazwa(nazwa);
        jednostkaUczestnictwa=new jednostkaUczestnictwa(walutaData,this);
        defaultPodmiotKupujacyConstructor(surowiecData,walutaData,"Fundusz");
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public aktywa.jednostkaUczestnictwa getJednostkaUczestnictwa() {
        return jednostkaUczestnictwa;
    }

    public void setJednostkaUczestnictwa(aktywa.jednostkaUczestnictwa jednostkaUczestnictwa) {
        this.jednostkaUczestnictwa = jednostkaUczestnictwa;
    }

    public SimpleStringProperty getNazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }
}
