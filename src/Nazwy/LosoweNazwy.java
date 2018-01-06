package Nazwy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LosoweNazwy {


    Random generator = new Random();
    private ArrayList<String> listaNazwRynkowWalut = new ArrayList<String>();
    private ArrayList<String> listaNazwRynkowSurowcow = new ArrayList<String>();
    private ArrayList<String> listaNazwRynkowAkcji = new ArrayList<String>();
    private ArrayList<String> listaNazwWalut = new ArrayList<String>();
    private ArrayList<String> listaNazwSurowcow = new ArrayList<String>();
    private ArrayList<String> listaNazwSpolek = new ArrayList<String>();
    private ArrayList<String> listaImionInwestorow = new ArrayList<String>();
    private ArrayList<String> listaNazwiskInwestorow = new ArrayList<String>();


    public LosoweNazwy(){
        wczytajPlik(listaNazwRynkowWalut,"src/Nazwy/NazwyRynkowWalut");
        wczytajPlik(listaNazwRynkowSurowcow,"src/Nazwy/NazwyRynkowSurowcow");
        wczytajPlik(listaNazwRynkowAkcji,"src/Nazwy/NazwyRynkowAkcji");
        wczytajPlik(listaNazwWalut,"src/Nazwy/NazwyWalut");
        wczytajPlik(listaNazwSurowcow,"src/Nazwy/NazwySurowcow");
        wczytajPlik(listaNazwSpolek,"src/Nazwy/NazwySpolek");
        wczytajPlik(listaImionInwestorow ,"src/Nazwy/ImionaInwestorow");
        wczytajPlik(listaNazwiskInwestorow,"src/Nazwy/NazwiskaInwestorow");
    }

    private void wczytajPlik(ArrayList<String> lista,String lokalizacjaPliku){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(lokalizacjaPliku));
            String line;
            while((line=reader.readLine())!=null){
                lista.add(line);
            }
        }
        catch(IOException e){
            System.out.print("Ni mo pliku ");System.out.print(lokalizacjaPliku);System.out.print(" panie \n");
        }
    }

    public String getNazwa(ArrayList<String> lista, boolean usun){
        String temp;
        int ktoraNazwa=generator.nextInt(lista.size());
        int i;
        for(i=0; i<lista.size();i++){
            if(i==ktoraNazwa){
                temp=lista.get(i);
                if(usun==true) lista.remove(i);
                return temp;
            }
        }
        return null;
    }

    public String getNazweRynkuWalut(){
        return getNazwa(listaNazwRynkowWalut,true);
    }

    public String getNazweRynkuSurowcow(){
        return getNazwa(listaNazwRynkowSurowcow,true);
    }

    public String getNazweRynkuAkcji(){
        return getNazwa(listaNazwRynkowAkcji,true);
    }

    public String getNazweWaluty(){
        return getNazwa(listaNazwWalut,true);
    }

    public String getNazweSurowca(){
        return getNazwa(listaNazwSurowcow,true);
    }

    public String getNazweSpolki(){
        return getNazwa(listaNazwSpolek,true);
    }

    public String getImieInwestora(){
        return getNazwa(listaImionInwestorow,false);
    }

    public String getNazwiskoInwestora() {
        return getNazwa(listaNazwiskInwestorow,false);
    }
}
