	package almacenamiento;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import estructuraDinamica.ListaDoble;
import modelo.Cajero;
import modelo.Consulta;
import modelo.Deposito;
import modelo.Retiro;
import modelo.Transaccion;
import modelo.Usuario;

//Clase para escribir en un archivo usando el manipulador de datos
public class Escribir {

	
	//Posibilitan la escritura de archivos
	private DataManage manipulador=null;
	private final int REGISTRO=1601;//Numero de registros
	private int statusNo=0;
	
	//Referencias a las listas
	private ListaDoble<Transaccion> trans;
	private ListaDoble<Usuario> users;
	private ListaDoble<Cajero> cajero;
	
	
	//Constructor con referencia
	public Escribir(DataManage manipulador,ListaDoble<Transaccion> trans,ListaDoble<Usuario> users,ListaDoble<Cajero> cajero) {
		//Obtener las referencias
		this.manipulador=manipulador;
		this.cajero=cajero;
		this.trans=trans;
		this.users=users;
	}
	
	//Limpiado de archivo para escritura
	public void Limpiar() {
		
		this.statusNo=0;
		
		File archivo=null;
		archivo=manipulador.obtenerArchivo();
		if(archivo!=null) {
			FileWriter escritor=null;
			escritor=manipulador.obtenerEscritor(archivo,false);
			if(escritor!=null) {
				BufferedWriter flujo=null;
				flujo=manipulador.flujoEscritor(escritor);
				if(flujo!=null) {
					boolean escribir=manipulador.Limpiar(flujo);
					if(!escribir) {
						this.statusNo=4;
					}
				}else {
					this.statusNo=3;
				}
			}else {
				this.statusNo=2;
			}
		}else {
			this.statusNo=1;
		}
		
		
	}
	
	
	
	//Obtener el mensaje de error
	public String mensajeError() {
		String mensaje="";
		switch(this.statusNo) {
		

			case 1:{
				mensaje="no se puede acceder al archivo indicado.";
			}break;
		
		
			case 2:{
				mensaje="error al asignar el escritor de archivos.";		
			}break;
					
					
			case 3:{
				mensaje="error al establecer el flujo de datos con el archivo.";
			}break;
			
			
			case 4:{
				mensaje="error al tratar de escribir en el archivo.";
			}break;
		
		}
		
		return mensaje;
	}
	
	
	//Obtener el estado de escritura
	public int obtenerEstado() {
		return this.statusNo;
	}
	
	
	//Guardar los datos
	public void guardar() {
		ArrayList<String> datos=null;
		datos=transformarContenido();
		if((datos!=null) || (datos.size()!=0)) {
			
			Limpiar();
			
			this.statusNo=0;
			File archivo=null;
			archivo=manipulador.obtenerArchivo();
			if(archivo!=null) {
				FileWriter escritor=null;
				escritor=manipulador.obtenerEscritor(archivo,true);
				if(escritor!=null) {
					BufferedWriter flujo=null;
					flujo=manipulador.flujoEscritor(escritor);
					if(flujo!=null) {
						boolean escribir=manipulador.Escribir(flujo, datos);
						if(!escribir) {
							this.statusNo=4;
						}
					}else {
						this.statusNo=3;
					}
				}else {
						this.statusNo=2;
				}
			}else {
				this.statusNo=1;
			}
				
		}
	}
	
	//Transformar todo el contenido de las estructuras dinamicas para su almacenado
	public ArrayList<String> transformarContenido(){
		ArrayList<String> contenido=new <String>ArrayList();
		
		//Agregar cajeros
		cajero.asignarRecorrer(1);
		while(cajero.actual()) {
			contenido.add(cajero.obtenerDato().transformacion());
			cajero.avanzar();
		}
		
		//Agregar usuarios
		users.asignarRecorrer(1);
		while(users.actual()) {
			
			String usuario=users.obtenerDato().transformar();
			
			//Comprobar transacciones
			if(trans.comprobarTransacciones(users.obtenerDato().getnIdentificacion())) {
				//Obtener transacciones
				ArrayList<Transaccion> obtenidas=null;
				obtenidas=trans.obtenerTransacciones(users.obtenerDato().getnIdentificacion());
				if(obtenidas!=null) {
					for(int cont=0;cont<obtenidas.size();cont++) {
						if(obtenidas.get(cont).getTipo()==1) {
							usuario+=((Consulta)obtenidas.get(cont)).transformacion();
						}else if(obtenidas.get(cont).getTipo()==2) {
							usuario+=((Deposito)obtenidas.get(cont)).transformacion();
						}else {
							usuario+=((Retiro)obtenidas.get(cont)).transformacion();
						}
					}
				}
			}
			
			//Comprobar si existe la llave en el contenido buscado
			String hash=Integer.toString(Integer.parseInt(users.obtenerDato().getnIdentificacion())%REGISTRO);
			int existe=existeHash(contenido,hash);
			if(existe==-1) {
				contenido.add(hash+usuario);
			}else {
				contenido.set(existe, contenido.get(existe)+usuario);
			}
			
			users.avanzar();
			
		}
		
		return contenido;
	}
	
	
	
	
	//Comprobar si ya existe una clave de acceso 'hash' en el fichero
	public int existeHash(ArrayList<String> comprober,String value) {
		int general=-1;
		String valorActual="";
		for(int cont=0;cont<comprober.size();cont++) {
			valorActual=comprober.get(cont);
			if(valorActual.length()!=0) {
				String frag[]=valorActual.split("@");
				if(!frag[0].equalsIgnoreCase("cajero")) {
					if(frag[0].equalsIgnoreCase(value)) {
						general=cont;
					}
				}
			}
		}
		return general;
	}
	
}
