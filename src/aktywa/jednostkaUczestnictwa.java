package aktywa;

import kupujacy.funduszInwestycyjny;

import java.util.List;
import java.util.Random;

public class jednostkaUczestnictwa {
    private funduszInwestycyjny funduszInwestycyjny;
    private waluta walutaJednostek;
    private float wartoscJednostki;

    public jednostkaUczestnictwa(List<waluta> walutaList,funduszInwestycyjny funduszInwestycyjny){
        Random generator = new Random();
        this.funduszInwestycyjny=funduszInwestycyjny;
        int ktorawaluta = generator.nextInt(walutaList.size());
        int i=0;
        for(waluta currentWaluta:walutaList){
            if(i==ktorawaluta)walutaJednostek=currentWaluta;
        }
        wartoscJednostki=generator.nextInt(300)+generator.nextFloat();
    }

    public kupujacy.funduszInwestycyjny getFunduszInwestycyjny() {
        return funduszInwestycyjny;
    }

    public void setFunduszInwestycyjny(kupujacy.funduszInwestycyjny funduszInwestycyjny) {
        this.funduszInwestycyjny = funduszInwestycyjny;
    }

    public waluta getWalutaJednostek() {
        return walutaJednostek;
    }

    public void setWalutaJednostek(waluta walutaJednostek) {
        this.walutaJednostek = walutaJednostek;
    }

    public float getWartoscJednostki() {
        return wartoscJednostki;
    }

    public void setWartoscJednostki(float wartoscJednostki) {
        this.wartoscJednostki = wartoscJednostki;
    }
}
