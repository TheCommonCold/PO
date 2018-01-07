package portfel;

public class stackZlecen {
    private zlecenie zlecenie;
    private int ilosc;

    public stackZlecen(zlecenie zlecenie){
        this.zlecenie=zlecenie;
        ilosc=1;
    }

    public zlecenie getZlecenie() {
        return zlecenie;
    }

    public void setZlecenie(zlecenie zlecenie) {
        this.zlecenie = zlecenie;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public void incrementIlosc(){ilosc++;}

    public boolean isTheSame(zlecenie currentZlecenie){
        if(zlecenie.getChceKupic().equals(currentZlecenie.getChceKupic()) && zlecenie.getChceSprzedac().equals(currentZlecenie.getChceSprzedac())){
            return true;
        }
        return false;
    }
}
