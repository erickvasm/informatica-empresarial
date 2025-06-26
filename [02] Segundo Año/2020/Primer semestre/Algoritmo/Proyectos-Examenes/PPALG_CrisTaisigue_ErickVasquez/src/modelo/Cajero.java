package modelo;

//Clase cajero
public class Cajero {

	//Atributos
	private int codigoCajero;
	private String ubicacion;
	
	//Constructor
	public Cajero() {
		
	}

	
	//Set & Get
	public int getCodigoCajero() {
		return codigoCajero;
	}

	public void setCodigoCajero(int codigoCajero) {
		this.codigoCajero = codigoCajero;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
	//Transformar para guardar
	public String transformacion() {
		return "cajero@"+this.getCodigoCajero()+","+this.getUbicacion();
	}
}
