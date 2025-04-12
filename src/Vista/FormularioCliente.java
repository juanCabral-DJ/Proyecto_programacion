package Vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controlador.ControladorCliente;
import Modelo.Cliente;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class FormularioCliente extends JFrame {
	private JTextArea txtrNombre;
	private JTextArea txtrDirección;
	private JTextArea txtrTelefono;
	private ControladorCliente control;
	private SistemaGUI sistema;
	public FormularioCliente(SistemaGUI sistema) throws SQLException {
		this.sistema = sistema;
		this.control = new ControladorCliente();
		setTitle("Agregar Clientes");
	    setSize(357, 437);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(192, 192, 192));
	    panel.setBounds(0, 0, 347, 405);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Ingrese el nombre del cliente:");
	    lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_1.setBounds(54, 33, 209, 25);
	    panel.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("Ingrese la dirección:");
	    lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_2.setBounds(54, 127, 153, 25);
	    panel.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("Ingrese el telefono:");
	    lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_3.setBounds(54, 221, 132, 25);
	    panel.add(lblNewLabel_3);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					Agregar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_1.setBackground(new Color(210, 210, 210));
	    panel_1.setBounds(54, 315, 94, 25);
	    panel.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Agregar");
	    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
	    lblNewLabel.setBounds(30, 6, 45, 13);
	    panel_1.add(lblNewLabel);
	    
	    txtrNombre = new JTextArea("Nombre");
	    txtrNombre.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtrNombre.getText().equals("Nombre")) {
	            	txtrNombre.setText("");
	            	txtrNombre.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtrNombre.getText().isEmpty()) {
	            	txtrNombre.setText("Nombre");
	            	txtrNombre.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtrNombre.setBackground(new Color(192, 192, 192));
	    txtrNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtrNombre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrNombre.setBounds(54, 68, 131, 15);
	    panel.add(txtrNombre);
	    
	    txtrDirección = new JTextArea("Dirección");
	    txtrDirección.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtrDirección.getText().equals("Dirección")) {
	            	txtrDirección.setText("");
	            	txtrDirección.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtrDirección.getText().isEmpty()) {
	            	txtrDirección.setText("Dirección");
	            	txtrDirección.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtrDirección.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrDirección.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtrDirección.setBackground(Color.LIGHT_GRAY);
	    txtrDirección.setBounds(54, 162, 132, 15);
	    panel.add(txtrDirección);
	    
	    txtrTelefono = new JTextArea("Telefono");
	    txtrTelefono.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtrTelefono.getText().equals("Telefono")) {
	            	txtrTelefono.setText("");
	            	txtrTelefono.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtrTelefono.getText().isEmpty()) {
	            	txtrTelefono.setText("Telefono");
	            	txtrTelefono.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtrTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrTelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtrTelefono.setBackground(Color.LIGHT_GRAY);
	    txtrTelefono.setBounds(54, 256, 98, 15);
	    panel.add(txtrTelefono);

	}
	
	void Agregar() throws SQLException {
		String nombre = txtrNombre.getText();
		String direccion = txtrDirección.getText();
		String contacto = txtrTelefono.getText();
		
		if(!nombre.isEmpty() || !direccion.isEmpty() || !contacto.isEmpty()) {
			Cliente cliente = new Cliente(0,nombre,direccion, contacto);
			control.Guardar(cliente);
			JOptionPane.showMessageDialog(null,"Cliente agregado con exito");
			dispose();
			sistema.actualizarCliente();
		}else {
			throw new SQLException("No es posible agregar este cliente alguno de los campos esta vacio");
		}
	}
}
