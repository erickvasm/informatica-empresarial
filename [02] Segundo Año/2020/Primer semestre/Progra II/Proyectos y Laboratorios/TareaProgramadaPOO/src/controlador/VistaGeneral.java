package controlador;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.Cliente;
import modelo.Funcionario;

//Clase de Vista General
public class VistaGeneral extends JPanel{
	
	//Componente
	private JTextArea areaTexto;
	
	private ArrayList<Funcionario> func;//Puntero del ArrayList central funcionarios
	private ArrayList<Cliente> pres;//Puntero del ArrayList central prestamos
	
	//Constructor
	public VistaGeneral(ArrayList<Cliente> pres,ArrayList<Funcionario> func) {
		
		//Se determinan los punteros
		this.func=func;
		this.pres=pres;
		
		
		
		//Componentes y caracteristicas
		this.setLayout(null);

		JScrollPane scrollText = new JScrollPane();
		scrollText.setBounds(294, 21, 473, 231);
		this.add(scrollText);
		
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		areaTexto.setLineWrap(true);
		scrollText.setViewportView(areaTexto);
		
		JLabel lblNewLabel = new JLabel("Vista General");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(57, 21, 151, 22);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html><body>Instrucciones: seleccione un fila de la tabla superior, el sistema detectara si se trata de informacion de funcionarios o de prestamos, posterior a esto mostrara dicha informacion.</body></html>");
		lblNewLabel_1.setBounds(23, 72, 199, 116);
		this.add(lblNewLabel_1);
		
	}
	
	//Se resetean todos los elementos
	public void reset() {
		areaTexto.setText("");
	}
	
	
	//Se obtiene el indice a visualizar con el modo de gestion
	public void CatchIndex(int index,boolean type) {
		areaTexto.setText("");
		//Se determina el modo de gestion
		if(type) {
			//Funcionarios
			areaTexto.setText(func.get(index).desplegarInformacion());
		}else{
			//Clientes
			//Se busca el funcionario atendedor
			Funcionario tempFunc=null;
			areaTexto.setText(pres.get(index).desplegarInformacion());
			for(Funcionario fun:func) {
				if(fun.getCarnetFuncionario().equalsIgnoreCase(pres.get(index).getFuncAtendedor())) {
					tempFunc=fun;
				}
			}
			//Se encuentra el funcionario atendedor
			if(tempFunc==null) {
				areaTexto.append("\nNo se encontro el funcionario atendedor, porfavor actualize los datos.");
			}else {
				areaTexto.append("\nFuncionario Atendedor:"+"\n"+tempFunc.desplegarInformacion());
			}
		}
	}
	
	
}
