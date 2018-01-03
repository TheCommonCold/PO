package portfel;

import aktywa.aktywa;

import java.util.*;
public class portfel {
    private Set<stackAkcji> akcje;
    private Set<stackWalut> waluty;
    private Set<stackSurowcow> surowce;

    public portfel(){
        akcje = new HashSet<>();
        waluty = new HashSet<>();
        surowce = new HashSet<>();
    }

    public stackWalut getStackWaluty(aktywa waluta){
        for(stackWalut currentStackWalut:waluty){
            if(currentStackWalut.getWaluta().equals(waluta)){
                return currentStackWalut;
            }
        }
        return null;
    }
    public stackSurowcow getStackSurowcow(aktywa surowiec){
        for(stackSurowcow currentStackSurowcow:surowce){
            if(currentStackSurowcow.getSurowiec().equals(surowiec)){
                return currentStackSurowcow;
            }
        }
        return null;
    }

    public stackAkcji getStackAkcji(aktywa akcja){
        for(stackAkcji currentStackAkcji:akcje){
            if(currentStackAkcji.getAkcja().equals(akcja)){
                return currentStackAkcji;
            }
        }
        return null;
    }

    public void addNowaAkcja(stackAkcji akcje){
        this.akcje.add(akcje);
    }

    public void addNowySurowiec(stackSurowcow surowiec){
        this.surowce.add(surowiec);
    }

    public void addNowaWaluta(stackWalut waluta){
        this.waluty.add(waluta);
    }

    public Set<stackAkcji> getAkcje() {
        return akcje;
    }

    public void setAkcje(Set<stackAkcji> akcje) {
        this.akcje = akcje;
    }

    public Set<stackWalut> getWaluty() {
        return waluty;
    }

    public void setWaluty(Set<stackWalut> waluty) {
        this.waluty = waluty;
    }

    public Set<stackSurowcow> getSurowce() {
        return surowce;
    }

    public void setSurowce(Set<stackSurowcow> surowce) {
        this.surowce = surowce;
    }
}
