package portfel;

import aktywa.akcje;

public class stackAkcji extends stackAktyw {

    public stackAkcji(akcje akcja , int ilosc){
        this.akcja=akcja;
        setIlosc(ilosc);
    }

    private akcje akcja;

    public akcje getAkcja() {
        return akcja;
    }

    public void setAkcja(akcje akcja) {
        this.akcja = akcja;
    }
}
