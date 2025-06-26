package lesc_cristaisigueerickvasquez_2020;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

//MotionPanel, esta clase permitirá que la barra de título personalizada tenga la capacida de arrastrar toda la ventana
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

            // Localización de la venta
            int thisX = parent.getLocation().x;
            int thisY = parent.getLocation().y;

            // Determinar posición del mouse
            int xMoved = e.getX() - initialClick.x;
            int yMoved = e.getY() - initialClick.y;

            // Mover la ventana a esa posición
            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            parent.setLocation(X, Y);
        }
    });
    }
}