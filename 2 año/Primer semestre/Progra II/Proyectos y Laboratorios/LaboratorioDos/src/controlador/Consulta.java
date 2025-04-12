package controlador;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Persona;

//Clase donde se realizan consultas de empleados
public class Consulta extends JPanel{

	//Componentes
	private JTextField textField;
	private JButton searchButton;
	private JTextArea searchText;
	
	private Color color=new Color(205,225,139);
	
	
	//Constructor
	public Consulta() {
		
		//Componentes y caracteristicas
		this.setLayout(null);
		this.setBounds(10, 11, 570, 334);
		
		JLabel lblNewLabel = new JLabel("Consulta");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 163, 40);
		this.add(lblNewLabel);
		

		searchText = new JTextArea();
		searchText.setEditable(false);
		searchText.setLineWrap(true);
		
		JScrollPane scrollinfo = new JScrollPane(searchText);
		scrollinfo.setBounds(69, 62, 422, 220);
		this.add(scrollinfo);
		
		
		JLabel lblNewLabel_1 = new JLabel("<html>\r\n<body>\r\nInstrucciones: digite la identificación del empleado a buscar y despues presione el boton de Buscar\r\n</body>\r\n</html>");
		lblNewLabel_1.setBounds(158, 27, 402, 31);
		this.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(177, 286, 213, 31);
		this.add(textField);
		textField.setColumns(10);
		
		searchButton = new JButton("Buscar");
		searchButton.setBounds(402, 286, 89, 30);
		this.add(searchButton);
		
		JLabel lblIndentificacion = new JLabel("Indentificación:");
		lblIndentificacion.setBounds(69, 293, 104, 14);
		this.add(lblIndentificacion);
		
		
	}
	
	//Se busca el empleado en la lista enlazada principal 
	public void searchEmployee(LinkedList<Persona> emp) {
		Persona founded=null;
		if(emp.size()>0) {
			if(textField.getText().length()<3) {
				searchText.setText("Inserte una identificación valida.");
			}else {
				for(Persona persona:emp) {
					if(persona.getIdentificacion().equalsIgnoreCase(textField.getText())) {
						founded=persona;
					}
				}
				
				if(founded==null) {
					searchText.setText("No se ha encontrado al empleado.");
				}else {
					searchText.setText(founded.mostrarInformacion());
					textField.setText("");
				}
				
			}
		}else {
			searchText.setText("No han sido ingresados empleados.");
		}
	}
	
	
	//Obtenemos el boton de busqueda
	public JButton obtenerSearch() {
		return searchButton;
	}
	
	
	//Reseteamos todos los componentes
	public void reset() {
		textField.setText("");
		searchText.setText("");
	}
	
	//Obtener el color de marcado
	public Color getColor() {
		return color;
	}
	
}
