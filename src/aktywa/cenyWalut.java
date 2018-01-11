package aktywa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CenyWalut implements Serializable {
    private Waluta Waluta;
    private List<CenaWaluty> wartosc = new ArrayList<>();
    private List<CenaWaluty> wartoscZapis;

    public CenyWalut(Waluta Waluta) {
        this.Waluta = Waluta;
    }

    public void addWaluta(Waluta Waluta, float cenaKupna, float cenaSprzedazy) {
        wartosc.add(new CenaWaluty(Waluta, cenaKupna, cenaSprzedazy));
    }

    public Waluta getWaluta() {
        return Waluta;
    }

    public void setWaluta(Waluta Waluta) {
        this.Waluta = Waluta;
    }

    public ObservableList<CenaWaluty> getWartosc() {
        return FXCollections.observableArrayList(wartosc);
    }

    public void setWartosc(ObservableList<CenaWaluty> wartosc) {
        this.wartosc = wartosc;
    }
}
