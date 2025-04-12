package controlador;

import java.awt.Font;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.Electronico;
import modelo.Funcionario;
import modelo.Libro;
import modelo.Prestamo;

//Clase para insertar un nuevo Funcionario o Prestamo
public class Insertar extends JPanel{

	//Atributos y Componentes
	private JTextField identificacion;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField edad;
	private JTextField horas;
	private JTextField funcion;
	private JTextField carnet;
	private JLabel lblHorasSemanales;
	private JLabel lblFuncion;
	private JLabel lblCarnet;
	private JTextField contacto;
	private JTextField titulo;
	private JTextField autor;
	private JTextField editorial;
	private JTextField year;
	private JTextField tema;
	private JComboBox tipoDisco;
	private JLabel lblTemaContenido;
	private JLabel lblTipoDisco;
	private JLabel addTo;
	private JLabel lblContacto;
	private JLabel lblFuncionarioAtendedor;
	private JLabel lblRecurso;
	private JComboBox funcAtendedor;
	private JRadioButton radioInOne;
	private JRadioButton radioInTwo;
	private JLabel lblTitulo;
	private JLabel lblAutor;
	private JLabel lblAo;
	private JLabel lblEditorial;
	private JTextField devolucion;
	private JLabel lblDevolucion;
	private JLabel Aviso;
	private JButton addButton;
	
	private boolean tipoGestion=true;//Se establece el modo de gestion
	private ArrayList<Funcionario> apuntaFunc;//Puntero al ArrayList central funcionarios
	private ArrayList<Cliente> apuntaPrestamo;//Puntero al ArrayList central prestamos
	
