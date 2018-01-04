package spolka;
import aktywa.surowiec;
import aktywa.waluta;
import aktywa.akcje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kupujacy.inwestor;
import portfel.stackAkcji;
import rynek.rynekAkcji;

import java.util.Date;
import java.util.Random;

public class spolka {
    private String name;
    private Date dataPierwszejWyceny;
    private float wartosc;
    private float kursOtwarcia;
    private float minimalnyKurs;
    private float maksymalnyKurs;
    private akcje akcja;
    private int liczbaAkcji;
    private float zysk;
    private float przychod;
    private float kapitalWlasny;
    private float kapitalZakladowy;
    private int wolumen;

    public akcje getAkcja() {
        return akcja;
    }

    public void setAkcja(akcje akcja) {
        this.akcja = akcja;
    }

    public spolka(rynekAkcji rynek, ObservableList<inwestor> inwestorData, ObservableList<waluta> walutaData, ObservableList<surowiec> surowiecData, ObservableList<akcje>akcjeData, int ratioKupujacychDoAktyw){
        Random generator = new Random();
        name=Integer.toString(generator.nextInt());
        defaultSpolkaConstructor(rynek,inwestorData,walutaData,surowiecData,akcjeData,ratioKupujacychDoAktyw);
    }

    public spolka(rynekAkcji rynek,ObservableList<inwestor> inwestorData, ObservableList<waluta> walutaData, ObservableList<surowiec> surowiecData,ObservableList<akcje>akcjeData, int ratioKupujacychDoAktyw,String nazwa){
        this.name=nazwa;
        defaultSpolkaConstructor(rynek,inwestorData,walutaData,surowiecData,akcjeData,ratioKupujacychDoAktyw);
    }

    public void defaultSpolkaConstructor(rynekAkcji rynek,ObservableList<inwestor> inwestorData, ObservableList<waluta> walutaData, ObservableList<surowiec> surowiecData,ObservableList<akcje>akcjeData, int ratioKupujacychDoAktyw){
        for(int i =0;i<ratioKupujacychDoAktyw;i++){
            inwestorData.add(new inwestor(surowiecData,walutaData));
        }
        Random generator = new Random();
        akcja=new akcje(rynek,this);
        dataPierwszejWyceny = new Date();
        kursOtwarcia=generator.nextFloat()+generator.nextInt(500);
        minimalnyKurs=kursOtwarcia;
        maksymalnyKurs=kursOtwarcia;
        wartosc=kursOtwarcia;
        akcjeData.add(akcja);
        zysk=0;
        przychod=0;
        liczbaAkcji=0;
        kapitalWlasny=generator.nextFloat()+generator.nextInt(1000000000);
        kapitalZakladowy=generator.nextFloat()+generator.nextInt(100000000);
        obroty=0;
        for(inwestor currentInwestor:inwestorData){
            int temp=generator.nextInt(100000/inwestorData.size());
            currentInwestor.getAssets().addNowaAkcja(new stackAkcji(akcja,temp));
            liczbaAkcji+=temp;
        }
    }

    public Date getDataPierwszejWyceny() {
        return dataPierwszejWyceny;
    }

    public void setDataPierwszejWyceny(Date dataPierwszejWyceny) {
        this.dataPierwszejWyceny = dataPierwszejWyceny;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public float getZysk() {
        return zysk;
    }

    public void setZysk(float zysk) {
        this.zysk = zysk;
    }

    public float getPrzychod() {
        return przychod;
    }

    public void setPrzychod(float przychod) {
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

    private float obroty;
}
