package kupujacy;

public class inwestor extends podmiotKupujacy {
    private int pesel;
    private int budzetInwestycyjny;

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public int getBudzetInwestycyjny() {
        return budzetInwestycyjny;
    }

    public void setBudzetInwestycyjny(int budzetInwestycyjny) {
        this.budzetInwestycyjny = budzetInwestycyjny;
    }
}
