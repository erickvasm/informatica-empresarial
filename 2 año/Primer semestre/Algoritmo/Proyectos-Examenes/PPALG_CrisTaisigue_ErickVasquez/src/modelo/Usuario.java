package modelo;


//Clase Cliente
public class Usuario {
	
	
	//Atributos
	private String nIdentificacion;//9 caracteres
	private String direccion;
	private String nombre;//Completo
	private double saldo;
	private int nCuenta;//5 digitos
	private int nClave;//4 digitos
	
	
	
	
	//Constructor
	public Usuario() {
		
	}

	//Set & Get
	public String getnIdentificacion() {
		return nIdentificacion;
	}

	public void setnIdentificacion(String nIdentificacion) {
		this.nIdentificacion = nIdentificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getnCuenta() {
		return nCuenta;
	}

	public void setnCuenta(int nCuenta) {
		this.nCuenta = nCuenta;
	}

	public int getnClave() {
		return nClave;
	}

	public void setnClave(int nClave) {
		this.nClave = nClave;
	}
	
	
	//Mostrar informacion
	public String mostrarInformacion() {
		return "\nIdentificacion:"+this.getnIdentificacion()+"\nNúmero Cuenta:"+this.getnCuenta()+"\nClave Cuenta:"+this.getnClave()
		+"\nNombre Completo:"+this.getNombre()+"\nDireccion:"+this.getDireccion()+"\nSaldo:"+this.getSaldo();
	}
	
	//Transformar
	public String transformar() {
		return "@"+this.getnIdentificacion()+","+this.getnCuenta()+","+this.getnClave()+","+this.getNombre()+","+this.getDireccion()+","+((Math.round((this.getSaldo()) * 10000d) / 10000d));
	}
	

}
