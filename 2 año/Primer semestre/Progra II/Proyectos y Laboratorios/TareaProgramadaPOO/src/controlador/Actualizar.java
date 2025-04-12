package controlador;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.Electronico;
import modelo.Funcionario;
import modelo.Libro;
import modelo.Prestamo;

//Clase para actualizar informacion de los prestamos o funcionarios
public class Actualizar extends JPanel{

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
		private JComboBox funcAtendedor;
		private JLabel lblTitulo;
		private JLabel lblAutor;
		private JLabel lblAo;
		private JLabel lblEditorial;
		private JTextField devolucion;
		private JLabel lblDevolucion;
		private JLabel Aviso;
		private JButton addButton;
		
		private boolean tipoGestion=true;//Nos indica si la gestion esta en modoo de Prestamos o Funcionarios
		private int providedIndex=-1;//Aqui se guardara el indice del objeto provisto
		private ArrayList<Funcionario> apuntaFunc;//Puntero al ArrayList central funcionarios
		private ArrayList<Cliente> apuntaPrestamo;//Puntero al ArrayList central prestamos
		
	
	//Constructor
	public Actualizar(ArrayList<Cliente> pres,ArrayList<Funcionario> func) {
		//Le damos valores a los punteros
		this.apuntaFunc=func;
		this.apuntaPrestamo=pres;
		
		//Propiedaes y componentes
		this.setLayout(null);
		
		Aviso = new JLabel("");
		Aviso.setBounds(250, 10, 530, 40);
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
		
		addButton = new JButton("Actualizar");
		addButton.setEnabled(false);
		addButton.setBounds(668, 217, 112, 35);
		this.add(addButton);
		
		addTo = new JLabel("Actualizar");
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
		
		funcAtendedor = new JComboBox();
		funcAtendedor.setBounds(371, 56, 158, 20);
		this.add(funcAtendedor);
		
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
		
		//Se cambia el modo de gestion a Funcionarios
		optionChanger(true);
	}
	
	//Método para Actualizar la informacion editada
	public boolean Actualizar() {
		boolean response=false;
		try {
			//Se verifica el modo de gestion
			if(tipoGestion) {
				//Funcionarios
				Funcionario tempFunc=new Funcionario();
				tempFunc.setIdentificacion(identificacion.getText());
				tempFunc.setApellido(apellido.getText());
				tempFunc.setEdad(Integer.parseInt(edad.getText()));
				tempFunc.setNombre(nombre.getText());
					
				tempFunc.setFuncion(funcion.getText());
				tempFunc.setCarnetFuncionario(carnet.getText());
				tempFunc.setHorasSemanales(Integer.parseInt(horas.getText()));
				//Se sustituye en el arrayList funcionarios con el indice provisto
				apuntaFunc.set(providedIndex, tempFunc);
			}else {
				//Prestamos
				Cliente tempClient=new Cliente();
				
				tempClient.setIdentificacion(identificacion.getText());
				tempClient.setApellido(apellido.getText());
				tempClient.setEdad(Integer.parseInt(edad.getText()));
				tempClient.setNombre(nombre.getText());
				
				tempClient.setContacto(contacto.getText());
				String textual=funcAtendedor.getSelectedItem().toString();
				String fun[]=textual.split("@");
				tempClient.setFuncAtendedor(fun[0]);
				Prestamo tempPrestamo;
				
				//Se determina si el objeto prestamo del cliente es de tipo Libro o Electronico
				if(apuntaPrestamo.get(providedIndex).getPrestamo().getTipo().equalsIgnoreCase("Libro")) {
					//Tipo Libro
					tempPrestamo= new Libro();
					((Libro) tempPrestamo).setFechaDevolucion(devolucion.getText());
					((Libro) tempPrestamo).setAutor(autor.getText());
					((Libro) tempPrestamo).setEditorial(editorial.getText());
					((Libro) tempPrestamo).setTitulo(titulo.getText());
					((Libro) tempPrestamo).setYear(Integer.parseInt(year.getText()));
				}else {
					//Tipo Electronico
					tempPrestamo=new Electronico();
					((Electronico) tempPrestamo).setFechaDevolucion(devolucion.getText());
					((Electronico) tempPrestamo).setTemaContenido(tema.getText());
					((Electronico) tempPrestamo).setTipoDisco(tipoDisco.getSelectedItem().toString());
				}
				tempClient.setPrestamo(tempPrestamo);
				//Se sustituye en el arrayList prestamos con el indice provisto
				apuntaPrestamo.set(providedIndex, tempClient);
			}
			response=true;
		}catch(Exception e) {
			response=false;
		}
		return response;
	}

	
	
	
	
	
	//Despliega la informacion obtenida del indice provisto dependiendo del modo de gestion en los campos
	public void catchIndex(int index,boolean type) {
		providedIndex=index;//Indicamos el indice dado
		if(type) {
			//Habilitamos la edicion de campos y rellenamos con la informacion del funcionario
			enabledComponents(true);
			Funcionario tempFunc=apuntaFunc.get(index);
			nombre.setText(tempFunc.getNombre());
			apellido.setText(tempFunc.getApellido());
			identificacion.setText(tempFunc.getIdentificacion());
			edad.setText(Integer.toString(tempFunc.getEdad()));
			horas.setText(Integer.toString(tempFunc.getHorasSemanales()));
			funcion.setText(tempFunc.getFuncion());
			carnet.setText(tempFunc.getCarnetFuncionario());
		}else {
			//Habilitamos la edicion de campos y rellenamos con la informacion del Cliente
			Cliente tempCl=apuntaPrestamo.get(index);
			nombre.setText(tempCl.getNombre());
			apellido.setText(tempCl.getApellido());
			identificacion.setText(tempCl.getIdentificacion());
			edad.setText(Integer.toString(tempCl.getEdad()));
			devolucion.setText(tempCl.getPrestamo().getFechaDevolucion());
			contacto.setText(tempCl.getContacto());
				
			//Se determina si existen funcionarios para poder realizar la actualizacion de datos
			if(apuntaFunc.size()<=0) {
				//No existen, se desabilitan los componentes
				enabledComponents(false);
			}else {
				//Existe, se habilita la edicion de campos
				fillComboBox(tempCl.getFuncAtendedor());//La opcion de funcionario atendedor se autorellena
				enabledComponents(true);
			}
			
			//Se determina el tipo de recurso prestado
			if(tempCl.getPrestamo().getTipo().equalsIgnoreCase("Libro")) {
				//Libro
				titulo.setText(((Libro)tempCl.getPrestamo()).getTitulo());
				autor.setText(((Libro)tempCl.getPrestamo()).getAutor());
				editorial.setText(((Libro)tempCl.getPrestamo()).getEditorial());
				year.setText(Integer.toString(((Libro)tempCl.getPrestamo()).getYear()));;
				//Se muestran los campos de libros
				enPrestamoChanger(true);
			}else {
				//Tipo Electronico
				if(((Electronico)tempCl.getPrestamo()).getTipoDisco().equalsIgnoreCase("DVD")) {
					tipoDisco.setSelectedIndex(1);
				}else {
					tipoDisco.setSelectedIndex(0);
				}
				tema.setText(((Electronico)tempCl.getPrestamo()).getTemaContenido());
				//Se muestran los campos de Electronico
				enPrestamoChanger(false);
			}
		}
	}
	
	
	
