package portfel;

import java.util.ArrayList;
import java.util.Set;

public class zleceniaKupnaSprzedazy {
    ArrayList<zlecenie> zleceniaKupna = new ArrayList<zlecenie>();
    ArrayList<zlecenie> zleceniaSprzedazy= new ArrayList<zlecenie>();

    public ArrayList<zlecenie> getZleceniaKupna() {
        return zleceniaKupna;
    }

    public void setZleceniaKupna(ArrayList<zlecenie> zleceniaKupna) {
        this.zleceniaKupna = zleceniaKupna;
    }

    public ArrayList<zlecenie> getZleceniaSprzedazy() {
        return zleceniaSprzedazy;
    }

    public void setZleceniaSprzedazy(ArrayList<zlecenie> zleceniaSprzedazy) {
        this.zleceniaSprzedazy = zleceniaSprzedazy;
    }

    public void addZlecenieKupna(zlecenie zlecenie){
        zleceniaKupna.add(zlecenie);
    }

    public void addZlecenieSprzedazy(zlecenie zlecenie){
        zleceniaSprzedazy.add(zlecenie);
    }

    public void resetZlecenia(){
        zleceniaKupna.clear();
        zleceniaSprzedazy.clear();
    }
}
