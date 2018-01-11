package życie;

import Nazwy.LosoweNazwy;
import aktywa.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kupujacy.FunduszInwestycyjny;
import kupujacy.Inwestor;
import kupujacy.PodmiotKupujacy;
import portfel.*;
import rynek.Rynek;
import rynek.RynekAkcji;
import rynek.RynekSurowcow;
import rynek.RynekWalut;
import spolka.Spolka;

import java.util.Random;

public class DaneRynku {
    private Serializer godSaveUsAll = new Serializer(this);
    private volatile ObservableList<Aktywa> aktywaData = FXCollections.observableArrayList();
    private volatile ObservableList<RynekWalut> rynkiWalutData = FXCollections.observableArrayList();
    private volatile ObservableList<RynekSurowcow> rynkiSurowcowData = FXCollections.observableArrayList();
    private volatile ObservableList<RynekAkcji> rynkiAkcjiData = FXCollections.observableArrayList();
    private volatile ObservableList<Akcje> akcjeData = FXCollections.observableArrayList();
    private volatile ObservableList<Waluta> walutaData = FXCollections.observableArrayList();
    private volatile ObservableList<Surowiec> surowiecData = FXCollections.observableArrayList();
    private volatile ObservableList<Spolka> spolkaData = FXCollections.observableArrayList();
    private volatile ObservableList<Inwestor> inwestorData = FXCollections.observableArrayList();
    private volatile ObservableList<Rynek> rynekData = FXCollections.observableArrayList();
    private volatile ObservableList<FunduszInwestycyjny> funduszInwestycyjnyData = FXCollections.observableArrayList();
    private volatile ObservableList<PodmiotKupujacy> podmiotKupujacyData = FXCollections.observableArrayList();
    private ZleceniaKupnaSprzedazy zlecenia = new ZleceniaKupnaSprzedazy();
    private LosoweNazwy nazwy = new LosoweNazwy();
    private int liczbaAktyw = 0;
    private int liczbaWalut = 0;
    private int liczbaTur = 0;
    private Object monitorZlecen = new Object();
    private Object monitorPodmiotow =new Object();
    private Boolean monitorGUI = false;

    private Main main;
    private int liczbaKupujacych = 0;
    private int ratioKupujacychDoAktyw = 2;
    private boolean active=true;

    public DaneRynku() {
    }

    public DaneRynku(Main main) {
        this.main = main;
    }

    public Object getMonitorZlecen() {
        return monitorZlecen;
    }

    public void setMonitorZlecen(Object monitorZlecen) {
        this.monitorZlecen = monitorZlecen;
    }

    public void refresh() {
        main.refresh();
    }

    public void load() {
        System.out.print("test1");
        active=false;
        for(PodmiotKupujacy currentPodmiot:inwestorData )
        {
            currentPodmiot.halt();
        }
        for(Spolka currentSpolka:spolkaData){
            currentSpolka.halt();
        }
        synchronized (monitorZlecen) {
            try {
                monitorZlecen.notifyAll();
            } catch (Exception e) {

            }
        }
        godSaveUsAll.load();
        System.out.print("test2");
        active=true;
        System.out.print("test3");
        this.logicLoop();
        System.out.print("test4");
    }

    public void save() {
        godSaveUsAll.save();
    }

    public ZleceniaKupnaSprzedazy getZlecenia() {
        return zlecenia;
    }

    public void setZlecenia(ZleceniaKupnaSprzedazy zlecenia) {
        this.zlecenia = zlecenia;
    }

    public void kupowanie() {
        for (PodmiotKupujacy currentPodmiotKupujacy : podmiotKupujacyData) {
            // if(currentPodmiotKupujacy instanceof Inwestor)((Inwestor) currentPodmiotKupujacy).kupJednostkiUczestnictwa(funduszInwestycyjnyData);
            //currentPodmiotKupujacy.zlecKupno(zlecenia,rynkiWalutData,rynkiSurowcowData, rynkiAkcjiData);
        }
    }

    public void sprzedawanie() {
        for (PodmiotKupujacy currentPodmiotKupujacy : podmiotKupujacyData) {
            // currentPodmiotKupujacy.zlecSprzedaz(zlecenia,rynkiWalutData,rynkiSurowcowData,rynkiAkcjiData);
        }
    }

