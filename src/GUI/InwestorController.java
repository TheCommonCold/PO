package GUI;

import aktywa.aktywa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import kupujacy.inwestor;
import portfel.stackAkcji;
import portfel.stackAktyw;
import portfel.stackSurowcow;
import portfel.stackWalut;
import życie.DaneRynku;

public class InwestorController {

    @FXML
    private TableView<inwestor> inwestorTable;
    @FXML
    private TableView<stackWalut> walutaTable;
    @FXML
    private TableView<stackSurowcow> surowiecTable;
    @FXML
    private TableView<stackAkcji> akcjaTable;
    @FXML
    private TableColumn<inwestor, String> imieColumn;

    @FXML
    private TableColumn<inwestor, String> nazwiskoColumn;
    @FXML
    private TableColumn<inwestor, String> peselColumn;

    @FXML
    private TableColumn<stackWalut, String> walutaColumn;
    @FXML
    private TableColumn<stackSurowcow, String> surowiecColumn;
    @FXML
    private TableColumn<stackAkcji, String> akcjaColumn;

    @FXML
    private TableColumn<stackWalut, String> iloscWalutaColumn;
    @FXML
    private TableColumn<stackSurowcow, String> iloscSurowiecColumn;
    @FXML
    private TableColumn<stackAkcji, String> iloscAkcjaColumn;

    @FXML
    private Label imieLabel;
    @FXML
    private Label nazwiskoLabel;
    @FXML
    private Label peselLabel;

    DaneRynku daneRynku;


    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
        inwestorTable.setItems(daneRynku.getInwestorData());
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        imieColumn.setCellValueFactory(cellData -> cellData.getValue().getImieProperty());
        nazwiskoColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwiskoProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().getPeselProperty());
        inwestorTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showInwestor(newValue) )
        );
    }

    public void showInwestor(inwestor inwestor){
        imieLabel.setText(inwestor.getImie());
        nazwiskoLabel.setText(inwestor.getNazwisko());
        peselLabel.setText(Double.toString(inwestor.getPesel()));
        walutaTable.setItems(inwestor.getAssets().getWaluty());
        surowiecTable.setItems(inwestor.getAssets().getSurowce());
        walutaTable.setItems(inwestor.getAssets().getWaluty());
        akcjaTable.setItems(inwestor.getAssets().getAkcje());
        walutaColumn.setCellValueFactory(cellData -> cellData.getValue().getWaluta().getNazwaProperty());
        surowiecColumn.setCellValueFactory(cellData -> cellData.getValue().getSurowiec().getNazwaProperty());
        akcjaColumn.setCellValueFactory(cellData -> cellData.getValue().getAkcja().getNazwaProperty());
        iloscWalutaColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        iloscSurowiecColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        iloscAkcjaColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
    }

}
