package portfel;

public class StackZlecen {
    private Zlecenie Zlecenie;
    private int ilosc;

    public StackZlecen(Zlecenie Zlecenie) {
        this.Zlecenie = Zlecenie;
        ilosc = 1;
    }

    public Zlecenie getZlecenie() {
        return Zlecenie;
    }

    public void setZlecenie(Zlecenie Zlecenie) {
        this.Zlecenie = Zlecenie;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public void incrementIlosc() {
        ilosc++;
    }

    public boolean isTheSame(Zlecenie currentZlecenie) {
        if (Zlecenie.getChceKupic().equals(currentZlecenie.getChceKupic()) && Zlecenie.getChceSprzedac().equals(currentZlecenie.getChceSprzedac())) {
            return true;
        }
        return false;
    }
}
