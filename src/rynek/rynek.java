package rynek;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public abstract class Rynek implements Serializable {
    public String typRynku;
    private String nazwa;
    private float marza;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getMarza() {
        return marza;
    }

    public void setMarza(float marza) {
        this.marza = marza;
    }

    public String getTypRynku() {
        return typRynku;
    }

    public void setTypRynku(String typRynku) {
        this.typRynku = typRynku;
    }

    public SimpleStringProperty getTypRynkuPropery() {
        return new SimpleStringProperty(typRynku);
    }

    public SimpleStringProperty getNazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }
}
