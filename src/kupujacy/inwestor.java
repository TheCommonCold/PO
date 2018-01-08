package kupujacy;

import Nazwy.LosoweNazwy;
import javafx.beans.property.SimpleStringProperty;
import portfel.StackJednostekUczestnictwa;
import portfel.StackWalut;
import życie.DaneRynku;

import java.util.Random;

public class Inwestor extends PodmiotKupujacy {
    private double pesel;

    public Inwestor(DaneRynku daneRynku, LosoweNazwy nazwy) {
        setImie(nazwy.getImieInwestora());
        setNazwisko(nazwy.getNazwiskoInwestora());
        createPesel();
        defaultPodmiotKupujacyConstructor(daneRynku, "Inwestor");
    }

    public Inwestor(DaneRynku daneRynku, String imie, String nazwisko) {
        setImie(imie);
        setNazwisko(nazwisko);
        createPesel();
        defaultPodmiotKupujacyConstructor(daneRynku, "Inwestor");
    }

    public void createPesel() {
        Random generator = new Random();
        pesel = 0;
        for (int i = 1; i < 11; i++) {
            pesel = pesel + (generator.nextInt(10) * Math.pow(10, i));
        }
    }

    @Override
    public void run() {
        Random generator = new Random();
        int counter = 0;
        while (1 > 0) {
            if (counter == 10) {
                zwiekszenieBudzetu();
            }
            zlecKupno();
            zlecSprzedaz();
            synchronized (getDaneRynku().getMonitorPodmiotow()) {
                kupJednostkiUczestnictwa();
            }
            synchronized (getDaneRynku().getMonitorZlecen()) {
                try {
                    getDaneRynku().getMonitorZlecen().wait();
                } catch (Exception e) {

                }
            }
            counter++;
        }
    }


    public double getPesel() {
        return pesel;
    }

    public void setPesel(double pesel) {
        this.pesel = pesel;
    }

    public SimpleStringProperty getPeselProperty() {
        return new SimpleStringProperty(Double.toString(pesel));
    }

    public synchronized void kupJednostkiUczestnictwa() {
        Random generator = new Random();
        if (!getDaneRynku().getFunduszInwestycyjnyData().isEmpty() && !getAssets().getWaluty().isEmpty()) {
            for (FunduszInwestycyjny currentFundusz : getDaneRynku().getFunduszInwestycyjnyData()) {
                if (getAgresja() > generator.nextFloat()) {
                    if (getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()) != null) {
                        int ilosc = (int) (generator.nextInt((int) getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()).getIlosc() + 1) / currentFundusz.getJednostkaUczestnictwa().getWartoscJednostki());
                        if (ilosc * currentFundusz.getJednostkaUczestnictwa().getWartoscJednostki() < getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()).getIlosc()) {
                            getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()).subtractIlosc(ilosc * currentFundusz.getJednostkaUczestnictwa().getWartoscJednostki());
                            if (currentFundusz.getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()) == null) {
                                currentFundusz.getAssets().addNowaWaluta(new StackWalut(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek(), 0));
                            }
                            currentFundusz.getAssets().getStackWaluty(currentFundusz.getJednostkaUczestnictwa().getWalutaJednostek()).addIlosc(ilosc * currentFundusz.getJednostkaUczestnictwa().getWartoscJednostki());
                            if (getAssets().getStackJednostekUczestnictwa(currentFundusz.getJednostkaUczestnictwa()) == null) {
                                getAssets().addNowaJednostkaUczestnictwa(new StackJednostekUczestnictwa(currentFundusz.getJednostkaUczestnictwa(), 0));
                            }
                            getAssets().getStackJednostekUczestnictwa(currentFundusz.getJednostkaUczestnictwa()).addIlosc(ilosc);
                        }
                    }
                }
            }
        }
    }


    public void zwiekszenieBudzetu() {
        Random generator = new Random();
        if (generator.nextFloat() < 0.05 && getAssets().getWaluty().size() > 0) {
            int ktoraWaluta = generator.nextInt(getAssets().getWaluty().size());
            int i = 0;
            for (StackWalut currentWaluta : getAssets().getWaluty()) {
                if (i == ktoraWaluta) {
                    if (getAssets().getStackWaluty(currentWaluta.getWaluta()) != null) {
                        getAssets().getStackWaluty(currentWaluta.getWaluta()).addIlosc(generator.nextInt(10000));
                    }
                }
                i++;
            }
        }
    }

}
