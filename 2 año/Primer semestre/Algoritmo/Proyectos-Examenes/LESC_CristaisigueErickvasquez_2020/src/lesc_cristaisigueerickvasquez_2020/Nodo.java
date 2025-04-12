package lesc_cristaisigueerickvasquez_2020;

//Clase Nodo
public class Nodo {
	
	Nodo siguiente;//Apunta siguiente
	Estudiante estudiante;//tipo de dato
	
	//Constructor
	public Nodo(Estudiante estudiante) {
		this.siguiente=null;
		this.estudiante=estudiante;
	}	
	
}
