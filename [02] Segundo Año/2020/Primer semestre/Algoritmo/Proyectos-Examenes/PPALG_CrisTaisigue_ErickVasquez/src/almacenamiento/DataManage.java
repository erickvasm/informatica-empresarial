package almacenamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

//Esta clase permite establecer un flujo de escritura y lectura con la base de datos
public class DataManage {

	
	//Direccion del archivo
	private final String DIRECCION="src/almacenamiento/Entidad_Financiera.txt";
	
	//Archivo
	private File archivo;
	
	//Lector
	private FileReader lector;
	private BufferedReader streamLeer;
	
	//Escritor
	private FileWriter escritor;
	private BufferedWriter streamEscribir;
	
	//Constructor
	public DataManage() {
		
	}
					
	
								//METODOS PRINCIPALES
	
	//Obtener archivo
	public File obtenerArchivo() {
		try {
			archivo=new File(DIRECCION);
			if((archivo.exists()) && (!archivo.isDirectory())) {
				return archivo;
			}else {
				System.out.println("Error Interno:Verifique la direccion del archivo, debe existir y no debe ser un directorio");
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Interno: error al intentar verificar el archivo revise la direccion");
			return null;
		}
	}
	
	
	//Cerrar flujo de datos IN/OUT
	public void cerrarTodo(boolean type) {
		//Cerrar flujos
		if(type) {
			try {streamLeer.close();}catch(Exception e) {/*Cerrardo*/};
			try {lector.close();}catch(Exception e) {/*Cerrado*/}
		}else {
			try {streamEscribir.close();}catch(Exception e) {/*Cerrardo*/};
			try {escritor.close();}catch(Exception e) {/*Cerrado*/}
		}
	}
	
	
										//RECUPERAR
	//Obtener lector
	public FileReader obtenerLector(File archivo) {
		try {
			if(archivo!=null) {
				lector=new FileReader(archivo);
				return lector;
			}else {
				System.out.println("Error Interno: al momento de definir el lector, el archivo provisto no existe 'null'");
				cerrarTodo(true);
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:al establecer el lector con el archivo provisto");
			cerrarTodo(true);
			return null;
		}
	}
	
	
	//Obtener flujo lector
	public BufferedReader flujoLector(FileReader reader) {
		try {
			if(reader!=null) {
				streamLeer=new BufferedReader(reader);
				return streamLeer;
			}else {
				System.out.println("Error Interno: al momento de definir el flujo de entrada, el lector provisto no existe 'null'");
				cerrarTodo(true);
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:al establecer el flujo con el lector provisto");
			cerrarTodo(true);
			return null;
		}
	}
	
	//Obtener el contenido
	public ArrayList<String> Leer(BufferedReader flujo){
		try {
			ArrayList<String> contenido=new <String>ArrayList();
			if(flujo!=null) {
				String line="";
				while((line=flujo.readLine())!=null) {
					if(!line.equalsIgnoreCase("")) {
						contenido.add(line);
					}
				}
				cerrarTodo(true);
				return contenido;
			}else {
				System.out.println("Error Interno: al momento de definir el contenido a traves del flujo, el flujo de entrada provisto no existe 'null'");
				cerrarTodo(true);
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:obtener el contenido del flujo de entrada");
			cerrarTodo(true);
			return null;
		}
	}
	
	
	
	//Obtener cliente por su ID
	public String[] ObtenerCliente(BufferedReader flujo,String hash){
		try {
			if(flujo!=null) {
				String encontrado=null;
				String line="";
				int contador=0;
				while(((line=flujo.readLine())!=null) && (encontrado==null)) {
					if((!line.equalsIgnoreCase("")) && (line.contains("@"))) {
						String frag[]=line.split("@");
						if((!frag[0].equalsIgnoreCase("cajero"))) {
							contador++;
							if(frag[0].equalsIgnoreCase(hash)){
								encontrado=line;
							}
						}
					}
				}
				cerrarTodo(true);
				if(encontrado!=null) {
					return new String[]{encontrado,contador+""};
				}else {
					return new String[] {"0","0"};
				}
			}else {
				System.out.println("Error Interno: al momento de definir el contenido del cliente buscado a traves del flujo, el flujo de entrada provisto no existe 'null'");
				cerrarTodo(true);
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:obtener el contenido del cliente buscado mediante el flujo de entrada");
			cerrarTodo(true);
			return null;
		}
	}
	
	
	
	
								//ESCRIBIR
	//Obtener escritor
	public FileWriter obtenerEscritor(File archivo,boolean apend) {
		try {
			if(archivo!=null) {
				escritor=new FileWriter(archivo,apend);
				return escritor;
			}else {
				System.out.println("Error Interno: al momento de definir el escritor, el archivo provisto no existe 'null'");
				cerrarTodo(false);
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:al establecer el escritor con el archivo provisto");
			cerrarTodo(false);
			return null;
		}
	}
	
	
	//Flujo de escritura
	public BufferedWriter flujoEscritor(FileWriter writer) {
		try {
			if(writer!=null) {
				streamEscribir=new BufferedWriter(writer);
				return streamEscribir;
			}else {
				System.out.println("Error Interno: al momento de definir el flujo de salida, el escritor provisto no existe 'null'");
				cerrarTodo(false);
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:al establecer el flujo de salida con el escritor provisto");
			cerrarTodo(false);
			return null;
		}
	}
	
	
	//Limpiar el fichero (El archivo tiene un formato corrupto, limpiado para guardado)
	public boolean Limpiar(BufferedWriter flujo){
		try {
			if(flujo!=null) {
				flujo.write("");
				cerrarTodo(false);
				return true;
			}else {
				System.out.println("Error Interno: al momento definir el contenido de salida, el flujo de salida provisto no existe 'null'");
				cerrarTodo(false);
				return false;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:al escribir utilizando el flujo de salida");
			cerrarTodo(false);
			return false;
		}
	}
	
	
	
	
	//Escribir todo el contenido
	public boolean Escribir(BufferedWriter flujo,ArrayList<String> contenido){
		try {
			if(flujo!=null) {
				if(contenido!=null) {
					for(int cont=0;cont<contenido.size();cont++) {
						flujo.write(contenido.get(cont)+"\n");
					}
					cerrarTodo(false);
					return true;
				}else {
					cerrarTodo(false);
					return false;
				}
			}else {
				System.out.println("Error Interno: al momento definir el contenido de salida, el flujo de salida provisto no existe 'null'");
				cerrarTodo(false);
				return false;
			}
		}catch(Exception e) {
			System.out.println("Error Interno:al escribir utilizando el flujo de salida");
			cerrarTodo(false);
			return false;
		}
	}
	
	
}
