package examen;

import javax.swing.JLabel;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

//Hilo hora proporciona la hora provista por el sistema
public class HiloHora extends Thread{

	private boolean condition=true,systemChange=false;//Condicion del ciclo
	private int seg=0,min=0, hour=0;//Segundos, minutos, horas del sistema de respaldo
	private JLabel referencia;//El campo de referencia
	private final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");//Formato 24H
	
	//Constructor recibe el campo y el nombre
	public HiloHora(JLabel campo,String nombre) {
		super(nombre);
		this.referencia=campo;
	}

	//Metodo run() funcion del hilo
	//este hilo se refrescara para mostrar la hora provista
	//por el sistema o utlizando el sistema de respaldo
	public void run() {
		//Mientradas que la condicion
		while(condition) {
			try{
			
				if(systemChange) {
					//Sistema de respaldo
					currentThread().sleep(1000);//Intervalo de un segundo
					cycle();
					referencia.setText(formater(3)+":"+formater(2)+":"+formater(1));
				}else {
					//24h Provisto por el sistema
					currentThread().sleep(1000);//Intervalo de un segundo
					referencia.setText(FORMAT.format(new Date()));
				}
				
				
			}catch(Exception e) {
				systemChange=true;//Se cambia al sistema de respaldo
				referencia.setText("00:00:00");
			}
			
		}
		
	}
	
	
	//Ciclo en el sistema de respaldo
	public void cycle() {
		if((seg+1)==60) {
			
			seg=0;
			
			if((min+1)==60) {
				min=0;
				
				if((hour+1)==24) {
					hour=0;
				}else {
					hour++;
				}
				
			}else {
				min++;
			}
			
		}else {
			seg++;
		}
	}
	
	
	//Definir el formato en sistema de respaldos
	public String formater(int type) {
		String response="";
		
		switch(type) {
			case 1:{
				response=Integer.toString(seg);
				if(seg<=9) {
					response="0"+response;
				}
			}break;
			
			case 2:{
				response=Integer.toString(min);
				if(min<=9) {
					response="0"+response;
				}
			}break;
			
			case 3:{
				response=Integer.toString(hour);
				if(hour<=9) {
					response="0"+response;
				}
			}break;
		}
		
		return response;
	}
	
	
	//Establecer la condicion
	public void setCondition(boolean type) {
		this.condition=type;
	}
		
}
