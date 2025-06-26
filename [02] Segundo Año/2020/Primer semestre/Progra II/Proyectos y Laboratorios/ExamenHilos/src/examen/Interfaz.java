/* 1 Laboratorio de Programación II
 * @author Cris Taisigue B97785
 * @author Erick Vasquez B98334
 * 
 * **/


package examen;

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
import javax.swing.border.TitledBorder;

//Ventana maestra
public class Interfaz extends JFrame{
	
	/*
	 * Empleo de hilos lineas de referencia
	 * Hilo Uno: "hiloConsulta"=desplegar totales, (declaracion=Linea:70),(creacion=Linea:166),(Start()=Linea:167),(Stop()=Linea:261)
	 * Hilo Dos: "clockHour"=hilo de hora, (declaracion=Linea:74),(creacion=Linea:250),(Start()=Linea:251),(Stop()=Linea:264)
	 * 
	 * */
	
	
	//ArrayList para el almacenamiento de la información de los pacientes
	ArrayList<Paciente> pacientes=new ArrayList();
	
	//Ventana maestra
	JFrame mainWindow;
	
	/*Los siguientes objetos contienen la estructuración de las diversas opciones 
	 * que ofrece este sistema*/
	Crear pestanaCrear;
	Remover pestanaRemover;
	Actualizar pestanaActualizar;
	Consultar Consultar;
	
	
	//Componentes y detalles
	private Color backColor=new Color(43,42,42);
	private final int X=875,Y=750;
	private JPanel mainPanel;
	private JButton botonCerrar;
	private JLabel imgLabel;
	BufferedImage ico;
	private JLabel tittle;
	private JLabel lblCantidadPacienteTotales;
	private JLabel lblPacientesFemeninos;
	private JLabel lblPacientesMasculinos;
	private JLabel lblCondicionLeve;
	private JLabel lblCondicionRegular;
	private JLabel lblCondicionInstensivos;
	private JLabel horaDigital;
	
	//Hilo Que se refresca para mostrar los totales en el sistema
	HiloTotales hiloConsulta;//Declaracion

