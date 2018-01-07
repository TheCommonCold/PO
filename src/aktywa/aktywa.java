package aktywa;

import javafx.beans.property.SimpleStringProperty;
import rynek.Rynek;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Aktywa implements Serializable {
    private String nazwa;

    private transient Rynek Rynek;
    private List<Float> listaWartosci = new ArrayList<>();

    public Rynek getRynek() {
        return Rynek;
    }

    public void setRynek(Rynek Rynek) {
        this.Rynek = Rynek;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public SimpleStringProperty getNazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }

    public SimpleStringProperty getRynekProperty() {
        return new SimpleStringProperty(Rynek.getNazwa());
    }

    public void zapiszWartosc(float wartosc) {
        listaWartosci.add(wartosc);
    }

    public List<Float> getListaWartosci() {
        return listaWartosci;
    }

    public void setListaWartosci(List<Float> listaWartosci) {
        this.listaWartosci = listaWartosci;
    }
}
