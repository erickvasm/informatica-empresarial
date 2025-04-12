package examen;

import java.lang.Thread;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

//El hilo que muestra los totales
public class HiloTotales extends Thread{

	
	private boolean condition=true;//Condicion que indicara si se despliegan los totales
	
	private ArrayList<Paciente> referencia;//Referencia al arrayList central
	
	private JLabel cantidad;//Cantidad total de pacientes
	private JProgressBar barUno;//Cantidad total de pacientes masculinos
	private JProgressBar barDos;//Cantidad total de pacientes Femeninos
	private JProgressBar barTres;//Cantidad total de pacientes estables
	private JProgressBar barCuatro;//Cantidad total de pacientes regulares
	private JProgressBar barCinco;//Cantidad total de pacientes estado instesivo
	
	//Metodo que recibe la referencia de estos campos
	public HiloTotales(JLabel cantidad,JProgressBar barUno,JProgressBar barDos,JProgressBar barTres,
			JProgressBar barCuatro,JProgressBar barCinco,ArrayList<Paciente> referencia,String nombre) {

		super(nombre);//Establecemos el nombre del hilo

		//Establecemos valores a las referencias mediante los parametros
		this.cantidad=cantidad;
		this.referencia=referencia;
		this.barUno=barUno;
		this.barDos=barDos;
		this.barTres=barTres;
		this.barCuatro=barCuatro;
		this.barCinco=barCinco;
		
	}
	
	//Metodo run() estructuracion del hilo
	//Mediante el metodo run(), haremos que los totales se despliegen
	//refrescando la informacion de los JProgressBar's haciendo uso del metodo valor(x)
	//que dicho devuelve el porcentaje de alguna caracteristica solicitada entre el total de pacientes
	public void run() {
		//Mientras que la condicion
		while(condition) {
			try {
				
				currentThread().sleep(500);//Intervalo de medio segundo
				
				cantidad.setText("Cantidad Paciente Totales:"+referencia.size());//Pacientes totales
				barUno.setValue(valor(1));//Pacientes masculinos
				barDos.setValue(valor(2));//Pacientes femeninos
				barTres.setValue(valor(3));//Pacientes en condicion estable
				barCuatro.setValue(valor(4));//Pacientes en condicion regular
				barCinco.setValue(valor(5));//Pacientes en condicion intensiva
				
			}catch(Exception e) {
				
			}
		}
	}
	
	//Este metodo devuelve el total en porcentajes de alguna caracteristica solicita
	//entre los pacientes
	public int valor(int tipo) {
		int general=0;
		
		switch(tipo) {
		
			//Buscar los pacientes Masculinos
			case 1:{
				if(referencia.size()!=0) {
					for(Paciente paciente:referencia) {
						if(paciente.getGenero()=='M') {
							general++;
						}
					}
					general=(int)(((double)general/referencia.size())*100);
				}
			}break;
			
		
			//Buscar los pacientes Femeninos
			case 2:{
				if(referencia.size()!=0) {
					for(Paciente paciente:referencia) {
						if(paciente.getGenero()=='F') {
							general++;
						}
					}
					general=(int)(((double)general/referencia.size())*100);
				}
			}break;
			
		
			//Buscar los pacientes con condicion estable
			case 3:{
				if(referencia.size()!=0) {
					for(Paciente paciente:referencia) {
						if(paciente.getCondicion().equalsIgnoreCase("Estable")) {
							general++;
						}
					}
					general=(int)(((double)general/referencia.size())*100);
				}
			}break;
			
			
			//Buscar los pacientes con condicion regular
			case 4:{
				if(referencia.size()!=0) {
					for(Paciente paciente:referencia) {
						if(paciente.getCondicion().equalsIgnoreCase("Regular")) {
							general++;
						}
					}
					general=(int)(((double)general/referencia.size())*100);
				}
			}break;
			
			
			
			//Buscar los pacientes con condicion intensiva
			case 5:{
				if(referencia.size()!=0) {
					for(Paciente paciente:referencia) {
						if(paciente.getCondicion().equalsIgnoreCase("Intensivos")) {
							general++;
						}
					}
					general=(int)(((double)general/referencia.size())*100);
				}
			}break;
			
			
		}
		
		
		return general;
	}
	
	//Establecer la condicion del bucle while
	public void setCondition(boolean type) {
		this.condition=type;
	}
	
	
}
