package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlador.ControladorCliente;
import Controlador.ControladorProducto;
import Modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioProducto extends  JFrame {
	private ControladorProducto control;
	private SistemaGUI sistema;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtColor;
	private JTextField txtTipo;
	private JTextField txtPrecioDeVenta;
	
	public FormularioProducto(SistemaGUI sistema) throws SQLException {
		this.sistema = sistema;
		this.control = new ControladorProducto();
		setTitle("Agregar Producto");
	    setSize(498, 343);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(192, 192, 192));
	    panel.setBounds(0, 0, 484, 310);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Ingrese el nombre del producto: ");
	    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel.setBounds(32, 61, 187, 13);
	    panel.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("Ingrese el precio de producción:");
	    lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_1.setBounds(255, 61, 197, 13);
	    panel.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("Ingrese el color de la pintura:");
	    lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_2.setBounds(32, 136, 187, 13);
	    panel.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("Ingrese su tipo:");
	    lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_3.setBounds(255, 136, 197, 13);
	    panel.add(lblNewLabel_3);
	    
	    JLabel lblNewLabel_4 = new JLabel("Ingrese el precio de venta:");
	    lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_4.setBounds(32, 209, 187, 13);
	    panel.add(lblNewLabel_4);
	    
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
	    panel_1.setBounds(255, 227, 79, 24);
	    panel.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel_5 = new JLabel("Agregar");
	    lblNewLabel_5.setBounds(19, 6, 45, 13);
	    panel_1.add(lblNewLabel_5);
	    
	    txtNombre = new JTextField();
	    txtNombre.setText("Nombre");
	    txtNombre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtNombre.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtNombre.getText().equals("Nombre")) {
	            	txtNombre.setText("");
	            	txtNombre.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtNombre.getText().isEmpty()) {
	            	txtNombre.setText("Nombre");
	            	txtNombre.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtNombre.setBackground(new Color(192, 192, 192));
	    txtNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtNombre.setBounds(32, 84, 152, 19);
	    panel.add(txtNombre);
	    txtNombre.setColumns(10);
	    
	    txtPrecio = new JTextField();
	    txtPrecio.setText("Precio");
	    txtPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtPrecio.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtPrecio.getText().equals("Precio")) {
	            	txtPrecio.setText("");
	            	txtPrecio.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtPrecio.getText().isEmpty()) {
	            	txtPrecio.setText("Precio");
	            	txtPrecio.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtPrecio.setColumns(10);
	    txtPrecio.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtPrecio.setBackground(Color.LIGHT_GRAY);
	    txtPrecio.setBounds(252, 84, 152, 19);
	    panel.add(txtPrecio);
	    
	    txtColor = new JTextField();
	    txtColor.setText("Color");
	    txtColor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtColor.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtColor.getText().equals("Color")) {
	            	txtColor.setText("");
	            	txtColor.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtColor.getText().isEmpty()) {
	            	txtColor.setText("Color");
	            	txtColor.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtColor.setColumns(10);
	    txtColor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtColor.setBackground(Color.LIGHT_GRAY);
	    txtColor.setBounds(32, 158, 152, 19);
	    panel.add(txtColor);
	    
	    txtTipo = new JTextField();
	    txtTipo.setText("Tipo");
	    txtTipo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtTipo.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtTipo.getText().equals("Tipo")) {
	            	txtTipo.setText("");
	            	txtTipo.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtTipo.getText().isEmpty()) {
	            	txtTipo.setText("Tipo");
	            	txtTipo.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtTipo.setColumns(10);
	    txtTipo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtTipo.setBackground(Color.LIGHT_GRAY);
	    txtTipo.setBounds(255, 157, 152, 19);
	    panel.add(txtTipo);
	    
	    txtPrecioDeVenta = new JTextField();
	    txtPrecioDeVenta.setText("Precio de venta");
	    txtPrecioDeVenta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtPrecioDeVenta.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtPrecioDeVenta.getText().equals("Precio de venta")) {
	            	txtPrecioDeVenta.setText("");
	            	txtPrecioDeVenta.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtPrecioDeVenta.getText().isEmpty()) {
	            	txtPrecioDeVenta.setText("Precio de venta");
	            	txtPrecioDeVenta.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtPrecioDeVenta.setColumns(10);
	    txtPrecioDeVenta.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtPrecioDeVenta.setBackground(Color.LIGHT_GRAY);
	    txtPrecioDeVenta.setBounds(32, 232, 152, 19);
	    panel.add(txtPrecioDeVenta);
	    
}
	
	public void Agregar() throws SQLException {
		String nombre = txtNombre.getText();
		double precio = Double.parseDouble(txtPrecio.getText());
		String color = txtColor.getText();
		String tipo = txtTipo.getText();
		int precio_venta = Integer.parseInt(txtPrecioDeVenta.getText());
		
		if(!nombre.isEmpty() || precio > 0 || !color.isEmpty() || !tipo.isEmpty() || precio_venta > 0) {
			Producto producto = new Producto(0, nombre, precio, color, tipo, precio_venta);
			control.Guardar(producto);
			JOptionPane.showMessageDialog(null,"producto agregado con exito");
			dispose();
			sistema.actualizarProducto();
		}
	}
}