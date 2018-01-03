package aktywa;
import spolka.spolka;
import java.util.*;
public class akcje extends aktywa {
    public spolka getSpolka() {
        return spolka;
    }

    public void setSpolka(spolka spolka) {
        this.spolka = spolka;
    }

    public akcje(spolka spolka){
        this.spolka=spolka;
        setNazwa(spolka.getName());
    }

    private spolka spolka;
}
