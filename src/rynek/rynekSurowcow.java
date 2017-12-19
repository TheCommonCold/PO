package rynek;
import aktywa.surowiec;

import java.util.*;
public class rynekSurowcow extends rynek {
    public Set<surowiec> getListSurowcow() {
        return listSurowcow;
    }

    public void setListSurowcow(Set<surowiec> listSurowcow) {
        this.listSurowcow = listSurowcow;
    }

    private Set<surowiec> listSurowcow;
}
