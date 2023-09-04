import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mechanik extends PracownikFirmyUzytkownikZewnetrzny {
    private boolean czyNaprawiaKlimatyzacje;
    private boolean czyNaprawiaWiFi;

    //ekstensja
    private static List<Mechanik> ekstensjaMechanik = new ArrayList();

    //konstruktor
    public Mechanik(String imie, String nazwisko, String adres, Date dataUrodzenia, String email, Date dataZatrudnienia, String nrTel, boolean czyNaprawiaKlimatyzacje, boolean czyNaprawiaWiFi) throws Exception {
        super(imie, nazwisko, adres, dataUrodzenia, email);
        this.setRodzajObiektu(typOsoba.PracownikFirmy);
        this.setDataZatrudnienia(dataZatrudnienia);
        this.setNrTel(nrTel);
        this.czyNaprawiaKlimatyzacje = czyNaprawiaKlimatyzacje;
        this.czyNaprawiaWiFi = czyNaprawiaWiFi;
        ekstensjaMechanik.add(this);
    }

    //do wyswietlenia ekstensji
    public static void pokazListe() {
        for (Mechanik mechanik : ekstensjaMechanik) {
            System.out.println(mechanik.toString());
        }
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaMechanik(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaMechanik);
    }
    public static void czytajEkstensjaMechanik(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaMechanik = (List<Mechanik>) input.readObject();
    }

    @Override
    public String toString() {
        return "Mechanik{" + super.toString() +
                ", czyNaprawiaKlimatyzacje=" + czyNaprawiaKlimatyzacje +
                ", czyNaprawiaWiFi=" + czyNaprawiaWiFi +
                '}';
    }

    //asocjacja ZlecenieNaprawy - Mechanik - po stronie Mechanik zmienilem licznosc na 1
    private List<ZlecenieNaprawy> asocjacjaZlecenieNaprawy = new ArrayList<>();

    public void usunZlecenieNaprawy (ZlecenieNaprawy zlecenieNaprawy) {
        if(!asocjacjaZlecenieNaprawy.contains(zlecenieNaprawy)) {
            asocjacjaZlecenieNaprawy.remove(zlecenieNaprawy);
        }
    }
    public void przypiszDoZlecenieNaprawy(ZlecenieNaprawy zlecenieNaprawy) {
        if(!asocjacjaZlecenieNaprawy.contains(zlecenieNaprawy)) {
            asocjacjaZlecenieNaprawy.add(zlecenieNaprawy);

            zlecenieNaprawy.setMechanikDokonujacyNaprawy(this);
        }
    }

}
