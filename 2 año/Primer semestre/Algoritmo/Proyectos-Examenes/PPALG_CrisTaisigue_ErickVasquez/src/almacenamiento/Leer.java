package almacenamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JTextArea;

import estructuraDinamica.ListaDoble;
import modelo.Cajero;
import modelo.Consulta;
import modelo.Deposito;
import modelo.Retiro;
import modelo.Transaccion;
import modelo.Usuario;

//Clase leer, permite obtener los datos de un fichero
public class Leer {

	private DataManage manipulador=null;//Manipulador de archivos
	private ArrayList<String> recuperado=null;//Contenido recuperado
	private int errorNo=0;//Estado de lectura
	private final int REGISTRO=1601;//Numero de registros
	
	
	//Constructor recibe el manipulador de archivos
	public Leer(DataManage manipulador) {
		this.manipulador=manipulador;
	}
	
	
	
	//Obtener el numero de error
	public int obtenerError() {
		return this.errorNo;
	}
	
	
	//Obtener contenido del archivo
	public void leerRecuperados(){
		
		this.errorNo=0;
		this.recuperado=null;
		
		File archivo=null;
		archivo=manipulador.obtenerArchivo();
		
		
		if(archivo!=null) {
		
			FileReader lector=null;	
			lector=manipulador.obtenerLector(archivo);
			if(lector!=null) {
				
				BufferedReader flujo=null;
				flujo=manipulador.flujoLector(lector);
				
				if(flujo!=null) {
					
					ArrayList<String> texto=null;
					texto=manipulador.Leer(flujo);
					
					if(texto!=null) {
						this.recuperado=texto;
					}else {
						this.errorNo=4;
					}
					
				}else {
					this.errorNo=3;
				}
				
			}else {
				this.errorNo=2;
			}
			
		}else {
			this.errorNo=1;
		}
	
	}
	
	
	//Obtener el mensaje de error
	public String mensajeError() {
		String mensaje="";
		
		switch(this.errorNo) {
			
			case 1:{
				mensaje="No se ha encontrado el archivo en la direccion indicada.";
			}break;
			
			case 2:{
				mensaje="Ocurrio un error al tratar de definir el lector de archivos.";
			}break;
			
			
			case 3:{
				mensaje="Ocurrio un error en la transferencia de datos.";
			}break;


			case 4:{
				mensaje="Ocurrio un error al momento de recuperar la informacion del archivo";
			}break;
		}
		
		return mensaje;
	}
	
