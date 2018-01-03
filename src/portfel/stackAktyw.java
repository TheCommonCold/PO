package portfel;

public class stackAktyw {
    private float ilosc;

    public float getIlosc() {
        return ilosc;
    }

    public void setIlosc(float ilosc) {
        this.ilosc = ilosc;
    }

    public void subtractIlosc(float liczba){
        this.ilosc-=liczba;
    }

    public void addIlosc(float liczba){
        this.ilosc+=liczba;
    }
}
