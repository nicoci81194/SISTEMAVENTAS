package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JLabel lblAcceso;
	public static JMenu mnMantenimiento;
	public static JMenu mnConsultas;
	private JMenu mnVenta;
	private JMenu mnSistema;
	private JMenuItem mntmProductos;
	private JMenuItem mntmTrabajadores;
	private JMenuItem mntmCliente;
	private JMenuItem mntmVenta;
	private JMenuItem mntmReporte;
	private JMenuItem mnCerrar;
	public static JLabel lblIdT;
	public static JLabel lblNombre;
	public static JLabel lblApellido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
					//frame.setExtendedState(MAXIMIZE_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/img/logo100p.jpg")));
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 395);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSistema = new JMenu("Sistema de Ventas");
		mnSistema.setFont(new Font("Verdana", Font.BOLD, 13));
		mnSistema.setBackground(Color.WHITE);
		mnSistema.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/logo20p.jpg")));
		menuBar.add(mnSistema);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setFont(new Font("Verdana", Font.BOLD, 13));
		mnMantenimiento.setBackground(Color.WHITE);
		mnMantenimiento.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/mantenimiento.png")));
		menuBar.add(mnMantenimiento);
		
		mntmProductos = new JMenuItem("Mantenimiento Productos");
		mntmProductos.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmProductos.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/producto (1).png")));
		mntmProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mProducto p = new mProducto();
				contentPane.add(p);
				p.setVisible(true);
			}
		});
		mnMantenimiento.add(mntmProductos);
		
		mntmTrabajadores = new JMenuItem("Mantenimiento Trabajadores");
		mntmTrabajadores.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmTrabajadores.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/vendedor (1).png")));
		mntmTrabajadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mTrabajador t = new mTrabajador();
				contentPane.add(t);
				t.setVisible(true);
			}
		});
		mnMantenimiento.add(mntmTrabajadores);
		
		mnVenta = new JMenu("Venta");
		mnVenta.setFont(new Font("Verdana", Font.BOLD, 13));
		mnVenta.setBackground(Color.WHITE);
		mnVenta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/venta.png")));
		menuBar.add(mnVenta);
		
		mntmCliente = new JMenuItem("Registrar Cliente");
		mntmCliente.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmCliente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/cliente (1).png")));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vRegistrarCliente rc = new vRegistrarCliente();
				contentPane.add(rc);
				rc.setVisible(true);
			}
		});
		mnVenta.add(mntmCliente);
		
		mntmVenta = new JMenuItem("Registrar Venta");
		mntmVenta.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmVenta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/vent1as.png")));
		mntmVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vRegistrarVenta rv = new vRegistrarVenta();
				contentPane.add(rv);
				rv.setVisible(true);
								
				vRegistrarVenta.txtIdV.setText(lblIdT.getText());
				vRegistrarVenta.txtVendedor.setText(lblNombre.getText()+" "+lblApellido.getText());
			}
		});
		mnVenta.add(mntmVenta);
		
		mnConsultas = new JMenu("Consultas");
		mnConsultas.setFont(new Font("Verdana", Font.BOLD, 13));
		mnConsultas.setBackground(Color.WHITE);
		mnConsultas.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/reporte.png")));
		menuBar.add(mnConsultas);
		
		mntmReporte = new JMenuItem("Reporte por dias");
		mntmReporte.setFont(new Font("Verdana", Font.BOLD, 13));
		mntmReporte.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/reportes (1).png")));
		mntmReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cReporte r = new cReporte();
				contentPane.add(r);
				r.setVisible(true);
			}
		});
		mnConsultas.add(mntmReporte);
		
		JMenu mnSalir = new JMenu("Salir");
		mnSalir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/cerrarN.png")));
		mnSalir.setFont(new Font("Verdana", Font.BOLD, 13));
		mnSalir.setBackground(Color.WHITE);
		mnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuBar.add(mnSalir);
		
		mnCerrar = new JMenuItem("Cerrar Sesi\u00F3n");
		mnCerrar.setFont(new Font("Verdana", Font.BOLD, 13));
		mnCerrar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/salir (1).png")));
		mnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(rootPane, "¿Realmente desea salir?","Salida",JOptionPane.YES_NO_OPTION)==0) {
				System.exit(0);
				}
			}
		});
		mnSalir.add(mnCerrar);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAcceso = new JLabel("New label");
		lblAcceso.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAcceso.setBounds(30, 55, 88, 14);
		contentPane.add(lblAcceso);
		
		lblIdT = new JLabel("New label");
		lblIdT.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblIdT.setBounds(30, 30, 88, 14);
		contentPane.add(lblIdT);
		
		lblNombre = new JLabel("New label");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombre.setBounds(30, 80, 88, 14);
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("New label");
		lblApellido.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblApellido.setBounds(30, 105, 88, 14);
		contentPane.add(lblApellido);
	}
}
