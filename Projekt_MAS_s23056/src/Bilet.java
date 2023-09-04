import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bilet implements Serializable {

    //implementacja ograniczenia unikatowy na nrID
    private static int nrID;
    private int nrTrasy; //wyliczalny - pochodzi od nrTrasy podnaej w konstruktorze
    private float wysokoscZnizki; //wyliczalny - w czesci implementacja przypadku uzycia
    private float cenaBiletu; //wyliczalny - w czesci implementacja przypadku uzycia
    private int nrMiejscaWAutobusie; //zrobić wyliczalny

    //aby moc wyswietlac bilety sprzedane w danym okresie nalezalo wprowadzic dodatkowe pole dataSprzedazy
    private Date dataSprzedazy;

    //ekstensja
    private static List<Bilet> ekstensjaBilet = new ArrayList();

    //konstruktor
    public Bilet(Trasa trasa , ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakuu) {
        nrID++;
        this.nrTrasy = trasa.getNr();
        this.setTrasa(trasa); // ustawienie asocjacji bilet-trasa
        this.setZarejestrowanyUzytkownik(zarejestrowanyUzytkownikDokonujacyZakuu);
    }

    //implementacja ograniczenia unikatowy na nrID
    public void setNrID(int nrID) throws Exception {
        for (Bilet bilet : ekstensjaBilet) {
            if (bilet.nrID == nrID) {
                throw new Exception();
            }
        }
        this.nrID = nrID;
    }

    //metoda do wyswietlania zawartosci ekstensji
    public static void pokazlListe() {
         for (Bilet bilet : ekstensjaBilet) {
             System.out.println(bilet.toString());
         }
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaBilet(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaBilet);
    }
    public static void czytajEkstensjaBilet(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaBilet = (List<Bilet>) input.readObject();
    }

    @Override
    public String toString() {
        return "  Bilet " + '\n' +
                "  nr ID biletu = " + nrID + '\n' +
                "  nr ID trasy = " + nrTrasy + '\n' +
                "  wartość zniżki = " + znizka + '\n' +
                "  cena biletu = " + cenaBiletu + '\n' +
                "  nr miejsca w autobusie = " + nrMiejscaWAutobusie + '\n' ;
    }

    //asocjacja ZarejestrowanyUzytkownik - Bilet - po stronie ZarejestrowanyUzytkownik zmienilem licznosc na 1, bilet moze zostac zakupiony tylko przez 1 ZarejetrowanegoUzytkownika
    private ZarejestrowanyUzytkownik zarejestrowanyUzytkownik;

    public ZarejestrowanyUzytkownik getZarejestrowanyUzytkownik() {
        return this.zarejestrowanyUzytkownik;
    }

    public void setZarejestrowanyUzytkownik(ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu) {
        if (this.zarejestrowanyUzytkownik != null)
            this.zarejestrowanyUzytkownik.usunBilet(this);
        this.zarejestrowanyUzytkownik = zarejestrowanyUzytkownikDokonujacyZakupu;
        zarejestrowanyUzytkownikDokonujacyZakupu.przypiszDoBilet(this);
    }

    //asocjacja Bilet - Znizka (po stronie Znizka zmienilem liczność na 0 lub 1, nadny bilet moze przyslugiwac tylko 1 lub zadna znizka
    private Znizka znizka;
    public Znizka getZnizka() {
        return this.znizka;
    }
    public void setZnizka(Znizka znizkaNaBilet) {
        if (this.znizka != null)
            this.znizka.usunBilet(this);
        this.znizka = znizkaNaBilet;
        znizkaNaBilet.przypiszDoBilet(this);
    }

    //asocjacja Pasazer - Bilet
    private Pasazer pasazer;

    public Pasazer getPasazer() {
        return this.pasazer;
    }

    public void setPasazer(Pasazer pasazerKtoryZakupilBilet) {
        if (this.pasazer != null)
            this.pasazer.usunBilet(this);
        this.pasazer = pasazerKtoryZakupilBilet;
        pasazerKtoryZakupilBilet.przypiszDoBilet(this);
    }

    //asocjacja Raport - Bilet zamienilem na wiele do wielu (wiekszy sens biznesowy)
    private List<Raport> asocjacjaRaport = new ArrayList<>();
    public void przypiszDoRaport(Raport raport) {
        if(!asocjacjaRaport.contains(raport)) {
            asocjacjaRaport.add(raport);

            raport.przypiszDoBilet(this);
        }
    }

    //asocjacja Trasa - Bilet
    private Trasa trasa;

    public Trasa getTrasa() {
        return this.trasa;
    }
    public void setTrasa(Trasa trasaNaKtorZostalZakupionyBilet) {
        if (this.trasa != null)
            this.trasa.usunBilet(this);
        this.trasa = trasaNaKtorZostalZakupionyBilet;
        trasaNaKtorZostalZakupionyBilet.przypiszDoBilet(this);
    }

    //implementacja przypadku uzycia

    public void obliczWysokoscZnizkiICeneBiletu () {
        if (znizka != null) {
            this.wysokoscZnizki = this.trasa.getCenaPrzejazdu() * (this.znizka.getIleWynosi() / 100);
        } else {
            this.wysokoscZnizki = 0;
        }
        this.cenaBiletu = this.trasa.getCenaPrzejazdu() - this.wysokoscZnizki;
    }

    public void setNrMiejscaWAutobusie(int nrMiejscaWAutobusie) {
        this.nrMiejscaWAutobusie = nrMiejscaWAutobusie;
    }

    public void kupBilet(ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacayZakupu) {
        Pasazer pasazer = new Pasazer(zarejestrowanyUzytkownikDokonujacayZakupu);
        this.setPasazer(pasazer);
        this.setZarejestrowanyUzytkownik(zarejestrowanyUzytkownikDokonujacayZakupu);
        if(this.trasa.czyZmienicStatusNaBrakMiejsc()) {
            this.trasa.setStatus(enumTrasaStatus.brakMiejsc);
        }
        ekstensjaBilet.add(this);
    }

    //do zwrocenia ekstensji

    public static List<Bilet> getEkstensjaBilet() {
        return ekstensjaBilet;
    }
}
