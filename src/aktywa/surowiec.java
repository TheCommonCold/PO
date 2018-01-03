package aktywa;

import javafx.collections.ObservableList;

import java.util.Random;

public class surowiec extends aktywa {
    private String jednostkaHandlowa;
    private waluta waluta;
    private float wartosc;
    private float wartoscMinimalna;
    private float wartoscMaksymalna;

    public surowiec(ObservableList<waluta> walutaData){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultSurowiecConstructor(walutaData);
    }

    public surowiec(ObservableList<waluta> walutaData, String nazwa){
        setNazwa(nazwa);
        defaultSurowiecConstructor(walutaData);
    }

    public void defaultSurowiecConstructor(ObservableList<waluta> walutaData){
        Random generator = new Random();
        jednostkaHandlowa = Integer.toString(generator.nextInt());
        int stop = generator.nextInt(walutaData.size());
        int i=0;
        for(waluta waluta:walutaData){
            if(i==stop){
                this.waluta=waluta;
                break;
            }
            i++;
        }
        wartosc = generator.nextInt(10000)+generator.nextFloat();
        wartoscMinimalna = wartosc;
        wartoscMaksymalna = wartosc;
    }

    public String getJednostkaHandlowa() {
        return jednostkaHandlowa;
    }

    public void setJednostkaHandlowa(String jednostkaHandlowa) {
        this.jednostkaHandlowa = jednostkaHandlowa;
    }

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
    }

    public float getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public float getWartoscMinimalna() {
        return wartoscMinimalna;
    }

    public void setWartoscMinimalna(int wartoscMinimalna) {
        this.wartoscMinimalna = wartoscMinimalna;
    }

    public float getWartoscMaksymalna() {
        return wartoscMaksymalna;
    }

    public void setWartoscMaksymalna(int wartoscMaksymalna) {
        this.wartoscMaksymalna = wartoscMaksymalna;
    }
}
