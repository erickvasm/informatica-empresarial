/*
 * @author Cris Taisigue
 * @author Erick Vasquez
 * 
 * 
 * */
package ep3_col_cristaisigue_erickvasquez;


import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

//Clase Interfaz ventana principal
public class Interfaz extends JFrame {

	//Propiedades de la ventana
	private JPanel contentPane;
	private final int X=650,Y=700;
	
	private Subasta subastaGanado=new Subasta();//La subasta que contendra las caracteristicas que haran posible la simulacion
	private ColaCamion iniciarDescarga=new ColaCamion();//Cola de respaldo a la hora de descargar
	private Camion currentDescargando=null;//Camion a descargar
	private CabezaRes currentCabezaRes=null;//Cabeza de res subastada
	
	//general
	private JTabbedPane tabbedPane;
	private JLabel animaCount;
	
	
	
	//Opcion de agregar
	private JTextField nombreGanaderia;
	private JTextField placaCamion;
	private JTextField capacidad;
	private Tabla tablaAgregar;
	private JLabel agregarAviso;
	
	//Opcion de descargar
	private JLabel lblDescargandoCamionDe;
	private Tabla tablaDescargar;
	private JLabel avisoDescargar;
	private JButton enOrden;
	private JButton btnEmpezarADescargar;
	
	
	//Opcion subastar
	private JLabel ganaderiaPer;
	private JLabel lblPrecio;
	private JLabel lblSello;
	private JLabel pesoAnimal;
	private JLabel avisoSubasta;
	private Tabla tablaSubasta;
	private JComboBox comboBox;
	private JButton btnSubastar;
	private JButton btnNuevaSubasta;
	private JButton btnIniciarSubasta;
	private JButton btnObtenerInforme;
	private JLabel lblNewLabel_1;
	private JLabel lblInformeDeLa;
	private JLabel numeroCabezas;
	private JLabel montoTotal;
	private JLabel gananciaPorSubasta;
	private JLabel lblInformeDeGanaderias;
	private JScrollPane scrollPane_3;
	private JTextArea informeArea;
	
	
	
