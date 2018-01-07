package GUI;

import aktywa.Akcje;
import indeks.Indeks;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import rynek.Rynek;
import rynek.RynekAkcji;
import życie.DaneRynku;


public class RynkiController {
    DaneRynku daneRynku;

    @FXML
    private TableView<Rynek> rynkiTable;
    @FXML
    private TableColumn<Rynek, String> nazwaColumn;
    @FXML
    private TableColumn<Rynek, String> typColumn;

    @FXML
    private TableView<Indeks> indeksTable;
    @FXML
    private TableColumn<Indeks, String> nazwaIndeksuColumn;

    @FXML
    private TableView<Akcje> akcjeTable;
    @FXML
    private TableColumn<Akcje, String> nazwaakcjeColumn;

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
        rynkiTable.setItems(daneRynku.getRynekData());
    }

    public void initialize() {
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        typColumn.setCellValueFactory(cellData -> cellData.getValue().getTypRynkuPropery());
        rynkiTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showRynki(newValue))
        );
    }

    public void showRynki(Rynek Rynek) {
        akcjeTable.setVisible(false);
        indeksTable.setVisible(false);
        if (Rynek instanceof RynekAkcji) {
            akcjeTable.setVisible(true);
            indeksTable.setVisible(true);
            indeksTable.setItems(((RynekAkcji) Rynek).getListaIndeksow());
            nazwaIndeksuColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
            indeksTable.getSelectionModel().selectedItemProperty().addListener(
                    ((observable, oldValue, newValue) -> showAkcje(newValue))
            );
        }
    }

    public void showAkcje(Indeks Indeks) {
        akcjeTable.setItems(Indeks.getListSpolek());
        nazwaakcjeColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
    }

    public void hideStuff() {
        akcjeTable.setVisible(false);
        indeksTable.setVisible(false);
    }

    public void refresh() {
        rynkiTable.refresh();
        indeksTable.refresh();
        akcjeTable.refresh();
    }
}
