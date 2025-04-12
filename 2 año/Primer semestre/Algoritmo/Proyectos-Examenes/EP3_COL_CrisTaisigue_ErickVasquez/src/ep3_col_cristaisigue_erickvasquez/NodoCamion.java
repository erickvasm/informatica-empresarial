package ep3_col_cristaisigue_erickvasquez;

//Nodo en la ColaCamion
public class NodoCamion {

	NodoCamion siguiente;//referencia al siguiente
	Camion dato;//tipo de dato
	
	//Nodo Camion
	public NodoCamion(Camion dato) {
		siguiente=null;
		this.dato=dato;
	}
	
	
}
