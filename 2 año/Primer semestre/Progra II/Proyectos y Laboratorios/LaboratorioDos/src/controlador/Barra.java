package controlador;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Clase de barra dinamica
public class Barra{
	
	//Componentes
	private Color barColor;
	private JPanel BarraArriba;
	
	//Constructor
	public Barra(JFrame ventana) {
		
		//Componentes y caracteristicas
		BarraArriba = new MotionPanel(ventana);
		barColor=new Color(54,54,54);
		BarraArriba.setBounds(0, 0, 900, 57);
		BarraArriba.setLayout(null);
		BarraArriba.setBackground(barColor);
		
		JLabel titlle = new JLabel("Planilla Empleados");
		titlle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		titlle.setForeground(Color.WHITE);
		titlle.setBounds(128, 11, 400, 35);
		BarraArriba.add(titlle);
	
	
		JLabel image = new JLabel();
		imageResizer(image,"/recursos/Employee.png",55,55);
		image.setBounds(1, 1, 55, 55);
		BarraArriba.add(image);
	
		
		
		
		
		//Este componente cerrara el programa
		JButton closeButton = new JButton("X");
		closeButton.setBackground(Color.RED);
		closeButton.setForeground(Color.BLACK);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		closeButton.setBounds(828, 11, 62, 35);
		BarraArriba.add(closeButton);
		
	}
	
	
	//Obtenemos la barra dinamica
	public JPanel obtenerBarra() {
		return BarraArriba;
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
