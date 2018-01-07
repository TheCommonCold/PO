package aktywa;

import Nazwy.LosoweNazwy;
import rynek.RynekWalut;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Waluta extends Aktywa {

    private Set<String> listaKrajow = new HashSet<>();

    public Waluta(RynekWalut rynek, LosoweNazwy nazwy) {
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultWalutaConstructor(rynek, nazwy);
    }

    public Waluta(RynekWalut rynek, LosoweNazwy nazwy, String nazwa) {
        setNazwa(nazwa);
        defaultWalutaConstructor(rynek, nazwy);
    }

    public void defaultWalutaConstructor(RynekWalut rynek, LosoweNazwy nazwa) {
        Random generator = new Random();
        for (int i = 0; i < generator.nextInt(3) + 1; i++) {
            listaKrajow.add(nazwa.getNazweKrajow());
        }
        setRynek(rynek);
        rynek.addNewWaluta(this);
    }

    public String displayListaKrajow() {
        String listaKrajow = new String();
        for (String kraj : this.listaKrajow) {
            listaKrajow = listaKrajow + " " + kraj;
        }
        return listaKrajow;
    }

    public Set<String> getListaKrajow() {
        return listaKrajow;
    }

    public void setListaKrajow(Set<String> listaKrajow) {
        this.listaKrajow = listaKrajow;
    }
}
