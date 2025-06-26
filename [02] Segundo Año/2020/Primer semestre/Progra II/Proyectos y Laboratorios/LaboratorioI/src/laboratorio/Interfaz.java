/*Laboratorio 1 Programaci�n II
 * @author Cris Taisigue B97785
 * @author Erick Vasquez 
 * 
 * **/

package laboratorio;

import paneles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Ventana maestra
public class Interfaz extends JFrame{
	
	//ArrayList para el almacenamiento de la informaci�n de los pacientes
	ArrayList<Paciente> pacientes=new ArrayList();
	
	//Ventana maestra
	JFrame mainWindow;
	
	/*Los siguientes objetos contienen la estructuraci�n de las diversas opciones 
	 * que ofrece este sistema*/
	Crear pestanaCrear;
	Principal pestanaPrincipal;
	Remover pestanaRemover;
	Actualizar pestanaActualizar;
	Consulta pestanaConsulta;
	Instrucciones pestanaInstrucciones;
	
	//Componentes y detalles
	private Color backColor=new Color(43,42,42);
	private final int X=1000,Y=700;
	private JPanel mainPanel;
	private JTextField diasHA;
	private JTextField direccionA;
	private JTextField telefonoA;
	private JTextField identificacionA;
	private JTextField apellidoA;
	private JTextField nombreA;
	private JButton botonCerrar;
	private JLabel imgLabel;
	BufferedImage ico;
	BufferedImage icoPUno;
	BufferedImage icoPDos;
	BufferedImage icoPTres;
	private JLabel protocolos;
	private JLabel protoUno;
	private JLabel protoDos;
	private JLabel protoTres;
	private JLabel tittle;
	private JLabel showOne;
	private JLabel lblPestaaPricipal;
	private JLabel showTwo;
	private JLabel showThree;
	private JLabel showFour;
	private JLabel showFive;
	private JLabel espOne;
	private JLabel espTwo;
	private JLabel espThree;
	private JLabel espFour;
	private JLabel espFive;
	private JLabel imaTwo;
	private JLabel imaThree;
	private JLabel imaOne;
	private JLabel label_6;
	private JLabel imaFour;
	private JPanel visorImagenes;
	
	//Constructor de la interfaz
	public Interfaz() throws IOException {
	
		//Caracter�sticas de la ventana
		mainWindow=new JFrame();
		
		mainWindow.setSize(X,Y);
		mainWindow.setUndecorated(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setTitle("Centro de Control de Datos COVID-19");
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		//A�adiendo el icono
		try {
			
		    Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../recursos/red.png"));
		    mainWindow.setIconImage(iconoPropio);
		}catch(Exception e) {
			//Pass
		}
		
		mainWindow.getContentPane().add(mainPanel);
		
		
		//Componentes de la ventana
		JTabbedPane panelPestanas = new JTabbedPane(JTabbedPane.TOP);
		panelPestanas.setBounds(10, 73, 974, 616);
		mainPanel.add(panelPestanas);
		
		
		
		
		//Pesta�a Principal
		pestanaPrincipal=new Principal();
		pestanaPrincipal.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				pestanaPrincipal.obtenerTabla().actualizarTabla(pacientes);
			}
		});

		panelPestanas.addTab("Principal", null, pestanaPrincipal, null);
		
			//Protocolos im�genes
		protocolos = new JLabel("Protocolos a seguir:");
		protocolos.setBounds(767, 75, 154, 14);
		pestanaPrincipal.add(protocolos);
		
		protoUno = new JLabel("");
		imageResizer(protoUno,"src/recursos/protoOne.png");
		protoUno.setBounds(751, 133, 140, 121);
		pestanaPrincipal.add(protoUno);
		
		
		protoDos = new JLabel("");
		imageResizer(protoDos,"src/recursos/protoTwo.png");
		protoDos.setBounds(751, 287, 140, 121);
		pestanaPrincipal.add(protoDos);
	
		
		protoTres = new JLabel("");
		imageResizer(protoTres,"src/recursos/protoThree.png");	
		protoTres.setBounds(751, 444, 140, 121);
		pestanaPrincipal.add(protoTres);
		

		
		//Pesta�a Crear
		pestanaCrear=new Crear(pacientes);
		panelPestanas.addTab("A\u00F1adir", null, pestanaCrear, null);
		
		
		
		
		//Pesta�a Actualizar
		pestanaActualizar=new Actualizar(pacientes);
		panelPestanas.addTab("Actualizar", null, pestanaActualizar, null);
		

		
		//Pesta�a Remover
		pestanaRemover=new Remover(pacientes);
		panelPestanas.addTab("Eliminar", null, pestanaRemover, null);		
		
		
		
		//Pesta�a Consulta
		pestanaConsulta=new Consulta(pacientes);
		panelPestanas.addTab("Recopilaci�n y Consulta", null,pestanaConsulta, null);
		
		//Pesta�a Instrucciones
		pestanaInstrucciones=new Instrucciones();
		panelPestanas.addTab("Instrucciones", null, pestanaInstrucciones, null);
		
		
		
		//Panel superior
		JPanel paneler = new MotionPanel(mainWindow);
		paneler.setLayout(null);
		paneler.setBounds(0, 0, 1000, 55);
		paneler.setBackground(backColor);
		mainPanel.add(paneler);
		
			//Cierra totalmente el programa
		botonCerrar = new JButton("X");
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		botonCerrar.setForeground(new Color(255, 255, 255));
		botonCerrar.setBackground(Color.RED);
		botonCerrar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonCerrar.setToolTipText("Cerrar");
		botonCerrar.setBounds(937, 11, 53, 33);;
		botonCerrar.setFocusPainted(false);
		
		//Imagen Logo
		imgLabel=new JLabel("");
		imageResizer(imgLabel,"src/recursos/redIcon.png");
		imgLabel.setBounds(0, 0, 67, 55);
		
		paneler.add(imgLabel);
		paneler.add(botonCerrar);
		
		tittle = new JLabel("COVID19: Control de Datos de Pacientes");
		tittle.setForeground(new Color(255, 255, 255));
		tittle.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		tittle.setBounds(88, 0, 522, 55);
		paneler.add(tittle);
		
		
		
		//Props de la ventana
		mainWindow.setVisible(true);
		mainWindow.toFront();
		mainWindow.requestFocus();
	}
	
	//Cargador de imagenes
	public void imageResizer(JLabel componente,String dir) {
		try {
			BufferedImage imagenBuffer= ImageIO.read(new File(dir));
			ImageIcon icono=new ImageIcon(imagenBuffer);
				
			componente.setIcon(icono);
			
		}catch(Exception e) {
			componente.setText("");
		}
	}
	
	
	//M�todo main para ejecutar la Interfaz
	public static void main(String[] args) throws IOException {
		Interfaz ventana=new Interfaz();
	}
}