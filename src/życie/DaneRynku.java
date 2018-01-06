package życie;
import Nazwy.LosoweNazwy;
import aktywa.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import kupujacy.inwestor;
import portfel.*;
import rynek.rynek;
import rynek.rynekAkcji;
import rynek.rynekSurowcow;
import rynek.rynekWalut;
import spolka.spolka;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DaneRynku {
    private ObservableList<aktywa>aktywaData = FXCollections.observableArrayList();
    private ObservableList<rynekWalut> rynkiWalutData = FXCollections.observableArrayList();
    private ObservableList<rynekSurowcow> rynkiSurowcowData = FXCollections.observableArrayList();
    private ObservableList<rynekAkcji> rynkiAkcjiData= FXCollections.observableArrayList();
    private ObservableList<akcje>akcjeData=FXCollections.observableArrayList();
    private ObservableList<waluta> walutaData= FXCollections.observableArrayList();
    private ObservableList<surowiec> surowiecData= FXCollections.observableArrayList();
    private ObservableList<spolka> spolkaData= FXCollections.observableArrayList();
    private ObservableList<inwestor> inwestorData= FXCollections.observableArrayList();
    private ObservableList<rynek> rynekData = FXCollections.observableArrayList();
    private zleceniaKupnaSprzedazy zlecenia=new zleceniaKupnaSprzedazy();
    private LosoweNazwy nazwy = new LosoweNazwy();
    private int liczbaAktyw =0;
    private int liczbaWalut =0;


    public zleceniaKupnaSprzedazy getZlecenia() {
        return zlecenia;
    }

    public void setZlecenia(zleceniaKupnaSprzedazy zlecenia) {
        this.zlecenia = zlecenia;
    }

    public void kupowanie(){
        for(inwestor currentInwestor:inwestorData){
            currentInwestor.zlecKupno(zlecenia,rynkiWalutData,rynkiSurowcowData, rynkiAkcjiData);
        }
    }

    public void sprzedawanie(){
        for(inwestor currentInwestor:inwestorData){
            currentInwestor.zlecSprzedaz(zlecenia,rynkiWalutData,rynkiSurowcowData,rynkiAkcjiData);
        }
    }

    public void wykonajOperacjeKupnaSprzedazy(){
        Random generator = new Random();
        for(zlecenie currentSprzedaz:zlecenia.getZleceniaSprzedazy()){
            for(zlecenie currentKupno: zlecenia.getZleceniaKupna()){
                if(currentSprzedaz.getChceSprzedac().equals(currentKupno.getChceKupic()) && currentSprzedaz.getChceKupic().equals(currentKupno.getChceSprzedac())) {
                    float iloscSprzedajacego = 0, iloscKupujacego = 0;
                    if (currentSprzedaz.getChceSprzedac() instanceof waluta && currentSprzedaz.getChceKupic() instanceof waluta && currentKupno.getChceSprzedac() instanceof waluta && currentKupno.getChceKupic() instanceof waluta) {
                        iloscSprzedajacego = currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceSprzedac()).getIlosc();
                        iloscKupujacego = currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc();
                        float przelicznik = 1;
                        for (rynekWalut currentRynekWalut : rynkiWalutData) {
                                for (cenyWalut currentCenyWalut : currentRynekWalut.getListaCen()) {
                                    if (currentCenyWalut.getWaluta().equals(currentSprzedaz.getChceSprzedac())) {
                                        for (cenaWaluty currentCenaWaluta : currentCenyWalut.getWartosc()) {
                                            if (currentCenaWaluta.getWaluta().equals(currentKupno.getChceSprzedac())) {
                                                przelicznik = currentCenaWaluta.getCenaKupna();
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                float ilosc = 0;
                                if (iloscKupujacego * przelicznik < iloscSprzedajacego) {
                                    ilosc = (generator.nextInt((int) (iloscKupujacego * przelicznik)) + generator.nextFloat()) / 2;
                                } else {
                                    ilosc = (generator.nextInt((int) (iloscSprzedajacego)) + generator.nextFloat()) / 2;
                                }
                                if (currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceKupic()) == null) {
                                    currentKupno.getZlecacz().getAssets().addNowaWaluta(new stackWalut((waluta) currentKupno.getChceKupic(),0));
                                }
                                if (currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()) == null) {
                                    currentSprzedaz.getZlecacz().getAssets().addNowaWaluta(new stackWalut((waluta) currentSprzedaz.getChceKupic(),0));
                                }
                                System.out.print("ilosc: ");
                                System.out.print(ilosc);
                                System.out.print("\n");
                                System.out.print(ilosc/przelicznik);
                                System.out.print("\n");
                                currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceSprzedac()).subtractIlosc(ilosc);
                                currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceKupic()).addIlosc(ilosc);
                                currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()).addIlosc(ilosc / przelicznik);
                                currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).subtractIlosc(ilosc / przelicznik);
                        }
                    }
                    if(currentSprzedaz.getChceSprzedac() instanceof surowiec && currentSprzedaz.getChceKupic() instanceof waluta && currentKupno.getChceSprzedac() instanceof waluta && currentKupno.getChceKupic() instanceof surowiec){
                        iloscSprzedajacego = currentSprzedaz.getZlecacz().getAssets().getStackSurowcow(currentSprzedaz.getChceSprzedac()).getIlosc();
                        iloscKupujacego = currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc();
                                float przelicznik = ((surowiec) currentSprzedaz.getChceSprzedac()).getWartosc();
                                float ilosc=0;
                                if(iloscSprzedajacego<iloscKupujacego/przelicznik){
                                    ilosc=(generator.nextInt((int)(iloscSprzedajacego))+generator.nextFloat())/2;
                                }else{
                                    ilosc=(generator.nextInt((int)(iloscKupujacego/przelicznik))+generator.nextFloat())/2;
                                }
                                if (currentKupno.getZlecacz().getAssets().getStackSurowcow(currentKupno.getChceKupic()) == null) {
                                    currentKupno.getZlecacz().getAssets().addNowySurowiec(new stackSurowcow((surowiec) currentKupno.getChceKupic(),0));
                                }
                                if (currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()) == null) {
                                    currentSprzedaz.getZlecacz().getAssets().addNowaWaluta(new stackWalut((waluta) currentSprzedaz.getChceKupic(),0));
                                }
                                System.out.print("ilosc: ");
                                System.out.print(ilosc);
                                System.out.print("\n");
                                System.out.print(ilosc*przelicznik);
                                System.out.print("\n");
                                currentSprzedaz.getZlecacz().getAssets().getStackSurowcow(currentSprzedaz.getChceSprzedac()).subtractIlosc(ilosc);
                                currentKupno.getZlecacz().getAssets().getStackSurowcow(currentKupno.getChceKupic()).addIlosc(ilosc);
                                currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()).addIlosc(ilosc*przelicznik);
                                currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).subtractIlosc(ilosc*przelicznik);
                    }
                    if(currentSprzedaz.getChceSprzedac() instanceof akcje && currentSprzedaz.getChceKupic() instanceof waluta && currentKupno.getChceSprzedac() instanceof waluta && currentKupno.getChceKupic() instanceof akcje){
                        iloscSprzedajacego = currentSprzedaz.getZlecacz().getAssets().getStackAkcji(currentSprzedaz.getChceSprzedac()).getIlosc();
                        iloscKupujacego = currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc();
                        float przelicznik = ((akcje) currentSprzedaz.getChceSprzedac()).getSpolka().getWartosc();
                        float ilosc=0;
                        if(iloscSprzedajacego<iloscKupujacego/przelicznik){
                            ilosc=(generator.nextInt((int)(iloscSprzedajacego))+generator.nextFloat())/2;
                        }else{
                            ilosc=(generator.nextInt((int)(iloscKupujacego/przelicznik))+generator.nextFloat())/2;
                        }
                        if (currentKupno.getZlecacz().getAssets().getStackAkcji(currentKupno.getChceKupic()) == null) {
                            currentKupno.getZlecacz().getAssets().addNowaAkcja(new stackAkcji((akcje) currentKupno.getChceKupic(),0));
                        }
                        if (currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()) == null) {
                            currentSprzedaz.getZlecacz().getAssets().addNowaWaluta(new stackWalut((waluta) currentSprzedaz.getChceKupic(),0));
                        }
                        System.out.print("ilosc: ");
                        System.out.print(ilosc);
                        System.out.print("\n");
                        System.out.print(ilosc*przelicznik);
                        System.out.print("\n");
                        currentSprzedaz.getZlecacz().getAssets().getStackAkcji(currentSprzedaz.getChceSprzedac()).subtractIlosc(ilosc);
                        currentKupno.getZlecacz().getAssets().getStackAkcji(currentKupno.getChceKupic()).addIlosc(ilosc);
                        currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()).addIlosc(ilosc*przelicznik);
                        currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).subtractIlosc(ilosc*przelicznik);

                    }
                }
            }
        }
    }

    public ObservableList<rynekSurowcow> getRynkiSurowcowData() {
        return rynkiSurowcowData;
    }

    public void setRynkiSurowcowData(ObservableList<rynekSurowcow> rynkiSurowcowData) {
        this.rynkiSurowcowData = rynkiSurowcowData;
    }

    private int liczbaKupujacych =0;
    private int ratioKupujacychDoAktyw=2;

    public void addRynkiSurowiecData(rynekSurowcow rynekSurowcow){
        rynkiSurowcowData.add(rynekSurowcow);
        rynekData.add(rynekSurowcow);
    }

    public void addRynkiWalutData(rynekWalut rynekWalut){
        rynkiWalutData.add(rynekWalut);
        rynekData.add(rynekWalut);
    }

    public void addRynkiAkcjiData(rynekAkcji rynekAkcji){
        rynkiAkcjiData.add(rynekAkcji);
        rynekData.add(rynekAkcji);
    }

    public void addwalutaData(waluta waluta){
        walutaData.add(waluta);
        aktywaData.add(waluta);
    }

    public void addsurowiecData(surowiec surowiec){
        surowiecData.add(surowiec);
        aktywaData.add(surowiec);
    }

    public ObservableList<aktywa> getAktywaData() {
        return aktywaData;
    }

    public void setAktywaData(ObservableList<aktywa> aktywaData) {
        this.aktywaData = aktywaData;
    }

    public void addspolkaData(spolka spolka){
        spolkaData.add(spolka);

        aktywaData.add(spolka.getAkcja());
    }


    public void addinwestorData(inwestor inwestor){
        inwestorData.add(inwestor);
    }

    public ObservableList<rynekWalut> getRynkiWalutData() {
        return rynkiWalutData;
    }

    public void setRynkiWalutData(ObservableList<rynekWalut> rynkiWalutData) {
        this.rynkiWalutData = rynkiWalutData;
    }

    public ObservableList<waluta> getWalutaData() {
        return walutaData;
    }

    public void setWalutaData(ObservableList<waluta> walutaData) {
        this.walutaData = walutaData;
    }

    public ObservableList<surowiec> getSurowiecData() {
        return surowiecData;
    }

    public void setSurowiecData(ObservableList<surowiec> surowiecData) {
        this.surowiecData = surowiecData;
    }

    public ObservableList<spolka> getSpolkaData() {
        return spolkaData;
    }

    public void setSpolkaData(ObservableList<spolka> spolkaData) {
        this.spolkaData = spolkaData;
    }

    public ObservableList<inwestor> getInwestorData() {
        return inwestorData;
    }

    public void setInwestorData(ObservableList<inwestor> inwestorData) {
        this.inwestorData = inwestorData;
    }

    public int getLiczbaAktyw() {
        return liczbaAktyw;
    }

    public void setLiczbaAktyw(int liczbaAktyw) {
        this.liczbaAktyw = liczbaAktyw;
    }

    public int getLiczbaWalut() {
        return liczbaWalut;
    }

    public void setLiczbaWalut(int liczbaWalut) {
        this.liczbaWalut = liczbaWalut;
    }

    public int getLiczbaKupujacych() {
        return liczbaKupujacych;
    }

    public void setLiczbaKupujacych(int liczbaKupujacych) {
        this.liczbaKupujacych = liczbaKupujacych;
    }

    public int getRatioKupujacychDoAktyw() {
        return ratioKupujacychDoAktyw;
    }

    public ObservableList<rynekAkcji> getRynkiAkcjiData() {
        return rynkiAkcjiData;
    }

    public void setRynkiAkcjiData(ObservableList<rynekAkcji> rynkiAkcjiData) {
        this.rynkiAkcjiData = rynkiAkcjiData;
    }

    public ObservableList<akcje> getAkcjeData() {
        return akcjeData;
    }

    public void setAkcjeData(ObservableList<akcje> akcjeData) {
        this.akcjeData = akcjeData;
    }

    public void setRatioKupujacychDoAktyw(int ratioKupujacychDoAktyw) {
        this.ratioKupujacychDoAktyw = ratioKupujacychDoAktyw;

    }

    public LosoweNazwy getNazwy() {
        return nazwy;
    }

    public void setNazwy(LosoweNazwy nazwy) {
        this.nazwy = nazwy;
    }

    public ObservableList<rynek> getRynekData() {
        return rynekData;
    }

    public void setRynekData(ObservableList<rynek> rynekData) {
        this.rynekData = rynekData;
    }

    public void logicLoop(){
        new Thread(new Runnable(){
            public void run(){
                while(1>0){
                    setLiczbaAktyw(getWalutaData().size()+getSurowiecData().size()+getSpolkaData().size());
                    setLiczbaKupujacych(getInwestorData().size());
                    setLiczbaWalut(getWalutaData().size());
                    if(getLiczbaAktyw()*getRatioKupujacychDoAktyw()>getLiczbaKupujacych()){
                        for(int i = getLiczbaKupujacych();i<getLiczbaAktyw()*getRatioKupujacychDoAktyw();i++){
                            addinwestorData(new inwestor(getSurowiecData(),getWalutaData(),nazwy));
                        }
                    }
                    kupowanie();
                    sprzedawanie();
                    wykonajOperacjeKupnaSprzedazy();
                    getZlecenia().resetZlecenia();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
