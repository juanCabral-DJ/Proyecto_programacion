package Vista;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import Controlador.ControladorCliente;
import Controlador.ControladorFactura;
import Controlador.ControladorProducto;
import Modelo.Cliente;
import Modelo.Exportar_Excel;
import Modelo.Factura;
import Modelo.Producto;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class SistemaGUI extends JFrame{
	private DefaultTableModel t1;
	private DefaultTableModel t2;
	private DefaultTableModel t3;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JTable table_4;
	private ControladorCliente control;
	private ControladorProducto control2;
	private ControladorFactura control3;
    private Cliente cliente;
    private Producto producto;
    private Factura factura;
    
	public SistemaGUI() throws SQLException {
		this.factura = new Factura();
		this.cliente = new Cliente();
		this.producto = new Producto();
		this.control = new ControladorCliente();
		this.control2 = new ControladorProducto();
		this.control3 = new ControladorFactura();
		setTitle("Sistema de facturación");
	    setSize(1000, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	    tabbedPane.setBounds(0, 0, 990, 540);
	    getContentPane().add(tabbedPane);
	    
	    //Ventana para las facturas
	    
	    t1 = new DefaultTableModel(new Object[]{"id", "fecha","pago", "estado"}, 0){
       	 @Override
       	    public boolean isCellEditable(int row, int column) {
       	        return false; 
       	    }
       	
       };
       table_3 = new JTable(t1);
       table_3.getTableHeader().setReorderingAllowed(false);
       table_3.setShowHorizontalLines(true);
       table_3.setShowVerticalLines(true);
       table_3.setGridColor(Color.BLACK);
       JScrollPane scroll = new JScrollPane(table_3);
       scroll.getVerticalScrollBar().setUI(new diseñoscroll());
       scroll.setBounds(0, 0, 678, 456);
	    
       PanelFactura fondo = new PanelFactura("C:\\Users\\juand\\Downloads\\fact3.jpg"); 
       fondo.setLocation(0, 0);
       fondo.setSize(1000, 600);
       fondo.setLayout(null);
	    tabbedPane.addTab("Facturas", null, fondo, null);
		fondo.setLayout(null);
	    
	    JPanel panel_3 = new JPanel();
	    panel_3.setBounds(21, 47, 678, 456);
	    fondo.add(panel_3);
	    panel_3.setLayout(null);

	    table_3.setBounds(10, 10, 631, 411);
	    panel_3.add(scroll);
	    
	    JPanel panel_4 = new JPanel();
	    panel_4.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					BorrarFactura();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_4.setBackground(new Color(216, 216, 216));
	    panel_4.setBounds(763, 277, 138, 45);
	    fondo.add(panel_4);
	    panel_4.setLayout(null);
	    
	    JLabel lblNewLabel_3 = new JLabel("Eliminar\r\n");
	    lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_3.setBounds(46, 10, 82, 25);
	    panel_4.add(lblNewLabel_3);
	    
	    JPanel panel_5 = new JPanel();
	    panel_5.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					AgregarFactura();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_5.setBackground(new Color(216, 216, 216));
	    panel_5.setBounds(763, 133, 138, 45);
	    fondo.add(panel_5);
	    panel_5.setLayout(null);
	    
	    JLabel lblNewLabel_4 = new JLabel("Agregar\r\n");
	    lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_4.setBounds(46, 10, 100, 25);
	    panel_5.add(lblNewLabel_4);
	    
	    JLabel lblNewLabel = new JLabel("Módulo de Facturación");
	    lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
	    lblNewLabel.setBounds(21, 10, 649, 27);
	    fondo.add(lblNewLabel);
	    
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	   //Ventana para los clientes
	    t2 = new DefaultTableModel(new Object[]{"id", "nombre","direccion", "contacto"}, 0){
	       	 @Override
	       	    public boolean isCellEditable(int row, int column) {
	       	        return false; 
	       	    }
	       	
	       };
	    
	    PanelCliente fondo2 = new PanelCliente("C:\\Users\\juand\\Downloads\\fact4.jpg");
	    tabbedPane.addTab("Clientes", null, fondo2, null);
	    fondo2.setLayout(null);
	    
	    table_4 = new JTable(t2);
	    table_4.setBounds(21, 47, 678, 456);
	    table_4.getTableHeader().setReorderingAllowed(false);
	    table_4.setShowHorizontalLines(true);
	    table_4.setShowVerticalLines(true);
	    table_4.setGridColor(Color.BLACK);
	    JScrollPane scroll1 = new JScrollPane(table_4);
	    scroll1.getVerticalScrollBar().setUI(new diseñoscroll());
	    scroll1.setBounds(0, 0, 678, 456);
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(21, 47, 678, 456);
	    panel.setLayout(null);
	    panel.add(scroll1);
	    fondo2.add(panel);
	    
	    JPanel panel_5_1 = new JPanel();
	    panel_5_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					FormCliente();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_5_1.setLayout(null);
	    panel_5_1.setBackground(new Color(216, 216, 216));
	    panel_5_1.setBounds(763, 133, 138, 45);
	    fondo2.add(panel_5_1);
	    
	    JLabel lblNewLabel_4_1 = new JLabel("Agregar\r\n");
	    lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_4_1.setBounds(46, 10, 100, 25);
	    panel_5_1.add(lblNewLabel_4_1);
	    
	    JPanel panel_5_2 = new JPanel();
	    panel_5_2.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					EditarCliente();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_5_2.setLayout(null);
	    panel_5_2.setBackground(new Color(216, 216, 216));
	    panel_5_2.setBounds(763, 239, 138, 45);
	    fondo2.add(panel_5_2);
	    
	    JLabel lblNewLabel_4_2 = new JLabel("Editar");
	    lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_4_2.setBounds(52, 10, 100, 25);
	    panel_5_2.add(lblNewLabel_4_2);
	    
	    JPanel panel_5_3 = new JPanel();
	    panel_5_3.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		 try {
					BorrarCliente();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_5_3.setLayout(null);
	    panel_5_3.setBackground(new Color(216, 216, 216));
	    panel_5_3.setBounds(763, 345, 138, 45);
	    fondo2.add(panel_5_3);
	    
	    JLabel lblNewLabel_4_3 = new JLabel("Eliminar");
	    lblNewLabel_4_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_4_3.setBounds(46, 10, 100, 25);
	    panel_5_3.add(lblNewLabel_4_3);
	    
	    JLabel lblNewLabel_1 = new JLabel("Módulo de Clientes");
	    lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
	    lblNewLabel_1.setBounds(21, 10, 649, 27);
	    fondo2.add(lblNewLabel_1);
	    
	    //Ventana para el inventario
	    PanelProducto fondo3 = new PanelProducto("C:\\Users\\juand\\Downloads\\fact6.jpg");
	    
	    t3 = new DefaultTableModel(new Object[]{"id", "nombre","color", "tipo", "precio_venta"}, 0){
	       	 @Override
	       	    public boolean isCellEditable(int row, int column) {
	       	        return false; 
	       	    }
	       	
	       };
	  
	    tabbedPane.addTab("Inventario", null, fondo3, null);
	    fondo3.setLayout(null);
	    table_2 = new JTable(t3);
	    JScrollPane scroll2 = new JScrollPane(table_2);
	    scroll2.getVerticalScrollBar().setUI(new diseñoscroll());
	    scroll2.setBounds(0, 0, 678, 456);
	    table_2.setBounds(10, 10, 779, 451);
	    
	    JPanel panel1 = new JPanel();
	    panel1.setBounds(21, 47, 678, 456);
	    panel1.setLayout(null);
	    panel1.add(scroll2);
	    fondo3.add(panel1);
	    
	    JPanel panel_5_1_1 = new JPanel();
	    panel_5_1_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					AgregarProducto();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_5_1_1.setLayout(null);
	    panel_5_1_1.setBackground(new Color(216, 216, 216));
	    panel_5_1_1.setBounds(763, 133, 138, 45);
	    fondo3.add(panel_5_1_1);
	    
	    JLabel lblNewLabel_4_1_1 = new JLabel("Agregar\r\n");
	    lblNewLabel_4_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_4_1_1.setBounds(46, 10, 100, 25);
	    panel_5_1_1.add(lblNewLabel_4_1_1);
	    
	    JPanel panel_5_2_1 = new JPanel();
	    panel_5_2_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					EditarProducto();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    panel_5_2_1.setLayout(null);
	    panel_5_2_1.setBackground(new Color(216, 216, 216));
	    panel_5_2_1.setBounds(763, 239, 138, 45);
	    fondo3.add(panel_5_2_1);
	    
	    JLabel lblNewLabel_4_2_1 = new JLabel("Editar");
	    lblNewLabel_4_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_4_2_1.setBounds(52, 10, 100, 25);
	    panel_5_2_1.add(lblNewLabel_4_2_1);
	    
	    JPanel panel_5_3_1 = new JPanel();
	    panel_5_3_1.setLayout(null);
	    panel_5_3_1.setBackground(new Color(216, 216, 216));
	    panel_5_3_1.setBounds(763, 345, 138, 45);
	    fondo3.add(panel_5_3_1);
	    
	    JLabel lblNewLabel_4_3_1 = new JLabel("Eliminar");
	    lblNewLabel_4_3_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					BorrarProducto();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    lblNewLabel_4_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lblNewLabel_4_3_1.setBounds(46, 10, 100, 25);
	    panel_5_3_1.add(lblNewLabel_4_3_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("Módulo de Inventario");
	    lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
	    lblNewLabel_2.setBounds(21, 10, 649, 27);
	    fondo3.add(lblNewLabel_2);
	    
	    //Menu del sistema
	    
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu mnNewMenu = new JMenu("Archivo");
	    menuBar.add(mnNewMenu);
	    
	    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Salir");
	    mntmNewMenuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Salir();
	    	}
	    });
	    
	    JMenuItem mntmNewMenuItem_2 = new JMenuItem("Generar reporte");
	    mntmNewMenuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					ListarFactura();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		try {
					ListarFactura();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    mnNewMenu.add(mntmNewMenuItem_2);
	    
	    JMenuItem mntmNewMenuItem_5 = new JMenuItem("Listar clientes");
	    mntmNewMenuItem_5.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					ListarClientes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    mnNewMenu.add(mntmNewMenuItem_5);
	    
	    JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listar productos");
	    mntmNewMenuItem_6.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					ListarProducto();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    mnNewMenu.add(mntmNewMenuItem_6);
	    mnNewMenu.add(mntmNewMenuItem_1);
	    
	    JMenu mnNewMenu_1 = new JMenu("Ayuda");
	    menuBar.add(mnNewMenu_1);
	    
	    JMenuItem mntmNewMenuItem = new JMenuItem("Contacto");
	    mntmNewMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		mostrarSoporte();
	    	}
	    });
	    mnNewMenu_1.add(mntmNewMenuItem);
	    
	  
	    setLocationRelativeTo(null);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	//Parte de clientes
	
	public void FormCliente() throws SQLException {
		
		FormularioCliente form = new FormularioCliente(this);
		form.setLocationRelativeTo(this);
	    
	    // Deshabilita la principal
	    this.setEnabled(false);
	    form.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            setEnabled(true);
	            toFront(); // trae la principal al frente
	        }

	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Esto es necesario para que se dispare windowClosed
	            form.dispose();
	        	
	        }
	    });

	    form.setVisible(true);
	}
	public void BorrarCliente() throws SQLException {
		BorrarCliente form1 = new BorrarCliente(this);
		form1.setLocationRelativeTo(this);
	    
	    // Deshabilita la principal
	    this.setEnabled(false);
	    form1.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            setEnabled(true);
	            toFront(); // trae la principal al frente
	        }

	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Esto es necesario para que se dispare windowClosed
	            form1.dispose();
	        	
	        }
	    });

	    form1.setVisible(true);
	}
