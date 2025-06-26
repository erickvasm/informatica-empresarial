package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import estructuraDinamica.ListaDoble;
import modelo.Cajero;
import modelo.Usuario;


//Clase crear, registrar clientes y cajeros
public class Crear extends JPanel{

	//Componentes
	private JTextField reIdentificacion;
	private JTextField reNumCuenta;
	private JTextField reNomCompleto;
	private JTextField reDireccion;
	private JTextField rePin;
	private JTextField reSaldo;
	private JTextField codigoCajero;
	private JTextField ubicacionCajero;	
	private JLabel avisoRegCliente;
	private JLabel avisoRegCajero;
	
	//Referencias a las estructuras dinamicas
	private ListaDoble<Cajero> refCajero;
	private ListaDoble<Usuario> refUsers;
	//Referencia al estado de guardado
	private JLabel referencia;
	
	//Constructor
	public Crear(ListaDoble<Cajero> refCajero,ListaDoble<Usuario> refUsers,JLabel referencia) {
	
		//Obtener las referencias
		this.referencia=referencia;
		this.refCajero=refCajero;
		this.refUsers=refUsers;
		this.setLayout(null);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("<html><body>Instrucciones: seleccione que desea tregistrar, de click en la pesta\u00F1a \"Registrar Cliente\" o \"Registrar Cajero\"</body></html>");
		lblNewLabel_4.setBounds(10, 11, 655, 38);
		this.add(lblNewLabel_4);
		
		
		JTabbedPane registroPestanas = new JTabbedPane(JTabbedPane.TOP);
		registroPestanas.setBounds(10, 62, 655, 479);
		this.add(registroPestanas);
		
		
		
		
		//REGISTRAR CLIENTE
		JPanel regCliente = new JPanel();
		regCliente.setLayout(null);
		registroPestanas.addTab("Registrar Cliente", null, regCliente, null);
		
		JLabel lblNewLabel_5 = new JLabel("N\u00FAmero Identificacion:");
		lblNewLabel_5.setBounds(73, 63, 145, 14);
		regCliente.add(lblNewLabel_5);
		
		JLabel lblNmeroCuenta = new JLabel("N\u00FAmero Cuenta:");
		lblNmeroCuenta.setBounds(73, 104, 145, 14);
		regCliente.add(lblNmeroCuenta);
		
		JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
		lblNombreCompleto.setBounds(73, 143, 145, 14);
		regCliente.add(lblNombreCompleto);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(73, 179, 145, 14);
		regCliente.add(lblDireccion);
		
		JLabel lblClaveCuenta = new JLabel("Clave Cuenta:");
		lblClaveCuenta.setBounds(73, 220, 145, 14);
		regCliente.add(lblClaveCuenta);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(73, 256, 145, 14);
		regCliente.add(lblSaldo);
		
		avisoRegCliente = new JLabel("");
		avisoRegCliente.setBounds(10, 374, 480, 66);
		regCliente.add(avisoRegCliente);
		
		JButton btnNewButton_1 = new JButton("Agregar Cliente");
		btnNewButton_1.setBounds(511, 405, 139, 35);
		regCliente.add(btnNewButton_1);
		
		reIdentificacion = new JTextField();
		reIdentificacion.setBounds(228, 60, 195, 20);
		regCliente.add(reIdentificacion);
		reIdentificacion.setColumns(10);
		
		reNumCuenta = new JTextField();
		reNumCuenta.setColumns(10);
		reNumCuenta.setBounds(228, 101, 195, 20);
		regCliente.add(reNumCuenta);
		
		reNomCompleto = new JTextField();
		reNomCompleto.setColumns(10);
		reNomCompleto.setBounds(228, 140, 195, 20);
		regCliente.add(reNomCompleto);
		
		reDireccion = new JTextField();
		reDireccion.setColumns(10);
		reDireccion.setBounds(228, 176, 195, 20);
		regCliente.add(reDireccion);
		
		rePin = new JTextField();
		rePin.setColumns(10);
		rePin.setBounds(228, 217, 195, 20);
		regCliente.add(rePin);
		
		reSaldo = new JTextField();
		reSaldo.setBounds(228, 253, 195, 20);
		regCliente.add(reSaldo);
		reSaldo.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("<html><body>Instrucciones: ingrese los valores que se le solicitan para crear un nuevo cliente, posterior a esto de click sobre el boton 'Agregar Cliente'.</body></html>");
		lblNewLabel_6.setBounds(10, 11, 630, 35);
		regCliente.add(lblNewLabel_6);
		
		
		
		
		
		
		
		
		//REGISTRAR CAJERO
		JPanel regCajero = new JPanel();
		regCajero.setLayout(null);
		registroPestanas.addTab("Registrar Cajero", null, regCajero, null);
		
		JLabel lblNewLabel_7 = new JLabel("<html><body>Instrucciones: ingrese los datos solicitados para agregar un nuevo cajero, posterior a esto presione el boton 'Agregar Cajero'.</body></html>");
		lblNewLabel_7.setBounds(10, 11, 630, 57);
		regCajero.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Codigo Cajero:");
		lblNewLabel_8.setBounds(78, 126, 151, 14);
		regCajero.add(lblNewLabel_8);
		
		codigoCajero = new JTextField();
		codigoCajero.setBounds(228, 123, 183, 20);
		regCajero.add(codigoCajero);
		codigoCajero.setColumns(10);
		
		JLabel lblUbicacion = new JLabel("Ubicacion:");
		lblUbicacion.setBounds(78, 173, 151, 14);
		regCajero.add(lblUbicacion);
		
		ubicacionCajero = new JTextField();
		ubicacionCajero.setColumns(10);
		ubicacionCajero.setBounds(228, 170, 183, 20);
		regCajero.add(ubicacionCajero);
		
		avisoRegCajero = new JLabel("");
		avisoRegCajero.setBounds(10, 383, 447, 57);
		regCajero.add(avisoRegCajero);
		
		JButton btnNewButton_2 = new JButton("Agregar Cajero");
		btnNewButton_2.setBounds(495, 403, 145, 37);
		regCajero.add(btnNewButton_2);
		
		
		//Registrar cliente
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarCliente()==0) {
					if(guardarCliente()) {
						resetAll();
						avisoRegCliente.setText("Cliente agregado");
						referencia.setText("Estado de guardado: Existen datos sin guardarse, presione 'Guardar Todo'");
					}
				}
			}
		});
		
		//Registrar cajero
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarCajero()==0) {
					if(guardarCajero()) {
						resetAll();
						referencia.setText("Estado de guardado: Existen datos sin guardarse, presione 'Guardar Todo'");
						avisoRegCajero.setText("Cajero agregado");
					}
				}
			}
		});
	}
	
	
	
	//Guardar la informacion de cajero
	public boolean guardarCajero() {
		boolean respuesta=false;
		try {
			Cajero temp=new Cajero();
			temp.setCodigoCajero(Integer.parseInt(codigoCajero.getText()));
			temp.setUbicacion(ubicacionCajero.getText());
			refCajero.InsertarDerecha(temp);
			respuesta=true;
		}catch(Exception e) {
			respuesta=false;
		}
		return respuesta;
	}
	
	
	
	//Conprobar los campos de cajero
	public int comprobarCajero() {
		int general=0;
		
		if((general==0) && (codigoCajero.getText().length()==5)) {
			try {
				int value=Integer.parseInt(codigoCajero.getText());
				if(refCajero.coincidenciaCodigo(value)) {
					if((general==0)) {
						general=1;
					}
				}
			}catch(Exception e) {
				if((general==0)) {
					general=2;
				}
			}
		}else {
			if((general==0)) {
				general=3;
			}
		}
		
		
		if((general==0) && (ubicacionCajero.getText().length()>=6)) {
			if(caracteres(ubicacionCajero.getText())) {
				if(general==0) {
					general=4;
				}
			}
		}else {
			if(general==0) {
				general=5;
			}
		}
		
		switch(general) {
		
		case 1:{
			avisoRegCajero.setText("<html><body>Ya existe un cajero con el codigo ingresado</body></html>");
		}break;
		
		
		case 2:{
			avisoRegCajero.setText("<html><body>Ingrese valores numericos en el codigo de cajero</body></html>");		
		}break;
				
		case 3:{
			avisoRegCajero.setText("<html><body>El codigo de cajero debera contener 5 caracteres</body></html>");
		}break;
		
		case 4:{
			avisoRegCajero.setText("<html><body>No se permiten los caracteres '/' ',' '@'</body></html>");
		}break;
		
		case 5:{
			avisoRegCajero.setText("<html><body>La ubicacion del cajero debe contener al menos 6 caracteres</body></html>");
		}break;
		
		
		}
		
		return general;
	}
	
	
	//Resetear todos los campos
	public void resetAll() {
		avisoRegCajero.setText("");
		avisoRegCliente.setText("");
		reIdentificacion.setText("");
		reNumCuenta.setText("");
		reNomCompleto.setText("");
		reDireccion.setText("");
		rePin.setText("");
		reSaldo.setText("");
		codigoCajero.setText("");
		ubicacionCajero.setText("");	
	}
	
	
	//Guardar cliente
	public boolean guardarCliente() {
		boolean respuesta=false;
		try {
			Usuario temp=new Usuario();
			temp.setDireccion(reDireccion.getText());
			temp.setnClave(Integer.parseInt(rePin.getText()));
			temp.setnCuenta(Integer.parseInt(reNumCuenta.getText()));
			temp.setnIdentificacion(reIdentificacion.getText());
			temp.setNombre(reNomCompleto.getText());
			temp.setSaldo(Double.parseDouble(reSaldo.getText()));
			refUsers.InsertarDerecha(temp);
			respuesta=true;
		}catch(Exception e) {
			respuesta=false;
		}
		return respuesta;
	}
	
	
	
	
	//Comprobar los datos del cliente
	public int comprobarCliente() {
		
		int general=0;
		
		if((general==0) && (reIdentificacion.getText().length()==9)) {
			try {
				int value=Integer.parseInt(reIdentificacion.getText());
				if((refUsers.coincidenciaID(reIdentificacion.getText())) && (general==0)) {
					general=1;
				}
			}catch(Exception e) {
				if(general==0) {
					general=2;
				}
			}
		}else {
			if(general==0) {
				general=3;
			}
		}
		
		
		
		if((general==0) &&(reNumCuenta.getText().length()==5)) {
			try {
				int value=Integer.parseInt(reNumCuenta.getText());
				if((refUsers.coincidenciaCuenta(value)) && (general==0)) {
					general=4;
				}
			}catch(Exception e) {
				if(general==0) {
					general=5;
				}
			}
		}else {
			if(general==0) {
				general=6;
			}
		}
		
		
		if((general==0) && (reNomCompleto.getText().length()>=6)) {
			if((caracteres(reNomCompleto.getText())) && (general==0)) {
				general=7;
			}
		}else {
			if(general==0) {
				general=8;
			}
		}
		
		
		
		if((general==0) && (reDireccion.getText().length()>=6)) {
			if((caracteres(reDireccion.getText())) && (general==0)) {
				general=9;
			}
		}else {
			if(general==0) {
				general=10;
			}
		}
		
		
		if(general==0) {
			try {
				double saldo=Double.parseDouble(reSaldo.getText());
				if((general==0) && (saldo<0)) {
					general=11;
				}
			}catch(Exception e) {
				if(general==0) {
					general=12;
				}
			}
		}
		
		if((general==0) && (rePin.getText().length()==4)) {
			try {
				int value=Integer.parseInt(rePin.getText());
			}catch(Exception e) {
				if(general==0) {
					general=13;
				}
			}
		}else {
			if(general==0) {
				general=14;
			}
		}
		
		
		
		switch(general) {
		
		
			case 1:{
				avisoRegCliente.setText("<html><body>Ya existe un cliente con la identificacion provista</body></html>");
			}break;
			
			case 2:{
				avisoRegCliente.setText("<html><body>Ingrese solo valores numericos en el campo de indentificacion</body></html>");
			}break;
			
			case 3:{
				avisoRegCliente.setText("<html><body>La identificacion debe tener 9 caracteres</body></html>");
			}break;
			
			case 4:{
				avisoRegCliente.setText("<html><body>Ya existe una cuenta con el número de cuenta provisto</body></html>");
			}break;
			
			case 5:{
				avisoRegCliente.setText("<html><body>Ingrese solo valores numeriocos en el numero de cuenta</body></html>");
			}break;
			
			case 6:{
				avisoRegCliente.setText("<html><body>El número de cuenta debera tener 5 digitos</body></html>");
			}break;
			
			case 7:{
				avisoRegCliente.setText("<html><body>No se permiten los caracteres  ' /  '    '  , '     '  @  ' para el campo de nombre</body></html>");
			}break;
			
			case 8:{
				avisoRegCliente.setText("<html><body>Ingrese su nombre completo, al menos 6 caracteres</body></html>");
			}break;
			
			case 9:{
				avisoRegCliente.setText("<html><body>No se permiten los caracteres  ' /  '    '  , '     '  @  ' para el campo de direccion</body></html>");
			}break;
			
			case 10:{
				avisoRegCliente.setText("<html><body>La direccion debera tener al menos 6 caracteres</body></html>");
			}break;
			
			case 11:{
				avisoRegCliente.setText("<html><body>El saldo provisto tendra que ser mayor o igual a 0</body></html>");
			}break;
			
			case 12:{
				avisoRegCliente.setText("<html><body>Ingrese valores numericos en el campo de saldo</body></html>");
			}break;
			
			case 13:{
				avisoRegCliente.setText("<html><body>Ingrese valores númericos en el campo de PIN</body></html>");
			}break;
			
			case 14:{
				avisoRegCliente.setText("<html><body>El PIN debera contener 4 caracteres</body></html>");
			}break;
			
		
		
		}
		
		return general;
	}
	
	
	
	
	
	//Restriccion de caracteres
	public boolean caracteres(String value) {
		boolean respuesta=false;
		if((value.contains("/")) || (value.contains(",")) || (value.contains("@"))){
			respuesta=true;
		}
		return respuesta;
	}
	
	
}
