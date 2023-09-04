import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Autobus implements Serializable{

    private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
    private String nrRej;
    private String marka;
    private String model;
    private Date rokProdukcji;
    private int iloscMiejscSiedziacych;
    private boolean czyKlimatyzowany;
    private boolean czyWiFi;
    private List<Integer> listaWolnychMiejsc;

    //ekstensja
    private static List<Autobus> ekstensjaAutobus = new ArrayList();

    //konstruktor
    public Autobus(String nrRej, String marka, String model, Date rokProdukcji, int iloscMiejscSiedziacych, boolean czyKlimatyzowany, boolean czyWiFi) throws Exception {
        //implementacja ograniczenia unikatowy na nrRej
        for (Autobus autobus : ekstensjaAutobus) {
            if (autobus.nrRej.equals(nrRej)) {
                throw new Exception("Istanieje juz autobus o takim nr rejestracyjnym");
            }
        }
        this.nrRej = nrRej;
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
        this.iloscMiejscSiedziacych = iloscMiejscSiedziacych;
        this.czyKlimatyzowany = czyKlimatyzowany;
        this.czyWiFi = czyWiFi;
        listaWolnychMiejsc = new ArrayList<Integer>();
        //na poczatku wszystkie miejasca sa wolne zatem:
        for (int i = 0; i <= iloscMiejscSiedziacych; i++) {
            listaWolnychMiejsc.add(i);
        }
        ekstensjaAutobus.add(this);
    }

    //implementacja ograniczenia unikatowy na nrRej
    public void setNrRej(String nrRej) throws Exception {
        for (Autobus autobus : ekstensjaAutobus) {
            if (autobus.nrRej.equals(nrRej)) {
                throw new Exception("Istanieje juz autobus o takim nr rejestracyjnym");
            }
        }
        this.nrRej = nrRej;
    }

    //metoda do wyswietlania zawartosci ekstensji
    public static void pokazListePosiadanych() {
        for (Autobus autobus : ekstensjaAutobus) {
            System.out.println(autobus.toString());
        }
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaAutous(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaAutobus);
    }
    public static void czytajEkstensjaAutobus(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaAutobus = (List<Autobus>) input.readObject();
    }

    @Override
    public String toString() {
        return "Autobus{" +
                "nrRej='" + nrRej + '\'' +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rokProdukcji=" + sdf.format(rokProdukcji) +
                ", iloscMiejscSiedziacych=" + iloscMiejscSiedziacych +
                ", czyKlimatyzowany=" + czyKlimatyzowany +
                ", czyWiFi=" + czyWiFi +
                '}';
    }

    //asocjacja Autobus-Kierowca
    private List<Kierowca> asocjacjaKierowcy = new ArrayList<>();
    public void przypiszDoKierowcy(Kierowca kierowca) {
        if(!asocjacjaKierowcy.contains(kierowca)) {
            asocjacjaKierowcy.add(kierowca);

           kierowca.przypiszDoAutobusu(this);

        }
    }

    //asocjacja ZlecenieNaprawy - Autobus - po stronie Autobus zmienilem licznosc na 1
    private List<ZlecenieNaprawy> asocjacjaZlecenieNaprawy = new ArrayList<>();

    public void usunZlecenieNaprawy (ZlecenieNaprawy zlecenieNaprawy) {
        if(!asocjacjaZlecenieNaprawy.contains(zlecenieNaprawy)) {
            asocjacjaZlecenieNaprawy.remove(zlecenieNaprawy);
        }
    }
    public void przypiszDoZlecenieNaprawy(ZlecenieNaprawy zlecenieNaprawy) {
        if(!asocjacjaZlecenieNaprawy.contains(zlecenieNaprawy)) {
            asocjacjaZlecenieNaprawy.add(zlecenieNaprawy);

            zlecenieNaprawy.setAutobusDoNaprawy(this);
        }
    }

    //asocjacja Autobus-Trasa
    private Trasa trasa;

    public Trasa getTrasa() {
        return trasa;
    }
    public void setTrasa(Trasa nowaTrasa) {
        this.trasa = nowaTrasa;
        if (this.trasa.getAutobus() == null)
        this.trasa.setAutobus(this);

    }

    //implementacja przypadku uzycia

    //getter listy wolnych miejsc
    public List<Integer> getListaWolnychMiejsc() {
        return listaWolnychMiejsc;
    }

    //rezerwacja miejsca
    public void rezerwujMiejsce(Integer i) {
        this.listaWolnychMiejsc.remove(i);
    }

    //getter dla ekstensji
    public static List<Autobus> getEkstensjaAutobus() {
        return ekstensjaAutobus;
    }

    //geetere przypisani Kierowcy

    public List<Kierowca> getAsocjacjaKierowcy() {
        return asocjacjaKierowcy;
    }
}
