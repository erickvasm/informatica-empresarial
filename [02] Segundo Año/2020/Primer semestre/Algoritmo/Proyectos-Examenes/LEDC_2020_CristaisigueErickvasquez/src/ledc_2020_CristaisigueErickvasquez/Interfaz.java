/*
 * @author Cris Taisigue
 * @author Erick Vasquez
 * 
 * */

package ledc_2020_CristaisigueErickvasquez;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Interfaz extends JFrame {
	
	
	//Atributos y componentes
	private JPanel contentPane;
	private JTextField textBcarnet;
	private JTextField textNombre;
	private JTextField textCarnet;
	private JTextField textPromedio;
	private JTextField textCarrera;
	private JTable tablaEstudiantes;
	private JTextField textField;
	private JComboBox beca;
	private DefaultTableModel modeloTabla;
	private JLabel AvisoAdd;
	private JLabel lblNombre;
	private JLabel lblPromedio;
	private JLabel lblBeca;
	private JLabel lblCarrera;
	private JLabel lblCarnet;
	private JTextArea textArea;
	private JButton btnEliminar;
	private JLabel AvisoEliminar;
	private JCheckBox chckbxNewCheckBox;
	private JTextField referencia;
	private JRadioButton radioButton;
	private JRadioButton rdbtnAntes;
	private JRadioButton rdbtnPrimeroultimo;
	private JLabel AvisoRecorrer;
	private JRadioButton rdbtnUltimoAPrimero;
	private JTextArea textoRecorrer;
	
	private final int X=700,Y=700;//Proporciones de la ventana
	private String currentId="";//Almacenara el carnet a eliminar
	private ListaDoble estudiantes=new ListaDoble();//Lista enlazada Doble (Vacia)

	
	/*
	 * Usos de la lista doble lineas de ejemplo:
	 * Buscar: ObtenerEstudiante()=644, .mostrarInformacion()=649
	 * Insertar: InsertarIzquierda()=760
	 * Insertar Especifico: InsertarReferencia()=757
	 * Borrar: BorrarEspecifico()=588
	 * Recorrer: .Avanzar()=464, .Retroceder()=484 / Mostrar Lista: mostrarLista()=180
	 * */
	
	
	//Constructor
	public Interfaz() {
		
		//Componentes y caracteristicas
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(X,Y);
		setUndecorated(true);
		contentPane = new JPanel();
		setResizable(false);
	    setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel principal
		JPanel lamina = new JPanel();
		lamina.setBounds(0, 0, 700, 700);
		contentPane.add(lamina);
		lamina.setLayout(null);
		
		//Panel de pestañas
		JTabbedPane pestañas = new JTabbedPane(JTabbedPane.TOP);
		pestañas.setBounds(10, 346, 684, 347);
		lamina.add(pestañas);
		
		
		
		
		
		
		
		
		//Panel de añadir
		Panel añadir = new Panel();
		añadir.setBackground(SystemColor.menu);
		pestañas.addTab("Añadir", null, añadir, null);
		añadir.setLayout(null);
		
		JLabel jAñadir = new JLabel("A\u00F1adir estudiante");
		jAñadir.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		jAñadir.setBounds(9, 11, 169, 27);
		añadir.add(jAñadir);
		
		JLabel jNombre = new JLabel("Nombre:");
		jNombre.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		jNombre.setBounds(9, 52, 56, 16);
		añadir.add(jNombre);
		
		JLabel jCarnet = new JLabel("Carnet:");
		jCarnet.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		jCarnet.setBounds(8, 112, 56, 16);
		añadir.add(jCarnet);
		
		JLabel jPromedio = new JLabel("Promedio Ponderado:");
		jPromedio.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		jPromedio.setBounds(251, 52, 126, 16);
		añadir.add(jPromedio);
		
		JLabel jBeca = new JLabel("Nivel de Beca: ");
		jBeca.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		jBeca.setBounds(251, 112, 126, 16);
		añadir.add(jBeca);
		
		textNombre = new JTextField();
		textNombre.setBounds(77, 49, 116, 22);
		añadir.add(textNombre);
		textNombre.setColumns(10);
		
		textCarnet = new JTextField();
		textCarnet.setColumns(10);
		textCarnet.setBounds(77, 109, 116, 22);
		añadir.add(textCarnet);
		
		textPromedio = new JTextField();
		textPromedio.setColumns(10);
		textPromedio.setBounds(389, 49, 116, 22);
		añadir.add(textPromedio);
		
		//Evento del boton "Añadir" en la opcion de agregar Estudiante
		JButton btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Se ejecuta el metodo de agregar de esta clase, la cual hara comprobaciones de los
				 * campos a guardar, si todo es correcto, llamara al metodo "InsertarIzquierda()" de la 
				 * clase lista enlazada doble
				 * */
				if(agregar()) {
					//Si se agrego correctamente, se avisa y se actualiza la tabla
					resetAll();
					AvisoAdd.setText("Añadido exitosamente");
					//Se llama el metodo de la clase lista enlazada Doble (ListaDoble) "mostrarLista()"
					//el cual se le pasara como parametro el modelo de una tabla
					//para posterior a esto, recorrer la lista enlazada doble y mostrarla en formato de fila
					//y columnas "tabla"
					estudiantes.mostrarLista(modeloTabla);
				}
			}
		});
		btnAñadir.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAñadir.setBounds(572, 281, 97, 27);
		añadir.add(btnAñadir);
		
		lblCarrera = new JLabel("Carrera");
		lblCarrera.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCarrera.setBounds(9, 176, 56, 16);
		añadir.add(lblCarrera);
		
		textCarrera = new JTextField();
		textCarrera.setColumns(10);
		textCarrera.setBounds(78, 173, 116, 22);
		añadir.add(textCarrera);
		
		AvisoAdd = new JLabel("");
		AvisoAdd.setBounds(28, 294, 461, 14);
		añadir.add(AvisoAdd);
		
		beca = new JComboBox();
		beca.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1", "No posee"}));
		beca.setBounds(390, 111, 116, 20);
		añadir.add(beca);


		
		rdbtnAntes = new JRadioButton("Antes");
		rdbtnAntes.setEnabled(false);
		rdbtnAntes.setSelected(true);
		rdbtnAntes.setBounds(468, 176, 77, 23);
		añadir.add(rdbtnAntes);
		
		radioButton = new JRadioButton("Depues");
		radioButton.setEnabled(false);
		radioButton.setBounds(559, 176, 77, 23);
		añadir.add(radioButton);
		
		ButtonGroup grupo=new ButtonGroup();
		grupo.add(rdbtnAntes);
		grupo.add(radioButton);

		
		referencia = new JTextField();
		referencia.setEnabled(false);
		referencia.setBounds(478, 216, 145, 20);
		añadir.add(referencia);
		referencia.setColumns(10);
		
		//Se determina si se agregara con una referencia
		chckbxNewCheckBox = new JCheckBox("Insertar Con Referencia:");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Se muestran o se ocultan los componentes de insertar con referencia
                if(chckbxNewCheckBox.isSelected()) {
                	tipoInsertar(false);
                }else {
                	tipoInsertar(true);
                }
            }
        });
		chckbxNewCheckBox.setBounds(251, 174, 199, 23);
		añadir.add(chckbxNewCheckBox);
		
		JLabel lblCarnetEstudianteReferencia = new JLabel("Carnet del Estudiante Referente:");
		lblCarnetEstudianteReferencia.setBounds(254, 219, 196, 14);
		añadir.add(lblCarnetEstudianteReferencia);
		
		
		
		
		
		
		
		
		
		//Panel de busqueda
		Panel buscar = new Panel();
		buscar.setBackground(SystemColor.menu);
		pestañas.addTab("Buscar", null, buscar, null);
		buscar.setLayout(null);
		
		JLabel bCarnet = new JLabel("N\u00FAmero de carnet: ");
		bCarnet.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		bCarnet.setBounds(158, 284, 131, 25);
		buscar.add(bCarnet);
		
		textBcarnet = new JTextField();
		textBcarnet.setBounds(275, 286, 116, 22);
		buscar.add(textBcarnet);
		textBcarnet.setColumns(10);
		
		//Evento del boton "Consultar"
		Button btnBuscar = new Button("Consultar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llama el metodo de esta clase "buscar()" el cual realizara una serie
				//condicionales para hacer posible mostrar la informacion de un estudiante
				//en especifico, por medio de su carnet
				buscar();
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnBuscar.setBounds(411, 285, 79, 24);
		buscar.add(btnBuscar);
		
		JLabel bBuscar = new JLabel("Buscar estudiante");
		bBuscar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bBuscar.setBounds(10, 11, 160, 25);
		buscar.add(bBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 47, 449, 223);
		buscar.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		
		
		
		
		
		
		
		
		
		
		
		//Panel de eliminar
		Panel eliminar = new Panel();
		eliminar.setBackground(SystemColor.menu);
		pestañas.addTab("Eliminar", null, eliminar, null);
		eliminar.setLayout(null);
		
		JLabel lblEliminar = new JLabel("Eiminar estudiante");
		lblEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEliminar.setBounds(0, 11, 160, 25);
		eliminar.add(lblEliminar);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNombre.setBounds(0, 74, 134, 16);
		eliminar.add(lblNombre);
		
		lblCarnet = new JLabel("Carnet:");
		lblCarnet.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCarnet.setBounds(0, 129, 148, 16);
		eliminar.add(lblCarnet);
		
		lblPromedio = new JLabel("Promedio Ponderado:");
		lblPromedio.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPromedio.setBounds(169, 74, 185, 16);
		eliminar.add(lblPromedio);
		
		lblBeca = new JLabel("Nivel de Beca: ");
		lblBeca.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblBeca.setBounds(175, 129, 185, 16);
		eliminar.add(lblBeca);
		
		//El boton de "Eliminar" se activa cuando se ha encontrado el estudiante a eliminar
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se elimina el estudiante con el carnet provisto por la
				//variable global "currentId"
				//Se comprueba si la eliminacion en la lista enlazada doble fue correcta
				if(Eliminar()) {
					//Se elimino el estudiante con el carnet provisto
					//Se avisa
					resetAll();
					AvisoEliminar.setText("Eliminado completo");
					//Se despliega la informacion de la lista enlazada doble mediante una tabla (tabla posterior)
					estudiantes.mostrarLista(modeloTabla);
				}
			}
		});
		btnEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnEliminar.setBounds(572, 273, 97, 32);
		eliminar.add(btnEliminar);
		
		lblCarrera = new JLabel("Carrera:");
		lblCarrera.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCarrera.setBounds(411, 74, 208, 16);
		eliminar.add(lblCarrera);
		
		//Evento del boton "Buscar" en el panel de Eliminar
		JButton buscarE= new JButton("Buscar");
		buscarE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llama al metodo de esta clase mostrarEliminar()
				//esto para mostrar informacion previa obtenida de la lista
				//enlazada doble antes de decidir eliminar un estudiante
				mostrarEliminar();
			}
		});
		buscarE.setBounds(456, 273, 106, 35);
		eliminar.add(buscarE);
		
		textField = new JTextField();
		textField.setBounds(310, 276, 135, 28);
		eliminar.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Carnet del estudiante a eliminar:");
		lblNewLabel.setBounds(101, 283, 199, 14);
		eliminar.add(lblNewLabel);
		
		AvisoEliminar = new JLabel("");
		AvisoEliminar.setBounds(258, 248, 411, 14);
		eliminar.add(AvisoEliminar);
		
		
		
		
		
		
		
		
		//Panel de Recorrer
		JPanel recorrer = new JPanel();
		recorrer.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent arg0) {
				//Se determina el tipo de orden por defecto a recorrer
				resetRecorrer();
				if(estudiantes.Vacia()) {
					textoRecorrer.setText("La lista esta vacia");
				}else {
					estudiantes.asignar(1);
					textoRecorrer.setText(estudiantes.obtenerValor().mostrarInformacion());
				}
			}
		});
		recorrer.setLayout(null);
		pestañas.addTab("Recorrer", null, recorrer, null);
		
		JLabel lblRecorrer = new JLabel("Recorrer");
		lblRecorrer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRecorrer.setBounds(10, 11, 169, 27);
		recorrer.add(lblRecorrer);
		
		ActionListener listener = actionEvent -> changeOrder((JRadioButton) actionEvent.getSource());
		rdbtnPrimeroultimo = new JRadioButton("Primero a Ultimo");
		rdbtnPrimeroultimo.addActionListener(listener);
		rdbtnPrimeroultimo.setSelected(true);
		rdbtnPrimeroultimo.setBounds(10, 93, 163, 23);
		recorrer.add(rdbtnPrimeroultimo);
		
		rdbtnUltimoAPrimero = new JRadioButton("Ultimo a Primero");
		rdbtnUltimoAPrimero.addActionListener(listener);
		rdbtnUltimoAPrimero.setBounds(10, 131, 163, 23);
		recorrer.add(rdbtnUltimoAPrimero);
		
		ButtonGroup botonesRecorrer=new ButtonGroup();
		botonesRecorrer.add(rdbtnUltimoAPrimero);
		botonesRecorrer.add(rdbtnPrimeroultimo);
		
		JLabel lblOrdenDeRecorrido = new JLabel("Orden de Recorrido");
		lblOrdenDeRecorrido.setBounds(10, 67, 144, 14);
		recorrer.add(lblOrdenDeRecorrido);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(189, 36, 437, 231);
		recorrer.add(scrollPane_2);
		
		textoRecorrer = new JTextArea();
		textoRecorrer.setEditable(false);
		textoRecorrer.setLineWrap(true);
		scrollPane_2.setViewportView(textoRecorrer);
		
		//Siguiente en el recorrido
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verifica si existe un estudiante siguiente
				//Se desplaza y probee dicha informacion
				if(!estudiantes.Vacia()) {
					if(estudiantes.siguiente()) {
						AvisoRecorrer.setText("");
						estudiantes.avanzar();
						textoRecorrer.setText(estudiantes.obtenerValor().mostrarInformacion());
					}else {
						AvisoRecorrer.setText("No hay estudiantes siguientes");
					}
				}
			}
		});
		btnSiguiente.setBounds(538, 285, 89, 23);
		recorrer.add(btnSiguiente);
		
		//Anterior en el recorrido
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verifica si existe un estudiante anterior
				//Se desplaza y probee dicha informacion
				if(!estudiantes.Vacia()) {
					if(estudiantes.anterior()) {
						AvisoRecorrer.setText("");
						estudiantes.retroceder();
						textoRecorrer.setText(estudiantes.obtenerValor().mostrarInformacion());
					}else {
						AvisoRecorrer.setText("No hay estudiantes anteriores");
					}
				}
			}
		});
		btnAnterior.setBounds(189, 285, 89, 23);
		recorrer.add(btnAnterior);
		
		AvisoRecorrer = new JLabel("");
		AvisoRecorrer.setBounds(264, 11, 293, 14);
		recorrer.add(AvisoRecorrer);
		
		
		
		
		
		
		
		
		
		//Componentes y caracteristicas del panel general
		JLabel lAtributos = new JLabel("Lista Estudiantes");
		lAtributos.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lAtributos.setBounds(10, 73, 213, 27);
		lamina.add(lAtributos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 111, 674, 218);
		lamina.add(scrollPane_1);
		
		tablaEstudiantes = new JTable();
		scrollPane_1.setViewportView(tablaEstudiantes);

		//El modelo de la tabla, el cual se proporciona para desplegar la informacion de los estudiantes en
		//la lista enlazada simple
		modeloTabla=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posicion","Carnet", "Nombre","Carrera","Beca","Promedio Ponderado"
			}
		);
		tablaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaEstudiantes.setDefaultEditor(Object.class,null);
		tablaEstudiantes.setModel(modeloTabla);
		
		JLabel lblNewLabel_1 = new JLabel("<html><body>Nota: al realizar cualquier operacion, la tabla desplegara automaticamente la informacion de los estudiantes almacenados</body></html>");
		lblNewLabel_1.setBounds(200, 71, 456, 29);
		lamina.add(lblNewLabel_1);
		
		//La barra dinamica permite crear una barra personalizada
		JPanel barraDinamica = new MotionPanel(this);
		barraDinamica.setLayout(null);
		barraDinamica.setBackground(new Color(30,30,30));
		barraDinamica.setBounds(0, 0, 700, 48);
		lamina.add(barraDinamica);
		
		JLabel lblControlEstudiantes = new JLabel("CONTROL ESTUDIANTES");
		lblControlEstudiantes.setForeground(Color.WHITE);
		lblControlEstudiantes.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblControlEstudiantes.setBounds(10, 11, 339, 26);
		barraDinamica.add(lblControlEstudiantes);
		
		//Evento en el boton salir que cerrara el programa
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBackground(Color.RED);
		btnSalir.setForeground(Color.BLACK);
		btnSalir.setToolTipText("Darle click cerrara todo el programa");
		btnSalir.setBounds(589, 11, 101, 26);
		barraDinamica.add(btnSalir);
		
		//Este escuchador de evento permitira que los campos de las opciones se resetean al cambiarse
		pestañas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Se llama al metodo que resetea todos los campos de todas las opciones
				resetAll();
				estudiantes.mostrarLista(modeloTabla);
			}
		});		
		
		toFront();
		requestFocus();
	}
	
	
	
	//El metodo eliminar llamara al metodo de "BorrarEspecifico()" de la lista enlazada doble
	//este metodo pide un carnet con el cual localizar al estudiante y eliminarlo,
	//este valor es provisto por la variable global "currentId" que toma su valor cuando el usuario
	//hace el desplegamiento de la vista previa antes de eliminar un estudiante.
	public boolean Eliminar() {
		boolean response=false;
		try {

			if(estudiantes.buscarCoincidencias(currentId)) {
				//Elimina un estudiante por medio de su carnet en la lista enlazada doble
				estudiantes.BorrarEspecifico(currentId);//Llamada al metodo "BorrarEspecifico" de la lista enlazada doble
				response=true;
			}		
		}catch(Exception e) {
			response=false;
		}
		return response;
	}
	
	
	
	//Este metodo se encargara de mostrar informacion previa al usuario de estudiante a eliminar por medio de su carnet
	//asi tambien cambiara el valor de la variable global "currentId" con dicho carnet provisto
	public void mostrarEliminar(){
		//Se comprueba si la lista esta vacia
		if(estudiantes.Vacia()) {
			AvisoEliminar.setText("No existen estudiantes agregados");
		}else {
			if(textField.getText().length()<=2) {
				AvisoEliminar.setText("Ingrese al menos tres caracteres");
			}else {
				Estudiante buscado=null;
				buscado=estudiantes.ObtenerEstudiante(textField.getText());
				if(buscado==null) {
					resetAll();
					AvisoEliminar.setText("No se encontro ningun estudiante");
				}else {
					
					currentId=textField.getText();
					lblNombre.setText("Nombre:"+buscado.getNombre());
					lblCarrera.setText("Carrera:"+buscado.getCarrera());
					lblCarnet.setText("Carnet:"+buscado.getCarnet());
					lblPromedio.setText("Promedio Ponderado:"+buscado.getPromedioPonderado());
					lblBeca.setText("Tipo Beca:"+((buscado.getTipoBeca()=='N')?"No posee":Character.toString(buscado.getTipoBeca())));
					btnEliminar.setEnabled(true);
					AvisoEliminar.setText("Presione el boton de eliminar");
					
					
				}
			}
		}
	}
	
	
	//Este metodo permite al usuario encontrar un Estudiante por medio de su carner y desplegar su informacion
	public void buscar() {
		//Se comprueba si la lista esta vacia
		if(estudiantes.Vacia()) {
			textArea.setText("No existen estudiantes agregados");
		}else {
			if(textBcarnet.getText().length()<=2) {
				textArea.setText("Ingrese al menos tres caracteres");
			}else {
				
				Estudiante buscado=null;
				//Se busca al estudiante mediante el metodo de la lista enlazada doble
				buscado=estudiantes.ObtenerEstudiante(textBcarnet.getText());
				if(buscado==null) {
					textArea.setText("No se encontro ningun estudiante");
				}else {
					
					textArea.setText(buscado.mostrarInformacion());
					
				}
			}
		}
	}
	
		
	//Se resetean todos los componentes de todas las opciones
	public void resetAll() {
		//Agregar
		AvisoAdd.setText("");
		textNombre.setText("");
		textCarnet.setText("");
		textCarrera.setText("");
		textPromedio.setText("");
		tipoInsertar(true);
		resetRecorrer();
		//Buscar
		textArea.setText("");
		textBcarnet.setText("");
		//Eliminar
		AvisoEliminar.setText("");;
		btnEliminar.setEnabled(false);
		textField.setText("");
		lblNombre.setText("Nombre:");
		lblCarrera.setText("Carrera:");
		lblCarnet.setText("Carnet:");
		lblPromedio.setText("Promedio Ponderado:");
		lblBeca.setText("Tipo Beca:");
		currentId="";
		
	}
	
	//Este metodo permite cambiar el orden en el cual se realizara
	//el recorrido en la opcion de "Recorrer"
	public void changeOrder(JRadioButton boton) {
		AvisoRecorrer.setText("");
		switch(boton.getText()) {
		
			case "Primero a Ultimo":{
				if(!estudiantes.Vacia()) {
					estudiantes.asignar(1);
					textoRecorrer.setText(estudiantes.obtenerValor().mostrarInformacion());
				}else {
					textoRecorrer.setText("Lista Vacia");
				}
			}break;
			
			case "Ultimo a Primero":{
				if(!estudiantes.Vacia()) {
					estudiantes.asignar(2);
					textoRecorrer.setText(estudiantes.obtenerValor().mostrarInformacion());
				}else {
					textoRecorrer.setText("Lista Vacia");
				}
			}break;
		
		}
	}
	
	//Resetea los campos de la opcion "Recorrer"
	public void resetRecorrer() {
		estudiantes.asignar(3);
		rdbtnPrimeroultimo.setSelected(true);
		textoRecorrer.setText("");
		AvisoRecorrer.setText("");
	}
	
	//Permite mostrar o ocultar los campos de Insertar con Referencia
	//en la opcion de Agregar
	public void tipoInsertar(boolean type) {
		if(type) {
			chckbxNewCheckBox.setSelected(false);
			referencia.setEnabled(false);
			referencia.setText("");
			rdbtnAntes.setEnabled(false);
			radioButton.setEnabled(false);
		}else {
			chckbxNewCheckBox.setSelected(true);
			referencia.setEnabled(true);
			referencia.setText("");
			rdbtnAntes.setEnabled(true);
			radioButton.setEnabled(true);
		}
	}
	
	
	
	//Este metodo realizara la comprobacion de campos, si todo es correcto se crea un nuevo estudiante
	//y se agregara a la lista enlazada doble mediante "Insertar()" o "InsertarEspecifico()"
	public boolean agregar() {
		boolean response=false;
		try {
			if(comprobarCampos()==0) {
				Estudiante temp=new Estudiante();
				temp.setCarnet(textCarnet.getText());
				temp.setCarrera(textCarrera.getText());
				temp.setPromedioPonderado(Double.parseDouble(textPromedio.getText()));
				temp.setNombre(textNombre.getText());
				if(beca.getSelectedItem().toString().equalsIgnoreCase("No posee")) {
					temp.setTipoBeca('N');
				}else {
					temp.setTipoBeca(beca.getSelectedItem().toString().charAt(0));
				}
				
				if(chckbxNewCheckBox.isSelected()) {
					//Añadir con referencia
					estudiantes.InsertarReferencia(temp, ((rdbtnAntes.isSelected())?true:false), referencia.getText());
				}else {
					//Añadir sin referencia
					estudiantes.InsertarIzquierda(temp);
				}
				response=true;
			}

		}catch(Exception e) {
			response=false;
		}
		
		return response;
			
	}
	
	
	
	//Comprobar los campos de la opcion de agregar
	public int comprobarCampos() {
		int general=0;
		if(textNombre.getText().equalsIgnoreCase("")) {
			general=1;
		}else if(textCarnet.getText().length()<=2) {
			general=2;
		}else if(textCarrera.getText().equalsIgnoreCase("")){
			general=1;
		}else if(textCarnet.getText().equalsIgnoreCase("")) {
			general=1;
		}else if(textPromedio.getText().equalsIgnoreCase("")) {
			general=1;
		}else if(estudiantes.buscarCoincidencias(textCarnet.getText())) {
			general=3;
		}else{
			try {
				double myDouble=Double.parseDouble(textPromedio.getText());
				if(myDouble<0) {
					general=4;
				}
			}catch(Exception e) {
				general=4;
			}
		}
		
		if(chckbxNewCheckBox.isSelected()){
			if(referencia.getText().length()<=2) {
				general=5;
			}else {
				if(!estudiantes.buscarCoincidencias(referencia.getText())) {
					general=6;
				}
			}
		}
		
		switch(general) {
			case 1:{
				AvisoAdd.setText("Hay campos vacios");
			}break;
			
			case 2:{
				AvisoAdd.setText("El carnet debe contener al menos 3 caracteres");
			}break;
			
			case 3:{
				AvisoAdd.setText("El carnet provisto para el estudiante nuevo ya esta en uso");
			}break;
			
			case 4:{
				AvisoAdd.setText("Ingrese correctamente el promedio ponderado (Valores Positivos)");
			}break;
			
			case 5:{
				AvisoAdd.setText("Ingrese un carnet referencia de al menos 3 caracteres");
			}break;
			
			case 6:{
				AvisoAdd.setText("No se encontro el estudiante referente");
			}break;
		}
		
		return general;
	}
	
	
	
	
	
	//Metodo main para la ejecucion de la ventana
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