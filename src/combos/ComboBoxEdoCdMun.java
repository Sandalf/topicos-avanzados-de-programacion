package combos;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ComboBoxEdoCdMun extends JPanel implements ActionListener {

	private JComboBox<String> comBoxEstados;
	private JComboBox<String> comBoxMunicipios;
	private JComboBox<String> comBoxCiudades;

	private ArchivoEstados archivoEstados;
	private ArchivoMunicipios archivoMunicipios;
	private ArchivoCiudades archivoCiudades;

	private Estado estadoSeleccionado = new Estado();
	private Municipio municipioSeleccionado = new Municipio();
	private Ciudad ciudadSeleccionada = new Ciudad();
	
	private String opcionDefault = "Seleccionar";

	public ComboBoxEdoCdMun() throws Exception {
		crearInterfaz();
		crearEventos();
		setVisible(true);
	}

	public ComboBoxEdoCdMun(String estado) throws Exception {
		crearInterfaz(estado);
		crearEventos();
		setVisible(true);
	}

	public ComboBoxEdoCdMun(String estado, String municipio) throws Exception {
		crearInterfaz(estado, municipio);
		crearEventos();
		setVisible(true);
	}

	public void crearInterfaz() throws Exception {
		setLayout(new GridLayout(0, 6));
		inicializarArchivos();
		crearCombos();
	}

	public void crearInterfaz(String estado) throws Exception {
		setLayout(new GridLayout(0, 6));
		inicializarArchivos();
		crearCombos();
		cargarParametroComboEdo(estado);
	}
	
	public void crearInterfaz(String estado, String municipio) throws Exception {
		setLayout(new GridLayout(0, 6));
		inicializarArchivos();
		crearCombos();
		cargarParametroComboEdoMun(estado,municipio);
	}
	
	public void crearCombos() throws Exception {
		crearComboBoxEstados();
		crearComboBoxMunicipios();
		crearComboBoxCiudades();
	}
	
	public void cargarParametroComboEdo(String estado) throws HeadlessException, Exception {
		if(existeEstado(estado)) {
			seleccionarEstado(estado);		
			comBoxEstados.setSelectedItem(estado);
		} else {
			JOptionPane.showMessageDialog(this, "Estado seleccionado no encontrado: " + estado);
		}
	}
	
	public void cargarParametroComboEdoMun(String estado, String municipio) throws HeadlessException, Exception {
		if(existeEstado(estado)) {
			seleccionarEstado(estado);		
			comBoxEstados.setSelectedItem(estado);	
			if(existeMunicipio(municipio)) {
				seleccionarMuncipio(municipio);
				comBoxMunicipios.setSelectedItem(municipio);
			} else {
				JOptionPane.showMessageDialog(this, "Municipio seleccionado no encontrado: " + municipio);
			}		
		} else {
			JOptionPane.showMessageDialog(this, "Estado seleccionado no encontrado: " + estado);
		}
	}

	public void crearComboBoxEstados() throws Exception {
		add(new JLabel("Estados:"));
		String[] elementosComboBox = crearElementosComboBoxEstados();
		comBoxEstados = new JComboBox<String>(elementosComboBox);
		add(comBoxEstados);
	}

	public String[] crearElementosComboBoxEstados() throws Exception {
		archivoEstados = new ArchivoEstados();
		ArrayList<Estado> listaEstados = archivoEstados.leerEstados();
		String[] elementos = new String[listaEstados.size() + 1];
		elementos[0] = opcionDefault;
		for (int i = 1; i < elementos.length; i++) {
			elementos[i] = listaEstados.get(i - 1).getNombreEstado();
		}
		return elementos;
	}

	public void crearComboBoxMunicipios() throws Exception {
		add(new JLabel("Municipios:"));
		comBoxMunicipios = new JComboBox<String>();
		comBoxMunicipios.addItem(opcionDefault);
		comBoxMunicipios.setEnabled(false);
		add(comBoxMunicipios);
	}

	public void crearComboBoxCiudades() {
		add(new JLabel("Ciudades:"));
		comBoxCiudades = new JComboBox<String>();
		comBoxCiudades.addItem(opcionDefault);
		comBoxCiudades.setEnabled(false);
		add(comBoxCiudades);
	}

	public void inicializarArchivos() throws FileNotFoundException {
		archivoEstados = new ArchivoEstados();
		archivoMunicipios = new ArchivoMunicipios();
		archivoCiudades = new ArchivoCiudades();
	}

	public void crearEventos() {
		comBoxEstados.addActionListener(this);
		comBoxMunicipios.addActionListener(this);
	}

	public String[] obtenerElementosComboBoxCiudades() throws Exception {
		ArrayList<Ciudad> listaCiudades = archivoCiudades.buscarCiudades(estadoSeleccionado.getEstadoId(),
				municipioSeleccionado.getMunicipioId());
		String[] elementos = new String[listaCiudades.size() + 1];
		elementos[0] = opcionDefault;
		for (int i = 1; i < elementos.length; i++) {
			elementos[i] = listaCiudades.get(i - 1).getNombreCiudad();
		}
		return elementos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == comBoxEstados) {
				String nombreEstado = (String) comBoxEstados.getSelectedItem();
				seleccionarEstado(nombreEstado);
				
				comBoxMunicipios.setSelectedIndex(0);
				
			} else if (e.getSource() == comBoxMunicipios) {
				String nombreMunicipio = (String) comBoxMunicipios.getSelectedItem();
				seleccionarMuncipio(nombreMunicipio);
			} else if (e.getSource() == comBoxCiudades) {
				String nombreCiudad = (String) comBoxCiudades.getSelectedItem();
				seleccionarCiudad(nombreCiudad);
			}
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "Ocurrio un error al seleccionar");
			error.printStackTrace();
		}
	}

	public void seleccionarEstado(String nombreEstado) throws Exception {
		if (!esElementoInicial(nombreEstado)) {
			if (existeEstado(nombreEstado)) {
				estadoSeleccionado = archivoEstados.buscarEstado(nombreEstado);
				cargarComboBoxMunicipios();
			} else {
				JOptionPane.showMessageDialog(this, "Estado seleccionado no encontrado");
			}
		} else {
			reiniciarCombo(comBoxMunicipios);
			reiniciarCombo(comBoxCiudades);
		}
	}

	public void seleccionarMuncipio(String nombreMunicipio) throws Exception {	
		if (!esElementoInicial(nombreMunicipio)) {
			if (existeMunicipio(nombreMunicipio)) {
				municipioSeleccionado = archivoMunicipios.buscarMunicipio(estadoSeleccionado.getEstadoId(), nombreMunicipio);
				cargarComboBoxCiudades();
			} else {
				JOptionPane.showMessageDialog(this, "Municipio seleccionado no encontrado");
			}
		} else {
			reiniciarCombo(comBoxCiudades);
		}
	}

	public void seleccionarCiudad(String nombreCiudad) throws Exception {
		if (!esElementoInicial(nombreCiudad)) {
			if (existeCiudad(nombreCiudad)) {
				ciudadSeleccionada = archivoCiudades.buscarCiudad(estadoSeleccionado.getEstadoId(),municipioSeleccionado.getMunicipioId(), nombreCiudad);
			} else {
				JOptionPane.showMessageDialog(this, "Ciudad seleccionada no encontrada");
			} 
		}
	}
	
	public void cargarComboBoxMunicipios() throws Exception {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(obtenerElmentosComboBoxMunicipios());
		comBoxMunicipios.setModel(modelo);
		comBoxMunicipios.setEnabled(true);
	}

	public String[] obtenerElmentosComboBoxMunicipios() throws Exception {
		ArrayList<Municipio> listaMunicipios = archivoMunicipios.buscarMunicipios(estadoSeleccionado.getEstadoId());
		String[] elementos = new String[listaMunicipios.size() + 1];
		elementos[0] = opcionDefault;
		for (int i = 1; i < elementos.length; i++) {
			elementos[i] = listaMunicipios.get(i - 1).getNombreMunicipio();
		}
		return elementos;
	}

	public void cargarComboBoxCiudades() throws Exception {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(obtenerElementosComboBoxCiudades());
		comBoxCiudades.setModel(modelo);
		comBoxCiudades.setEnabled(true);
	}
	
	public boolean esElementoInicial(String elemento) {
		return elemento == opcionDefault ? true : false;
	}
	
	public boolean existeEstado(String nombreEstado) throws Exception {
		return archivoEstados.buscarEstado(nombreEstado) != null ? true : false;
	}
	
	public boolean existeMunicipio(String nombreMunicipio) throws Exception {
		return archivoMunicipios.buscarMunicipio(estadoSeleccionado.getEstadoId(), nombreMunicipio) != null ? true : false;
	}
	
	public boolean existeCiudad(String nombreCiudad) throws Exception {
		return archivoCiudades.buscarCiudad(estadoSeleccionado.getEstadoId(),municipioSeleccionado.getMunicipioId(), nombreCiudad) != null ? true : false;
	}
	
	public void reiniciarCombo(JComboBox<String> comboBox) {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(new String[]{ opcionDefault });
		
		
		comboBox.removeAllItems();
		comboBox.setModel(modelo);
		comboBox.setEnabled(false);
	}
}
