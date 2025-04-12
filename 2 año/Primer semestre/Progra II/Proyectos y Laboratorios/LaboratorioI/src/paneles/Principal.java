package paneles;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

//Panel Principal
public class Principal extends JPanel{

	//Tabla donde se muestran los pacientes
	private Tabla tablaUno;
	
	//Constructor del Panel Principal
	public Principal() {
		
		
		//Características
		this.setLayout(null);
		
		
		//Componentes
		JScrollPane scrollPrincipal = new JScrollPane();
		scrollPrincipal.setBounds(10, 66, 700, 520);
		this.add(scrollPrincipal);
		
		//Tabla donde se desplegara la información de los pacientes
		tablaUno=new Tabla();
		tablaUno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPrincipal.setViewportView(tablaUno);
		
		JLabel instruccion = new JLabel(">Instrucciones: Aquí podrá ver detalles de los pacientes diagnosticados con COVID-	19");
		instruccion.setBounds(50, 11, 655, 31);
		this.add(instruccion);
		
		
	}
	
	//Retornar la Tabla de este Panel
	public Tabla obtenerTabla() {
		return tablaUno;
	}
}
