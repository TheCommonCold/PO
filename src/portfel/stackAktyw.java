package portfel;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class StackAktyw implements Serializable {
    private float ilosc;

    public float getIlosc() {
        return ilosc;
    }

    public void setIlosc(float ilosc) {
        this.ilosc = ilosc;
    }

    public void subtractIlosc(float liczba) {
        this.ilosc -= liczba;
    }

    public void addIlosc(float liczba) {
        this.ilosc += liczba;
    }

    public SimpleStringProperty getIloscProperty() {
        return new SimpleStringProperty(Float.toString(ilosc));
    }
}
