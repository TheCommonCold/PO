package portfel;

import aktywa.Waluta;

public class StackWalut extends StackAktyw {
    private Waluta Waluta;

    public StackWalut(Waluta Waluta, float ilosc) {
        this.Waluta = Waluta;
        setIlosc(ilosc);
    }

    public Waluta getWaluta() {
        return Waluta;
    }

    public void setWaluta(Waluta Waluta) {
        this.Waluta = Waluta;
    }
}
