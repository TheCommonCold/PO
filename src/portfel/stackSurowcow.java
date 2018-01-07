package portfel;

import aktywa.surowiec;

import java.util.Random;

public class stackSurowcow extends stackAktyw {
    private aktywa.surowiec surowiec;

    public stackSurowcow(surowiec surowiec,float ilosc){
        this.surowiec=surowiec;
        setIlosc(ilosc);
    }

    public surowiec getSurowiec() {
        return surowiec;
    }

    public void setSurowiec(surowiec surowiec) {
        this.surowiec = surowiec;
    }
}
