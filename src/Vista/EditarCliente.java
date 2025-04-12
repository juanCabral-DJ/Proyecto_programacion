package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controlador.ControladorCliente;
import Modelo.Cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditarCliente extends JFrame {
	private JTextArea txtrId;
	private JTextArea txtrDirección;
	private JTextArea txtrTelefono;
	private ControladorCliente control;
	private SistemaGUI sistema;
	
	public EditarCliente(SistemaGUI sistema) throws SQLException {
		this.sistema = sistema;
		this.control = new ControladorCliente();
		setTitle("Editar Clientes");
	    setSize(409, 255);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(192, 192, 192));
	    panel.setBounds(0, 0, 399, 222);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblNewLabel_2 = new JLabel("Ingrese la nueva dirección:");
	    lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_2.setBounds(37, 97, 153, 25);
	    panel.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("Ingrese el telefono nuevo:");
	    lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_3.setBounds(219, 39, 153, 25);
	    panel.add(lblNewLabel_3);
	    
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
	    txtrDirección.setBounds(38, 132, 132, 15);
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
	    txtrTelefono.setBounds(219, 74, 110, 15);
	    panel.add(txtrTelefono);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					Editar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_1.setBackground(new Color(210, 210, 210));
	    panel_1.setBounds(232, 122, 77, 25);
	    panel.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Editar");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
	    lblNewLabel.setBounds(26, 6, 45, 13);
	    panel_1.add(lblNewLabel);
	    
	    txtrId = new JTextArea("ID");
	    txtrId.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtrId.getText().equals("ID")) {
	            	txtrId.setText("");
	            	txtrId.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtrId.getText().isEmpty()) {
	            	txtrId.setText("ID");
	            	txtrId.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtrId.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtrId.setBackground(Color.LIGHT_GRAY);
	    txtrId.setBounds(37, 72, 45, 15);
	    panel.add(txtrId);
	    
	    JLabel lblNewLabel_2_1 = new JLabel("Ingrese el id del cliente:");
	    lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_2_1.setBounds(37, 39, 153, 25);
	    panel.add(lblNewLabel_2_1);
	    
	    setLocationRelativeTo(null);
	}
	
	public void Editar() throws SQLException {
		int id = Integer.parseInt(txtrId.getText());
		String new_dir = txtrDirección.getText();
		String new_tel = txtrTelefono.getText();
		
		if(id > 0 || !new_dir.isEmpty() || !new_tel.isEmpty()) {
			control.Actualizar(id, new_dir, new_tel);
			JOptionPane.showMessageDialog(null,"Cliente editado con exito");
			dispose();
			sistema.actualizarCliente();
		}else {
			throw new SQLException("No es posible editar este cliente, no se ha encontrado");
		}
	}
}