	//Permite intercambiar entre vistas de el recurso prestado al cliente (Libro/Electronico)
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
	
	//Permite cambiar el modo de gestion a Funcionario o Prestamo
	public void optionChanger(boolean type) {
		tipoGestion=type;
		hideAll();
		resetAll();
		enabledComponents(false);
		if(type) {
			
			addTo.setText("Actualizar Funcionario");
			Aviso.setText("");
			horas.setVisible(true);
			funcion.setVisible(true);
			carnet.setVisible(true);
			lblHorasSemanales.setVisible(true);
			lblFuncion.setVisible(true);
			lblCarnet.setVisible(true);
		}else {
			//Prestamo
			Aviso.setText("");
			addTo.setText("Actualizar Prestamo");
			lblFuncionarioAtendedor.setVisible(true);
			funcAtendedor.setVisible(true);
			lblContacto.setVisible(true);
			contacto.setVisible(true);
			lblDevolucion.setVisible(true);
			devolucion.setVisible(true);
			if(apuntaFunc.size()<=0) {
				Aviso.setText("<html><body>Aviso: No se han encontrado funcionarios, para poder actualizar la informacion</body></html>");
			}
			enPrestamoChanger(true);
			
		}
	}
	
	//Permite activar o desactivar los componentes
	public void enabledComponents(boolean type) {
		tipoDisco.setEnabled(type);
		funcAtendedor.setEnabled(type);
		
		addButton.setEnabled(type);
		
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

	}

	
	//Auto rellena la opcion funcionario atendedor pertinente a la seccion de gestion de prestamos
	public boolean fillComboBox(String carnet) {
		//Se verifica si existen funcionarios
		if(apuntaFunc.size()<=0) {
			//No existen
			return false;
		}else {
			//Existen
			int index=-1;//Se guardara el indice del 
			int current=0;//Poscion actual en el foreach
			funcAtendedor.setModel(new DefaultComboBoxModel());//Se resetean las opciones de funcionario atendedor
			//Se añaden opciones a funcionario atendedor
			for(Funcionario funcionario:apuntaFunc) {
				funcAtendedor.addItem(funcionario.getCarnetFuncionario()+"@"+funcionario.getNombre());
				//Se buscar el funcionario que realizo el prestamo
				if(funcionario.getCarnetFuncionario().equalsIgnoreCase(carnet)) {
					//Se encuentra se establece su indice
					index=current;
				}
				current++;
				
			}
			
			//Si el indice no corresponde a ningun funcionario
			if(index<0) {
				Aviso.setText("No se encontro el funcionario con el que se realizo el prestamo"
						+ " porfavor actualize los datos");
			}else {
				//Si corresponde
				Aviso.setText("");
				funcAtendedor.setSelectedIndex(index);
			}
			
			return true;
		}
	}
	
	
	//Se resetean los componentes y el indice provisto
	public void resetAll() {
		providedIndex=-1;
		Aviso.setText("");
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
	}

	//Se esconden todos los elementos
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
		funcAtendedor.setVisible(false);
		lblContacto.setVisible(false);
		contacto.setVisible(false);
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
	
	
	//Se obtiene el boton de Actualizars
	public JButton obtenerBoton() {
		return addButton;
	}
	
	
	//Se obtiene el aviso
	public JLabel obtenerAviso() {
		return Aviso;
	}
}