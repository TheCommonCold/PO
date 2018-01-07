package aktywa;

import kupujacy.FunduszInwestycyjny;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class JednostkaUczestnictwa implements Serializable {
    private FunduszInwestycyjny FunduszInwestycyjny;
    private Waluta walutaJednostek;
    private float wartoscJednostki;

    public JednostkaUczestnictwa(List<Waluta> walutaList, FunduszInwestycyjny FunduszInwestycyjny) {
        Random generator = new Random();
        this.FunduszInwestycyjny = FunduszInwestycyjny;
        int ktorawaluta = generator.nextInt(walutaList.size());
        int i = 0;
        for (Waluta currentWaluta : walutaList) {
            if (i == ktorawaluta) walutaJednostek = currentWaluta;
        }
        wartoscJednostki = generator.nextInt(300) + generator.nextFloat();
    }

    public FunduszInwestycyjny getFunduszInwestycyjny() {
        return FunduszInwestycyjny;
    }

    public void setFunduszInwestycyjny(FunduszInwestycyjny FunduszInwestycyjny) {
        this.FunduszInwestycyjny = FunduszInwestycyjny;
    }

    public Waluta getWalutaJednostek() {
        return walutaJednostek;
    }

    public void setWalutaJednostek(Waluta walutaJednostek) {
        this.walutaJednostek = walutaJednostek;
    }

    public float getWartoscJednostki() {
        return wartoscJednostki;
    }

    public void setWartoscJednostki(float wartoscJednostki) {
        this.wartoscJednostki = wartoscJednostki;
    }
}
