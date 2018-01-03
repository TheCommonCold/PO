package rynek;
import aktywa.surowiec;
import aktywa.waluta;
import javafx.collections.ObservableList;

import java.util.*;
public class rynekSurowcow extends rynek {

    public rynekSurowcow(ObservableList<surowiec> surowceData){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultRynekSurowcowConstructor(surowceData);
    }

    public rynekSurowcow(ObservableList<surowiec> surowceData,String nazwa){
        Random generator = new Random();
        defaultRynekSurowcowConstructor(surowceData);
    }

    public void defaultRynekSurowcowConstructor(ObservableList<surowiec> surowceData){
        Random generator = new Random();
        setMarza(generator.nextFloat()/4);
        for(surowiec currentSurowiec:surowceData){
            addNewSurowiec(currentSurowiec);
        }
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
