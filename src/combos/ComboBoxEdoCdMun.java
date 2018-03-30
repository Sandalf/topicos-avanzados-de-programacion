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
	
	private ArchivoEstados archivoEstados;
	private ArchivoMunicipios archivoMunicipios;
	
	private Estado estadoSeleccionado = new Estado();
	private Municipio municipioSeleccionado = new Municipio();

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
		comBoxMunicipios = new JComboBox<String>();
		comBoxMunicipios.addItem("Seleccionar");
		comBoxMunicipios.setEnabled(false);
		add(comBoxMunicipios);
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
		try {
			if (e.getSource() == comBoxEstados) {
				String nombreEstado = (String) comBoxEstados.getSelectedItem();		
				estadoSeleccionado = archivoEstados.buscarEstado(nombreEstado);
				if (estadoSeleccionado != null) {
					cargarComboBoxMunicipios();
				} else {
					JOptionPane.showMessageDialog(this,"Estado seleccionado no encontrado");
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this,"Ocurrio un error");
			e1.printStackTrace();
		}
	}
	
	public void cargarComboBoxMunicipios() throws Exception {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>( obtenerElmentosComboBoxMunicipios() );
		comBoxMunicipios.setModel(modelo);
		comBoxMunicipios.setEnabled(true);
	}
	
	public String[] obtenerElmentosComboBoxMunicipios() throws Exception {
		ArrayList<Municipio> listaMunicipios = archivoMunicipios.buscarMunicipios(estadoSeleccionado.getEstadoId());
		String[] elementos = new String[listaMunicipios.size()+1];
		elementos[0] = "Seleccionar";
		for(int i = 1; i < elementos.length; i++) {
			elementos[i] = listaMunicipios.get(i-1).getNombreMunicipio();
		}
		return elementos;
	}
}
