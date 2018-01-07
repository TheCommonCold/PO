package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import rynek.rynekAkcji;
import aktywa.cenaWaluty;
import aktywa.aktywa;
import aktywa.waluta;
import aktywa.surowiec;
import aktywa.akcje;
import spolka.spolka;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import rynek.rynekWalut;
import życie.DaneRynku;

import java.util.Random;


public class aktywaController {
    @FXML
    private TableView<aktywa> walutaTable;
    @FXML
    private TableView<cenaWaluty> cenyTable;

    @FXML
    private TableColumn<aktywa, String> nazwaColumn;
    @FXML
    private TableColumn<cenaWaluty, String> nazwaInnejWalutyColumn;
    @FXML
    private TableColumn<cenaWaluty, String> cenaKupnaColumn;
    @FXML
    private TableColumn<cenaWaluty, String> cenaSprzedazyColumn;

    @FXML
    private TableColumn<aktywa, String> rynekColumn;

    @FXML
    private Label nazwaWalutyLabel;
    @FXML
    private Label rynekWalutyLabel;
    @FXML
    private Label nazwaSurowcaLabel;
    @FXML
    private Label rynekSurowcaLabel;
    @FXML
    private Label listaKrajowLabel;
    @FXML
    private Label jednostkaHandlowaLabel;
    @FXML
    private Label walutaSurowcaLabel;
    @FXML
    private Label wartoscSurowcaLabel;
    @FXML
    private Label wartoscMinimalnaLabel;
    @FXML
    private Label wartoscMaksymalnaLabel;
    @FXML
    private Label nazwaSpolkiLabel;
    @FXML
    private Label rynekSpolkiLabel;
    @FXML
    private Label dataPierwszejWycenyLabel;
    @FXML
    private Label wartoscSpolkiLabel;
    @FXML
    private Label walutaSpolkiLabel;
    @FXML
    private Label kursOtwarciaLabel;
    @FXML
    private Label minimalnyKursiLabel;
    @FXML
    private Label maksymalnyKursLabel;
    @FXML
    private Label akcjaLabel;
    @FXML
    private Label liczbaAkcjiLabel;
    @FXML
    private Label zyskLabel;
    @FXML
    private Label przychodLabel;
    @FXML
    private Label kapitalWlasnyLabel;
    @FXML
    private Label kapitalZakladowyLabel;
    @FXML
    private Label wolumenLabel;



    @FXML
    private GridPane walutyGrid;
    @FXML
    private GridPane surowceGrid;
    @FXML
    private GridPane akcjeGrid;
    @FXML
    private ButtonBar wykupBar;
    @FXML
    private TextField cenaWykupuField;
    @FXML
    private Button wykupButton;



    DaneRynku daneRynku;
    spolka currentlySelectedSpolka;

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
        walutaTable.setItems(daneRynku.getAktywaData());
    }

    public void handleWykup(){
        Random generator = new Random();
        currentlySelectedSpolka.wykupAkcji(daneRynku.getPodmiotKupujacyData(),Float.parseFloat(cenaWykupuField.getText()));
    }

    @FXML
    private void initialize() {
        cenaWykupuField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cenaWykupuField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        rynekColumn.setCellValueFactory(cellData -> cellData.getValue().getRynekProperty());
        nazwaInnejWalutyColumn.setCellValueFactory(cellData -> cellData.getValue().getWaluta().getNazwaProperty());
        cenaKupnaColumn.setCellValueFactory(cellData -> cellData.getValue().getCenaKupnaProperty());
        cenaSprzedazyColumn.setCellValueFactory(cellData -> cellData.getValue().getCenaSprzedazyProperty());
        hideStuff();
        walutaTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showWaluta(newValue) )
        );
    }

    private void showWaluta(aktywa aktywo){
        hideStuff();
        if(aktywo instanceof waluta ){
            cenyTable.setVisible(true);
            walutyGrid.setVisible(true);
            cenyTable.setItems(((rynekWalut)aktywo.getRynek()).getCenaWaluty((waluta)aktywo).getWartosc());
            nazwaWalutyLabel.setText(aktywo.getNazwa());
            listaKrajowLabel.setText(((waluta)aktywo).displayListaKrajow());
            rynekWalutyLabel.setText(aktywo.getRynek().getNazwa());
        }
        if(aktywo instanceof surowiec){
            surowceGrid.setVisible(true);
            nazwaSurowcaLabel.setText(aktywo.getNazwa());
            rynekSurowcaLabel.setText(aktywo.getRynek().getNazwa());
            jednostkaHandlowaLabel.setText(((surowiec)aktywo).getJednostkaHandlowa());
            walutaSurowcaLabel.setText(((surowiec)aktywo).getWaluta().getNazwa());
            wartoscSurowcaLabel.setText(Float.toString(((surowiec)aktywo).getWartosc()));
            wartoscMinimalnaLabel.setText(Float.toString(((surowiec)aktywo).getWartoscMinimalna()));
            wartoscMaksymalnaLabel.setText(Float.toString(((surowiec)aktywo).getWartoscMaksymalna()));

        }
        if(aktywo instanceof akcje){
            currentlySelectedSpolka=((akcje) aktywo).getSpolka();
            wykupBar.setVisible(true);
            akcjeGrid.setVisible(true);
            nazwaSpolkiLabel.setText(((akcje)aktywo).getSpolka().getName());
            rynekSpolkiLabel.setText(aktywo.getRynek().getNazwa());
            dataPierwszejWycenyLabel.setText(((akcje)aktywo).getSpolka().getDataPierwszejWyceny().toString());
            wartoscSpolkiLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getWartosc()));
            walutaSpolkiLabel.setText(((rynekAkcji)aktywo.getRynek()).getWalutaRynku().getNazwa());
            kursOtwarciaLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getKursOtwarcia()));
            minimalnyKursiLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getMinimalnyKurs()));
            maksymalnyKursLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getMaksymalnyKurs()));
            akcjaLabel.setText(aktywo.getNazwa());
            liczbaAkcjiLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getLiczbaAkcji()));
            zyskLabel.setText(Double.toString(((akcje)aktywo).getSpolka().getZysk()));
            przychodLabel.setText(Double.toString(((akcje)aktywo).getSpolka().getPrzychod()));
            kapitalWlasnyLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getKapitalWlasny()));
            kapitalZakladowyLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getKapitalZakladowy()));
            wolumenLabel.setText(Float.toString(((akcje)aktywo).getSpolka().getWolumen()));
        }
    }
    private void hideStuff(){
        cenyTable.setVisible(false);
        walutyGrid.setVisible(false);
        surowceGrid.setVisible(false);
        akcjeGrid.setVisible(false);
        wykupBar.setVisible(false);
    }
}
