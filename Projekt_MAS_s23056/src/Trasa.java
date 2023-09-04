import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//implementacja ograniczen na pole status
enum enumTrasaStatus { planowana , brakMiejsc , zrealizowana , niezrealziowana }
public class Trasa implements Serializable {

    private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
    private int nr;

    //wzgędem daigramu klas zmodyfikowałem na dwa pola (data odjazdu z godz. i min oraz data przyjazdu z godz. i min)
    private Date dataOdjazdu;
    private Date dataPrzyjazdu;
    private String miastoPoczatkowe;
    private String miastoKoncowe;
    private float cenaPrzejazdu;
    private float odleglosc;
    private enumTrasaStatus status;

    //getter do konstruktora Bilet potrzebny
    public int getNr() {
        return nr;
    }

    //implementacja ograniczenia unikatowy na nr Trasy
    public void setNr(int nr) throws Exception {
        for (Trasa trasa : ekstensjaTrasa) {
            if(trasa.nr == nr) {
                throw new Exception("Istnieje już trasa o takim nrID");
            }
        }
        this.nr = nr;
    }

    //ekstensja
    private static List<Trasa> ekstensjaTrasa = new ArrayList();

    //konstruktor
    public Trasa(int nr, Date dataOdjazdu, Date dataPrzyjazdu, String miastoPoczatkowe, String miastoKoncowe, float cenaPrzejazdu, float odleglosc, enumTrasaStatus status) throws Exception {
        //implementacja ograniczenia unikatowy na nr Trasy
        for (Trasa trasa : ekstensjaTrasa) {
            if (trasa.nr == nr) {
                throw new Exception("Istnieje już trasa o takim nrID");
            }
        }
        this.nr = nr;
        this.dataOdjazdu = dataOdjazdu;
        this.dataPrzyjazdu = dataPrzyjazdu;
        this.miastoPoczatkowe = miastoPoczatkowe;
        this.miastoKoncowe = miastoKoncowe;
        this.cenaPrzejazdu = cenaPrzejazdu;
        this.odleglosc = odleglosc;
        this.status = status;
        ekstensjaTrasa.add(this);
    }

    //do wyswietlenia ekstensji
    public static void pokazListe() {
        for(Trasa trasa : ekstensjaTrasa) {
            System.out.println(trasa.toString());
        }
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaTrasa(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaTrasa);
    }
    public static void czytajEkstensjaTrasa(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaTrasa = (List<Trasa>) input.readObject();
    }

    @Override
    public String toString() {
        return "Trasa{" +
                "nr=" + nr +
                ", dataOdjazdu=" + sdf.format(dataOdjazdu) +
                ", dataPrzyjazdu=" + sdf.format(dataPrzyjazdu) +
                ", miastoPoczatkowe='" + miastoPoczatkowe + '\'' +
                ", miastoKoncowe='" + miastoKoncowe + '\'' +
                ", cenaPrzejazdu=" + cenaPrzejazdu +
                ", odleglosc=" + odleglosc +
                ", status='" + status + '\'' +
                '}';
    }

    //asocjacja ZarzadzajacyTarasami - Trasa
    private ZarzadzajacyTrasami zarzadzajacyTrasami;

    public ZarzadzajacyTrasami getZarzadzajacyTrasami() {
        return this.zarzadzajacyTrasami;
    }

    public void setZarzadzajacyTrasami(ZarzadzajacyTrasami nowyZarzadzajacyTrasami) throws Exception {
        if (this.zarzadzajacyTrasami != null)
            this.zarzadzajacyTrasami.usunTrasa(this);
        this.zarzadzajacyTrasami = nowyZarzadzajacyTrasami;
        nowyZarzadzajacyTrasami.przypiszDoTrasa(this);
    }

    //asocjacja Trasa - Bilet
    private List<Bilet> asocjacjaBilet = new ArrayList<>();

    public void usunBilet (Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.remove(bilet);
        }
    }
    public void przypiszDoBilet(Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.add(bilet);

            bilet.setTrasa(this);
        }
    }

    //asocjacja Autobus-Trasa
    private Autobus autobus;

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus nowyAutobus) {
        this.autobus = nowyAutobus;
        if (this.autobus.getTrasa() == null)
            this.autobus.setTrasa(this);
    }

    //implementacja przypadku uzycia

    //metoda do wyszukiwania tras na podstawie podanych miast
    public static Trasa[] szukajTrasy (String miastoPoczatkowe, String miastoKoncowe) {
        List wyszukaneTrasy = new ArrayList<>();
        for (Trasa trasa : ekstensjaTrasa) {
            if ( trasa.miastoPoczatkowe.equals(miastoPoczatkowe) && trasa.miastoKoncowe.equals(miastoKoncowe) ) {
                wyszukaneTrasy.add(trasa);
            }
        }

        Trasa[] wyszukaneTrasyTab = new Trasa[wyszukaneTrasy.size()];

        wyszukaneTrasy.toArray(wyszukaneTrasyTab);

        return wyszukaneTrasyTab;
    }

    public static List<Trasa> getEkstensjaTrasa() {
        return ekstensjaTrasa;
    }

    //implementacja przypadku uzycia:

    //pobranie ceny
    public float getCenaPrzejazdu() {
        return cenaPrzejazdu;
    }

    //metoda do zmiany status

    public void setStatus(enumTrasaStatus status) {
        this.status = status;
    }

    //metoda do sprawdzenie czy zmienic status na brak miejsc
    public boolean czyZmienicStatusNaBrakMiejsc() {
        if (this.autobus.getListaWolnychMiejsc().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    //pobranie asocjacji z bilet

    public List<Bilet> getAsocjacjaBilet() {
        return asocjacjaBilet;
    }
}


