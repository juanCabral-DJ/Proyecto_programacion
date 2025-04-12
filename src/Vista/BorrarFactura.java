package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Controlador.ControladorFactura;

public class BorrarFactura  extends JFrame {
	private JTextArea txtrid;
	private ControladorFactura control;
	private SistemaGUI sistema;
	
	public BorrarFactura(SistemaGUI sistema) throws SQLException {
		this.sistema = sistema;
		this.control = new ControladorFactura();
		setTitle("Borrar Clientes");
	    setSize(281, 174);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(192, 192, 192));
	    panel.setBounds(0, 0, 269, 140);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel("Ingrese el id del cliente a borrar:");
	    lblNewLabel.setBounds(63, 21, 229, 13);
	    panel.add(lblNewLabel);
	    
	    txtrid = new JTextArea("ID");
	    txtrid.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (txtrid.getText().equals("ID")) {
	            	txtrid.setText("");
	            	txtrid.setForeground(Color.BLACK); // texto normal
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (txtrid.getText().isEmpty()) {
	            	txtrid.setText("ID");
	            	txtrid.setForeground(Color.GRAY); // volver al estilo inicial
	            }
	        }
	    });
	    txtrid.setBackground(new Color(192, 192, 192));
	    txtrid.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
	    txtrid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrid.setBounds(63, 44, 131, 15);
	    panel.add(txtrid);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		
	    		try {
					Borrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_1.setBackground(new Color(216, 216, 216));
	    panel_1.setBounds(84, 83, 90, 25);
	    panel.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Borrar");
	    lblNewLabel_1.setBounds(31, 6, 39, 13);
	    lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
	    panel_1.add(lblNewLabel_1);
	}
	
	public void Borrar() throws SQLException{
		int id = Integer.parseInt(txtrid.getText());
		
		if(id > 0) {
			control.Eliminar(id);
			JOptionPane.showMessageDialog(null,"Factura borrada con exito");
			dispose();
			sistema.actualizarFactura();
		}else {
			throw new SQLException("No es posible borrar este cliente, no existe");
		}
	}
}