package controlador;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.Persona;

//Clase de vista general de empleados
public class General extends JPanel{
	
	//Componentes
	JTextArea areaTexto;
	Color one=new Color(213,213,213);
	
	
	//Constructor
	public General() {
		
		//Componentes y caracteristicas
		this.setLayout(null);
		this.setBounds(10, 11, 570, 334);
		
		JLabel labeler = new JLabel("GENERAL");
		labeler.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		labeler.setBounds(10, 11, 124, 32);
		this.add(labeler);
	
		areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		areaTexto.setLineWrap(true);
		
		JScrollPane scrollinfo = new JScrollPane(areaTexto);
		scrollinfo.setBounds(83, 54, 388, 258);
		this.add(scrollinfo);
		
		JLabel help = new JLabel("<html><body>Instruccciones: seleccione un empleado de la tabla de arriba, para mostrar su información a detalle.</body></html>");
		help.setBounds(158, 11, 388, 27);
		this.add(help);
		
		
	}
	
	
	//Resetear los campos
	public void reset() {
		areaTexto.setText("");
	}
	
	
	//Mostrar de forma general un empleado por medio de su indice
	public void showEmployee(LinkedList<Persona> emp,int index) {
		
		ListIterator<Persona> iterator=emp.listIterator();
		iterator=emp.listIterator();
		
		int currentLinck=0;
		Persona current;
		boolean condition=true;
		
		while((iterator.hasNext()) && (condition)) {
			current=iterator.next();
			if(currentLinck==index) {
				areaTexto.setText(current.mostrarInformacion());
				condition=false;
			}
			currentLinck++;
		}
	}
	
	//Obtener color
	public Color getColor() {
		return one;
	}
}
