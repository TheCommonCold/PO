package aktywa;

import javafx.beans.property.SimpleStringProperty;

public class cenaWaluty {
    private waluta waluta;
    private float cenaKupna;
    private float cenaSprzedazy;

    cenaWaluty(waluta waluta,float cenaKupna, float cenaSprzedazy){
        this.waluta=waluta;
        this.cenaKupna=cenaKupna;
        this.cenaSprzedazy=cenaSprzedazy;
    }

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
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

    public SimpleStringProperty getCenaKupnaProperty(){
        return new SimpleStringProperty(Float.toString(cenaKupna));
    }
    public SimpleStringProperty getCenaSprzedazyProperty(){
        return new SimpleStringProperty(Float.toString(cenaSprzedazy));
    }

}
