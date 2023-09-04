import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIPokazAsocjacje extends JFrame implements ActionListener {
    JPanel jPanel;
    JButton buttonPokazPrzypisaneAutobusy, buttonPokazPrzypisanychKierowcow,
            buttonPokazPrzypisaneBilety, buttonPokazPrzypisanaTrasa;
    JLabel jLWybierzAutobus, jLPrzypisaniKierowcy, jLWybierzKierowce, jLPrzypisaneAutobusy,
            jLAsocjacjaWieleWiele, jLAsocjacjaWieleJeden, jLWybierzTrasa, jLPrzypisaneBilety, jLWybierzBilet, jLPrzypisanaTrasa;
    JComboBox jComboBoxWszyscyKierowcy, jComboBoxWszytskieAutobusy, jComboBoxPrzypisaneAutobusy, jComboBoxPrzypisaniKierowcy,
            jComboBoxWszystkieTrasy, jComboBoxWszystkieBilety, jComboBoxPrzypisanaTrasa, jComboBoxZakupioneBilety;
    public GUIPokazAsocjacje() {

        this.setTitle("Asocjacje");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(50, 10);

        Dimension dimension = new Dimension(1800, 800);

        jPanel = new JPanel();
        jPanel.setPreferredSize(dimension);
        jPanel.setBackground(Color.green);
        jPanel.setLayout(null);

        buttonPokazPrzypisaneAutobusy = new JButton("POKAZ PRZYPISANE AUTOBUSY");
        buttonPokazPrzypisaneAutobusy.setBounds(910, 150, 250, 20);
        buttonPokazPrzypisaneAutobusy.addActionListener(this);
        jPanel.add(buttonPokazPrzypisaneAutobusy);

        buttonPokazPrzypisanychKierowcow = new JButton("POKAZ PRZYPISANYCH KIEROWCOW");
        buttonPokazPrzypisanychKierowcow.setBounds(90, 150, 250, 20);
        buttonPokazPrzypisanychKierowcow.addActionListener(this);
        jPanel.add(buttonPokazPrzypisanychKierowcow);

        buttonPokazPrzypisaneBilety = new JButton("POKAZ PRZYPISANE BILETY");
        buttonPokazPrzypisaneBilety.setBounds(90, 470, 250, 20);
        buttonPokazPrzypisaneBilety.addActionListener(this);
        jPanel.add(buttonPokazPrzypisaneBilety);

        buttonPokazPrzypisanaTrasa = new JButton("POKAZ PRZYPISANA TRASE");
        buttonPokazPrzypisanaTrasa.setBounds(910, 470, 250, 20);
        buttonPokazPrzypisanaTrasa.addActionListener(this);
        jPanel.add(buttonPokazPrzypisanaTrasa);

        jLAsocjacjaWieleWiele = new JLabel("Asocjajca Wiele - Wiele (Autobus - Kierowca): ");
        jLAsocjacjaWieleWiele.setBounds(90, 15, 300, 20);
        jLAsocjacjaWieleWiele.setBackground(Color.WHITE);
        jLAsocjacjaWieleWiele.setOpaque(true);

        jLAsocjacjaWieleJeden = new JLabel("Asocjajca Wiele - Jeden (Bilet - Trasa)");
        jLAsocjacjaWieleJeden.setBounds(90, 300, 300, 20);
        jLAsocjacjaWieleJeden.setBackground(Color.WHITE);
        jLAsocjacjaWieleJeden.setOpaque(true);

        jLWybierzAutobus = new JLabel("Wybierz Autobus: ");
        jLWybierzAutobus.setBounds(90, 50, 200, 20);

        jLPrzypisaniKierowcy = new JLabel("Przypisani Kierowcy: ");
        jLPrzypisaniKierowcy.setBounds(90, 200, 200, 20);

        jLWybierzKierowce = new JLabel("Wybierz Kierowce: ");
        jLWybierzKierowce.setBounds(910, 50, 200, 20);

        jLPrzypisaneAutobusy = new JLabel("Przypisane Autobusy: ");
        jLPrzypisaneAutobusy.setBounds(910, 200, 200, 20);

        jComboBoxWszyscyKierowcy = new JComboBox();
        jComboBoxWszyscyKierowcy.setBounds(910, 80, 800, 30);
        for (Kierowca kierowca : Kierowca.getEkstensjaKierowca()) {
            jComboBoxWszyscyKierowcy.addItem(kierowca);
        }

        jComboBoxWszytskieAutobusy = new JComboBox();
        jComboBoxWszytskieAutobusy.setBounds(90, 80, 800, 30);
        for (Autobus autobus : Autobus.getEkstensjaAutobus()) {
            jComboBoxWszytskieAutobusy.addItem(autobus);
        }

        jComboBoxPrzypisaneAutobusy = new JComboBox();
        jComboBoxPrzypisaneAutobusy.setBounds(910, 230, 800, 30);

        jComboBoxPrzypisaniKierowcy = new JComboBox();
        jComboBoxPrzypisaniKierowcy.setBounds(90, 230, 800, 30);

        jLWybierzTrasa = new JLabel("Wybierz Trase: ");
        jLWybierzTrasa.setBounds(90, 360, 200, 30);

        jLPrzypisaneBilety = new JLabel("Przypisane bilety: ");
        jLPrzypisaneBilety.setBounds(90, 510, 200, 30);

        jLWybierzBilet = new JLabel("Wybierz bilet: ");
        jLWybierzBilet.setBounds(910, 360, 200, 30);

        jLPrzypisanaTrasa = new JLabel("Przypisana trasa: ");
        jLPrzypisanaTrasa.setBounds(910, 510, 200, 30);

        jComboBoxWszystkieTrasy = new JComboBox();
        jComboBoxWszystkieTrasy.setBounds(90, 400, 800, 30);
        for (Trasa trasa : Trasa.getEkstensjaTrasa()) {
            jComboBoxWszystkieTrasy.addItem(trasa);
        }

        jComboBoxWszystkieBilety = new JComboBox();
        jComboBoxWszystkieBilety.setBounds(910, 400, 800, 30);
        for (Bilet bilet : Bilet.getEkstensjaBilet()) {
            jComboBoxWszystkieBilety.addItem(bilet);
        }

        jComboBoxPrzypisanaTrasa = new JComboBox();
        jComboBoxPrzypisanaTrasa.setBounds(910, 550, 800, 30);

        jComboBoxZakupioneBilety = new JComboBox();
        jComboBoxZakupioneBilety.setBounds(90, 550, 800, 30);

        jPanel.add(jLAsocjacjaWieleWiele);
        jPanel.add(jLWybierzAutobus);
        jPanel.add(jLPrzypisaniKierowcy);
        jPanel.add(jLWybierzKierowce);
        jPanel.add(jLPrzypisaneAutobusy);
        jPanel.add(jComboBoxWszyscyKierowcy);
        jPanel.add(jComboBoxWszytskieAutobusy);
        jPanel.add(jComboBoxPrzypisaneAutobusy);
        jPanel.add(jComboBoxPrzypisaniKierowcy);

        jPanel.add(jLAsocjacjaWieleJeden);
        jPanel.add(jLWybierzTrasa);
        jPanel.add(jComboBoxWszystkieTrasy);
        jPanel.add(jLWybierzBilet);
        jPanel.add(jComboBoxWszystkieBilety);
        jPanel.add(jLPrzypisanaTrasa);
        jPanel.add(jComboBoxPrzypisanaTrasa);
        jPanel.add(jLPrzypisaneBilety);
        jPanel.add(jComboBoxZakupioneBilety);

        this.getContentPane().add(jPanel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object rodzaj = e.getSource();

        if (rodzaj == buttonPokazPrzypisanychKierowcow) {

            jComboBoxPrzypisaniKierowcy.removeAllItems();

            Autobus autobus = (Autobus) jComboBoxWszytskieAutobusy.getSelectedItem();

            for (Kierowca kierowca : autobus.getAsocjacjaKierowcy()) {
                jComboBoxPrzypisaniKierowcy.addItem(kierowca);
            }

        } else if (rodzaj == buttonPokazPrzypisaneAutobusy) {

            jComboBoxPrzypisaneAutobusy.removeAllItems();

            Kierowca kierowca = (Kierowca) jComboBoxWszyscyKierowcy.getSelectedItem();

            for (Autobus autobus : kierowca.getAsocjacjaAutobusy()) {
                jComboBoxPrzypisaneAutobusy.addItem(autobus);
            }
        } else if (rodzaj == buttonPokazPrzypisaneBilety) {

            jComboBoxZakupioneBilety.removeAllItems();

            Trasa trasa = (Trasa) jComboBoxWszystkieTrasy.getSelectedItem();

            for (Bilet bilet : trasa.getAsocjacjaBilet()) {
                jComboBoxZakupioneBilety.addItem(bilet);
            }
        } else if (rodzaj == buttonPokazPrzypisanaTrasa) {

            jComboBoxPrzypisanaTrasa.removeAllItems();

            Bilet bilet = (Bilet) jComboBoxWszystkieBilety.getSelectedItem();

            jComboBoxPrzypisanaTrasa.addItem(bilet.getTrasa());
        }
    }
}