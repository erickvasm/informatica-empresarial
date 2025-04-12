package controlador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import estructuraDinamica.ListaDoble;
import modelo.Cajero;
import modelo.Consulta;
import modelo.Deposito;
import modelo.Retiro;
import modelo.Transaccion;
import modelo.Usuario;

//Intefaz de cajero automatico
public class CajeroInterfaz extends JPanel{

	//Componentes
	private JLabel avisoAcceso;
	private JPasswordField pinUsuario;
	private JTextField idUsuario;
	private JTextField montoDebitar;
	private JTextField montoDepositar;
	private JLabel debitarLabelTwo;
	private JTextArea textoConsulta;
	private JLabel debitarLabelOne;
	private JLabel nuevoSaldo;
	private JLabel lblNewLabel_5;
	private JLabel avisoTransaccion;
	private JComboBox comboBox;
	private JTabbedPane etapas;
	
	//Obtener la fecha y la hora
	private SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat fecha = new SimpleDateFormat("dd:MM:yy");
	private Date tiempo=new Date();
	
	//Usuario y cajero accesados
	private String cajero="";
	private Usuario editando=null;
	
	//Referencias a las estructuras dinamicas
	private ListaDoble<Cajero> refCajero;
	private ListaDoble<Transaccion> refTrans;
	private ListaDoble<Usuario> refUsuario;
	
