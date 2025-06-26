package paneles;
import java.util.ArrayList;


import examen.Paciente;
import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

//Clase Tabla se emplea en los distintos paneles para hacer posible la gesti�n de pacientes
public class Tabla extends JTable{
	
	//Modelo de la tabla
	private DefaultTableModel modeloTabla;
	
	//Constructor
	public Tabla(){
		
	
		//Caracter�sticas del modelo de tabla empleado
		modeloTabla=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#","Nombre", "Apellido","Genero","ID","Direcci�n","Tel�fono","D�as H","Condici�n","Hora Registro"
			}
		);
		

		this.setDefaultEditor(Object.class,null);
		this.setModel(modeloTabla);

	}

	
	//M�todo actualizar tabla con datos del ArrayList pacientes
	public void actualizarTabla(ArrayList<Paciente> pacent) {
		//Se resetea la tabla
		modeloTabla.setRowCount(0);
		if(pacent.size()!=0) {
			for(int cont=0;cont<pacent.size();cont++) {
				modeloTabla.addRow(new Object[] {cont+1,pacent.get(cont).getNombre(),
				pacent.get(cont).getApellido(),pacent.get(cont).getGenero(),
				pacent.get(cont).getIdentificacion(),pacent.get(cont).getDireccion(),
				pacent.get(cont).getTelefono(),pacent.get(cont).getDiasH(),pacent.get(cont).getCondicion(),
				pacent.get(cont).getHoraRegistro()
				});
			}
		}
	}

	//Retornar el modelo de tabla
	public DefaultTableModel obtenerModelo() {
		return modeloTabla;
	}
}
