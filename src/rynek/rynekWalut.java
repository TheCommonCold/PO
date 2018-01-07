package rynek;

import aktywa.CenaWaluty;
import aktywa.CenyWalut;
import aktywa.Waluta;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RynekWalut extends Rynek {

    private Set<Waluta> listaWalut = new HashSet<>();
    private Set<CenyWalut> listaCen = new HashSet<>();

    public RynekWalut() {
        Random generator = new Random();
        setNazwa(Integer.toString(generator.nextInt()));
        defaultRynekWalutConstructor();
    }

    public RynekWalut(String nazwa) {
        setNazwa(nazwa);
        defaultRynekWalutConstructor();
    }

    public void defaultRynekWalutConstructor() {
        setTypRynku("Waluty");
        Random generator = new Random();
        setMarza(generator.nextFloat() / 4);
    }

    public CenyWalut getCenaWaluty(Waluta Waluta) {
        for (CenyWalut currentCenyWalut : listaCen) {
            if (currentCenyWalut.getWaluta().equals(Waluta)) {
                return currentCenyWalut;
            }
        }
        return null;
    }

    public void addNewWaluta(Waluta Waluta) {
        Random generator = new Random();
        listaWalut.add(Waluta);
        if (getListaCen().size() != 0) {
            CenyWalut addedWaluta = new CenyWalut(Waluta);
            for (CenyWalut currentCenyWalut : listaCen) {
                float a = generator.nextInt(10) + generator.nextFloat();
                if (generator.nextFloat() < 0.5) {
                    currentCenyWalut.addWaluta(addedWaluta.getWaluta(), a, 1 / a);
                    addedWaluta.addWaluta(currentCenyWalut.getWaluta(), 1 / a, a);
                } else {
                    addedWaluta.addWaluta(currentCenyWalut.getWaluta(), a, 1 / a);
                    currentCenyWalut.addWaluta(addedWaluta.getWaluta(), 1 / a, a);
                }
            }
            listaCen.add(addedWaluta);
        } else listaCen.add(new CenyWalut(Waluta));
    }

    public Set<Waluta> getListaWalut() {
        return listaWalut;
    }

    public void setListaWalut(Set<Waluta> listaWalut) {
        this.listaWalut = listaWalut;
    }

    public Set<CenyWalut> getListaCen() {
        return listaCen;
    }

    public void setListaCen(Set<CenyWalut> listaCen) {
        this.listaCen = listaCen;
    }


    public void zapiszCeny() {
        for (CenyWalut currentCenywalut : listaCen) {
            for (CenaWaluty currentCenaWaluty : currentCenywalut.getWartosc()) {
                currentCenaWaluty.zapiszWartoscKupna();
            }
        }
    }
}
