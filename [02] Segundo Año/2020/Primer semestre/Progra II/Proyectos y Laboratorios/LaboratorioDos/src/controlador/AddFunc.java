package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.*;


//Clase para agregar un nuevo empleado a la lista enlazada
public class AddFunc extends JPanel{

	//Componentes
	private JTextField nombre;
	private JTextField apellido;
	private JTextField identificacion;
	private JTextField fechaAlta;
	private JTextField departamento;
	private JTextField horas;
	private JTextField actividades;
	private JLabel activitiesGen;
	private JLabel notes;
	private JComboBox comboBox_1;
	private JComboBox genero;
	private JButton createButton;
	private Color color=new Color(108,191,133);
	
	//Constructor
	public AddFunc() {
		
		//Componentes y caracteristicas
		this.setLayout(null);
		this.setBounds(10, 11, 570, 334);
		
		JLabel lblNewLabel = new JLabel("A\u00F1adir");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 141, 19);
		this.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 71, 55, 14);
		this.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(85, 68, 109, 20);
		this.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(213, 68, 55, 14);
		this.add(lblApellido);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(278, 65, 109, 20);
		this.add(apellido);
		
		JLabel lblIdentificacion = new JLabel("Identificacion:");
		lblIdentificacion.setBounds(20, 118, 91, 14);
		this.add(lblIdentificacion);
		
		identificacion = new JTextField();
		identificacion.setColumns(10);
		identificacion.setBounds(121, 115, 109, 20);
		this.add(identificacion);
		
		JLabel lblNewLabel_1 = new JLabel("<html>\r\n<body>\r\nInstrucciones: rellene todos los campos con la informacion del empleado, luego presione Guardar\r\n</body>\r\n</html>");
		lblNewLabel_1.setBounds(184, 17, 356, 28);
		this.add(lblNewLabel_1);
		
		genero = new JComboBox();
		genero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		genero.setBounds(469, 68, 91, 20);
		this.add(genero);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(411, 68, 55, 14);
		this.add(lblGenero);
		
		ButtonGroup botonesRadio=new ButtonGroup();
		
		JLabel lblTipoEmpleado = new JLabel("Tipo Empleado:");
		lblTipoEmpleado.setBounds(269, 118, 90, 14);
		this.add(lblTipoEmpleado);
		
		comboBox_1 = new JComboBox();
		
		
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Funcionario", "Gerente"}));
		comboBox_1.setBounds(369, 115, 141, 20);
		
		
		//Campos varian segun el tipo de empleado
		comboBox_1.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        former(comboBox_1.getSelectedIndex());
		    }
		});
		
		
		this.add(comboBox_1);
		
		JLabel lblFechaAltaContrato = new JLabel("Fecha Alta Contrato:");
		lblFechaAltaContrato.setBounds(20, 154, 131, 14);
		this.add(lblFechaAltaContrato);
		
		fechaAlta = new JTextField();
		fechaAlta.setColumns(10);
		fechaAlta.setBounds(141, 151, 109, 20);
		this.add(fechaAlta);
		
		departamento = new JTextField();
		departamento.setColumns(10);
		departamento.setBounds(379, 151, 109, 20);
		this.add(departamento);
		
		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(267, 154, 102, 14);
		this.add(lblDepartamento);
		
		JLabel lblHorasSemanales = new JLabel("Horas Semanales:");
		lblHorasSemanales.setBounds(20, 193, 116, 14);
		this.add(lblHorasSemanales);
		
		horas = new JTextField();
		horas.setColumns(10);
		horas.setBounds(141, 190, 55, 20);
		this.add(horas);
		
		activitiesGen = new JLabel("Actividades Gerenciales: ");
		activitiesGen.setVisible(false);
		activitiesGen.setBounds(213, 193, 146, 14);
		this.add(activitiesGen);
		
		actividades = new JTextField();
		actividades.setVisible(false);
		actividades.setBounds(380, 190, 131, 20);
		this.add(actividades);
		actividades.setColumns(10);
		
		createButton = new JButton("A\u00F1adir");
	
		createButton.setBounds(458, 287, 102, 36);
		this.add(createButton);
		
		//Resetea los campos
		JButton clearButton = new JButton("Limpiar");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(true);
			}
		});
		clearButton.setBounds(335, 287, 102, 36);
		this.add(clearButton);
		
		notes = new JLabel();
		notes.setVisible(false);
		notes.setBounds(10, 269, 240, 43);
		this.add(notes);
		
		
	}

	//Método para almacenar empleados
	public boolean save(LinkedList<Persona> empleado) {
		boolean response=false;
		
		
		try {
			//Se determina el tipo de empleado
			if(comboBox_1.getSelectedIndex()==0) {
				Funcionario tempFunc=new Funcionario();
				
				tempFunc.setAltaContrato(fechaAlta.getText());
				tempFunc.setApellido(apellido.getText());
				tempFunc.setNombre(nombre.getText());
				tempFunc.setDepartamento(departamento.getText());
				tempFunc.setGenero(((genero.getSelectedIndex()==0)?'M':'F'));
				tempFunc.setHorasTrabajadas(Integer.parseInt(horas.getText()));
				tempFunc.setCedula(identificacion.getText());
				tempFunc.calcularPago();
				empleado.addFirst(tempFunc);
			}else {
				Gerente tempGen=new Gerente();
				tempGen.setAltaContrato(fechaAlta.getText());
				tempGen.setApellido(apellido.getText());
				tempGen.setNombre(nombre.getText());
				tempGen.setDepartamento(departamento.getText());
				tempGen.setGenero(((genero.getSelectedIndex()==0)?'M':'F'));
				tempGen.setHorasTrabajadas(Integer.parseInt(horas.getText()));
				tempGen.setActividades(actividades.getText());
				tempGen.setCedula(identificacion.getText());
				empleado.addFirst(tempGen);
				tempGen.calcularPago();
			}
			
			response=true;
		}catch(Exception e) {
			response=false;
		}
		
		notes.setVisible(true);
		if(response) {
			reset(false);
			notes.setText("Se ha añadido exitosamente");
		}else{
			notes.setText("Verifique los campos");
		}
		
		return response;
	}
	
	//Método para resetear los campos
	public void reset(boolean parameter) {
		nombre.setText("");
		apellido.setText("");
		identificacion.setText("");
		fechaAlta.setText("");
		departamento.setText("");
		horas.setText("");
		actividades.setText("");
		if(parameter) {
			notes.setText("");
			notes.setVisible(false);
		}
	}
	
	
	//Dependiendo del tipo de empleado, varia los campos a la hora de crear.
	public void former(int type) {
		if(type==0) {
			activitiesGen.setVisible(false);
			actividades.setVisible(false);
		}else {
			activitiesGen.setVisible(true);
			actividades.setVisible(true);
		}
	}
	
	
	//Obtenemos el boton de guardar
	public JButton obtenerGuardar() {
		return createButton;
	}
	
	
	public Color getColor() {
		return color;
	}
	
}
