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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import conexionBD.Conexion;
import modelo.Paciente;

//Panel de actualizar
public class Actualizar extends JPanel{
	
	//Componentes
	private JTextField diasHA;
	private JTextField direccionA;
	private JTextField telefonoA;
	private JTextField identificacionA;
	private JTextField apellidoA;
	private JButton actualizarBoton;
	private JTextField nombreA;
	private JComboBox generoA;
	private JComboBox condicionA;
	private JLabel aviso;
	private String id="";
	private	int cell=0;
	
	private Conexion conReferencia;
	
	//Constructor del Panel Actualizar
	public Actualizar(Conexion referencia) {
		

		this.conReferencia=referencia;
		

		//Características de los componentes
		actualizarBoton = new JButton("Actualizar");
		actualizarBoton.setEnabled(false);
		actualizarBoton.setBounds(847, 530, 112, 44);
				
		//Tabla donde se desplegaran los pacientes a actualizar
		Tabla tablaActualizar = new Tabla();
		tablaActualizar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Captar eventos en el componente Tabla
		tablaActualizar.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	if(!e.getValueIsAdjusting()) {
		    		cell=tablaActualizar.getSelectedRow();
		    		actualizarBoton.setEnabled(true);
		    		if(cell>=0) {
		    			
		    			id=(String)tablaActualizar.getValueAt(cell, 4);
		    		
		    			//System.out.println(Integer.toString((int)tablaActualizar.getValueAt(cell, 7)));
		    			//diasHA.setText("ss");
		    			diasHA.setText((String)tablaActualizar.getValueAt(cell, 7));
		    			direccionA.setText((String)tablaActualizar.getValueAt(cell, 5));
		    			telefonoA.setText((String)tablaActualizar.getValueAt(cell, 6));
		    			identificacionA.setText((String)tablaActualizar.getValueAt(cell, 4));
		    			apellidoA.setText((String)tablaActualizar.getValueAt(cell, 2));
		    			nombreA.setText((String)tablaActualizar.getValueAt(cell, 1));
		    			if(((String)tablaActualizar.getValueAt(cell, 3)).charAt(0)=='F') {
		    				generoA.setSelectedIndex(0);
		    			}else {
		    				generoA.setSelectedIndex(1);
		    			}
		    			
		    			if(((String)tablaActualizar.getValueAt(cell, 8)).equalsIgnoreCase("Estable")) {
		    				condicionA.setSelectedIndex(0);
		    			}else if(((String)tablaActualizar.getValueAt(cell, 8)).equalsIgnoreCase("Regular")) {
		    				condicionA.setSelectedIndex(1);
		    			}else {
		    				condicionA.setSelectedIndex(2);
		    			}
		    			
		    		}else {
		    			reset();
		    		}
		    	}
		    }
		});
		
		
		aviso = new JLabel("");
		aviso.setBounds(209, 511, 499, 35);
		
		/*Este evento permite que las informaciones de los campos se puedan utilizar para 
		 * actualizar los datos de la fila escogida en la Tabla
		 * */
		actualizarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dataComprober()==0) {
					aviso.setText(">Actualizando paciente...");
					if(guardar()) {
						aviso.setText(">Actualización de datos del paciente exitosa");
						reset();
						tablaActualizar.actualizarTabla(conReferencia);
					}
				}
			}});
		
		
		//Añadimiento de componentes
		JScrollPane scrollActualizar = new JScrollPane();
		scrollActualizar.setBounds(10, 66, 949, 306);
		this.add(scrollActualizar);
		scrollActualizar.setViewportView(tablaActualizar);

		
		JLabel instruccion = new JLabel(">Instrucciones: Seleccione un paciente al cual desea modificar sus datos, posterior a esto modifique los campos de abajo y de al botón de actualizar");
		instruccion.setToolTipText("");
		instruccion.setBounds(50, 11, 845, 31);
		this.add(instruccion);
		
		condicionA = new JComboBox();
		condicionA.setModel(new DefaultComboBoxModel(new String[] {"Estable", "Regular", "Intensivos"}));
		condicionA.setBounds(878, 383, 81, 29);
		this.add(condicionA);
		
		diasHA = new JTextField();
		diasHA.setBounds(24, 504, 55, 29);
		this.add(diasHA);
		diasHA.setColumns(10);
		
		direccionA = new JTextField();
		direccionA.setColumns(10);
		direccionA.setBounds(549, 383, 118, 29);
		this.add(direccionA);
		
		telefonoA = new JTextField();
		telefonoA.setColumns(10);
		telefonoA.setBounds(402, 383, 118, 29);
		this.add(telefonoA);
		
		identificacionA = new JTextField();
		identificacionA.setColumns(10);
		identificacionA.setBounds(260, 383, 118, 29);
		this.add(identificacionA);
		
		apellidoA = new JTextField();
		apellidoA.setColumns(10);
		apellidoA.setBounds(128, 383, 118, 29);
		this.add(apellidoA);
		
		nombreA = new JTextField();
		nombreA.setColumns(10);
		nombreA.setBounds(0, 383, 118, 29);
		this.add(nombreA);
		
		JLabel lblCondicion = new JLabel("Condición");
		lblCondicion.setBounds(878, 433, 81, 14);
		this.add(lblCondicion);
		
		generoA = new JComboBox();
		generoA.setModel(new DefaultComboBoxModel(new String[] {"Femenino", "Masculino"}));
		generoA.setBounds(787, 383, 81, 29);
		this.add(generoA);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(787, 433, 46, 14);
		this.add(lblGenero);
		
		JLabel diasLabel = new JLabel("Días Hospitalización");
		diasLabel.setBounds(10, 544, 143, 14);
		this.add(diasLabel);
		
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(10, 423, 46, 14);
		this.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(138, 423, 46, 14);
		this.add(apellidoLabel);
		
		JLabel idLabel = new JLabel("Identificación");
		idLabel.setBounds(270, 423, 118, 14);
		this.add(idLabel);
		
		JLabel teleLabel = new JLabel("Teléfono");
		teleLabel.setBounds(412, 423, 94, 14);
		this.add(teleLabel);
		
		JLabel direccionLabel = new JLabel("Dirección");
		direccionLabel.setBounds(559, 423, 126, 14);
		this.add(direccionLabel);
		
		this.add(actualizarBoton);
		this.setLayout(null);
		this.add(aviso);
		this.add(scrollActualizar);
		
		
		
		//Captar eventos en base a este Panel
		this.addComponentListener(new ComponentAdapter() {
			//Se oculta
			@Override
			public void componentHidden(ComponentEvent arg0) {
				reset();
				aviso.setText("");
			}
			//Se muestra
			@Override
			public void componentShown(ComponentEvent e) {
				tablaActualizar.actualizarTabla(conReferencia);
			}
		});
		
	}
	
	
	//Método para resetear los campos
	public void reset() {
		id="";
		actualizarBoton.setEnabled(false);
		diasHA.setText("");
		direccionA.setText("");
		telefonoA.setText("");
		identificacionA.setText("");
		apellidoA.setText("");
		nombreA.setText("");
	}
	
	
	
	
	
	public int dataComprober() {
		int general=0;
		
		
		if(((nombreA.getText().length()<=2) || (nombreA.getText().length()>20)) && (general==0)) {
			general=1;
		}
		
		
		if(((apellidoA.getText().length()<=2) || (apellidoA.getText().length()>20)) && (general==0)) {
			general=2;
		}
		
		
		
		if((identificacionA.getText().length()!=9) && (general==0)) {
			general=3;
		}
		
		if(((direccionA.getText().length()<=5) || (direccionA.getText().length()>145)) && (general==0)) {
			general=4;
		}
		
		
		if((telefonoA.getText().length()==8)) {
			try {
				int val=Integer.parseInt(telefonoA.getText());
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
		
		
		if((diasHA.getText().length()>0)) {
			try {
				int val=Integer.parseInt(diasHA.getText());
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
		
		
		if((general==0) && (!id.equalsIgnoreCase(identificacionA.getText()))) {
			Statement conexion=conReferencia.TryConnection();
			if((conexion==null) && (general==0)) {
				general=10;
			}else {
				ArrayList<Paciente> pacientes=null;
				String query="SELECT * FROM pacientes WHERE Identificacion="+"'"+identificacionA.getText()+"'";
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
				aviso.setText("<html><body>El campo nombre debera tener minimo 3 caracteres y maximo 20.</body></html>");
			}break;
		
			
			case 2:{
				aviso.setText("<html><body>El campo apellido debera tener minimo 3 caracteres y maximo 20 </body></html>");
			}break;
		
			
			case 3:{
				aviso.setText("<html><body>La identificacion debera tener 9 caracteres</body></html>");
			}break;
		
			
			case 4:{
				aviso.setText("<html><body>La direccion debera tener minimo 6 caracteres y maximo 145</body></html>");
			}break;
		
			
			case 5:{
				aviso.setText("<html><body>El campo de telefono debera tener valores numericos enteros</body></html>");
			}break;
		
			
			case 6:{
				aviso.setText("<html><body>El campo de telefono debera tener 8 caracteres</body></html>");
			}break;
		
			
			case 7:{
				aviso.setText("<html><body>El campo dias hospitalizacion debera tener valores numericos positivos</body></html>");
			}break;
		
			
			case 8:{
				aviso.setText("<html><body>El campo dias hospitalizacion debera tener valores numericos positivos</body></html>");
			}break;
		
			
			case 9:{
				aviso.setText("<html><body>Ingrese valores en el campo campo dias hospitalizacion</body></html>");
			}break;
		
			
			case 10:{
				aviso.setText("<html><body>Conexion fallida con la base de datos al tratar de comprobar la identificacion</body></html>");	
			}break;
		
			
			
			case 11:{
				aviso.setText("<html><body>Error en la ejecucion de la consulta en la base de datos al tratar de comprobar la identificacion</body></html>");		
			}break;
		
			
			
			case 12:{
				aviso.setText("<html><body>Existe un paciente con la misma indentificacion en la base de datos</body></html>");	
			}break;
		
		}
		
		return general;
	}
	
	
	//Método para guardar los nuevos datos mediante la obtención de filas en la tabla
	public boolean guardar() {
		try {
			
			Statement conexion=conReferencia.TryConnection();
			if(conexion==null) {
				aviso.setText("<html><body>Error al intentar conectarse con la base de datos al tratar de actualizar el paciente</body></html>");
				return false;
			}else {
				
				//Identificacion,Nombre,Apellido,Genero,Direccion,Telefono,Condicion,Dias
				
				String query="UPDATE pacientes SET Identificacion='"+identificacionA.getText()+"'"
						+ ", Nombre='"+nombreA.getText()+"', Apellido='"+apellidoA.getText()+"',Genero='"+((generoA.getSelectedItem().toString().equalsIgnoreCase("Femenino"))?"F":"M")
								+ "',Direccion='"+direccionA.getText()+"',Telefono="+(Integer.parseInt(telefonoA.getText()))+""
										+ ",Condicion='"+(condicionA.getSelectedItem().toString())+"',Dias="+(Integer.parseInt(diasHA.getText()))+" WHERE Identificacion='"+id+"'";
				boolean update=conReferencia.UpdateQueryFormer(conexion, query);
				if(update) {
					return true;
				}else {
					aviso.setText("<html><body>Error al ejecutar la actualizacion en la base de datos</body></html>");
					return false;
				}
			}
			
		}catch(Exception e) {
			return false;
		}
		
	}
	
}