	//Constructor
	public Insertar(ArrayList<Cliente> pres,ArrayList<Funcionario> func) {
		//Se determinan los punteros
		this.apuntaFunc=func;
		this.apuntaPrestamo=pres;
		
		this.setLayout(null);
		
		//Componenentes y caracteristias
		Aviso = new JLabel("");
		Aviso.setBounds(400, 10, 300, 40);
		this.add(Aviso);
		
		
		JLabel lblIdentifiacion = new JLabel("Identificacion:");
		lblIdentifiacion.setBounds(10, 59, 97, 14);
		this.add(lblIdentifiacion);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 95, 97, 14);
		this.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 132, 97, 14);
		this.add(lblApellido);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(10, 165, 97, 14);
		this.add(lblEdad);
		
		identificacion = new JTextField();
		identificacion.setBounds(89, 56, 126, 20);
		this.add(identificacion);
		identificacion.setColumns(10);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(89, 92, 126, 20);
		this.add(nombre);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(89, 129, 126, 20);
		this.add(apellido);
		
		edad = new JTextField();
		edad.setColumns(10);
		edad.setBounds(89, 162, 126, 20);
		this.add(edad);
		
		addButton = new JButton("A\u00F1adir");
		addButton.setBounds(668, 217, 112, 35);
		this.add(addButton);
		
		addTo = new JLabel("A\u00F1adir");
		addTo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		addTo.setBounds(54, 11, 182, 14);
		this.add(addTo);
		
		lblContacto = new JLabel("Contacto:");
		lblContacto.setBounds(10, 203, 79, 14);
		this.add(lblContacto);
		
		contacto = new JTextField();
		contacto.setColumns(10);
		contacto.setBounds(89, 200, 126, 20);
		this.add(contacto);
		
		lblFuncionarioAtendedor = new JLabel("Funcionario Atendedor:");
		lblFuncionarioAtendedor.setBounds(237, 59, 139, 14);
		this.add(lblFuncionarioAtendedor);
		
		lblRecurso = new JLabel("Recurso:");
		lblRecurso.setBounds(237, 95, 79, 14);
		this.add(lblRecurso);
		
		funcAtendedor = new JComboBox();
		funcAtendedor.setBounds(371, 56, 158, 20);
		this.add(funcAtendedor);
		
		//Permite cambiar el recuso en prestamo
		 ActionListener listenerChange = actionEvent -> eventTypeChanger((JRadioButton)actionEvent.getSource());
		
		radioInOne = new JRadioButton("Libro");
		radioInOne.addActionListener(listenerChange);
		radioInOne.setSelected(true);
		radioInOne.setBounds(333, 91, 64, 23);
		this.add(radioInOne);
		
		radioInTwo = new JRadioButton("Electronico");
		radioInTwo.addActionListener(listenerChange);
		radioInTwo.setBounds(410, 91, 111, 23);
		this.add(radioInTwo);
		
		ButtonGroup tipoRecurso=new ButtonGroup();
		tipoRecurso.add(radioInOne);
		tipoRecurso.add(radioInTwo);
		
		lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(237, 132, 79, 14);
		this.add(lblTitulo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(237, 165, 79, 14);
		this.add(lblAutor);
		
		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(237, 200, 79, 14);
		this.add(lblEditorial);
		
		lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(237, 227, 79, 14);
		this.add(lblAo);
		
		titulo = new JTextField();
		titulo.setColumns(10);
		titulo.setBounds(294, 129, 126, 20);
		this.add(titulo);
		
		autor = new JTextField();
		autor.setColumns(10);
		autor.setBounds(294, 162, 126, 20);
		this.add(autor);
		
		editorial = new JTextField();
		editorial.setColumns(10);
		editorial.setBounds(294, 200, 126, 20);
		this.add(editorial);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(294, 232, 126, 20);
		this.add(year);
		
		lblDevolucion = new JLabel("Devolución:");
		lblDevolucion.setBounds(10, 232, 79, 14);
		this.add(lblDevolucion);
		
		
		devolucion = new JTextField();
		devolucion.setColumns(10);
		devolucion.setBounds(89, 232, 126, 20);
		this.add(devolucion);
		
		
		lblTipoDisco = new JLabel("Tipo Disco:");
		lblTipoDisco.setBounds(539, 59, 64, 14);
		this.add(lblTipoDisco);
		
		tipoDisco = new JComboBox();
		tipoDisco.setModel(new DefaultComboBoxModel(new String[] {"CD-Rom", "DVD"}));
		tipoDisco.setBounds(613, 56, 147, 20);
		this.add(tipoDisco);
		
		lblTemaContenido = new JLabel("Tema Contenido");
		lblTemaContenido.setBounds(534, 95, 102, 14);
		this.add(lblTemaContenido);
		
		tema = new JTextField();
		tema.setColumns(10);
		tema.setBounds(634, 92, 126, 20);
		this.add(tema);
		
		
		lblHorasSemanales = new JLabel("Horas semanales:");
		lblHorasSemanales.setBounds(251, 59, 112, 14);
		this.add(lblHorasSemanales);
		
		lblFuncion = new JLabel("Funcion:");
		lblFuncion.setBounds(251, 95, 97, 14);
		this.add(lblFuncion);
		
		lblCarnet = new JLabel("Carnet:");
		lblCarnet.setBounds(251, 132, 97, 14);
		this.add(lblCarnet);
		
		horas = new JTextField();
		horas.setColumns(10);
		horas.setBounds(356, 56, 126, 20);
		this.add(horas);
		
		funcion = new JTextField();
		funcion.setColumns(10);
		funcion.setBounds(356, 92, 126, 20);
		this.add(funcion);
		
		carnet = new JTextField();
		carnet.setColumns(10);
		carnet.setBounds(356, 129, 126, 20);
		this.add(carnet);
		
		gestionChanger(true);
	
	}
	
	
	//Guardar Prestamo o Funcionario
	public boolean guardar() {
		boolean response=false;
		try {
			//Se determina el modo de gestion
			if(tipoGestion) {
				//Modo Funcionario
				
				/*Se crea un objeto Funcionario temporal al 
				 * cual se le modificaran sus valores
				 * 
				 * */
				Funcionario tempFunc=new Funcionario();
				tempFunc.setIdentificacion(identificacion.getText());
				tempFunc.setApellido(apellido.getText());
				tempFunc.setEdad(Integer.parseInt(edad.getText()));
				tempFunc.setNombre(nombre.getText());
					
				tempFunc.setFuncion(funcion.getText());
				tempFunc.setCarnetFuncionario(carnet.getText());
				tempFunc.setHorasSemanales(Integer.parseInt(horas.getText()));
				
				//Se añade al arrayList funcionarios
				apuntaFunc.add(tempFunc);
			}else {
				//Modo Prestamo
				
				Cliente tempClient=new Cliente();
				/*Se crea un objeto Cliente temporal al 
				 * cual se le modificaran sus valores
				 * 
				 * */
				tempClient.setIdentificacion(identificacion.getText());
				tempClient.setApellido(apellido.getText());
				tempClient.setEdad(Integer.parseInt(edad.getText()));
				tempClient.setNombre(nombre.getText());
				
				tempClient.setContacto(contacto.getText());
				String textual=funcAtendedor.getSelectedItem().toString();
				String fun[]=textual.split("@");
				tempClient.setFuncAtendedor(fun[0]);
				
				
				Prestamo tempPrestamo;
				//Se determina el tipo de recurso en prestamo
				if(radioInOne.isSelected()) {
					tempPrestamo= new Libro();
					((Libro) tempPrestamo).setFechaDevolucion(devolucion.getText());
					((Libro) tempPrestamo).setAutor(autor.getText());
					((Libro) tempPrestamo).setEditorial(editorial.getText());
					((Libro) tempPrestamo).setTitulo(titulo.getText());
					((Libro) tempPrestamo).setYear(Integer.parseInt(year.getText()));
				}else {
					tempPrestamo=new Electronico();
					((Electronico) tempPrestamo).setFechaDevolucion(devolucion.getText());
					((Electronico) tempPrestamo).setTemaContenido(tema.getText());
					((Electronico) tempPrestamo).setTipoDisco(tipoDisco.getSelectedItem().toString());
				}
				tempClient.setPrestamo(tempPrestamo);//Se añade el prestamo al Cliente temporal
				//Se añade el objeto de Cliente temporal al ArrayList prestamos
				apuntaPrestamo.add(tempClient);
			}
			response=true;
		}catch(Exception e) {
			response=false;
		}
		return response;
	}
	
	//Rellenar JComboBox con los funcionarios
	public boolean fillComboBox() {
		//Se determina si existen
		if(apuntaFunc.size()<=0) {
			//No existen
			return false;
		}else {
			//Existen
			funcAtendedor.setModel(new DefaultComboBoxModel());
			for(Funcionario funcionario:apuntaFunc) {
				funcAtendedor.addItem(funcionario.getCarnetFuncionario()+"@"+funcionario.getNombre());
			}
			return true;
		}
	}
	
	//Se intercambia el modo de Gestion y con esto los componentes
	public void gestionChanger(boolean type) {
		hideAll();
		tipoGestion=type;
		if(type) {
			//Funcionario
			enabledComponents(true);
			Aviso.setText("");
			addTo.setText("A\u00F1adir Funcionario");
			horas.setVisible(true);
			funcion.setVisible(true);
			carnet.setVisible(true);
			lblHorasSemanales.setVisible(true);
			lblFuncion.setVisible(true);
			lblCarnet.setVisible(true);
		}else {
			//Prestamo
			Aviso.setText("");
			addTo.setText("A\u00F1adir Prestamo");
			lblFuncionarioAtendedor.setVisible(true);
			lblRecurso.setVisible(true);
			funcAtendedor.setVisible(true);
			lblContacto.setVisible(true);
			contacto.setVisible(true);
			radioInOne.setSelected(true);
			radioInOne.setVisible(true);
			radioInTwo.setVisible(true);
			lblDevolucion.setVisible(true);
			devolucion.setVisible(true);
			//Se verifica si hay funcionarios atendedores
			if(!fillComboBox()) {
				Aviso.setText("<html><body>Aviso: Se debe ingresar al menos un funcionario para realizar la agregacion de prestamos</body></html>");
				enabledComponents(false);
			}else {
				enabledComponents(true);
			}
			enPrestamoChanger(true);
		}
		
	}
	
	//Escucha los eventos para desplegar los campos segun el tipo de recurso seleccionado
	public void eventTypeChanger(JRadioButton radio) {
		
		if(radio.getText().equalsIgnoreCase("Libro")) {
			enPrestamoChanger(true);
		}else {
			enPrestamoChanger(false);
		}
	}
	
	//Metodo para cambiar los campos del tipo de recurso a prestar
	public void enPrestamoChanger(boolean type) {
		
		if(type) {
			//Mostrar campos de Libro
			titulo.setVisible(true);
			autor.setVisible(true);
			editorial.setVisible(true);
			year.setVisible(true);
			lblTitulo.setVisible(true);
			lblAutor.setVisible(true);
			lblAo.setVisible(true);
			lblEditorial.setVisible(true);
			//Ocultar campos de Electronico
			tipoDisco.setVisible(false);
			lblTemaContenido.setVisible(false);
			lblTipoDisco.setVisible(false);
			tema.setVisible(false);
		}else {
			//Ocultar campos de Libro
			titulo.setVisible(false);
			autor.setVisible(false);
			editorial.setVisible(false);
			year.setVisible(false);
			lblTitulo.setVisible(false);
			lblAutor.setVisible(false);
			lblAo.setVisible(false);
			lblEditorial.setVisible(false);
			//Mostrar campos de Electronico
			tipoDisco.setVisible(true);
			lblTemaContenido.setVisible(true);
			lblTipoDisco.setVisible(true);
			tema.setVisible(true);
		}
	}
	
	//Ocultar todos los componentes
	public void hideAll() {
		//Funcionario
		horas.setVisible(false);
		funcion.setVisible(false);
		carnet.setVisible(false);
		lblHorasSemanales.setVisible(false);
		lblFuncion.setVisible(false);
		lblCarnet.setVisible(false);
		
		//Prestamo
			//General
		lblFuncionarioAtendedor.setVisible(false);
		lblRecurso.setVisible(false);
		funcAtendedor.setVisible(false);
		lblContacto.setVisible(false);
		contacto.setVisible(false);
		radioInOne.setVisible(false);
		radioInTwo.setVisible(false);
		lblDevolucion.setVisible(false);
		devolucion.setVisible(false);
		
			//Recurso libro
		titulo.setVisible(false);
		autor.setVisible(false);
		editorial.setVisible(false);
		year.setVisible(false);
		lblTitulo.setVisible(false);
		lblAutor.setVisible(false);
		lblAo.setVisible(false);
		lblEditorial.setVisible(false);
		
			//Rercurso Electronico
		tipoDisco.setVisible(false);
		lblTemaContenido.setVisible(false);
		lblTipoDisco.setVisible(false);
		tema.setVisible(false);
	}
	
	//Habilitar o deshabilitar los componentes
	public void enabledComponents(boolean type) {
		nombre.setEnabled(type);
		apellido.setEnabled(type);
		identificacion.setEnabled(type);
		edad.setEnabled(type);
		horas.setEnabled(type);
		funcion.setEnabled(type);
		carnet.setEnabled(type);
		contacto.setEnabled(type);
		devolucion.setEnabled(type);
		titulo.setEnabled(type);
		autor.setEnabled(type);
		editorial.setEnabled(type);
		year.setEnabled(type);
		tema.setEnabled(type);
		funcAtendedor.setEnabled(type);
		tipoDisco.setEnabled(type);

	}
	
	//Resetea todos los componentes
	public void resetAll() {
		funcAtendedor.setModel(new DefaultComboBoxModel());
		nombre.setText("");
		apellido.setText("");
		identificacion.setText("");
		edad.setText("");
		
		
		//Funcionario
		horas.setText("");
		funcion.setText("");
		carnet.setText("");
		
		//Prestamo
			//General
		contacto.setText("");
		devolucion.setText("");
		
			//Recurso libro
		titulo.setText("");
		autor.setText("");
		editorial.setText("");
		year.setText("");

		
			//Rercurso Electronico
		tema.setText("");
		gestionChanger(tipoGestion);
	}
	
	//Obtener el aviso
	public JLabel getAviso() {
		return Aviso;
	}
	
	//Obtener el boton de añadir
	public JButton obtenerBoton() {
		return addButton;
	}
}
