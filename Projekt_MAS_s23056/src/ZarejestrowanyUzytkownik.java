import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZarejestrowanyUzytkownik extends PracownikFirmyUzytkownikZewnetrzny implements Serializable {

    //ekstensja
    private static List<ZarejestrowanyUzytkownik> ekstensjaZarejestrowanyUzytkownik = new ArrayList();

    public ZarejestrowanyUzytkownik(String imie, String nazwisko, String adres, Date dataUrodzenia, String email) {
        super(imie, nazwisko, adres, dataUrodzenia, email);
        this.setRodzajObiektu(typOsoba.UzytkowinkZewnetrzny);
        ekstensjaZarejestrowanyUzytkownik.add(this);
    }

    //do wyswietlenia ekstensji
    public static void pokazListe() {
        for (ZarejestrowanyUzytkownik zarejestrowanyUzytkownik : ekstensjaZarejestrowanyUzytkownik) {
            System.out.println(zarejestrowanyUzytkownik.toString());
        }
    }

    //zwrocenie ekstensji
    public static List<ZarejestrowanyUzytkownik> getekstensjaZarejestrowanyUzytkownik() {
        return ekstensjaZarejestrowanyUzytkownik;
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaZarejestrowanyUzytkownik(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaZarejestrowanyUzytkownik);
    }
    public static void czytajEkstensjaZarejestrowanyUzytkownik(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaZarejestrowanyUzytkownik = (List<ZarejestrowanyUzytkownik>) input.readObject();
    }

    @Override
    public String toString() {
        return "ZarejestrowanyUzytkownik{" + super.toString() +
                '}';
    }

    //getter na potrzeby konstruktora klasy Pasazer

    //asocjacja ZarejestrowanyUzytkownik - Bilet - po stronie ZarejestrowanyUzytkownik zmienilem licznosc na 1, bilet moze zostac zakupiony tylko przez 1 ZarejetrowanegoUzytkownika

    private List<Bilet> asocjacjaBilet = new ArrayList<>();

    public void usunBilet (Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.remove(bilet);
        }
    }
    public void przypiszDoBilet(Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.add(bilet);

            bilet.setZarejestrowanyUzytkownik(this);
        }
    }
}
