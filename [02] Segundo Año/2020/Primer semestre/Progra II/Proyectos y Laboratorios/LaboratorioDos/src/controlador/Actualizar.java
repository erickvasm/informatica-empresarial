package controlador;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Funcionario;
import modelo.Gerente;
import modelo.Persona;


//Clase para actualizar la informacion de los empleados
public class Actualizar extends JPanel{

	
	//Componentes
	private JTextField nombre;
	private JTextField apellido;
	private JTextField identificacion;
	private JTextField departamento;
	private JTextField horas;
	private JTextField actividades;
	private JTextField fechaAlta;
	private JLabel aviso;
	private JComboBox genero;
	private JButton update;
	private JLabel actividadesLabel;
	private Color color=new Color(91,108,255);
	
	private LinkedList<Persona> emp;//guardar la lista enlazada primaria
	private int currentIndex=-1;//Indice del empleado a actualizar
	
	//Constructor
	public Actualizar(LinkedList<Persona> emp) {
		
		
		//Componentes y caracteristicas
		this.emp=emp;
		
		this.setLayout(null);
		this.setBounds(10, 11, 570, 334);
		
		JLabel lblthis = new JLabel("Actualizar");
		lblthis.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblthis.setBounds(10, 11, 104, 36);
		this.add(lblthis);
		
		JLabel lblNewLabel = new JLabel("<html>\r\n<body>\r\nInstrucciones: seleccione un empleado de la tabla superior, posterior a esto edite los campos y cuando termine presione Actualizar\r\n</body>\r\n</html>");
		lblNewLabel.setBounds(138, 26, 403, 50);
		this.add(lblNewLabel);
		
		update = new JButton("Actualizar");
		update.setEnabled(false);
		update.setBounds(464, 293, 96, 30);
		this.add(update);
		
		aviso = new JLabel("");
		aviso.setBounds(29, 287, 389, 20);
		this.add(aviso);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 84, 63, 14);
		this.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(80, 81, 111, 20);
		this.add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(271, 78, 111, 20);
		this.add(apellido);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(201, 81, 63, 14);
		this.add(lblApellido);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(392, 81, 63, 14);
		this.add(lblGenero);
		
		genero = new JComboBox();
		genero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		genero.setBounds(456, 78, 104, 20);
		this.add(genero);
		
		identificacion = new JTextField();
		identificacion.setColumns(10);
		identificacion.setBounds(117, 124, 111, 20);
		this.add(identificacion);
		
		JLabel lblIdentificacion = new JLabel("Identificacion:");
		lblIdentificacion.setBounds(10, 127, 96, 14);
		this.add(lblIdentificacion);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(237, 127, 96, 14);
		this.add(lblDepartamento);
		
		departamento = new JTextField();
		departamento.setColumns(10);
		departamento.setBounds(344, 124, 111, 20);
		this.add(departamento);
		
		horas = new JTextField();
		horas.setColumns(10);
		horas.setBounds(127, 160, 101, 20);
		this.add(horas);
		
		JLabel lblHorasSemanales = new JLabel("Horas Semanales:");
		lblHorasSemanales.setBounds(10, 163, 111, 14);
		this.add(lblHorasSemanales);
		
		actividades = new JTextField();
		actividades.setColumns(10);
		actividades.setBounds(90, 197, 120, 20);
		this.add(actividades);
		
		actividadesLabel = new JLabel("Actividades:");
		actividadesLabel.setBounds(10, 200, 72, 14);
		this.add(actividadesLabel);
		
		JLabel label_1 = new JLabel("Fecha Alta:");
		label_1.setBounds(255, 163, 72, 14);
		this.add(label_1);
		
		fechaAlta = new JTextField();
		fechaAlta.setColumns(10);
		fechaAlta.setBounds(335, 160, 120, 20);
		this.add(fechaAlta);

		
	}
	
	
	//Actualizar la informacion
	public boolean update() {
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
						Persona nuevaPersona=null;
						if(current.getClass().getName().toString().equalsIgnoreCase("modelo.Funcionario")) {
							Funcionario tempFunc=new Funcionario();
							
							tempFunc.setAltaContrato(fechaAlta.getText());
							tempFunc.setApellido(apellido.getText());
							tempFunc.setNombre(nombre.getText());
							tempFunc.setDepartamento(departamento.getText());
							tempFunc.setGenero(((genero.getSelectedIndex()==0)?'M':'F'));
							tempFunc.setHorasTrabajadas(Integer.parseInt(horas.getText()));
							tempFunc.setCedula(identificacion.getText());
							tempFunc.calcularPago();
							nuevaPersona=tempFunc;
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
							tempGen.calcularPago();
							nuevaPersona=tempGen;
						}
						
						
						if(nuevaPersona!=null) {
							iterator.remove();
							iterator.add(nuevaPersona);
						}
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
	
	
	
	
	
	
	//Mostrar los campos del empleado para actualizar
	public void displayEmployee(int index) {
		currentIndex=index;
		ListIterator<Persona> iterator=emp.listIterator();
		iterator=emp.listIterator();
		int currentLinck=0;
		Persona current;
		boolean condition=true;
		
		while((iterator.hasNext()) && (condition)) {
			current=iterator.next();
			if(currentLinck==index) {
				nombre.setText(current.getNombre());
				apellido.setText(current.getApellido());
				identificacion.setText(current.getIdentificacion());
				fechaAlta.setText(  ((Funcionario) current).getAltaContrato()  );
				horas.setText(Integer.toString(((Funcionario) current).getHorasTrabajadas()));
				genero.setSelectedIndex((current.getGenero()=='M')?0:1);
				departamento.setText(  ((Funcionario) current).getDepartamento()  );
				
				
				
				if(!current.getClass().getName().toString().equalsIgnoreCase("modelo.Funcionario")) {
					actividades.setVisible(true);
					actividades.setText(((Gerente) current).getActividades());
					actividadesLabel.setVisible(true);
				}else {
					actividades.setVisible(false);
					actividades.setText("");
					actividadesLabel.setVisible(false);
				}
				
				update.setEnabled(true);
				
				condition=false;
			}	
			currentLinck++;
		}
	}
	
	
	
	//Resetear los campos
	public void reset(boolean parameter) {
		currentIndex=-1;
		update.setEnabled(false);
		nombre.setText("");
		apellido.setText("");
		identificacion.setText("");
		fechaAlta.setText("");
		departamento.setText("");
		horas.setText("");
		actividades.setText("");
		if(parameter) {
			aviso.setText("");
			aviso.setVisible(false);
		}
	}
	
	
	//Obtener el boton de actualizar
	public JButton obtenerBoton() {
		return update;
	}
	
	
	//Obtener aviso
	public JLabel obtenerAviso() {
		return aviso;
	}
	
	//Obtener color de marcado
	public Color getColor() {
		return color;
	}
	
}
