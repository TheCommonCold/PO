package aktywa;

public class surowiec extends aktywa {
    private String jednostkaHandlowa;
    private waluta waluta;
    private int wartosc;
    private int wartoscMinimalna;
    private int wartoscMaksymalna;

    public String getJednostkaHandlowa() {
        return jednostkaHandlowa;
    }

    public void setJednostkaHandlowa(String jednostkaHandlowa) {
        this.jednostkaHandlowa = jednostkaHandlowa;
    }

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getWartoscMinimalna() {
        return wartoscMinimalna;
    }

    public void setWartoscMinimalna(int wartoscMinimalna) {
        this.wartoscMinimalna = wartoscMinimalna;
    }

    public int getWartoscMaksymalna() {
        return wartoscMaksymalna;
    }

    public void setWartoscMaksymalna(int wartoscMaksymalna) {
        this.wartoscMaksymalna = wartoscMaksymalna;
    }
}
