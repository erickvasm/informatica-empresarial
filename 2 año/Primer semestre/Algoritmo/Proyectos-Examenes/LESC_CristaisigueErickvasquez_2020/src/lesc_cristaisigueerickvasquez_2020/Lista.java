package lesc_cristaisigueerickvasquez_2020;

import javax.swing.table.DefaultTableModel;

//Lista enlazada simple
public class Lista {
	
	//Atributos
	private Nodo inicio;	
	private int extension;
	
	//Constructor
	public Lista() {
		this.inicio=null;
		this.extension=0;
	}
	
	
	
	//Se determina si la lista enlazada simple esta vacia
	public boolean siVacio() {
		return (inicio==null)?true:false;
	}

	
	
	
	
	//Insertar en primera posicion
	public Lista insertar(Estudiante estudiante) {
		Nodo nuevoNodo=new Nodo(estudiante);
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


	
	//Metodo para obtener un Estudiante determinado mediante su carnet
	public Estudiante buscar(String carnet) {
		Estudiante encontrado=null;
		if(!siVacio()) {
			
			Nodo actual=inicio;
			boolean condition=true;
			
			while((actual!=null) && (condition)) {
				if(actual.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					encontrado=actual.estudiante;
					condition=false;
				}else {
					actual=actual.siguiente;
				}
			}
			
		}
		
		return encontrado;
	}
	
	

	//Eliminado especifico
	public void eliminarEspecifico(String carnet) {
		Nodo aBorrar=null;
		if(!siVacio()) {
			Nodo actual=inicio;
			if(actual.siguiente==null) {
				if(actual.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					aBorrar=actual;
					inicio=aBorrar.siguiente;
					extension--;
				}
			}else {

				if(actual.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					aBorrar=actual;
					inicio=inicio.siguiente;
					extension--;
				}else {
					boolean condition=true;
					while((actual!=null) && (condition)) {
						if(actual.siguiente==null) {
							condition=false;
						}else {
							if(actual.siguiente.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
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
	
	

	
	//Verificar si existe un carnet igual al proporcionado
	public boolean buscarCarnet(String carnet) {
		boolean response=false;
		if(!siVacio()) {
			//System.out.println("siu");
			Nodo actual=inicio;
			boolean condition=true;
			
			while((actual!=null) && (condition)) {
				if(actual.estudiante.getCarnet().equalsIgnoreCase(carnet)) {
					response=true;
					condition=false;
				}
				actual=actual.siguiente;
			}
			
		}
		
		return response;
	}
	
	
	
	//Metodo para mostrar a todos los estudiantes en una tabla automaticamente
	public Nodo mostrarLista(DefaultTableModel modelo) {
		Nodo actual=inicio;
		modelo.setRowCount(0);
		if(!siVacio()) {
			int cont=0;
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
	
	
	
	
}
