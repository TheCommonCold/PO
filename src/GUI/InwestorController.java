package GUI;

import aktywa.aktywa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import kupujacy.inwestor;
import kupujacy.podmiotKupujacy;
import portfel.*;
import życie.DaneRynku;

public class InwestorController {

    @FXML
    private TableView<podmiotKupujacy> PodmiotKupujacyTable;
    @FXML
    private TableView<stackWalut> walutaTable;
    @FXML
    private TableView<stackSurowcow> surowiecTable;
    @FXML
    private TableView<stackAkcji> akcjaTable;
    @FXML
    private TableView<stackJednostekUczestnictwa> funduszTable;

    @FXML
    private TableColumn<podmiotKupujacy, String> imieColumn;
    @FXML
    private TableColumn<podmiotKupujacy, String> nazwiskoColumn;
    @FXML
    private TableColumn<podmiotKupujacy, String> typColumn;

    @FXML
    private TableColumn<stackWalut, String> walutaColumn;
    @FXML
    private TableColumn<stackSurowcow, String> surowiecColumn;
    @FXML
    private TableColumn<stackAkcji, String> akcjaColumn;
    @FXML
    private TableColumn<stackJednostekUczestnictwa,String>funduszColumn;

    @FXML
    private TableColumn<stackWalut, String> iloscWalutaColumn;
    @FXML
    private TableColumn<stackSurowcow, String> iloscSurowiecColumn;
    @FXML
    private TableColumn<stackAkcji, String> iloscAkcjaColumn;
    @FXML
    private TableColumn<stackJednostekUczestnictwa,String> iloscJednostekColumn;



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
        PodmiotKupujacyTable.setItems(daneRynku.getPodmiotKupujacyData());
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        imieColumn.setCellValueFactory(cellData -> cellData.getValue().getImieProperty());
        nazwiskoColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwiskoProperty());
        typColumn.setCellValueFactory(cellData -> cellData.getValue().getTypProperty());
        PodmiotKupujacyTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showInwestor(newValue) )
        );
    }

    public void showInwestor(podmiotKupujacy podmiotKupujacy){
        hideStuff();
        imieLabel.setText(podmiotKupujacy.getImie());
        nazwiskoLabel.setText(podmiotKupujacy.getNazwisko());
        if(podmiotKupujacy instanceof inwestor){
            peselLabel.setVisible(true);
            funduszTable.setVisible(true);
            peselLabel.setText(Double.toString(((inwestor) podmiotKupujacy).getPesel()));
            funduszTable.setItems(podmiotKupujacy.getAssets().getJednostkiUczestnictwa());
            funduszColumn.setCellValueFactory(cellData -> cellData.getValue().getJednostkaUczestnictwa().getFunduszInwestycyjny().getNazwaProperty());
            iloscJednostekColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        }
        walutaTable.setItems(podmiotKupujacy.getAssets().getWaluty());
        surowiecTable.setItems(podmiotKupujacy.getAssets().getSurowce());
        walutaTable.setItems(podmiotKupujacy.getAssets().getWaluty());
        akcjaTable.setItems(podmiotKupujacy.getAssets().getAkcje());
        walutaColumn.setCellValueFactory(cellData -> cellData.getValue().getWaluta().getNazwaProperty());
        surowiecColumn.setCellValueFactory(cellData -> cellData.getValue().getSurowiec().getNazwaProperty());
        akcjaColumn.setCellValueFactory(cellData -> cellData.getValue().getAkcja().getNazwaProperty());
        iloscWalutaColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        iloscSurowiecColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        iloscAkcjaColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
    }

    public void hideStuff(){
        peselLabel.setVisible(false);
        funduszTable.setVisible(false);
    }

}
