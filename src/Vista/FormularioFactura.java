package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorFactura;
import Modelo.Factura;
import Modelo.Factura.Detalle_Factura;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioFactura extends JFrame{
	private JTable tablaDetalles;
	private JTextArea txtrNombre;
	private JTextArea txtrNombre_1;
	private JTextArea txtrNombre_2;
	private JTextArea txtrNombre_2_1;
	private JTextArea txtrNombre_3;
	private JTextArea txtrNombre_4;
    private DefaultTableModel modeloTabla;
	private ControladorFactura control;
	private SistemaGUI sistema;
	
	public FormularioFactura(SistemaGUI sistema) throws SQLException {
		this.sistema = sistema;
		this.control = new ControladorFactura();
		setTitle("Facturar");
	    setSize(516, 370);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(192, 192, 192));
	    panel.setBounds(0, 3, 506, 107);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Fecha:");
	    lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_1.setBounds(20, 10, 51, 13);
	    panel.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("Vendedor:");
	    lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_1_1.setBounds(151, 10, 70, 13);
	    panel.add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("Pago: ");
	    lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_1_2.setBounds(332, 10, 45, 13);
	    panel.add(lblNewLabel_1_2);
	    
	    txtrNombre = new JTextArea("");
	    txtrNombre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrNombre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	    txtrNombre.setBackground(Color.LIGHT_GRAY);
	    txtrNombre.setBounds(63, 8, 60, 15);
	    panel.add(txtrNombre);
	    
	    txtrNombre_1 = new JTextArea("");
	    txtrNombre_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrNombre_1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	    txtrNombre_1.setBackground(Color.LIGHT_GRAY);
	    txtrNombre_1.setBounds(212, 8, 98, 15);
	    panel.add(txtrNombre_1);
	    
	    txtrNombre_2 = new JTextArea("");
	    txtrNombre_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrNombre_2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	    txtrNombre_2.setBackground(Color.LIGHT_GRAY);
	    txtrNombre_2.setBounds(369, 8, 45, 15);
	    panel.add(txtrNombre_2);
	    
	    JLabel lblNewLabel_1_1_1 = new JLabel("Id del cliente:");
	    lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_1_1_1.setBounds(20, 57, 84, 13);
	    panel.add(lblNewLabel_1_1_1);
	    
	    JLabel lblNewLabel_1_1_2 = new JLabel("Tipo:");
	    lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_1_1_2.setBounds(151, 57, 51, 13);
	    panel.add(lblNewLabel_1_1_2);
	    
	    JLabel lblNewLabel_1_1_3 = new JLabel("Estado:");
	    lblNewLabel_1_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    lblNewLabel_1_1_3.setBounds(332, 57, 60, 13);
	    panel.add(lblNewLabel_1_1_3);
	    
	    txtrNombre_2_1 = new JTextArea("");
	    txtrNombre_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrNombre_2_1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	    txtrNombre_2_1.setBackground(Color.LIGHT_GRAY);
	    txtrNombre_2_1.setBounds(89, 55, 24, 15);
	    panel.add(txtrNombre_2_1);
	    
	    txtrNombre_3 = new JTextArea("");
	    txtrNombre_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrNombre_3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	    txtrNombre_3.setBackground(Color.LIGHT_GRAY);
	    txtrNombre_3.setBounds(193, 55, 112, 15);
	    panel.add(txtrNombre_3);
	    
	    txtrNombre_4 = new JTextArea("");
	    txtrNombre_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	    txtrNombre_4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	    txtrNombre_4.setBackground(Color.LIGHT_GRAY);
	    txtrNombre_4.setBounds(383, 55, 70, 15);
	    panel.add(txtrNombre_4);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBackground(new Color(192, 192, 192));
	    panel_1.setBounds(0, 108, 506, 147);
	    getContentPane().add(panel_1);
	    
	    String[] columnas = {"ID Producto", "Cantidad", "ITBIS", "Precio Venta"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        panel_1.setLayout(null);
        tablaDetalles = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaDetalles);
        scroll.setBounds(10,10,321,115);
        panel_1.add(scroll);
        
        JPanel panel_2 = new JPanel();
        panel_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		 modeloTabla.addRow(new Object[]{"", "", "", ""});
        	}
        });
        panel_2.setBackground(new Color(210, 210, 210));
        panel_2.setBounds(367, 27, 94, 26);
        panel_1.add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Agregar");
        lblNewLabel.setBounds(29, 5, 45, 15);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        panel_2.add(lblNewLabel);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int filaSeleccionada = tablaDetalles.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
                }
            
        	}
        });
        panel_2_1.setLayout(null);
        panel_2_1.setBackground(new Color(210, 210, 210));
        panel_2_1.setBounds(367, 79, 94, 26);
        panel_1.add(panel_2_1);
        
        JLabel lblEliminar = new JLabel("Eliminar");
        lblEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lblEliminar.setBounds(29, 5, 45, 15);
        panel_2_1.add(lblEliminar);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(192, 192, 192));
        panel_3.setBounds(0, 253, 506, 85);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);
        
        JPanel panel_4 = new JPanel();
        panel_4.addMouseListener(new MouseAdapter() {
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
        panel_4.setBackground(new Color(210, 210, 210));
        panel_4.setBounds(192, 29, 136, 32);
        panel_3.add(panel_4);
        panel_4.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("Agregar factura");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(23, 10, 113, 13);
        panel_4.add(lblNewLabel_2);
	}
	
	private void Agregar() throws SQLException {
	    if (txtrNombre_2.getText().trim().isEmpty() || txtrNombre_2_1.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Por favor completa todos los campos numéricos antes de guardar.");
	        return;
	    }

	    Factura fact = new Factura();
	    fact.setidfactura(0);
	    fact.setfecha(txtrNombre.getText());
	    fact.setvendedor(txtrNombre_1.getText());
	    fact.setpago(Integer.parseInt(txtrNombre_2.getText()));
	    fact.setfkcliente(Integer.parseInt(txtrNombre_2_1.getText()));
	    fact.setestado(txtrNombre_4.getText());
	    fact.settipo(txtrNombre_3.getText());

	    ArrayList<Detalle_Factura> detalles = new ArrayList<>();
	    
	    if (tablaDetalles.isEditing()) {
	    	tablaDetalles.getCellEditor().stopCellEditing();
	    }


	    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	        try {
	            String idProdStr = modeloTabla.getValueAt(i, 0).toString().trim();
	            String cantidadStr = modeloTabla.getValueAt(i, 1).toString().trim();
	            String itbisStr = modeloTabla.getValueAt(i, 2).toString().trim();
	            String precioStr = modeloTabla.getValueAt(i, 3).toString().trim();

	            if (idProdStr.isEmpty() || cantidadStr.isEmpty() || itbisStr.isEmpty() || precioStr.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Fila " + (i + 1) + " contiene campos vacíos.");
	                return; // Sale del método completamente
	            }

	            Detalle_Factura detalle = fact.new Detalle_Factura();
	            detalle.setfkproducto(Integer.parseInt(idProdStr));
	            detalle.setcantidad(Integer.parseInt(cantidadStr));
	            detalle.setITBIS(Double.parseDouble(itbisStr));
	            detalle.setprecio_venta(Integer.parseInt(precioStr));

	            detalles.add(detalle);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Error de formato en la fila " + (i + 1) + ": " + e.getMessage());
	            return;
	        }
	    }

	    fact.setDetalles(detalles); // Asignar todos los detalles

	    // Ahora sí, guardar la factura completa con todos los detalles
	    if (control.Guardar(fact)) {
	    	sistema.actualizarFactura();
	        JOptionPane.showMessageDialog(null, "Factura guardada correctamente.");
	        dispose(); 
	    } else {
	        JOptionPane.showMessageDialog(null, "Error al guardar la factura.");
	    }

	}
}
