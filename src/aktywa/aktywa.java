package aktywa;
import rynek.rynek;
import javafx.beans.property.SimpleStringProperty;

public abstract class aktywa {
    private String nazwa;

    private rynek rynek;

    public rynek getRynek() {
        return rynek;
    }

    public void setRynek(rynek rynek) {
        this.rynek = rynek;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public SimpleStringProperty getNazwaProperty(){
        return new SimpleStringProperty(nazwa);
    }

    public SimpleStringProperty getRynekProperty(){
        return new SimpleStringProperty(rynek.getNazwa());
    }
}
