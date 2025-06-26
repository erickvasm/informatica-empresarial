package modelo;

//Clase Electronico
public class Electronico extends Prestamo{
	
	
	//Atributos
	private String tipoDisco;
	private String temaContenido;
	
	
	//Constructor
	public Electronico() {
		super();
		super.setTipo("Electronico");
	}

	
	//Metodo abstracto Sobre-escrito Desplegar informacion del prestamo (Electronico)
	@Override
	public String mostrarRecurso() {
		return "\t\tRecurso en Prestamo:\n"+"Tipo: "+super.getTipo()+"\nDisco: "+getTipoDisco()+"\nTematica contenida: "+getTemaContenido()+"\nFecha Devolucion: "+super.getFechaDevolucion();
	}
		
	
	
	//SET & GET
	public String getTipoDisco() {
		return tipoDisco;
	}


	public void setTipoDisco(String tipoDisco) {
		this.tipoDisco = tipoDisco;
	}


	public String getTemaContenido() {
		return temaContenido;
	}


	public void setTemaContenido(String temaContenido) {
		this.temaContenido = temaContenido;
	}
	
	
	
	

}