	//Constructor
	public Interfaz() {
		
		
		//Propiedades de la ventana
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(X,Y);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.contentPane = new JPanel();
		this.setTitle("MIADEPAN Subasta");
		this.contentPane.setLayout(null);
		this.setContentPane(contentPane);
		
		
		
		
		//Panel de pestanas
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 624, 649);
		contentPane.add(tabbedPane);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Opcion de la subasta
		JPanel subasta = new JPanel();
		subasta.setBorder(new TitledBorder(null, "Subasta de Animales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		subasta.setLayout(null);
		tabbedPane.addTab("Subasta", null, subasta, null);
		
		JLabel lblNewLabel = new JLabel("<html><body>Instrucciones: Si desea iniciar la subasta presione el boton de 'Iniciar Subasta' tome encuenta que las demas opciones se desactivaran y usted debera finaliza la subasta e iniciar una nueva con el  boton 'Nueva Subasta' si desea volver a ingresar las ganaderias con sus respectivos camiones y cabezas de res.</body></html>");
		lblNewLabel.setBounds(10, 25, 599, 58);
		subasta.add(lblNewLabel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 136, 599, 274);
		subasta.add(scrollPane_2);
		
		tablaSubasta = new Tabla(3);
		scrollPane_2.setViewportView(tablaSubasta);
		
		btnNuevaSubasta = new JButton("Nueva Subasta");
		btnNuevaSubasta.setEnabled(false);
		btnNuevaSubasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetAll();
			}
		});
		btnNuevaSubasta.setBounds(472, 94, 137, 23);
		subasta.add(btnNuevaSubasta);
		
		btnIniciarSubasta = new JButton("Iniciar Subasta");
		btnIniciarSubasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(subastaGanado.getCamionesDescargados()<=2) {
					avisoSubasta.setText("Debera descargar al menos 3 camiones");
				}else {
					iniciarSubasta(btnIniciarSubasta);
				}
			}
		});
		btnIniciarSubasta.setBounds(328, 94, 134, 23);
		subasta.add(btnIniciarSubasta);
	
		btnSubastar = new JButton("SUBASTAR!");
		btnSubastar.setEnabled(false);
		
		btnSubastar.setBounds(481, 587, 128, 23);
		subasta.add(btnSubastar);
		
		JLabel lblDetallesDeLa = new JLabel("DETALLES DE LA CABEZA DE RES SUBASTADA");
		lblDetallesDeLa.setBounds(10, 421, 294, 14);
		subasta.add(lblDetallesDeLa);
		
		ganaderiaPer = new JLabel("Ganaderia Perteneciente:");
		ganaderiaPer.setBounds(39, 457, 265, 14);
		subasta.add(ganaderiaPer);
		
		pesoAnimal = new JLabel("Peso(KG):");
		pesoAnimal.setBounds(39, 482, 159, 14);
		subasta.add(pesoAnimal);
		
		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(352, 457, 207, 14);
		subasta.add(lblPrecio);
		
		lblSello = new JLabel("Sello:");
		lblSello.setBounds(352, 482, 243, 14);
		subasta.add(lblSello);
		
		animaCount = new JLabel("Animales que fueron descargados:0");
		animaCount.setBounds(20, 94, 213, 14);
		subasta.add(animaCount);
		
		avisoSubasta = new JLabel("");
		avisoSubasta.setBounds(20, 562, 429, 48);
		subasta.add(avisoSubasta);
		
		JLabel lblGanaderiaCompradora = new JLabel("Ganaderia compradora");
		lblGanaderiaCompradora.setBounds(39, 514, 159, 14);
		subasta.add(lblGanaderiaCompradora);
		
		comboBox = new JComboBox();
		comboBox.setBounds(208, 511, 195, 20);
		subasta.add(comboBox);

		subasta.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				resetSubasta();
				animaCount.setText("Animales que fueron descargados:"+subastaGanado.getSubastaCabezaRes().getLongitud());
			}
		});
		btnSubastar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subastar();
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Opcion de agregar camion y ganaderia
		JPanel agregar = new JPanel();
		agregar.setBorder(new TitledBorder(null, "AGREGAR", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("Agregar", null, agregar, null);
		agregar.setLayout(null);
		
		JLabel lblGanaderia = new JLabel("GANADERIA:");
		lblGanaderia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGanaderia.setBounds(22, 39, 134, 22);
		agregar.add(lblGanaderia);
		
		JLabel lblCamionDeLa = new JLabel("CAMION DE LA GANADERIA:");
		lblCamionDeLa.setBounds(22, 122, 184, 14);
		agregar.add(lblCamionDeLa);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(506, 571, 103, 39);
		agregar.add(btnAgregar);
		
		nombreGanaderia = new JTextField();
		nombreGanaderia.setBounds(242, 72, 168, 20);
		agregar.add(nombreGanaderia);
		nombreGanaderia.setColumns(10);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la ganaderia:");
		lblNombreDeLa.setBounds(55, 75, 177, 14);
		agregar.add(lblNombreDeLa);
		
		JLabel lblPlacaCamion = new JLabel("Placa Camion:");
		lblPlacaCamion.setBounds(58, 157, 98, 14);
		agregar.add(lblPlacaCamion);
		
		placaCamion = new JTextField();
		placaCamion.setBounds(146, 154, 86, 20);
		agregar.add(placaCamion);
		placaCamion.setColumns(10);
		
		JLabel lblCapacidad = new JLabel("Capacidad(Toneladas):");
		lblCapacidad.setBounds(283, 157, 154, 14);
		agregar.add(lblCapacidad);
		
		capacidad = new JTextField();
		capacidad.setBounds(468, 154, 86, 20);
		agregar.add(capacidad);
		capacidad.setColumns(10);
		
		JLabel lblCabezaDeRes = new JLabel("CABEZA DE RES");
		lblCabezaDeRes.setBounds(22, 234, 134, 14);
		agregar.add(lblCabezaDeRes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 259, 524, 301);
		agregar.add(scrollPane);
		
		tablaAgregar = new Tabla(1);
		scrollPane.setViewportView(tablaAgregar);
		
		JButton btnAgregarRes = new JButton("Agregar Res");
		btnAgregarRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaAgregar.AgregarCelda();
			}
		});
		btnAgregarRes.setBounds(447, 230, 107, 23);
		agregar.add(btnAgregarRes);
		
		JButton btnQuitarRes = new JButton("Quitar Res");
		btnQuitarRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaAgregar.EliminarCelda();
			}
		});
		btnQuitarRes.setBounds(334, 230, 103, 23);
		agregar.add(btnQuitarRes);
		
		agregarAviso = new JLabel("");
		agregarAviso.setBounds(22, 571, 474, 39);
		agregar.add(agregarAviso);
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		
		agregar.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				resetAgregar();
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		//Opcion de descargar los camiones en cola
		JPanel descargar = new JPanel();
		descargar.setBorder(new TitledBorder(null, "Descargar Camion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		descargar.setLayout(null);
		tabbedPane.addTab("Descargar", null, descargar, null);
		
		lblDescargandoCamionDe = new JLabel("Descargando camion de la ganaderia:");
		lblDescargandoCamionDe.setBounds(70, 90, 423, 14);
		descargar.add(lblDescargandoCamionDe);
		
		btnEmpezarADescargar = new JButton("Empezar a descargar");
		btnEmpezarADescargar.setBounds(32, 128, 161, 23);
		descargar.add(btnEmpezarADescargar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 299, 548, 255);
		descargar.add(scrollPane_1);
		
		tablaDescargar = new Tabla(2);
		tablaDescargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tablaDescargar.isEditing()) {
					tablaDescargar.getCellEditor().stopCellEditing();
				}
			}
		});
		
		enOrden = new JButton("Descargar en Orden");
		
		tablaDescargar.botonColumna().obtenerBoton().addActionListener(new ActionListener() {
			
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	        	enOrden.setEnabled(false);
	        	int index=tablaDescargar.comprober(tablaDescargar.botonColumna().obtenerIndex());
	        	descargar(false,index);
	        }
	    });
		
		scrollPane_1.setViewportView(tablaDescargar);
		
		enOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descargar(true,0);
			}
		});
		enOrden.setBounds(379, 177, 185, 30);
		descargar.add(enOrden);
		
		JLabel lblsiDeseaEmpezar = new JLabel("<html><body>Instrucciones:Si desea empezar a descargar camiones debera presionar 'Empezar a descargar', tenga en cuenta que si presiona dicho boton, usted debera a descargar todos los camiones que existan en cola.</body></html>");
		lblsiDeseaEmpezar.setBounds(32, 29, 536, 50);
		descargar.add(lblsiDeseaEmpezar);
		
		JLabel lblInstrucionesSiDesea = new JLabel("<html><body>Instruciones:si desea descargar las cabezas de rese en el orden por defecto presione el boton 'Descargar en Orden' si  en cambio lo desea hacer en un orden determinado por usted debera presionar 'Descargar' en cada representacion (fila) de la cabeza de res, tome en cuenta que si presiona dicho boton, el boton de 'Descargar en Orden' se desactivara.</body></html>");
		lblInstrucionesSiDesea.setBounds(32, 177, 337, 111);
		descargar.add(lblInstrucionesSiDesea);
		
		avisoDescargar = new JLabel("");
		avisoDescargar.setBounds(32, 565, 548, 45);
		descargar.add(avisoDescargar);
		
		btnEmpezarADescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descargarCamiones();
			}
		});
		
		descargar.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				resetDescargar(false);
			}
		});
		
		
		
		
		
		
		//Opcion de presentar el informe
		JPanel informe = new JPanel();
	
		informe.setLayout(null);
		tabbedPane.addTab("Informe", null, informe, null);
		
		btnObtenerInforme = new JButton("Obtener Informe");
		
		btnObtenerInforme.setBounds(474, 587, 135, 23);
		informe.add(btnObtenerInforme);
		
		lblNewLabel_1 = new JLabel("<html><body>Instrucciones: si desea obtener el infome del dia, debe tomar en cuenta que se haya llevado a cabo una subasta y esta este por terminada, si esto es asi, al presionar el boton de 'Obtener Informe' vera los informes correspondientes.</body></html>");
		lblNewLabel_1.setBounds(10, 36, 599, 62);
		informe.add(lblNewLabel_1);
		
		lblInformeDeLa = new JLabel("Informe De La Subasta");
		lblInformeDeLa.setBounds(37, 109, 200, 14);
		informe.add(lblInformeDeLa);
		
		numeroCabezas = new JLabel("Numero Cabezas Subastadas:");
		numeroCabezas.setBounds(76, 134, 242, 14);
		informe.add(numeroCabezas);
		
		montoTotal = new JLabel("Monto Total Subastado:");
		montoTotal.setBounds(76, 169, 297, 14);
		informe.add(montoTotal);
		
		gananciaPorSubasta = new JLabel("Ganancia por lo Subastado:");
		gananciaPorSubasta.setBounds(76, 202, 297, 14);
		informe.add(gananciaPorSubasta);
		
		lblInformeDeGanaderias = new JLabel("Informe de Ganaderias y Camiones");
		lblInformeDeGanaderias.setBounds(37, 235, 227, 14);
		informe.add(lblInformeDeGanaderias);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(37, 273, 529, 298);
		informe.add(scrollPane_3);
		
		informeArea = new JTextArea();
		informeArea.setLineWrap(true);
		informeArea.setEditable(false);
		
		scrollPane_3.setViewportView(informeArea);
		
		btnObtenerInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verInforme();
			}
		});
		
		informe.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				resetInforme();
			}
		});	
		
		
		
		
		this.setVisible(true);
		
	}
	
	//Metodo para contral si la subasta termino o se inicio una nueva
	public void resetAll() {
		subastaGanado.getGanaderias().clear();
		subastaGanado.setGanancia(0);
		subastaGanado.setTerminado(false);
		subastaGanado.setMontoTotal(0);
		subastaGanado.setNumeroSubastado(0);
		subastaGanado.setCamionesDescargados(0);
		int sizeCamion=subastaGanado.getCamionesCola().getLongitud();
		for(int cont=0;cont<sizeCamion;cont++) {
			Camion current=subastaGanado.getCamionesCola().Leer();
		}
		
		resetSubasta();
	}
	
	
	
	
	
	
	

					//Metodos de la opcion de informe
	//Ver informe
	public void verInforme() {
		if(subastaGanado.isTerminado()) {
			ColaCamion duplicant=new ColaCamion();
			
			informeArea.setText("\t\tInforme Ganaderia & Camiones");
			
			numeroCabezas.setText("Numero Cabezas Subastadas:"+subastaGanado.getNumeroSubastado());
			montoTotal.setText("Monto Total Subastado:"+subastaGanado.getMontoTotal());
			gananciaPorSubasta.setText("Ganancia por lo Subastado:"+subastaGanado.getGanancia());
			int size=subastaGanado.getCamionesCola().getLongitud();
			for(int cont=0;cont<size;cont++) {
				Camion current=null;
				current=subastaGanado.getCamionesCola().Leer();
				if(current!=null) {
					Ganaderia ganaderia=null;
					ganaderia=obtenerGanaderia(current.getNombreGanaderia());
					if(ganaderia!=null) {
						String cabezasRes="";
						int contador=1;
						for(CabezaRes cab:current.getGanado()) {
							cabezasRes+="\n\t"+contador+") Peso(kg):"+cab.getPeso()+" Sello:"+cab.getSello();
							contador++;
						}
						
						informeArea.append("\n________________________________\n"+
					"\tGanaderia:"+ganaderia.getNombreGanaderia()+"\n"+"Monto Total Invertido:"+ganaderia.getMontoInvertido()+"\n"+"N° Cabezas Presentadas:"+ganaderia.getNumeroPresentado()
					+"\nN° Cabezas Compradas:"+ganaderia.getNumeroComprado()+"\nCabezas Res Compradas:\n"+cabezasRes+"\n\tCamion de la ganaderia:\nNumero de Cabezas"+current.getGanado().size()+
					"\nMonto Total Efectivo:"+ganaderia.getMontoInvertido()+"\nCabezas de Res:\n"+cabezasRes);
					}
					duplicant.Escribir(current);
				}
				
				
			}
			informeArea.setCaretPosition(0);;
			
			int sizeD=duplicant.getLongitud();
			for(int contTwo=0;contTwo<sizeD;contTwo++) {
				subastaGanado.getCamionesCola().Escribir(duplicant.Leer());
			}
			
		}else {
			informeArea.setText("Debera iniciar y terminar una subasta");
		}
	}
	
	//Resetear los campos de la opcion de ver informe
	public void resetInforme() {
		numeroCabezas.setText("Numero Cabezas Subastadas:");
		montoTotal.setText("Monto Total Subastado:");
		gananciaPorSubasta.setText("Ganancia por lo Subastado:");
		informeArea.setText("");
	}
	
	
	
	
	
	
	
					//Metodos de la opcion Subastar
	
	//Prepara la subasta y se presenta la primera cabeza de res
	public void iniciarSubasta(JButton boton) {
		subastaGanado.setNumeroSubastado(subastaGanado.getSubastaCabezaRes().getLongitud());
		boton.setEnabled(false);
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
		tablaSubasta.presentar(subastaGanado.getSubastaCabezaRes());
		cargarSiguiente();//se presenta la primera cabeza de res
	}
	
	//Se presentan las cabezas de res
	public void cargarSiguiente() {
		CabezaRes nuevo=new CabezaRes();
		nuevo.setGanaderiaPerteneciente((String)tablaSubasta.getValueAt(0,3));
		nuevo.setPeso(Double.parseDouble((String)tablaSubasta.getValueAt(0,1)));
		nuevo.setSello((String)tablaSubasta.getValueAt(0,2));
		ganaderiaPer.setText("Ganaderia Perteneciente:"+nuevo.getGanaderiaPerteneciente());
		pesoAnimal.setText("Peso(KG):"+nuevo.getPeso());
		lblSello.setText("Sello:"+nuevo.getSello());
		lblPrecio.setText("Precio:"+(nuevo.getPeso()*754));
		currentCabezaRes=nuevo;
		fillComboBox(nuevo.getGanaderiaPerteneciente());
		btnSubastar.setEnabled(true);
		tablaSubasta.obtenerModelo().removeRow(0);
	}
	
	//Autollenar la opcion de la ganaderia compradora
	public void fillComboBox(String except) {
		comboBox.setModel(new DefaultComboBoxModel(new String[] {}));
		for(Ganaderia ganaderia:subastaGanado.getGanaderias()) {
			if(!ganaderia.getNombreGanaderia().equalsIgnoreCase(except)) {
				comboBox.addItem(ganaderia.getNombreGanaderia());
			}
		}
	}
	
	
	//Obtener ganaderia por su nombre
	public Ganaderia obtenerGanaderia(String nombre) {
		Ganaderia buscado=null;
		for(int cont=0;cont<subastaGanado.getGanaderias().size();cont++) {
			if((subastaGanado.getGanaderias().get(cont).getNombreGanaderia().equalsIgnoreCase(nombre)) &&(buscado==null)) {
				buscado=subastaGanado.getGanaderias().get(cont);
			}
		}
		return buscado;
	}
	
	
	
	//Asignar al corral de la ganaderia comprobadora y modificar calculos que se mostraran despues
	public void subastar() {
		if(currentCabezaRes!=null) {
			Ganaderia buscada=null;
			buscada=obtenerGanaderia((String)comboBox.getSelectedItem());
			if(buscada!=null) {
				buscada.getCorral().Escribir(currentCabezaRes);
				subastaGanado.setMontoTotal(subastaGanado.getMontoTotal()+(currentCabezaRes.getPeso()*754));
				subastaGanado.setGanancia(subastaGanado.getGanancia()+(((currentCabezaRes.getPeso()*754)*15)/100));
				buscada.setNumeroComprado(buscada.getNumeroComprado()+1);
				buscada.setMontoInvertido(buscada.getMontoInvertido()+(currentCabezaRes.getPeso()*754));
				currentCabezaRes=null;
			}
			if(tablaSubasta.getRowCount()!=0) {
				cargarSiguiente();
			}else {
				
				
				subastaGanado.setTerminado(true);
				resetSubasta();
				cargarCabezasRes();
				
				avisoSubasta.setText("<html><body>Subasta terminada, si desea ver el informe del dia dirijase a la opcion 'Informe', si desea empezar una nueva subata presione 'Nueva Subasta'</body></html>");
			}
		}
	}
	

	//Cuando la subasta termine, los camiones asignados a las ganaderias se cargaran con las cabezas de res en los corrales 
	public void cargarCabezasRes() {
		ColaCamion camionesCargar=new ColaCamion();
		int size=subastaGanado.getCamionesCola().getLongitud();
		for(int cont=0;cont<size;cont++) {
			Camion actual=subastaGanado.getCamionesCola().Leer();
			if(actual!=null) {
				
				Ganaderia ganaderia=obtenerGanaderia(actual.getNombreGanaderia());
				if(ganaderia!=null) {
					int sizeTwo=ganaderia.getCorral().getLongitud();
					for(int contTwo=0;contTwo<sizeTwo;contTwo++) {
						actual.getGanado().add(ganaderia.getCorral().Leer());
					}
					camionesCargar.Escribir(actual);
					
				}
			}
		}
		
		
		int sizeThree=camionesCargar.getLongitud();
		for(int contThree=0;contThree<sizeThree;contThree++) {
			Camion actual=camionesCargar.Leer();
			if(actual!=null) {
				subastaGanado.getCamionesCola().Escribir(actual);
			}
		}
	}
	
	
	
	//Resetear los campos de subasta
	public void resetSubasta() {
		ganaderiaPer.setText("Ganaderia Perteneciente:");
		pesoAnimal.setText("Peso(KG):");
		lblSello.setText("Sello:");
		lblPrecio.setText("Precio:");
		comboBox.setModel(new DefaultComboBoxModel());
		if(subastaGanado.isTerminado()) {
			avisoSubasta.setText("");
			btnIniciarSubasta.setEnabled(false);
			btnNuevaSubasta.setEnabled(true);
			btnSubastar.setEnabled(false);
			tabbedPane.setEnabledAt(3, true);
			tabbedPane.setEnabledAt(0, true);
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.setEnabledAt(2, false);
		}else {
			avisoSubasta.setText("");
			btnIniciarSubasta.setEnabled(true);
			btnNuevaSubasta.setEnabled(false);
			btnSubastar.setEnabled(false);
			tabbedPane.setEnabledAt(3, true);
			tabbedPane.setEnabledAt(0, true);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setEnabledAt(2, true);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
						//Metodos de la opcion agregar
	
	//Agregar un nuevo camion y ganaderia
	public void agregar() {
		if(subastaGanado.getCamionesCola().getLongitud()!=6) {
			if(nombreGanaderia.getText().length()<=2) {
				agregarAviso.setText("<html><body>Ingrese al menos 3 caracteres en el campo del nombre de la ganaderia</body></html>");
			}else {
				if((placaCamion.getText().length()<=5) || (placaCamion.getText().length()>6)) {
					agregarAviso.setText("<html><body>Ingrese 6 caracteres en la placa del camion, no menos no mas</body></html>");
				}else {
					if(verificarPlaca(placaCamion.getText())) {
						try {
							int valor=Integer.parseInt(capacidad.getText());
							if(valor!=0) {
								
								if(tablaAgregar.getModel().getRowCount()!=0) {
									boolean comprobacion[]=tablaAgregar.comprobarFilas();
									if((comprobacion[0]) && (comprobacion[1])) {
										if(tablaAgregar.capacidad()!=-1) {
											try {
												if(tablaAgregar.capacidad()>(Integer.parseInt(capacidad.getText())*1000)) {
													agregarAviso.setText("Se excede la capacidad del camion");
												}else {
													
													if(buscarCoincidencias(nombreGanaderia.getText())) {
														agregarAviso.setText("El nombre de la ganaderia ya esta en uso");
													}else {
														Ganaderia tempGanaderia=new Ganaderia();
														tempGanaderia.setNombreGanaderia(nombreGanaderia.getText());
														tempGanaderia.setNumeroPresentado(tablaAgregar.getRowCount());
														
														Camion tempCamion=new Camion();
														tempCamion.setCapacidad(Integer.parseInt(capacidad.getText()));
														tempCamion.setPlacaCamion(placaCamion.getText());
														tempCamion.setNombreGanaderia(nombreGanaderia.getText());
														tempCamion.setGanado(tablaAgregar.obtenerReses(nombreGanaderia.getText()));
														
														
														subastaGanado.getGanaderias().add(tempGanaderia);
														subastaGanado.getCamionesCola().Escribir(tempCamion);
														
														
														resetAgregar();
														agregarAviso.setText("Se ha agrego el camion");
														
														
													}
													
													
												}
											}catch(Exception o) {
												
											}
										}
									}else if(!(comprobacion[0]) && (comprobacion[1])) {
										agregarAviso.setText("<html><body>Existe un sello con menos o mas de 3 caracteres en la tabla (columna sello)</body></html>");
									}else if((comprobacion[0]) && (!comprobacion[1])) {
										agregarAviso.setText("<html><body>Existe un sello repetido en la tabla</body></html>");
									}else if((!comprobacion[0]) && (!comprobacion[1])) {
										agregarAviso.setText("<html><body>En la columna de peso debera ingresar valores numericos enteros positivos</body></html>");
									}
								}else {
									agregarAviso.setText("<html><body>Ingrese al menos una cabeza de res</body></html>");
								}
								
								
								
							}else {
								
								agregarAviso.setText("<html><body>Ingrese valores numericos enteros positivos en el campo de capacidad</body></html>");
								
								
							}
						}catch(Exception e) {
							agregarAviso.setText("<html><body>Ingrese valores numericos enteros positivos en el campo de capacidad</body></html>");
						}
					}else {
						agregarAviso.setText("<html><body>Respete el formato del numero de placa 3 Letras seguidas por 3 numeros</body></html>");
					}
				}
			}
		}else {
			agregarAviso.setText("<html><body>La subasta alcanzo el número maximo de camiones (6)</body></html>");
		}
	}
	
	
	//Buscar si existe una ganaderia con el mismo nombre
	public boolean buscarCoincidencias(String nombreBuscado) {
		boolean response=false;
		if(subastaGanado.getGanaderias().size()!=0) {
			for(int cont=0;cont<subastaGanado.getGanaderias().size();cont++) {
				if((subastaGanado.getGanaderias().get(cont)!=null) && (!response)) {
					if(subastaGanado.getGanaderias().get(cont).getNombreGanaderia().equalsIgnoreCase(nombreBuscado)) {
						response=true;
					}
				}
			}
		}
		return response;
	}
	
	
	
	//Resetear la opcion de agregar
	public void resetAgregar() {
		agregarAviso.setText("");
		nombreGanaderia.setText("");
		capacidad.setText("");
		placaCamion.setText("");
		tablaAgregar.reset();
	}	
	
	
	
	
	//Verificar que la placa provista este correcta
	public boolean verificarPlaca(String placa) {
		boolean response=true;
		try {
			for(int cont=0;cont<placa.length();cont++) {
				try {
					int valor=Integer.parseInt(Character.toString(placa.charAt(cont)));
					
					if((cont==0) || (cont==1) || (cont==2)) {
						response=false;
					}
				}catch(Exception e) {
					if((cont==3) || (cont==4) || (cont==5)) {
						response=false;
					}
				}
			}
		}catch(Exception e) {
			response=false;
		}
		return response;
	}
	
	
	
	
	
	
	
	
							//METODOS DE LA OPCION DESCARGAR CAMIONES
	
	//Empezar a descargar los camiones
	public void descargarCamiones() {
		btnEmpezarADescargar.setEnabled(false);
		if(subastaGanado.getCamionesCola().getLongitud()<=2) {
			avisoDescargar.setText("<html><body>Debera existir al menos 3 camiones en la cola</body></html>");					
			btnEmpezarADescargar.setEnabled(true);
		}else {
			
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.setEnabledAt(3, false);
			siguienteCamion();//Se presenta el primer camion
		}
	}
	
	//Descargar camion de dos formas
	public void descargar(boolean moda,int index) {
		if(moda) {
			//Por orden
			if(currentDescargando!=null) {
				ArrayList<CabezaRes> descarga=currentDescargando.getGanado();
				for(int cont=0;cont<descarga.size();cont++) {
					if(descarga.get(cont)!=null) {
						subastaGanado.getSubastaCabezaRes().Escribir(descarga.get(cont));
					}
				}
				currentDescargando.getGanado().clear();
				iniciarDescarga.Escribir(currentDescargando);
				siguienteCamion();
			}else {
				avisoDescargar.setText("Empieze la descarga...");
			}
		}else {
			//Seleccion manual
	    	subastaGanado.getSubastaCabezaRes().Escribir(currentDescargando.getGanado().get(index));
        	currentDescargando.getGanado().remove(index);
        	if(tablaDescargar.getRowCount()==0) {
        		iniciarDescarga.Escribir(currentDescargando);
        		siguienteCamion();
        	}
		}
	}
	
	
	//Se obtiene el proximo camion a descargar
	public void siguienteCamion() {
		currentDescargando=subastaGanado.getCamionesCola().Leer();
		if(currentDescargando!=null) {
			if(currentDescargando.getGanado().size()!=0) {
				enOrden.setEnabled(true);
				tablaDescargar.visualizarDescarga(currentDescargando.getGanado());
				lblDescargandoCamionDe.setText("Descargando camion de la ganaderia:"+currentDescargando.getNombreGanaderia());
			}else {
				
				iniciarDescarga.Escribir(currentDescargando);
        		siguienteCamion();
			}
		}else {
				
			int size=iniciarDescarga.getLongitud();
			subastaGanado.setCamionesDescargados(size);
			for(int cont=0;cont<size;cont++) {
				subastaGanado.getCamionesCola().Escribir(iniciarDescarga.Leer());
			}
			

			tabbedPane.setEnabledAt(0, true);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setEnabledAt(3, true);
			
			resetDescargar(false);
			avisoDescargar.setText("Se descargaron los camiones");
			
			
			btnEmpezarADescargar.setEnabled(true);
		}
	}
	
	
	//Se resetean los campos de la opcion de descargar
	public void resetDescargar(boolean type) {
		avisoDescargar.setText("");
		btnEmpezarADescargar.setEnabled(true);
		enOrden.setEnabled(true);
		tablaDescargar.reset();
		lblDescargandoCamionDe.setText("");
		if(!type) {
			currentDescargando=null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				//METODO MAIN
	//Metodo main
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
