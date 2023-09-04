import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Osoba implements Serializable {
    private String imie;
    private String nazwisko;
    private String adres;
    private Date dataUrodzenia;
    private String email; //dodalem na pole na potrzeby dziedziczenia dynamicznego w Pasazer

    //ekstensja
    private static List<Osoba> ekstensjaOsoba = new ArrayList();

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaOsoba(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaOsoba);
    }
    public static void czytajEkstensjaOsoba(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaOsoba = (List<Osoba>) input.readObject();
    }
    public Osoba(String imie, String nazwisko, String adres, Date dataUrodzenia, String email) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.dataUrodzenia = dataUrodzenia;
        this.email = email;
        ekstensjaOsoba.add(this);
    }

    @Override
    public String toString() {
        return  "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", adres='" + adres + '\'' +
                ", dataUrodzenia=" + dataUrodzenia + '\'' +
                ", email=" + email;
    }

    //gettery na potrzeby dostepu w konstruktorze klasy Pasazer:

    public String getImie() {
        return imie;
    }
    public String getNazwisko() {
        return nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getEmail() {
        return email;
    }
}
