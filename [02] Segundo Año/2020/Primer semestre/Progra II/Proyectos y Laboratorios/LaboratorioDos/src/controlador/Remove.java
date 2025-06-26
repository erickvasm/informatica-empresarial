package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Persona;


//Clase para eliminar empleados
public class Remove extends JPanel{

	//Componentes
	private JLabel notes;
	private JLabel nombreLabel;
	private JLabel ideLabel;
	private JLabel apellidoLabel;
	private JButton delButton;
	private Color color=new Color(203,69,69);
	
	private int currentIndex=-1;//Indice obtenido por eliminar
	
	//Constructor
	public Remove() {
		
		//Componentes y caracteristicas
		this.setLayout(null);
		this.setBounds(10, 11, 570, 334);
		
		JLabel lblNewLabel = new JLabel("Eliminar");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 106, 32);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>\r\n<body>\r\n\tIntrucciones: seleccione un empleado en la tabla superior, luego debe presionar el boton Eliminar.\r\n</body>\r\n</html");
		lblNewLabel_1.setBounds(20, 54, 512, 39);
		this.add(lblNewLabel_1);
		
		delButton = new JButton("Eliminar");
		delButton.setEnabled(false);
	
		delButton.setBounds(445, 284, 115, 39);
		this.add(delButton);
		
		nombreLabel = new JLabel("Nombre:");
		nombreLabel.setBounds(43, 155, 187, 14);
		this.add(nombreLabel);
		
		apellidoLabel = new JLabel("Apellido:");
		apellidoLabel.setBounds(43, 190, 187, 14);
		this.add(apellidoLabel);
		
		ideLabel = new JLabel("Identificacion:");
		ideLabel.setBounds(43, 232, 187, 14);
		this.add(ideLabel);
		
		JLabel lblDetallesDelEmpleado = new JLabel("Detalles del empleado a eliminar:");
		lblDetallesDelEmpleado.setBounds(65, 120, 271, 14);
		this.add(lblDetallesDelEmpleado);
		
		notes = new JLabel("");
		notes.setVisible(false);
		notes.setBounds(65, 284, 271, 14);
		this.add(notes);
		
		
	}
	
	
	//Eliminar empleado
	public boolean deleteEmploye(LinkedList<Persona> emp) {
		boolean response=false;
		
		try {
			if(currentIndex>=0) {
		
				ListIterator<Persona> iterator=emp.listIterator();
				iterator=emp.listIterator();
				int currentLinck=0;
				Persona current;
				boolean condition=true;
				
				while((iterator.hasNext()) && (condition)) {
					current=iterator.next();
					if(currentLinck==currentIndex) {
						iterator.remove();
						condition=false;
					}
					currentLinck++;
				}
			}
			response=true;
		}catch(Exception e) {
			response=false;
		}
		
		return response;
	}
	
	//Mostrar informacion general del empleado antes de eliminarlo
	public void showEmployee(LinkedList<Persona> emp,int index) {
		obtenerAnuncio().setVisible(true);
		ListIterator<Persona> iterator=emp.listIterator();
		iterator=emp.listIterator();
		currentIndex=index;
		int currentLinck=0;
		Persona current;
		boolean condition=true;
		
		while((iterator.hasNext()) && (condition)) {
			current=iterator.next();
			if(currentLinck==index) {
				nombreLabel.setText("Nombre: "+current.getNombre());
				apellidoLabel.setText("Apellido: "+current.getApellido());
				ideLabel.setText("Identificacion: "+current.getIdentificacion());
				delButton.setEnabled(true);
				condition=false;
			}
			currentLinck++;
		}
	}
	
	//Resetear los campos
	public void reset(boolean value) {
		currentIndex=-1;
		delButton.setEnabled(false);
		if(value) {
			notes.setText("");
			notes.setVisible(false);
		}
		nombreLabel.setText("Nombre: ");
		apellidoLabel.setText("Apellido: ");
		ideLabel.setText("Identificacion: ");
		
	}
	
	
	//Obtener el boton de eliminar
	public JButton obtenerBoton() {
		return delButton;
	}
	
	//Obtener el componente de anuncios
	public JLabel obtenerAnuncio() {
		return notes;
	}
	
	//Obtener el color de marcado
	public Color getColor() {
		return color;
	}
	
	
}