public void EditarCliente() throws SQLException {
		
	EditarCliente form2 = new EditarCliente(this);
		form2.setLocationRelativeTo(this);
	    
	    // Deshabilita la principal
	    this.setEnabled(false);
	    form2.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            setEnabled(true);
	            toFront(); // trae la principal al frente
	        }

	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Esto es necesario para que se dispare windowClosed
	            form2.dispose();
	        	
	        }
	    });

	    form2.setVisible(true);
	}

    //Parte de Producto

   public void AgregarProducto() throws SQLException {
	   FormularioProducto form_ = new FormularioProducto(this);
	   form_.setLocationRelativeTo(this);
	    
	    // Deshabilita la principal
	    this.setEnabled(false);
	    form_.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            setEnabled(true);
	            toFront(); // trae la principal al frente
	        }

	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Esto es necesario para que se dispare windowClosed
	            form_.dispose();
	        	
	        }
	    });

	    form_.setVisible(true);
   }
   
   public void BorrarProducto() throws SQLException {
	   BorrarProducto form_1 = new BorrarProducto(this);
	   form_1.setLocationRelativeTo(this);
	    
	    // Deshabilita la principal
	    this.setEnabled(false);
	    form_1.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            setEnabled(true);
	            toFront(); // trae la principal al frente
	        }

	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Esto es necesario para que se dispare windowClosed
	            form_1.dispose();
	        	
	        }
	    });

	    form_1.setVisible(true);
   }
   
   public void EditarProducto() throws SQLException {
		
	   EditarProducto form_2 = new EditarProducto(this);
			form_2.setLocationRelativeTo(this);
		    
		    // Deshabilita la principal
		    this.setEnabled(false);
		    form_2.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosed(WindowEvent e) {
		            setEnabled(true);
		            toFront(); // trae la principal al frente
		        }

		        @Override
		        public void windowClosing(WindowEvent e) {
		            // Esto es necesario para que se dispare windowClosed
		            form_2.dispose();
		        	
		        }
		    });

		    form_2.setVisible(true);
		}
