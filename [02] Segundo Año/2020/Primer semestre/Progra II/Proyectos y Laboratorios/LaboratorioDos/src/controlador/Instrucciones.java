package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

//Clase de instrucciones
public class Instrucciones extends JPanel{

	//Componentes
	private JLabel bigOne;
	private JLabel bigTwo;
	private JLabel bigThree;
	private JLabel bigFour;
	private JLabel bigFive;
	private JLabel instruccion;
	
	//Constructor
	public Instrucciones() {
		this.setLayout(null);
		
		//Componentes y caracteristicas
		JLabel lblthis = new JLabel("Instrucciones");
		lblthis.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblthis.setBounds(463, 11, 323, 57);
		this.add(lblthis);
		
		ActionListener  change= actionEvent -> displayOption( (JRadioButton) actionEvent.getSource()   );
		
		JRadioButton radioOne = new JRadioButton("Vista General");
		radioOne.setSelected(true);
		radioOne.addActionListener(change);
		radioOne.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		radioOne.setBounds(94, 138, 180, 23);
		this.add(radioOne);
		
		
		JRadioButton radioTwo = new JRadioButton("Agregar");
		radioTwo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		radioTwo.setBounds(94, 199, 180, 23);
		radioTwo.addActionListener(change);
		this.add(radioTwo);
		
		JRadioButton radioThree = new JRadioButton("Actualizar");
		radioThree.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		radioThree.setBounds(94, 258, 180, 23);
		radioThree.addActionListener(change);
		this.add(radioThree);
		
		JRadioButton radioFour = new JRadioButton("Eliminar");
		radioFour.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		radioFour.setBounds(94, 333, 180, 23);
		radioFour.addActionListener(change);
		this.add(radioFour);
		
		JRadioButton radioFive = new JRadioButton("Consultar");
		radioFive.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		radioFive.setBounds(94, 391, 180, 23);
		radioFive.addActionListener(change);
		this.add(radioFive);
		
		
		ButtonGroup radios=new ButtonGroup();
		radios.add(radioOne);
		radios.add(radioTwo);
		radios.add(radioThree);
		radios.add(radioFour);
		radios.add(radioFive);
		
		
		
		
		JPanel imageVisor = new JPanel();
		imageVisor.setLayout(null);
		imageVisor.setBorder(new LineBorder(new Color(0, 0, 0)));
		imageVisor.setBounds(305, 79, 505, 407);
		this.add(imageVisor);
		
		bigOne = new JLabel("");
		imageResizer(bigOne,"/recursos/view.png",500,400);
		bigOne.setBounds(0, 0, 505, 407);
		imageVisor.add(bigOne);
		
		bigTwo = new JLabel("");
		imageResizer(bigTwo,"/recursos/add.png",500,400);
		bigTwo.setVisible(false);
		bigTwo.setBounds(0, 0, 505, 407);
		imageVisor.add(bigTwo);
		
		bigThree = new JLabel("");
		imageResizer(bigThree,"/recursos/update.png",500,400);
		bigThree.setBounds(0, 0, 505, 407);
		bigThree.setVisible(false);
		imageVisor.add(bigThree);
		
		bigFour = new JLabel("");
		imageResizer(bigFour,"/recursos/delete.png",500,400);
		bigFour.setBounds(0, 0, 505, 407);
		bigFour.setVisible(false);
		imageVisor.add(bigFour);
		
		bigFive = new JLabel("");
		imageResizer(bigFive,"/recursos/search.png",500,400);
		bigFive.setBounds(0, 0, 505, 407);
		bigFive.setVisible(false);
		imageVisor.add(bigFive);
		
		JLabel lblOpciones = new JLabel("Opciones:");
		lblOpciones.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblOpciones.setBounds(111, 92, 120, 31);
		this.add(lblOpciones);
		
		instruccion = new JLabel("<html><body>En la opción de Vista General, podra ver información de empleados"
				+ " seleccionandolos en la tabla superior, el sistema automaticamente cargara su información.</body></html>");
		instruccion.setVerticalAlignment(SwingConstants.TOP);
		instruccion.setBounds(288, 514, 522, 67);
		this.add(instruccion);
	
		
		
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
	
	
	//Método para cambiar los detalles de las opciones
	public void displayOption(JRadioButton selected) {
		hideAll();
		switch(selected.getText()) {
			
			case "Vista General":{
				bigOne.setVisible(true);
				instruccion.setText("<html><body>En la opción de Vista General, podra ver información de empleados"
						+ " seleccionandolos en la tabla superior, el sistema automaticamente cargara su información.</body></html>");
			}break;
			
			case "Agregar":{
				bigTwo.setVisible(true);
				instruccion.setText("<html><body>Para añadir un nuevo empleado al sistema debera completar todos los campos"
						+ " solicitados, posterior a esto dar click al boton de Añadir.</body></html>");
			}break;
			
			
			case "Actualizar":{
				bigThree.setVisible(true);
				instruccion.setText("<html><body>Para actualizar un empleado debera seleccionarlo en la tabla superior,"
						+ " posterior a esto debera realizar la modificación de campos y luego presionar el boton"
						+ " de Actualizar.</body></html>");
			}break;
			
			
			case "Eliminar":{
				bigFour.setVisible(true);
				instruccion.setText("<html><body>Para eliminar un empleado del sistema, debera selecionarlo en la tabla superior"
						+ ", antes de eliminarlo usted vera una vista General de ese empleado, si finalmente decidio eliminarlo debera presionar "
						+ "el boton de Eliminar.</body></html>");
			}break;
			
			
			case "Consultar":{
				bigFive.setVisible(true);
				instruccion.setText("<html><body>Para consultar debera insertar en el campo inferior la identificacion del empleado"
						+ " a buscar, si se encuentra el sistema cargara automaticamente los datos de ese empleado.</body></html>");
			}break;
			
			
		
		}
		
	}
	
	//Esconder todas las imagenes
	public void hideAll() {
		bigOne.setVisible(false);
		bigTwo.setVisible(false);
		bigThree.setVisible(false);
		bigFour.setVisible(false);
		bigFive.setVisible(false);
	}
	
}
