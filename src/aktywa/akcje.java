package aktywa;

import rynek.RynekAkcji;
import spolka.Spolka;

public class Akcje extends Aktywa {
    private Spolka Spolka;

    public Akcje(RynekAkcji rynek, Spolka Spolka) {
        this.Spolka = Spolka;
        setNazwa(Spolka.getNazwa());
        setRynek(rynek);
        rynek.addNewAkcja(this);
    }

    public Spolka getSpolka() {
        return Spolka;
    }

    public void setSpolka(Spolka Spolka) {
        this.Spolka = Spolka;
    }

}
