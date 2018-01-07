package indeks;
import spolka.spolka;

import java.util.*;
public class indeks {
    private String nazwa;
    private Set<spolka> listSpolek;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Set<spolka> getListSpolek() {
        return listSpolek;
    }

    public void setListSpolek(Set<spolka> listSpolek) {
        this.listSpolek = listSpolek;
    }
}
