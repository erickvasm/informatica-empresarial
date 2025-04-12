package controlador;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Cliente;
import modelo.Funcionario;

//Clase Interfaz visual primaria
public class Interfaz{

	//Componentes y atributos
	private JFrame mainFrame;
	private JPanel mainPanel;
	private final int X=800,Y=700;
	private Barra mainBarra;
	private Tabla tablaCambiante;
	private Insertar insertar;
	private VistaGeneral generalView;
	private Remover remove;
	private Consulta search;
	private Actualizar update;
	
	private boolean gestion=true;//Modo de gestion
	private ArrayList<Funcionario> funcionarios=new <Funcionario>ArrayList();//ArrayList Funcionario central
	private ArrayList<Cliente> prestamos =new <Cliente>ArrayList();//ArrayList Prestamos central
	
	
	//Constructor
	public Interfaz() {
		
		//Panel principal
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		//Propiedades de la ventana
		mainFrame=new JFrame();
		mainFrame.setSize(X,Y);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setUndecorated(true);
		mainFrame.getContentPane().add(mainPanel);
		
		//Barra dinamica
		mainBarra=new Barra(mainFrame);
		mainPanel.add(mainBarra.obtenerBarra());
		
		//Panel de pestanas
		JTabbedPane panelPestanas = new JTabbedPane(JTabbedPane.TOP);
		panelPestanas.setBounds(0, 74, 800, 626);
		mainPanel.add(panelPestanas);
		
		//Pestana General
		JPanel general = new JPanel();
		general.setLayout(null);
		panelPestanas.addTab("General", null, general, null);
		
		
		
		
		
		
		
		
		//Pestana de opciones
		JTabbedPane pestanaOpciones = new JTabbedPane(JTabbedPane.TOP);
		pestanaOpciones.setBounds(0, 307, 795, 291);
		general.add(pestanaOpciones);

		//Scroll de la tabla cambiante
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(267, 24, 505, 272);
		general.add(scroll);
				
		//Tabla de valores cambiantes
		tablaCambiante = new Tabla(prestamos,funcionarios);
		//Se determina si alguna de la filas de la tabla es seleccionada
		tablaCambiante.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	if(!e.getValueIsAdjusting()) {
		    		if(tablaCambiante.getSelectedRow()>=0) {
		    			//Se envia el indice seleccionado a la opcion desplegada
		    			sendIndex(tablaCambiante.getSelectedRow(),pestanaOpciones.getSelectedIndex());
		    		}
		    	}
		    }
		});
		scroll.setViewportView(tablaCambiante);
				
		
		
		
		//OPCIONES
		
		//Opcion Vista General
		generalView=new VistaGeneral(prestamos,funcionarios);
		pestanaOpciones.addTab("Vista General", null, generalView, null);
		
		
		
		
		
		
		
		
		//Opcion Insertar
		insertar=new Insertar(prestamos,funcionarios);
		insertar.obtenerBoton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(insertar.guardar()) {
					insertar.getAviso().setText("Aviso: guardado exitosamente");
					insertar.resetAll();
					tablaCambiante.actualizarTabla(gestion);
				
				}else {
					insertar.getAviso().setText("Aviso: verifique los campos");
				}
			}
		});
		pestanaOpciones.addTab("Insertar", null, insertar, null);
		
		
		
		
		
		
		//Opcion Actualizar
		update = new Actualizar(prestamos,funcionarios);
		//Si el boton de actualizar es presionado, se determina si la actualizacion fue exitosa
		update.obtenerBoton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se actualiza
				if(update.Actualizar()) {
					//Exitosa
					update.resetAll();
					update.enabledComponents(false);
					tablaCambiante.actualizarTabla(gestion);
					update.obtenerAviso().setText("Actualizacion exitosa.");
				}else {
					//Error
					update.obtenerAviso().setText("Verifique los campos");
				}
			}
		});
		pestanaOpciones.addTab("Actualizar", null, update, null);
		
		
		
		
		
		
		
		
		
		//Opcion Remover
		remove = new Remover();
		//Al presionar el boton de eliminar se borra del ArrayList correspondiente el objeto
		//por medio del indice provisto
		remove.obtenerBoton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(remove.Eliminar(gestion,prestamos,funcionarios)) {
					tablaCambiante.actualizarTabla(gestion);
				}
			}
		});
		pestanaOpciones.addTab("Remover", null, remove, null);
		
		
		
		
		
		
		
		
		
		//Opcion de Buscar
		search = new Consulta();
		//Al presionar el boton de buscar, se consulta en el ArrayList correspondiente
		search.obtenerBoton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search.consultar(gestion, prestamos, funcionarios);
			}
		});
		pestanaOpciones.addTab("Consultar", null, search, null);
		
		
		
		
		
		
		//Se capta el evento de cambio de opcion
		pestanaOpciones.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Se actualiza la tabla y se resetean todos los componentes
				tablaCambiante.actualizarTabla(gestion);
				resetAll();
				disableAll();
			}
		});		
		
		
		
		
		
		
		//Pestana de instruciones
		JPanel instrucciones = new JPanel();
		panelPestanas.addTab("Instrucciones", null, instrucciones, null);
				
		
		
		
		
		//Componentes y detalles en el Panel General
		JLabel nota = new JLabel("GESTIONAR");
		nota.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		nota.setBounds(90, 33, 119, 14);
		general.add(nota);
		
		//Se cambia el modo de gestion
		ActionListener listener = actionEvent -> cambiarGestion((JRadioButton) actionEvent.getSource());
		JRadioButton radioOne = new JRadioButton("Funcionario");
		radioOne.setSelected(true);
		radioOne.setBounds(81, 97, 109, 23);
		radioOne.addActionListener(listener);
		general.add(radioOne);
		
		JRadioButton radioTwo = new JRadioButton("Prestamos");
		radioTwo.setBounds(79, 149, 109, 23);
		radioTwo.addActionListener(listener);
		general.add(radioTwo);
	
		ButtonGroup botones=new ButtonGroup();
		botones.add(radioOne);
		botones.add(radioTwo);
		mainFrame.setVisible(true);
	}
	
	//Se envia el indice a la opcion desplegado
	public void sendIndex(int index,int pane) {
		switch(pane) {
			//Vista General
			case 0:{
				generalView.CatchIndex(index, gestion);
			}break;
			
			//Actualizar
			case 2:{
				update.catchIndex(index, gestion);
			}break;
			
			//Remover
			case 3:{
				remove.CatchIndex(index, prestamos, funcionarios, gestion);
			}break;
			
		}
	}
	
	//Se desactivan todos los componentes
	public void disableAll() {
		update.enabledComponents(false);
		update.optionChanger(gestion);
	}
	
	
	//Se resetean todos los componentes de todas las opciones
	public void resetAll() {
		update.resetAll();
		generalView.reset();
		insertar.resetAll();
		remove.reset();
		search.reset();
	}
	
	//Este metodo permite que las opciones reciban el cambio de modo de gestion
	public void cambiarGestion(JRadioButton radio) {
		//Se resetea todo
		resetAll();
		if(radio.getText().equalsIgnoreCase("Funcionario")) {			
			//Modo gestion Funcionarios
			insertar.gestionChanger(true);
			remove.gestionChanger(true);
			tablaCambiante.actualizarTabla(true);
			search.optionChanger(true);
			update.optionChanger(true);
			
			gestion=true;
		}else {
			//Modo Gestion Prestamos
			insertar.gestionChanger(false);
			remove.gestionChanger(false);
			tablaCambiante.actualizarTabla(false);
			search.optionChanger(false);
			update.optionChanger(false);
			
			
			gestion=false;
		}
	}
	
	
	//Se obtiene las imagenes de un directorio de recursos
	public void imageResizer(JLabel componente,String dir,int x,int y) {
		try {
			URL imagenBuffer= Interfaz.class.getResource(dir);		
			BufferedImage ima=ImageIO.read(imagenBuffer.openStream());
			Image imagenRedimensionada = ima.getScaledInstance(x,y, Image.SCALE_SMOOTH);
		
			ImageIcon icono=new ImageIcon(imagenRedimensionada);
			componente.setIcon(icono);
		}catch(Exception e) {
			componente.setText("");
		}
	}
}
