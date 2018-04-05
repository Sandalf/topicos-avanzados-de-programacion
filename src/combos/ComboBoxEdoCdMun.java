package combos;

import java.awt.GridLayout;
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

	public ComboBoxEdoCdMun() throws Exception {
		crearInterfaz();
		crearEventos();
		setVisible(true);
	}
	
	//------------------------------------------------------------------------//
	public void crearInterfaz() throws Exception {
		setLayout(new GridLayout(3,0));
		
		inicializarArchivos();
		
		crearComboBoxEstados();
		crearComboBoxMunicipios();
		crearComboBoxCiudades();
	}
	
	//------------------------------------------------------------------------//
	public void crearComboBoxEstados() throws Exception {		
		add(new JLabel("Estados:"));
		String[] elementosComboBox = crearElementosComboBoxEstados();		
		comBoxEstados = new JComboBox<String>(elementosComboBox);
		add(comBoxEstados);
	}
	
	//------------------------------------------------------------------------//
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
	
//------------------------------------------------------------------------//
	public void crearComboBoxMunicipios() throws Exception {		
		add(new JLabel("Municipios:"));	
		comBoxMunicipios = new JComboBox<String>();
		comBoxMunicipios.addItem("Seleccionar");
		comBoxMunicipios.setEnabled(false);
		add(comBoxMunicipios);
	}
	
	//------------------------------------------------------------------------//
	public void crearComboBoxCiudades(){
		add(new JLabel("Ciudades:"));
		comBoxCiudades = new JComboBox<String>();
		comBoxCiudades.addItem("Seleccionar");
		comBoxCiudades.setEnabled(false);
		add(comBoxCiudades);
	}
	
	//------------------------------------------------------------------------//
	public void inicializarArchivos() throws FileNotFoundException {
		archivoEstados = new ArchivoEstados();
		archivoMunicipios = new ArchivoMunicipios();
		archivoCiudades = new ArchivoCiudades();
	}
	
	//------------------------------------------------------------------------//
	public void crearEventos() {
		comBoxEstados.addActionListener(this);
		comBoxMunicipios.addActionListener(this);
	}
	
	//------------------------------------------------------------------------//
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == comBoxEstados) {
				seleccionarEstado();
			} else if(e.getSource() == comBoxMunicipios){
				seleccionarMuncipio();
			} else if(e.getSource() == comBoxCiudades){
				seleccionarCiudad();
			}	
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this,"Ocurrio un error al seleccionar");
			error.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------------//
	public void cargarComboBoxMunicipios() throws Exception {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>( obtenerElmentosComboBoxMunicipios() );
		comBoxMunicipios.setModel(modelo);
		comBoxMunicipios.setEnabled(true);
	}
	
	//------------------------------------------------------------------------//
	public String[] obtenerElmentosComboBoxMunicipios() throws Exception {
		ArrayList<Municipio> listaMunicipios = archivoMunicipios.buscarMunicipios(estadoSeleccionado.getEstadoId());
		String[] elementos = new String[listaMunicipios.size()+1];
		elementos[0] = "Seleccionar";
		for(int i = 1; i < elementos.length; i++) {
			elementos[i] = listaMunicipios.get(i-1).getNombreMunicipio();
		}
		return elementos;
	}
	
	//------------------------------------------------------------------------//
	public void cargarComboBoxCiudades() throws Exception {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>( obtenerElmentosComboBoxCiudades() );
		comBoxCiudades.setModel(modelo);
		comBoxCiudades.setEnabled(true);
	}
	
	//------------------------------------------------------------------------//	
	public String[] obtenerElmentosComboBoxCiudades() throws Exception{
		ArrayList<Ciudad> listaCiudades = archivoCiudades.buscarCiudades(estadoSeleccionado.getEstadoId(),municipioSeleccionado.getMunicipioId());
		String[] elementos = new String[listaCiudades.size()+1];
		elementos[0] = "Seleccionar";
		for(int i = 1 ; i < elementos.length ; i++){
			elementos[i] = listaCiudades.get(i-1).getNombreCiudad();
		}
		return elementos;
	}
	
	public void seleccionarEstado() throws Exception {
		String nombreEstado = (String) comBoxEstados.getSelectedItem();		
		estadoSeleccionado = archivoEstados.buscarEstado(nombreEstado);
		if (estadoSeleccionado != null) {
			cargarComboBoxMunicipios();
		} else {
			JOptionPane.showMessageDialog(this,"Estado seleccionado no encontrado");
		}
	}
	
	public void seleccionarMuncipio() throws Exception {
		String nombreMunicipio = (String) comBoxMunicipios.getSelectedItem();
		municipioSeleccionado = archivoMunicipios.buscarMunicipio(estadoSeleccionado.getEstadoId(),nombreMunicipio);
		if(municipioSeleccionado != null) {
			cargarComboBoxCiudades();
		} else {
			JOptionPane.showMessageDialog(this,"Municipio seleccionado no encontrado");
		}
	}
	
	public void seleccionarCiudad() throws Exception {
		String nombreCiudad = (String) comBoxCiudades.getSelectedItem();
		ciudadSeleccionada = archivoCiudades.buscarCiudad(estadoSeleccionado.getEstadoId(),municipioSeleccionado.getMunicipioId(),nombreCiudad);
		if(municipioSeleccionado == null) {
			JOptionPane.showMessageDialog(this,"Ciudad seleccionada no encontrado");
		}
	} 
}
