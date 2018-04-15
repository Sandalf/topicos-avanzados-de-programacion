package comboBusqueda;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import combos.ArchivoEstados;
import combos.Estado;

@SuppressWarnings("serial")
public class Aplicacion extends JFrame{

	private ArchivoEstados archivoEstados;
	private ComboFiltrado comboFiltrado;

	public Aplicacion() {
		CrearInterfaz();
		setVisible(true);
	}

	public void CrearInterfaz() {
		try {			
			setSize(300,300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setLayout(new GridLayout(2, 0));
			comboFiltrado = new ComboFiltrado(crearElementosEstados());
			add(comboFiltrado);
			setVisible(true);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Ocurrio un error al inicilizar");
			e.printStackTrace();
		}

	}
	
	public String[] crearElementosEstados() throws Exception {
		archivoEstados = new ArchivoEstados();
		ArrayList<Estado> listaEstados = archivoEstados.leerEstados();
		String[] elementos = new String[listaEstados.size()];
		for (int i = 0; i < elementos.length; i++) {
			elementos[i] = listaEstados.get(i).getNombreEstado();
		}
		return elementos;
	}

}
