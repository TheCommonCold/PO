package aktywa;

public class cenaWaluty {
    private waluta waluta;
    private double cenaKupna;
    private double cenaSprzedazy;

    public waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(waluta waluta) {
        this.waluta = waluta;
    }

    public double getCenaKupna() {
        return cenaKupna;
    }

    public void setCenaKupna(double cenaKupna) {
        this.cenaKupna = cenaKupna;
    }

    public double getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public void setCenaSprzedazy(double cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }
}
