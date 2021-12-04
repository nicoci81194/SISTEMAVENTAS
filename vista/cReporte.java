package vista;

import java.awt.EventQueue;

import datos.Conexion;
import datos.dVenta;
import entidad.eProducto;
import entidad.eVenta;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;

public class cReporte extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ACA NO OLVIDAR PARA LLAMAR AL REPORTE
		private Connection connection = new Conexion().conectar();
	dVenta dv= new dVenta();
	int totalregistros1 = 0;
	
	private JTextField txtInicio;
	private JTextField txtFin;
	private JButton btnReporte;
	private JButton btnBuscar;
	private JTable table;
	private JLabel lblRegistros;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cReporte frame = new cReporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//DATOS PARA LA CARGA DEL JTABLE
	DefaultTableModel tabla;
	String Columnas[] = {"IdVenta","IdCliente","IdTrabajador","NumeroVenta","FechaVenta","Monto","Estado"};
	Object Filas[][];
	ArrayList<eVenta> miLista;	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	//METODO PARA CARGAR EL CONTROL JTABLE
	private void CargarTabla() {
		
		tabla = new DefaultTableModel();
		miLista = new ArrayList<eVenta>();
		miLista = dv.Listar();
		Filas = new Object[miLista.size()][7];
		for(int i = 0; i < Columnas.length; i++)tabla.addColumn(Columnas[i]);
		for(int i = 0; i < miLista.size(); i++) {
			Filas[i][0] = miLista.get(i).getIdVenta();
			Filas[i][1] = miLista.get(i).getIdCliente();
			Filas[i][2] = miLista.get(i).getIdTrabajador();
			Filas[i][3] = miLista.get(i).getNumeroVenta();
			Filas[i][4] = miLista.get(i).getFechaVenta();
			Filas[i][5] = miLista.get(i).getMonto();
			Filas[i][6] = miLista.get(i).getEstado();
			
			tabla.addRow(Filas[i]);
		}
		table.setModel(tabla);		
	}	
	
	public void contar() {
		totalregistros1= tabla.getRowCount();
		lblRegistros.setText("Registros "+Integer.toString(totalregistros1));
	}
	

	
	public cReporte() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Consulta");
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 628, 385);
		getContentPane().setLayout(null);
		
		txtInicio = new JTextField();
		txtInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtInicio.setBounds(128, 54, 174, 20);
		getContentPane().add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFin = new JTextField();
		txtFin.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtFin.setBounds(428, 54, 174, 20);
		getContentPane().add(txtFin);
		txtFin.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(UIManager.getColor("Button.background"));
		btnBuscar.setIcon(new ImageIcon(cReporte.class.getResource("/img/buscar.png")));
		btnBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonBuscar(e);
			}
		});
		btnBuscar.setBounds(168, 128, 108, 29);
		getContentPane().add(btnBuscar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setIcon(new ImageIcon(cReporte.class.getResource("/img/reporteBT.png")));
		btnReporte.setBackground(UIManager.getColor("Button.background"));
		btnReporte.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonReporte(e);
			}
		});
		btnReporte.setBounds(335, 128, 108, 29);
		getContentPane().add(btnReporte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 162, 592, 142);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblRegistros = new JLabel("Registros 0");
		lblRegistros.setFont(new Font("Verdana", Font.BOLD, 12));
		lblRegistros.setBounds(10, 315, 156, 14);
		getContentPane().add(lblRegistros);
		
		btnSalir = new JButton("Cerrar");
		btnSalir.setIcon(new ImageIcon(cReporte.class.getResource("/img/cerrarN.png")));
		btnSalir.setBackground(UIManager.getColor("Button.background"));
		btnSalir.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonSalir(e);
			}
		});
		btnSalir.setBounds(503, 315, 99, 29);
		getContentPane().add(btnSalir);
		
		lblNewLabel = new JLabel("CONSULTA POR RANGO DE D\u00CDAS");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel.setBounds(166, 11, 270, 20);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Fecha de Inicio");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 57, 141, 14);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Fecha Final");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(335, 57, 108, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("yyyy-mm-dd");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(173, 85, 86, 18);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("yyyy-mm-dd");
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(475, 84, 86, 20);
		getContentPane().add(lblNewLabel_4);
		
		CargarTabla();
		contar();

	}
	
	//BOTON BUSCAR
	protected void BotonBuscar(ActionEvent e) {
		limpiarTabla();
		fecha(txtInicio.getText(),txtFin.getText());
		dVenta func = new dVenta();
		lblRegistros.setText("Total Registros " + Integer.toString(func.totalregistros));
		
	}
	
	
	public void  fecha(String string, String string2){
		dVenta ds = new dVenta();
		
		DefaultTableModel modelo = ds.fecha(string,string2);
		//totalregistros= totalregistros+1;
		table.setModel(modelo);
				
	}
	
	//LIMPIAR TABLA
	public void limpiarTabla(){
		for(int i=0; i<tabla.getRowCount(); i++){
			tabla.removeRow(i);
			i=i-1;
		}
	}
	
	//BOTON REPORTE
	protected void BotonReporte(ActionEvent e) {
		//Variables para reporte
		
		Map p = new HashMap();
		Map q = new HashMap();
		p.put("inicio", txtInicio.getText());
		q.put("fin", txtFin.getText());
		JasperReport report;
		JasperPrint print;
		
		try {
			//ruta proyecto
			report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
					"/src/reporte/rDias.jrxml");
			print=JasperFillManager.fillReport(report, p, connection);
			//visualizar
			JasperViewer view = new JasperViewer(print,false);
			
			view.setTitle("Reporte de Venta");
			view.setVisible(true);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	//BOTON CERRAR
	protected void BotonSalir(ActionEvent e) {
		dispose();
	}

}
