package portfel;

import aktywa.waluta;

import java.util.Random;

public class stackWalut extends stackAktyw{
    private aktywa.waluta waluta;

    public stackWalut(waluta waluta, float ilosc){
        Random generator = new Random();
        this.waluta=waluta;
        setIlosc(ilosc);
    }

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
    }
}
