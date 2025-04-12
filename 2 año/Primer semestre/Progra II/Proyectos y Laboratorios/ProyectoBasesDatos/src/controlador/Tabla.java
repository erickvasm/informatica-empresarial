package controlador;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Paciente;
import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import conexionBD.Conexion;

//Clase Tabla se emplea en los distintos paneles para hacer posible la gestión de pacientes
public class Tabla extends JTable{
	
	//Modelo de la tabla
	private DefaultTableModel modeloTabla;
	
	//Constructor
	public Tabla(){
		
	
		//Características del modelo de tabla empleado
		modeloTabla=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#","Nombre", "Apellido","Genero","ID","Dirección","Teléfono","Días H","Condición"
			}
		);
		

		this.setDefaultEditor(Object.class,null);
		this.setModel(modeloTabla);

	}

	
	//Método actualizar tabla con datos del ArrayList pacientes
	public void actualizarTabla(Conexion conReferencia) {
		//Se resetea la tabla
		modeloTabla.setRowCount(0);
		Statement conexion=conReferencia.TryConnection();
		if(conexion==null) {
			
		}else {
			ArrayList<Paciente> pacent=null;
			String query="SELECT * FROM pacientes";
			pacent=conReferencia.ResultQueryFormer(conexion, query);
			if(pacent!=null) {
				if(pacent.size()!=0) {
					for(int cont=0;cont<pacent.size();cont++) {
						modeloTabla.addRow(new String[] {(Integer.toString(cont+1)),pacent.get(cont).getNombre(),
						pacent.get(cont).getApellido(),Character.toString(pacent.get(cont).getGenero()),
						pacent.get(cont).getIdentificacion(),pacent.get(cont).getDireccion(),
						pacent.get(cont).getTelefono(),Integer.toString(pacent.get(cont).getDiasH()),pacent.get(cont).getCondicion()
						});
					}
				}
			}else {
				
			}
		}
	}

	//Retornar el modelo de tabla
	public DefaultTableModel obtenerModelo() {
		return modeloTabla;
	}
}
