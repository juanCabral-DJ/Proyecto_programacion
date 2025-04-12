package Vista;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelCliente extends JPanel {
	 private Image imagenFondo;

	    public PanelCliente(String rutaImagen) {
	        try {
	            this.imagenFondo = new ImageIcon(rutaImagen).getImage();
	        } catch (Exception e) {
	            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
	        }
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Dibuja la imagen en todo el panel
	        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
	    }

}
