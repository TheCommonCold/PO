package kupujacy;

import Nazwy.LosoweNazwy;
import aktywa.surowiec;
import aktywa.waluta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import portfel.stackJednostekUczestnictwa;
import portfel.stackWalut;

import java.util.List;
import java.util.Random;

public class inwestor extends podmiotKupujacy {
    private double pesel;

    public inwestor(ObservableList<surowiec> surowiecData, ObservableList<waluta> walutaData, LosoweNazwy nazwy){
        setImie(nazwy.getImieInwestora());
        setNazwisko(nazwy.getNazwiskoInwestora());
        createPesel();
        defaultPodmiotKupujacyConstructor(surowiecData,walutaData,"Inwestor");
    }

    public inwestor(ObservableList<surowiec> surowiecData,ObservableList<waluta> walutaData,String imie,String nazwisko){
        setImie(imie);
        setNazwisko(nazwisko);
        createPesel();
        defaultPodmiotKupujacyConstructor(surowiecData,walutaData,"Inwestor");
    }

    public void createPesel(){
        Random generator = new Random();
        pesel = 0;
        for (int i = 1; i < 11; i++) {
            pesel = pesel + (generator.nextInt(10) * Math.pow(10,i));
        }
    }

    public double getPesel() {
        return pesel;
    }

    public void setPesel(double pesel) {
        this.pesel = pesel;
    }

    public SimpleStringProperty getPeselProperty(){return new SimpleStringProperty(Double.toString(pesel));}

    public void kupJednostkiUczestnictwa(ObservableList<funduszInwestycyjny>listaFunduszy){
        Random generator = new Random();
        if(!listaFunduszy.isEmpty() && !getAssets().getWaluty().isEmpty()){
            for(funduszInwestycyjny currentFundusz:listaFunduszy){
                if(getAgresja()>generator.nextFloat()){
                    if(getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek())!=null){
                        if(getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()).getIlosc()>currentFundusz.getJednostkaUczestnictwa().getWartoscJednostki()){
                            getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()).subtractIlosc(currentFundusz.getJednostkaUczestnictwa().getWartoscJednostki());
                            if(currentFundusz.getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek())==null){
                                currentFundusz.getAssets().addNowaWaluta(new stackWalut(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek(),0));
                            }
                            currentFundusz.getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()).addIlosc(currentFundusz.getJednostkaUczestnictwa().getWartoscJednostki());
                            if(getAssets().getStackJednostekUczestnictwa(currentFundusz.getJednostkaUczestnictwa())==null){
                                getAssets().addNowaJednostkaUczestnictwa(new stackJednostekUczestnictwa(currentFundusz.getJednostkaUczestnictwa(),0));
                            }
                            getAssets().getStackJednostekUczestnictwa(currentFundusz.getJednostkaUczestnictwa()).addIlosc(1);
                        }
                    }
                }
            }
        }
    }

    public void zwiekszenieBudzetu(ObservableList<waluta> walutaData){
        Random generator = new Random();
        if(generator.nextFloat()<0.2 && walutaData.size()>0){
            int ktoraWaluta=generator.nextInt(walutaData.size());
            int i=0;
            for(waluta currentWaluta:walutaData){
                if(i==ktoraWaluta){
                    if(getAssets().getStackWaluty(currentWaluta)!=null){
                        getAssets().getStackWaluty(currentWaluta).addIlosc(generator.nextInt(10000));
                    }else{
                        getAssets().addNowaWaluta(new stackWalut(currentWaluta,generator.nextInt(10000)));
                    }
                }
                i++;
            }
        }
    }

}
