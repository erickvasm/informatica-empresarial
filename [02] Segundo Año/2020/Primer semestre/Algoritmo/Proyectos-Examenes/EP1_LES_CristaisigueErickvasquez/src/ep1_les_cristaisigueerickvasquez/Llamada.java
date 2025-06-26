package ep1_les_cristaisigueerickvasquez;

//Clase llamada tipo de dato en el Nodo de la lista simple
public class Llamada {

	//Atributos
	private final int costeLlamada=5;
	private String nTelefono;
	private String nombreCliente;
	private int duracion;
	private int coste;
	
	
	//Constructor
	public Llamada() {
		
	}
	
	//Actualizar la informacion de la llamada
	public void actualizarInformacion() {
		setCoste(getDuracion()*getCosteLlamada());
	}
	
	
	
	//Set & Get
	
	//Mostrar todos los datos de llamada
	public String mostrarLLamada() {
		actualizarInformacion();
		return "Número Telefono: "+getnTelefono()+"\nNombre Cliente: "+getNombreCliente()+
				"\nDuracion: "+getDuracion()+"\nCoste: "+getCoste();
	}
	
	
	
	
	public int getCoste() {
		return coste;
	}


	public void setCoste(int coste) {
		this.coste = coste;
	}


	public String getnTelefono() {
		return nTelefono;
	}

	public void setnTelefono(String nTelefono) {
		this.nTelefono = nTelefono;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCosteLlamada() {
		return costeLlamada;
	}
	

}
