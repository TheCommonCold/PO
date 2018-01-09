package GUI;

import aktywa.Surowiec;
import aktywa.Waluta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import rynek.RynekAkcji;
import rynek.RynekSurowcow;
import rynek.RynekWalut;
import spolka.Spolka;
import życie.DaneRynku;

public class AktywaEdytorController {

    Callback<ListView<RynekWalut>, ListCell<RynekWalut>> factoryWaluty = lv -> new ListCell<RynekWalut>() {

        @Override
        protected void updateItem(RynekWalut item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getNazwa());
        }

    };
    Callback<ListView<RynekSurowcow>, ListCell<RynekSurowcow>> factorySurowce = lv -> new ListCell<RynekSurowcow>() {

        @Override
        protected void updateItem(RynekSurowcow item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getNazwa());
        }

    };
    Callback<ListView<RynekAkcji>, ListCell<RynekAkcji>> factoryAkcji = lv -> new ListCell<RynekAkcji>() {

        @Override
        protected void updateItem(RynekAkcji item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getNazwa());
        }

    };
    private Stage dialogStage;
    private DaneRynku daneRynku;
    private boolean okClicked = false;
    @FXML
    private CheckBox addAktywaCheckBox;
    @FXML
    private CheckBox addRynkiCheckBox;
    @FXML
    private ComboBox<String> aktywoComboBox = new ComboBox<>();
    @FXML
    private ComboBox<RynekWalut> rynkiWalutComboBox = new ComboBox<RynekWalut>();
    @FXML
    private TextField nazwaTextField;
    @FXML
    private ComboBox<RynekSurowcow> rynkiSurowcowComboBox = new ComboBox<RynekSurowcow>();
    @FXML
    private ComboBox<RynekAkcji> rynkiAkcjiComboBox = new ComboBox<RynekAkcji>();

    @FXML
    private void initialize() {
        addAktywaCheckBox.setSelected(true);
        rynkiWalutComboBox.setCellFactory(factoryWaluty);
        rynkiSurowcowComboBox.setCellFactory(factorySurowce);
        rynkiAkcjiComboBox.setCellFactory(factoryAkcji);
        rynkiWalutComboBox.setButtonCell(factoryWaluty.call(null));
        rynkiSurowcowComboBox.setButtonCell(factorySurowce.call(null));
        rynkiAkcjiComboBox.setButtonCell(factoryAkcji.call(null));
        aktywoComboBox.getItems().addAll("Waluta", "Surowiec", "Spolka");
        aktywoComboBox.getSelectionModel().selectedIndexProperty().addListener(
                ((observable, oldValue, newValue) -> showRynki(newValue))
        );
    }

    public void handleRynkiSetCheckBox() {
        addAktywaCheckBox.setSelected(false);
        hideRynkiComboBox();
    }

    public void handleAktywaSetCheckBox() {
        addRynkiCheckBox.setSelected(false);
        hideRynkiComboBox();
        aktywoComboBox.setValue("Waluta");
        rynkiWalutComboBox.getSelectionModel().selectFirst();
        showRynki(0);
    }


    public void showRynki(Number number) {
        hideRynkiComboBox();
        if (addAktywaCheckBox.isSelected()) {
            if (number.intValue() == 0 && !daneRynku.getRynkiWalutData().equals(null)) {
                rynkiWalutComboBox.setVisible(true);
                rynkiWalutComboBox.setItems(daneRynku.getRynkiWalutData());
                rynkiWalutComboBox.getSelectionModel().selectFirst();
            }
            if (number.intValue() == 1 && !daneRynku.getRynkiSurowcowData().equals(null)) {
                rynkiSurowcowComboBox.setVisible(true);
                rynkiSurowcowComboBox.setItems(daneRynku.getRynkiSurowcowData());
                rynkiSurowcowComboBox.getSelectionModel().selectFirst();
            }
            if (number.intValue() == 2 && !daneRynku.getRynkiAkcjiData().equals(null)) {
                rynkiAkcjiComboBox.setVisible(true);
                rynkiAkcjiComboBox.setItems(daneRynku.getRynkiAkcjiData());
                rynkiAkcjiComboBox.getSelectionModel().selectFirst();
            }
        }
    }

    public void hideRynkiComboBox() {
        rynkiWalutComboBox.setVisible(false);
        rynkiAkcjiComboBox.setVisible(false);
        rynkiSurowcowComboBox.setVisible(false);
    }


    public void handleOkButton() {
        synchronized (daneRynku.getMonitorZlecen()){
            try{
                daneRynku.getMonitorZlecen().wait();
            }catch(Exception e){}
        }
        if (addAktywaCheckBox.isSelected()) {
            if (aktywoComboBox.getValue() == "Waluta" && daneRynku.getRynkiWalutData().size() != 0) {
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())
                    daneRynku.addwalutaData(new Waluta(rynkiWalutComboBox.getValue(), daneRynku.getNazwy(), daneRynku.getNazwy().getNazweWaluty()));
                else
                    daneRynku.addwalutaData(new Waluta(rynkiWalutComboBox.getValue(), daneRynku.getNazwy(), nazwaTextField.getText()));
            }
            if (aktywoComboBox.getValue() == "Surowiec" && daneRynku.getRynkiSurowcowData().size() != 0) {
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())
                    daneRynku.addsurowiecData(new Surowiec(rynkiSurowcowComboBox.getValue(), daneRynku.getWalutaData(), daneRynku.getNazwy(), daneRynku.getNazwy().getNazweSurowca()));
                else
                    daneRynku.addsurowiecData(new Surowiec(rynkiSurowcowComboBox.getValue(), daneRynku.getWalutaData(), daneRynku.getNazwy(), nazwaTextField.getText()));
            }
            if (aktywoComboBox.getValue() == "Spolka" && daneRynku.getRynkiAkcjiData().size() != 0) {
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())
                    daneRynku.addspolkaData(new Spolka(rynkiAkcjiComboBox.getValue(), daneRynku, daneRynku.getRatioKupujacychDoAktyw(), daneRynku.getNazwy()));
                else
                    daneRynku.addspolkaData(new Spolka(rynkiAkcjiComboBox.getValue(), daneRynku, daneRynku.getRatioKupujacychDoAktyw(), nazwaTextField.getText(), daneRynku.getNazwy()));

            }
        } else {
            if (aktywoComboBox.getValue() == "Waluta") {
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())
                    daneRynku.addRynkiWalutData(new RynekWalut(daneRynku.getNazwy().getNazweRynkuWalut()));
                else daneRynku.addRynkiWalutData(new RynekWalut(nazwaTextField.getText()));
            }
            if (aktywoComboBox.getValue() == "Surowiec") {
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())
                    daneRynku.addRynkiSurowiecData(new RynekSurowcow(daneRynku.getNazwy().getNazweRynkuSurowcow()));
                else daneRynku.addRynkiSurowiecData(new RynekSurowcow(nazwaTextField.getText()));
            }
            if (aktywoComboBox.getValue() == "Spolka") {
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())
                    daneRynku.addRynkiAkcjiData(new RynekAkcji(daneRynku.getWalutaData(), daneRynku.getNazwy().getNazweRynkuAkcji()));
                else daneRynku.addRynkiAkcjiData(new RynekAkcji(daneRynku.getWalutaData(), nazwaTextField.getText()));
            }
        }
        daneRynku.setMonitorGUI(true);
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }


}
