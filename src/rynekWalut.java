import java.util.*;
public class rynekWalut extends rynek {
    public Set<waluta> getListaWalut() {
        return listaWalut;
    }

    public void setListaWalut(Set<waluta> listaWalut) {
        this.listaWalut = listaWalut;
    }

    private Set<waluta> listaWalut;
}
