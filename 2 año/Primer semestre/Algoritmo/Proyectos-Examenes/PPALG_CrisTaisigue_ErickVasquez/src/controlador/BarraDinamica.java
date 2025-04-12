package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.midi.SysexMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//Clase barra dinamica
public class BarraDinamica{

	//Componentes y propiedades de la venta
	private Color colorOne=new Color(50,50,50);
	private Color colorTwo=new Color(40,40,40);
	private JPanel barra;//Barra personalizada
	private JLabel referencia;//Texto de estado de guardado
	
	
	//Contructor de la barra dinamica
	public BarraDinamica(JFrame ventana,JLabel referencia) {
		
		barra=new MotionPanel(ventana);
		this.referencia=referencia;
		
		
		//Caracteristicas
		barra.setBorder(new LineBorder(new Color(0, 0, 0)));
		barra.setBounds(0, 0, 700, 78);
		barra.setBackground(colorOne);
		barra.setLayout(null);
	
		
		//Label de imagen
		JLabel compIma = new JLabel("");
		compIma.setToolTipText("Entidad financiera, administrar actividades.");
		compIma.setBounds(10, 11, 56, 56);
		imageResizer(compIma,"/recursos/icon.png",56,56);
		barra.add(compIma);
		
		JLabel lblNewLabel = new JLabel("ADMINISTRAR - CAJEROS");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(101, 25, 374, 34);
		barra.add(lblNewLabel);
		
		JButton exitButton = new JButton("X");
		exitButton.setToolTipText("¿Desea cerrar el programa?");
		exitButton.setBackground(Color.RED);
		exitButton.setForeground(Color.BLACK);
		exitButton.setBounds(636, 11, 54, 54);
		barra.add(exitButton);
		
		
		
		
		//Eventos
		barra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				color(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				color(false);
			}
		});
		
		
		//Cuando se sale de la cuenta o se cambio de pestaña se resetean los punteros de edicion
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(referencia.getText().equalsIgnoreCase("Estado de guardado: Existen datos sin guardarse, presione 'Guardar Todo'")) {
					int i=JOptionPane.showConfirmDialog(null, "Existen datos sin guardarse, puede guardarlos mediante el boton que se aloja en la parte superior del sistema 'Guardar Todo', ¿Desea salir del sistema?","Saliendo...",JOptionPane.YES_NO_CANCEL_OPTION);
					if(i==0) {
						System.exit(0);
					}
				}else {
					System.exit(0);
				}
			}
		});
		
	
	}
	
	//Establecer color de la barra dinamica
	public void color(boolean type) {
		if(type) {
			barra.setBackground(colorTwo);
		}else {
			barra.setBackground(colorOne);
		}
	}
	
	
	//Cargador de imagenes
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
	
	//Obtener el panel
	public JPanel obtenerBarra() {
		return this.barra;
	}
}
