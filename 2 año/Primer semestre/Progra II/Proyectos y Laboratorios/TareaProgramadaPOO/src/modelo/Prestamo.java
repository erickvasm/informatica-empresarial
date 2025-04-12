package modelo;

public abstract class Prestamo {
	
	//Atributos
	private String fechaDevolucion;
	private String tipo;
	
	
	//Constructor
	public Prestamo() {
		
	}

	
	//Metodo abstracto
	public abstract String mostrarRecurso();
	

	//SET & GET
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}


	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
}
