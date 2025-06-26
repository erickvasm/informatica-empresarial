package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import conexionBD.Conexion;
import modelo.*;

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
	private JLabel correcter;

	//Donde se guardara el arreglo de "pacientes"
	private Conexion conReferencia;
	
	//Constructor del Panel de Crear
	public Crear(Conexion conexion) {
				
				
				this.conReferencia=conexion;
				
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
				
				correcter=new JLabel("");
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
					
						
						if(dataComprober()==0) {
							correcter.setText("<html><body>Realizando peticion a la base de datos.</body></html>");
							if(guardar()) {
								correcter.setText("Se guardó correctamente el paciente: "+nombre.getText());
								reset();
							}
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
			
			
			Statement conexion=conReferencia.TryConnection();
			if(conexion==null) {
				correcter.setText("<html><body>Error al intentar conectarse con la base de datos al tratar de guardar el paciente</body></html>");
				return false;
			}else {
				
				//Identificacion,Nombre,Apellido,Genero,Direccion,Telefono,Condicion,Dias
				
				String query="INSERT INTO pacientes VALUES('"+pacienteTemp.getIdentificacion()+"','"+pacienteTemp.getNombre()
				+"','"+pacienteTemp.getApellido()+"','"+pacienteTemp.getGenero()+"','"+pacienteTemp.getDireccion()+
				"',"+pacienteTemp.getTelefono()+",'"+pacienteTemp.getCondicion()+"',"+pacienteTemp.getDiasH()+")";
				
				boolean update=conReferencia.UpdateQueryFormer(conexion, query);
				if(update) {
					return true;
				}else {
					correcter.setText("<html><body>Error al ejecutar la agregacion a la base de datos</body></html>");
					return false;
				}
			}
		}catch(Exception e) {
			return false;
		}
	}
	
	
	public int dataComprober() {
		int general=0;
		
		if(((nombre.getText().length()<=2) || (nombre.getText().length()>20)) && (general==0)) {
			general=1;
		}
		
		
		if(((apellido.getText().length()<=2) || (apellido.getText().length()>20)) && (general==0)) {
			general=2;
		}
		
		if((identifiacion.getText().length()!=9) && (general==0)) {
			general=3;
		}
		
		if(((direccion.getText().length()<=5) || (direccion.getText().length()>145)) && (general==0)) {
			general=4;
		}
		
		
		if((telefono.getText().length()==8)) {
			try {
				int val=Integer.parseInt(telefono.getText());
			}catch(Exception e) {
				if(general==0) {
					general=5;
				}
			}
		}else {
			if(general==0) {
				general=6;
			}
		}
		
		
		if((diasH.getText().length()>0)) {
			try {
				int val=Integer.parseInt(diasH.getText());
				if((val<=0) && (general==0)) {
					general=7;
				}
			}catch(Exception e) {
				if(general==0) {
					general=8;
				}
			}
		}else {
			if(general==0) {
				general=9;
			}
		}
		
		
		if(general==0) {
			Statement conexion=conReferencia.TryConnection();
			if((conexion==null) && (general==0)) {
				general=10;
			}else {
				ArrayList<Paciente> pacientes=null;
				String query="SELECT * FROM pacientes WHERE Identificacion="+"'"+identifiacion.getText()+"'";
				pacientes=conReferencia.ResultQueryFormer(conexion,query);
				if((pacientes==null) && (general==0)) {
					general=11;
				}else {
					if((pacientes.size()!=0) && (general==0)) {
						general=12;
					}
				}
			}
		}
		
		
		switch(general) {
		
			case 1:{
				correcter.setText("<html><body>El campo nombre debera tener minimo 3 caracteres y maximo 20.</body></html>");
			}break;
		
			
			case 2:{
				correcter.setText("<html><body>El campo apellido debera tener minimo 3 caracteres y maximo 20 </body></html>");
			}break;
		
			
			case 3:{
				correcter.setText("<html><body>La identificacion debera tener 9 caracteres</body></html>");
			}break;
		
			
			case 4:{
				correcter.setText("<html><body>La direccion debera tener minimo 6 caracteres y maximo 145</body></html>");
			}break;
		
			
			case 5:{
				correcter.setText("<html><body>El campo de telefono debera tener valores numericos enteros</body></html>");
			}break;
		
			
			case 6:{
				correcter.setText("<html><body>El campo de telefono debera tener 8 caracteres</body></html>");
			}break;
		
			
			case 7:{
				correcter.setText("<html><body>El campo dias hospitalizacion debera tener valores numericos positivos</body></html>");
			}break;
		
			
			case 8:{
				correcter.setText("<html><body>El campo dias hospitalizacion debera tener valores numericos positivos</body></html>");
			}break;
		
			
			case 9:{
				correcter.setText("<html><body>Ingrese valores en el campo campo dias hospitalizacion</body></html>");
			}break;
		
			
			case 10:{
				correcter.setText("<html><body>Conexion fallida con la base de datos al tratar de comprobar la identificacion</body></html>");	
			}break;
		
			
			
			case 11:{
				correcter.setText("<html><body>Error en la ejecucion de la consulta en la base de datos al tratar de comprobar la identificacion</body></html>");		
			}break;
		
			
			
			case 12:{
				correcter.setText("<html><body>Existe un paciente con la misma indentificacion en la base de datos</body></html>");	
			}break;
		
		}
		
		return general;
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
	