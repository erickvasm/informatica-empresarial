package ep1_les_cristaisigueerickvasquez;

//Clase Lista, Lista enlazada simples
public class Lista {

	//Atributos
	private Nodo inicio;	
	private int extension;
	
	//Constructor
	public Lista() {
		this.inicio=null;
		this.extension=0;
	}
	
	
	//Eliminar lista
	public void vaciaLista() {
		inicio=null;
	}
	
	
	//Se determina si la lista enlazada simple esta vacia
	public boolean siVacio() {
		return (inicio==null)?true:false;
	}

	
	
	//Insertar en primera posicion
	public Lista insertar(Llamada llamada) {
		Nodo nuevoNodo=new Nodo(llamada);
		Nodo p=null;
		
		if(siVacio()) {
			this.inicio=nuevoNodo;
		}else {
			p=inicio;
			while(p.siguiente!=null) {
				p=p.siguiente;
			}
			p.siguiente=nuevoNodo;
			
		}
		this.extension++;
		return this;
	}
	
	//Borrar el primer elemento
	public Nodo eliminarPrimero() {
		extension--;
		Nodo aBorrar=inicio;
		inicio=inicio.siguiente;
		return aBorrar;
	}

	
	
	
	
	
	
	
	//Metodo para obtener una llamada determinada mediante su numero de telefono
	public Llamada buscar(String telefono) {
		//Se declara una llamada vacia
		Llamada encontrado=null;
		//Se comprueba si la lista enlazada simple no esta vacia
		if(!siVacio()) {
			//Si no
			//Recorremos la lista enlazada
			Nodo actual=inicio;
			boolean condition=true;
			
			while((actual!=null) && (condition)) {
				//Se busca el telefono entre todas las llamadas
				if(actual.llamada.getnTelefono().equalsIgnoreCase(telefono)) {
					//Se encuentra, se retorna
					encontrado=actual.llamada;
					condition=false;
				}else {
					actual=actual.siguiente;
				}

			}
			
		}
		
		return encontrado;
	}
	
	
	
	
	
	
	//Eliminado especifico por medio del numero de telefono provisto
	public void eliminarEspecifico(String telefono) {
		//Nodo a borrar en null
		Nodo aBorrar=null;
		//Se comprueba si la lista no esta vacia
		if(!siVacio()) {
			//Si no lo esta
			Nodo actual=inicio;
			//Si el primer Nodo coincide con el telefono buscado
			if(actual.siguiente==null) {
				if(actual.llamada.getnTelefono().equalsIgnoreCase(telefono)) {
					aBorrar=actual;
					inicio=aBorrar.siguiente;
					extension--;
				}
			}else {
				//Sino
				//Se recorre toda la lista siempre comparando la llamada (Nodo) siguiente
				if(actual.llamada.getnTelefono().equalsIgnoreCase(telefono)) {
					aBorrar=actual;
					inicio=inicio.siguiente;
					extension--;
				}else {
					boolean condition=true;
					while((actual!=null) && (condition)) {
						if(actual.siguiente==null) {
							condition=false;
						}else {
							if(actual.siguiente.llamada.getnTelefono().equalsIgnoreCase(telefono)) {
								aBorrar=actual.siguiente;
								actual.siguiente=aBorrar.siguiente;
								condition=false;
								extension--;
							}
							actual=actual.siguiente;
						}
					}
				}
			}
		}
		
	}
	

	
	
	//Verificar si existe un telefono igual al proporcionado
	public boolean buscarTelefono(String telefono) {
		boolean response=false;
		if(!siVacio()) {
			Nodo actual=inicio;
			boolean condition=true;
			
			while((actual!=null) && (condition)) {
				if(actual.llamada.getnTelefono().equalsIgnoreCase(telefono)) {
					response=true;
					condition=false;
				}
				actual=actual.siguiente;
			}
			
		}
		
		return response;
	}
	
	
	
	
	
	//Retorna el total de llamadas
	public int totalLlamadas() {
		int contador=0;
		if(!siVacio()) {
			Nodo actual=inicio;
			while(actual!=null) {
				contador++;
				actual=actual.siguiente;
			}
		}
		return contador;
	}
	

	
	//Obtener el total de duracion de las llamadas
	public int totalTiempo() {
		int contador=0;
		if(!siVacio()) {
			Nodo actual=inicio;
			while(actual!=null) {
				contador+=actual.llamada.getDuracion();
				actual=actual.siguiente;
			}
		}
		return contador;
	}
	
	//Mostrar todas las llamadas en esta lista
	public String mostrarLLamadas() {
		String llamadas="\n\t\tLlamadas Atendidas";
		if(!siVacio()) {
			int contador=1;
			Nodo actual=inicio;
			while(actual!=null) {
				actual.llamada.actualizarInformacion();
				llamadas+="\nN°"+contador+"\n"+actual.llamada.mostrarLLamada();
				contador++;
				actual=actual.siguiente;
			}
		}
		return llamadas;
	}
	
	
}
