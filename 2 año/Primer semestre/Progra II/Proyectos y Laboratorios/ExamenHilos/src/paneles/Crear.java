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

import examen.*;

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
	
	private JLabel referenciaHora;

	//Donde se guardara el arreglo de "pacientes"
	private ArrayList<Paciente> pacient;
	
	
	//Constructor del Panel de Crear
	public Crear(ArrayList<Paciente> pacient,JLabel referencia) {
				
				//Obtención del ArrayList "pacientes"
				this.pacient=pacient;
				this.referenciaHora=referencia;
				
				//Caracteristicas
				this.setBorder(new TitledBorder(null, "A\u00F1adir Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				this.setLayout(null);
				
				//Caracteristicas de los componentes
				nombre = new JTextField();
				nombre.setBounds(130, 60, 239, 25);
				this.add(nombre);
				nombre.setColumns(10);
				
				apellido = new JTextField();
				apellido.setBounds(130, 115, 239, 25);
				this.add(apellido);
				apellido.setColumns(10);
				
				identifiacion = new JTextField();
				identifiacion.setBounds(602, 60, 239, 23);
				this.add(identifiacion);
				identifiacion.setColumns(10);
				
				direccion = new JTextField();
				direccion.setBounds(130, 180, 239, 25);
				this.add(direccion);
				direccion.setColumns(10);
				
				diasH = new JTextField();
				diasH.setBounds(602, 115, 71, 23);
				this.add(diasH);
				diasH.setColumns(10);
				
				telefono = new JTextField();
				telefono.setBounds(130, 240, 239, 25);
				this.add(telefono);
				telefono.setColumns(10);
				
				condicion = new JComboBox();
				condicion.setModel(new DefaultComboBoxModel(new String[] {"Estable", "Regular", "Intensivos"}));
				condicion.setBounds(585, 240, 100, 20);
				this.add(condicion);
				
				genero = new JComboBox();
				genero.setModel(new DefaultComboBoxModel(new String[] {"Femenino", "Masculino"}));
				genero.setBounds(597, 180, 100, 20);
				this.add(genero);
				
				JButton saveButton = new JButton("Guardar");
				saveButton.setBounds(720, 280, 117, 48);
				this.add(saveButton);
				
				JButton descartarBoton = new JButton("Descartar");
				descartarBoton.setBounds(580, 280, 123, 48);
				this.add(descartarBoton);
				
				JLabel apellidoLabel = new JLabel("Apellido");
				apellidoLabel.setBounds(49, 115, 71, 14);
				this.add(apellidoLabel);
				
				JLabel nombreLabel = new JLabel("Nombre");
				nombreLabel.setBounds(49, 60, 71, 14);
				this.add(nombreLabel);
				
				JLabel idlabel = new JLabel("Identificación");
				idlabel.setBounds(426, 60, 123, 14);
				this.add(idlabel);
				
				JLabel diaslabel = new JLabel("Días Hospitalizado");
				diaslabel.setBounds(429, 115, 136, 14);
				this.add(diaslabel);
				
				JLabel genLabel = new JLabel("Genero");
				genLabel.setBounds(426, 180, 123, 14);
				this.add(genLabel);
				
				JLabel conLabel = new JLabel("Condición");
				conLabel.setBounds(426, 240, 88, 14);
				this.add(conLabel);
				
				JLabel telelabel = new JLabel("Teléfono");
				telelabel.setBounds(49, 240, 71, 14);
				this.add(telelabel);
				
				JLabel direccionlabel = new JLabel("Dirección");
				direccionlabel.setBounds(49, 180, 71, 14);
				this.add(direccionlabel);
				
				JLabel Instrucciones = new JLabel(">Instrucciones:  Proporcionar datos del paciente, y guardar con el botón \"Guardar\"");
				Instrucciones.setBounds(50, 11, 655, 31);
				this.add(Instrucciones);
				
				JLabel correcter=new JLabel("");
				correcter.setBounds(50, 265, 500, 75);
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
					
						boolean check[]=guardar();
						
						if((check[0]) && (check[1])) {
							correcter.setText("Se guardó correctamente el paciente: "+nombre.getText());
							reset();
						}else if((!check[0]) && (!check[1])){
							correcter.setText("A Ocurrió un error, verifique que los campos numericos esten correctos.");
						}else if((check[0]) && (!check[1])){
							correcter.setText("La identificacion del paciente ya esta en uso.");
						}else if((!check[0]) && (check[1])) {
							correcter.setText("La identificacion debera tener al menos 6 caracteres.");
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
	
	
	//Buscar coincidencias
	public boolean buscarCoincidencias(String search) {
		boolean condition=false;
		for(Paciente paciente:pacient) {
			if(paciente.getIdentificacion().equalsIgnoreCase(search)) {
				condition=true;
			}
		}
		return condition;
	}
	
	
	//Este método sirve para almacenar la información obtenida de los campos en el ArrayList
	//Se crea un Objeto temporal al cual se le añadirán atributos varios
	//después de esto, se almacena en el ArrayList "pacientes"

	public boolean[] guardar() {
		boolean status[]= {false,false};
		
		try {
			if(identifiacion.getText().length()<=5) {
				status[0]=false;
				status[1]=true;
			}else {
				
				
				if(!buscarCoincidencias(identifiacion.getText())) {
				
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
					pacienteTemp.setHoraRegistro(referenciaHora.getText());
					pacient.add(pacienteTemp);
					
					status[0]=true;
					status[1]=true;
					
				}else {
					status[0]=true;
					status[1]=false;
				}
				
								
			}
			
			
		}catch(Exception e) {
			status[0]=false;
			status[1]=false;
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
	