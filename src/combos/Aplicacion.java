package combos;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Aplicacion extends JFrame {

	private ComboBoxEdoCdMun comboBoxEdoCdMun;

	public Aplicacion() {
		CrearInterfaz();
		setVisible(true);
	}

	public void CrearInterfaz() {
		try {
			setSize(300, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(false);
			setLayout(null);

			comboBoxEdoCdMun = new ComboBoxEdoCdMun();
			comboBoxEdoCdMun.setLocation(0,0);
			comboBoxEdoCdMun.setSize(300, 60);
			comboBoxEdoCdMun.setVisible(true);
			add(comboBoxEdoCdMun);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
