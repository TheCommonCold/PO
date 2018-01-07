package GUI;

import aktywa.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import rynek.RynekAkcji;
import rynek.RynekWalut;
import spolka.Spolka;
import życie.DaneRynku;

import java.util.Random;


public class AktywaController {
    DaneRynku daneRynku;
    Spolka currentlySelectedSpolka;
    @FXML
    private TableView<Aktywa> walutaTable;
    @FXML
    private TableView<CenaWaluty> cenyTable;
    @FXML
    private TableColumn<Aktywa, String> nazwaColumn;
    @FXML
    private TableColumn<CenaWaluty, String> nazwaInnejWalutyColumn;
    @FXML
    private TableColumn<CenaWaluty, String> cenaKupnaColumn;
    @FXML
    private TableColumn<CenaWaluty, String> cenaSprzedazyColumn;
    @FXML
    private TableColumn<Aktywa, String> rynekColumn;
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
    private Label obrotyLabel;
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
    @FXML
    private LineChart aktywaLineChart;
    @FXML
    private NumberAxis aktywaXAxis;
    @FXML
    private NumberAxis aktywaYAxis;
    @FXML
    private LineChart walutaLineChart;
    @FXML
    private NumberAxis walutaXAxis;
    @FXML
    private NumberAxis walutaYAxis;

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
        walutaTable.setItems(daneRynku.getAktywaData());
    }

    public void handleWykup() {
        Random generator = new Random();
        currentlySelectedSpolka.wykupAkcji(daneRynku.getPodmiotKupujacyData(), Float.parseFloat(cenaWykupuField.getText()));
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
                ((observable, oldValue, newValue) -> showWaluta(newValue))
        );
    }

    private void showWaluta(Aktywa aktywo) {
        hideStuff();
        if (aktywo instanceof Waluta) {
            walutaLineChart.setVisible(true);
            cenyTable.setVisible(true);
            walutyGrid.setVisible(true);
            cenyTable.setItems(((RynekWalut) aktywo.getRynek()).getCenaWaluty((Waluta) aktywo).getWartosc());
            nazwaWalutyLabel.setText(aktywo.getNazwa());
            listaKrajowLabel.setText(((Waluta) aktywo).displayListaKrajow());
            rynekWalutyLabel.setText(aktywo.getRynek().getNazwa());
            cenyTable.getSelectionModel().selectedItemProperty().addListener(
                    ((observable, oldValue, newValue) -> rysujWykresWalut(newValue)));
        }
        if (aktywo instanceof Surowiec) {
            rysujWykres(aktywo);
            surowceGrid.setVisible(true);
            aktywaLineChart.setVisible(true);
            nazwaSurowcaLabel.setText(aktywo.getNazwa());
            rynekSurowcaLabel.setText(aktywo.getRynek().getNazwa());
            jednostkaHandlowaLabel.setText(((Surowiec) aktywo).getJednostkaHandlowa());
            walutaSurowcaLabel.setText(((Surowiec) aktywo).getWaluta().getNazwa());
            wartoscSurowcaLabel.setText(Float.toString(((Surowiec) aktywo).getWartosc()));
            wartoscMinimalnaLabel.setText(Float.toString(((Surowiec) aktywo).getWartoscMinimalna()));
            wartoscMaksymalnaLabel.setText(Float.toString(((Surowiec) aktywo).getWartoscMaksymalna()));

        }
        if (aktywo instanceof Akcje) {
            rysujWykres(aktywo);
            currentlySelectedSpolka = ((Akcje) aktywo).getSpolka();
            wykupBar.setVisible(true);
            akcjeGrid.setVisible(true);
            aktywaLineChart.setVisible(true);
            nazwaSpolkiLabel.setText(((Akcje) aktywo).getSpolka().getName());
            rynekSpolkiLabel.setText(aktywo.getRynek().getNazwa());
            dataPierwszejWycenyLabel.setText(((Akcje) aktywo).getSpolka().getDataPierwszejWyceny().toString());
            wartoscSpolkiLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getWartosc()));
            walutaSpolkiLabel.setText(((RynekAkcji) aktywo.getRynek()).getWalutaRynku().getNazwa());
            kursOtwarciaLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getKursOtwarcia()));
            minimalnyKursiLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getMinimalnyKurs()));
            maksymalnyKursLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getMaksymalnyKurs()));
            akcjaLabel.setText(aktywo.getNazwa());
            liczbaAkcjiLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getLiczbaAkcji()));
            zyskLabel.setText(Double.toString(((Akcje) aktywo).getSpolka().getZysk()));
            przychodLabel.setText(Double.toString(((Akcje) aktywo).getSpolka().getPrzychod()));
            kapitalWlasnyLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getKapitalWlasny()));
            kapitalZakladowyLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getKapitalZakladowy()));
            wolumenLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getWolumen()));
            obrotyLabel.setText(Float.toString(((Akcje) aktywo).getSpolka().getObroty()));
        }
    }

    private void hideStuff() {
        walutaLineChart.setVisible(false);
        cenyTable.setVisible(false);
        walutyGrid.setVisible(false);
        surowceGrid.setVisible(false);
        akcjeGrid.setVisible(false);
        wykupBar.setVisible(false);
        aktywaLineChart.setVisible(false);
    }

    public void refresh() {
        walutaTable.refresh();
        cenyTable.refresh();
    }

    public void rysujWykres(Aktywa Aktywa) {
        aktywaLineChart.setCreateSymbols(false);
        aktywaLineChart.getXAxis().setAutoRanging(true);
        aktywaLineChart.getYAxis().setAutoRanging(true);
        aktywaLineChart.getData().clear();
        aktywaXAxis = new NumberAxis(0, daneRynku.getLiczbaTur(), 1);
        aktywaXAxis.setLabel("Tury");
        if (Aktywa instanceof Surowiec) {
            aktywaYAxis = new NumberAxis(((Surowiec) Aktywa).getWartoscMinimalna() * 0.9, ((Surowiec) Aktywa).getWartoscMaksymalna() * 1.1, 10);
        }
        if (Aktywa instanceof Akcje) {
            aktywaYAxis = new NumberAxis(((Akcje) Aktywa).getSpolka().getMinimalnyKurs() * 0.9, ((Akcje) Aktywa).getSpolka().getMaksymalnyKurs() * 1.1, 10);
        }
        ;
        aktywaYAxis.setLabel("Wartość");
        XYChart.Series series = new XYChart.Series();
        int i = 0;
        for (double wartosc : Aktywa.getListaWartosci()) {
            series.getData().add(new XYChart.Data(i, wartosc));
            i++;
        }
        aktywaLineChart.getData().add(series);
    }

    public void rysujWykresWalut(CenaWaluty CenaWaluty) {
        try {
            walutaLineChart.setCreateSymbols(false);
            walutaLineChart.getXAxis().setAutoRanging(true);
            walutaLineChart.getYAxis().setAutoRanging(true);
            walutaLineChart.getData().clear();
            //Defining X axis
            walutaXAxis = new NumberAxis(0, daneRynku.getLiczbaTur(), 1);
            walutaXAxis.setLabel("Tury");
            //Defining y axis
            walutaYAxis = new NumberAxis(0, 1000, 10);
            walutaYAxis.setLabel("Wartość");
            XYChart.Series series = new XYChart.Series();
            int i = 0;
            for (double wartosc : CenaWaluty.getListaWartosciKupna()) {
                series.getData().add(new XYChart.Data(i, wartosc));
                i++;
            }
            walutaLineChart.getData().add(series);
            ;
        } catch (NullPointerException e) {

        }
    }
}
