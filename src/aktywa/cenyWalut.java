package aktywa;

import java.util.HashSet;
import java.util.Set;

public class cenyWalut {
    private waluta waluta;
    private Set<cenaWaluty> wartosc = new HashSet<>();

    public cenyWalut(waluta waluta){
        this.waluta = waluta;
    }

    public void addWaluta(waluta waluta,float cenaKupna, float cenaSprzedazy){
        wartosc.add(new cenaWaluty(waluta,cenaKupna,cenaSprzedazy));
    }

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
    }

    public Set<cenaWaluty> getWartosc() {
        return wartosc;
    }

    public void setWartosc(Set<cenaWaluty> wartosc) {
        this.wartosc = wartosc;
    }
}
