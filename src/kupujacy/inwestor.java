package kupujacy;

import aktywa.surowiec;
import aktywa.waluta;
import javafx.collections.ObservableList;
import portfel.portfel;
import portfel.stackSurowcow;
import portfel.stackWalut;

import java.util.Random;

public class inwestor extends podmiotKupujacy {
    private double pesel;

    public inwestor(ObservableList<surowiec> surowiecData,ObservableList<waluta> walutaData){
        Random generator = new Random();
        setImie(Integer.toString(generator.nextInt()));
        setNazwisko(Integer.toString(generator.nextInt()));
        defaultInwestorConstructor(surowiecData,walutaData);
    }

    public inwestor(ObservableList<surowiec> surowiecData,ObservableList<waluta> walutaData,String imie,String nazwisko){
        setImie(imie);
        setNazwisko(nazwisko);
        defaultInwestorConstructor(surowiecData,walutaData);
    }

    public void defaultInwestorConstructor(ObservableList<surowiec> surowiecData,ObservableList<waluta> walutaData) {
        Random generator = new Random();
        setAgresja((generator.nextFloat()/4)+(1/4));
        pesel = 0;
        for (int i = 1; i < 11; i++) {
            pesel = pesel + (generator.nextInt(10) * Math.pow(10,i));
        }
        setAssets(new portfel());
        for(surowiec currentSurowiec:surowiecData){
            if(generator.nextFloat()>0.5){
                getAssets().addNowySurowiec(new stackSurowcow(currentSurowiec,generator.nextFloat()+generator.nextInt(10000)));
            }
        }
        for(waluta currentWaluta:walutaData){
                if(generator.nextFloat()>0.5){
                    getAssets().addNowaWaluta(new stackWalut(currentWaluta,generator.nextFloat()+generator.nextInt(100000)));
                }
        }
    }

    public double getPesel() {
        return pesel;
    }

    public void setPesel(double pesel) {
        this.pesel = pesel;
    }

}