	//Este hilo mostrara la hora del sistema la cual luego se utilizara
	//para definir la hora de registro del paciente
	HiloHora clockHour;//Declaracion
	
	
	//Constructor de la interfaz
	public Interfaz() throws IOException {
	
		//Características de la ventana
		mainWindow=new JFrame();
		
		mainWindow.setSize(X,Y);
		mainWindow.setUndecorated(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setTitle("Centro de Control de Datos COVID-19");
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		//Añadiendo el icono
		try {
			
		    Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../recursos/red.png"));
		    mainWindow.setIconImage(iconoPropio);
		}catch(Exception e) {
			//Pass
		}
		
		mainWindow.getContentPane().add(mainPanel);
		
		
		
		
		
		
		
		//Panel de datos que se actualiza mediante el hilo
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DATOS TOTALES", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatos.setBounds(10, 496, 855, 243);
		mainPanel.add(panelDatos);
		
		lblCantidadPacienteTotales = new JLabel("Cantidad Paciente Totales:");
		lblCantidadPacienteTotales.setBounds(10, 28, 246, 14);
		panelDatos.add(lblCantidadPacienteTotales);
		
		lblPacientesFemeninos = new JLabel("Pacientes Femeninos:");
		lblPacientesFemeninos.setBounds(10, 75, 167, 14);
		panelDatos.add(lblPacientesFemeninos);
		
		lblPacientesMasculinos = new JLabel("Pacientes Masculinos:");
		lblPacientesMasculinos.setBounds(10, 133, 167, 14);
		panelDatos.add(lblPacientesMasculinos);
		
		lblCondicionLeve = new JLabel("Condicion Leve:");
		lblCondicionLeve.setBounds(10, 193, 167, 14);
		panelDatos.add(lblCondicionLeve);
		
		lblCondicionRegular = new JLabel("Condicion Regular:");
		lblCondicionRegular.setBounds(403, 75, 167, 14);
		panelDatos.add(lblCondicionRegular);
		
		lblCondicionInstensivos = new JLabel("Condicion Instensivos:");
		lblCondicionInstensivos.setBounds(403, 133, 167, 14);
		panelDatos.add(lblCondicionInstensivos);
		
		JProgressBar barMas = new JProgressBar();
		barMas.setStringPainted(true);
		barMas.setBounds(165, 128, 204, 32);
		panelDatos.add(barMas);
		
		JProgressBar barLeve = new JProgressBar();
		barLeve.setStringPainted(true);
		barLeve.setBounds(165, 187, 204, 32);
		panelDatos.add(barLeve);
		
		JProgressBar barRegular = new JProgressBar();
		barRegular.setStringPainted(true);
		barRegular.setBounds(540, 68, 204, 32);
		panelDatos.add(barRegular);
		
		JProgressBar barIntensivo = new JProgressBar();
		barIntensivo.setStringPainted(true);
		barIntensivo.setBounds(540, 128, 204, 32);
		panelDatos.add(barIntensivo);
		
		JProgressBar barFem = new JProgressBar();
		barFem.setStringPainted(true);
		barFem.setBounds(165, 68, 204, 32);
		panelDatos.add(barFem);
		
		//Creacion del hilo, pasandole los campos, la referencia al arrayList y el nombre de dicho
		hiloConsulta=new HiloTotales(lblCantidadPacienteTotales,barMas,barFem,barLeve,barRegular,barIntensivo,pacientes,"Consulta Hilo");
		hiloConsulta.start();//Se inicia el hilo
		
		
		
		
		
		
		
		
		
		
		//Componentes de la ventana
		JTabbedPane panelPestanas = new JTabbedPane(JTabbedPane.TOP);
		panelPestanas.setBounds(10, 66, 860, 419);
		mainPanel.add(panelPestanas);
		
		
		
		
		

		horaDigital = new JLabel("");//Campo modificado por el hilo de hora
			
		
		//Pestaña Crear
		pestanaCrear=new Crear(pacientes,horaDigital);
		panelPestanas.addTab("A\u00F1adir", null, pestanaCrear, null);
		
		
		
		
		//Pestaña Actualizar
		pestanaActualizar=new Actualizar(pacientes);
		panelPestanas.addTab("Actualizar", null, pestanaActualizar, null);
		

		
		//Pestaña Remover
		pestanaRemover=new Remover(pacientes);
		panelPestanas.addTab("Eliminar", null, pestanaRemover, null);		
		
		
		
		//Panel de consulta
		Consultar=new Consultar(pacientes);
		panelPestanas.addTab("Consultar Paciente", null, Consultar, null);
		
		
		
		//Panel superior
		JPanel paneler = new MotionPanel(mainWindow);
		paneler.setLayout(null);
		paneler.setBounds(0, 0, 875, 55);
		paneler.setBackground(backColor);
		mainPanel.add(paneler);
		
		
		//Imagen Logo
		imgLabel=new JLabel("");
		imageResizer(imgLabel,"src/recursos/redIcon.png");
		imgLabel.setBounds(0, 0, 67, 55);
		
		paneler.add(imgLabel);
		
		tittle = new JLabel("COVID19: Control de Datos de Pacientes");
		tittle.setForeground(new Color(255, 255, 255));
		tittle.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		tittle.setBounds(88, 0, 522, 55);
		paneler.add(tittle);
		
		JLabel lblHorah = new JLabel("Hora (24H):");
		lblHorah.setForeground(Color.WHITE);
		lblHorah.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHorah.setBounds(516, 20, 87, 20);
		paneler.add(lblHorah);
		
		horaDigital.setForeground(Color.WHITE);
		horaDigital.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		horaDigital.setBounds(620, 18, 122, 20);
		paneler.add(horaDigital);
		
		
		//Creamos el hilo de la hora
		clockHour=new HiloHora(horaDigital,"Reloj");
		clockHour.start();//Preparamos e iniciamos el hilo de la hora
		
		
		//Cierra totalmente el programa
		botonCerrar = new JButton("X");
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				hiloConsulta.setCondition(false);//Indicamos al hilo de consulta que detenga el desplegamiento de datos
				hiloConsulta.stop();//se detiene el hilo
				
				clockHour.setCondition(false);//Indicamos al hilo de la hora que se detenga
				clockHour.stop();//Se detiene el hilo
				System.exit(0);//Cerramos el sistema
				
				
			}
		});
		
		botonCerrar.setForeground(new Color(255, 255, 255));
		botonCerrar.setBackground(Color.RED);
		botonCerrar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonCerrar.setToolTipText("Cerrar");
		botonCerrar.setBounds(796, 13, 53, 33);;
		botonCerrar.setFocusPainted(false);
		paneler.add(botonCerrar);
		
		
		
		
		
		
		
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
	
	
	//Método main para ejecutar la Interfaz
	public static void main(String[] args) throws IOException {
		Interfaz ventana=new Interfaz();
	}
}