import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pasazer extends PracownikFirmyUzytkownikZewnetrzny implements Serializable {
    private List<Znizka> przyslugujaceZnizki;

    //ekstensja
    private static List<Pasazer> ekstensjaPasazer = new ArrayList();

    //konstruktor (implementacja dziedziczenia dynamicznego)
    public Pasazer(PracownikFirmyUzytkownikZewnetrzny uzytkownikDokonujacyZakupu) {
        super(uzytkownikDokonujacyZakupu.getImie(), uzytkownikDokonujacyZakupu.getNazwisko(), uzytkownikDokonujacyZakupu.getAdres(), uzytkownikDokonujacyZakupu.getDataUrodzenia(), uzytkownikDokonujacyZakupu.getEmail());
        ekstensjaPasazer.add(this);
    }

    //do wyswietlenia ekstensji
    public static void pokazListe() {
        for(Pasazer pasazer : ekstensjaPasazer) {
            System.out.println(pasazer.toString());
        }
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaPasazer(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaPasazer);
    }
    public static void czytajEkstensjaPasazer(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaPasazer = (List<Pasazer>) input.readObject();
    }

    @Override
    public String toString() {
        return "Pasazer{" + super.toString() +
                "przyslugujaceZnizki=" + przyslugujaceZnizki +
                '}';
    }

    //asocjacja Pasazer - Bilet
    private List<Bilet> asocjacjaBilet = new ArrayList<>();

    public void usunBilet (Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.remove(bilet);
        }
    }
    public void przypiszDoBilet(Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.add(bilet);

            bilet.setPasazer(this);
        }
    }
}
