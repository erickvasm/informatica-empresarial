package estructuraDinamica;

import modelo.Transaccion;
import modelo.Usuario;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import modelo.Cajero;
import modelo.Consulta;
import modelo.Retiro;
import modelo.Deposito;



//Lista Enlazada Doble Generica
public class ListaDoble<T> {

	
	private Nodo ListaDoble;//Referencia a lista doble
	private Nodo Recorrer;//Referencia para recorrer la lista
	private boolean enOrden;//La lista esta ordenada
	private int intercambio;//Intercambios de ordenamiento
	private int comparaciones;//Comparaciones de ordenamiento
	
	//Constructor del LED
	public ListaDoble() {
		this.ListaDoble=null;
		this.Recorrer=null;
		enOrden=false;
		intercambio=0;
		comparaciones=0;
	}
	
	
	
	
	
	
					//Metodos para manipular la referencia de "Recorrer"
	
	//Esta ordenada
	public boolean isEnOrden() {
		return enOrden;
	}
	
	//Obtener intercambios
	public int getIntercambio() {
		return intercambio;
	}

	//Obtener comparaciones
	public int getComparaciones() {
		return comparaciones;
	}


	//Asignar el dato a la referencia "Recorrer" (Principio,Ultimo,Vacio)
	public void asignarRecorrer(int type) {
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
	
	//Comprobar nodo actual "Recorrer"
	public boolean actual() {
		return (Recorrer!=null)?true:false;
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
	
	
	//Obtener el dato actual en la referencia "Recorrer"
	public T obtenerDato() {
		if(Recorrer!=null) {
			return (T) Recorrer.dato;
		}else {
			return null;
		}
	}
	
	
	
	
								//OPERACIONES DE LA LISTA ENLAZADA DOBLE
	
	//Obtener el elemento por su indice
	public Nodo obtenerIndice(int index) {
		Nodo referencia=ListaDoble;
		Nodo buscado=null;
		for(int cont=0;cont<=index;cont++) {
			
			if((cont==index) && (referencia!=null)) {
				buscado=referencia;
			}
			
			if(referencia.siguiente!=null) {
				referencia=referencia.siguiente;
			}
		}
		return buscado;
	}
	

	
	//Se comprueba si la LED esta vacia
	public boolean Vacia() {
		return (ListaDoble==null)?true:false;
	}
	
	
	//Insertar a la izquierda
	public void InsertarIzquierda(T dato) {
		Nodo nuevoNodo=new Nodo(dato);
		if(Vacia()) {
			ListaDoble=nuevoNodo;
		}else {
			nuevoNodo.siguiente=ListaDoble;
			nuevoNodo.siguiente.anterior=nuevoNodo;
			ListaDoble=nuevoNodo;
		}
	}
	
	
	
	//Insertar a la derecha
	public void InsertarDerecha(T dato) {
		Nodo nuevoNodo=new Nodo(dato);
		if(Vacia()) {
			InsertarIzquierda(dato);
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
	
	
	//Borrar la lista
	public void borrarLista() {
		this.ListaDoble=null;
	}
	
	
				
	//Se obtiene la longitud de la LED
	public int obtenerLongitud() {
		int general=0;
		Nodo referencia=ListaDoble;
		while(referencia!=null) {
			referencia=referencia.siguiente;
			general++;
		}
		return general;
	}
	
	
	
							//DATO TRANSACCIONES
	//Obtener siguiente numero por tipo
	public int obtenerNumero(int type) {
		int general=0;
		if(!Vacia()) {
			Nodo recorrer=ListaDoble;
			while(recorrer!=null) {
				if(((Transaccion)recorrer.dato).getTipo()==type) {
					if(((Transaccion)recorrer.dato).getnTransaccion()>general) {
						general=((Transaccion)recorrer.dato).getnTransaccion();
					}
				}
				recorrer=recorrer.siguiente;
			}
		}
		return general+1;
	}
	
	
	
	//Obtener transaccion
	public Transaccion obtenerTransaccion() {
		if(Recorrer!=null) {
			if(((Transaccion)Recorrer.dato).getTipo()==1) {
				return ((Consulta)Recorrer.dato);
			}else if(((Transaccion)Recorrer.dato).getTipo()==2) {
				return ((Deposito)Recorrer.dato);
			}else {
				return ((Retiro)Recorrer.dato);
			}
		}else {
			return null;
		}
	}
	
	//Comprobar si el cliente tiene transacciones
	public boolean comprobarTransacciones(String id) {
		boolean response=false;
		Nodo recorrer=ListaDoble;
		while((recorrer!=null) && (!response)) {
			if(((Transaccion)recorrer.dato).getCliente().equalsIgnoreCase(id)) {
				response=true;
			}
			recorrer=recorrer.siguiente;
		}
		return response;
	}
	
	//Obtener las transacciones de cierto cliente
	public ArrayList<Transaccion> obtenerTransacciones(String value){
		try {
			ArrayList<Transaccion> tra=new <String>ArrayList();
			Nodo recorrer=ListaDoble;
			while(recorrer!=null) {
				if(((Transaccion)recorrer.dato).getCliente().equalsIgnoreCase(value)){
					tra.add((Transaccion)recorrer.dato);
				}
				recorrer=recorrer.siguiente;
			}
			if(tra.size()!=0) {
				return tra;
			}else {
				return null;
			}
			
		}catch(Exception e) {
			return null;
		}
	}
	
	
	
							//DATO CAJERO
	//Buscar coincidencias del codigo
	public boolean coincidenciaCodigo(int value) {
		boolean respuesta=false;
		if(!Vacia()) {
			Nodo recorrer=ListaDoble;
			while((recorrer!=null) && (!respuesta)) {
				if(((Cajero)recorrer.dato).getCodigoCajero()==value) {
					respuesta=true;
				}
				recorrer=recorrer.siguiente;
			}
		}
		return respuesta;
	}
	
	//Rellenar el combobox en el area de acceso
	public void rellenarCombo(JComboBox referencia) {
		if(Vacia()) {
			referencia.setModel(new DefaultComboBoxModel(new String[] {"No existen cajeros"}));
			referencia.setEnabled(false);
		}else {
			Nodo recorrer=ListaDoble;
			referencia.setModel(new DefaultComboBoxModel(new String[] {}));
			while(recorrer!=null) {
				referencia.addItem(((Cajero)recorrer.dato).getCodigoCajero()+"@"+((Cajero)recorrer.dato).getUbicacion());
				recorrer=recorrer.siguiente;
			}
		}
	}
	
							//DATO CLIENTE
	//Buscar coincidencias de indentificacion
	public boolean coincidenciaID(String value) {
		boolean respuesta=false;
		if(!Vacia()) {
			Nodo recorrer=ListaDoble;
			while((recorrer!=null) && (!respuesta)) {
				if(((Usuario)recorrer.dato).getnIdentificacion().equalsIgnoreCase(value)) {
					respuesta=true;
				}
				recorrer=recorrer.siguiente;
			}
		}
		return respuesta;
	}

	

	//Obtener el cliente por cuenta
	public Usuario obtenerCliente(String id) {
		if(!Vacia()) {
			Usuario buscado=null;
			Nodo recorrer=ListaDoble;
			while((recorrer!=null) && (buscado==null)) {
				if(((Usuario)recorrer.dato).getnIdentificacion().equalsIgnoreCase(id)) {
					buscado=(Usuario)recorrer.dato;
				}
				recorrer=recorrer.siguiente;
			}
			if(buscado!=null) {
				return buscado;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	
	
	//Buscar coincidencias de indentificacion
	public boolean coincidenciaCuenta(int value) {
		boolean respuesta=false;
		if(!Vacia()) {
			Nodo recorrer=ListaDoble;
			while((recorrer!=null) && (!respuesta)) {
				if(((Usuario)recorrer.dato).getnCuenta()==value) {
					respuesta=true;
				}
				recorrer=recorrer.siguiente;
			}
		}
		return respuesta;
	}
	
	//>ALGORITMOS DE ORDENAMIENTO
	
	
	
	
		
	
	
										//METODO QUICKSORT
	
	//Obtener el ultimo nodo
    public Nodo lastNodo(Nodo Nodo){ 
        while(Nodo.siguiente!=null) { 
            Nodo = Nodo.siguiente; 
        }
        return Nodo; 
    } 
	
    //Establecer pivote en cada iteracion
	 public Nodo partition(Nodo l,Nodo h) { 
	       //Establecer el pivote
	        int x = ((Usuario) h.dato).getnCuenta(); 
	           
	        Nodo i = l.anterior; 
	          
	        for(Nodo j=l; j!=h; j=j.siguiente) {
	        	
	        	//Comparacion
	        	comparaciones++;
	            if(((Usuario)j.dato).getnCuenta() <= x) 
	            { 
	            	
	                i = (i==null) ? l : i.siguiente; 
	                Usuario temp = ((Usuario)i.dato); 
	                i.dato = j.dato;
	                j.dato = temp;
	                //Intercambio
	                intercambio++;
	            } 
	        } 
	        i = (i==null) ? l : i.siguiente;//Al siguiente
	        Usuario temp = ((Usuario)i.dato); 
	        i.dato = h.dato;
	        h.dato = temp;
	        //Intercambio
	        intercambio++;
	        return i; 
	    } 
	      
	   	//Metodo recursivo quicksort
	    public void _quickSort(Nodo l,Nodo h) 
	    { 
	        if((h!=null) && l!=h && (l!=h.siguiente)){ 
	            Nodo temp = partition(l,h); 
	            _quickSort(l,temp.anterior); 
	            _quickSort(temp.siguiente,h); 
	        } 
	    } 
	    
	    //LLama al quicksort recursivo
	    public void quickSort() 
	    { 	
	    	
	        enOrden=true;
	    	comparaciones=0;
	    	intercambio=0;
	    	if(!Vacia()) {
	    		Nodo head = lastNodo(ListaDoble); 
	    		//Llamar al quicksort recursivo
		  	     _quickSort(ListaDoble,head); 
	    	}
	    } 

	    	    
	    										//METODO SHELL
	    
	    
	    //Ordenar la LED mediante el algoritmo de ordenamiento SHELL
	    public void shell() {
	    	
	    	enOrden=true;
	    	comparaciones=0;
	    	intercambio=0;
	    	
	        Usuario aux;
	        int i;
	        boolean cambios;
	        
	        //Establecer central
	        for (int salto =((obtenerLongitud()%2)!=0)?Math.round(obtenerLongitud()/2):(obtenerLongitud()/2); salto != 0; salto /= 2) {
	            cambios = true;
	            while (cambios) {//Cuando existan intercambios                                      
	                cambios = false;
	                int longitud=obtenerLongitud();
	                for (i = salto; i < longitud; i++)//Pasada
	                {
	                	//Comparacion
	                	comparaciones++;
	                    if ( ((Usuario)obtenerIndice(i-salto).dato).getnCuenta() > ((Usuario)obtenerIndice(i).dato).getnCuenta()) {
	                    	//Intercambio
	                    	intercambio++;
	                    	aux = ((Usuario)obtenerIndice(i).dato);
	                    	obtenerIndice(i).dato=((Usuario)obtenerIndice(i-salto).dato);
	                    	obtenerIndice(i-salto).dato=aux;
	                        cambios = true;                                 
	                    }
	                }
	            }
	        }
	} 
}
