package combos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxEdoCdMun extends JPanel implements ActionListener {
	
	private JComboBox<String> comBoxEstados;
	private JComboBox<String> comBoxMunicipios;
	
	private ArchivoEstados archivoEstados;
	private ArchivoMunicipios archivoMunicipios;

	public ComboBoxEdoCdMun() throws Exception {
		crearInterfaz();
		crearEventos();
		setVisible(true);
	}
	
	public void crearInterfaz() throws Exception {
		setLayout(new GridLayout(3,0));
		
		inicializarArchivos();
		
		crearComboBoxEstados();
		crearComboBoxMunicipios();
	}
	
	public void crearComboBoxEstados() throws Exception {		
		add(new JLabel("Estados:"));
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
	
	public void crearComboBoxMunicipios() throws Exception {		
		add(new JLabel("Municipios:"));
		String[] elementosComboBox = crearElementosComboBoxMunicipios();		
		comBoxMunicipios = new JComboBox<String>(elementosComboBox);
		comBoxMunicipios.setEnabled(false);
		add(comBoxMunicipios);
	}
	
	public String[] crearElementosComboBoxMunicipios() throws Exception  {
		ArrayList<Municipio> listaMunicipios = archivoMunicipios.leerMunicipios();
		String[] elementos = new String[listaMunicipios.size()+1];
		elementos[0] = "Seleccionar";
		for(int i = 1; i < elementos.length; i++) {
			elementos[i] = listaMunicipios.get(i-1).getNombreMunicipio();
		}
		return elementos;
	}
	
	public void inicializarArchivos() throws FileNotFoundException {
		archivoEstados = new ArchivoEstados();
		archivoMunicipios = new ArchivoMunicipios();
	}
	
	public void crearEventos() {
		comBoxEstados.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comBoxEstados) {
			System.out.println("Estado seleccionado");
		}
	}
	
}
