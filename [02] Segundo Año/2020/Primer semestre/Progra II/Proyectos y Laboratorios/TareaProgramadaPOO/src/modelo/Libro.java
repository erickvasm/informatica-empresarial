package modelo;

//Clase Libro
public class Libro extends Prestamo{

	
	//Atributos
	private String titulo;
	private String autor;
	private String editorial;
	private int year;
	
	//Constructor
	public Libro() {
		super();
		super.setTipo("Libro");
	}

	
	//Metodo abstracto Sobre-escrito Desplegar informacion del prestamo (Libro)
	@Override
	public String mostrarRecurso() {
		return "\t\tRecurso en Prestamo:\n"+"Tipo: "+super.getTipo()+"\nTitulo: "+getTitulo()+"\nAutor: "+getAutor()+"\nEditorial: "+getEditorial()
		+"\nAño: "+getYear()+"\nFecha Devolucion: "+super.getFechaDevolucion();
	}
	
	
	//SET & GET
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	
}
