package ep1_les_cristaisigueerickvasquez;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Clase vista principal Interfaz
public class Interfaz extends JFrame {
	
	/*Referencias de la Evaluacion
	 * 
	 * 1-Creacion de la estructura de datos: (clase='Empleados',Linea:18) 'Al crearse un nuevo Empleado se creara una nueva lista enlazada simple asociada a este'
	 * 		(clase='Interfaz',Linea:66) 'creacion de un arreglo de tipo Empleado'
	 * 
	 * 1-Ingreso en la estrucutura de datos:(clase='Interfaz',Metodo:Agregar()) (Empleado=Linea:1106,'Se agrega al arreglo de empleados') 
	 * (Llamada=Linea:1137,'Se usa el metodo de la lista enlazada simple .insertar()')
	 * 
	 * 2-Busqueda y presentacion de un empleado:  (clase='Interfaz',Metodo:Consultar(),Linea:977)
	 * 'Se busca un empleado en el arreglo de empleados y se muestra su informacion mediante su metodo mostrarInformacion()'
	 * 
	 * 3-Busqueda y presentacion de una llamada: (clase='Interfaz',Metodo:Consultar(),Linea:1005)
	 * 'Se obtiene una llamada mediante el metodo de la lista enlazada .buscar() despues se muestra su informacion mediante el metodo mostrarLlamada()'
	 * 
	 * 4-Borrar un empleado en especifico: (clase='Interfaz',Metodo:Eliminar(),Linea:772)
	 * 'Primero se vacia la lista de llamadas del empleado, luego se elimina este del arreglo de empleados'
	 * 
	 * 5-Borrar una llamada en especifico: (clase='Interfaz',Metodo:Eliminar(),Linea:785)
	 * 'Se elimina una llamada de la lista enlazada simple mediante su metodo .eliminarEspecifico()'
	 * 
	 * 6-Borrar toda la lista: (clase='Interfaz',Metodo:Eliminar(),Linea=762)
	 * 'Se vacia la lista enlazada simple asociada al empleado mediante el metodo .vaciarLista()'
	 * */
	
	//Atributos
	private JPanel contentPane;//Panel general
	private final int X=650,Y=700;//Dimensiones
	private Empleado empleados[]=new Empleado[8];//Arreglo de empleados donde estaran alojadas las listas
	private boolean gestion=true;//El tipo de operacion (Empleados / Llamadas)
	private String currentDelete="";//La direccion a elimianar en la opcion "Eliminar"
	private Color colorUno=new Color(204,231,239);//Color empleado
	private Color colorDos=new Color(236,218,181);//Color llamada
	
	
	
	//Componentes generales
	private DefaultTableModel modeloTabla;
	private JTable tablaEmpleado;
	private JLabel lblEmpleados;
		
	
	//Campos de la opcion agregar
	private JTextField campoUno;
	private JTextField campoDos;
	private JTextField campoTres;
	private JComboBox campoCuatro;
	private JLabel textoUno;
	private JLabel textoDos;
	private JLabel textoTres;
	private JLabel textoCuatro;
	private JLabel tituloAgregar;
	private JLabel avisoAgregar;
	private JButton addButton;

	//Componentes de la opcion Consultar
	private JTextArea textoConsultar;
	private JLabel tituloConsultar;
	private JTextField aBuscar;
	private JLabel textoBuscado;
	
	//Componentes de la opcion Eliminar
	private JButton deleteButton;
	private JButton searchDelete;
	private JLabel tituloEliminar;
	private JTextField aBuscarEliminar;
	private JLabel searchEText;
	private JLabel lblAEliminar;
	private JLabel textoEliminarUno;
	private JLabel textoEliminarDos;
	private JLabel textoEliminarTres;
	private JLabel avisoEliminar;
	private JCheckBox CheckTodo;
	private JCheckBox CheckLista;
	private JLabel lblDesplegarTodo;
	private JScrollPane scrollPane;
	private JTextArea textoTodo;
	private JLabel lblIngreseAQue;
	
	//Paneles generales
	private JPanel delete;
	private JPanel consultar;
	private JPanel agregar;
	
