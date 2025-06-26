package controlador;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
//MotionPanel, esta clase permitir� que la barra de t�tulo personalizada se pueda arrastrar
public class MotionPanel extends JPanel{
	
    private Point initialClick;
    private JFrame parent;
    
    //Constructor
    public MotionPanel(final JFrame parent){
	    this.parent = parent;
	
	    //Captar el evento de mouse apretado
	    addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });
	
	    
	    //Captar el evento de "arrastrar" 
	    addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {	        	
	        	
	            // Localizaci�n de la venta
	            int thisX = parent.getLocation().x;
	            int thisY = parent.getLocation().y;
	
	            // Determinar posici�n del mouse
	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;
	
	            // Mover la ventana a esa posici�n
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            
	            try {
	            	parent.setLocation(X, Y);
	            }catch(Exception w) {
	            	
	            }
	        }
	    });
    }
    
}