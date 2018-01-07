package aktywa;

import Nazwy.LosoweNazwy;
import javafx.collections.ObservableList;
import rynek.RynekSurowcow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Surowiec extends Aktywa {
    private String jednostkaHandlowa;
    private Waluta Waluta;
    private float wartosc;
    private float wartoscMinimalna;
    private float wartoscMaksymalna;
    private float wartoscPoczatkowa;
    private List<Float> listaWartosci = new ArrayList<>();

    public Surowiec(RynekSurowcow rynek, ObservableList<Waluta> walutaData, LosoweNazwy nazwy) {
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultSurowiecConstructor(rynek, walutaData, nazwy);
    }

    public Surowiec(RynekSurowcow rynek, ObservableList<Waluta> walutaData, LosoweNazwy nazwy, String nazwa) {
        setNazwa(nazwa);
        defaultSurowiecConstructor(rynek, walutaData, nazwy);
    }

    public void defaultSurowiecConstructor(RynekSurowcow rynek, ObservableList<Waluta> walutaData, LosoweNazwy nazwy) {
        Random generator = new Random();
        jednostkaHandlowa = nazwy.getNazweJednostki();
        int stop = generator.nextInt(walutaData.size());
        int i = 0;
        for (Waluta Waluta : walutaData) {
            if (i == stop) {
                this.Waluta = Waluta;
                break;
            }
            i++;
        }
        setRynek(rynek);
        wartosc = generator.nextInt(10000) + generator.nextFloat();
        wartoscMinimalna = wartosc;
        wartoscMaksymalna = wartosc;
        wartoscPoczatkowa = wartosc;
        rynek.addNewSurowiec(this);
    }

    public String getJednostkaHandlowa() {
        return jednostkaHandlowa;
    }

    public void setJednostkaHandlowa(String jednostkaHandlowa) {
        this.jednostkaHandlowa = jednostkaHandlowa;
    }

    public Waluta getWaluta() {
        return Waluta;
    }

    public void setWaluta(Waluta Waluta) {
        this.Waluta = Waluta;
    }

    public float getWartosc() {
        return wartosc;
    }

    public void setWartosc(float wartosc) {

        this.wartosc = wartosc;
        if (wartosc > wartoscMaksymalna) wartoscMaksymalna = wartosc;
        if (wartosc < wartoscMinimalna) wartoscMinimalna = wartosc;
    }

    public float getWartoscMinimalna() {
        return wartoscMinimalna;
    }

    public void setWartoscMinimalna(int wartoscMinimalna) {
        this.wartoscMinimalna = wartoscMinimalna;
    }

    public float getWartoscMaksymalna() {
        return wartoscMaksymalna;
    }

    public void setWartoscMaksymalna(int wartoscMaksymalna) {
        this.wartoscMaksymalna = wartoscMaksymalna;
    }

    public float getWartoscPoczatkowa() {
        return wartoscPoczatkowa;
    }

    public void setWartoscPoczatkowa(float wartoscPoczatkowa) {
        this.wartoscPoczatkowa = wartoscPoczatkowa;
    }
}
