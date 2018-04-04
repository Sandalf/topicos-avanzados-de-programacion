package comboBusqueda;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import combos.ComboBoxEdoCdMun;

public class Aplicacion extends JFrame{

	private ComboFiltrado comboFiltrado;

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
			comboFiltrado = new ComboFiltrado();
			comboFiltrado.setLocation(0,0);
			comboFiltrado.setSize(300, 60);
			comboFiltrado.setVisible(true);
			add(comboFiltrado);
			setVisible(true);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Ocurrio un error al inicilizar");
			e.printStackTrace();
		}

	}

}
