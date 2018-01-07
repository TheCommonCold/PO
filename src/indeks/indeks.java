package indeks;

import aktywa.Akcje;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;

public class Indeks implements Serializable {
    private String nazwa;
    private transient ObservableList<Akcje> listSpolek = FXCollections.observableArrayList();
    private List<Akcje> listaSpolekZapis;

    public Indeks(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public ObservableList<Akcje> getListSpolek() {
        return listSpolek;
    }

    public void setListSpolek(ObservableList<Akcje> listSpolek) {
        this.listSpolek = listSpolek;
    }

    public void addNewSpolka(Akcje Akcje) {
        listSpolek.add(Akcje);
    }

    public SimpleStringProperty getNazwaProperty() {
        return new SimpleStringProperty(nazwa);
    }
}
