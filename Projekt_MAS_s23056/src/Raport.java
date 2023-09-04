import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//implementacja ograniczen na pole opcja
enum enumOpcjaRaport {liczbaSprzedanuychBiletowNaTrasach , wartoscSprzedanuychBiletowNaTrasach}
public class Raport implements Serializable {
    private Date dataUtworzenia;
    private Date miesiacPoczatkowy;
    private Date miesiacKoncowy;
    private enumOpcjaRaport opcja;

    //ekstensja
    private static List<Raport> ekstensjaRaport = new ArrayList();

    //konstruktor
    public Raport(Date miesiacPoczatkowy, Date miesiacKoncowy, enumOpcjaRaport opcja) {
        this.dataUtworzenia = new Date();
        this.miesiacPoczatkowy = miesiacPoczatkowy;
        this.miesiacKoncowy = miesiacKoncowy;
        this.opcja = opcja;
        ekstensjaRaport.add(this);
    }

    //do wyswietlenia ekstensji
    public static void pokazListe() {
        for(Raport raport : ekstensjaRaport) {
            System.out.println(raport.toString());
        }
    }

    //zapis i odczyt ekstensji
    public static void zapiszEkstensjaRaport(ObjectOutputStream output) throws IOException {
        output.writeObject(ekstensjaRaport);
    }
    public static void czytajEkstensjaRaport(ObjectInputStream input) throws IOException, ClassNotFoundException {
        ekstensjaRaport = (List<Raport>) input.readObject();
    }

    @Override
    public String toString() {
        return "Raport{" +
                "dataUtworzenia=" + dataUtworzenia +
                ", miesiacPoczatkowy=" + miesiacPoczatkowy +
                ", miesiacKoncowy=" + miesiacKoncowy +
                ", opcja='" + opcja + '\'' +
                '}';
    }

    //asocjacja Raport - Bilet zamienilem na wiele do wielu (wiekszy sens biznesowy)
    private List<Bilet> asocjacjaBilet = new ArrayList<>();
    public void przypiszDoBilet(Bilet bilet) {
        if(!asocjacjaBilet.contains(bilet)) {
            asocjacjaBilet.add(bilet);

            bilet.przypiszDoRaport(this);
        }
    }

}
