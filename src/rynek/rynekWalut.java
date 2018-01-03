package rynek;

import aktywa.cenaWaluty;
import aktywa.cenyWalut;
import aktywa.waluta;
import javafx.collections.ObservableList;

import java.util.*;
public class rynekWalut extends rynek {

    public rynekWalut(ObservableList<waluta> walutaData){
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultRynekWalutConstructor(walutaData);
    }

    public rynekWalut(ObservableList<waluta> walutaData, String nazwa){
        setNazwa(nazwa);
        defaultRynekWalutConstructor(walutaData);
    }

    public void defaultRynekWalutConstructor(ObservableList<waluta> walutaData){
        Random generator = new Random();
        setMarza(generator.nextFloat()/4);
        if(walutaData.size()!=0) {
            for (waluta currentWaluta : walutaData) {
                addNewWaluta(currentWaluta);
            }
        }
    }

    public void updateRynekWalut(ObservableList<waluta> walutaData){
        if(this.getListaWalut().size()<walutaData.size()){
            for(waluta currentWaluta:walutaData){
                if(!this.getListaWalut().contains(currentWaluta)){
                    this.addNewWaluta(currentWaluta.getNazwa());
                }
            }
        }
    }

    public void addNewWaluta(waluta waluta){
        Random generator = new Random();
        listaWalut.add(waluta);
        if(getListaCen().size()!=0){
            cenyWalut addedWaluta = new cenyWalut(waluta);
            for(cenyWalut currentCenyWalut:listaCen){
                float a=generator.nextInt(10)+generator.nextFloat();
                if(generator.nextFloat()<0.5) {
                    currentCenyWalut.addWaluta(addedWaluta.getWaluta(),a,1/a);
                    addedWaluta.addWaluta(currentCenyWalut.getWaluta(),1/a,a);
                }else{
                    addedWaluta.addWaluta(currentCenyWalut.getWaluta(),a,1/a);
                    currentCenyWalut.addWaluta(addedWaluta.getWaluta(),1/a,a);
                }
            }
            listaCen.add(addedWaluta);
        }
        else listaCen.add(new cenyWalut(waluta));
    }

    public void addNewWaluta(String nazwa){
        listaWalut.add(new waluta(getListaWalut(),nazwa));
    }

    public Set<waluta> getListaWalut() {
        return listaWalut;
    }

    public void setListaWalut(Set<waluta> listaWalut) {
        this.listaWalut = listaWalut;
    }

    private Set<waluta> listaWalut= new HashSet<>();

    private Set<cenyWalut> listaCen= new HashSet<>();

    public Set<cenyWalut> getListaCen() {
        return listaCen;
    }

    public void setListaCen(Set<cenyWalut> listaCen) {
        this.listaCen = listaCen;
    }
}
