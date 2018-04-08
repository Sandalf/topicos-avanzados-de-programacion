package combos;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Aplicacion extends JFrame {

	private ComboBoxEdoCdMun comboBoxEdoCdMun;
	private ComboBoxEdoCdMun comboBoxEdoCdMun2;
	private ComboBoxEdoCdMun comboBoxEdoCdMun3;

	public Aplicacion() {
		CrearInterfaz();
		setVisible(true);
	}

	public void CrearInterfaz() {
		try {
			setSize(800, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(true);
			setLayout(new GridLayout(3,0));
			comboBoxEdoCdMun = new ComboBoxEdoCdMun();
			add(comboBoxEdoCdMun);
			comboBoxEdoCdMun2 = new ComboBoxEdoCdMun("JALISCO");
			add(comboBoxEdoCdMun2);
			comboBoxEdoCdMun3 = new ComboBoxEdoCdMun("JALISCO","MUN1JALISCO");
			add(comboBoxEdoCdMun3);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Ocurrio un error al inicilizar");
			e.printStackTrace();
		}

	}

}
