package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controlador.ControladorCliente;
import Controlador.ControladorProducto;

public class EditarProducto extends JFrame {
	private JTextArea txtrId;
	private JTextArea txt2;
	private JTextArea txt1;
	private ControladorProducto control;
	private SistemaGUI sistema;
	
	public EditarProducto(SistemaGUI sistema) throws SQLException {
		this.sistema = sistema;
		this.control = new ControladorProducto();
		setTitle("Editar Producto");
	    setSize(409, 255);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(192, 192, 192));
	    panel.setBounds(0, 0, 399, 222);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	   
	    JLabel lblNewLabel_2 = new JLabel("Ingrese el nuevo precio de venta:");
	    lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_2.setBounds(37, 97, 216, 25);
	    panel.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("Ingrese el nuevo precio de producción:");
	    lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_3.setBounds(180, 39, 209, 25);
	    panel.add(lblNewLabel_3);
	    
	    txt1 = new JTextArea("Precio de venta");
	    txt1.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txt1.getText().equals("Precio de venta")) {
	            	txt1.setText("");
	            	txt1.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txt1.getText().isEmpty()) {
	            	txt1.setText("Precio de venta");
	            	txt1.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txt1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txt1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txt1.setBackground(Color.LIGHT_GRAY);
	    txt1.setBounds(38, 132, 132, 15);
	    panel.add(txt1);
	    
	    txt2 = new JTextArea("Precio de producción");
	    txt2.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txt2.getText().equals("Precio de producción")) {
	            	txt2.setText("");
	            	txt2.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txt2.getText().isEmpty()) {
	            	txt2.setText("Precio de venta");
	            	txt2.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txt2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txt2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txt2.setBackground(Color.LIGHT_GRAY);
	    txt2.setBounds(180, 74, 110, 15);
	    panel.add(txt2);
	    
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
	    panel_1.setBounds(237, 122, 77, 25);
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
	    
	    JLabel lblNewLabel_2_1 = new JLabel("Ingrese el id del producto:");
	    lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_2_1.setBounds(37, 39, 153, 25);
	    panel.add(lblNewLabel_2_1);
	    
	    setLocationRelativeTo(null);
	}
	
	public void Editar() throws SQLException {
		int id = Integer.parseInt(txtrId.getText());
		double new_precio = Double.parseDouble(txt2.getText());
		int new_precioventa = Integer.parseInt(txt1.getText());
		
		if(id > 0 || new_precio > 0 || new_precioventa > 0) {
			control.Actualizar(id, new_precio, new_precioventa);
			JOptionPane.showMessageDialog(null,"Cliente editado con exito");
			dispose();
			sistema.actualizarProducto();
		}else {
			throw new SQLException("No es posible editar este cliente, no se ha encontrado");
		}
	}
}
	