    public void wykonajOperacjeKupnaSprzedazy() {
        synchronized (monitorPodmiotow){
            for (Zlecenie currentSprzedaz : zlecenia.getZleceniaSprzedazy()) {
                for (Zlecenie currentKupno : zlecenia.getZleceniaKupna()) {
                    if (currentSprzedaz.getChceSprzedac().equals(currentKupno.getChceKupic()) && currentSprzedaz.getChceKupic().equals(currentKupno.getChceSprzedac())) {
                        operacjeKupnaSprzedazyWalut(currentSprzedaz, currentKupno);
                        operacjeKupnaSprzedazySurowcow(currentSprzedaz, currentKupno);
                        operacjeKupnaSprzedazyAkcji(currentSprzedaz, currentKupno);
                    }
                }
            }
            for (Zlecenie currentSprzedaz : zlecenia.getZleceniaSprzedazy()) {
                updateCenWalut(currentSprzedaz);
                updateCenaSurowca(currentSprzedaz);
                updateCenaAkcji(currentSprzedaz);
            }
            for (Zlecenie currentKupno : zlecenia.getZleceniaKupna()) {
                updateCenWalut(currentKupno);
                updateCenaSurowca(currentKupno);
                updateCenaAkcji(currentKupno);
            }
        }
        synchronized (monitorZlecen) {
            try {
                monitorZlecen.notifyAll();
            } catch (Exception e) {

            }
        }
    }

    public void updateCenWalut(Zlecenie currentZlecenie) {
        for (RynekWalut currentRynekWalut : rynkiWalutData) {
            for (CenyWalut currentCenyWalut : currentRynekWalut.getListaCen()) {
                if (currentCenyWalut.getWaluta().equals(currentZlecenie.getChceSprzedac())) {
                    for (CenaWaluty currentCenaWaluta : currentCenyWalut.getWartosc()) {
                        if (currentCenaWaluta.getWaluta().equals(currentZlecenie.getChceKupic())) {
                            currentCenaWaluta.setCenaKupna((float) (currentCenaWaluta.getCenaKupna() / 1.01));
                            currentCenaWaluta.setCenaSprzedazy((float) (currentCenaWaluta.getCenaSprzedazy() * 1.01));
                            break;
                        }
                    }
                }
                if (currentCenyWalut.getWaluta().equals(currentZlecenie.getChceKupic())) {
                    for (CenaWaluty currentCenaWaluta : currentCenyWalut.getWartosc()) {
                        if (currentCenaWaluta.getWaluta().equals(currentZlecenie.getChceSprzedac())) {
                            currentCenaWaluta.setCenaKupna((float) (currentCenaWaluta.getCenaKupna() * 1.01));
                            currentCenaWaluta.setCenaSprzedazy((float) (currentCenaWaluta.getCenaSprzedazy() / 1.01));
                            break;
                        }
                    }
                }
            }
        }
    }

    public void updateCenaSurowca(Zlecenie currentZlecenie) {
        if (currentZlecenie.getChceKupic() instanceof Surowiec) {
            ((Surowiec) currentZlecenie.getChceKupic()).setWartosc((float) (((Surowiec) currentZlecenie.getChceKupic()).getWartosc() * 1.01));
        }
        if (currentZlecenie.getChceSprzedac() instanceof Surowiec) {
            ((Surowiec) currentZlecenie.getChceSprzedac()).setWartosc((float) (((Surowiec) currentZlecenie.getChceSprzedac()).getWartosc() / 1.01));
        }
    }

    public void updateCenaAkcji(Zlecenie currentZlecenie) {
        if (currentZlecenie.getChceKupic() instanceof Akcje) {
            ((Akcje) currentZlecenie.getChceKupic()).getSpolka().setWartosc((float) (((Akcje) currentZlecenie.getChceKupic()).getSpolka().getWartosc() * 1.01));
        }
        if (currentZlecenie.getChceSprzedac() instanceof Akcje) {
            ((Akcje) currentZlecenie.getChceSprzedac()).getSpolka().setWartosc((float) (((Akcje) currentZlecenie.getChceSprzedac()).getSpolka().getWartosc() / 1.01));
        }
    }

