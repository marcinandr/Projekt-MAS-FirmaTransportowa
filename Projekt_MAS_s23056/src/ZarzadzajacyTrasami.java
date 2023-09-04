import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZarzadzajacyTrasami extends PracownikFirmyUzytkownikZewnetrzny implements Serializable {
    private List<String> odbytyKursZarzadzania;

    //ekstensja
    private static List<ZarzadzajacyTrasami> ekstensjaZarzadzajacyTrasami = new ArrayList();

    //konstruktor
    public ZarzadzajacyTrasami(String imie, String nazwisko, String adres, Date dataUrodzenia, String email, Date dataZatrudnienia, String nrTel) throws Exception {
        super(imie, nazwisko, adres, dataUrodzenia, email);
        this.setRodzajObiektu(typOsoba.PracownikFirmy);
        this.setDataZatrudnienia(dataZatrudnienia);
        this.setNrTel(nrTel);
        ekstensjaZarzadzajacyTrasami.add(this);
    }

    //do wyswietlania ekestensji
    public static void pokazListe() {
        for (ZarzadzajacyTrasami zarzadzajacyTrasami : ekstensjaZarzadzajacyTrasami) {
            System.out.println(zarzadzajacyTrasami.toString());
        }
    }

   ///zapis i odczyt ekstensji
   public static void zapiszEkstensjaZarzadzajacyTrasami(ObjectOutputStream output) throws IOException {
       output.writeObject(ekstensjaZarzadzajacyTrasami);
   }
    public static void czytajEkstensjaZarzadzajacyTrasami(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaZarzadzajacyTrasami = (List<ZarzadzajacyTrasami>) input.readObject();
    }

    @Override
    public String toString() {
        return "ZarzadzajacyTrasami{" + super.toString() +
                ", odbytyKursZarzadzania=" + odbytyKursZarzadzania +
                '}';
    }

    //asocjacja ZarzadzajacyTrasami - Trasa
    private List<Trasa> asocjacjaTrasy = new ArrayList<>();

    public void usunTrasa (Trasa trasa) {
        if(!asocjacjaTrasy.contains(trasa)) {
            asocjacjaTrasy.remove(trasa);
        }
    }
    public void przypiszDoTrasa(Trasa trasa) throws Exception {
        //implementacja ogranicznia - ZarzadzajacyTrasami nie moze miec przypisanych wiecej niz 20 tras
        if (asocjacjaTrasy.size() >= 20) {
            throw new Exception("Zarzadzajacy Trasami nie moze miec przypisane wiecej niz 20 tras");
        } else if (!asocjacjaTrasy.contains(trasa)){
            asocjacjaTrasy.add(trasa);
            trasa.setZarzadzajacyTrasami(this);
        }
    }
}
