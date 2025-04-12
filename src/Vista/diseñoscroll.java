package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class diseñoscroll extends BasicScrollBarUI {
	    @Override
	    protected void configureScrollBarColors() {
	        this.thumbColor = new Color(250, 250, 250); // color del "thumb"
	        this.trackColor = new Color(200, 200, 200); // color de fondo
	    }
}
