package vista;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import negocio.nProducto;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import entidad.eProducto;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import datos.Conexion;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class mProducto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdP;
	private JTextField txtDesc;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextField txtBuscar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mProducto frame = new mProducto();
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
	//ACA NO OLVIDAR PARA LLAMAR AL REPORTE
	private Connection connection = new Conexion().conectar();
	
	nProducto ObjNP = new nProducto();
	int totalregistros = 0;
	
	//DATOS PARA LA CARGA DEL JTABLE
	DefaultTableModel tabla;
	String Columnas[] = {"IdProducto","Descripcion","Precio","Stock","Estado"};
	Object Filas[][];
	ArrayList<eProducto> miLista;	
	
	private JLabel lblNewLabel;
	private JComboBox cboEstado;
	private JButton btnAceptar;
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JButton btnReporte;
	private JButton btnSalir;
	private JLabel lblNewLabel_9;
	
	
	//METODO PARA CARGAR EL CONTROL JTABLE
	private void CargarTabla() {
		
		tabla = new DefaultTableModel();
		miLista = new ArrayList<eProducto>();
		miLista = ObjNP.Listar();
		Filas = new Object[miLista.size()][5];
		for(int i = 0; i < Columnas.length; i++)tabla.addColumn(Columnas[i]);
		for(int i = 0; i < miLista.size(); i++) {
			Filas[i][0] = miLista.get(i).getIdProducto();
			Filas[i][1] = miLista.get(i).getDescripcion();
			Filas[i][2] = miLista.get(i).getPrecio();
			Filas[i][3] = miLista.get(i).getStock();
			Filas[i][4] = miLista.get(i).getEstado();
			
			tabla.addRow(Filas[i]);
		}
		table.setModel(tabla);		
	}	
	
	//METODO CONTAR
	public void contar() {
		totalregistros= tabla.getRowCount();
		lblNewLabel.setText("Registros "+Integer.toString(totalregistros));
	}
	
	//METODO LIMPIAR CAJAS
	private void LimpiarCajas() {
		txtIdP.setText("");
		txtDesc.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		txtIdP.requestFocus();
		cboEstado.setSelectedItem("Seleccionar");
	}
	
	public mProducto() {
		setTitle("Matenimiento");
		getContentPane().setBackground(new Color(255, 255, 255));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 892, 395);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(10, 40, 275, 313);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("DATOS");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 11, 97, 17);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("IdProducto");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 43, 97, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Descripcion");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 68, 97, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Precio");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 93, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Stock");
		lblNewLabel_6.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 118, 46, 14);
		panel.add(lblNewLabel_6);
		
		txtIdP = new JTextField();
		txtIdP.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIdP.setColumns(10);
		txtIdP.setBounds(100, 40, 104, 20);
		panel.add(txtIdP);
		
		txtDesc = new JTextField();
		txtDesc.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDesc.setColumns(10);
		txtDesc.setBounds(100, 65, 164, 20);
		panel.add(txtDesc);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(100, 90, 164, 20);
		panel.add(txtPrecio);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtStock.setColumns(10);
		txtStock.setBounds(100, 115, 104, 20);
		panel.add(txtStock);
		
		JLabel lblNewLabel_7 = new JLabel("Estado");
		lblNewLabel_7.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 143, 46, 14);
		panel.add(lblNewLabel_7);
		
		cboEstado = new JComboBox();
		cboEstado.setFont(new Font("Verdana", Font.PLAIN, 12));
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "1", "2"}));
		cboEstado.setBounds(100, 140, 104, 22);
		panel.add(cboEstado);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setIcon(new ImageIcon(mProducto.class.getResource("/img/agregar.png")));
		btnAgregar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAgregar(e);
			}
		});
		btnAgregar.setBounds(67, 207, 127, 27);
		panel.add(btnAgregar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBackground(new Color(255, 255, 255));
		btnActualizar.setIcon(new ImageIcon(mProducto.class.getResource("/img/modificar.png")));
		btnActualizar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonActualizar(e);
			}
		});
		btnActualizar.setBounds(67, 241, 127, 27);
		panel.add(btnActualizar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(mProducto.class.getResource("/img/aceptarN.png")));
		btnAceptar.setBackground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAceptar(e);
			}
		});
		btnAceptar.setBounds(67, 173, 127, 27);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setIcon(new ImageIcon(mProducto.class.getResource("/img/cancelarN.png")));
		btnCancelar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonCancelar(e);
			}
		});
		btnCancelar.setBounds(67, 275, 127, 27);
		panel.add(btnCancelar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBounds(295, 40, 569, 313);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("LISTADO DE PRODUCTOS");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 11, 201, 17);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_8 = new JLabel("Buscar");
		lblNewLabel_8.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 36, 46, 14);
		panel_1.add(lblNewLabel_8);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(66, 33, 113, 20);
		panel_1.add(txtBuscar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setIcon(new ImageIcon(mProducto.class.getResource("/img/buscarProducto.png")));
		btnBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonBuscar(e);
			}
		});
		btnBuscar.setBounds(200, 30, 113, 27);
		panel_1.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setIcon(new ImageIcon(mProducto.class.getResource("/img/suprimir.png")));
		btnEliminar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonEliminar(e);
			}
		});
		btnEliminar.setBounds(323, 30, 113, 27);
		panel_1.add(btnEliminar);
		
		btnSalir = new JButton("Cerrar");
		btnSalir.setIcon(new ImageIcon(mProducto.class.getResource("/img/cerrarN.png")));
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonSalir(e);
			}
		});
		btnSalir.setBounds(458, 276, 101, 27);
		panel_1.add(btnSalir);
		
		lblNewLabel = new JLabel("Registros");
		lblNewLabel.setBackground(new Color(211, 211, 211));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 282, 132, 14);
		panel_1.add(lblNewLabel);
		
		
		
		btnReporte = new JButton("Reporte");
		btnReporte.setIcon(new ImageIcon(mProducto.class.getResource("/img/reporteBT.png")));
		btnReporte.setBackground(new Color(255, 255, 255));
		btnReporte.setFont(new Font("Verdana", Font.BOLD, 12));
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonReporte(e);
			}
		});
		btnReporte.setBounds(446, 30, 113, 27);
		panel_1.add(btnReporte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 549, 195);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMiTabla(e);
			}
		});
		scrollPane.setViewportView(table);
		
		//CARGAR TABLA
		CargarTabla();
		//CONTAR REGISTRO DE LA TABLA
		contar();
		//BOTON ACEPTAR EMPIZA DESACTIVADO
		btnAceptar.setEnabled(false);
		
		lblNewLabel_9 = new JLabel("MANTENIMIENTO PRODUCTO");
		lblNewLabel_9.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel_9.setForeground(new Color(0, 0, 0));
		lblNewLabel_9.setBounds(10, 11, 262, 18);
		getContentPane().add(lblNewLabel_9);
	}
	
	protected void BotonAgregar(ActionEvent e) {
		LimpiarCajas();
		txtIdP.setText(ObjNP.GeneraCodigoProducto());
		txtIdP.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnAceptar.setEnabled(true);
	}
	
	protected void BotonAceptar(ActionEvent e) {
		if(btnAgregar.isEnabled()==false){
		String id=txtIdP.getText();
		String des=txtDesc.getText();
		double pre=Double.parseDouble(txtPrecio.getText());
		int sto=Integer.parseInt(txtStock.getText());
		String est=cboEstado.getSelectedItem().toString();
		
		eProducto ObjP = new eProducto(id,des,pre,sto,est);
		ObjNP.Insertar(ObjP);
		CargarTabla();
		LimpiarCajas();
		JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
		
		btnAgregar.setEnabled(true);
		txtIdP.setEnabled(true);
		btnAceptar.setEnabled(false);
		contar();
		}
		
		if(btnActualizar.isEnabled()==false){
			eProducto ObjP = new eProducto(txtIdP.getText(),
					txtDesc.getText(),
					Double.parseDouble(txtPrecio.getText()),
					Integer.parseInt(txtStock.getText()),
					cboEstado.getSelectedItem().toString()
					);
			ObjNP.Modificar(ObjP);
			LimpiarCajas();
			CargarTabla();	
			JOptionPane.showMessageDialog(null, "SE MODIFICO PRODUCTO");
			
			btnActualizar.setEnabled(true);
			txtIdP.setEnabled(true);
			btnAceptar.setEnabled(false);
		}		
	}
	
	protected void BotonActualizar(ActionEvent e) {
		int fila=table.getSelectedRow();
		
		if(fila==-1){			
			JOptionPane.showMessageDialog(this, "Debe selecionar una fila");
			//LimpiarCajas();
			txtIdP.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnAceptar.setEnabled(true);			
		}			
	}
	
	protected void BotonCancelar(ActionEvent e) {
		btnAceptar.setEnabled(false);
		btnActualizar.setEnabled(true);
		btnAgregar.setEnabled(true);
		txtIdP.setEnabled(true);
		LimpiarCajas();
		JOptionPane.showMessageDialog(null, "SE CANCECLO ACCION");		
	}
	
	protected void BotonBuscar(ActionEvent e) {
		eProducto ObjP = new eProducto();
		ObjP = ObjNP.Buscar(txtBuscar.getText());

		if(ObjP !=null){
			txtIdP.setText(ObjP.getIdProducto());
			txtDesc.setText(ObjP.getDescripcion());
			txtPrecio.setText(ObjP.getPrecio()+"");
			txtStock.setText(ObjP.getStock()+"");
		}else{
			JOptionPane.showMessageDialog(null, "ERROR, CODIGO PRODUCTO NO EXISTE");
		}
	}
	
	protected void BotonEliminar(ActionEvent e) {
		int fila=table.getSelectedRow();
		if(fila==-1){
			JOptionPane.showMessageDialog(this, "Debe selecionar una fila");
		}else{
			ObjNP.Suprimir(txtIdP.getText());
			LimpiarCajas();
			CargarTabla();
			JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO CORRECTAMENTE");
		}
	}
	
	protected void BotonReporte(ActionEvent e) {
		//VARIABLES
		Map p = new HashMap();
		JasperReport report;
		JasperPrint print;
		
		try {
			//RUTA
			report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
					"/src/reporte/rProducto.jrxml");
			print=JasperFillManager.fillReport(report, p, connection);
			//VISUALIZAR
			JasperViewer view = new JasperViewer(print,false);
			
			view.setTitle("Reporte de Productos");
			view.setVisible(true);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected void BotonSalir(ActionEvent e) {
		dispose();
	}
	
	// METODO MOUSE CLICKED EN MI TABLA
	protected void mouseClickedMiTabla(MouseEvent e) {
		int f = table.getSelectedRow();
		txtIdP.setText(table.getValueAt(f, 0).toString());
		txtDesc.setText(table.getValueAt(f, 1).toString());
		txtPrecio.setText(table.getValueAt(f, 2).toString());
		txtStock.setText(table.getValueAt(f, 3).toString());
	}

}
