package aktywa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

public class cenyWalut {
    private waluta waluta;
    private ObservableList<cenaWaluty> wartosc = FXCollections.observableArrayList();;

    public cenyWalut(waluta waluta){
        this.waluta = waluta;
    }

    public void addWaluta(waluta waluta,float cenaKupna, float cenaSprzedazy){
        wartosc.add(new cenaWaluty(waluta,cenaKupna,cenaSprzedazy));
    }

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
    }

    public ObservableList<cenaWaluty>  getWartosc() {
        return wartosc;
    }

    public void setWartosc(ObservableList<cenaWaluty>  wartosc) {
        this.wartosc = wartosc;
    }
}
