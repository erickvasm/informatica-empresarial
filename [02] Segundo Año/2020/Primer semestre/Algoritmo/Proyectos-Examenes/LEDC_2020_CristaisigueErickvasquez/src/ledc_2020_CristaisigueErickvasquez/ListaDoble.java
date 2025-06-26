package ledc_2020_CristaisigueErickvasquez;
import javax.swing.table.DefaultTableModel;

//Clase Lista Doble
public class ListaDoble {

	
	Nodo ListaDoble;//Referencia a lista doble
	Nodo Recorrer;//Referencia para recorrer la lista
	
	//Constructor
	public ListaDoble() {
		this.ListaDoble=null;
		this.Recorrer=null;
	}
	
	
	
	
	
	
					//Metodos para manipular la referencia de "Recorrer"
	
	//Asignar el valor a la referencia "Recorrer" (Principio,Ultimo,Vacio)
	public void asignar(int type) {
		switch(type) {
			case 1:{
				Recorrer=ListaDoble;
			}break;
				
			case 2:{
				Recorrer=ListaDoble;
				while(Recorrer.siguiente!=null) {
					Recorrer=Recorrer.siguiente;
				}
			}break;
			
			case 3:{
				Recorrer=null;
			}break;
		}
	}
	
	//Comprobar nodo anterior "Recorrer"
	public boolean anterior() {
		return (Recorrer.anterior!=null)?true:false;
	}
	
	
	//Comprobar nodo siguiente "Recorrer"
	public boolean siguiente() {
		return (Recorrer.siguiente!=null)?true:false;
	}
	
	//Avanzar "Recorrer"
	public void avanzar() {
		Recorrer=Recorrer.siguiente;
	}
	
	//Retroceder "Recorrer"
	public void retroceder() {
		Recorrer=Recorrer.anterior;
	}
	
	//Obtener el valor actual en la referencia "Recorrer"
	public Estudiante obtenerValor() {
		return Recorrer.estudiante;
	}
	
	
	
	
	
	
						//Operaciones De La Lista
	
