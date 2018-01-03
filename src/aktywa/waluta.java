package aktywa;

import javafx.collections.ObservableList;

import java.util.*;

public class waluta extends aktywa {

    public  waluta(){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
    }

    public  waluta(String nazwa){
        setNazwa(nazwa);
    }

    public waluta(Set<waluta> walutaData){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultWalutaConstructor(walutaData);
    }

    public waluta(Set<waluta> walutaData,String nazwa){
        setNazwa(nazwa);
        defaultWalutaConstructor(walutaData);
    }

    public void defaultWalutaConstructor(Set<waluta> walutaData){
        Random generator = new Random();
        for(int i=0;i<generator.nextInt(100);i++){
            listaKrajow.add(Integer.toString(generator.nextInt()));
        }
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