	//El contenido recuperado se guarda en las estructuras dinamicas
	public void transformarLeer(ListaDoble<Transaccion> tra,ListaDoble<Usuario> user,ListaDoble<Cajero> caja) {
		
		leerRecuperados();
		if(obtenerError()==0) {
			ArrayList<String> contenido=null;
			contenido=obtenerRecuperados();
			if(contenido!=null) {
				String linea="";
				for(int cont=0;cont<contenido.size();cont++) {
					if(contenido.get(cont).length()!=0) {
						
						linea=contenido.get(cont);
						String fragOne[]=linea.split("@");
						
						//Tipo de registro
						if(fragOne[0].equalsIgnoreCase("cajero")) {
							//Cajero
							if(fragOne[1].length()!=0) {
								String fragTwo[]=fragOne[1].split(",");
								Cajero nuevoCajero=new Cajero();
								nuevoCajero.setCodigoCajero(Integer.parseInt(fragTwo[0]));
								nuevoCajero.setUbicacion(fragTwo[1]);
								caja.InsertarDerecha(nuevoCajero);
							}
						}else {
							//Cliente
							for(int sec=1;sec<fragOne.length;sec++) {
								if(fragOne[sec].length()!=0) {
									//Contiene transacciones
									if(!fragOne[sec].contains("/")) {
										//Creacion de usuario
										String fragTwo[]=fragOne[sec].split(",");
										Usuario nuevoUsuario=new Usuario();
										nuevoUsuario.setnIdentificacion(fragTwo[0]);
										nuevoUsuario.setnCuenta(Integer.parseInt(fragTwo[1]));
										nuevoUsuario.setnClave(Integer.parseInt(fragTwo[2]));
										nuevoUsuario.setNombre(fragTwo[3]);
										nuevoUsuario.setDireccion(fragTwo[4]);
										nuevoUsuario.setSaldo(Double.parseDouble(fragTwo[5]));
										
										user.InsertarDerecha(nuevoUsuario);
										
										
									}else {
										//Creacion de usuario
										String primarialFrag[]=fragOne[sec].split("/");
										String fragTwo[]=primarialFrag[0].split(",");
										Usuario nuevoUsuario=new Usuario();
										nuevoUsuario.setnIdentificacion(fragTwo[0]);
										nuevoUsuario.setnCuenta(Integer.parseInt(fragTwo[1]));
										nuevoUsuario.setnClave(Integer.parseInt(fragTwo[2]));
										nuevoUsuario.setNombre(fragTwo[3]);
										nuevoUsuario.setDireccion(fragTwo[4]);
										nuevoUsuario.setSaldo(Double.parseDouble(fragTwo[5]));
										
										user.InsertarDerecha(nuevoUsuario);
										
										//Agregar transacciones
										if(primarialFrag.length>1) {
											for(int i=1;i<primarialFrag.length;i++) {
												
												if(primarialFrag[i].length()>1) {
													String fragFour[]=primarialFrag[i].split(",");
										
													
													
													
													if(fragFour[0].equalsIgnoreCase("1")) {
														//Consulta
														
														Transaccion temp=new Consulta();
														temp.setTipo(1);
														temp.setFecha(fragFour[1]);
														temp.setHora(fragFour[2]);
														temp.setCajero(Integer.parseInt(fragFour[3]));
														temp.setCliente(nuevoUsuario.getnIdentificacion());
														temp.setnTransaccion(Integer.parseInt(fragFour[4]));
														((Consulta)temp).setSaldo(Double.parseDouble(fragFour[5]));
														tra.InsertarIzquierda(temp);
														
														
														
													}else if(fragFour[0].equalsIgnoreCase("2")){
														//Deposito
														Transaccion temp=new Deposito();
														temp.setTipo(2);
														temp.setFecha(fragFour[1]);
														temp.setHora(fragFour[2]);
														temp.setCajero(Integer.parseInt(fragFour[3]));
														temp.setCliente(nuevoUsuario.getnIdentificacion());
														temp.setnTransaccion(Integer.parseInt(fragFour[4]));
														((Deposito)temp).setMontoDepositado(Double.parseDouble(fragFour[5]));
														((Deposito)temp).setNuevoSaldo(Double.parseDouble(fragFour[6]));
														
														
														tra.InsertarIzquierda(temp);
														
														
													}else {
														//Retiro
														Transaccion temp=new Retiro();
														temp.setTipo(3);
														temp.setFecha(fragFour[1]);
														temp.setHora(fragFour[2]);
														temp.setCajero(Integer.parseInt(fragFour[3]));
														temp.setCliente(nuevoUsuario.getnIdentificacion());
														temp.setnTransaccion(Integer.parseInt(fragFour[4]));
														((Retiro)temp).setMontoDebitado(Double.parseDouble(fragFour[5]));
														((Retiro)temp).setSaldoCorrespondiente(Double.parseDouble(fragFour[6]));
														
														tra.InsertarIzquierda(temp);
														
														
													}
												}
													
													
												}
												
												
											}
										}
										
										
										
									}
									
							}
						}
					}
				}
			}
		}
		
		
		
	}


