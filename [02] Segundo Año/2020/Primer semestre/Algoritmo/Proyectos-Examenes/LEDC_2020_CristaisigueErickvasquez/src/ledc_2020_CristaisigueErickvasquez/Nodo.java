package ledc_2020_CristaisigueErickvasquez;

//Clase Nodo
public class Nodo {

	Nodo anterior;//Referencia al anterior
	Nodo siguiente;//Referencia al siguiente
	Estudiante estudiante;//Tipo de dato Estudiante
	
	
	//Constructor
	public Nodo(Estudiante estudiante) {
		this.estudiante=estudiante;//Estudiante provisto
		anterior=null;//Inicializacion nula
		siguiente=null;//Inicializacion nula
	}
	
	
}
