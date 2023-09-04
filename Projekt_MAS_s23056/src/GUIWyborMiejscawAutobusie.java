import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUIWyborMiejscawAutobusie extends JFrame implements ActionListener {

    Bilet kupowanyBilet;

    ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu;
    JPanel jPanel;
    JLabel jLabelRezerwacja;
    JComboBox jComboBoxWolneMiejsca;

    JButton buttonRezerwuj;

    String wybierzMiejsce = "Wybierz miejsce....";

    public GUIWyborMiejscawAutobusie(Bilet kupowanyBilet , ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu) {

        this.kupowanyBilet = kupowanyBilet;
        this.zarejestrowanyUzytkownikDokonujacyZakupu = zarejestrowanyUzytkownikDokonujacyZakupu;

        this.setTitle("Rezerwcaja miejsca w autobusie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(50, 10);

        Dimension dimension = new Dimension(1600, 800);

        jPanel = new JPanel();
        jPanel.setPreferredSize(dimension);
        jPanel.setBackground(Color.BLUE);
        jPanel.setLayout(null);

        jLabelRezerwacja = new JLabel("  Wybierz miejsce, które chcesz zarezerwować i naciśnij REZRWUJ: ");
        jLabelRezerwacja.setBounds(250, 80, 500, 60);
        jLabelRezerwacja.setBackground(Color.LIGHT_GRAY);
        jLabelRezerwacja.setOpaque(true);
        jPanel.add(jLabelRezerwacja);

        jComboBoxWolneMiejsca = new JComboBox();
        jComboBoxWolneMiejsca.setBounds(500, 300, 300, 50);
        jComboBoxWolneMiejsca.addItem(wybierzMiejsce);
        for (Integer nrMiejsca : this.kupowanyBilet.getTrasa().getAutobus().getListaWolnychMiejsc()) {
            jComboBoxWolneMiejsca.addItem(nrMiejsca);
        }
        jPanel.add(jComboBoxWolneMiejsca);

        buttonRezerwuj = new JButton("REZERWUJ");
        buttonRezerwuj.setBounds(520, 450, 250, 40);
        buttonRezerwuj.addActionListener(this);
        jPanel.add(buttonRezerwuj);

        this.getContentPane().add(jPanel);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (jComboBoxWolneMiejsca.getSelectedItem().equals(wybierzMiejsce)) {
            JOptionPane.showMessageDialog(null, "Nie wybrałeś miejsca. Wybierz miejsce");
        } else {
            this.kupowanyBilet.getTrasa().getAutobus().rezerwujMiejsce( (Integer)jComboBoxWolneMiejsca.getSelectedItem() );
            this.kupowanyBilet.setNrMiejscaWAutobusie((int)jComboBoxWolneMiejsca.getSelectedItem());
            new GUIAkceptacjaBiletu(this.kupowanyBilet , this.zarejestrowanyUzytkownikDokonujacyZakupu);
            this.dispose();
        }
    }
}