package rynek;

import aktywa.Surowiec;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RynekSurowcow extends Rynek {

    private Set<Surowiec> listSurowcow = new HashSet<>();

    public RynekSurowcow() {
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultRynekSurowcowConstructor();
    }

    public RynekSurowcow(String nazwa) {
        Random generator = new Random();
        setNazwa(nazwa);
        defaultRynekSurowcowConstructor();
    }

    public void defaultRynekSurowcowConstructor() {
        setTypRynku("Surowce");
        Random generator = new Random();
        setMarza(generator.nextFloat() / 4);
    }

    public void addNewSurowiec(Surowiec Surowiec) {
        listSurowcow.add(Surowiec);
    }

    public Set<Surowiec> getListSurowcow() {
        return listSurowcow;
    }

    public void setListSurowcow(Set<Surowiec> listSurowcow) {
        this.listSurowcow = listSurowcow;
    }
}
