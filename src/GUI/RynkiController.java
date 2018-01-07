package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import życie.DaneRynku;
import javafx.scene.control.TableView;
import rynek.rynek;


public class RynkiController {
    DaneRynku daneRynku;

    @FXML
    private TableView<rynek> rynkiTable;
    @FXML
    private TableColumn<rynek,String> nazwaColumn;
    @FXML
    private TableColumn<rynek,String> typColumn;

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
        rynkiTable.setItems(daneRynku.getRynekData());
        nazwaColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        typColumn.setCellValueFactory(cellData -> cellData.getValue().getTypRynkuPropery());
        rynkiTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showRynki(newValue) )
        );
    }

    public void showRynki(rynek rynek){

    }
}
