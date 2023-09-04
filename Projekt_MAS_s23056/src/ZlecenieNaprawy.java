import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//implemntacja ograniczenia na pole status
enum enumStatusZleceniaNaprawy {zgloszone , wTrakcieRealizacji , zrealizowane}
public class ZlecenieNaprawy implements Serializable {
    private String adres;
    private String krotkiOpis;
    private enumStatusZleceniaNaprawy status;
    private Date dataZgloszenia;
    private Date dataNaprawy; //opcjonalny

    //ekstensja
    private static List<ZlecenieNaprawy> ekstensjaZlecenieNaprawy = new ArrayList();

    //konstruktor
    public ZlecenieNaprawy(String adres, String krotkiOpis, enumStatusZleceniaNaprawy status, Date dataZgloszenia) throws Exception {
        //implementacja ograniczenia max 500 znaków
        if (krotkiOpis.length() > 500) {
            throw new Exception("Krótki opis naprawy może mieć maksymalnie 500 znaków");
        }
        this.adres = adres;
        this.krotkiOpis = krotkiOpis;
        this.status = status;
        this.dataZgloszenia = dataZgloszenia;
        ekstensjaZlecenieNaprawy.add(this);
    }

    //getter i setter do atrybutu opcjonalnego

    public Date getDataNaprawy() throws Exception {
        if(this.dataNaprawy != null) {
            return this.dataNaprawy;
        } else {
            throw new Exception("Naprawa nie została jeszcze wykonana");
        }
    }

    public void setDataNaprawy(Date data) throws Exception {
        if(this.dataNaprawy == null) {
            this.dataNaprawy = data;
        } else {
            throw new Exception("Naprawa została już wykonana");
        }
    }
    //implementacja ograniczenia max 500 znaków
    public void setKrotkiOpis(String krotkiOpis) throws Exception {
        if (krotkiOpis.length() > 500) {
            throw new Exception("Krótki opis naprawy może mieć maksymalnie 500 znaków");
        }
        this.krotkiOpis = krotkiOpis;
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaZlecenieNaprawy(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaZlecenieNaprawy);
    }
    public static void czytajEkstensjaZlecenieNaprawy(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaZlecenieNaprawy = (List<ZlecenieNaprawy>) input.readObject();
    }

    //wyświetlenie ekstensji
    public static void pokazListe() {
        for (ZlecenieNaprawy zlecenieNaprawy : ekstensjaZlecenieNaprawy) {
            System.out.println(zlecenieNaprawy);
        }
    }

    @Override
    public String toString() {
        return "ZlecenieNaprawy{" +
                "adres='" + adres + '\'' +
                ", krotkiOpis='" + krotkiOpis + '\'' +
                ", status='" + status + '\'' +
                ", dataZgloszenia=" + dataZgloszenia +
                ", dataNaprawy=" + dataNaprawy +
                '}';
    }

    //asocjacja ZlecenieNaprawy - Mechanik - po stronie Mechanik zmienilem licznosc na 1
    private Mechanik mechanikDokonujacyNaprawy;

    public Mechanik getMechanikDokonujacyNaprawy() {
        return this.mechanikDokonujacyNaprawy;
    }

    public void setMechanikDokonujacyNaprawy(Mechanik nowyMechanik) {
        if (this.mechanikDokonujacyNaprawy != null)
            this.mechanikDokonujacyNaprawy.usunZlecenieNaprawy(this);
        this.mechanikDokonujacyNaprawy = nowyMechanik;
        nowyMechanik.przypiszDoZlecenieNaprawy(this);
    }

    //asocjacja ZlecenieNaprawy - Autobus - po stronie Autobus zmienilem licznosc na 1
    private Autobus autobusDoNaprawy;

    public Autobus getAutobusDoNaprawy() {
        return this.autobusDoNaprawy;
    }

    public void setAutobusDoNaprawy(Autobus nowyAutobus) {
        if (this.autobusDoNaprawy != null)
            this.autobusDoNaprawy.usunZlecenieNaprawy(this);
        this.autobusDoNaprawy = nowyAutobus;
        nowyAutobus.przypiszDoZlecenieNaprawy(this);
    }

}
