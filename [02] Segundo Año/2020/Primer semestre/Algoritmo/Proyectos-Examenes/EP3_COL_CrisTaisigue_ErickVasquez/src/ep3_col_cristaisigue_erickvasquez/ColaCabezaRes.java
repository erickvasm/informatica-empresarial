package ep3_col_cristaisigue_erickvasquez;

//Cola de las cabezas de res
public class ColaCabezaRes {

	//Referencias
	private NodoCabezaRes primero;
	private NodoCabezaRes ultimo;
	private int longitud;
		
		
	//Constructor
	public ColaCabezaRes() {
		primero=null;
		ultimo=null;
		longitud=0;
	}
		
								
								//Cabeza de Res
		
	//Insertar general
	public void Escribir(CabezaRes dato) {
		if(longitud!=0) {
			InsertarExistente(dato);
		}else {
			InsertarVacia(dato);
		}
	}
		
		
		
	//Insertar en una cola vacia
	public void InsertarVacia(CabezaRes dato) {
		NodoCabezaRes nodoInsertar=new NodoCabezaRes(dato);
		primero=nodoInsertar;
		ultimo=nodoInsertar;
		longitud++;
	}
		
		
	//Insertar en una cola ocupada
	public void InsertarExistente(CabezaRes dato) {
		NodoCabezaRes nodoInsertar=new NodoCabezaRes(dato);
		ultimo.siguiente=nodoInsertar;
		ultimo=nodoInsertar;
		longitud++;
	}
		
		
		
		
		
	//Leer general,usa los metodos LeerUnico y LeerExistente mediante condicionales
	public CabezaRes Leer() {
		CabezaRes dato=null;
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
	public CabezaRes LeerExistente() {
		NodoCabezaRes referencia;
		referencia=primero;
		primero=primero.siguiente;
		longitud--;
		return referencia.dato;
	}
		
		
	//Leer con un unico elemento en la cola
	public CabezaRes LeerUnico() {
		NodoCabezaRes referencia;
		referencia=primero;
		primero=primero.siguiente;
		ultimo=null;
		longitud--;
		return referencia.dato;
	}
		
	//Obtener la longitud de la cola	
	public int getLongitud() {
		return this.longitud;
	}
}
