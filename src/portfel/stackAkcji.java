package portfel;

import aktywa.Akcje;

public class StackAkcji extends StackAktyw {

    private Akcje akcja;

    public StackAkcji(Akcje akcja, int ilosc) {
        this.akcja = akcja;
        setIlosc(ilosc);
    }

    public Akcje getAkcja() {
        return akcja;
    }

    public void setAkcja(Akcje akcja) {
        this.akcja = akcja;
    }
}