///
////////////////////////////////////////////////////////////////////////////////////////
//Parte de factura
   
   public void AgregarFactura() throws SQLException{
	   FormularioFactura form_3 = new FormularioFactura(this);
	   form_3.setLocationRelativeTo(this);
	    
	    // Deshabilita la principal
	    this.setEnabled(false);
	    form_3.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosed(WindowEvent e) {
	            setEnabled(true);
	            toFront(); // trae la principal al frente
	        }

	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Esto es necesario para que se dispare windowClosed
	        	form_3.dispose();
	        	
	        }
	    });

	    form_3.setVisible(true);
   }
   
   public void BorrarFactura() throws SQLException {
   BorrarFactura form_4 = new BorrarFactura(this);
   form_4.setLocationRelativeTo(this);
    
    // Deshabilita la principal
    this.setEnabled(false);
    form_4.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            setEnabled(true);
            toFront(); // trae la principal al frente
        }

        @Override
        public void windowClosing(WindowEvent e) {
            // Esto es necesario para que se dispare windowClosed
        	form_4.dispose();
        	
        }
    });

    form_4.setVisible(true);
}
   

	////////////////////////////////////////////////
	void Salir() {
		int op = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
		if(op == JOptionPane.YES_NO_OPTION) {
			System.exit(0);;
		}
	}
	
	private void mostrarSoporte() {
	    String mensaje = "Soporte tecnico\n\n" +
	                     "Nombre: Juan David Cabral Ortiz\n" +
	                     "Telefono: 829-923-5017\n" +
	                     "Correo: juandavidcabral71@gmail.com";
	                     
	    JOptionPane.showMessageDialog(this, mensaje, "Perfil del Estudiante", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void ListarClientes() throws SQLException {
		ArrayList<Cliente> listar = control.Listar(); 
		cliente.exportarAExcel(listar, "clientes.xlsx");
		JOptionPane.showMessageDialog(this, "Archivo guardado en excel con exito");
	}
	
	public void ListarFactura() throws SQLException {
		ArrayList<Factura> listar = control3.Listar(); 
		factura.exportarAExcel(listar, "factura.xlsx");
		JOptionPane.showMessageDialog(this, "Archivo guardado en excel con exito");
	}
	
	public void ListarProducto() throws SQLException {
		ArrayList<Producto> listar = control2.Listar(); 
	    producto.exportarAExcel(listar, "Inventario.xlsx");
	    JOptionPane.showMessageDialog(this, "Archivo guardado en excel con exito");
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	void actualizarFactura() throws SQLException {
		 ArrayList<Factura> lista = control3.Listar(); 

		    t1.setRowCount(0); // Limpiar la tabla

		    for (Factura factura : lista) {
		        t1.addRow(new Object[]{
		            factura.getidfactura(),
		            factura.getfecha(),
		            factura.getpago(),
		            factura.getestado()
		        });
		    }
	}
		    
	void actualizarCliente() throws SQLException {
		 ArrayList<Cliente> lista = control.Listar(); 

		    t2.setRowCount(0); // Limpiar la tabla

		    for (Cliente cliente : lista) {
		        t2.addRow(new Object[]{
		            cliente.getidcliente(),
		            cliente.getnombre(),
		            cliente.getdireccion(),
		            cliente.getContacto()
		        });
		    }
	    }
	
	void actualizarProducto() throws SQLException {
		 ArrayList<Producto> lista = control2.Listar(); 

		    t3.setRowCount(0); // Limpiar la tabla

		    for (Producto producto : lista) {
		        t3.addRow(new Object[]{
		        	producto.getidproducto(),
		        	producto.getnombre(),
		        	producto.getcolor(),
		        	producto.gettipo_pintura(),
		        	producto.getprecio_venta()
		        });
		    }
	    }
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws SQLException {
		SistemaGUI prueba = new SistemaGUI();
		
	}
}
