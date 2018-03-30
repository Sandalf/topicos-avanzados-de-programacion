package combos;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxEdoCdMun extends JPanel {
	
	private JComboBox<String> comBoxEstados;
	private ArchivoEstados archivoEstados;

	public ComboBoxEdoCdMun() throws Exception {
		crearInterfaz();
		setVisible(true);
	}
	
	public void crearInterfaz() throws Exception {
		setLayout(new GridLayout(1,0));
		
		add(new JLabel("Estados:"));
		crearComboBoxEstados();	
	}
	
	public void crearComboBoxEstados() throws Exception {		
		String[] elementosComboBox = crearElementosComboBoxEstados();		
		comBoxEstados = new JComboBox<String>(elementosComboBox);
		add(comBoxEstados);
	}
	
	public String[] crearElementosComboBoxEstados() throws Exception  {
		archivoEstados = new ArchivoEstados();	
		ArrayList<Estado> listaEstados = archivoEstados.leerEstados();
		String[] elementos = new String[listaEstados.size()+1];
		elementos[0] = "Seleccionar";
		for(int i = 1; i < elementos.length; i++) {
			elementos[i] = listaEstados.get(i-1).getNombreEstado();
		}
		return elementos;
	}
	
}
