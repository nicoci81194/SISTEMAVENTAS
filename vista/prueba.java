package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datos.Conexion;
import datos.dVenta;
import entidad.eVenta;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class prueba extends JFrame {
	//ACA NO OLVIDAR PARA LLAMAR AL REPORTE
			private Connection connection = new Conexion().conectar();
		dVenta dv= new dVenta();
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtId;
	private JButton btnReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba frame = new prueba();
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
	private JButton btnBuscar;
	
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
	

	
	public prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 414, 189);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtId = new JTextField();
		txtId.setBounds(68, 22, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map p = new HashMap();
				//Map q = new HashMap();
				p.put("id", txtId.getText());
				//q.put("fin", txtFin.getText());
				JasperReport report;
				JasperPrint print;
				
				try {
					//ruta proyecto
					report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
							"/src/reporte/rVentas.jrxml");
					print=JasperFillManager.fillReport(report, p, connection);
					//visualizar
					JasperViewer view = new JasperViewer(print,false);
					
					view.setTitle("Reporte de Venta");
					view.setVisible(true);
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnReporte.setBounds(301, 21, 89, 23);
		contentPane.add(btnReporte);
		
		btnBuscar = new JButton("buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar(e);
			}
		});
		btnBuscar.setBounds(174, 21, 89, 23);
		contentPane.add(btnBuscar);
		
		CargarTabla();
	}
	
	protected void buscar(ActionEvent e) {
		limpiarTabla();
		fecha(txtId.getText());
		dVenta func = new dVenta();
		//lblRegistros.setText("Total Registros " + Integer.toString(func.totalregistros));
	}
	
	void fecha(String ob) {
		dVenta ds = new dVenta();
		
		DefaultTableModel modelo = ds.BuscarId(ob);
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
}
