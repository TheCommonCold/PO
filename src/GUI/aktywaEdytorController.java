package GUI;

import aktywa.aktywa;
import aktywa.waluta;
import javafx.scene.control.*;
import spolka.spolka;
import javafx.event.ActionEvent;
import javafx.event.Event;
import aktywa.surowiec;
import javafx.util.Callback;
import rynek.rynekWalut;
import rynek.rynekSurowcow;
import rynek.rynekAkcji;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import życie.DaneRynku;

public class aktywaEdytorController {

    private Stage dialogStage;
    private DaneRynku daneRynku;
    private boolean okClicked = false;

    @FXML
    private CheckBox addAktywaCheckBox;

    @FXML
    private CheckBox addRynkiCheckBox;

    @FXML
    private ComboBox <String> aktywoComboBox = new ComboBox<>();

    @FXML
    private ComboBox<rynekWalut> rynkiWalutComboBox = new ComboBox<rynekWalut>();

    @FXML
    private TextField nazwaTextField;


    Callback<ListView<rynekWalut>, ListCell<rynekWalut>> factoryWaluty = lv -> new ListCell<rynekWalut>() {

        @Override
        protected void updateItem(rynekWalut item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getNazwa());
        }

    };

    @FXML
    private ComboBox<rynekSurowcow> rynkiSurowcowComboBox = new ComboBox<rynekSurowcow>();


    Callback<ListView<rynekSurowcow>, ListCell<rynekSurowcow>> factorySurowce = lv -> new ListCell<rynekSurowcow>() {

        @Override
        protected void updateItem(rynekSurowcow item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getNazwa());
        }

    };

    @FXML
    private ComboBox<rynekAkcji> rynkiAkcjiComboBox = new ComboBox<rynekAkcji>();


    Callback<ListView<rynekAkcji>, ListCell<rynekAkcji>> factoryAkcji = lv -> new ListCell<rynekAkcji>() {

        @Override
        protected void updateItem(rynekAkcji item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getNazwa());
        }

    };



    @FXML
    private void initialize(){
        rynkiWalutComboBox.setCellFactory(factoryWaluty);
        rynkiSurowcowComboBox.setCellFactory(factorySurowce);
        rynkiAkcjiComboBox.setCellFactory(factoryAkcji);
        rynkiWalutComboBox.setButtonCell(factoryWaluty.call(null));
        rynkiSurowcowComboBox.setButtonCell(factorySurowce.call(null));
        rynkiAkcjiComboBox.setButtonCell(factoryAkcji.call(null));
        aktywoComboBox.getItems().addAll("Waluta","Surowiec","Spolka");
        aktywoComboBox.getSelectionModel().selectedIndexProperty().addListener(
                ((observable, oldValue, newValue) -> showRynki(newValue) )
        );
    }

    public void handleRynkiSetCheckBox(){
        addAktywaCheckBox.setSelected(false);
        hideRynkiComboBox();
    }

    public void handleAktywaSetCheckBox(){
        addRynkiCheckBox.setSelected(false);
        hideRynkiComboBox();
        aktywoComboBox.setValue("Waluta");
        rynkiWalutComboBox.getSelectionModel().selectFirst();
        showRynki(0);
    }


    public void showRynki(Number number){
        hideRynkiComboBox();
        if(addAktywaCheckBox.isSelected()){
            if(number.intValue()==0 && !daneRynku.getRynkiWalutData().equals(null)){
                rynkiWalutComboBox.setVisible(true);
                rynkiWalutComboBox.setItems(daneRynku.getRynkiWalutData());
            }
            if(number.intValue()==1 && !daneRynku.getRynkiSurowcowData().equals(null)){
                rynkiSurowcowComboBox.setVisible(true);
                rynkiSurowcowComboBox.setItems(daneRynku.getRynkiSurowcowData());
            }
            if(number.intValue()==2 && !daneRynku.getRynkiAkcjiData().equals(null)){
                rynkiAkcjiComboBox.setVisible(true);
                rynkiAkcjiComboBox.setItems(daneRynku.getRynkiAkcjiData());
            }
        }
    }

    public void hideRynkiComboBox(){
        rynkiWalutComboBox.setVisible(false);
        rynkiAkcjiComboBox.setVisible(false);
        rynkiSurowcowComboBox.setVisible(false);
    }

    public void handleOkButton(){
        if(addAktywaCheckBox.isSelected()) {
            if(aktywoComboBox.getValue()=="Waluta" && daneRynku.getRynkiWalutData().size()!=0){
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())daneRynku.addwalutaData(new waluta(rynkiWalutComboBox.getValue(),daneRynku.getNazwy().getNazweWaluty()));
                else daneRynku.addwalutaData(new waluta(rynkiWalutComboBox.getValue(),nazwaTextField.getText()));
            }
            if(aktywoComboBox.getValue()=="Surowiec" && daneRynku.getRynkiSurowcowData().size()!=0){
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())daneRynku.addsurowiecData(new surowiec(rynkiSurowcowComboBox.getValue(),daneRynku.getWalutaData(),daneRynku.getNazwy().getNazweSurowca()));
                else daneRynku.addsurowiecData(new surowiec(rynkiSurowcowComboBox.getValue(),daneRynku.getWalutaData(),nazwaTextField.getText()));
            }
            if(aktywoComboBox.getValue()=="Spolka" && daneRynku.getRynkiAkcjiData().size()!=0){
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())daneRynku.addspolkaData(new spolka(rynkiAkcjiComboBox.getValue(),daneRynku.getInwestorData(),daneRynku.getPodmiotKupujacyData(),daneRynku.getWalutaData(),daneRynku.getSurowiecData(),daneRynku.getAkcjeData(),daneRynku.getRatioKupujacychDoAktyw(),daneRynku.getNazwy().getNazweSpolki(),daneRynku.getNazwy()));
                else daneRynku.addspolkaData(new spolka(rynkiAkcjiComboBox.getValue(),daneRynku.getInwestorData(),daneRynku.getPodmiotKupujacyData(),daneRynku.getWalutaData(),daneRynku.getSurowiecData(),daneRynku.getAkcjeData(),daneRynku.getRatioKupujacychDoAktyw(),nazwaTextField.getText(),daneRynku.getNazwy()));

            }
        }else{
            if(aktywoComboBox.getValue()=="Waluta"){
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())daneRynku.addRynkiWalutData(new rynekWalut(daneRynku.getNazwy().getNazweRynkuWalut()));
                else daneRynku.addRynkiWalutData(new rynekWalut(nazwaTextField.getText()));
            }
            if(aktywoComboBox.getValue()=="Surowiec"){
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())daneRynku.addRynkiSurowiecData(new rynekSurowcow(daneRynku.getNazwy().getNazweRynkuSurowcow()));
                else daneRynku.addRynkiSurowiecData(new rynekSurowcow(nazwaTextField.getText()));
            }
            if(aktywoComboBox.getValue()=="Spolka") {
                if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty())daneRynku.addRynkiAkcjiData(new rynekAkcji(daneRynku.getWalutaData(),daneRynku.getNazwy().getNazweRynkuAkcji()));
                else daneRynku.addRynkiAkcjiData(new rynekAkcji(daneRynku.getWalutaData(),nazwaTextField.getText()));
            }
        }
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
