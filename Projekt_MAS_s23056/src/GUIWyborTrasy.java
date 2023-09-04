import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIWyborTrasy extends JFrame implements ActionListener {

	JPanel jPanel;
	JButton buttonSzukaj, buttonKupBilet;
	JLabel jLMiastoPoczatkowe, jLMiastoKoncowe, jLWyszukaneTrasy;
	JTextField jTFMiastoPoczatkowe, jTFMiastoKoncowe;
	String stringMiastoPoczatkowe, stringMiastoKoncowe;
	JComboBox jComboBoxWyszykaneTrasy;
	ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu;

	String info = "Nie obsługujemy takiej trasy. Podaj inne Miasata";
	public GUIWyborTrasy(ZarejestrowanyUzytkownik zarejestrowanyUzytkownikDokonujacyZakupu) {

		this.zarejestrowanyUzytkownikDokonujacyZakupu = zarejestrowanyUzytkownikDokonujacyZakupu;

		this.setTitle("Wybor Trasy");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(50 , 10);

		Dimension dimension = new Dimension(1600, 800);

		jPanel = new JPanel();
		jPanel.setPreferredSize(dimension);
		jPanel.setBackground(Color.green);
		jPanel.setLayout(null);

		buttonSzukaj = new JButton("SZUKAJ");
		buttonSzukaj.setBounds(500, 50, 200, 20);
		buttonSzukaj.addActionListener(this);
		jPanel.add(buttonSzukaj);

		buttonKupBilet = new JButton("KUP BILET");
		buttonKupBilet.setBounds(500, 300, 200, 20);
		buttonKupBilet.addActionListener(this);
		jPanel.add(buttonKupBilet);

		jLMiastoPoczatkowe = new JLabel("Miasto poczatkowe: ");
		jLMiastoPoczatkowe.setBounds(100, 30, 200, 20);
		jTFMiastoPoczatkowe = new JTextField("");
		jTFMiastoPoczatkowe.setBounds(250, 30, 200, 20);

		jLMiastoKoncowe = new JLabel("Miasto końcowe: ");
		jLMiastoKoncowe.setBounds(100, 60, 200, 20);
		jTFMiastoKoncowe = new JTextField("");
		jTFMiastoKoncowe.setBounds(250, 60, 200, 20);

		jLWyszukaneTrasy = new JLabel("Dostepne trasy: ");
		jLWyszukaneTrasy.setBounds(100, 90, 200, 20);

		jComboBoxWyszykaneTrasy = new JComboBox();
		jComboBoxWyszykaneTrasy.setBounds(250, 90, 1200, 20);

		jPanel.add(jLMiastoPoczatkowe);
		jPanel.add(jTFMiastoPoczatkowe);
		jPanel.add(jLMiastoKoncowe);
		jPanel.add(jTFMiastoKoncowe);
		jPanel.add(jLWyszukaneTrasy);
		jPanel.add(jComboBoxWyszykaneTrasy);

		this.getContentPane().add(jPanel);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object rodzaj = e.getSource();

		if (rodzaj == buttonSzukaj) {

			jComboBoxWyszykaneTrasy.removeAllItems();

			this.stringMiastoPoczatkowe = jTFMiastoPoczatkowe.getText();
			this.stringMiastoKoncowe = jTFMiastoKoncowe.getText();

			Trasa[] szukaneTrasy = Trasa.szukajTrasy(stringMiastoPoczatkowe, stringMiastoKoncowe);

			if (szukaneTrasy.length != 0) {

				JCheckBoxMenuItem[] jCheckBoxMenuItemTab = new JCheckBoxMenuItem[szukaneTrasy.length];

				for (int i = 0; i < szukaneTrasy.length; i++) {
					jComboBoxWyszykaneTrasy.addItem(szukaneTrasy[i]);
				}
			} else {
				jComboBoxWyszykaneTrasy.addItem(info);
			}
		} else if (rodzaj == buttonKupBilet) {
			if(jComboBoxWyszykaneTrasy.getSelectedItem() == null || jComboBoxWyszykaneTrasy.getSelectedItem().equals(info)) {
				JOptionPane.showMessageDialog(null, "Nie dokonałeś wyboru Trasy. Jeżeli chcesz kupić bilet musisz wybrać trasę");
			} else {
				new GUIPodanieInformacji((Trasa)jComboBoxWyszykaneTrasy.getSelectedItem() , this.zarejestrowanyUzytkownikDokonujacyZakupu);
				this.dispose();
			}
		}

	}
}