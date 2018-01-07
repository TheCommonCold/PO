package portfel;
import aktywa.jednostkaUczestnictwa;

public class stackJednostekUczestnictwa extends stackAktyw {
    private jednostkaUczestnictwa jednostkaUczestnictwa;

    public stackJednostekUczestnictwa(jednostkaUczestnictwa jednostkaUczestnictwa, float ilosc){
        this.jednostkaUczestnictwa=jednostkaUczestnictwa;
        setIlosc(ilosc);
    }

    public aktywa.jednostkaUczestnictwa getJednostkaUczestnictwa() {
        return jednostkaUczestnictwa;
    }

    public void setJednostkaUczestnictwa(aktywa.jednostkaUczestnictwa jednostkaUczestnictwa) {
        this.jednostkaUczestnictwa = jednostkaUczestnictwa;
    }
}
