package comboBusqueda;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ComboFiltrado extends JPanel implements ActionListener, MouseListener, KeyListener, FocusListener {

	private JComboBox<String> combo;
	private JTextField textField;
	private String[] elementosCombo;
	private String[] elementosComboOriginales;
	private String parametroBusqueda = "";
	private ComboBoxEditor comboBoxEditor;
	private JPanel botonesPanel;
	private JButton btnOriginal;
	private JButton btnOrdenar;
	private boolean ordenado = false;

	public ComboFiltrado(String[] elementosCombo){
		this.elementosCombo = elementosCombo;
		this.elementosComboOriginales = elementosCombo;
		crearInterfaz();
		crearEventos();
		setVisible(true);	
	}

	public void crearInterfaz() {
		setLayout(new GridLayout(0,2));
		combo = new JComboBox<String>(elementosCombo);
		combo.setEditable(true);
		comboBoxEditor = combo.getEditor();
		textField = (JTextField) combo.getEditor().getEditorComponent();
		add(combo);	
		
		botonesPanel = new JPanel();
		botonesPanel.setLayout(new GridLayout(2,0));
		
		btnOriginal = new JButton("Ori");
		botonesPanel.add(btnOriginal);
		
		btnOrdenar = new JButton("Ord");
		botonesPanel.add(btnOrdenar);
		
		add(botonesPanel);
	}

	public void crearEventos() {
		textField.addKeyListener(this);		
		textField.addMouseListener(this);
		textField.addFocusListener(this);
		btnOriginal.addActionListener(this);	
		btnOrdenar.addActionListener(this);
		combo.addActionListener(this);
	}

	public void filtrarResultados(String parametroBusqueda) {
		DefaultComboBoxModel<String> modelo = null;
		ArrayList<String> elementosFiltrados = new ArrayList<String>();

		for(String elemento : elementosCombo) {
			if(elemento.toLowerCase().contains(parametroBusqueda.toLowerCase())) {
				elementosFiltrados.add(elemento);
			}
		}

		String[] elementos = new String[elementosFiltrados.size()];
		for (int i = 0; i < elementosFiltrados.size(); i++) {
			elementos[i] = elementosFiltrados.get(i);
		}
		
		if(ordenado) {
			Arrays.sort(elementos);
		}
		
		modelo = new DefaultComboBoxModel<String>(elementos);
		combo.setModel(modelo);
		comboBoxEditor.setItem(new String(parametroBusqueda));
	}
	
	public String borrarCaracter() {
		String cadenaOriginal = parametroBusqueda;
		String cadenaModificada = "";
		int posicionCursor = textField.getCaretPosition();
	    if (cadenaOriginal != null && cadenaOriginal.length() > 0) {
	    		for(int i = 0; i < cadenaOriginal.length(); i++) {
	    			if(i != posicionCursor) {
	    				cadenaModificada += cadenaOriginal.charAt(i);
	    			}
	    		}
	    }
	    return cadenaModificada;
	}
	
	public void cargarElementosOriginales() {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(elementosComboOriginales);
		combo.setModel(modelo);
	}
	
	public void cargarElementosOrdenados() {	
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(obtenerElementosOrdenados());
		combo.setModel(modelo);
	}
	
	public String[] obtenerElementosOrdenados() {
		int size = combo.getItemCount();
		String[] elementos = new String[size];
		if(size > 0) {
			for (int i = 0; i < size; i++) {
				elementos[i] = combo.getItemAt(i);
			}
			Arrays.sort(elementos);
			return elementos;
		} else {
			return new String[]{};
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			if(e.getSource() == btnOriginal) {
				ordenado = false;
				cargarElementosOriginales();
			} else if(e.getSource() == btnOrdenar) {
				ordenado = true;
				cargarElementosOrdenados();
			}
		} else if(e.getSource() instanceof JComboBox) {
			if(e.getSource() == combo) {
				parametroBusqueda = textField.getText();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent key) {
		String caracter = Character.toString(key.getKeyChar());
		String patron = "[a-zA-Z]";
		if(caracter.matches(patron)) {
			parametroBusqueda += caracter;
			filtrarResultados(parametroBusqueda);
			combo.showPopup();
		} else if (key.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			parametroBusqueda = borrarCaracter();
			filtrarResultados(parametroBusqueda);
			combo.showPopup();
		}	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			parametroBusqueda = textField.getText();		
	}

	@Override
	public void mousePressed(MouseEvent e) {	}

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void focusGained(FocusEvent e) {
			parametroBusqueda = textField.getText();
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

}
