package paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import laboratorio.*;

//Panel de crear
public class Crear extends JPanel{
	
	//Componentes
	private JTextField nombre;
	private JTextField apellido;
	private JTextField identifiacion;
	private JTextField direccion;
	private JTextField diasH;
	private JTextField telefono;
	private JComboBox condicion;
	private JComboBox genero;

	//Donde se guardara el arreglo de "pacientes"
	private ArrayList<Paciente> pacient;
	
	
	//Constructor del Panel de Crear
	public Crear(ArrayList<Paciente> pacient) {
				
				//Obtención del ArrayList "pacientes"
				this.pacient=pacient;
		
				
				//Caracteristicas
				this.setBorder(new TitledBorder(null, "A\u00F1adir Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				this.setLayout(null);
				
				//Caracteristicas de los componentes
				nombre = new JTextField();
				nombre.setBounds(130, 101, 239, 25);
				this.add(nombre);
				nombre.setColumns(10);
				
				apellido = new JTextField();
				apellido.setBounds(130, 190, 239, 25);
				this.add(apellido);
				apellido.setColumns(10);
				
				identifiacion = new JTextField();
				identifiacion.setBounds(602, 102, 239, 23);
				this.add(identifiacion);
				identifiacion.setColumns(10);
				
				direccion = new JTextField();
				direccion.setBounds(130, 281, 239, 25);
				this.add(direccion);
				direccion.setColumns(10);
				
				diasH = new JTextField();
				diasH.setBounds(602, 191, 71, 23);
				this.add(diasH);
				diasH.setColumns(10);
				
				telefono = new JTextField();
				telefono.setBounds(130, 368, 239, 25);
				this.add(telefono);
				telefono.setColumns(10);
				
				condicion = new JComboBox();
				condicion.setModel(new DefaultComboBoxModel(new String[] {"Estable", "Regular", "Intensivos"}));
				condicion.setBounds(585, 370, 100, 20);
				this.add(condicion);
				
				genero = new JComboBox();
				genero.setModel(new DefaultComboBoxModel(new String[] {"Femenino", "Masculino"}));
				genero.setBounds(597, 289, 100, 20);
				this.add(genero);
				
				JButton saveButton = new JButton("Guardar");
				saveButton.setBounds(842, 530, 117, 48);
				this.add(saveButton);
				
				JButton descartarBoton = new JButton("Descartar");
				descartarBoton.setBounds(693, 530, 123, 48);
				this.add(descartarBoton);
				
				JLabel apellidoLabel = new JLabel("Apellido");
				apellidoLabel.setBounds(49, 195, 71, 14);
				this.add(apellidoLabel);
				
				JLabel nombreLabel = new JLabel("Nombre");
				nombreLabel.setBounds(49, 106, 71, 14);
				this.add(nombreLabel);
				
				JLabel idlabel = new JLabel("Identificación");
				idlabel.setBounds(426, 112, 123, 14);
				this.add(idlabel);
				
				JLabel diaslabel = new JLabel("Días Hospitalizado");
				diaslabel.setBounds(429, 201, 136, 14);
				this.add(diaslabel);
				
				JLabel genLabel = new JLabel("Genero");
				genLabel.setBounds(426, 298, 123, 14);
				this.add(genLabel);
				
				JLabel conLabel = new JLabel("Condición");
				conLabel.setBounds(426, 379, 88, 14);
				this.add(conLabel);
				
				JLabel telelabel = new JLabel("Teléfono");
				telelabel.setBounds(49, 373, 71, 14);
				this.add(telelabel);
				
				JLabel direccionlabel = new JLabel("Dirección");
				direccionlabel.setBounds(49, 286, 71, 14);
				this.add(direccionlabel);
				
				JLabel Instrucciones = new JLabel(">Instrucciones:  Proporcionar datos del paciente, y guardar con el botón \"Guardar\"");
				Instrucciones.setBounds(50, 11, 655, 31);
				this.add(Instrucciones);
				
				JLabel correcter=new JLabel("");
				correcter.setBounds(50, 500, 500, 75);
				this.add(correcter);
				
				
				//Captar eventos en base a este JPanel
				this.addComponentListener(new ComponentAdapter() {
					//Se oculta
					
					public void componentHidden(ComponentEvent e) {
						correcter.setText("");
					}
				});
				
				//Este evento permite ejecutar el método guardar()
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						if(guardar()) {
							correcter.setText("Se guardó correctamente el paciente: "+nombre.getText());
							reset();
						}else {
							correcter.setText("Ocurrió un error a la hora de guardar el paciente, verifique los campos.");
						}
						
					}
				});
				
				//Borra la información de los campos de entrada
				descartarBoton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						reset();
					}
				});
				
	}
	
	//Este método sirve para almacenar la información obtenida de los campos en el ArrayList
	//Se crea un Objeto temporal al cual se le añadirán atributos varios
	//después de esto, se almacena en el ArrayList "pacientes"

	public boolean guardar() {
		boolean status=false;
		
		try {
			Paciente pacienteTemp=new Paciente();
			
			pacienteTemp.setApellido(apellido.getText());
			pacienteTemp.setCondicion(condicion.getSelectedItem().toString());
			pacienteTemp.setDiasH(Integer.parseInt(diasH.getText()));
			pacienteTemp.setDireccion(direccion.getText());
			if(genero.getSelectedItem().toString().equalsIgnoreCase("Femenino")) {
				pacienteTemp.setGenero('F');
			}else {
				pacienteTemp.setGenero('M');
			}
			pacienteTemp.setIdentificacion(identifiacion.getText());
			pacienteTemp.setNombre(nombre.getText());
			pacienteTemp.setTelefono(telefono.getText());
			
			
			pacient.add(pacienteTemp);
			
			status=true;
		}catch(Exception e) {
			
			status=false;
		}
		
		return status;
	}
	
	//Resetear todos los campos de entrada
	public void reset() {
		nombre.setText("");
		identifiacion.setText("");
		diasH.setText("");
		direccion.setText("");
		telefono.setText("");
		apellido.setText("");
		
	}
}
	