	//Es vacia
	public boolean Vacia() {
		return (ListaDoble==null)?true:false;
	}
	
	
	//Insertar a la izquierda
	public void InsertarIzquierda(Estudiante estudiante) {
		Nodo nuevoNodo=new Nodo(estudiante);
		if(Vacia()) {
			ListaDoble=nuevoNodo;
		}else {
			nuevoNodo.siguiente=ListaDoble;
			nuevoNodo.siguiente.anterior=nuevoNodo;
			ListaDoble=nuevoNodo;
		}
	}
	
	
	//Insertar un nuevo elemento (antes,despues) de la referencia dada
	//el parametro type determinara si se insertar despues o antes de la referencia
	//el paramtro carnet ayudara a localizar dicha referencia
	public boolean InsertarReferencia(Estudiante estudiante,boolean type,String carnet) {
		boolean response=false;
		//Se confirma que exista la referencia
		if(!Vacia() && (buscarCoincidencias(carnet))) {
			
			Nodo Agregar=new Nodo(estudiante);
			Nodo Referencia=ListaDoble;
		
			boolean condition=true;
			
			//Se localiza
			while((Referencia!=null) && (condition)) {
				if(Referencia.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					condition=false;
				}else {
					Referencia=Referencia.siguiente;
				}
			}
			
			//Se determina si el nodo referente tiene anterior y siguiente
			boolean RefAnte=(Referencia.anterior!=null)?true:false;
			boolean RefSig=(Referencia.siguiente!=null)?true:false;
			
			//Insertar antes del nodo referente
			if(type) {
				//El nodo referente tiene un nodo anterior
				if(RefAnte) {
					//Si
					Agregar.siguiente=Referencia;
					Agregar.anterior=Referencia.anterior;
					Referencia.anterior.siguiente=Agregar;
					Referencia.anterior=Agregar;
				}else {
					//No
					Agregar.siguiente=Referencia;
					Agregar.siguiente.anterior=Agregar;
					ListaDoble=Agregar;
				}
			}else {
				//Insertar despues del nodo referente
				
				//El nodo referente tiene un nodo siguiente
				if(RefSig) {
					//Si
					Agregar.anterior=Referencia;
					Agregar.siguiente=Referencia.siguiente;
					Referencia.siguiente.anterior=Agregar;
					Referencia.siguiente=Agregar;
				}else{
					//No
					Agregar.anterior=Referencia;
					Referencia.siguiente=Agregar;
				}
			}
	
			response=true;
					
		}
		return response;
	}
	
	
	//Insertar a la derecha
	public void InsertarDerecha(Estudiante estudiante) {
		Nodo nuevoNodo=new Nodo(estudiante);
		if(Vacia()) {
			InsertarIzquierda(estudiante);
		}else {
			Nodo actual=ListaDoble;
			while(actual.siguiente!=null) {
				actual=actual.siguiente;
			}
			nuevoNodo.siguiente=actual.siguiente;
			actual.siguiente=nuevoNodo;
			nuevoNodo.anterior=actual;
			
		}
	}
	
	
	//Buscar si el carnet provisto ya esta en uso
	public boolean buscarCoincidencias(String carnet) {
		boolean response=false;
		if(!Vacia()) {
			Nodo Actual=ListaDoble;
			boolean condition=true;
			while((Actual!=null) && (condition)) {
				if(Actual.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					response=true;
					condition=false;
				}
				Actual=Actual.siguiente;
			}
		}
		return response;
	}
	
	
	
	

	
	//Borrar un estudiante especifico
	public Nodo BorrarEspecifico(String carnet) {
		Nodo aBorrar=null;
		//Se comprueba que la lista no este vacia
		if(!Vacia()) {
			
			Nodo Actual=ListaDoble;
			boolean condition=true;
			//Se recorre la lista para localizar el elemento a eliminar
			while((Actual!=null) && (condition)) {
				if(Actual.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					aBorrar=Actual;
					condition=false;
				}
				Actual=Actual.siguiente;
			}
			
			//Se encontro el estudiante
			if(aBorrar!=null) {
				
				//Se determina si el nodo al que pertenece el estudiante
				//tiene nodo siguiente y anterior
				boolean existAnte=(aBorrar.anterior!=null)?true:false;
				boolean existSig=(aBorrar.siguiente!=null)?true:false;
				
				
				if((!existAnte) && (existSig)) {
					//Si el nodo no tiene anterior Y tiene siguiente
					aBorrar.siguiente.anterior=null;
					ListaDoble=aBorrar.siguiente;
				}else if((existAnte) && (existSig)) {
					//Si el nodo tiene anterior Y tiene siguiente
					aBorrar.siguiente.anterior=aBorrar.anterior;
					aBorrar.anterior.siguiente=aBorrar.siguiente;
				}else if((existAnte) && (!existSig)) {
					//Si el nodo tiene anterior Y no tiene siguiente
					aBorrar.anterior.siguiente=null;
				}else if((!existAnte) && (!existSig)) {
					//Si el nodo no tiene anterior Y no tiene siguiente
					ListaDoble=null;
				}
					
			}	
			
		}
		
		
		return aBorrar;
	}
	
	
	
	
	//Desplegar todos los estudiantes por medio de System Print
	public void desplegar() {
		if(!Vacia()) {
			Nodo actual=ListaDoble;
			while(actual!=null) {
				System.out.println(actual.estudiante.getCarnet());
				actual=actual.siguiente;
			}
		}
	}
	
	
	//Metodo para mostrar a todos los estudiantes en una tabla automaticamente
	public Nodo mostrarLista(DefaultTableModel modelo) {	
		
		Nodo actual=ListaDoble;
		modelo.setRowCount(0);//Se resetea la tabla
		//Se comprueba si no esta vacia
		if(!Vacia()) {
			int cont=0;
			//Se despliega todos los Estudiantes de la lista por medio de la tabla provista
			while(actual!=null) {
				cont++;
				String carnet=actual.estudiante.getCarnet();
				String carrera=actual.estudiante.getCarrera();
				String promedio=Double.toString(actual.estudiante.getPromedioPonderado());
				String nombre=actual.estudiante.getNombre();
				String tipoBeca="";
					
				if(actual.estudiante.getTipoBeca()=='N') {
					tipoBeca="No posee";
				}else {
					tipoBeca=Character.toString(actual.estudiante.getTipoBeca());
				}
					
				modelo.addRow(new String[] {Integer.toString(cont),carnet,nombre,carrera,tipoBeca,promedio});
					
				actual=actual.siguiente;
			}		
		}
		return actual;
	}
	
	
	//Obtener estudiante por medio de su carnet
	public Estudiante ObtenerEstudiante(String carnet) {
		Estudiante buscado=null;
		if(!Vacia()) {
			Nodo recorrer=ListaDoble;
			boolean condition=true;
			while((recorrer!=null) && (condition)) {
				if(recorrer.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					buscado=recorrer.estudiante;
					condition=false;
				}
				recorrer=recorrer.siguiente;
			}
		}
		
		return buscado;
	}
	
}