	//Panel ayuda
	JLabel textoAyuda;
	
	
	//Constructor de la Interfaz
	public Interfaz() {
		//Panel general
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		//Propiedades de la venta
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(X,Y);
		this.setResizable(false);
		this.setContentPane(contentPane);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
		//Añadiendo el icono
		try {
		    Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../ep1_les_cristaisigueerickvasquez/icon.png"));
		    this.setIconImage(iconoPropio);
		}catch(Exception e) {
			//Pass
		}
		
		
		//Barra Dinamica
		JPanel BarraDinamica = new MotionPanel(this);
		BarraDinamica.setLayout(null);
		BarraDinamica.setBackground(new Color(55,55,55));
		BarraDinamica.setBounds(0, 0, 650, 68);
		contentPane.add(BarraDinamica);
		
		JLabel lblPandeSa = new JLabel("PANDE S.A");
		lblPandeSa.setForeground(Color.WHITE);
		lblPandeSa.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblPandeSa.setBounds(22, 11, 199, 34);
		BarraDinamica.add(lblPandeSa);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBackground(Color.RED);
		btnSalir.setForeground(Color.BLACK);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se cerrara la ventana
				System.exit(0);
			}
		});		
		btnSalir.setBounds(549, 11, 91, 46);
		BarraDinamica.add(btnSalir);
		
		lblEmpleados = new JLabel("EMPLEADOS (0/8)");
		lblEmpleados.setBounds(22, 79, 157, 14);
		contentPane.add(lblEmpleados);
		
		
		
	
		
		
		
		
		
		
		//Panel de pestanas
		JTabbedPane panelPestanas = new JTabbedPane(JTabbedPane.TOP);
		panelPestanas.setBounds(0, 343, 650, 357);
		contentPane.add(panelPestanas);
		
		
		
		
		
		
		
		
		//Opcion de agregar (Empleado / Llamada)
		agregar = new JPanel();
		agregar.setLayout(null);
		agregar.setBackground(colorUno);
		panelPestanas.addTab("Agregar", null, agregar, null);
		
		addButton = new JButton("Agregar");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Llama al metodo agregar();
				agregar();
			}
		});
		addButton.setBounds(516, 241, 119, 42);
		agregar.add(addButton);
		
		tituloAgregar = new JLabel("AGREGAR EMPLEADO");
		tituloAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tituloAgregar.setBounds(10, 11, 235, 31);
		agregar.add(tituloAgregar);
		
		textoUno = new JLabel("Numero Empleado:");
		textoUno.setBounds(20, 53, 134, 14);
		agregar.add(textoUno);
		
		textoDos = new JLabel("Nombre:");
		textoDos.setBounds(20, 102, 134, 14);
		agregar.add(textoDos);
		
		textoTres = new JLabel("Horas Trabajadas:");
		textoTres.setBounds(20, 153, 140, 14);
		agregar.add(textoTres);
		
		textoCuatro = new JLabel("Empleado Atendedor:");
		textoCuatro.setVisible(false);
		textoCuatro.setBounds(20, 204, 134, 14);
		agregar.add(textoCuatro);
		
		avisoAgregar = new JLabel("");
		avisoAgregar.setBounds(54, 241, 391, 42);
		agregar.add(avisoAgregar);
		
		campoUno = new JTextField();
		campoUno.setBounds(180, 53, 134, 20);
		agregar.add(campoUno);
		campoUno.setColumns(10);
		
		campoDos = new JTextField();
		campoDos.setColumns(10);
		campoDos.setBounds(180, 99, 134, 20);
		agregar.add(campoDos);
		
		campoTres = new JTextField();
		campoTres.setColumns(10);
		campoTres.setBounds(180, 150, 134, 20);
		agregar.add(campoTres);
		
		campoCuatro = new JComboBox();
		campoCuatro.setVisible(false);
		campoCuatro.setBounds(180, 201, 134, 20);
		agregar.add(campoCuatro);
		
		
		
		
		
		
		
		
		
		
		//Opcion de consultar (Empleado / Llamada)
		consultar = new JPanel();
		consultar.setBackground(colorUno);
		consultar.setLayout(null);
		panelPestanas.addTab("Consultar", null, consultar, null);
		
		tituloConsultar = new JLabel("CONSULTAR EMPLEADO");
		tituloConsultar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tituloConsultar.setBounds(10, 11, 200, 29);
		consultar.add(tituloConsultar);
		
		JScrollPane scrollConsultar = new JScrollPane();
		scrollConsultar.setBounds(110, 51, 396, 223);
		consultar.add(scrollConsultar);
		
		textoConsultar = new JTextArea();
		textoConsultar.setEditable(false);
		textoConsultar.setLineWrap(true);
		scrollConsultar.setViewportView(textoConsultar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Se consulta
				consultar();
			}
		});
		btnConsultar.setBounds(397, 289, 109, 29);
		consultar.add(btnConsultar);
		
		aBuscar = new JTextField();
		aBuscar.setBounds(241, 289, 146, 28);
		consultar.add(aBuscar);
		aBuscar.setColumns(10);
		
		textoBuscado = new JLabel("Numero Empleado");
		textoBuscado.setBounds(110, 296, 121, 14);
		consultar.add(textoBuscado);
		
		
		
		
		
		
		
		
		
		
		
		
		
		//opcion de eliminar (Empleado / Llamada)
		delete = new JPanel();
		delete.setBackground(colorUno);
		delete.setLayout(null);
		panelPestanas.addTab("Eliminar", null, delete, null);
		
		deleteButton = new JButton("Eliminar");
		deleteButton.setEnabled(false);
		deleteButton.setBounds(543, 276, 92, 42);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se elimina
				Eliminar();
			}
		});
		delete.add(deleteButton);
		
		searchDelete = new JButton("Buscar");
		searchDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se muestra el (Empleado / Llamada) a eliminar
				mostrarEliminar();
			}
		});
		searchDelete.setBounds(441, 276, 92, 42);
		delete.add(searchDelete);
		
		tituloEliminar = new JLabel("ELIMINAR EMPLEADO");
		tituloEliminar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tituloEliminar.setBounds(10, 11, 245, 29);
		delete.add(tituloEliminar);
		
		aBuscarEliminar = new JTextField();
		aBuscarEliminar.setBounds(258, 283, 173, 29);
		delete.add(aBuscarEliminar);
		aBuscarEliminar.setColumns(10);
		
		searchEText = new JLabel("Numero Empleado");
		searchEText.setBounds(107, 290, 121, 14);
		delete.add(searchEText);
		
		lblAEliminar = new JLabel("A Eliminar");
		lblAEliminar.setBounds(20, 51, 120, 14);
		delete.add(lblAEliminar);
		
		textoEliminarUno = new JLabel("Numero Empleado");
		textoEliminarUno.setBounds(34, 91, 194, 14);
		delete.add(textoEliminarUno);
		
		textoEliminarDos = new JLabel("Nombre:");
		textoEliminarDos.setBounds(34, 128, 194, 14);
		delete.add(textoEliminarDos);
		
		textoEliminarTres = new JLabel("Horas Trabajadas:");
		textoEliminarTres.setBounds(34, 172, 194, 14);
		delete.add(textoEliminarTres);
		
		avisoEliminar = new JLabel("");
		avisoEliminar.setVerticalAlignment(SwingConstants.TOP);
		avisoEliminar.setBounds(258, 230, 377, 35);
		delete.add(avisoEliminar);
		
		CheckTodo = new JCheckBox("ELIMINAR TODO");
		CheckTodo.setBackground(null);
		CheckTodo.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
            	//Si esta selecionado eliminar todo
              if(CheckTodo.isSelected()) {
            	  eliminarTodo(true);
              }else {
            	  eliminarTodo(false);
              }
            }
        });
		CheckTodo.setBounds(338, 200, 162, 23);
		delete.add(CheckTodo);
		
		CheckLista = new JCheckBox("Eliminar solo la Lista de Llamadas asociada");
		CheckLista.setBackground(null);
		CheckLista.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
            	//Si la opcion eliminar lista esta seleccionada
              if(CheckLista.isSelected()) {
            	  avisoEliminar.setText("<html><body>Se eliminara solo la lista de llamadas asociada a este empleado ¿Continuar?</body><html>");
              }else {
            	  avisoEliminar.setText("¿Desea Eliminar este empleado con las llamadas asociadas?");
              }
            }
        });
		CheckLista.setBounds(338, 156, 297, 29);
		delete.add(CheckLista);

		
		
		
		
		
		
		
		
		
		
		
		
		//opcion de desplegar todo
		JPanel showAll = new JPanel();

		showAll.setLayout(null);
		panelPestanas.addTab("Desplegar todo", null, showAll, null);
		
		lblDesplegarTodo = new JLabel("DESPLEGAR TODO");
		lblDesplegarTodo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDesplegarTodo.setBounds(10, 11, 172, 14);
		showAll.add(lblDesplegarTodo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 46, 507, 252);
		showAll.add(scrollPane);
		
		textoTodo = new JTextArea();
		textoTodo.setEditable(false);
		textoTodo.setLineWrap(true);
		scrollPane.setViewportView(textoTodo);
		
		showAll.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent arg0) {
				//Si el componente se muestra llamara al metodo MostrarTodo()
				//que desplegara los empleados y sus llamadas
				mostrarTodo();
			}
		});
		
		
		
		
		
		
		
		
		
		//Este evento permite resetear las opciones cuando se cambia de opcion
		panelPestanas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setHelpText(panelPestanas.getSelectedIndex());
				resetGeneral();
			}
		});
		
		
		
		
		
		
		
		
		//Componentes del panel general
		ButtonGroup botones=new ButtonGroup();
		ActionListener listener= ActionEvent->cambiarGestion(((JRadioButton) ActionEvent.getSource()).getText());
		JScrollPane scrollTabla = new JScrollPane();
		scrollTabla.setBounds(172, 79, 452, 209);
		contentPane.add(scrollTabla);
		tablaEmpleado = new JTable();
		scrollTabla.setViewportView(tablaEmpleado);
		//Tabla superior donde se muestran los empleados
		modeloTabla=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"#","N°Empleado", "Nombre","Horas","Llamadas","Tiempo","Salario"
				}
			);
		tablaEmpleado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaEmpleado.setDefaultEditor(Object.class,null);
		tablaEmpleado.setModel(modeloTabla);
		
		
		JRadioButton radioOne = new JRadioButton("Empleados");
		radioOne.setBackground(colorUno);
		radioOne.setBounds(22, 239, 93, 23);
		contentPane.add(radioOne);
		radioOne.setSelected(true);
		radioOne.addActionListener(listener);
		botones.add(radioOne);
		
		JRadioButton radioTwo = new JRadioButton("LLamadas");
		radioTwo.setBackground(colorDos);
		radioTwo.setBounds(22, 270, 93, 23);
		contentPane.add(radioTwo);
		radioTwo.addActionListener(listener);
		botones.add(radioTwo);
		
		lblIngreseAQue = new JLabel("<html><body>Ingrese donde se enfocaran las opciones:</body></html>");
		lblIngreseAQue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIngreseAQue.setBounds(22, 142, 119, 90);
		contentPane.add(lblIngreseAQue);
		
		textoAyuda=new JLabel("");
		textoAyuda.setBounds(0,0,450,250);
		
		JPanel panelInstrucciones = new JPanel();
		panelInstrucciones.setVisible(false);
		panelInstrucciones.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInstrucciones.setBounds(117, 80, 450, 250);
		panelInstrucciones.add(textoAyuda);
		panelInstrucciones.setLayout(null);
		contentPane.add(panelInstrucciones,new Integer(1),0);
		
		JButton button = new JButton("?");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelInstrucciones.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelInstrucciones.setVisible(false);
			}
		});
		
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		button.setBounds(577, 299, 47, 37);
		contentPane.add(button);
		
		
		
		//Visibilidad de la ventana
		setHelpText(0);
		this.toFront();
		this.requestFocus();
		this.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
							//METODOS GENERALES

	//resetear general / se resetean todos los campos
	public void resetGeneral() {
		currentDelete="";//Se resetea la direccion a eliminar
		actualizarTabla();
		resetAgregar();
		cambiarAgregar(gestion);
		resetearConsultar();
		cambiarEliminar(gestion);
	}
	
	
	//actualizar la tabla con los datos de los empleados
	public void actualizarTabla() {
		modeloTabla.setRowCount(0);
		lblEmpleados.setText("EMPLEADOS (0/8)");
		int iterator=1;
		for(int cont=0;cont<empleados.length;cont++) {
			if(empleados[cont]!=null) {
				
				lblEmpleados.setText("EMPLEADOS ("+iterator+"/8)");
				
				Empleado current=empleados[cont];
				current.actualizarInformacion();
				
				modeloTabla.addRow(new String[] {
						
						Integer.toString(iterator),
						current.getNumeroEmpleado(),
						current.getNombre(),
						Integer.toString(current.getHoras()),
						Integer.toString(current.getnLlamadas()),
						Integer.toString(current.getTiempoLlamadas()),
						Double.toString(current.getSalario())
						
				});
				iterator++;
			}
		}
	}
	
	//Se resetea la opcion de desplegar todo
	public void resetMostrarTodo() {
		textoTodo.setText("");
	}
	
	
	//La opcion de mostrar todo desplegara todos los empleados y todas las llamadas
	public void mostrarTodo() {
		resetMostrarTodo();
		if(usoEmpleados()==0) {
			textoTodo.setText("No se han ingresado empleados aún.");
		}else {
			int contador=1;
			for(int cont=0;cont<empleados.length;cont++) {
				if(empleados[cont]!=null) {
					textoTodo.append("\n------------------------------------"
							+ "\nEmpleado N°:"+contador+"\n"+empleados[cont].mostrarInformacion(true));
					contador++;
				}
			}
			textoTodo.setCaretPosition(0);
		}
		
	}
	
	
	
	
	//Cambiar destino de operaciones (modo)
	public void cambiarGestion(String opcion) {
		if(opcion.equalsIgnoreCase("Empleados")) {
			gestion=true;
			cambiarAgregar(true);
			cambiarConsultar(true);
			cambiarEliminar(true);
			changeColor(true);
		}else {
			gestion=false;
			cambiarEliminar(false);
			cambiarConsultar(false);
			cambiarAgregar(false);
			changeColor(false);
		}
	}
	
	//Cambiar el color de los paneles dependiendo del modo seleccionado
	public void changeColor(boolean type) {
		if(type) {
			delete.setBackground(colorUno);
			agregar.setBackground(colorUno);
			consultar.setBackground(colorUno);
		}else {
			delete.setBackground(colorDos);
			agregar.setBackground(colorDos);
			consultar.setBackground(colorDos);
		}
	}
	
	
	//Devueleve el indice vacio en el arreglo de Empleados
	public int slotVacio() {
		int contador=-1;
		boolean condition=true;
		for(int cont=0;cont<empleados.length;cont++) {
			if((empleados[cont]==null) && (condition)) {
				contador=cont;
				condition=false;
			}
		}
		return contador;
	}
	
		
		
	//Devuelve cuantos empleados existen
	public int usoEmpleados() {
		int contador=0;
		for(int cont=0;cont<empleados.length;cont++) {
			if(empleados[cont]!=null) {
				contador++;
			}
		}
		return contador;
	}
	
	
	//Comprueba si el numero de empleado esta en uso
	public boolean comprobarUsoNumero(String numero) {
		boolean response=false;
		for(int cont=0;cont<empleados.length;cont++) {
			if(empleados[cont]!=null) {
				if(empleados[cont].getNumeroEmpleado().equalsIgnoreCase(numero)) {
					response=true;
				}
			}
		}
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
							//METODOS DE LA OPCION ELIMINAR
	
	//El metodo eliminar realiza la comprobacion de campos y otras caracteristicas para eliminar
	public void Eliminar() {
		//Si el boton de check "Eliminar todo esta seleccionado"
		if(CheckTodo.isSelected()) {
			//Eliminar todo
			if(usoEmpleados()==0) {
				//No hay empleados
				avisoEliminar.setText("No se han agregado empleados ni llamadas todavia");
			}else {
				//Elimina absolutamente todo, tanto empleados como sus llamadas
				for(int cont=0;cont<empleados.length;cont++) {
					if(empleados[cont]!=null) {
						//Se vacia la lista asociada al empleado
						empleados[cont].obtenerLlamadas().vaciaLista();
						//se elimina el empleado del arreglo empleados
						empleados[cont]=null;
					}
				}
				actualizarTabla();
				avisoEliminar.setText("Eliminado total completo");
			}
		}else {
			//Si la opcion de eliminar todo no esta seleccionada
			//Se verifica que existe una direccion valida a eliminar
			if(currentDelete.length()!=0) {
				//Si existe
				//Se determina el modo de la opcion eliminar (Empleado /LLamada)
				if(gestion) {
					//Empleado
					int index=Integer.parseInt(currentDelete);//Se obtiene el indice del empleado a eliminar
					//Si la opcion de eliminar solo la lista asociada esta seleccionada
					if(CheckLista.isSelected()) {
						empleados[index].obtenerLlamadas().vaciaLista();
						currentDelete="";
						cambiarEliminar(gestion);
						avisoEliminar.setText("Se elimino exitosamente la lista de llamadas");
						
					}else {	
						//Si no
						//Se vacia la lista asociada al empleado y se elimina
						//el empleado por su indice provisto
						empleados[index].obtenerLlamadas().vaciaLista();
						empleados[index]=null;
						currentDelete="";//Se resetea la direccion a eliminar
						cambiarEliminar(gestion);
						avisoEliminar.setText("Se elimino exitosamente el empleado");
					}
				}else {
					//Si la opcion de eliminar esta en modo Llamada
					String index[]=currentDelete.split("@");//Se divide la direccion
					int indexOf=Integer.parseInt(index[0]);//Se obtiene el indice del empleado que aloja la llamada
					String number=index[1];//Se obtiene el numero de telefono a eliminar
					//Con el indice provisto se obtiene el empleado y este a su vez
					//provee la lista de llamadas, en la cual eliminaremos una llamada
					//en especifico usando el numero provisto
					empleados[indexOf].obtenerLlamadas().eliminarEspecifico(number);
					currentDelete="";//Se resetea la direccion
					cambiarEliminar(gestion);
					avisoEliminar.setText("Se elimino exitosamente la llamada");
					
				}
				
				actualizarTabla();
			}
		}
	}
	
	
	//Se muestran datos previos antes de eliminar
	public void mostrarEliminar() {
		//Se determina el modo de la opcion (Llamada / Empleado)
		if(gestion) {
			//Se comprueba que existan empleados
			if(usoEmpleados()==0) {
				avisoEliminar.setText("No se han agregado empleados todavia");
			}else {
				//Existen empleados
				if(aBuscarEliminar.getText().length()!=6) {
					avisoEliminar.setText("<html><body>El numero de empleado debe contener 6 caracteres, ni uno menos ni uno mas</body></html>");
				}else {
					if(!comprobarUsoNumero(aBuscarEliminar.getText())) {
						avisoEliminar.setText("No se ha encontrado el empleado");
					}else {
						//El empleado a eliminar existe
						int index=-1;
						Empleado mostrar=null;
						//Se obtiene el indice del empleado a eliminar
						for(int cont=0;cont<empleados.length;cont++) {
							if((empleados[cont]!=null) && (mostrar==null)) {
								if(empleados[cont].getNumeroEmpleado().equalsIgnoreCase(aBuscarEliminar.getText())) {
									index=cont;
									mostrar=empleados[cont];
								}
							}
						}
						
						//La direccion a eliminar toma el valor del indice de ese empleado
						currentDelete=Integer.toString(index);
						
						//Se muestra su informacion previa antes de eliminar
						textoEliminarUno.setText("Numero Empleado: "+mostrar.getNumeroEmpleado());
						textoEliminarDos.setText("Nombre: "+mostrar.getNombre());
						textoEliminarTres.setText("Horas Trabajadas: "+Integer.toString(mostrar.getHoras()));
						avisoEliminar.setText("¿Desea Eliminar este empleado con las llamadas asociadas?");
						CheckLista.setEnabled(true);
						deleteButton.setEnabled(true);
					}
				}
				
				
				
			}
		}else {
			//Modo de opcion elimianar en LLamadas
			//Se comprueba la existencia de empleados
			if(usoEmpleados()==0) {
				avisoEliminar.setText("No se han econtrado empleados ni llamadas");
			}else {
				//Si existen
				
				int index=-1;
				
				//Se busca la llamada entre todos los empleados mediante el numero provisto
				for(int cont=0;cont<empleados.length;cont++) {
					if((empleados[cont]!=null) && (index==-1)) {
						if(empleados[cont].obtenerLlamadas().buscarTelefono(aBuscarEliminar.getText())) {
							index=cont;
						}
					}
				}
				
				//Si no se encontro la llamada
				if(index==-1) {
					avisoEliminar.setText("No se encontro la llamada");
				}else {
					//Se encontro
					//El valor de la direccion a eliminar toma el valor
					//del indice del empleado en donde se aloja la llamada
					//y seguido a este el numero de telefono a eliminar
					currentDelete=index+"@"+aBuscarEliminar.getText();
					
					Llamada current=empleados[index].obtenerLlamadas().buscar(aBuscarEliminar.getText());
					
					//Se muestra la informacion previa de la llamada antes de ser eliminada
					textoEliminarUno.setText("Numero Telefono: "+current.getnTelefono());
					textoEliminarDos.setText("Nombre Cliente: "+current.getNombreCliente());
					textoEliminarTres.setText("Duracion Llamada (seg): "+Integer.toString(current.getDuracion()));
					avisoEliminar.setText("¿Desea Eliminar?");
					deleteButton.setEnabled(true);
				}
			
				
			}
		}
	}
	
	
	
	
	//Metodo eliminar todo mediante el boton de Check "Eliminar Todo"
	public void eliminarTodo(boolean type) {
		//Se resetea la vista de opcion eliminar en el modo provisto
		cambiarEliminar(gestion);
		//Si el boton esta seleccionado
		if(type) {
			//Se desactivan los demas campos y queda activada el boton de Eliminar
			CheckTodo.setSelected(true);
			tituloEliminar.setText("ELIMINAR TODO");
			avisoEliminar.setText("<html><body>¿Desea eliminar todos los registros? esto incluye todos los empleados con sus respectivas llamadas.</body></html>");
			searchDelete.setEnabled(false);
			deleteButton.setEnabled(true);
			aBuscarEliminar.setEnabled(false);
		}
	}
	
	
	//Cambia el modo de opcion eliminar (Empleado / Llamada)
	public void cambiarEliminar(boolean type) {
		
		currentDelete="";//Se resetea la direccion a eliminar
		
		aBuscarEliminar.setText("");
		avisoEliminar.setText("");
		CheckTodo.setSelected(false);
		aBuscarEliminar.setEnabled(true);
		searchDelete.setEnabled(true);
		deleteButton.setEnabled(false);
		CheckLista.setEnabled(false);
		CheckLista.setVisible(false);
		CheckLista.setSelected(false);
		
		if(type) {
			tituloEliminar.setText("ELIMINAR EMPLEADO");
			CheckLista.setVisible(true);
			searchEText.setText("Numero Empleado");
			textoEliminarUno.setText("Numero Empleado:");
			textoEliminarDos.setText("Nombre:");
			textoEliminarTres.setText("Horas Trabajadas:");
		}else{
			tituloEliminar.setText("ELIMINAR LLAMADA");
			searchEText.setText("Numero LLamada");
			textoEliminarUno.setText("Numero Telefono:");
			textoEliminarDos.setText("Nombre Cliente:");
			textoEliminarTres.setText("Duracion Llamada (seg):");
		}
	}
	
	
	
					
	
	
	
	
	
	
	
	
	
	
						//METODOS DE LA OPCION CONSULTAR
	
	//Consultar una llamada o un empleado
	public void consultar() {
		//Se determinana el modo de la opcion (Empleado / Llamada)
		if(gestion) {
			//Empleado
			//Se comprueba que los campos pedidos esten bien
			if(comprobarConsultar()==0) {
				//Se crea un Empleado temporal
				Empleado buscando=null;
				//Se busca el empleado entre el arreglo de empleados
				for(int cont=0;cont<empleados.length;cont++) {
					if((empleados[cont]!=null) && (buscando==null)) {
						if(empleados[cont].getNumeroEmpleado().equalsIgnoreCase(aBuscar.getText())) {
							buscando=empleados[cont];
						}
					}
				}
				
				resetearConsultar();
				//Se ecuentra el empleado
				if(buscando==null) {
					//No
					textoConsultar.setText("No se encontro el empleado");
				}else {
					//Si
					textoConsultar.setText(buscando.mostrarInformacion(true));//Se muestra la informacion del empleado
					textoConsultar.setCaretPosition(0);
				}
			}
		}else {
			//Llamada
			//Se comprueban los campos pedidos
			if(comprobarConsultar()==0) {
				
				int employeeIndex=-1;
				Llamada buscada=null;
				//Se busca entre todos los empleados la llamada
				for(int cont=0;cont<empleados.length;cont++) {
					if((empleados[cont]!=null) && (buscada==null)) {
						//Se obtiene la llamada ('si es que existe') del empleado evaluado
						buscada=empleados[cont].obtenerLlamadas().buscar(aBuscar.getText());
						employeeIndex=cont;
					}
				}
				
				resetearConsultar();
				//Se encontro la llamada
				if(buscada==null) {
					//No
					textoConsultar.setText("No se ha encontrado la llamada");
				}else {
					//Si
					//Se muestra la informacionde la llamada y del empleado atendedor
					textoConsultar.setText("\t\tLlamada\n"+buscada.mostrarLLamada()+"\n"+empleados[employeeIndex].mostrarInformacion(false));
					textoConsultar.setCaretPosition(0);
				}
			}
		}
	}
	
	
	//Cambiar el destino de consulta (Empleado / Llamada)
	public void cambiarConsultar(boolean type) {
		resetearConsultar();
		if(type) {
			tituloConsultar.setText("CONSULTAR EMPLEADO");
			textoBuscado.setText("Numero Empleado");
		}else {
			textoBuscado.setText("Numero Telefono");
			tituloConsultar.setText("CONSULTAR LLAMADA");
		}
	}
	
	
	//Resetear los campos de la opcion consultar
	public void resetearConsultar() {
		aBuscar.setText("");
		textoConsultar.setText("");
	}
	
	
	//Comprobar que los campos pedidos en la opcion de busqueda sean correctos
	public int comprobarConsultar() {
		int general=0;
		if(gestion) {
			
			if((usoEmpleados()==0) && (general==0)) {
				general=11;
			}
			
			if((aBuscar.getText().length()!=6) && (general==0)) {
				general=12;
			}
		}else{
			if((aBuscar.getText().length()<=5) && (general==0)) {
				general=21;
			}
			
			if((usoEmpleados()==0) && (general==0)) {
				general=22;
			}
		}
		
		switch(general) {
			case 11:{
				textoConsultar.setText("No se han añadido empleados para consultar.");
			}break;
			case 12:{
				textoConsultar.setText("El campo de Numero Empleado debera ser igual a 6 caracteres, ni uno menos ni uno mas.");
			}break;
			case 22:{
				textoConsultar.setText("No se han añadido empleados que posibiliten acceder a las llamadas.");
			}break;
			case 21:{
				textoConsultar.setText("Ingrese al menos 6 caracteres en el campo de Numero Telefono");
			}break;
		}
		
		return general;
	}
	
	
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
						//METODOS DE LA OPCION AGREGAR
	
	//Agregar general, se comprueban los campos con el metodo comprobarCampos()
	//Si todo es segun lo especificado se realizara la creacion de la Llamada o del
	//Empleado
	public void agregar() {
		//Se comprueba si la opcion esta en modo agregar Llamada o Empleado
		if(gestion) {
			//Empleado
			//Se comprueba que no hay anormalidades en los campos
			if(comprobarCampos()==0) {
				//Se crea un Empleado temporal al que se le pasaran los datos
				//de los campos
				Empleado empleadoTemp=new Empleado();
				empleadoTemp.setHoras(Integer.parseInt(campoTres.getText()));
				empleadoTemp.setNombre(campoDos.getText());
				empleadoTemp.setNumeroEmpleado(campoUno.getText());
				empleados[slotVacio()]=empleadoTemp;//Se añade el empleado al arreglo de empleados[]
				resetAgregar();
				if(slotVacio()==-1) {
					activarAgregar(false);
				}
				actualizarTabla();
				avisoAgregar.setText("Se agrego el empleado exitosamente");
			}
		}else {
			//LLamada
			//Se comprueba que existan empleados a los cuales atribuirles llamadas
			if(comprobarCampos()==0) {
				//splitDni lo que hace es obtener el numero de empleado
				//al cual se le atribuye la llamada
				String splitDni[]=campoCuatro.getSelectedItem().toString().split("@");
				String dni=splitDni[0];//Numero de empleado
				
				//Se crea una llamada temporal
				Llamada tempora=new Llamada();
				tempora.setNombreCliente(campoDos.getText());
				tempora.setnTelefono(campoUno.getText());
				tempora.setDuracion(Integer.parseInt(campoTres.getText()));
				tempora.actualizarInformacion();
				boolean condition=true;
				//Se busca el empleado que se eligio mediante el Numero de Empleado
				for(int cont=0;cont<empleados.length;cont++) {
					if((empleados[cont]!=null) && (condition)) {
						if(empleados[cont].getNumeroEmpleado().equalsIgnoreCase(dni)) {
							//Este empleado brinda su lista (lista enlazada simple) de llamadas,
							//en base a esta lista se añade la llamada temporal mediante el metodo .insertar()
							//de la propia lista
							empleados[cont].obtenerLlamadas().insertar(tempora);
							condition=false;
						}
					}
				}
				resetAgregar();
				avisoAgregar.setText("Se agrego la llamada exitosamente");
				actualizarTabla();
			}
		}
	}
	
	
	
	
	
	//Cambiar campos en la opcion de agregar (Empleado / Llamada)
	public void cambiarAgregar(boolean type) {
		activarAgregar(true);
		resetAgregar();
		if(type) {
			if(slotVacio()==-1) {
				activarAgregar(false);
				avisoAgregar.setText("A alcanzado el numero maximo de empleados");
			}
			tituloAgregar.setText("AGREGAR EMPLEADO");
			textoUno.setText("Numero Empleado:");
			textoDos.setText("Nombre:");
			textoTres.setText("Horas Trabajadas");
			textoCuatro.setVisible(false);
			campoCuatro.setVisible(false);
		}else {
			tituloAgregar.setText("AGREGAR LLAMADA");
			textoUno.setText("Numero Telefono:");
			textoDos.setText("Nombre Cliente:");
			textoTres.setText("Duracion Llamada (seg)");
			textoCuatro.setVisible(true);
			campoCuatro.setVisible(true);
			if(usoEmpleados()<=1) {
				activarAgregar(false);
				avisoAgregar.setText("<html><body>Se deben añadir al menos dos empleados para agregar llamadas.</body></html>");
			}else {
				
				campoCuatro.setModel(new DefaultComboBoxModel());
				for(int cont=0;cont<empleados.length;cont++) {
					if(empleados[cont]!=null) {
						campoCuatro.addItem(empleados[cont].getNumeroEmpleado()+"@"+empleados[cont].getNombre());
						 
					}
				}
			}

		}
	}
	
	
	
	
	
	
	
	
	
	//Resetear todos los campos de agregar
	public void resetAgregar() {
		avisoAgregar.setText("");
		campoUno.setText("");
		campoDos.setText("");
		campoTres.setText("");
		campoCuatro.setModel(new DefaultComboBoxModel());
		if(usoEmpleados()!=0) {
			for(int cont=0;cont<empleados.length;cont++) {
				if(empleados[cont]!=null) {
					campoCuatro.addItem(empleados[cont].getNumeroEmpleado()+"@"+empleados[cont].getNombre());
					 
				}
			}
		}
	}
	
	
	//Activa o desactiva los componentes de llamada
	public void activarAgregar(boolean type) {
		campoUno.setEnabled(type);
		campoDos.setEnabled(type);
		campoTres.setEnabled(type);
		campoCuatro.setEnabled(type);
		addButton.setEnabled(type);
	}
	
	
	
	
	//Comprobar que el formato del numero de empleado este correcto
	public boolean comprobarNumeroEmpleado(String comprobar) {
		boolean response=false;
		try {
			//Se comprueba la longitud
			if(comprobar.length()!=6) {
				return false;
			}else {
				//Que la primera letra sea 'E' segun el formato pre-establecido
				if(!Character.toString(comprobar.charAt(0)).equalsIgnoreCase("E")) {
					return false;
				}else {
					//Se comprueba que este seguido por 5 numeros
					for(int cont=1;cont<comprobar.length();cont++) {
						Integer.parseInt(Character.toString(comprobar.charAt(cont)));
					}
				}
				return true;
			}
		}catch(Exception e) {
			response=false;
		}
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	//Comprobar campos pedidos
	public int comprobarCampos() {
		avisoAgregar.setText("");
		int general=0;
		if(gestion) {
			if((campoUno.getText().length()!=6) && (general==0)) {
				general=11;
			}
			if((campoDos.getText().length()<=2) && (general==0)){
				general=12;
			}
			if((campoTres.getText().length()==0) && (general==0)) {
				general=13;
			}
			if((campoTres.getText().length()>=1) && (general==0)) {
				try {
					int valor=Integer.parseInt(campoTres.getText());
					if(valor<=0) {
						general=14;
					}
				}catch(Exception e) {
					general=14;
				}
			}
			if((!comprobarNumeroEmpleado(campoUno.getText()))&& (general==0)){
				general=15;
			}
			if((comprobarUsoNumero(campoUno.getText())) && (general==0)) {
				general=16;
			}
		}else {
			if((campoUno.getText().length()<=5) && (general==0)) {
				general=21;
			}
			
			if((campoUno.getText().length()>5) && (general==0)) {
				boolean condition=true;
				for(int cont=0;cont<empleados.length;cont++) {
					if(empleados[cont]!=null) {
						if(empleados[cont].obtenerLlamadas().buscarTelefono(campoUno.getText())){
							condition=false;
						}
					}
				}
				if(!condition) {
					general=22;
				}
			}

			if((campoDos.getText().length()<=2) && (general==0)) {
				general=23;
			}
			
			if((campoTres.getText().length()>=0) && (general==0)) {
				try {
					int valor=Integer.parseInt(campoTres.getText());
					if(valor<=0) {
						general=24;
					}
				}catch(Exception e) {
					general=24;
				}
			}
		}
		
		switch(general) {
			case 11:{
				avisoAgregar.setText("<html><body>El numero de empleado tendra que tener 6 caracteres, no menos ni mas.</body></html>");
			}break;
			case 12:{
				avisoAgregar.setText("<html><body>Rellene el campo de nombre con al menos 3 caracteres.</body></html>");
			}break;
			case 13:{
				avisoAgregar.setText("<html><body>Rellene el campo de horas trabajadas.</body></html>");
			}break;
			case 14:{
				avisoAgregar.setText("<html><body>Rellene el campo de horas trabajadas con valores numericos positivos</body></html>");
			}break;
			case 15:{
				avisoAgregar.setText("<html><body>El formato del numero de empleado es incorrecto"
						+ ", el formato a seguir es una 'E' al principio seguido por cinco números, ejemplo: 'E00000'.</body></html>");
			}break;
			case 16:{
				avisoAgregar.setText("<html><body>El numero de empleado ya esta en uso.</body></html>");
			}break;
			case 21:{
				avisoAgregar.setText("<html><body>El numero de telefono debe tener al menos 6 caracteres.</body></html>");
			}break;
			case 22:{
				avisoAgregar.setText("<html><body>El numero de telefono ya esta en uso.</body></html>");
			}break;
			case 23:{
				avisoAgregar.setText("<html><body>El nombre del cliente debera tener al menos 3 caracteres.</body></html>");
			}break;
			case 24:{
				avisoAgregar.setText("<html><body>Ingrese la duracion de la llamada con valores numericos positivos enteros.</body></html>");
			}break;
		}
		
		return general;
	}
	
	
	
	
	
							//Metodos del Panel de ayuda
	//
	public void setHelpText(int type) {
		switch(type) {
			case 0:{
				textoAyuda.setText("<html><body>Opcion actual: Agregar<br>"
						+ "Debera elejir que desea agregar para esto puede dirigirse"
						+ " a los botones de seleccion unica debajo del texto 'Ingrese donde se enfocaran las opciones' "
						+ "encontrara los botones de 'Empleado' y 'Llamada' usted seleccionara acorde a su necesidad"
						+ ", al seleccionar usted vera como la ventana muestra diferentes campos propios de el modo seleccionado (Empleado / Llamada), "
						+ "Si usted elijio crear un Empleado, debera rellenar los campos que se le indican y tener en cuenta el formato"
						+ " del número del empleado 'E00000', si en cambio usted elijio crear una nueva Llamada debera tener en "
						+ "cuenta deben estar registrados al menos dos empleados, si esto es hecho, debera rellenar los campos solicitados"
						+ " y presionar el boton de 'Agregar'.</body></html>");
			}break;

			case 1:{
				textoAyuda.setText("<html><body>Opcion actual: Consultar<br>"
						+ "Si usted desea realizar una consulta en especifico, debera elegir que desea consultar (Llamada /Empleado), esto lo"
						+ " puede hacer usted en los botones de seleccion unica debajo del texto 'Ingrese donde se enfocaran las opciones' "
						+ ", si selecciono 'Empleados' ustede debera proporcionar un número de empleado en la barra posterior y presionar "
						+ "el boton 'Consultar', si por el contrario escogió 'Llamadas' debera proporcionar un número de telefono y seguido "
						+ "a esto presionar el boton de 'Consultar'.</body></html>");
			}break;
		
			case 2:{
				textoAyuda.setText("<html>Opcion actual: Eliminar<br>Para seleccionar que desea eliminar debera dirigirse a los botones de "
						+ "seleccion unica debajo del texto 'Ingrese donde se enfocaran las opciones' (en ambas selecciones (Empleados / Llamadas) "
						+ "se contara con un boton de check con el texto 'Eliminar Todo' si lo marca y seguido a esto presiona 'Eliminar' borrara todos "
						+ "los empleados con sus respectivas llamadas asociadas).<br>Si seleccionó:<br>"
						+ "Empleados: proporcione el número de empleado, presione el boton 'Buscar' y de al boton 'Eliminar', si desea solo eliminar la lista"
						+ " de llamadas asociadas marque 'Eliminar solo la Lista de Llamadas asociada' y presione 'Eliminar'<br>Llamadas: proporcione el número de telefono"
						+ " de la llamada, presione 'Buscar' y luego 'Eliminar'</body></html>");
			}break;
			
			case 3:{
				textoAyuda.setText("<html><body>Opcion actual: Desplegar Todo<br>"
						+ "En esta opcion automaticamente se mostraran todos los datos registrados, los empleados con sus respectivas llamadas enlazadas.</body></html>");
			}break;
		}
	}
	
	
	
	
	
	
	
	
								//Metodo MAIN
	//Metodo main para armar la ventana
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
