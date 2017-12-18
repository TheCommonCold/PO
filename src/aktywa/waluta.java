package aktywa;

import java.util.Set;
import java.lang.String;
public class waluta extends aktywa {
    private cenaWaluty ceny;
    private String kraj;

    public waluta(String nazwa, String kraj ){
        this.kraj= kraj;
        this.setNazwa(nazwa);
    }

}
