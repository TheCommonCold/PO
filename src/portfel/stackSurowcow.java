package portfel;

import aktywa.Surowiec;

public class StackSurowcow extends StackAktyw {
    private Surowiec Surowiec;

    public StackSurowcow(Surowiec Surowiec, float ilosc) {
        this.Surowiec = Surowiec;
        setIlosc(ilosc);
    }

    public Surowiec getSurowiec() {
        return Surowiec;
    }

    public void setSurowiec(Surowiec Surowiec) {
        this.Surowiec = Surowiec;
    }
}
