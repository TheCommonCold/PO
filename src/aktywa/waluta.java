package aktywa;

import javafx.collections.ObservableList;
import rynek.rynekWalut;

import java.util.*;

public class waluta extends aktywa {

    public waluta(rynekWalut rynek){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultWalutaConstructor(rynek);
    }

    public waluta(rynekWalut rynek,String nazwa){
        setNazwa(nazwa);
        defaultWalutaConstructor(rynek);
    }

    public void defaultWalutaConstructor(rynekWalut rynek){
        Random generator = new Random();
        for(int i=0;i<generator.nextInt(100);i++){
            listaKrajow.add(Integer.toString(generator.nextInt()));
        }
        setRynek(rynek);
        rynek.addNewWaluta(this);
    }


    public String displayListaKrajow(){
        String listaKrajow= new String();
        for(String kraj:this.listaKrajow){
            listaKrajow=listaKrajow + " " +kraj;
        }
        return listaKrajow;
    }

    public Set<String> getListaKrajow() {
        return listaKrajow;
    }

    public void setListaKrajow(Set<String> listaKrajow) {
        this.listaKrajow = listaKrajow;
    }

    private Set<String> listaKrajow = new HashSet<>();
}
