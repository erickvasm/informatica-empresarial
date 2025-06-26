package controlador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import almacenamiento.Leer;
import estructuraDinamica.ListaDoble;
import modelo.Consulta;
import modelo.Deposito;
import modelo.Retiro;
import modelo.Transaccion;
import modelo.Usuario;

//Menu principal, ordenamiento y busqueda
public class MenuPrincipal extends JPanel{

	
	//Componentes
	private JTextField textField;
	private JTextArea shellText;
	private JTextArea quickText;
	private JProgressBar interQuick;
	private JProgressBar compaQuick;
	private JProgressBar compaShell;
	private JProgressBar interShell;
	private JTextArea serchArea;
	private JLabel lblNewLabel_7;
	
	//Referencia a las estructuras dinamicas
	private ListaDoble<Transaccion> transacciones;
	private ListaDoble<Usuario> usuarios;

	//Lector referencia
	private Leer lector;
	
	//Referencia al estado
	private JLabel referencia;
	
	//Constructor
	public MenuPrincipal(ListaDoble<Transaccion> transacciones,Leer lector,ListaDoble<Usuario> usuarios,JLabel referencia) {
		
		
		//Obtener referencias
		this.transacciones=transacciones;
		this.lector=lector;
		this.usuarios=usuarios;
		this.referencia=referencia;
		
		this.setLayout(null);
		JLabel lblNewLabel = new JLabel("BUSCAR CLIENTE");
		lblNewLabel.setBounds(10, 11, 116, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html><body>Instrucciones: ingrese el n\u00FAmero de identificacion del cliente a buscar y posterior a esto presione 'Buscar'</body></html>");
		lblNewLabel_1.setBounds(30, 66, 218, 43);
		this.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(33, 169, 116, 20);
		this.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(159, 169, 89, 23);
		this.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 23, 400, 183);
		this.add(scrollPane);
		
		
		serchArea = new JTextArea();
		serchArea.setEditable(false);
		serchArea.setLineWrap(true);
		serchArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(serchArea);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 209, 655, 7);
		this.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("<html><body>ORDENAMIENTO: Presione el boton 'Ordenar' para odernar las transacciones</body></body>");
		lblNewLabel_2.setBounds(10, 216, 507, 14);
		this.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("ORDENAR");
		btnNewButton_1.setBounds(549, 217, 116, 23);
		this.add(btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(327, 270, 6, 266);
		this.add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("Ordenamiento Quick Sort");
		lblNewLabel_3.setBounds(408, 533, 234, 14);
		this.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ordenamiento Shell");
		lblNewLabel_4.setBounds(93, 533, 155, 14);
		this.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Intercambios:");
		lblNewLabel_5.setBounds(10, 508, 89, 14);
		this.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Comparaciones");
		lblNewLabel_6.setBounds(10, 488, 95, 14);
		this.add(lblNewLabel_6);
		
		JLabel label = new JLabel("Intercambios:");
		label.setBounds(343, 508, 89, 14);
		this.add(label);
		
		JLabel label_1 = new JLabel("Comparaciones");
		label_1.setBounds(343, 488, 95, 14);
		this.add(label_1);
		
		
		interShell = new JProgressBar();
		interShell.setStringPainted(true);
		interShell.setBounds(99, 510, 196, 14);
		this.add(interShell);
		
		compaShell = new JProgressBar();
		compaShell.setStringPainted(true);
		compaShell.setBounds(102, 488, 193, 14);
		this.add(compaShell);
		
		
		compaQuick = new JProgressBar();
		compaQuick.setStringPainted(true);
		compaQuick.setBounds(445, 488, 193, 14);
		this.add(compaQuick);
		
		interQuick = new JProgressBar();
		interQuick.setStringPainted(true);
		interQuick.setBounds(442, 510, 196, 14);
		this.add(interQuick);
		
		lblNewLabel_7 = new JLabel("Metodo con mayor efectividad:");
		lblNewLabel_7.setBounds(213, 241, 320, 14);
		this.add(lblNewLabel_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 262, 309, 220);
		this.add(scrollPane_1);
		
		shellText = new JTextArea();
		shellText.setEditable(false);
		shellText.setLineWrap(true);
		scrollPane_1.setViewportView(shellText);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(337, 262, 309, 220);
		this.add(scrollPane_2);
		
		quickText = new JTextArea();
		quickText.setEditable(false);
		quickText.setLineWrap(true);
		scrollPane_2.setViewportView(quickText);
		
		
		//Buscar cliente por su identificacion 'dispersion'
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if(referencia.getText().equalsIgnoreCase("Estado de guardado: Existen datos sin guardarse, presione 'Guardar Todo'")) {
						int i=JOptionPane.showConfirmDialog(null, "Existen datos sin guardarse, para la busqueda solo se tomaran en cuenta los registros guardados, ¿Desea continuar la busqueda?","¿Continuar?",JOptionPane.YES_NO_OPTION);
						if(i==0) {
							buscar();
						}
					}else{
						buscar();
					}
				}catch(Exception e) {
					
				}
			}
		});
		
		//Ordenamiento quickSort y shell
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenamiento();
			}
		});
		
		//Resetear todos los componentes
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				resetAll();
			}
		});
		
		
	}
	
	
	//Buscar un cliente por su identificacion 'Hash'
	public void buscar() {
		if(textField.getText().length()!=9) {
			serchArea.setText("Ingrese una identificacion de 9 caracteres");
		}else {
			try {
				int value=Integer.parseInt(textField.getText());
				lector.mostrarBusqueda(textField.getText(), serchArea);//Buscar en el fichero la llave
				serchArea.setCaretPosition(0);
			}catch(Exception e) {
				serchArea.setText("Ingrese solo valores numericos en el campo de identificacion");
			}
		}
	}
	
	
	//Ordenamiento por quickSort y shell
	public void ordenamiento() {
		if(!usuarios.Vacia()) {
			
			resetAll();
			
			
			
			//Crear listas temporales con los datos de usuario
			ListaDoble<Usuario> tempQuick=new <Usuario>ListaDoble();
			usuarios.asignarRecorrer(1);
			//Copiar usuarios
			while(usuarios.actual()) {
				tempQuick.InsertarDerecha(usuarios.obtenerDato());
				usuarios.avanzar();
			}
			
			ListaDoble<Usuario> tempShell=new <Usuario>ListaDoble();
			usuarios.asignarRecorrer(1);
			//Copiar usuarios
			while(usuarios.actual()) {
				tempShell.InsertarDerecha(usuarios.obtenerDato());
				usuarios.avanzar();
			}
			
			//QuickSort
			if(!tempQuick.Vacia()) {
				try {
					tempQuick.quickSort();//Ordenamiento quicksort
					interQuick.setValue(tempQuick.getIntercambio());
					compaQuick.setValue(tempQuick.getComparaciones());
					
					//Mostrar datos
					tempQuick.asignarRecorrer(1);
					while(tempQuick.actual()) {
						ArrayList<Transaccion> list=null;
						list=transacciones.obtenerTransacciones(tempQuick.obtenerDato().getnIdentificacion());
						quickText.append("\n\n>Cuenta N°:"+tempQuick.obtenerDato().getnCuenta()+"   Identificacion:"+tempQuick.obtenerDato().getnIdentificacion());
						if(list!=null) {
							quickText.append("\nTransacciones:");
							for(Transaccion t:list) {
								if(t.getTipo()==1) {
									quickText.append("\n\n"+((Consulta)t).mostrarInformacion());
								}else if(t.getTipo()==2) {
									quickText.append("\n\n"+((Deposito)t).mostrarInformacion());
								}else {
									quickText.append("\n\n"+((Retiro)t).mostrarInformacion());
								}
							}
						}else {
							quickText.append("\nEste cliente no tiene transacciones");
						}
						tempQuick.avanzar();
					}
					quickText.setCaretPosition(0);
					
				}catch(Exception e) {
					
				}
			}
			
			//Ordenamiento Shell
			if(!tempShell.Vacia()) {
				try {
					tempShell.shell();//Metodo shell
					compaShell.setValue(tempShell.getComparaciones());
					interShell.setValue(tempShell.getIntercambio());
					
					
					//Mostrar los datos
					tempShell.asignarRecorrer(1);
					while(tempShell.actual()) {
						ArrayList<Transaccion> list=null;
						list=transacciones.obtenerTransacciones(tempShell.obtenerDato().getnIdentificacion());
						shellText.append("\n\n>Cuenta N°:"+tempShell.obtenerDato().getnCuenta()+"   Identificacion:"+tempShell.obtenerDato().getnIdentificacion());
						if(list!=null) {
							shellText.append("\nTransacciones:");
							for(Transaccion t:list) {
								if(t.getTipo()==1) {
									shellText.append("\n\n"+((Consulta)t).mostrarInformacion());
								}else if(t.getTipo()==2) {
									shellText.append("\n\n"+((Deposito)t).mostrarInformacion());
								}else {
									shellText.append("\n\n"+((Retiro)t).mostrarInformacion());
								}
							}
						}else {
							shellText.append("\nEste cliente no tiene transacciones");
						}
						tempShell.avanzar();
					}
					shellText.setCaretPosition(0);
					
				}catch(Exception e) {
					
				}
			}
			
			//Comparaciones de efectividad
			if((tempShell.getComparaciones()+tempShell.getIntercambio())==(tempQuick.getComparaciones()+tempQuick.getIntercambio())) {
				lblNewLabel_7.setText("Metodo con mayor efectividad:Ambos");
			}else if((tempShell.getComparaciones()+tempShell.getIntercambio())<(tempQuick.getComparaciones()+tempQuick.getIntercambio())) {
				lblNewLabel_7.setText("Metodo con mayor efectividad:Shell");
			}else {
				lblNewLabel_7.setText("Metodo con mayor efectividad:QuickSort");
			}
			
		}else {
			shellText.setText("No existen números de cuentas asociadas a los clientes");
			quickText.setText("No existen números de cuentas asociadas a los clientes");
		}
	}
	
	
	
	
	//Resetear todos los campos
	public void resetAll() {
		
		textField.setText("");
		shellText.setText("");
		quickText.setText("");
		interQuick.setValue(0);
		compaQuick.setValue(0);
		compaShell.setValue(0);
		interShell.setValue(0);
		serchArea.setText("Tome en cuenta que solos los registros disponibles en el archivo de guardado se usaran para la busqueda, por lo tanto si desea una busqueda completa, debera guardar los datos recientemente agregados con el boton 'Guardar Todo'");
		lblNewLabel_7.setText("Metodo con mayor efectividad:");
	}

}
