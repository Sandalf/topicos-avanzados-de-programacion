package comboBusqueda;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboFiltrado extends JPanel implements ActionListener{
	private JComboBox combo = new JComboBox();
	public ComboFiltrado(){
		crearInterfaz();
		crearEventos();
	}

	public void crearInterfaz() {
		setLayout(new GridLayout(0,2));
		combo.setEditable(true);
		add(combo);
		
		
	}

	public void crearEventos() {
		
		
	}


	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}
