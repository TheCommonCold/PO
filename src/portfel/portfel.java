package portfel;

import java.util.*;
public class portfel {
    private Set<stackAkcji> akcje;
    private Set<stackAkcji> waluty;
    private Set<stackAkcji> surowce;

    public Set<stackAkcji> getAkcje() {
        return akcje;
    }

    public void setAkcje(Set<stackAkcji> akcje) {
        this.akcje = akcje;
    }

    public Set<stackAkcji> getWaluty() {
        return waluty;
    }

    public void setWaluty(Set<stackAkcji> waluty) {
        this.waluty = waluty;
    }

    public Set<stackAkcji> getSurowce() {
        return surowce;
    }

    public void setSurowce(Set<stackAkcji> surowce) {
        this.surowce = surowce;
    }
}
