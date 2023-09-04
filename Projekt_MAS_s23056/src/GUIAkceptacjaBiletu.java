import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class GUIAkceptacjaBiletu extends JFrame implements ActionListener {

    ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu;
    Bilet kupowanyBilet;
    JPanel jPanel;
    JTextArea jTextAreaBilet, jTextAreaInfo;

    JButton buttonAkcpetuj;

    String info = "  Wyświetlono dane wybranego biletu. " + '\n' +
            "  Jeśli chcesz dokonać zkupu biletu, kliknij Akceptuj. " + '\n' +
            "  Zostaniesz przekierowany do zewnętrznego systemu " + '\n' +
            "  obsługującego płatności on-lnie";

    public GUIAkceptacjaBiletu(Bilet kupowanyBilet, ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu) {

        this.kupowanyBilet = kupowanyBilet;
        this.zarejestrowanyUzytkownikDokonujacyZakupu = zarejestrowanyUzytkownikDokonujacyZakupu;

        this.setTitle("Akceptacja biletu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(50, 10);

        Dimension dimension = new Dimension(1600, 800);

        jPanel = new JPanel();
        jPanel.setPreferredSize(dimension);
        jPanel.setBackground(Color.GREEN);
        jPanel.setLayout(null);

        jTextAreaBilet = new JTextArea(this.kupowanyBilet.toString());
        jTextAreaBilet.setBounds(450, 80, 400, 120);
        jTextAreaBilet.setBackground(Color.BLUE);
        jTextAreaBilet.setForeground(Color.WHITE);
        jTextAreaBilet.setOpaque(true);
        jPanel.add(jTextAreaBilet);

        jTextAreaInfo = new JTextArea(info);
        jTextAreaInfo.setBounds(500, 240, 300, 120);
        jTextAreaInfo.setBackground(Color.YELLOW);
        jTextAreaInfo.setOpaque(true);
        jPanel.add(jTextAreaInfo);

        buttonAkcpetuj = new JButton("AKCEPTUJ");
        buttonAkcpetuj.setBounds(520, 390, 250, 40);
        buttonAkcpetuj.addActionListener(this);
        jPanel.add(buttonAkcpetuj);

        this.getContentPane().add(jPanel);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.kupowanyBilet.kupBilet(this.zarejestrowanyUzytkownikDokonujacyZakupu);
        Main.zapisEktensji();
        new GUIPokazAsocjacje();
        this.dispose();

    }
}