    public void operacjeKupnaSprzedazyWalut(Zlecenie currentSprzedaz, Zlecenie currentKupno) {
        Random generator = new Random();
        float iloscSprzedajacego = 0, iloscKupujacego = 0;
        if (currentSprzedaz.getChceSprzedac() instanceof Waluta && currentSprzedaz.getChceKupic() instanceof Waluta && currentKupno.getChceSprzedac() instanceof Waluta && currentKupno.getChceKupic() instanceof Waluta) {
            iloscSprzedajacego = currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceSprzedac()).getIlosc();
            iloscKupujacego = currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc();
            float przelicznik = 1;
            for (RynekWalut currentRynekWalut : rynkiWalutData) {
                for (CenyWalut currentCenyWalut : currentRynekWalut.getListaCen()) {
                    if (currentCenyWalut.getWaluta().equals(currentSprzedaz.getChceSprzedac())) {
                        for (CenaWaluty currentCenaWaluta : currentCenyWalut.getWartosc()) {
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
                    ilosc = (generator.nextInt((int) (iloscKupujacego * przelicznik) + 1) + generator.nextFloat()) / 2;
                } else {
                    ilosc = (generator.nextInt((int) (iloscSprzedajacego) + 1) + generator.nextFloat()) / 2;
                }
                if (currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceKupic()) == null) {
                    currentKupno.getZlecacz().getAssets().addNowaWaluta(new StackWalut((Waluta) currentKupno.getChceKupic(), 0));
                }
                if (currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()) == null) {
                    currentSprzedaz.getZlecacz().getAssets().addNowaWaluta(new StackWalut((Waluta) currentSprzedaz.getChceKupic(), 0));
                }
                if (currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceSprzedac()).getIlosc() > ilosc) {
                    if (currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc() > ilosc / przelicznik) {
                        System.out.print(currentKupno.getZlecacz().getImie());
                        System.out.print(" ");
                        System.out.print(currentSprzedaz.getZlecacz().getImie());
                        System.out.print("\n");
                        System.out.print(currentKupno.getChceKupic().getNazwa());
                        System.out.print(" ");
                        System.out.print(currentSprzedaz.getChceKupic().getNazwa());
                        System.out.print("\n");
                        System.out.print("ilosc: ");
                        System.out.print(ilosc);
                        System.out.print("\n");
                        System.out.print(ilosc / przelicznik);
                        System.out.print("\n");
                        System.out.print("\n");
                        currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceSprzedac()).subtractIlosc(ilosc);
                        currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceKupic()).addIlosc(ilosc);
                        currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()).addIlosc(ilosc / przelicznik);
                        currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).subtractIlosc(ilosc / przelicznik);
                    }
                }
            }
        }
    }

    public void operacjeKupnaSprzedazySurowcow(Zlecenie currentSprzedaz, Zlecenie currentKupno) {
        Random generator = new Random();
        float iloscSprzedajacego = 0, iloscKupujacego = 0;
        if (currentSprzedaz.getChceSprzedac() instanceof Surowiec && currentSprzedaz.getChceKupic() instanceof Waluta && currentKupno.getChceSprzedac() instanceof Waluta && currentKupno.getChceKupic() instanceof Surowiec) {
            iloscSprzedajacego = currentSprzedaz.getZlecacz().getAssets().getStackSurowcow(currentSprzedaz.getChceSprzedac()).getIlosc();
            iloscKupujacego = currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc();
            float przelicznik = ((Surowiec) currentSprzedaz.getChceSprzedac()).getWartosc();
            float ilosc = 0;
            if (iloscKupujacego <= 0) System.out.print("ujemna ilosc");
            if (iloscSprzedajacego <= 0) System.out.print("ujemna ilosc");
            System.out.print("surowce");
            System.out.print("\n");
            if (iloscSprzedajacego < iloscKupujacego / przelicznik) {
                ilosc = (generator.nextInt((int) (iloscSprzedajacego) + 1) + generator.nextFloat()) / 2;
            } else {
                ilosc = (generator.nextInt((int) (iloscKupujacego / przelicznik) + 1) + generator.nextFloat()) / 2;
            }
            if (currentKupno.getZlecacz().getAssets().getStackSurowcow(currentKupno.getChceKupic()) == null) {
                currentKupno.getZlecacz().getAssets().addNowySurowiec(new StackSurowcow((Surowiec) currentKupno.getChceKupic(), 0));
            }
            if (currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()) == null) {
                currentSprzedaz.getZlecacz().getAssets().addNowaWaluta(new StackWalut((Waluta) currentSprzedaz.getChceKupic(), 0));
            }
            if (currentSprzedaz.getZlecacz().getAssets().getStackSurowcow(currentSprzedaz.getChceSprzedac()).getIlosc() > ilosc) {
                if (currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc() > ilosc * przelicznik) {
                    System.out.print(currentKupno.getZlecacz().getImie());
                    System.out.print(" ");
                    System.out.print(currentSprzedaz.getZlecacz().getImie());
                    System.out.print("\n");
                    System.out.print(currentKupno.getChceKupic().getNazwa());
                    System.out.print(" ");
                    System.out.print(currentSprzedaz.getChceKupic().getNazwa());
                    System.out.print("\n");
                    System.out.print("ilosc: ");
                    System.out.print(ilosc);
                    System.out.print("\n");
                    System.out.print(ilosc * przelicznik);
                    System.out.print("\n");
                    currentSprzedaz.getZlecacz().getAssets().getStackSurowcow(currentSprzedaz.getChceSprzedac()).subtractIlosc(ilosc);
                    currentKupno.getZlecacz().getAssets().getStackSurowcow(currentKupno.getChceKupic()).addIlosc(ilosc);
                    currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()).addIlosc(ilosc * przelicznik);
                    currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).subtractIlosc(ilosc * przelicznik);
                }
            }
        }
    }

    public void operacjeKupnaSprzedazyAkcji(Zlecenie currentSprzedaz, Zlecenie currentKupno) {
        Random generator = new Random();
        float iloscSprzedajacego = 0, iloscKupujacego = 0;
        if (currentSprzedaz.getChceSprzedac() instanceof Akcje && currentSprzedaz.getChceKupic() instanceof Waluta && currentKupno.getChceSprzedac() instanceof Waluta && currentKupno.getChceKupic() instanceof Akcje) {
            iloscSprzedajacego = currentSprzedaz.getZlecacz().getAssets().getStackAkcji(currentSprzedaz.getChceSprzedac()).getIlosc();
            iloscKupujacego = currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc();
            float przelicznik = ((Akcje) currentSprzedaz.getChceSprzedac()).getSpolka().getWartosc();
            int ilosc = 0;
            if (iloscKupujacego <= 0) System.out.print("ujemna ilosc");
            if (iloscSprzedajacego <= 0) System.out.print("ujemna ilosc");
            System.out.print("Akcje");
            System.out.print("\n");
            if (iloscSprzedajacego < iloscKupujacego / przelicznik) {
                ilosc = (generator.nextInt((int) (iloscSprzedajacego) + 1)) / 2;
            } else {
                ilosc = (generator.nextInt((int) (iloscKupujacego / przelicznik) + 1)) / 2;
            }
            if (currentKupno.getZlecacz().getAssets().getStackAkcji(currentKupno.getChceKupic()) == null) {
                currentKupno.getZlecacz().getAssets().addNowaAkcja(new StackAkcji((Akcje) currentKupno.getChceKupic(), 0));
            }
            if (currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()) == null) {
                currentSprzedaz.getZlecacz().getAssets().addNowaWaluta(new StackWalut((Waluta) currentSprzedaz.getChceKupic(), 0));
            }
            if (currentSprzedaz.getZlecacz().getAssets().getStackAkcji(currentSprzedaz.getChceSprzedac()).getIlosc() > ilosc) {
                if (currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).getIlosc() > ilosc * przelicznik) {
                    System.out.print(currentKupno.getZlecacz().getImie());
                    System.out.print(" ");
                    System.out.print(currentSprzedaz.getZlecacz().getImie());
                    System.out.print("\n");
                    System.out.print(currentKupno.getChceKupic().getNazwa());
                    System.out.print(" ");
                    System.out.print(currentSprzedaz.getChceKupic().getNazwa());
                    System.out.print("\n");
                    System.out.print("ilosc: ");
                    System.out.print(ilosc);
                    System.out.print("\n");
                    System.out.print(ilosc * przelicznik);
                    System.out.print("\n");
                    ((Akcje) currentSprzedaz.getChceSprzedac()).getSpolka().addWolumen(ilosc);
                    ((Akcje) currentSprzedaz.getChceSprzedac()).getSpolka().addObroty(ilosc * przelicznik);
                    currentSprzedaz.getZlecacz().getAssets().getStackAkcji(currentSprzedaz.getChceSprzedac()).subtractIlosc(ilosc);
                    currentKupno.getZlecacz().getAssets().getStackAkcji(currentKupno.getChceKupic()).addIlosc(ilosc);
                    currentSprzedaz.getZlecacz().getAssets().getStackWaluty(currentSprzedaz.getChceKupic()).addIlosc(ilosc * przelicznik);
                    currentKupno.getZlecacz().getAssets().getStackWaluty(currentKupno.getChceSprzedac()).subtractIlosc(ilosc * przelicznik);
                }
            }

        }
    }

    public ObservableList<RynekSurowcow> getRynkiSurowcowData() {
        return rynkiSurowcowData;
    }

    public void setRynkiSurowcowData(ObservableList<RynekSurowcow> rynkiSurowcowData) {
        this.rynkiSurowcowData = rynkiSurowcowData;
    }

    public void addRynkiSurowiecData(RynekSurowcow RynekSurowcow) {
        rynkiSurowcowData.add(RynekSurowcow);
        rynekData.add(RynekSurowcow);
    }

    public void addRynkiWalutData(RynekWalut RynekWalut) {
        rynkiWalutData.add(RynekWalut);
        rynekData.add(RynekWalut);
    }

    public void addRynkiAkcjiData(RynekAkcji RynekAkcji) {
        rynkiAkcjiData.add(RynekAkcji);
        rynekData.add(RynekAkcji);
    }

    public void addwalutaData(Waluta Waluta) {
        walutaData.add(Waluta);
        aktywaData.add(Waluta);
    }

    public void addsurowiecData(Surowiec Surowiec) {
        surowiecData.add(Surowiec);
        aktywaData.add(Surowiec);
    }

    public ObservableList<Aktywa> getAktywaData() {
        return aktywaData;
    }

    public void setAktywaData(ObservableList<Aktywa> aktywaData) {
        this.aktywaData = aktywaData;
    }

    public void addspolkaData(Spolka Spolka) {
        spolkaData.add(Spolka);

        aktywaData.add(Spolka.getAkcja());
    }


    public void addinwestorData(Inwestor Inwestor) {
        inwestorData.add(Inwestor);
        podmiotKupujacyData.add(Inwestor);
    }

    public void addFunduszInwestycyjny(FunduszInwestycyjny FunduszInwestycyjny) {
        funduszInwestycyjnyData.add(FunduszInwestycyjny);
        podmiotKupujacyData.add(FunduszInwestycyjny);
    }

    public ObservableList<RynekWalut> getRynkiWalutData() {
        return rynkiWalutData;
    }

    public void setRynkiWalutData(ObservableList<RynekWalut> rynkiWalutData) {
        this.rynkiWalutData = rynkiWalutData;
    }

    public ObservableList<Waluta> getWalutaData() {
        return walutaData;
    }

    public void setWalutaData(ObservableList<Waluta> walutaData) {
        this.walutaData = walutaData;
    }

    public ObservableList<Surowiec> getSurowiecData() {
        return surowiecData;
    }

    public void setSurowiecData(ObservableList<Surowiec> surowiecData) {
        this.surowiecData = surowiecData;
    }

    public ObservableList<Spolka> getSpolkaData() {
        return spolkaData;
    }

    public void setSpolkaData(ObservableList<Spolka> spolkaData) {
        this.spolkaData = spolkaData;
    }

    public ObservableList<Inwestor> getInwestorData() {
        return inwestorData;
    }

    public void setInwestorData(ObservableList<Inwestor> inwestorData) {
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

    public void setRatioKupujacychDoAktyw(int ratioKupujacychDoAktyw) {
        this.ratioKupujacychDoAktyw = ratioKupujacychDoAktyw;

    }

    public ObservableList<RynekAkcji> getRynkiAkcjiData() {
        return rynkiAkcjiData;
    }

    public void setRynkiAkcjiData(ObservableList<RynekAkcji> rynkiAkcjiData) {
        this.rynkiAkcjiData = rynkiAkcjiData;
    }

    public ObservableList<Akcje> getAkcjeData() {
        return akcjeData;
    }

    public void setAkcjeData(ObservableList<Akcje> akcjeData) {
        this.akcjeData = akcjeData;
    }

    public void wypuszczanieNowychAkcji() {
        for (Spolka currentSpolka : spolkaData) {
            currentSpolka.wypuscNoweAkcje(podmiotKupujacyData);
        }
    }

    public LosoweNazwy getNazwy() {
        return nazwy;
    }

    public void setNazwy(LosoweNazwy nazwy) {
        this.nazwy = nazwy;
    }

    public ObservableList<Rynek> getRynekData() {
        return rynekData;
    }

    public void setRynekData(ObservableList<Rynek> rynekData) {
        this.rynekData = rynekData;
    }

    public ObservableList<FunduszInwestycyjny> getFunduszInwestycyjnyData() {
        return funduszInwestycyjnyData;
    }

    public void setFunduszInwestycyjnyData(ObservableList<FunduszInwestycyjny> funduszInwestycyjnyData) {
        this.funduszInwestycyjnyData = funduszInwestycyjnyData;
    }

    public ObservableList<PodmiotKupujacy> getPodmiotKupujacyData() {
        return podmiotKupujacyData;
    }

    public void setPodmiotKupujacyData(ObservableList<PodmiotKupujacy> podmiotKupujacyData) {
        this.podmiotKupujacyData = podmiotKupujacyData;
    }

    public void zapisywanieWartosic() {
        for (Surowiec currentSurowiec : surowiecData) {
            currentSurowiec.zapiszWartosc(currentSurowiec.getWartosc());
        }
        for (Spolka currentSpolka : spolkaData) {
            currentSpolka.getAkcja().zapiszWartosc(currentSpolka.getWartosc());
        }
        for (RynekWalut currentRynek : rynkiWalutData) {
            currentRynek.zapiszCeny();
        }
    }

    public void updateSpolki() {
        for (Spolka currentSpolka : spolkaData) {
            currentSpolka.generateZysk();
            currentSpolka.generateKursOtwarcia();
            currentSpolka.setObroty(0);
            currentSpolka.setWolumen(0);
        }
    }

    public void watkiRun() {
        for (PodmiotKupujacy currentPodmiotKupujacy : podmiotKupujacyData) {
            if (!currentPodmiotKupujacy.isAlive()) currentPodmiotKupujacy.start();
        }
        for (Spolka currentSpolka : spolkaData) {
            if (!currentSpolka.isAlive()) currentSpolka.start();
        }
    }

    public DaneRynku getDaneRynku() {
        return this;
    }

    public void debugZlecenia() {
        System.out.print("ZLECENIA KUPNA: ");
        System.out.print("\n");
        for (Zlecenie currentZlecenieKupna : getZlecenia().getZleceniaKupna()) {
            System.out.print(currentZlecenieKupna.getZlecacz().getImie());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceKupic().getNazwa());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceSprzedac().getNazwa());
            System.out.print("\n");
        }
        System.out.print("ZLECENIA SPRZEDAZY: ");
        System.out.print("\n");
        for (Zlecenie currentZlecenieKupna : getZlecenia().getZleceniaSprzedazy()) {
            System.out.print(currentZlecenieKupna.getZlecacz().getImie());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceSprzedac().getNazwa());
            System.out.print(" ");
            System.out.print(currentZlecenieKupna.getChceKupic().getNazwa());
            System.out.print("\n");
        }
    }

    public Object getMonitorPodmiotow() {
        return monitorPodmiotow;
    }

    public void setMonitorPodmiotow(Object monitorPodmiotow) {
        this.monitorPodmiotow = monitorPodmiotow;
    }

    public Object getMonitorGUI() {
        return monitorGUI;
    }

    public void setMonitorGUI(boolean bool) {
        this.monitorGUI = bool;
    }



    public void logicLoop() {
        new Thread(new Runnable() {
            public void run() {
                while (active==true) {
                    getMonitorPodmiotow();
                    System.out.print(liczbaTur);System.out.print("\n");
                    while(monitorGUI==true){System.out.print(monitorGUI);};
                    setLiczbaAktyw(getAktywaData().size());
                    setLiczbaKupujacych(getPodmiotKupujacyData().size());
                    setLiczbaWalut(getWalutaData().size());
                        synchronized (monitorPodmiotow) {
                            if (getLiczbaAktyw() * getRatioKupujacychDoAktyw() > getLiczbaKupujacych()) {
                                for (int i = getLiczbaKupujacych(); i < getLiczbaAktyw() * getRatioKupujacychDoAktyw(); i++) {
                                    addinwestorData(new Inwestor(getDaneRynku(), nazwy));
                                    if (i % 2 == 0)
                                        addFunduszInwestycyjny(new FunduszInwestycyjny(getDaneRynku(), nazwy));
                                }
                            }
                            watkiRun();
                            wypuszczanieNowychAkcji();
                            zapisywanieWartosic();
                            refresh();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            wykonajOperacjeKupnaSprzedazy();
                        }
                    getZlecenia().resetZlecenia();
                    liczbaTur++;
                }
            }
        }).start();
    }

    public int getLiczbaTur() {
        return liczbaTur;
    }

    public void setLiczbaTur(int liczbaTur) {
        this.liczbaTur = liczbaTur;
    }
}
