import java.util.*;
public class rynekAkcji extends rynek{
    public Set<akcja> getListAkcji() {
        return listAkcji;
    }

    public void setListAkcji(Set<akcja> listAkcji) {
        this.listAkcji = listAkcji;
    }

    private Set<akcja> listAkcji;
}
