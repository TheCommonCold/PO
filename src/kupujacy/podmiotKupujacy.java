package kupujacy;

import aktywa.Akcje;
import aktywa.Surowiec;
import aktywa.Waluta;
import javafx.beans.property.SimpleStringProperty;
import portfel.*;
import rynek.RynekAkcji;
import rynek.RynekSurowcow;
import rynek.RynekWalut;
import życie.DaneRynku;

import java.io.Serializable;
import java.util.Random;

public abstract class PodmiotKupujacy extends Thread implements Serializable {
    private String imie;
    private String nazwisko;
    private Portfel assets;
    private float agresja;
    private String typ;
    private transient DaneRynku daneRynku;
    private boolean active=true;


    public float getAgresja() {
        return agresja;
    }

    public void setAgresja(float agresja) {
        this.agresja = agresja;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Portfel getAssets() {
        return assets;
    }

    public void setAssets(Portfel assets) {
        this.assets = assets;
    }

    public void defaultPodmiotKupujacyConstructor(DaneRynku daneRynku, String typ) {
        this.daneRynku = daneRynku;
        this.typ = typ;
        Random generator = new Random();
        setAgresja((generator.nextFloat() / 4) + (1 / 4));
        setAssets(new Portfel());
        for (Surowiec currentSurowiec : daneRynku.getSurowiecData()) {
            if (generator.nextFloat() > 0.3) {
                getAssets().addNowySurowiec(new StackSurowcow(currentSurowiec, generator.nextFloat() + generator.nextInt(10000)));
            }
        }
        for (Waluta currentWaluta : daneRynku.getWalutaData()) {
            if (generator.nextFloat() > 0.3) {
                getAssets().addNowaWaluta(new StackWalut(currentWaluta, generator.nextFloat() + generator.nextInt(100000)));
            }
        }
    }

    public void zlecKupno() {
        Random generator = new Random();
        if (assets.getWaluty().size() > 0) {
            for (RynekWalut currentRynek : daneRynku.getRynkiWalutData()) {
                for (Waluta currentWaluta : currentRynek.getListaWalut()) {
                    for (StackWalut currentStackWalut : assets.getWaluty()) {
                        if (getAgresja() > generator.nextFloat() && currentStackWalut.getIlosc() > 2 && !currentWaluta.equals(currentStackWalut.getWaluta()) && currentRynek.getListaWalut().contains(currentStackWalut.getWaluta())) {
                            daneRynku.getZlecenia().addZlecenieKupna(new Zlecenie(this, currentWaluta, currentStackWalut.getWaluta()));
                            break;
                        }
                    }
                }
            }
        }
        for (RynekSurowcow currentRynek : daneRynku.getRynkiSurowcowData()) {
            for (Surowiec currentSurowiec : currentRynek.getListSurowcow()) {
                for (StackWalut currentStackWalut : assets.getWaluty()) {
                    if (currentSurowiec.getWaluta().equals(currentStackWalut.getWaluta())) {
                        if (getAgresja() > generator.nextFloat() && currentStackWalut.getIlosc() > currentSurowiec.getWartosc()) {
                            daneRynku.getZlecenia().addZlecenieKupna(new Zlecenie(this, currentSurowiec, currentSurowiec.getWaluta()));
                        }
                    }
                }
            }
        }
        for (RynekAkcji currentRynekAkcji : daneRynku.getRynkiAkcjiData()) {
            for (Akcje currentAkcja : currentRynekAkcji.getListAkcji()) {
                for (StackWalut currentStackWalut : assets.getWaluty()) {
                    if (currentRynekAkcji.getWalutaRynku().equals(currentStackWalut.getWaluta())) {
                        if (getAgresja() > generator.nextFloat() && currentStackWalut.getIlosc() > currentAkcja.getSpolka().getWartosc()) {
                            daneRynku.getZlecenia().addZlecenieKupna(new Zlecenie(this, currentAkcja, currentRynekAkcji.getWalutaRynku()));
                        }
                    }
                }
            }
        }

    }

    public void zlecSprzedaz() {
        Random generator = new Random();
        for (RynekWalut currentRynek : daneRynku.getRynkiWalutData()) {
            for (StackWalut currentStackWalut : assets.getWaluty()) {
                for (Waluta currentWaluta : currentRynek.getListaWalut()) {
                    if (getAgresja() > generator.nextFloat() && currentStackWalut.getIlosc() > 2 && !currentStackWalut.getWaluta().equals(currentWaluta) && currentRynek.getListaWalut().contains(currentStackWalut.getWaluta())) {
                        daneRynku.getZlecenia().addZlecenieSprzedazy(new Zlecenie(this, currentWaluta, currentStackWalut.getWaluta()));
                    }
                }
            }
        }
        for (RynekSurowcow currentRynek : daneRynku.getRynkiSurowcowData()) {
            for (StackSurowcow currentStackSurowcow : assets.getSurowce()) {
                if (currentStackSurowcow.getIlosc() > 0) {
                    if (getAgresja() > generator.nextFloat()) {
                        daneRynku.getZlecenia().addZlecenieSprzedazy(new Zlecenie(this, currentStackSurowcow.getSurowiec().getWaluta(), currentStackSurowcow.getSurowiec()));
                    }
                }
            }
        }
        for (RynekAkcji currentRynekAkcji : daneRynku.getRynkiAkcjiData()) {
            for (Akcje currentAkcja : currentRynekAkcji.getListAkcji()) {
                for (StackAkcji currentStackAkcji : assets.getAkcje()) {
                    if (currentStackAkcji.getIlosc() > 0 && currentAkcja.equals(currentStackAkcji.getAkcja())) {
                        if (getAgresja() > generator.nextFloat()) {
                            daneRynku.getZlecenia().addZlecenieSprzedazy((new Zlecenie(this, currentRynekAkcji.getWalutaRynku(), currentStackAkcji.getAkcja())));
                        }
                    }
                }
            }
        }
    }

    public SimpleStringProperty getImieProperty() {
        return new SimpleStringProperty(imie);
    }

    public SimpleStringProperty getTypProperty() {
        return new SimpleStringProperty(typ);
    }

    public SimpleStringProperty getNazwiskoProperty() {
        return new SimpleStringProperty(nazwisko);
    }

    public void halt(){active=false;}

    public void run() {
        Random generator = new Random();
        while (active==true) {
            zlecKupno();
            zlecSprzedaz();
            synchronized (daneRynku.getMonitorZlecen()) {
                try {
                    daneRynku.getMonitorZlecen().wait();
                } catch (Exception e) {

                }
            }
        }

    }

    public DaneRynku getDaneRynku() {
        return daneRynku;
    }

    public void setDaneRynku(DaneRynku daneRynku) {
        this.daneRynku = daneRynku;
    }

    public void wczyt(DaneRynku daneRynku){
        assets.wczyt();
        this.daneRynku=daneRynku;
    }
}
