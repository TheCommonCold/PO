package spolka;

import Nazwy.LosoweNazwy;
import aktywa.Akcje;
import javafx.collections.ObservableList;
import kupujacy.Inwestor;
import kupujacy.PodmiotKupujacy;
import portfel.StackAkcji;
import portfel.StackWalut;
import rynek.RynekAkcji;
import życie.DaneRynku;

import java.util.Date;
import java.util.Random;

public class Spolka extends Thread {
    private String name;
    private Date dataPierwszejWyceny;
    private float wartosc;
    private float kursOtwarcia;
    private float minimalnyKurs;
    private float maksymalnyKurs;
    private Akcje akcja;
    private int liczbaAkcji;
    private double zysk;
    private double przychod;
    private float kapitalWlasny;
    private float kapitalZakladowy;
    private int wolumen;
    private float wartoscPoczatkowa;
    private boolean chceWypuscic = false;
    private float obroty;
    private boolean active=true;
    private DaneRynku daneRynku;

    public Spolka(RynekAkcji rynek, DaneRynku daneRynku, int ratioKupujacychDoAktyw, LosoweNazwy nazwy) {
        Random generator = new Random();
        name = Integer.toString(generator.nextInt());
        defaultSpolkaConstructor(rynek, daneRynku, ratioKupujacychDoAktyw, nazwy);
    }

    public Spolka(RynekAkcji rynek, DaneRynku daneRynku, int ratioKupujacychDoAktyw, String nazwa, LosoweNazwy nazwy) {
        this.name = nazwa;
        defaultSpolkaConstructor(rynek, daneRynku, ratioKupujacychDoAktyw, nazwy);
    }

    public Akcje getAkcja() {
        return akcja;
    }

    public void setAkcja(Akcje akcja) {
        this.akcja = akcja;
    }

    public void defaultSpolkaConstructor(RynekAkcji rynek, DaneRynku daneRynku, int ratioKupujacychDoAktyw, LosoweNazwy nazwy) {
        for (int i = 0; i < ratioKupujacychDoAktyw; i++) {
            daneRynku.getInwestorData().add(new Inwestor(daneRynku, nazwy));
        }
        daneRynku=daneRynku.getDaneRynku();
        Random generator = new Random();
        akcja = new Akcje(rynek, this);
        dataPierwszejWyceny = new Date();
        kursOtwarcia = generator.nextFloat() + generator.nextInt(500);
        minimalnyKurs = kursOtwarcia;
        maksymalnyKurs = kursOtwarcia;
        wartosc = kursOtwarcia;
        wartoscPoczatkowa = wartosc;
        daneRynku.getAkcjeData().add(akcja);
        przychod = generator.nextInt(10000000);
        zysk = przychod * 0.77;
        liczbaAkcji = 0;
        kapitalWlasny = generator.nextFloat() + generator.nextInt(1000000000);
        kapitalZakladowy = generator.nextFloat() + generator.nextInt(100000000);
        obroty = 0;
        wolumen = 0;
        for (PodmiotKupujacy currentPodmiot : daneRynku.getPodmiotKupujacyData()) {
            int temp = generator.nextInt(100000 / daneRynku.getPodmiotKupujacyData().size());
            currentPodmiot.getAssets().addNowaAkcja(new StackAkcji(akcja, temp));
            liczbaAkcji += temp;
        }
    }

    public void wykupAkcji(ObservableList<PodmiotKupujacy> podmiotKupujacyData, float cenaWykupu) {
        Random generator = new Random();
        int tempLiczbaAkcji = liczbaAkcji;
        for (PodmiotKupujacy currentPodmiot : podmiotKupujacyData) {
            if (currentPodmiot.getAssets().getStackAkcji(akcja) != null && currentPodmiot.getAssets().getStackAkcji(akcja).getIlosc() > 2) {
                int temp = generator.nextInt((int) currentPodmiot.getAssets().getStackAkcji(akcja).getIlosc());
                currentPodmiot.getAssets().getStackAkcji(akcja).subtractIlosc(temp);
                if (currentPodmiot.getAssets().getStackWaluty(((RynekAkcji) akcja.getRynek()).getWalutaRynku()) != null) {
                    currentPodmiot.getAssets().getStackWaluty(((RynekAkcji) akcja.getRynek()).getWalutaRynku()).addIlosc(temp * cenaWykupu);
                } else {
                    currentPodmiot.getAssets().addNowaWaluta(new StackWalut(((RynekAkcji) akcja.getRynek()).getWalutaRynku(), temp * cenaWykupu));
                }
                liczbaAkcji -= temp;
            }
        }
    }

