package aktywa;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CenaWaluty implements Serializable {
    private Waluta Waluta;
    private float cenaKupna;
    private float cenaSprzedazy;

    private List<Float> listaWartosciKupna = new ArrayList<>();
    private List<Float> listaWartosciSprzedazy = new ArrayList<>();

    CenaWaluty(Waluta Waluta, float cenaKupna, float cenaSprzedazy) {
        this.Waluta = Waluta;
        this.cenaKupna = cenaKupna;
        this.cenaSprzedazy = cenaSprzedazy;
    }

    public void zapiszWartoscKupna() {
        listaWartosciKupna.add(cenaKupna);
    }

    public List<Float> getListaWartosciKupna() {
        return listaWartosciKupna;
    }

    public void setListaWartosciKupna(List<Float> listaWartosciKupna) {
        this.listaWartosciKupna = listaWartosciKupna;
    }

    public void zapiszWartoscSprzedazy() {
        listaWartosciSprzedazy.add(cenaSprzedazy);
    }

    public List<Float> getListaWartosciSprzedazy() {
        return listaWartosciSprzedazy;
    }

    public void setListaWartosciSprzedazy(List<Float> listaWartosciSprzedazy) {
        this.listaWartosciSprzedazy = listaWartosciSprzedazy;
    }

    public Waluta getWaluta() {
        return Waluta;
    }

    public void setWaluta(Waluta Waluta) {
        this.Waluta = Waluta;
    }

    public float getCenaKupna() {
        return cenaKupna;
    }

    public void setCenaKupna(float cenaKupna) {
        this.cenaKupna = cenaKupna;
    }

    public float getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public void setCenaSprzedazy(float cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }

    public SimpleStringProperty getCenaKupnaProperty() {
        return new SimpleStringProperty(Float.toString(cenaKupna));
    }

    public SimpleStringProperty getCenaSprzedazyProperty() {
        return new SimpleStringProperty(Float.toString(cenaSprzedazy));
    }

}
