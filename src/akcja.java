import java.util.*;
public class akcja extends aktywa {
    private String kraj;
    private waluta waluta;
    private String miasto;

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public Set<indeks> getListIndeksow() {
        return listIndeksow;
    }

    public void setListIndeksow(Set<indeks> listIndeksow) {
        this.listIndeksow = listIndeksow;
    }

    private Set<indeks> listIndeksow;
}
