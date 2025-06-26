package ep1_les_cristaisigueerickvasquez;

//Clase nodo
public class Nodo {
	

	Nodo siguiente;//Apunta siguiente
	Llamada llamada;//tipo de dato
	
	//Constructor
	public Nodo(Llamada llamada) {
		this.siguiente=null;
		this.llamada=llamada;
	}	
	
}
