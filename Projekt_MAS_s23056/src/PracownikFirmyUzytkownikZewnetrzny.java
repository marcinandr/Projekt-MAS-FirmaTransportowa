import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//implementacja overlapping - zastapienie klasy PracownikFirmy i UzytkownikZewnetrzny jedna klasa PracownikFirmyUzytkownikZewnetrzny

enum typOsoba {PracownikFirmy , UzytkowinkZewnetrzny}

public class PracownikFirmyUzytkownikZewnetrzny extends Osoba implements Serializable {

    //informacja o rodzajach obiektu
    private List<typOsoba> rodzajObiektu = new ArrayList<>();

    //pola dla PracownikFirmy
    private Date dataZatrudnienia;
    private String nrTel;

    //konstruktor
    public PracownikFirmyUzytkownikZewnetrzny(String imie , String nazwisko , String adres , Date dataUrodzenia, String email) {
        super(imie, nazwisko, adres, dataUrodzenia, email);
    }

    //getter i setter dla rodzaju obiektu

    private List<typOsoba> getRodzajObiektu() {
        return rodzajObiektu;
    }

    public void setRodzajObiektu(typOsoba rodzajObiektu) {
        if( ! this.rodzajObiektu.contains(rodzajObiektu))
        this.rodzajObiektu.add(rodzajObiektu);
    }

    //gettery i settery dla pol z PracownikFrimry

    public Date getDataZatrudnienia() throws Exception {
        if (this.rodzajObiektu.contains(typOsoba.PracownikFirmy)) {
            return this.dataZatrudnienia;
        } else {
            throw new Exception("Ta osoba nie jest pracownikiem firmy");
        }
    }

    public String getNrTel() throws Exception {
        if (this.rodzajObiektu.contains(typOsoba.PracownikFirmy)) {
            return this.nrTel;
        } else {
            throw new Exception("Ta osoba nie jest pracownikiem firmy");
        }
    }

    public void setDataZatrudnienia(Date dataZatrudnienia) throws Exception {
        if (this.rodzajObiektu.contains(typOsoba.PracownikFirmy)) {
            this.dataZatrudnienia = dataZatrudnienia;
        } else {
            throw new Exception("Ta osoba nie jest pracownikiem firmy");
        }
    }

    public void setNrTel(String nrTel) throws Exception {
        if (this.rodzajObiektu.contains(typOsoba.PracownikFirmy)) {
            this.nrTel = nrTel;
        } else {
            throw new Exception("Ta osoba nie jest pracownikiem firmy");
        }
    }

    @Override
    public String toString() {
        if (rodzajObiektu.contains(typOsoba.PracownikFirmy)) {
            return super.toString() +
                    " dataZatrudnienia=" + dataZatrudnienia +
                    ", nrTel='" + nrTel + "' ";
        } else {
            return super.toString();
        }
    }
}
