package controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;


//Panel de instrucciones
public class Instrucciones extends JPanel{

	//Componentes
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
	
	//Constructor
	public Instrucciones() {
		
		//Cofinguración del Panel
		this.setBorder(new TitledBorder(null, "Uso del Sistema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setLayout(null);
		
		//Componentes y características
		showOne = new JLabel("<html><body>El sistema pone a disposici\u00F3n las herramientas b\u00E1sicas para realizar gestiones en base  <br> a la informaci\u00F3n de los pacientes (Principal, A\u00F1adir, Actualizar, Eliminar, Recopilaci\u00F3n y Consulta)</body></html>");
		showOne.setBounds(59, 21, 738, 69);
		this.add(showOne);
		
		lblPestaaPricipal = new JLabel("1) Pesta\u00F1a Pricipal");
		lblPestaaPricipal.setBounds(30, 101, 114, 14);
		this.add(lblPestaaPricipal);
		
		showTwo = new JLabel("2) Pesta\u00F1a A\u00F1adir");
		showTwo.setBounds(317, 101, 114, 14);
		this.add(showTwo);
		
		showThree = new JLabel("3) Pesta\u00F1a Actualizar");
		showThree.setBounds(648, 101, 158, 14);
		this.add(showThree);
		
		showFour = new JLabel("5) Pesta\u00F1a Recopilaci\u00F3n y Consulta");
		showFour.setBounds(328, 343, 209, 14);
		this.add(showFour);
		
		showFive = new JLabel("4) Pesta\u00F1a Eliminar");
		showFive.setBounds(30, 343, 209, 14);
		this.add(showFive);
		
		espOne = new JLabel("<html>\r\n<body>\r\nAqu\u00ED podr\u00E1 ver los pacientes a\u00F1adidos al sistema\r\n</body>\r\n</html>");
		espOne.setVerticalAlignment(SwingConstants.TOP);
		espOne.setBounds(10, 247, 229, 97);
		this.add(espOne);
		
		espTwo = new JLabel("<html>\r\n<body>\r\nPara a\u00F1adir deber\u00E1 llenar los campos que se le \r\nsoliciten correctamente y despu\u00E9s dar al bot\u00F3n de \r\n\"Guardar\"\r\n</body>\r\n</html>");
		espTwo.setVerticalAlignment(SwingConstants.TOP);
		espTwo.setBounds(288, 247, 229, 97);
		this.add(espTwo);
		
		espThree = new JLabel("<html>\r\n<body>\r\nPara actualizar la informaci\u00F3n de un paciente deber\u00E1 clicar sobre\r\neste en la tabla presente, posterior a esto vera como los campos\r\nen la parte inferior se auto-rellenan, ahora solo debe modificar\r\nesos campos y clicar el bot\u00F3n \"Actualizar\"\r\n</body>\r\n</html>");
		espThree.setVerticalAlignment(SwingConstants.TOP);
		espThree.setBounds(613, 245, 229, 97);
		this.add(espThree);
		
		espFour = new JLabel("<html>\r\n<body>\r\nPara eliminar un paciente, deber\u00E1 clicar sobre este en la tabla presente,\r\nusted vera que autom\u00E1ticamente el bot\u00F3n de \"Eliminar\" se activa,\r\nal oprimir este bot\u00F3n el paciente se remueve por completo\r\n</body>\r\n</html>");
		espFour.setVerticalAlignment(SwingConstants.TOP);
		espFour.setBounds(10, 485, 229, 93);
		this.add(espFour);
		
		espFive = new JLabel("<html>\r\n<body>\r\nEn la secci\u00F3n izquierda podr\u00E1 ver datos estad\u00EDsticos generales,\r\nen la secci\u00F3n de la derecha podr\u00E1 realizar consultas\r\nde la informaci\u00F3n de un paciente proporcionando la identificaci\u00F3n debida\r\n</body>\r\n</html>");
		espFive.setVerticalAlignment(SwingConstants.TOP);
		espFive.setBounds(308, 499, 343, 89);
		this.add(espFive);
		
		
		//Visor de imagenes grandes
		visorImagenes = new JPanel();
		visorImagenes.setVisible(false);
		visorImagenes.setLayout(null);
		visorImagenes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		visorImagenes.setBackground(SystemColor.menu);
		visorImagenes.setBounds(147, 11, 521, 333);
		this.add(visorImagenes,new Integer(1),0);
		
		
		
		
		//Imagenes grandes
		JLabel bigOne = new JLabel("");
		bigOne.setVisible(false);
		imageResizer(bigOne,"src/recursos/main.png",501,311);
		bigOne.setBounds(10, 11, 501, 311);
		visorImagenes.add(bigOne);
		
		JLabel bigTwo = new JLabel("");
		bigTwo.setVisible(false);
		imageResizer(bigTwo,"src/recursos/add.png",501,311);
		bigTwo.setBounds(10, 11, 501, 311);
		visorImagenes.add(bigTwo);
		
		JLabel bigThree = new JLabel("");
		bigThree.setVisible(false);
		imageResizer(bigThree,"src/recursos/update.png",501,311);
		bigThree.setBounds(10, 11, 501, 311);
		visorImagenes.add(bigThree);
		
		JLabel bigFour = new JLabel("");
		bigFour.setVisible(false);
		imageResizer(bigFour,"src/recursos/delete.png",501,311);
		bigFour.setBounds(10, 11, 501, 311);
		visorImagenes.add(bigFour);
		
		JLabel bigFive = new JLabel("");
		bigFive.setVisible(false);
		imageResizer(bigFive,"src/recursos/search.png",501,311);
		bigFive.setBounds(10, 11, 501, 311);
		visorImagenes.add(bigFive);
		
		//Imagenes pequeñas
		imaTwo = new JLabel("");
		imaTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				imageAmplier(bigTwo,true,448,244);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				imageAmplier(bigTwo,false,0,0);
			}
		});
		imageResizer(imaTwo,"src/recursos/add.png",194,108);
		imaTwo.setBounds(278, 126, 194, 108);
		this.add(imaTwo);
		