    public synchronized void wypuscNoweAkcje(ObservableList<PodmiotKupujacy> podmiotKupujacyData) {
        Random generator = new Random();
        if (generator.nextFloat() < 0.8) {
            for (PodmiotKupujacy currentPodmiot : podmiotKupujacyData) {
                if (generator.nextFloat() < 0.1) {
                    int temp = generator.nextInt(100);
                    if (currentPodmiot.getAssets().getStackAkcji(akcja) != null) {
                        currentPodmiot.getAssets().getStackAkcji(akcja).addIlosc(temp);
                    } else {
                        currentPodmiot.getAssets().addNowaAkcja(new StackAkcji(akcja, temp));
                    }
                    liczbaAkcji += temp;
                }
            }
            chceWypuscic = false;
        }
    }

    public void halt(){active=false;}

    public void run() {
        Random generator = new Random();
        int counter = 0;
        while (active==true) {
            if (counter == 5) {
                generateZysk();
                generateKursOtwarcia();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (daneRynku.getMonitorPodmiotow()) {
                wypuscNoweAkcje(daneRynku.getPodmiotKupujacyData());
            }
            counter++;
        }
    }

    public void generateZysk() {
        Random generator = new Random();
        przychod = przychod + generator.nextInt(10000) - generator.nextInt(10000);
        zysk = przychod * 0.77;
    }

    public void generateKursOtwarcia() {
        kursOtwarcia = wartosc;
        if (kursOtwarcia > maksymalnyKurs) maksymalnyKurs = kursOtwarcia;
        if (kursOtwarcia < minimalnyKurs) minimalnyKurs = kursOtwarcia;
    }

    public Date getDataPierwszejWyceny() {
        return dataPierwszejWyceny;
    }

    public void setDataPierwszejWyceny(Date dataPierwszejWyceny) {
        this.dataPierwszejWyceny = dataPierwszejWyceny;
    }

    public String getNazwa() {
        return name;
    }

    public void setNazwa(String name) {
        this.name = name;
    }

    public float getKursOtwarcia() {
        return kursOtwarcia;
    }

    public void setKursOtwarcia(float kursOtwarcia) {
        this.kursOtwarcia = kursOtwarcia;
    }

    public float getMinimalnyKurs() {
        return minimalnyKurs;
    }

    public void setMinimalnyKurs(float minimalnyKurs) {
        this.minimalnyKurs = minimalnyKurs;
    }

    public float getMaksymalnyKurs() {
        return maksymalnyKurs;
    }

    public void setMaksymalnyKurs(float maksymalnyKurs) {
        this.maksymalnyKurs = maksymalnyKurs;
    }

    public int getLiczbaAkcji() {
        return liczbaAkcji;
    }

    public void setLiczbaAkcji(int liczbaAkcji) {
        this.liczbaAkcji = liczbaAkcji;
    }

    public double getZysk() {
        return zysk;
    }

    public void setZysk(double zysk) {
        this.zysk = zysk;
    }

    public double getPrzychod() {
        return przychod;
    }

    public void setPrzychod(double przychod) {
        this.przychod = przychod;
    }

    public float getKapitalWlasny() {
        return kapitalWlasny;
    }

    public void setKapitalWlasny(float kapitalWlasny) {
        this.kapitalWlasny = kapitalWlasny;
    }

    public float getKapitalZakladowy() {
        return kapitalZakladowy;
    }

    public void setKapitalZakladowy(float kapitalZakladowy) {
        this.kapitalZakladowy = kapitalZakladowy;
    }

    public int getWolumen() {
        return wolumen;
    }

    public void setWolumen(int wolumen) {
        this.wolumen = wolumen;
    }

    public float getObroty() {
        return obroty;
    }

    public void setObroty(float obroty) {
        this.obroty = obroty;
    }

    public float getWartosc() {
        return wartosc;
    }

    public void setWartosc(float wartosc) {
        this.wartosc = wartosc;
    }

    public float getWartoscPoczatkowa() {
        return wartoscPoczatkowa;
    }

    public void setWartoscPoczatkowa(float wartoscPoczatkowa) {
        this.wartoscPoczatkowa = wartoscPoczatkowa;
    }

    public void addWolumen(int ilosc) {
        wolumen += ilosc;
    }

    public void addObroty(float ilosc) {
        obroty += ilosc;
    }
}
