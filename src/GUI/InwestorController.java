package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import kupujacy.Inwestor;
import kupujacy.PodmiotKupujacy;
import portfel.StackAkcji;
import portfel.StackJednostekUczestnictwa;
import portfel.StackSurowcow;
import portfel.StackWalut;
import życie.DaneRynku;

public class InwestorController {

    DaneRynku daneRynku;
    @FXML
    private TableView<PodmiotKupujacy> PodmiotKupujacyTable;
    @FXML
    private TableView<StackWalut> walutaTable;
    @FXML
    private TableView<StackSurowcow> surowiecTable;
    @FXML
    private TableView<StackAkcji> akcjaTable;
    @FXML
    private TableView<StackJednostekUczestnictwa> funduszTable;
    @FXML
    private TableColumn<PodmiotKupujacy, String> imieColumn;
    @FXML
    private TableColumn<PodmiotKupujacy, String> nazwiskoColumn;
    @FXML
    private TableColumn<PodmiotKupujacy, String> typColumn;
    @FXML
    private TableColumn<StackWalut, String> walutaColumn;
    @FXML
    private TableColumn<StackSurowcow, String> surowiecColumn;
    @FXML
    private TableColumn<StackAkcji, String> akcjaColumn;
    @FXML
    private TableColumn<StackJednostekUczestnictwa, String> funduszColumn;
    @FXML
    private TableColumn<StackWalut, String> iloscWalutaColumn;
    @FXML
    private TableColumn<StackSurowcow, String> iloscSurowiecColumn;
    @FXML
    private TableColumn<StackAkcji, String> iloscAkcjaColumn;
    @FXML
    private TableColumn<StackJednostekUczestnictwa, String> iloscJednostekColumn;
    @FXML
    private Label imieLabel;
    @FXML
    private Label nazwiskoLabel;
    @FXML
    private Label peselLabel;

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
                ((observable, oldValue, newValue) -> showInwestor(newValue))
        );
    }

    public void showInwestor(PodmiotKupujacy PodmiotKupujacy) {
        hideStuff();
        imieLabel.setText(PodmiotKupujacy.getImie());
        nazwiskoLabel.setText(PodmiotKupujacy.getNazwisko());
        if (PodmiotKupujacy instanceof Inwestor) {
            peselLabel.setVisible(true);
            funduszTable.setVisible(true);
            peselLabel.setText(Double.toString(((Inwestor) PodmiotKupujacy).getPesel()));
            funduszTable.setItems(PodmiotKupujacy.getAssets().getJednostkiUczestnictwa());
            funduszColumn.setCellValueFactory(cellData -> cellData.getValue().getJednostkaUczestnictwa().getFunduszInwestycyjny().getNazwaProperty());
            iloscJednostekColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        }
        walutaTable.setItems(PodmiotKupujacy.getAssets().getWaluty());
        surowiecTable.setItems(PodmiotKupujacy.getAssets().getSurowce());
        walutaTable.setItems(PodmiotKupujacy.getAssets().getWaluty());
        akcjaTable.setItems(PodmiotKupujacy.getAssets().getAkcje());
        walutaColumn.setCellValueFactory(cellData -> cellData.getValue().getWaluta().getNazwaProperty());
        surowiecColumn.setCellValueFactory(cellData -> cellData.getValue().getSurowiec().getNazwaProperty());
        akcjaColumn.setCellValueFactory(cellData -> cellData.getValue().getAkcja().getNazwaProperty());
        iloscWalutaColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        iloscSurowiecColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
        iloscAkcjaColumn.setCellValueFactory(cellData -> cellData.getValue().getIloscProperty());
    }

    public void hideStuff() {
        peselLabel.setVisible(false);
        funduszTable.setVisible(false);
    }

    public void refresh() {
        PodmiotKupujacyTable.refresh();
        walutaTable.refresh();
        surowiecTable.refresh();
        akcjaTable.refresh();
        funduszTable.refresh();
    }

}