	//Al realizar una busqueda por llave 'Hash' de un cliente
	public void mostrarBusqueda(String identificacion, JTextArea referencia) {
		String data[]=null;
		
		String hash=Integer.toString(Integer.parseInt(identificacion)%REGISTRO);
		
		data=BusquedaHash(hash);
		
		if(data!=null) {
			if(!data[0].equalsIgnoreCase("0")) {
				
				String frag[]=data[0].split("@");
				Usuario tempUser=null;
				int pos=-1;
				
				for(int cont=1;cont<frag.length;cont++) {
					
					String fragTwo[]=frag[cont].split(",");
					if(fragTwo[0].equalsIgnoreCase(identificacion)) {
						if(fragTwo[5].contains("/")) {
							String simpleFrag[]=fragTwo[5].split("/");
							fragTwo[5]=simpleFrag[0];
						}
						pos=cont;
						tempUser=new Usuario();
						tempUser.setnIdentificacion(fragTwo[0]);
						tempUser.setnCuenta(Integer.parseInt(fragTwo[1]));
						tempUser.setnClave(Integer.parseInt(fragTwo[2]));
						tempUser.setNombre(fragTwo[3]);
						tempUser.setDireccion(fragTwo[4]);
						tempUser.setSaldo(Double.parseDouble(fragTwo[5]));
						
						referencia.setText("\t\tCliente Encontrado\nDireccion: Posicion "+cont+"  Llave "+hash+"\n"+tempUser.mostrarInformacion());
					}
						
				}
					
				if(pos!=-1) {
					String user=frag[pos];
					if(user.contains("/")) {
						
						String primarialFrag[]=user.split("/");
						if(primarialFrag.length>1) {
							for(int i=1;i<primarialFrag.length;i++) {
								
								if(primarialFrag[i].length()>1) {
									String fragFour[]=primarialFrag[i].split(",");
						
									if(fragFour[0].equalsIgnoreCase("1")) {
										//Consulta
										
										Transaccion temp=new Consulta();
										temp.setTipo(1);
										temp.setFecha(fragFour[1]);
										temp.setHora(fragFour[2]);
										temp.setCajero(Integer.parseInt(fragFour[3]));
										temp.setCliente(tempUser.getnIdentificacion());
										temp.setnTransaccion(Integer.parseInt(fragFour[4]));
										((Consulta)temp).setSaldo(Double.parseDouble(fragFour[5]));
										
										referencia.append("\n"+i+":\n"+((Consulta)temp).mostrarInformacion());
										
									}else if(fragFour[0].equalsIgnoreCase("2")){
										//Deposito
										Transaccion temp=new Deposito();
										temp.setTipo(2);
										temp.setFecha(fragFour[1]);
										temp.setHora(fragFour[2]);
										temp.setCajero(Integer.parseInt(fragFour[3]));
										temp.setCliente(tempUser.getnIdentificacion());
										temp.setnTransaccion(Integer.parseInt(fragFour[4]));
										((Deposito)temp).setMontoDepositado(Double.parseDouble(fragFour[5]));
										((Deposito)temp).setNuevoSaldo(Double.parseDouble(fragFour[6]));
										
										referencia.append("\n"+i+":\n"+((Deposito)temp).mostrarInformacion());
										
									}else {
										//Retiro
										Transaccion temp=new Retiro();
										temp.setTipo(3);
										temp.setFecha(fragFour[1]);
										temp.setHora(fragFour[2]);
										temp.setCajero(Integer.parseInt(fragFour[3]));
										temp.setCliente(tempUser.getnIdentificacion());
										temp.setnTransaccion(Integer.parseInt(fragFour[4]));
										((Retiro)temp).setMontoDebitado(Double.parseDouble(fragFour[5]));
										((Retiro)temp).setSaldoCorrespondiente(Double.parseDouble(fragFour[6]));
										
										referencia.append("\n"+i+"\n"+((Retiro)temp).mostrarInformacion());
										
								}
							}
									
									
						}
								
								
					}
						
						
					}else {
						referencia.append("\nEste cliente no tiene transacciones");
					}
				}
			}else {
				referencia.setText("No se ha encotrado el cliente con la identificacion provista");
			}
		}
	}
	
	
	
	
	
	//Devolver resultados de la busqueda por dispersion hash
	public String[] BusquedaHash(String hash) {
		
		this.errorNo=0;
		
		String central[]=null;
		
		File archivo=null;
		archivo=manipulador.obtenerArchivo();
		if(archivo!=null) {
			
			FileReader lector=null;
			lector=manipulador.obtenerLector(archivo);
			
			if(lector!=null) {
				
				BufferedReader flujo=null;
				flujo=manipulador.flujoLector(lector);
				
				if(flujo!=null) {
					String data[]=null;
					data=manipulador.ObtenerCliente(flujo, hash);
					if(data!=null) {
						central=data;
						return data;
					}else {
						
					}
				}else {
					
				}
				
			}else {
				
			}
			
			
		}else {
			
		}
		
		return central;
	}
	
	//Obtener recuperados
	public ArrayList<String> obtenerRecuperados(){
		return this.recuperado;
	}
	
}
