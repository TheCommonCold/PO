package portfel;

import aktywa.Aktywa;
import kupujacy.PodmiotKupujacy;

public class Zlecenie {
    private PodmiotKupujacy zlecacz;
    private Aktywa chceKupic;
    private Aktywa chceSprzedac;

    public Zlecenie(PodmiotKupujacy zlecacz, Aktywa chceKupic, Aktywa chceSprzedac) {
        this.zlecacz = zlecacz;
        this.chceKupic = chceKupic;
        this.chceSprzedac = chceSprzedac;
    }

    public PodmiotKupujacy getZlecacz() {
        return zlecacz;
    }

    public void setZlecacz(PodmiotKupujacy zlecacz) {
        this.zlecacz = zlecacz;
    }

    public Aktywa getChceKupic() {
        return chceKupic;
    }

    public void setChceKupic(Aktywa chceKupic) {
        this.chceKupic = chceKupic;
    }

    public Aktywa getChceSprzedac() {
        return chceSprzedac;
    }

    public void setChceSprzedac(Aktywa chceSprzedac) {
        this.chceSprzedac = chceSprzedac;
    }
}
