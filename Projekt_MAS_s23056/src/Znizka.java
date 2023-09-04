import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Znizka implements Serializable{
    private String komuPrzysluguje;
    private float ileWynosi;
    private String info;

    //ekstensja
    private static List<Znizka> ekstensjaZnizka = new ArrayList();

    //wyswietlanie ekstensji
    public static void pokazZnizki() {
        for (Znizka znizka : ekstensjaZnizka) {
            System.out.println(znizka.toString());
        }
    }

    //konstruktor
    public Znizka(String komuPrzysluguje, int ileWynosi, String info) {
        this.komuPrzysluguje = komuPrzysluguje;
        this.ileWynosi = ileWynosi;
        this.info = info;
        ekstensjaZnizka.add(this);
    }

    //zapis i odczyt ekstensji
    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaZnizka(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaZnizka);
    }
    public static void czytajEkstensjaZnizka(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaZnizka = (List<Znizka>) input.readObject();
    }

    @Override
    public String toString() {
        return komuPrzysluguje + " - " + ileWynosi + "%";
    }

    //asocjacja Bilet - Znizka
    private List<Bilet> asocjacjaBilet = new ArrayList<>();
    public void przypiszDoBilet(Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.add(bilet);

            bilet.setZnizka(this);
        }
    }
    public void usunBilet (Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.remove(bilet);
        }
    }

    //implementacja przypadku uzycia

    //metoda do zwrocenia obowiazujacych znizek

    public static List<Znizka> zwrocObowiazujaceZnizki() {
        List<Znizka> obowiazujaceZnizki = new ArrayList<>();
        for (Znizka znizka : ekstensjaZnizka) {
            obowiazujaceZnizki.add(znizka);
        }

        return obowiazujaceZnizki;
    }

    public String getKomuPrzysluguje() {
        return komuPrzysluguje;
    }

    public float getIleWynosi() {
        return ileWynosi;
    }

    public String getInfo() {
        return info;
    }
}
