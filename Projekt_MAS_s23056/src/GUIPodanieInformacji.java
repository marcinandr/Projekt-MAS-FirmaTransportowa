import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class GUIPodanieInformacji extends JFrame implements ActionListener {
    List<Znizka> obowiazujacaZnizki;
    JComboBox jComboBoxZnizki;
    JLabel jLabelRodzajZnizki, jLabelRodzajDokumentu, jLabelInfo;
    JPanel jPanel;
    JButton buttonWyslij, buttonPrzejdzDalej;
    String wybierzZnizke = "Dokonaj wyboru zniżki. Jeżeli przysługuje...";
    String info = "UWAGA: Zniżki zostaną naliczone automatycznie. " + '\n' +
                    "Podczas podróży knieczne jest posiadanie dokumentu uprawniającego. " + '\n' +
                    "Wybierz przysługującą zniżkę i naciśnij WYŚLIJ" + '\n';
    String info2 = "Lub naciśnij PRZEJDŹ DALEJ, jeżeli nie posiadasz zniżek";
    Bilet kupowanyBilet;

    ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu;
    public GUIPodanieInformacji(Trasa trasa , ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu) {

        this.kupowanyBilet = new Bilet(trasa , zarejestrowanyUzytkownikDokonujacyZakupu);

        this.zarejestrowanyUzytkownikDokonujacyZakupu = zarejestrowanyUzytkownikDokonujacyZakupu;

        obowiazujacaZnizki = Znizka.zwrocObowiazujaceZnizki();

        this.setTitle("Podanie informacji");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(50 , 10);

        Dimension dimension = new Dimension(1600, 800);

        jPanel = new JPanel();
        jPanel.setPreferredSize(dimension);
        jPanel.setBackground(Color.GRAY);

        jLabelRodzajZnizki = new JLabel("Obowiązujące zniżki: ");
        jLabelRodzajZnizki.setBounds(250, 30, 300, 30);
        jLabelRodzajZnizki.setBackground(Color.LIGHT_GRAY);
        jLabelRodzajZnizki.setOpaque(true);
        jPanel.add(jLabelRodzajZnizki);

        jLabelRodzajDokumentu = new JLabel("Rodzaj uprawniającego dokumentu: ");
        jLabelRodzajDokumentu.setBounds(650, 30, 300, 30);
        jLabelRodzajDokumentu.setBackground(Color.LIGHT_GRAY);
        jLabelRodzajDokumentu.setOpaque(true);
        jPanel.add(jLabelRodzajDokumentu);

        for (int i = 0; i < obowiazujacaZnizki.size(); i++) {
            JLabel jLabel = new JLabel(obowiazujacaZnizki.get(i).getKomuPrzysluguje() + " - " + obowiazujacaZnizki.get(i).getIleWynosi() + "%");
            int y = 80 + 40*i;
            jLabel.setBounds(250, y, 300, 30);
            jLabel.setBackground(Color.LIGHT_GRAY);
            jLabel.setOpaque(true);
            jPanel.add(jLabel);
        }

        for (int i = 0; i < obowiazujacaZnizki.size(); i++) {
            JLabel jLabel = new JLabel(obowiazujacaZnizki.get(i).getInfo());
            int y = 80 + 40*i;
            jLabel.setBounds(650, y, 300, 30);
            jLabel.setBackground(Color.LIGHT_GRAY);
            jLabel.setOpaque(true);
            jPanel.add(jLabel);
        }

        jLabelInfo = new JLabel(info);
        jLabelInfo.setBounds(100, 250, 1000, 40);
        jLabelInfo.setBackground(Color.LIGHT_GRAY);
        jLabelInfo.setOpaque(true);
        jPanel.add(jLabelInfo);

        jLabelInfo = new JLabel(info2);
        jLabelInfo.setBounds(100, 290, 1000, 40);
        jLabelInfo.setBackground(Color.LIGHT_GRAY);
        jLabelInfo.setOpaque(true);
        jPanel.add(jLabelInfo);

        jComboBoxZnizki = new JComboBox();
        jComboBoxZnizki.setBounds(500, 350, 300, 50);
        jComboBoxZnizki.addItem(wybierzZnizke);
        for (Znizka znizka : obowiazujacaZnizki) {
            jComboBoxZnizki.addItem(znizka);
        }
        jPanel.add(jComboBoxZnizki);

        jPanel.setLayout(null);
        buttonWyslij = new JButton("WYSLIJ");
        buttonWyslij.setBounds(520, 500, 250, 40);
        buttonWyslij.addActionListener(this);
        jPanel.add(buttonWyslij);

        jPanel.setLayout(null);
        buttonPrzejdzDalej = new JButton("PRZEJDŹ DALEJ");
        buttonPrzejdzDalej.setBounds(520, 550, 250, 40);
        buttonPrzejdzDalej.addActionListener(this);
        jPanel.add(buttonPrzejdzDalej);

        this.getContentPane().add(jPanel);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

       Object rodzaj = e.getSource();

       if (rodzaj == buttonWyslij) {

           if (jComboBoxZnizki.getSelectedItem().equals(wybierzZnizke)) {
               JOptionPane.showMessageDialog(null, "Nie wybrałeś żadnej zniżki. Naciśnij PRZEJDŹ DALEJ lub dokonaj wyboru zniżki");
           } else {
               this.kupowanyBilet.setZnizka((Znizka)jComboBoxZnizki.getSelectedItem());
               this.kupowanyBilet.obliczWysokoscZnizkiICeneBiletu();
               new GUIWyborMiejscawAutobusie(this.kupowanyBilet , this.zarejestrowanyUzytkownikDokonujacyZakupu);
               this.dispose();
           }
       } else if (rodzaj == buttonPrzejdzDalej) {
           this.kupowanyBilet.obliczWysokoscZnizkiICeneBiletu();
           new GUIWyborMiejscawAutobusie(this.kupowanyBilet , this.zarejestrowanyUzytkownikDokonujacyZakupu);
           this.dispose();
       }

    }
}
