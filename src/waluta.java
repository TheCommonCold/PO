import java.util.*;
public class waluta extends aktywa {
    private Set<cenaWaluty> ceny;

    public Set<cenaWaluty> getCeny() {
        return ceny;
    }

    public void setCeny(Set<cenaWaluty> ceny) {
        this.ceny = ceny;
    }

    public int getListaKrajow() {
        return listaKrajow;
    }

    public void setListaKrajow(int listaKrajow) {
        this.listaKrajow = listaKrajow;
    }

    private int listaKrajow;
}
