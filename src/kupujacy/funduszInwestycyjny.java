package kupujacy;

import Nazwy.LosoweNazwy;
import aktywa.JednostkaUczestnictwa;
import javafx.beans.property.SimpleStringProperty;
import życie.DaneRynku;

public class FunduszInwestycyjny extends PodmiotKupujacy {
    private String nazwa;
    private JednostkaUczestnictwa JednostkaUczestnictwa;

    public FunduszInwestycyjny(DaneRynku daneRynku, LosoweNazwy nazwy) {
        setImie(nazwy.getImieInwestora());
        setNazwisko(nazwy.getNazwiskoInwestora());
        setNazwa(nazwy.getNazweFunduszu());
        JednostkaUczestnictwa = new JednostkaUczestnictwa(daneRynku.getWalutaData(), this);
        defaultPodmiotKupujacyConstructor(daneRynku, "Fundusz");
    }

    public FunduszInwestycyjny(DaneRynku daneRynku, String imie, String nazwisko, String nazwa) {
        setImie(imie);
        setNazwisko(nazwisko);
        setNazwa(nazwa);
        JednostkaUczestnictwa = new JednostkaUczestnictwa(daneRynku.getWalutaData(), this);
        defaultPodmiotKupujacyConstructor(daneRynku, "Fundusz");
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public JednostkaUczestnictwa getJednostkaUczestnictwa() {
        return JednostkaUczestnictwa;
    }

    public void setJednostkaUczestnictwa(JednostkaUczestnictwa JednostkaUczestnictwa) {
        this.JednostkaUczestnictwa = JednostkaUczestnictwa;
    }

    public SimpleStringProperty getNazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }
}
