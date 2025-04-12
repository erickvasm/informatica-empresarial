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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import laboratorio.Paciente;;

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
	private	int cell=0;
	
	//ArrayList pacientes
	private ArrayList<Paciente> workingList;
	
	//Constructor del Panel Actualizar
	public Actualizar(ArrayList<Paciente> workingLister) {
		
		//Conseguir el ArrayList "pacientes"
		this.workingList=workingLister;
		

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
		    			diasHA.setText(Integer.toString(workingList.get(cell).getDiasH()));
		    			direccionA.setText(workingList.get(cell).getDireccion());
		    			telefonoA.setText(workingList.get(cell).getTelefono());
		    			identificacionA.setText(workingList.get(cell).getIdentificacion());
		    			apellidoA.setText(workingList.get(cell).getApellido());
		    			nombreA.setText(workingList.get(cell).getNombre());
		    			if(workingList.get(cell).getGenero()=='F') {
		    				generoA.setSelectedIndex(0);
		    			}else {
		    				generoA.setSelectedIndex(1);
		    			}
		    			
		    			if(workingList.get(cell).getCondicion()=="Estable") {
		    				condicionA.setSelectedIndex(0);
		    			}else if(workingList.get(cell).getCondicion()=="Regular") {
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
		
		
		JLabel aviso = new JLabel("");
		aviso.setBounds(209, 511, 499, 35);
		
		/*Este evento permite que las informaciones de los campos se puedan utilizar para 
		 * actualizar los datos de la fila escogida en la Tabla
		 * */
		actualizarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(guardar(cell)) {
					aviso.setText(">Actualización de datos del paciente "+(cell+1)+" exitosa");
					actualizarBoton.setEnabled(false);
					reset();
					tablaActualizar.actualizarTabla(workingList);
				}else {
					aviso.setText(">Ocurrió un problema a la hora de actualizar, verifique los campos");
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
				tablaActualizar.actualizarTabla(workingList);
			}
		});
		
	}
	
	
	//Método para resetear los campos
	public void reset() {
		diasHA.setText("");
		direccionA.setText("");
		telefonoA.setText("");
		identificacionA.setText("");
		apellidoA.setText("");
		nombreA.setText("");
	}
	
	
	//Método para guardar los nuevos datos mediante la obtención de filas en la tabla
	public boolean guardar(int celda) {
		boolean response=false;
		
		try {
			
			Paciente pacienteTemp=new Paciente();
			
			pacienteTemp.setApellido(apellidoA.getText());
			pacienteTemp.setCondicion(condicionA.getSelectedItem().toString());
			pacienteTemp.setDiasH(Integer.parseInt(diasHA.getText()));
			pacienteTemp.setDireccion(direccionA.getText());
			if(generoA.getSelectedItem().toString().equalsIgnoreCase("Femenino")) {
				pacienteTemp.setGenero('F');
			}else {
				pacienteTemp.setGenero('M');
			}
			pacienteTemp.setIdentificacion(identificacionA.getText());
			pacienteTemp.setNombre(nombreA.getText());
			pacienteTemp.setTelefono(telefonoA.getText());
			
			
			workingList.set(celda, pacienteTemp);
			
			response=true;
		}catch(Exception e) {
			response=false;
		}
		
		return response;
	}
	
}