		imaThree = new JLabel("");
		imaThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				imageAmplier(bigThree,true,85,255);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				imageAmplier(bigThree,false,0,0);
			}
		});
		imageResizer(imaThree,"src/recursos/update.png",194,108);
		imaThree.setBounds(579, 126, 194, 108);
		this.add(imaThree);
		
		
		
		
		imaOne = new JLabel("");
		imaOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				imageAmplier(bigOne,true,448,244);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				imageAmplier(bigOne,false,0,0);
			}
		});
		
		imageResizer(imaOne,"src/recursos/main.png",194,128);
		imaOne.setBounds(10, 126, 194, 108);
		this.add(imaOne);
		
		
		
		label_6 = new JLabel("");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				imageAmplier(bigFour,true,438,11);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				imageAmplier(bigFour,false,0,0);
			}
		});
		imageResizer(label_6,"src/recursos/delete.png",194,108);
		label_6.setBounds(0, 380, 194, 108);
		this.add(label_6);
		
		
		imaFour = new JLabel("");
		imageResizer(imaFour,"src/recursos/search.png",194,108);
		imaFour.setBounds(312, 380, 194, 108);
		imaFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				imageAmplier(bigFive,true,438,11);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				imageAmplier(bigFive,false,0,0);
			}
		});
		this.add(imaFour);

		
	}
	
	//Cargador de imagenes
	public void imageResizer(JLabel componente,String dir,int x,int y) {
		try {
			BufferedImage imagenBuffer= ImageIO.read(new File(dir));
			Image imagenRedimensionada = imagenBuffer.getScaledInstance(x,y, Image.SCALE_SMOOTH);
			ImageIcon icono=new ImageIcon(imagenRedimensionada);
			
			componente.setIcon(icono);
			
		}catch(Exception e) {
			componente.setText("");
		}
	}
	
	//Desplegador de imagenes grandes
	public void imageAmplier(JLabel componente,boolean show,int x,int y) {
		if(show) {
			visorImagenes.setLocation(x, y);
			componente.setVisible(true);
			visorImagenes.setVisible(true);
		}else{
			componente.setVisible(false);
			visorImagenes.setVisible(false);
		}
	}
	
	
}
