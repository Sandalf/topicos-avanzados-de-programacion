package comboBusqueda;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ComboFiltrado extends JPanel {

	private JComboBox<String> combo;
	private JTextField textField;
	private String[] elementosCombo;
	private String[] elementosComboOriginales;
	private String parametroBusqueda = "";
	private ComboBoxEditor comboBoxEditor;
	private JPanel botonesPanel;
	private JButton btnOriginal;
	private JButton btnOrdenar;

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
		textField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent key)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{		
						String caracter = Character.toString(key.getKeyChar());
						String patron = "[a-zA-Z]";
						if(caracter.matches(patron)) {
							parametroBusqueda += caracter;
							filtrarResultados(parametroBusqueda);
							combo.showPopup();
						} else if (key.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							parametroBusqueda = borrarCaracter(parametroBusqueda);
							filtrarResultados(parametroBusqueda);
							combo.showPopup();
						}
					}
				});
			}
		});
		
		textField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
	            	SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{	
						parametroBusqueda = textField.getText();
						textField.setCaretPosition(parametroBusqueda.length());
					}
				});    		
            }
        });
	
		btnOriginal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarElementosOriginales();
			}			
		});
		
		btnOrdenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarElementosOrdenados();
			}
			
		});
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

		modelo = new DefaultComboBoxModel<String>(elementos);
		combo.setModel(modelo);
		comboBoxEditor.setItem(new String(parametroBusqueda));
	}
	
	public String borrarCaracter(String str) {
	    if (str != null && str.length() > 0) {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
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

}
