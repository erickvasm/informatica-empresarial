package ep3_col_cristaisigue_erickvasquez;

import java.util.ArrayList;
//Clase Camion almacena las Cabezas de res
public class Camion {

	//Atributos
	private String nombreGanaderia;
	private String placaCamion;
	private int capacidad;
	private ArrayList<CabezaRes> ganado=new <CabezaRes>ArrayList();//Las cabezas de res en el camion
	
	
	//Constructor
	public Camion() {
		
	}

	
	//Obtener la capacidad en uso
	public int obtenerUsoCapacidad() {
		int response=0;
		if(ganado.size()!=0) {
			for(int cont=0;cont<ganado.size();cont++) {
				if(ganado.get(cont)!=null){
					response+=ganado.get(cont).getPeso();
				}
			}
		}
		return response;
	}
	
	
	//Set & Gets
	public String getNombreGanaderia() {
		return nombreGanaderia;
	}

	public void setNombreGanaderia(String nombreGanaderia) {
		this.nombreGanaderia = nombreGanaderia;
	}

	public String getPlacaCamion() {
		return placaCamion;
	}

	public void setPlacaCamion(String placaCamion) {
		this.placaCamion = placaCamion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public ArrayList<CabezaRes> getGanado() {
		return ganado;
	}

	public void setGanado(ArrayList<CabezaRes> ganado) {
		this.ganado = ganado;
	}
	
}
