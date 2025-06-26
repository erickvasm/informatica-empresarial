package ep3_col_cristaisigue_erickvasquez;

//Cola de los camiones
public class ColaCamion {

	//Referencias
	private NodoCamion primero;
	private NodoCamion ultimo;
	private int longitud;
			
			
	//Constructor
	public ColaCamion() {
		primero=null;
		ultimo=null;
		longitud=0;
	}
			
									
							//Camiones
			
	//Insertar general
	public void Escribir(Camion dato) {
		if(longitud!=0) {
			InsertarExistente(dato);
		}else {
			InsertarVacia(dato);
		}
	}
			
			
			
	//Insertar en una cola vacia
	public void InsertarVacia(Camion dato) {
		NodoCamion nodoInsertar=new NodoCamion(dato);
		primero=nodoInsertar;
		ultimo=nodoInsertar;
		longitud++;
	}
			
			
	//Insertar en una cola ocupada
	public void InsertarExistente(Camion dato) {
		NodoCamion nodoInsertar=new NodoCamion(dato);
		ultimo.siguiente=nodoInsertar;
		ultimo=nodoInsertar;
		longitud++;
	}
			
			
			
			
			
	//Leer general,usa los metodos LeerUnico y LeerExistente mediante condicionales
	public Camion Leer() {
		Camion dato=null;
		if(longitud==1) {
			dato= LeerUnico();
		}else {
			if(longitud!=0) {
				dato=LeerExistente();
			}
		}
				
		return dato;
	}
			
	//Leer con mas de un elemento de la cola
	public Camion LeerExistente() {
		NodoCamion referencia;
		referencia=primero;
		primero=primero.siguiente;
		longitud--;
		return referencia.dato;
	}
			
			
	//Leer con un unico elemento en la cola
	public Camion LeerUnico() {
		NodoCamion referencia;
		referencia=primero;
		primero=primero.siguiente;
		ultimo=null;
		longitud--;
		return referencia.dato;
	}
	
	//Obtener longitud de la cola
	public int getLongitud() {
		return this.longitud;
	}
	
}
