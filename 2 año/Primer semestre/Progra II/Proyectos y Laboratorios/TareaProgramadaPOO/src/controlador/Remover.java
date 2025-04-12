package controlador;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Cliente;
import modelo.Funcionario;

//Clase para Remover objetos del ArrayList de prestamos o funcionarios
public class Remover extends JPanel{
	
	
	//Componentes
	private int providedIndex=-1;//Indice provisto a remover
	private JLabel lblIdentificacion;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblSub;
	private JLabel not;
	private JButton btnEliminar;
	private JLabel Aviso;
	
	//Constructor
	public Remover() {
		
		//Caracteristicas y componentes
		this.setLayout(null);
		
		not = new JLabel("Remover");
		not.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		not.setBounds(38, 27, 218, 24);
		this.add(not);
		
		JLabel ins = new JLabel("<html><body>Instrucciones: Para eliminar un registro de prestamo o funcionario, debera seleccionarlo en la tabla superior, el sistema habilitara el boton de Eliminar, si decide eliminarlo presione dicho boton.</body></html>");
		ins.setBounds(428, 26, 352, 60);
		this.add(ins);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(667, 211, 113, 41);
		this.add(btnEliminar);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(48, 72, 135, 14);
		this.add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(48, 106, 135, 14);
		this.add(lblApellido);
		
		lblIdentificacion = new JLabel("Identificacion:");
		lblIdentificacion.setBounds(48, 147, 135, 14);
		this.add(lblIdentificacion);
		
		lblSub = new JLabel("sub");
		lblSub.setBounds(49, 190, 250, 14);
		this.add(lblSub);
		
		Aviso = new JLabel("");
		Aviso.setBounds(282, 224, 277, 14);
		this.add(Aviso);
		
		//Se cambia al modo de gestion funcionario
		gestionChanger(true);
	}
	
	
	//Se obtiene el indice de la entidad en el ArrayList indicado, se despliega su informacion
	public void CatchIndex(int index,ArrayList<Cliente> pres,ArrayList<Funcionario> fun,boolean type) {
		providedIndex=index;//indice dado
		btnEliminar.setEnabled(true);
		if(type) {
			//Funcionario
			Funcionario temp=fun.get(index);
			lblNombre.setText("Nombre:"+temp.getNombre());
			lblApellido.setText("Apellido:"+temp.getApellido());
			lblIdentificacion.setText("Identificacion:"+temp.getIdentificacion());
			lblSub.setText("Carnet:"+temp.getCarnetFuncionario());
		}else {
			//Cliente
			Cliente temp=pres.get(index);
			lblNombre.setText("Nombre:"+temp.getNombre());
			lblApellido.setText("Apellido:"+temp.getApellido());
			lblIdentificacion.setText("Identificacion:"+temp.getIdentificacion());
			lblSub.setText("Recurso prestado:"+temp.getPrestamo().getTipo());
		}
	}
	
	
	//Se eliminara un registro del ArrayList de Prestamos o Funcionarios
	public boolean Eliminar(boolean type,ArrayList<Cliente> pres,ArrayList<Funcionario> fun) {
		boolean response=false;
		try {
			//Se determina el modo de gestion
			if(type) {
				//Funcionarios
				fun.remove(providedIndex);
				gestionChanger(true);
			}else {
				//Clientes
				pres.remove(providedIndex);
				gestionChanger(false);
			}
			Aviso.setText("Se elimino correctamente");
			response=true;
		}catch(Exception e) {
			response=false;
		}
		return response;
	}
	
	
	
	
	//Cambia las caracteristicas de este panel dependiendo del modo de gestion
	public void gestionChanger(boolean type) {
		reset();//Se resetea todos los componentes
		//Modo de gestion
		if(type) {
			//Funcionario
			not.setText("Remover Funcionario");
			lblSub.setText("Carnet:");
		}else {
			//Cliente
			not.setText("Remover Prestamo");
			lblSub.setText("Recurso prestado:");
		}
	}
	
	//Resetear todos los componentes de este panel
	public void reset() {
		providedIndex=-1;
		lblNombre.setText("Nombre:");
		lblApellido.setText("Apellido:");
		lblIdentificacion.setText("Identificacion:");
		lblSub.setText("");
		btnEliminar.setEnabled(false);
		Aviso.setText("");
	}
	
	//Obtener el boton de eliminar
	public JButton obtenerBoton() {
		return btnEliminar;
	}
	
	//Obtener el aviso
	public JLabel obtenerAviso() {
		return Aviso;
	}
}
