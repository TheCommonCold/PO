package aktywa;
import rynek.rynekAkcji;
import spolka.spolka;

public class akcje extends aktywa {
    public spolka getSpolka() {
        return spolka;
    }

    public void setSpolka(spolka spolka) {
        this.spolka = spolka;
    }

    public akcje(rynekAkcji rynek, spolka spolka){
        this.spolka=spolka;
        setNazwa(spolka.getName());
        setRynek(rynek);
        rynek.addNewAkcja(this);
    }

    private spolka spolka;
}
