package controlador;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import almacenamiento.DataManage;
import almacenamiento.Escribir;
import almacenamiento.Leer;
import estructuraDinamica.ListaDoble;
import modelo.Cajero;
import modelo.Transaccion;
import modelo.Usuario;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Clase Interfaz, visual primaria
public class Interfaz extends JFrame {

	//LED (Estructuras Dinamicas) donde se guarda la informacion del sistema
	private ListaDoble<Transaccion> transacciones=new <Transaccion>ListaDoble();
	private ListaDoble<Usuario> usuarios=new <Usuario>ListaDoble();
	private ListaDoble<Cajero> cajeros=new <Cajero>ListaDoble();
	
	
	
	//Posibilitar el acceso al archivo
	private DataManage manipulador=new DataManage();
	private Leer lector=new Leer(manipulador);
	private Escribir escritor=new Escribir(manipulador,transacciones,usuarios,cajeros);
	

	//Componentes de la ventana
	private JPanel contentPane;
	private final int X=700,Y=700;
	private JLabel status;
	
	
	//Paneles de opciones
	MenuPrincipal menu;
	CajeroInterfaz cajero;
	Crear registro;
	
	
	//Constructor
	public Interfaz() {
		
		//Panel principal
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(60,60,60));
		contentPane.setLayout(null);
		
		//Props
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(X, Y);
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setTitle("Financiera-Administrar Cajeros");
		this.setLocationRelativeTo(null);

		//Agregar icono
		agregarIcono();
		
		
		
		//Estado de guardado
		status = new JLabel("Estado de guardado:");
		status.setForeground(Color.WHITE);
		status.setBounds(146, 79, 515, 30);
		contentPane.add(status);
		
		
		
		//Barra personalizada
		BarraDinamica barra=new BarraDinamica(this,status);
		contentPane.add(barra.obtenerBarra());
		
		
		
		//Panel de opciones
		JTabbedPane panelOpciones = new JTabbedPane(JTabbedPane.TOP);
		panelOpciones.setBounds(10, 112, 680, 577);
		contentPane.add(panelOpciones);
		
		
		//>Opcion de Menu Principal
		menu = new MenuPrincipal(transacciones,lector,usuarios,status);
		panelOpciones.addTab("Menu Principal", null, menu, null);
		
		
		//>Opcion de Cajero Automatico
		cajero = new CajeroInterfaz(cajeros,transacciones,usuarios,status);
		panelOpciones.addTab("Cajero Automatico", null, cajero, null);
		
		
		//>Opcion de Registro
		registro = new Crear(cajeros,usuarios,status);
		panelOpciones.addTab("Registrar datos", null, registro, null);
		
		//Guardar todos los datos contenidos en las estructuras dinamicas
		JButton guardar = new JButton("Guardar Todo");
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		guardar.setBounds(10, 86, 128, 23);
		contentPane.add(guardar);
		
		//Recuperar
		recuperar();
	}
	
	//Recuperar los clientes, transacciones y cajeros del fichero
	public void recuperar() {
		try {
			lector.transformarLeer(transacciones, usuarios, cajeros);
			status.setText("Estado de guardado:Recuperacion desde archivo exitosa");
		}catch(Exception e) {
			
			//El fichero presenta un formato de guardado corrupto
			
			status.setText("<html><body>Estado de guardado:Ocurrio un error al recuperar la informacion, formato corrupto</body></html>");
			if(!this.isVisible()) {
				this.setVisible(true);
			}
			
			int i=JOptionPane.showConfirmDialog(null, "A ocurrido un error al recuperar los datos del archivo, esto puede deberse a que existe un formato de datos corrupto ¿Desea resetear el archivo?","Error al Recupearar",JOptionPane.YES_NO_OPTION);
			
			if(i==0) {
				try {
					escritor.Limpiar();
					status.setText("<html><body>Estado de guardado:fichero reseteado con exito</body></html>");
					escritor.guardar();
				}catch(Exception o) {
					
				}
			}
		}
	}
	
	
	
	
	//Guardar toda la informacion contenida en las estructuras dinamicas
	public void guardar() {
		try {
			escritor.guardar();
			status.setText("Estado de guardado:Guardado Exitoso");
		}catch(Exception e) {
			status.setText("Estado de guardado:Ocurrio un error al tratar de guardar");
		}
	}
	
	
	//Agregar icono a la ventana
	public void agregarIcono() {
		try {
		    Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/icon.png"));
		    this.setIconImage(iconoPropio);
		}catch(Exception e) {
			
		}
	}
	
}