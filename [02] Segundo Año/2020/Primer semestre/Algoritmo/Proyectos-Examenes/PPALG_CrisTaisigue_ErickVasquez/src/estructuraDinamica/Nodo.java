package estructuraDinamica;

//Clase Nodo Generica
public class Nodo<T> {

	Nodo anterior;//Referencia al anterior
	Nodo siguiente;//Referencia al siguiente
	T dato;//Tipo de dato generico
	
	
	//Constructor
	public Nodo(T dato) {
		this.dato=dato;//Dato provisto
		anterior=null;//Inicializacion nula
		siguiente=null;//Inicializacion nula
	}
	
	
}
