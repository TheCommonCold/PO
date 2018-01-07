package portfel;

import aktywa.CenaWaluty;
import aktywa.CenyWalut;
import aktywa.Waluta;
import rynek.RynekWalut;

import java.util.ArrayList;
import java.util.Random;

public class ZleceniaKupnaSprzedazy {
    ArrayList<Zlecenie> zleceniaKupna = new ArrayList<Zlecenie>();
    ArrayList<Zlecenie> zleceniaSprzedazy = new ArrayList<Zlecenie>();

    public ArrayList<Zlecenie> getZleceniaKupna() {
        return zleceniaKupna;
    }

    public void setZleceniaKupna(ArrayList<Zlecenie> zleceniaKupna) {
        this.zleceniaKupna = zleceniaKupna;
    }

    public ArrayList<Zlecenie> getZleceniaSprzedazy() {
        return zleceniaSprzedazy;
    }

    public void setZleceniaSprzedazy(ArrayList<Zlecenie> zleceniaSprzedazy) {
        this.zleceniaSprzedazy = zleceniaSprzedazy;
    }

    public void addZlecenieKupna(Zlecenie Zlecenie) {
        zleceniaKupna.add(Zlecenie);
    }

    public void addZlecenieSprzedazy(Zlecenie Zlecenie) {
        zleceniaSprzedazy.add(Zlecenie);
    }

    public void resetZlecenia() {
        zleceniaKupna.clear();
        zleceniaSprzedazy.clear();
    }

    public void updatePricing() {
        ArrayList<StackZlecen> zliczoneZlecenia = new ArrayList<StackZlecen>();
        ArrayList<StackZlecen> listaZmianCen = new ArrayList<StackZlecen>();
        if (zleceniaKupna.size() > 0) {
            zliczZlecenia(zliczoneZlecenia, zleceniaKupna);
            zliczoneZlecenia.add(new StackZlecen(zleceniaKupna.get(0)));
        }
        if (zleceniaSprzedazy.size() > 0) {
            zliczZlecenia(zliczoneZlecenia, zleceniaSprzedazy);
            zliczoneZlecenia.add(new StackZlecen(zleceniaSprzedazy.get(0)));
        }
        for (StackZlecen currentStack1 : zliczoneZlecenia) {
            for (StackZlecen currentStack2 : zliczoneZlecenia) {
                if (currentStack1.getZlecenie().getChceSprzedac().equals(currentStack2.getZlecenie().getChceKupic()) && currentStack1.getZlecenie().getChceKupic().equals(currentStack2.getZlecenie().getChceSprzedac())) {
                    if (currentStack1.getIlosc() > currentStack2.getIlosc()) {
                        currentStack1.setIlosc(currentStack1.getIlosc() - currentStack2.getIlosc());
                        currentStack2.setIlosc(0);
                        if (!listaZmianCen.contains(currentStack1)) listaZmianCen.add(currentStack1);
                        listaZmianCen.add(currentStack1);
                    } else {
                        currentStack2.setIlosc(currentStack2.getIlosc() - currentStack1.getIlosc());
                        currentStack1.setIlosc(0);
                        if (!listaZmianCen.contains(currentStack2)) listaZmianCen.add(currentStack2);

                    }
                }
            }
        }
        zmianaCen(listaZmianCen);
    }

    public void zmianaCen(ArrayList<StackZlecen> listaZmianCen) {
        Random generator = new Random();
        for (StackZlecen zlecenie : listaZmianCen) {
            float ilosc = 1 + (zlecenie.getIlosc() / 1000);
            if (zlecenie.getZlecenie().getChceKupic() instanceof Waluta && zlecenie.getZlecenie().getChceSprzedac() instanceof Waluta) {
                for (CenyWalut cena : ((RynekWalut) zlecenie.getZlecenie().getChceKupic().getRynek()).getListaCen()) {
                    if (cena.getWaluta().equals(zlecenie.getZlecenie().getChceKupic())) {
                        for (CenaWaluty currentWaluta : cena.getWartosc()) {
                            if (currentWaluta.getWaluta().equals(zlecenie.getZlecenie().getChceSprzedac())) {
                                currentWaluta.setCenaSprzedazy(currentWaluta.getCenaSprzedazy() / ilosc);
                                currentWaluta.setCenaKupna(currentWaluta.getCenaKupna() * ilosc);
                                break;
                            }
                        }
                    }
                    break;
                }
                for (CenyWalut cena : ((RynekWalut) zlecenie.getZlecenie().getChceSprzedac().getRynek()).getListaCen()) {
                    if (cena.getWaluta().equals(zlecenie.getZlecenie().getChceSprzedac())) {
                        for (CenaWaluty currentWaluta : cena.getWartosc()) {
                            if (currentWaluta.getWaluta().equals(zlecenie.getZlecenie().getChceKupic())) {
                                currentWaluta.setCenaSprzedazy(currentWaluta.getCenaSprzedazy() / ilosc);
                                currentWaluta.setCenaKupna(currentWaluta.getCenaKupna() * ilosc);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void zliczZlecenia(ArrayList<StackZlecen> zliczoneZlecenia, ArrayList<Zlecenie> zleceniaSprzedazy) {
        for (Zlecenie currentZlecenie : zleceniaSprzedazy) {
            boolean flag = false;
            for (StackZlecen currentStack : zliczoneZlecenia) {
                if (currentStack.isTheSame(currentZlecenie)) {
                    currentStack.incrementIlosc();
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                zliczoneZlecenia.add(new StackZlecen(currentZlecenie));
            }
        }
    }

}
