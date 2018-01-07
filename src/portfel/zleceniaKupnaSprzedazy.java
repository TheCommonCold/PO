package portfel;

import aktywa.cenyWalut;
import aktywa.cenaWaluty;
import aktywa.waluta;
import rynek.rynekWalut;

import java.util.ArrayList;
import java.util.Random;

public class zleceniaKupnaSprzedazy {
    ArrayList<zlecenie> zleceniaKupna = new ArrayList<zlecenie>();
    ArrayList<zlecenie> zleceniaSprzedazy= new ArrayList<zlecenie>();

    public ArrayList<zlecenie> getZleceniaKupna() {
        return zleceniaKupna;
    }

    public void setZleceniaKupna(ArrayList<zlecenie> zleceniaKupna) {
        this.zleceniaKupna = zleceniaKupna;
    }

    public ArrayList<zlecenie> getZleceniaSprzedazy() {
        return zleceniaSprzedazy;
    }

    public void setZleceniaSprzedazy(ArrayList<zlecenie> zleceniaSprzedazy) {
        this.zleceniaSprzedazy = zleceniaSprzedazy;
    }

    public void addZlecenieKupna(zlecenie zlecenie){
        zleceniaKupna.add(zlecenie);
    }

    public void addZlecenieSprzedazy(zlecenie zlecenie){
        zleceniaSprzedazy.add(zlecenie);
    }

    public void resetZlecenia(){
        zleceniaKupna.clear();
        zleceniaSprzedazy.clear();
    }

    public void updatePricing(){
        ArrayList<stackZlecen> zliczoneZlecenia=new ArrayList<stackZlecen>();
        ArrayList<stackZlecen> listaZmianCen=new ArrayList<stackZlecen>();
        if(zleceniaKupna.size()>0){
            zliczZlecenia(zliczoneZlecenia,zleceniaKupna);
            zliczoneZlecenia.add(new stackZlecen(zleceniaKupna.get(0)));
        }
        if(zleceniaSprzedazy.size()>0){
            zliczZlecenia(zliczoneZlecenia,zleceniaSprzedazy);
            zliczoneZlecenia.add(new stackZlecen(zleceniaSprzedazy.get(0)));
        }
        for(stackZlecen currentStack1:zliczoneZlecenia){
            for(stackZlecen currentStack2:zliczoneZlecenia){
                if(currentStack1.getZlecenie().getChceSprzedac().equals(currentStack2.getZlecenie().getChceKupic()) &&currentStack1.getZlecenie().getChceKupic().equals(currentStack2.getZlecenie().getChceSprzedac())){
                    if(currentStack1.getIlosc()>currentStack2.getIlosc()){
                        currentStack1.setIlosc(currentStack1.getIlosc()-currentStack2.getIlosc());
                        currentStack2.setIlosc(0);
                        if(!listaZmianCen.contains(currentStack1))listaZmianCen.add(currentStack1);
                        listaZmianCen.add(currentStack1);
                    }else{
                        currentStack2.setIlosc(currentStack2.getIlosc()-currentStack1.getIlosc());
                        currentStack1.setIlosc(0);
                        if(!listaZmianCen.contains(currentStack2))listaZmianCen.add(currentStack2);

                    }
                }
            }
        }
        zmianaCen(listaZmianCen);
    }

    public void zmianaCen(ArrayList<stackZlecen> listaZmianCen){
        Random generator = new Random();
        for(stackZlecen zlecenie:listaZmianCen){
            float ilosc=1 + (zlecenie.getIlosc()/1000);
            if(zlecenie.getZlecenie().getChceKupic() instanceof waluta && zlecenie.getZlecenie().getChceSprzedac() instanceof waluta){
                for(cenyWalut cena: ((rynekWalut)zlecenie.getZlecenie().getChceKupic().getRynek()).getListaCen()){
                    if(cena.getWaluta().equals(zlecenie.getZlecenie().getChceKupic())){
                        for(cenaWaluty currentWaluta:cena.getWartosc()){
                            if(currentWaluta.getWaluta().equals(zlecenie.getZlecenie().getChceSprzedac())){
                                currentWaluta.setCenaSprzedazy(currentWaluta.getCenaSprzedazy()/ilosc);
                                currentWaluta.setCenaKupna(currentWaluta.getCenaKupna()*ilosc);
                                break;
                            }
                        }
                    }
                    break;
                }
                for(cenyWalut cena: ((rynekWalut)zlecenie.getZlecenie().getChceSprzedac().getRynek()).getListaCen()){
                    if(cena.getWaluta().equals(zlecenie.getZlecenie().getChceSprzedac())){
                        for(cenaWaluty currentWaluta:cena.getWartosc()){
                            if(currentWaluta.getWaluta().equals(zlecenie.getZlecenie().getChceKupic())){
                                currentWaluta.setCenaSprzedazy(currentWaluta.getCenaSprzedazy()/ilosc);
                                currentWaluta.setCenaKupna(currentWaluta.getCenaKupna()*ilosc);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void zliczZlecenia(ArrayList<stackZlecen> zliczoneZlecenia,ArrayList<zlecenie> zleceniaSprzedazy){
        for(zlecenie currentZlecenie:zleceniaSprzedazy) {
            boolean flag=false;
            for(stackZlecen currentStack:zliczoneZlecenia){
                if(currentStack.isTheSame(currentZlecenie)) {
                    currentStack.incrementIlosc();
                    flag = true;
                    break;
                }
            }
            if(flag==true){
                zliczoneZlecenia.add(new stackZlecen(currentZlecenie));
            }
        }
    }

}