	//Referencia de estado de guardado
	private JLabel refererencia;
	
	
	//Constructor
	public CajeroInterfaz(ListaDoble<Cajero> refCajero,ListaDoble<Transaccion> refTrans, ListaDoble<Usuario> refUsuario,JLabel referencia) {
		
		//Obtener las referencias
		this.refererencia=referencia;
		this.refCajero=refCajero;
		this.refTrans=refTrans;
		this.refUsuario=refUsuario;

		
			this.setLayout(null);
			etapas = new JTabbedPane(JTabbedPane.TOP);
			etapas.setBounds(0, 0, 675, 552);
			this.add(etapas);
						
			JPanel acceso = new JPanel();
			acceso.setLayout(null);
			etapas.addTab("Acceso", null, acceso, null);
					
			JLabel lblNewLabel = new JLabel("ENTRAR EN LA CUENTA");
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lblNewLabel.setBounds(193, 95, 317, 44);
			acceso.add(lblNewLabel);
					
			avisoAcceso = new JLabel("");
			avisoAcceso.setBounds(10, 438, 650, 44);
			acceso.add(avisoAcceso);
					
			JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de Identificacion:");
			lblNewLabel_1.setBounds(57, 177, 156, 25);
			acceso.add(lblNewLabel_1);
					
			JLabel lblNewLabel_2 = new JLabel("PIN:");
			lblNewLabel_2.setBounds(57, 242, 156, 25);
			acceso.add(lblNewLabel_2);
					
			pinUsuario = new JPasswordField();
			pinUsuario.setBounds(223, 242, 210, 25);
			acceso.add(pinUsuario);
					
			idUsuario = new JTextField();
			idUsuario.setBounds(223, 179, 210, 25);
			acceso.add(idUsuario);
			idUsuario.setColumns(10);
					
			JButton btnNewButton = new JButton("Entrar");
			btnNewButton.setBounds(223, 369, 210, 36);
			acceso.add(btnNewButton);
					
			JLabel lblNewLabel_3 = new JLabel("<html><body>Instrucciones: si desea hacer uso del cajero automatico debera ingresar su n\u00FAmero de identificacion seguido por su PIN, posterior a esto presione el boton 'Entrar'</body></html>");
			lblNewLabel_3.setBounds(10, 11, 650, 51);
			acceso.add(lblNewLabel_3);
				
			JLabel lblthis = new JLabel("Cajero:");
			lblthis.setBounds(57, 308, 156, 25);
			acceso.add(lblthis);
				
			comboBox = new JComboBox();
			comboBox.setEnabled(false);
			comboBox.setBounds(223, 308, 210, 25);
			acceso.add(comboBox);
						
			JPanel operaciones = new JPanel();
			operaciones.setLayout(null);
			etapas.addTab("Operaciones", null, operaciones, null);
			
			JLabel lblNewLabel_4 = new JLabel("<html><body>Instrucciones: mediante los botones de seleccion unica, escoja que transaccion desea realizar (Consulta,Retiro,Deposito).</body></html>");
			lblNewLabel_4.setBounds(10, 11, 650, 41);
			operaciones.add(lblNewLabel_4);
				
			JButton btnNewButton_1 = new JButton("Realizar Transaccion");
			btnNewButton_1.setBounds(506, 477, 154, 36);
			operaciones.add(btnNewButton_1);
				
			JButton btnNewButton_2 = new JButton("<html><body>Salir de la Cuenta</body></html>");
			btnNewButton_2.setBounds(506, 430, 154, 36);
			operaciones.add(btnNewButton_2);
				
			ActionListener accion=ActionEvent->change(((JRadioButton)ActionEvent.getSource()).getText().toString());
			
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Consulta");
			rdbtnNewRadioButton.setBounds(41, 59, 109, 23);
			rdbtnNewRadioButton.addActionListener(accion);
			rdbtnNewRadioButton.setSelected(true);
			operaciones.add(rdbtnNewRadioButton);
				
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Retiro");
			rdbtnNewRadioButton_1.setBounds(167, 59, 109, 23);
			rdbtnNewRadioButton_1.addActionListener(accion);
			operaciones.add(rdbtnNewRadioButton_1);
				
			JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Deposito");
			rdbtnNewRadioButton_2.setBounds(301, 59, 109, 23);
			rdbtnNewRadioButton_2.addActionListener(accion);
			operaciones.add(rdbtnNewRadioButton_2);
		
			
			ButtonGroup botones=new ButtonGroup();
			botones.add(rdbtnNewRadioButton_1);
			botones.add(rdbtnNewRadioButton);
			botones.add(rdbtnNewRadioButton_2);
			
			
			
			avisoTransaccion = new JLabel("");
			avisoTransaccion.setBounds(10, 430, 446, 83);
			operaciones.add(avisoTransaccion);
			
			
				
				
				
				
			lblNewLabel_5 = new JLabel("Monto a Depositar");
			lblNewLabel_5.setBounds(126, 145, 123, 14);
			operaciones.add(lblNewLabel_5);
				
			montoDepositar = new JTextField();
			montoDepositar.setBounds(266, 142, 181, 20);
			montoDepositar.setColumns(10);
			operaciones.add(montoDepositar);
			
			nuevoSaldo = new JLabel("Nuevo Saldo:");
			nuevoSaldo.setBounds(126, 206, 330, 14);
			operaciones.add(nuevoSaldo);
				
				
				
				
				
				
			debitarLabelOne = new JLabel("Monto a Debitar");
			debitarLabelOne.setBounds(104, 151, 125, 14);
			operaciones.add(debitarLabelOne);
				
			montoDebitar = new JTextField();
			montoDebitar.setBounds(239, 148, 197, 20);
			montoDebitar.setColumns(10);
			operaciones.add(montoDebitar);
				
			debitarLabelTwo = new JLabel("Saldo Correspondiente:");
			debitarLabelTwo.setBounds(104, 190, 332, 14);
			operaciones.add(debitarLabelTwo);
				
				
				
			textoConsulta = new JTextArea("\tPresione Realizar Transaccion para mostrar la consulta");
			textoConsulta.setFont(new Font("Monospaced", Font.PLAIN, 16));
			textoConsulta.setLineWrap(true);
			textoConsulta.setBounds(87, 89, 422, 314);
			textoConsulta.setEditable(false);
			operaciones.add(textoConsulta);
				
			
			//Cuando se sale de la cuenta o se cambio de pestaña se resetean los punteros de edicion
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					resetAll(true);
					etapas.setSelectedIndex(0);
				}
			});
			
			
			//Realizar transacciones
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnNewRadioButton.isSelected()) {
						Consulta cons=(Consulta)generarTransaccion(1);
						referencia.setText("Estado de guardado: Existen datos sin guardarse, presione 'Guardar Todo'");
						resetAll(false);
						avisoTransaccion.setText("Se genero la transaccion");
						textoConsulta.setText("\tConsulta Generada\n"+cons.mostrarInformacion());
					}else {
						if(comprobarTransaccion(rdbtnNewRadioButton_2.isSelected())==0) {
							if(rdbtnNewRadioButton_2.isSelected()) {
								Deposito cons=(Deposito)generarTransaccion(3);
								referencia.setText("Estado de guardado: Existen datos sin guardarse, presione 'Guardar Todo'");
								resetAll(false);
								avisoTransaccion.setText("Se genero la transaccion");
								nuevoSaldo.setText("Nuevo Saldo:"+cons.getNuevoSaldo());
							}else {
								Retiro cons=(Retiro)generarTransaccion(2);
								referencia.setText("Estado de guardado: Existen datos sin guardarse, presione 'Guardar Todo'");
								resetAll(false);
								avisoTransaccion.setText("Se genero la transaccion");
								debitarLabelTwo.setText("Saldo Correspondiente:"+cons.getSaldoCorrespondiente());
							}
						}
					}
				}
			});
			
			//Accesar al cajero automatico
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					editando=null;
					cajero="";
					
					if((refUsuario.Vacia()) || (refCajero.Vacia())) {
						avisoAcceso.setText("<html><body>Para acceder debera existir al menos un cajero y un cliente registrados</body></html>");
					}else {
						if(comprobarIngreso()==0) {
							if((comboBox.getSelectedItem().toString().length()==0) || (comboBox.getSelectedItem().toString().equalsIgnoreCase("")) || (comboBox.getSelectedItem().toString().equalsIgnoreCase("No existen cajeros"))) {
								avisoAcceso.setText("<html><body>Seleccione un cajero</body></html>");
							}else {
								editando=refUsuario.obtenerCliente(idUsuario.getText());
								if(editando!=null) {
									if(editando.getnClave()==Integer.parseInt(String.valueOf(pinUsuario.getPassword()))) {
										cajero=comboBox.getSelectedItem().toString();
										etapas.setSelectedIndex(1);
									}else{
										avisoAcceso.setText("<html><body>El pin proporcionado no coincide con el de la cuenta</body></html>");
									}
								}
							}
						}
					}
					
				}
			});
				
			
			//Resetear todo
			this.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent arg0) {
					fillCombo();
					resetAll(true);
					etapas.setSelectedIndex(0);
				}
			});
		
			
			etapas.setEnabledAt(1,false);
			etapas.setEnabledAt(0,false);
			change("Consulta");
		
	}
	
	
	//Generar transacciones
	public Transaccion generarTransaccion(int type) {
		if(type==1) {
			
			String cajeroSplit[]=cajero.split("@");
			int codigo=Integer.parseInt(cajeroSplit[0]);
			Transaccion temp=new Consulta();
			temp.setCajero(codigo);
			temp.setCliente(editando.getnIdentificacion());
			temp.setFecha(fecha.format(tiempo));
			temp.setHora(hora.format(tiempo));
			temp.setnTransaccion(refTrans.obtenerNumero(1));
			temp.setTipo(1);
			((Consulta)temp).setSaldo(editando.getSaldo());
			refTrans.InsertarDerecha(temp);
			
			return temp;
			
		}else if(type==2) {
			
			String cajeroSplit[]=cajero.split("@");
			int codigo=Integer.parseInt(cajeroSplit[0]);
			Transaccion temp=new Retiro();
			temp.setCajero(codigo);
			temp.setCliente(editando.getnIdentificacion());
			temp.setFecha(fecha.format(tiempo));
			temp.setHora(hora.format(tiempo));
			temp.setnTransaccion(refTrans.obtenerNumero(3));
			temp.setTipo(3);
			((Retiro)temp).setMontoDebitado(Double.parseDouble(montoDebitar.getText()));
			editando.setSaldo((Math.round((editando.getSaldo()-((Retiro)temp).getMontoDebitado()) * 10000d) / 10000d));
			((Retiro)temp).setSaldoCorrespondiente(editando.getSaldo());
			refTrans.InsertarDerecha(temp);
			
			return temp;
			
		}else {
		
			
			String cajeroSplit[]=cajero.split("@");
			int codigo=Integer.parseInt(cajeroSplit[0]);
			Transaccion temp=new Deposito();
			temp.setCajero(codigo);
			temp.setCliente(editando.getnIdentificacion());
			temp.setFecha(fecha.format(tiempo));
			temp.setHora(hora.format(tiempo));
			temp.setnTransaccion(refTrans.obtenerNumero(2));
			temp.setTipo(2);
			((Deposito)temp).setMontoDepositado(Double.parseDouble(montoDepositar.getText()));
			editando.setSaldo((Math.round((editando.getSaldo()+((Deposito)temp).getMontoDepositado()) * 10000d) / 10000d));
			((Deposito)temp).setNuevoSaldo(editando.getSaldo());
			refTrans.InsertarDerecha(temp);
			
			return temp;
			
		}
			
	}
	
	
	
	
	//Comprobar datos de la transaccion
	public int comprobarTransaccion(boolean type) {
		int general=0;
		if(type) {
			
			if(general==0) {
				try {
					double saldo=Double.parseDouble(montoDepositar.getText());
					if((general==0) && (saldo<=0)) {
						general=1;
					}
				}catch(Exception e) {
					if(general==0) {
						general=2;
					}
				}
			}
			
			switch(general) {
			
				case 1:{
					avisoTransaccion.setText("<html><body>El monto a depositar debera ser mayor a 0</body></html>");
				}break;
				
				case 2:{
					avisoTransaccion.setText("<html><body>Ingrese valores numericos en el monto a depositar</body></html>");
				}break;
			
			
			}
			
			
			
		}else{

			
			if(general==0) {
				try {
					double saldo=Double.parseDouble(montoDebitar.getText());
					if((general==0) && (saldo<=0)) {
						general=1;
					}else {
						if((editando!=null) && (general==0)) {
							if(((editando.getSaldo()-saldo)<0) && (general==0)) {
								general=3;
							}
						}
					}
				}catch(Exception e) {
					if(general==0) {
						general=2;
					}
				}
			}
			
			
			switch(general) {
			
				case 1:{
					avisoTransaccion.setText("<html><body>El monto a debitar tiene que ser mayor a 0</body></html>");
				}break;
				
				case 2:{
					avisoTransaccion.setText("<html><body>Ingrese valores numericos en el monto a debitar</body></html>");
				}break;
				
				case 3:{
					avisoTransaccion.setText("<html><body>El monto a debitar es mayor a lo que se dispone en la cuenta</body></html>");
				}break;
			
			}

			
			
			
		}
		
		
		return general;
	}
	
	
	
	
	//Cambiar tipo de transaccion
	public void change(String value) {
		resetAll(false);
		montoDebitar.setVisible(false);
		montoDepositar.setVisible(false);
		debitarLabelTwo.setVisible(false);
		textoConsulta.setVisible(false);
		debitarLabelOne.setVisible(false);
		nuevoSaldo.setVisible(false);
		lblNewLabel_5.setVisible(false);
		avisoTransaccion.setText("");
		
		if(value.equalsIgnoreCase("Consulta")) {
			textoConsulta.setVisible(true);
		}else if(value.equalsIgnoreCase("Retiro")) {
			montoDebitar.setVisible(true);
			debitarLabelOne.setVisible(true);
			debitarLabelTwo.setVisible(true);
		}else {
			nuevoSaldo.setVisible(true);
			montoDepositar.setVisible(true);
			lblNewLabel_5.setVisible(true);
		}
	}
	
	//Resetear todo
	public void resetAll(boolean type) {
		if(type) {
			cajero="";
			editando=null;
		}
		montoDebitar.setText("");
		montoDepositar.setText("");
		debitarLabelTwo.setText("Saldo Correspondiente:");
		textoConsulta.setText("Presione Realizar Transaccion para mostrar la consulta");
		nuevoSaldo.setText("Nuevo Saldo:");
		avisoAcceso.setText("");
		avisoTransaccion.setText("");
		pinUsuario.setText("");
		idUsuario.setText("");
	}


	//Rellenar combobox
	public void fillCombo() {
		comboBox.setEnabled(true);
		if(refCajero.Vacia()) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"No existen cajeros"}));
			comboBox.setEnabled(false);
		}else {
			refCajero.rellenarCombo(comboBox);
		}
	}
	
	
	//Comprobar datos de accesso
	public int comprobarIngreso() {
		int general=0;
		
		if((general==0) && (idUsuario.getText().length()==9)) {
			try {
				int value=Integer.parseInt(idUsuario.getText());
				if((refUsuario.obtenerCliente(idUsuario.getText())==null) && (general==0)) {
					general=1;
				}
			}catch(Exception e) {
				if(general==0) {
					general=2;
				}
			}
		}else if(general==0) {
			general=3;
		}
		
		
		if((general==0) && (String.valueOf(pinUsuario.getPassword()).length()==4)) {
			try {
				int pin=Integer.parseInt(String.valueOf(pinUsuario.getPassword()));
			}catch(Exception e) {
				if(general==0) {
					general=4;
				}
			}
		}else if(general==0) {
			general=5;
		}
		
		
		
		switch(general) {
		
			case 1:{
				avisoAcceso.setText("<html><body>No exise una cuenta asociada al número de identificacion provisto</body></html>");
			}break;
			
			case 2:{
				avisoAcceso.setText("<html><body>Ingrese valores numericos en campo de número de identificacion</body></html>");
			}break;
			
			case 3:{
				avisoAcceso.setText("<html><body>El número de identificacion tiene que tener 9 caracteres</body></html>");
			}break;
			
			case 4:{
				avisoAcceso.setText("<html><body>El valor de la clave tiene que ser numerico</body></html>");
			}break;
			
			case 5:{
				avisoAcceso.setText("<html><body>La clave debe tener 4 digitos</body></html>");
			}break;
			
		}
		
		return general;
	}
	
}
