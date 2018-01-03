package portfel;
import aktywa.aktywa;

import kupujacy.podmiotKupujacy;

public class zlecenie {
    private podmiotKupujacy zlecacz;
    private aktywa chceKupic;
    private aktywa chceSprzedac;

    public zlecenie(podmiotKupujacy zlecacz, aktywa chceKupic,aktywa chceSprzedac){
        this.zlecacz=zlecacz;
        this.chceKupic=chceKupic;
        this.chceSprzedac=chceSprzedac;
    }

    public podmiotKupujacy getZlecacz() {
        return zlecacz;
    }

    public void setZlecacz(podmiotKupujacy zlecacz) {
        this.zlecacz = zlecacz;
    }

    public aktywa getChceKupic() {
        return chceKupic;
    }

    public void setChceKupic(aktywa chceKupic) {
        this.chceKupic = chceKupic;
    }

    public aktywa getChceSprzedac() {
        return chceSprzedac;
    }

    public void setChceSprzedac(aktywa chceSprzedac) {
        this.chceSprzedac = chceSprzedac;
    }
}
