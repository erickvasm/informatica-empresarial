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
		barColor=new Color(65,65,65);
		BarraArriba.setBounds(0, 0, 800, 55);
		BarraArriba.setLayout(null);
		BarraArriba.setBackground(barColor);
		
		
		JLabel titlle = new JLabel("Sistema Bibliotecario");
		titlle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		titlle.setForeground(Color.WHITE);
		titlle.setBounds(20, 11, 400, 35);
		BarraArriba.add(titlle);
	
		JPanel inside = new JPanel();
		inside.setLayout(null);
		inside.setBounds(620, 0, 180, 55);
		inside.setBackground(new Color(28,28,28));
		BarraArriba.add(inside);
	
		
		//Mostrar icono de moverse
		JLabel iconer=new JLabel("");
		iconer.setBounds(50,5, 50,44);
		iconer.setBackground(Color.RED);
		imageResizer(iconer,"/recursos/move.png",50,44);
		inside.add(iconer);
		
		//Este componente cerrara el programa
		JButton closeButton = new JButton("X");
		closeButton.setBackground(Color.RED);
		closeButton.setForeground(Color.BLACK);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		closeButton.setBounds(120,5, 54, 44);
		inside.add(closeButton);
		
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
		
	
	
	//Obtenemos la barra dinamica
	public JPanel obtenerBarra() {
		return BarraArriba;
	}
		
}
