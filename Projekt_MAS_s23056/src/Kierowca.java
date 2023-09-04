import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kierowca extends PracownikFirmyUzytkownikZewnetrzny implements Serializable{
    private Date odKiedyPrawoJazdyKatD;

    //kontruktor
    public Kierowca(String imie, String nazwisko, String adres, Date dataUrodzenia, String email, Date dataZatrudnienia, String nrTel, Date odKiedyPrawoJazdyKatD) throws Exception {
        super(imie, nazwisko, adres, dataUrodzenia, email);
        this.setRodzajObiektu(typOsoba.PracownikFirmy);
        this.setDataZatrudnienia(dataZatrudnienia);
        this.setNrTel(nrTel);
        this.odKiedyPrawoJazdyKatD = odKiedyPrawoJazdyKatD;
        ekstensjaKierowca.add(this);
    }
    //ekstensja
    private static List<Kierowca> ekstensjaKierowca = new ArrayList();

    //metoda do wyswietlania ekstensji
    public static void pokazListe() {
        for (Kierowca kierowca : ekstensjaKierowca) {
            System.out.println(kierowca.toString());
        }
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaKierowca(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaKierowca);
    }
    public static void czytajEkstensjaKierowca(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaKierowca = (ArrayList<Kierowca>) input.readObject();
    }

    @Override
    public String toString() {
        return "Kierowca{" + super.toString() +
                "odKiedyPrawoJazdyKatD=" + odKiedyPrawoJazdyKatD +
                '}';
    }

    //asocjacja Autobus-Kierowca
    private List<Autobus> asocjacjaAutobusy = new ArrayList<>();
    public void przypiszDoAutobusu(Autobus autobus) {
        if(!asocjacjaAutobusy.contains(autobus)) {
            asocjacjaAutobusy.add(autobus);

            autobus.przypiszDoKierowcy(this);
        }
    }

    //getter dla ekstensji
    public static List<Kierowca> getEkstensjaKierowca() {
        return ekstensjaKierowca;
    }

    //getter dla asocjacji z Autobusami

    public List<Autobus> getAsocjacjaAutobusy() {
        return asocjacjaAutobusy;
    }
}
