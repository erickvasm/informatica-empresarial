package modelo;

//Clase abstracta Persona (Abstraccion)
public abstract class Persona {

	//Atributos (Encapsulamiento)
	private String identificacion;
	private String nombre;
	private String apellido;
	private int edad;
	
	//Constructor
	public Persona() {
		
	}

	
	//Mostrar informacion basica de la persona
	public String mostrarPersona() {
		return "Identificacion: "+getIdentificacion()+"\n Nombre: "+getNombre()+"\n Apellido: "+
				getApellido()+"\n Edad :"+getEdad();
	}
	
	//Metodo abstracto para mostrar informacion segun las clases derivadas (Abstraccion)
	public abstract String desplegarInformacion();
	
	
	// SET & GET
	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


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


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
	
}
