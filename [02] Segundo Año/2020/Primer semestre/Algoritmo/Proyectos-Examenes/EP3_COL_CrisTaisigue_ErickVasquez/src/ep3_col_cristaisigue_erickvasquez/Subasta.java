package ep3_col_cristaisigue_erickvasquez;

import java.util.ArrayList;

//Clase subasta donde se lleva acabo la simulacion
public class Subasta {

	//Atributos
	private int numeroSubastado=0;
	private double montoTotal=0;
	private double ganancia=0;
	private boolean terminado=false;
	private int camionesDescargados=0;
	
	private ArrayList<Ganaderia> ganaderias=new <Ganaderia>ArrayList();//Lista de ganaderias
							//Colas
	private ColaCabezaRes subastaCabezaRes=new ColaCabezaRes();//Ganado descargado para ser subastado
	private ColaCamion camionesCola=new ColaCamion();//Camiones para ser descargados
	
	//Constructor
	public Subasta() {
		
	}
	
	
	//Set & Get
	public int getNumeroSubastado() {
		return numeroSubastado;
	}


	public void setNumeroSubastado(int numeroSubastado) {
		this.numeroSubastado = numeroSubastado;
	}


	public double getMontoTotal() {
		return montoTotal;
	}


	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}


	public double getGanancia() {
		return ganancia;
	}


	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}



	public ColaCabezaRes getSubastaCabezaRes() {
		return subastaCabezaRes;
	}


	public void setSubastaCabezaRes(ColaCabezaRes subastaCabezaRes) {
		this.subastaCabezaRes = subastaCabezaRes;
	}


	public ArrayList<Ganaderia> getGanaderias() {
		return ganaderias;
	}


	public void setGanaderias(ArrayList<Ganaderia> ganaderias) {
		this.ganaderias = ganaderias;
	}


	public ColaCamion getCamionesCola() {
		return camionesCola;
	}


	public void setCamionesCola(ColaCamion camionesCola) {
		this.camionesCola = camionesCola;
	}


	public boolean isTerminado() {
		return terminado;
	}


	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}


	public int getCamionesDescargados() {
		return camionesDescargados;
	}


	public void setCamionesDescargados(int camionesDescargados) {
		this.camionesDescargados = camionesDescargados;
	}
	
	
}
