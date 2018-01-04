package GUI;

import aktywa.cenaWaluty;
import aktywa.waluta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import rynek.rynekWalut;
import życie.DaneRynku;


public class walutaController {
    @FXML
    private TableView<waluta> walutaTable;
    @FXML
    private TableView<cenaWaluty> cenyTable;

    @FXML
    private TableColumn<waluta, String> nazwaColumn;
    @FXML
    private TableColumn<cenaWaluty, String> nazwaInnejWalutyColumn;
    @FXML
    private TableColumn<cenaWaluty, String> cenaKupnaColumn;
    @FXML
    private TableColumn<cenaWaluty, String> cenaSprzedazyColumn;

    @FXML
    private TableColumn<waluta, String> rynekColumn;

    @FXML
    private Label nazwaLabel;
    @FXML
    private Label rynekLabel;
    @FXML
    private Label listaKrajowLabel;



    DaneRynku daneRynku;

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
        walutaTable.setItems(daneRynku.getWalutaData());
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        rynekColumn.setCellValueFactory(cellData -> cellData.getValue().getRynekProperty());
        walutaTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showWaluta(newValue) )
        );
    }

    private void showWaluta(waluta waluta){
        if(waluta!=null){
            cenyTable.setItems((rynekWalut)(waluta.getRynek()).get)
            nazwaLabel.setText(waluta.getNazwa());
            listaKrajowLabel.setText(waluta.displayListaKrajow());
        }
    }
}
