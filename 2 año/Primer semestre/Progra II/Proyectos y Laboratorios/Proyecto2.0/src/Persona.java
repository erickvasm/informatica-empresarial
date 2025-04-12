/**
 * @author Erick Vasquez Murillo B983344
 */

//Clase abstracta persona
public abstract class Persona {
	
	//Encapsulamiento y ocultación
	private String nombre;
	private String apellido;
	private char genero;
	private String identificacion;
	private String direccion;
	private String telefono;
	
	//Constructor vacio
	public Persona(){
		
	}
	
	//Metodo abstracto que se sobre-escribira en clase Paciente
	public abstract String mostrarInformacion();
	

	//Métodos SET Y GET
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public char getGenero() {
		return genero;
	}


	public void setGenero(char genero) {
		this.genero = genero;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
