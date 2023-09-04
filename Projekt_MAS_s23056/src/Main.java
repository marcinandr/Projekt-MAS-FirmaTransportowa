import java.io.*;
import java.util.Date;

public class Main {
    //nazwa pliku do zapisu ekstensji
    final static String extentFile = "extent.bin";

    //metoda do zapisu ekstenscji
    public static void zapisEktensji() {
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            Autobus.zapiszEkstensjaAutous(out);
            Bilet.zapiszEkstensjaBilet(out);
            Kierowca.zapiszEkstensjaKierowca(out);
            Mechanik.zapiszEkstensjaMechanik(out);
            Pasazer.zapiszEkstensjaPasazer(out);
            Raport.zapiszEkstensjaRaport(out);
            Trasa.zapiszEkstensjaTrasa(out);
            ZarejestrowanyUzytkownik.zapiszEkstensjaZarejestrowanyUzytkownik(out);
            ZarzadzajacyTrasami.zapiszEkstensjaZarzadzajacyTrasami(out);
            ZlecenieNaprawy.zapiszEkstensjaZlecenieNaprawy(out);
            Znizka.zapiszEkstensjaZnizka(out);
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //metoda do odczytu ekstensji
    public static void odczytEkstensji() {
        try {
            var in = new ObjectInputStream(new FileInputStream(extentFile));
            Autobus.czytajEkstensjaAutobus(in);
            Bilet.czytajEkstensjaBilet(in);
            Kierowca.czytajEkstensjaKierowca(in);
            Mechanik.czytajEkstensjaMechanik(in);
            Pasazer.czytajEkstensjaPasazer(in);
            Raport.czytajEkstensjaRaport(in);
            Trasa.czytajEkstensjaTrasa(in);
            ZarejestrowanyUzytkownik.czytajEkstensjaZarejestrowanyUzytkownik(in);
            ZarzadzajacyTrasami.czytajEkstensjaZarzadzajacyTrasami(in);
            ZlecenieNaprawy.czytajEkstensjaZlecenieNaprawy(in);
            Znizka.czytajEkstensjaZnizka(in);
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args)  {

        /*sprawdzenie czy jest zapisana ekstensja
        jezeli tak - odczytjemy ekstensje
        jezeli nie - tworzymy nowe obiekty
         */

        File file = new File(extentFile);
        if (file.exists()) {

            //odczyt ekstensji
            Main.odczytEkstensji();

            //uruchomienie przypadku uzycia "Zakup biletu"
            if (ZarejestrowanyUzytkownik.getekstensjaZarejestrowanyUzytkownik().get(1) != null) {
                new GUIWyborTrasy(ZarejestrowanyUzytkownik.getekstensjaZarejestrowanyUzytkownik().get(1));
            }

        }

        else {

            //tworrzenie obiketow

            try {

                //utworzenie obiektów
                Trasa trasa1 = new Trasa(1, new Date(123, 7, 12, 12, 30), new Date(123, 7, 12, 18, 30), "Kraków", "Warszawa", 250, 500, enumTrasaStatus.planowana);
                Trasa trasa21 = new Trasa(21, new Date(123, 8, 15, 6, 30), new Date(123, 8, 15, 12, 30), "Warszawa", "Gdańsk", 150, 350, enumTrasaStatus.planowana);
                Trasa trasa22 = new Trasa(22, new Date(123, 8, 15, 9, 30), new Date(123, 8, 15, 15, 30), "Warszawa", "Gdańsk", 150, 350, enumTrasaStatus.planowana);
                Trasa trasa23 = new Trasa(23, new Date(123, 8, 15, 10, 30), new Date(123, 8, 15, 16, 30), "Warszawa", "Gdańsk", 150, 350, enumTrasaStatus.planowana);
                Trasa trasa3 = new Trasa(3, new Date(123, 8, 16, 8, 0), new Date(123, 8, 16, 12, 0), "Warszawa", "Olsztyn", 100, 250, enumTrasaStatus.planowana);
                Trasa trasa4 = new Trasa(4, new Date(123, 9, 17, 9, 30), new Date(123, 9, 17, 15, 30), "Warszawa", "Zakopane", 280, 600, enumTrasaStatus.planowana);
                Trasa trasa5 = new Trasa(5, new Date(123, 9, 20, 11, 30), new Date(123, 9, 20, 20, 30), "Warszawa", "Szczecin", 300, 450, enumTrasaStatus.planowana);

                Znizka znizkaOsNiep = new Znizka("Zniżka dla osoby niepełnosprawnej", 75, "Legitymacja niepelnosprawnego");
                Znizka znizkaStudent = new Znizka("Zniżka dla studenta", 50, "Legitymacja studencka");
                Znizka znizkaZolnierz = new Znizka("Zniżka dla żołnierza", 25, "Ksiazeczka wojskowa");

                Autobus autobus1 = new Autobus("WI987SD", "Mercedes", "Citaro", new Date(123), 35, true, true);
                Autobus autobus2 = new Autobus("BS987SD", "Star", "Big", new Date(101), 40, true, true);
                Autobus autobus3 = new Autobus("CD3204O", "Jelcz", "Chelleng", new Date(115), 30, false, false);
                Autobus autobus4 = new Autobus("GH2141P", "Peugeot", "Grand", new Date(120), 45, true, false);
                Autobus autobus5 = new Autobus("SD21721", "Fiat", "City", new Date(118), 20, true, true);
                Autobus autobus6 = new Autobus("DA2q24L", "BMW", "Travel", new Date(2021), 28, false, true);
                Autobus autobus7 = new Autobus("FG0432K", "MAN", "Blue", new Date(1980), 25, true, true);

                ZarejestrowanyUzytkownik zarejestrowanyUzytkownik1 = new ZarejestrowanyUzytkownik("Kazimierz", "Iksisnki", "Warszawa, Grzybowska 19", new Date(), "kazimierz@wp.pl");
                ZarejestrowanyUzytkownik zarejestrowanyUzytkownik2 = new ZarejestrowanyUzytkownik("Ewa", "Pietruszka", "Warszawa, Kanałowa 19", new Date(), "ewa@onet.pl");
                ZarejestrowanyUzytkownik zarejestrowanyUzytkownik3 = new ZarejestrowanyUzytkownik("Jerzy", "Polak", "Warszawa, Plac Bankowy 19", new Date(), "jerzy@gmail.pl");

                Kierowca kierowca1 = new Kierowca("Marek", "Aaaaaa", "Warszawa, Partyzantów 16", new Date(1987, 12, 23), "marek@wp.pl", new Date(), "88856464", new Date());
                Kierowca kierowca2 = new Kierowca("Bolek", "Bbbbbb", "Warszawa, Benedykta 15", new Date(1985, 11, 15), "bolek@wp.pl", new Date(), "3432422342", new Date());
                Kierowca kierowca3 = new Kierowca("Franek", "Cccccc", "Warszawa, Bawełniana 3", new Date(1986, 10, 9), "franek@wp.pl", new Date(), "46546546", new Date());
                Kierowca kierowca4 = new Kierowca("Maciek", "Dddddd", "Warszawa, Calineczki 5", new Date(1990, 9, 10), "maciek@wp.pl", new Date(), "54654354", new Date());
                Kierowca kierowca5 = new Kierowca("Zdzisiek", "Eeeeee", "Warszawa, Postępu 16", new Date(1973, 8, 21), "zdzisiek@wp.pl", new Date(), "56545476", new Date());

                ZarzadzajacyTrasami zarzadzajacyTrasami1 = new ZarzadzajacyTrasami("Filip", "Xxxxxx", "Warszawa, Filantropijna 12", new Date(), "filip@onet.pl", new Date(), "8797897");
                ZarzadzajacyTrasami zarzadzajacyTrasami2 = new ZarzadzajacyTrasami("Alfred", "Xxxxxx", "Warszawa, Sukienna 45", new Date(), "alfred@onet.pl", new Date(), "21214324");
                ZarzadzajacyTrasami zarzadzajacyTrasami3 = new ZarzadzajacyTrasami("Ryszard", "Xxxxxx", "Warszawa, Drzewna 23", new Date(), "ryszard@onet.pl", new Date(), "23432423");


                Mechanik mechanik1 = new Mechanik("Gerwazy", "Wwwwww", "Warszawa, Białogrodzka 15", new Date(1953, 1, 8), "gerwazy@wp.pl", new Date(), "4894165", true, false);
                Mechanik mechanik2 = new Mechanik("Staszek", "Ssssss", "Warszawa, Fantazyjna 25", new Date(1996, 5, 23), "staszek@wp.pl", new Date(), "5165156", false, false);
                Mechanik mechanik3 = new Mechanik("Zygunt", "Tttttt", "Warszawa, Grzybowa 113", new Date(1963, 10, 15), "zygmunt@wp.pl", new Date(), "87986512", true, true);
                Mechanik mechanik4 = new Mechanik("Michał", "Hhhhhhh", "Warszawa, Krzyzowa 19", new Date(1980, 11, 16), "michal@wp.pl", new Date(), "84651678", true, true);
                Mechanik mechanik5 = new Mechanik("Fryderyk", "Pppppp", "Warszawa, Flamandzka 30", new Date(1971, 5, 25), "fryderyk@wp.pl", new Date(), "88856464", false, false);


                ZlecenieNaprawy zlecenieNaprawy1 = new ZlecenieNaprawy("Olsztyn, Konstruktorska 17", "hamlulce", enumStatusZleceniaNaprawy.zgloszone, new Date());
                ZlecenieNaprawy zlecenieNaprawy2 = new ZlecenieNaprawy("Białystok, Wolska 15", "układ kierowniczy", enumStatusZleceniaNaprawy.zgloszone, new Date());
                ZlecenieNaprawy zlecenieNaprawy3 = new ZlecenieNaprawy("Bydgoszcz, Zamkowa 35", "elektryka", enumStatusZleceniaNaprawy.zgloszone, new Date());
                ZlecenieNaprawy zlecenieNaprawy4 = new ZlecenieNaprawy("Gdańsk, Polska 35", "szyberdach", enumStatusZleceniaNaprawy.zgloszone, new Date());
                ZlecenieNaprawy zlecenieNaprawy5 = new ZlecenieNaprawy("Warszawa, Złota 35", "układ chłodniczy", enumStatusZleceniaNaprawy.zgloszone, new Date());

                //tworzenie asocjacji

                //kierowca - autobus (wiele do wielu)
                kierowca1.przypiszDoAutobusu(autobus1);
                kierowca1.przypiszDoAutobusu(autobus2);
                kierowca2.przypiszDoAutobusu(autobus2);
                kierowca3.przypiszDoAutobusu(autobus5);
                kierowca4.przypiszDoAutobusu(autobus3);
                autobus7.przypiszDoKierowcy(kierowca1);

                //zlecenieNaprawy - autobus (po stronie Autobus zmienilem licznosc na 1 zatem jest: * - 1 - wiekszy sens biznesowy)
                zlecenieNaprawy1.setAutobusDoNaprawy(autobus1);
                autobus1.przypiszDoZlecenieNaprawy(zlecenieNaprawy1);
                zlecenieNaprawy2.setAutobusDoNaprawy(autobus4);
                autobus4.przypiszDoZlecenieNaprawy(zlecenieNaprawy3);
                zlecenieNaprawy4.setAutobusDoNaprawy(autobus2);

                //mechanik - zlecenieNaprawy (po stronie Mechanik zmienilem licznosc na 1 zatem jest * - 1 - wiekszy sens biznesowy)
                zlecenieNaprawy1.setMechanikDokonujacyNaprawy(mechanik1);
                mechanik1.przypiszDoZlecenieNaprawy(zlecenieNaprawy1);
                zlecenieNaprawy2.setMechanikDokonujacyNaprawy(mechanik4);
                mechanik3.przypiszDoZlecenieNaprawy(zlecenieNaprawy1);

                //zarzadzajacyTrasami - Trasa (liczność 1 - * )
                zarzadzajacyTrasami1.przypiszDoTrasa(trasa1);
                zarzadzajacyTrasami2.przypiszDoTrasa(trasa21);
                zarzadzajacyTrasami3.przypiszDoTrasa(trasa22);
                trasa4.setZarzadzajacyTrasami(zarzadzajacyTrasami1);
                trasa23.setZarzadzajacyTrasami(zarzadzajacyTrasami3);
                trasa5.setZarzadzajacyTrasami(zarzadzajacyTrasami2);

                //powizania: bilet-zarejestrowanyUzytkownik, bilet-pasazer, bilet-znizka, - tworza sie podczas reazlaicji przypadku uzycia zakup biletu

                //trasa - autobus (licznosc 1-1)
                trasa1.setAutobus(autobus6);
                trasa21.setAutobus(autobus2);
                trasa22.setAutobus(autobus3);
                autobus1.setTrasa(trasa23);
                autobus4.setTrasa(trasa4);
                autobus5.setTrasa(trasa5);
                trasa3.setAutobus(autobus7);

                //uruchomienie przypadku uzycia "Zakup biletu"
                new GUIWyborTrasy(zarejestrowanyUzytkownik1);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //zapis ekstensji:
        Main.zapisEktensji();


    }
}
