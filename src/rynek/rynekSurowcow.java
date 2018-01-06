package rynek;
import aktywa.surowiec;

import java.util.*;
public class rynekSurowcow extends rynek {

    public rynekSurowcow(){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultRynekSurowcowConstructor();
    }

    public rynekSurowcow(String nazwa){
        Random generator = new Random();
        setNazwa(nazwa);
        defaultRynekSurowcowConstructor();
    }

    public void defaultRynekSurowcowConstructor(){
        Random generator = new Random();
        setMarza(generator.nextFloat()/4);
    }

    public void addNewSurowiec(surowiec surowiec){
        listSurowcow.add(surowiec);
    }

    public Set<surowiec> getListSurowcow() {
        return listSurowcow;
    }

    public void setListSurowcow(Set<surowiec> listSurowcow) {
        this.listSurowcow = listSurowcow;
    }

    private Set<surowiec> listSurowcow=new HashSet<>();
}
