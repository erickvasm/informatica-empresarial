package controlador;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.Funcionario;

//Clase para realizar consultas de funcionarios o prestamos
public class Consulta extends JPanel{

	//Componentes
	private JTextField buscado;
	private JLabel not;
	private JTextArea areaTexto;
	private JButton btnConsultar;
	private JLabel lblTipo;
	
	//Constructor
	public Consulta(){
	
		//Componentes y caracteristicas
		this.setLayout(null);
		
		not = new JLabel("Consultar Funcionarios");
		not.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		not.setBounds(44, 11, 203, 32);
		this.add(not);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 23, 441, 169);
		this.add(scrollPane);
		
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		scrollPane.setViewportView(areaTexto);
		
		JLabel ins = new JLabel("<html>\r\n<body>\r\nInstrucciones: si se realiza una consulta de  funcionario por favor digite el carnet del funcionario a consultar, si en cambio se desea consultar un prestamo debe digitar la identificacion del cliente.\r\n</body>\r\n</html>");
		ins.setBounds(54, 60, 177, 117);
		this.add(ins);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(632, 203, 89, 32);
		this.add(btnConsultar);
		
		buscado = new JTextField();
		buscado.setBounds(452, 203, 170, 26);
		this.add(buscado);
		buscado.setColumns(10);
		
		lblTipo = new JLabel("Carnet del Funcionario:");
		lblTipo.setBounds(280, 203, 162, 14);
		this.add(lblTipo);
		
	}
	
	//El Método consultar recibe los ArrayList prestamos y funcionarios, dicho busca la informacion solicitada y la muestra
	public void consultar(boolean type,ArrayList<Cliente> pres, ArrayList<Funcionario> func) {
		//Si el la identificacion o carnet buscado es igual o mayor a 3
		if(buscado.getText().length()>=3) {
			//Se determina el tipo de gestion (Prestamos/Funcionarios)
			if(type) {
				//Funcionarios
				//Se determina si existen funcionarios
				if(func.size()<=0) {
					//No existen
					areaTexto.setText("No se han ingresado funcionarios");
				}else {
					//existen
					Funcionario tempFunc=null;
					//Se recorre todo el ArrayList para buscar al funcionario solicitado
					for(Funcionario fun:func) {
						//Si el carnet de funcionario es igual al buscado
						if(fun.getCarnetFuncionario().equalsIgnoreCase(buscado.getText())) {
							tempFunc=fun;
						}
					}
					//Si no se encontro
					if(tempFunc==null) {
						areaTexto.setText("No se encontro al funcionario");
					}else {
						//Se encuentra
						areaTexto.setText("Se encontro:\n"+tempFunc.desplegarInformacion());
					}
				}
				
			}else {
				//Prestamos
				//Se determina si hay prestamos
				if(pres.size()<=0) {
					//No hay
					areaTexto.setText("No se han ingresado prestamos");
				}else {
					//Si exiten
					Cliente tempCl=null;
					//Se busca
					for(Cliente pre:pres) {
						if(pre.getIdentificacion().equalsIgnoreCase(buscado.getText())) {
							tempCl=pre;
						}
					}
					//No se encuentra
					if(tempCl==null) {
						areaTexto.setText("No se encontro el cliente realizador del prestamo");
					}else {
						//Se encontro, ahora se busca el funcionario que lo atendio
						Funcionario temp=null;
						for(Funcionario fun:func) {
							if(fun.getCarnetFuncionario().equalsIgnoreCase(tempCl.getFuncAtendedor())) {
								temp=fun;
							}
						}
						//No se encuentra el funcionario atendedor
						if(temp==null) {
							areaTexto.setText(tempCl.desplegarInformacion()+"\nNo se encontro el funcionario atendedor, porfavor actualize los datos.");
						}else {
							//Se encuentra
							areaTexto.setText(tempCl.desplegarInformacion()+"\nFuncionario Atendedor:\n"+temp.desplegarInformacion());
						}
					}
				}
				
			}
		}else {
			areaTexto.setText("Ingrese al menos 3 caracteres");
		}
	}
	
	//Posibilita cambiar el modo de gestion
	public void optionChanger(boolean type) {
		reset();
		if(type) {
			lblTipo.setText("Carnet del Funcionario:");
			not.setText("Consultar Funcionario");
		}else {
			not.setText("Consultar Prestamos");
			lblTipo.setText("Identificacion del cliente:");
		}
	}
	
	//Resetea los componentes
	public void reset() {
		areaTexto.setText("");
		buscado.setText("");
	}
	
	//Obtiene el boton de consultar
	public JButton obtenerBoton() {
		return btnConsultar;
	}
	
}
