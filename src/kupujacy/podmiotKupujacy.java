package kupujacy;
import aktywa.surowiec;
import aktywa.akcje;
import aktywa.aktywa;
import javafx.collections.ObservableList;
import rynek.rynek;
import rynek.rynekWalut;
import rynek.rynekAkcji;
import portfel.portfel;
import portfel.stackWalut;
import portfel.stackSurowcow;
import portfel.stackAkcji;
import portfel.zlecenie;
import portfel.zleceniaKupnaSprzedazy;
import aktywa.waluta;
import rynek.rynekSurowcow;

import java.util.List;
import java.util.Random;

public class podmiotKupujacy extends Thread {
    private String imie;
    private String nazwisko;
    private portfel assets;
    private float agresja;


    public float getAgresja() {
        return agresja;
    }

    public void setAgresja(float agresja) {
        this.agresja = agresja;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public portfel getAssets() {
        return assets;
    }

    public void setAssets(portfel assets) {
        this.assets = assets;
    }

    public void zlecKupno(zleceniaKupnaSprzedazy zleceniaData,List<rynekWalut> rynkiWalut, List<rynekSurowcow>rynkiSurowcow,List<rynekAkcji> rynkiAkcjiData){
        Random generator = new Random();
        if(assets.getWaluty().size()>0){
            for(rynekWalut currentRynek:rynkiWalut){
                for(waluta currentWaluta:currentRynek.getListaWalut()){
                        for(stackWalut currentStackWalut:assets.getWaluty()){
                            if(agresja>generator.nextFloat() && currentStackWalut.getIlosc()>1 && !currentWaluta.equals(currentStackWalut.getWaluta())){
                                zleceniaData.addZlecenieKupna(new zlecenie(this,currentWaluta,currentStackWalut.getWaluta()));
                                break;
                            }
                    }
                }
            }
        }
        for(rynekSurowcow currentRynek:rynkiSurowcow){
            for(surowiec currentSurowiec:currentRynek.getListSurowcow()){
                for(stackWalut currentStackWalut:assets.getWaluty()){
                    if(currentSurowiec.getWaluta().equals(currentStackWalut.getWaluta())){
                        if(agresja>generator.nextFloat() && currentStackWalut.getIlosc()>currentSurowiec.getWartosc()){
                            zleceniaData.addZlecenieKupna(new zlecenie(this,currentSurowiec,currentSurowiec.getWaluta()));
                        }
                    }
                }
            }
        }
        for(rynekAkcji currentRynekAkcji:rynkiAkcjiData){
            for(akcje currentAkcja:currentRynekAkcji.getListAkcji()){
                for(stackWalut currentStackWalut:assets.getWaluty()){
                    if(currentRynekAkcji.getWalutaRynku().equals(currentStackWalut.getWaluta())){
                        if(agresja>generator.nextFloat() && currentStackWalut.getIlosc()>currentAkcja.getSpolka().getWartosc()){
                            zleceniaData.addZlecenieKupna(new zlecenie (this,currentAkcja,currentRynekAkcji.getWalutaRynku()));
                        }
                    }
                }
            }
        }

    }

    public void zlecSprzedaz(zleceniaKupnaSprzedazy zleceniaData,List<rynekWalut> rynkiWalut, List<rynekSurowcow>rynkiSurowcow,List<rynekAkcji> rynkiAkcji){
        Random generator = new Random();
        for(rynekWalut currentRynek:rynkiWalut){
                for(stackWalut currentStackWalut:assets.getWaluty()){
                        for(waluta currentWaluta:currentRynek.getListaWalut()){
                            if(agresja>generator.nextFloat() && currentStackWalut.getIlosc()>1 && !currentStackWalut.getWaluta().equals(currentWaluta)){
                                zleceniaData.addZlecenieSprzedazy(new zlecenie(this,currentWaluta,currentStackWalut.getWaluta()));
                            }
                        }
                }
        }
        for(rynekSurowcow currentRynek:rynkiSurowcow){
                for(stackSurowcow currentStackSurowcow:assets.getSurowce()){
                    if(currentStackSurowcow.getIlosc()>0){
                        if(agresja>generator.nextFloat()){
                            zleceniaData.addZlecenieSprzedazy(new zlecenie(this,currentStackSurowcow.getSurowiec().getWaluta(),currentStackSurowcow.getSurowiec()));
                        }
                    }
                }
        }
        for(rynekAkcji currentRynekAkcji:rynkiAkcji){
            for(akcje currentAkcja:currentRynekAkcji.getListAkcji()){
                for(stackAkcji currentStackAkcji:assets.getAkcje()){
                    if(currentStackAkcji.getIlosc()>0 && currentAkcja.equals(currentStackAkcji.getAkcja())){
                        if(agresja>generator.nextFloat()){
                            zleceniaData.addZlecenieSprzedazy((new zlecenie(this,currentRynekAkcji.getWalutaRynku(),currentStackAkcji.getAkcja())));
                        }
                    }
                }
            }
        }
    }
}
