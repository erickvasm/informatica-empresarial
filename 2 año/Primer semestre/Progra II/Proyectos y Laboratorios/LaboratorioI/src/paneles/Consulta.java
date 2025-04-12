package paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import laboratorio.Paciente;

//Panel de consulta
public class Consulta extends JPanel{

	
	private ArrayList<Paciente> pacent;
	
	//Componentes
	private JTextField toSearch;
	private JLabel diasInfo;
	private JLabel teleInfo;
	private JLabel condicionInfo;
	private JLabel generoInfo;
	private JLabel identificacionInfo;
	private JLabel apellidoInfo;
	private JLabel nombreInfo;
	private JLabel direccionInfo;
	private JLabel signalTwo;
	private JLabel canTotal;
	private JLabel canFem;
	private JLabel canMas;
	private JLabel Estable;
	private JLabel Regular;
	private JLabel intensive;
	private JLabel iconoBusqueda;
	private BufferedImage iconMag;
	
	//Constructor
	public Consulta(ArrayList<Paciente> pacent) {
		//Obtención del ArrayList pacientes
		this.pacent=pacent;
		
		//Captar eventos del panel
		this.addComponentListener(new ComponentAdapter() {
			
			//Resetear los componentes de este panel cuando se oculte
			@Override
			public void componentHidden(ComponentEvent e) {
				reset();
			}
			
			//Actualizar estadísticas cuando el panel sea visible
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					//Actualizar datos generales
					autoFill(true);
				}catch(Exception a) {
					
					try {
						//Actualizar datos generales
						autoFill(false);
					}catch(Exception f) {
						//Error en la recolección de datos generales de la lista
					}
					
				}
			}
		});
		
		//Valores y características de los componentes
		this.setBorder(new TitledBorder(null, "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(326, 11, 15, 566);
		this.add(separator);
		
		canTotal = new JLabel("Cantidad Pacientes Total:");
		canTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		canTotal.setBounds(5, 81, 293, 19);
		this.add(canTotal);
		
		canFem = new JLabel("Pacientes Femeninos:");
		canFem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		canFem.setBounds(5, 127, 293, 19);
		this.add(canFem);
		
		canMas = new JLabel("Pacientes Masculinos:");
		canMas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		canMas.setBounds(5, 175, 293, 19);
		this.add(canMas);
		
		Estable = new JLabel("Condición Leve:");
		Estable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Estable.setBounds(5, 217, 293, 19);
		this.add(Estable);
		
		Regular = new JLabel("Condición Regular:");
		Regular.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Regular.setBounds(5, 264, 293, 19);
		this.add(Regular);
		
		intensive = new JLabel("Cuidados Intensivos:");
		intensive.setFont(new Font("Tahoma", Font.PLAIN, 15));
		intensive.setBounds(5, 317, 293, 19);
		this.add(intensive);
		
		JLabel lblinstruccionesenEsteSector = new JLabel(">En este sector se muestran datos totales");
		lblinstruccionesenEsteSector.setBounds(8, 46, 308, 14);
		this.add(lblinstruccionesenEsteSector);
		
		toSearch = new JTextField();
		toSearch.setBounds(539, 82, 257, 33);
		this.add(toSearch);
		toSearch.setColumns(10);
		
		//Ejecuta el método para buscar un paciente por su identificación
		JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search(toSearch.getText());
			}
		});
		searchButton.setBounds(817, 81, 107, 34);
		this.add(searchButton);
		
		JLabel signaler = new JLabel("Identificación Paciente");
		signaler.setFont(new Font("Tahoma", Font.PLAIN, 14));
		signaler.setBounds(363, 81, 155, 34);
		this.add(signaler);
		
		JLabel ayuda = new JLabel(">Consulta: proporcione la identificación del paciente y dé al botón \"Buscar\"");
		ayuda.setBounds(431, 24, 493, 33);
		this.add(ayuda);
		
		signalTwo = new JLabel("Detalles del paciente:");
		signalTwo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		signalTwo.setBounds(386, 159, 430, 14);
		this.add(signalTwo);
		
		nombreInfo = new JLabel("Nombre:");
		nombreInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nombreInfo.setBounds(405, 204, 533, 33);
		this.add(nombreInfo);
		
		apellidoInfo = new JLabel("Apellido:");
		apellidoInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		apellidoInfo.setBounds(405, 250, 533, 33);
		this.add(apellidoInfo);
		
		identificacionInfo = new JLabel("Identificación:");
		identificacionInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		identificacionInfo.setBounds(405, 303, 533, 33);
		this.add(identificacionInfo);
		
		generoInfo = new JLabel("Genero:");
		generoInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		generoInfo.setBounds(405, 347, 533, 33);
		this.add(generoInfo);
		
		condicionInfo = new JLabel("Condición:");
		condicionInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		condicionInfo.setBounds(405, 405, 533, 33);
		this.add(condicionInfo);
		
		teleInfo = new JLabel("Teléfono:");
		teleInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		teleInfo.setBounds(405, 464, 533, 33);
		this.add(teleInfo);
		
		diasInfo = new JLabel("Días hospitalizados:");
		diasInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		diasInfo.setBounds(405, 518, 533, 33);
		this.add(diasInfo);
		
		
		direccionInfo = new JLabel("Dirección:");
		direccionInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		direccionInfo.setBounds(405, 560, 533, 33);
		this.add(direccionInfo);
		
		
			try {
				iconMag= ImageIO.read(new File("src/recursos/magnifier.png"));
				iconoBusqueda = new JLabel(new ImageIcon(iconMag));
			}catch(Exception e) {
				iconoBusqueda=new JLabel("");
			}
			
			iconoBusqueda.setBounds(61, 368, 183, 183);
			this.add(iconoBusqueda);
		
		
	}
	
	//Método para consultar el ArrayList usando la identificación del paciente para encontrarlo
	public void search(String toFound) {
		if(toFound.length()>0) {
			//Valor negativo
			int master=-1;
			
			//Si al recorrer todo el ArrayList se encuentra alguna coincidencia 
			//la variable "master" tomara el valor de esa posición
			for(int cont=0;cont<pacent.size();cont++) {
				if(pacent.get(cont)!=null) {
					if(pacent.get(cont).getIdentificacion().equalsIgnoreCase(toFound)) {
						master=cont;
					}
				}
			}
			
			//Aquí se evalúa si se encontró alguna coincidencia
			if(master>=0) {
				//Se encontró
				signalTwo.setText("Detalles del paciente: Se encontró:");
				
				toSearch.setText("");
				
				nombreInfo.setText("Nombre: "+pacent.get(master).getNombre());
				apellidoInfo.setText("Apellido: "+pacent.get(master).getApellido());
				generoInfo.setText("Genero: "+Character.toString(pacent.get(master).getGenero()));
				condicionInfo.setText("Condición: "+pacent.get(master).getCondicion());
				
				identificacionInfo.setText("Identifiacación: "+pacent.get(master).getIdentificacion());
				diasInfo.setText("Días hospitalizados: "+Integer.toString(pacent.get(master).getDiasH()));
				direccionInfo.setText("Dirección: "+pacent.get(master).getDireccion());
				teleInfo.setText("Teléfono: "+pacent.get(master).getTelefono());
			}else {
				//No se encontró
				reset();
				signalTwo.setText("Detalles del paciente: no se ha encontrado el paciente");
			}
			
		}else {
			signalTwo.setText("Detalles del paciente: inserte una identificación valida");
		}
	}
	
	
	//Resetea los campos del panel
	public void reset() {
		
		signalTwo.setText("Detalles del paciente:");
		toSearch.setText("");
		
		nombreInfo.setText("Nombre: ");
		apellidoInfo.setText("Apellido: ");
		generoInfo.setText("Genero: ");
		condicionInfo.setText("Condición: ");
		
		identificacionInfo.setText("Identifiacación: ");
		diasInfo.setText("Días hospitalizados: ");
		direccionInfo.setText("Dirección: ");
		teleInfo.setText("Teléfono: ");
		
	}
	
	/*Este método permite obtener los datos generales para sus posteriores empleo
	 * en la parte de datos totales
	 * */
	public void autoFill(boolean method) {
		//Totales
		int totalFem=0;
		int totalMas=0;
		int totalReg=0;
		int totalLev=0;
		int totalInte=0;
	
		//Obtener los totales
		if(pacent.size()>0) {
			for(int cont=0;cont<pacent.size();cont++) {
				if(pacent.get(cont)!=null) {
					
					if(pacent.get(cont).getGenero()=='F') {
						totalFem+=1;
					}else{
						totalMas+=1;
					}
					
					if(pacent.get(cont).getCondicion().equalsIgnoreCase("Estable")) {
						totalLev+=1;
					}else if(pacent.get(cont).getCondicion().equalsIgnoreCase("Regular")) {
						totalReg+=1;
					}else {
						totalInte+=1;
					}
				}
			}
		}
		
		if(pacent.size()!=0) {
			
			//Mostrar datos generales Sistema de porcentaje
			if(method) {
				canTotal.setText("Cantidad Pacientes Total: "+pacent.size());
				canFem.setText("Pacientes Femeninos: "+(int)((totalFem>0)?((double)totalFem/pacent.size())*100:0)+"%");
				canMas.setText("Pacientes Masculinos: "+(int)((totalMas>0)?((double)totalMas/pacent.size())*100:0)+"%");
				Regular.setText("Condición Regular: "+(int)((totalReg>0)?((double)totalReg/pacent.size())*100:0)+"%");
				Estable.setText("Condición Estable: "+(int)((totalLev>0)?((double)totalLev/pacent.size())*100:0)+"%");
				intensive.setText("Cuidados Intensivos: "+(int)((totalInte>0)?((double)totalInte/pacent.size())*100:0)+"%");
			}else {
			//Mostrar datos generales Cantidades netas
				canTotal.setText("Cantidad Pacientes Total: "+pacent.size());
				canFem.setText("Pacientes Femeninos: "+totalFem);
				canMas.setText("Pacientes Masculinos: "+totalMas);
				Regular.setText("Condición Regular: "+totalReg);
				Estable.setText("Condición Estable: "+totalLev);
				intensive.setText("Cuidados Intensivos: "+totalInte);
			}
		}else {
			
			//No se ha incertadó ningún paciente =0
			canTotal.setText("Cantidad Pacientes Total: "+pacent.size());
			canFem.setText("Pacientes Femeninos: "+totalFem);
			canMas.setText("Pacientes Masculinos: "+totalMas);
			Regular.setText("Condición Regular: "+totalReg);
			Estable.setText("Condición Estable: "+totalLev);
			intensive.setText("Cuidados Intensivos: "+totalInte);
		}
		
	}
	
	
}
