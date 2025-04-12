package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class VentanaInicio extends JFrame {
	private SistemaGUI prueba;
    public VentanaInicio() throws SQLException {
    	this.prueba = new SistemaGUI();
        setTitle("Bienvenido");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        PanelConFondo fondo = new PanelConFondo("C:\\Users\\juand\\Downloads\\inicio.jpg"); 
        fondo.setLocation(0, 0);
        fondo.setSize(1000, 600);
        getContentPane().add(fondo);
        fondo.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("        Bienvenido al sistema de facturación");
        lblNewLabel.setBounds(119, 122, 711, 47);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
        fondo.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		 try {
					prueba.setVisible(true);
					prueba.actualizarCliente();
					prueba.actualizarProducto();
					prueba.actualizarFactura();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		 dispose();
        	}
        });
        panel.setBackground(new Color(196, 196, 196));
        panel.setBounds(372, 234, 209, 62);
        fondo.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Empezar a facturar");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1.setBounds(20, 10, 189, 42);
        panel.add(lblNewLabel_1);
        
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
				new VentanaInicio().setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
}