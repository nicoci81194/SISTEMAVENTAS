package vista;

import entidad.eCliente;
import negocio.nCliente;

import entidad.eProducto;
import negocio.nProducto;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import entidad.eTrabajador;

import entidad.eDetalleVenta;
import datos.Conexion;
import datos.dVenta;
import entidad.eVenta;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ImageIcon;

public class vRegistrarVenta extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//ACA NO OLVIDAR PARA LLAMAR AL REPORTE
	private Connection connection = new Conexion().conectar();
	
	nCliente ObjNC = new nCliente();
	nProducto ObjNP = new nProducto();	
	eProducto ObjP = new eProducto();
	dVenta ObjV=new dVenta();
	eTrabajador ObjVn = new eTrabajador();
	eDetalleVenta ObjNDV = new eDetalleVenta();
	
	// variables para agregar prod
	String IdProd;
	double Tpagar;
	int cantProd;
	double preProd;
	int totalregistros = 0;
	
	Object Columnas[] = {"item","codProd","Producto","Cant","PreUni","Total"};
	
	DefaultTableModel modelo= new DefaultTableModel(Columnas,0);
	
	
	private JTextField txtCBO;
	private JTextField txtTotalPagar;
	private JTextField txtDniCli;
	private JTextField txtIdP;
	private JTextField txtPrecioP;
	private JTextField txtFecha;
	private JTextField txtCli;
	private JTextField txtProd;
	private JTextField txtStock;
	
	private JButton btnImprimir;
	private JButton btnGenerarVenta;
	private JButton btnCerrar;
	private JButton btnEliminar;
	private JButton btnBuscarC;
	private JButton btnBuscarP;
	private JButton btnAgregar;
	private JSpinner txtCantidadP;
	private JLabel lblRegistros;
	private JTable table;
	private JScrollPane scrollPane;
	public static JTextField txtVendedor;
	public static JTextField txtIdV;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vRegistrarVenta frame = new vRegistrarVenta();
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
	public vRegistrarVenta() {
		setTitle("Registro Venta");
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 661, 603);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 0, 626, 147);
		getContentPane().add(panel);
		
		JLabel lblPuntoDeVenta = new JLabel("PUNTO DE VENTA SDEKI");
		lblPuntoDeVenta.setFont(new Font("Verdana", Font.BOLD, 17));
		lblPuntoDeVenta.setBounds(197, 11, 243, 27);
		panel.add(lblPuntoDeVenta);
		
		JLabel lblJugueteria = new JLabel("JUGUETERIA");
		lblJugueteria.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblJugueteria.setBounds(253, 49, 100, 14);
		panel.add(lblJugueteria);
		
		JLabel lblAlPorMayor = new JLabel("AL POR MAYOR Y MENOR");
		lblAlPorMayor.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblAlPorMayor.setBounds(224, 74, 180, 14);
		panel.add(lblAlPorMayor);
		
		JLabel lblNro = new JLabel("Nro:");
		lblNro.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNro.setBounds(234, 99, 59, 22);
		panel.add(lblNro);
		
		txtCBO = new JTextField();
		txtCBO.setForeground(Color.BLUE);
		txtCBO.setFont(new Font("Verdana", Font.BOLD, 12));
		//AAGREGAR PRUEBA
		txtCBO.setText(ObjV.GenerarCodigoCBP());
		txtCBO.setBounds(291, 101, 86, 20);
		panel.add(txtCBO);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 99, 626, 48);
		panel.add(panel_3);
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 359, 626, 170);
		getContentPane().add(panel_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 606, 148);
		panel_2.add(scrollPane);
		
		table = new JTable();
		//AGREGADO
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 158, 626, 152);
		getContentPane().add(panel_1);
		
		JLabel lblDniCliente = new JLabel("ID CLIENTE");
		lblDniCliente.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDniCliente.setBounds(10, 39, 104, 14);
		panel_1.add(lblDniCliente);
		
		JLabel lblCodProduto = new JLabel("ID PRODUTO");
		lblCodProduto.setFont(new Font("Verdana", Font.BOLD, 12));
		lblCodProduto.setBounds(10, 66, 130, 14);
		panel_1.add(lblCodProduto);
		
		JLabel lblPreProducto = new JLabel("PRECIO ");
		lblPreProducto.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPreProducto.setBounds(10, 92, 130, 14);
		panel_1.add(lblPreProducto);
		
		JLabel lblCantidadProd = new JLabel("CANTIDAD ");
		lblCantidadProd.setFont(new Font("Verdana", Font.BOLD, 12));
		lblCantidadProd.setBounds(10, 117, 130, 14);
		panel_1.add(lblCantidadProd);
		
		txtDniCli = new JTextField();
		txtDniCli.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDniCli.setColumns(10);
		txtDniCli.setBounds(105, 39, 129, 20);
		panel_1.add(txtDniCli);
		
		txtIdP = new JTextField();
		txtIdP.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIdP.setColumns(10);
		txtIdP.setBounds(105, 67, 129, 20);
		panel_1.add(txtIdP);
		
		txtPrecioP = new JTextField();
		txtPrecioP.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtPrecioP.setColumns(10);
		txtPrecioP.setBounds(105, 94, 129, 20);
		panel_1.add(txtPrecioP);
		
		btnBuscarC = new JButton("Buscar");
		btnBuscarC.setIcon(new ImageIcon(vRegistrarVenta.class.getResource("/img/buscarCliente.png")));
		btnBuscarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonBuscarCliente(e);
			}
		});
		btnBuscarC.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscarC.setBounds(244, 39, 116, 27);
		panel_1.add(btnBuscarC);
		
		btnBuscarP = new JButton("Buscar");
		btnBuscarP.setIcon(new ImageIcon(vRegistrarVenta.class.getResource("/img/buscarProducto.png")));
		btnBuscarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonBuscarProducto(e);
			}
		});
		btnBuscarP.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscarP.setBounds(244, 76, 116, 27);
		panel_1.add(btnBuscarP);
		
		txtFecha = new JTextField();
		txtFecha.setForeground(Color.BLUE);
		txtFecha.setFont(new Font("Verdana", Font.BOLD, 12));
		txtFecha.setColumns(10);
		txtFecha.setBounds(455, 11, 105, 20);
		panel_1.add(txtFecha);
		
		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setFont(new Font("Verdana", Font.BOLD, 12));
		lblCliente.setBounds(370, 41, 75, 14);
		panel_1.add(lblCliente);
		
		JLabel lblProducto = new JLabel("PRODUCTO");
		lblProducto.setFont(new Font("Verdana", Font.BOLD, 12));
		lblProducto.setBounds(370, 68, 97, 14);
		panel_1.add(lblProducto);
		
		JLabel lblStock = new JLabel("STOCK");
		lblStock.setFont(new Font("Verdana", Font.BOLD, 12));
		lblStock.setBounds(370, 92, 75, 14);
		panel_1.add(lblStock);
		
		JLabel lblVendedor = new JLabel("VENDEDOR");
		lblVendedor.setFont(new Font("Verdana", Font.BOLD, 12));
		lblVendedor.setBounds(10, 13, 75, 14);
		panel_1.add(lblVendedor);
		
		txtCli = new JTextField();
		txtCli.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCli.setColumns(10);
		txtCli.setBounds(455, 39, 159, 20);
		panel_1.add(txtCli);
		
		txtProd = new JTextField();
		txtProd.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtProd.setColumns(10);
		txtProd.setBounds(455, 66, 159, 20);
		panel_1.add(txtProd);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtStock.setColumns(10);
		txtStock.setBounds(455, 92, 90, 20);
		panel_1.add(txtStock);
		
		txtVendedor = new JTextField();
		txtVendedor.setForeground(Color.BLUE);
		txtVendedor.setFont(new Font("Verdana", Font.BOLD, 12));
		txtVendedor.setColumns(10);
		txtVendedor.setBounds(210, 11, 147, 20);
		panel_1.add(txtVendedor);
		
		txtCantidadP = new JSpinner();
		txtCantidadP.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCantidadP.setBounds(124, 118, 90, 20);
		panel_1.add(txtCantidadP);
		
		lblRegistros = new JLabel("Registros 0");
		lblRegistros.setFont(new Font("Verdana", Font.BOLD, 12));
		lblRegistros.setBounds(10, 334, 89, 14);
		getContentPane().add(lblRegistros);
		
		//FECHA 
		Calendar calendar=new GregorianCalendar();
		txtFecha.setText(""+calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH));		
		
		txtIdV = new JTextField();
		txtIdV.setForeground(Color.BLUE);
		txtIdV.setFont(new Font("Verdana", Font.BOLD, 12));
		txtIdV.setBounds(105, 11, 95, 20);
		panel_1.add(txtIdV);
		txtIdV.setColumns(10);
		
		lblNewLabel = new JLabel("FECHA ");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(370, 13, 60, 14);
		panel_1.add(lblNewLabel);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(381, 321, 116, 27);
		getContentPane().add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(vRegistrarVenta.class.getResource("/img/agregarCompra.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAgregar(e);
			}
		});
		btnAgregar.setFont(new Font("Verdana", Font.BOLD, 12));
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(vRegistrarVenta.class.getResource("/img/CancelarCompra.png")));
		btnEliminar.setBounds(525, 321, 111, 27);
		getContentPane().add(btnEliminar);
		btnEliminar.setFont(new Font("Verdana", Font.BOLD, 12));
		
		JLabel lblTotalAPagar = new JLabel("TOTAL A PAGAR");
		lblTotalAPagar.setBounds(418, 541, 122, 19);
		getContentPane().add(lblTotalAPagar);
		lblTotalAPagar.setFont(new Font("Verdana", Font.BOLD, 12));
		
		txtTotalPagar = new JTextField();
		txtTotalPagar.setBounds(550, 540, 86, 20);
		getContentPane().add(txtTotalPagar);
		txtTotalPagar.setForeground(Color.BLUE);
		txtTotalPagar.setFont(new Font("Verdana", Font.BOLD, 12));
		txtTotalPagar.setColumns(10);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(305, 540, 99, 27);
		getContentPane().add(btnCerrar);
		btnCerrar.setIcon(new ImageIcon(vRegistrarVenta.class.getResource("/img/cancelar.png")));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setFont(new Font("Verdana", Font.BOLD, 12));
		
		btnGenerarVenta = new JButton("Generar Venta");
		btnGenerarVenta.setBounds(10, 540, 153, 27);
		getContentPane().add(btnGenerarVenta);
		btnGenerarVenta.setIcon(new ImageIcon(vRegistrarVenta.class.getResource("/img/GenerarVenta.png")));
		btnGenerarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonGenerarVenta(e);
			}
		});
		btnGenerarVenta.setFont(new Font("Verdana", Font.BOLD, 12));
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(173, 540, 122, 27);
		getContentPane().add(btnImprimir);
		btnImprimir.setIcon(new ImageIcon(vRegistrarVenta.class.getResource("/img/imprimirN.png")));
		btnImprimir.setFont(new Font("Verdana", Font.BOLD, 12));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonImprimir(e);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonEliminar(e);
			}
			
		});


	}
	
	protected void BotonBuscarCliente(ActionEvent e) {
		eCliente ObjC = new eCliente();
		ObjC=ObjNC.Buscar(txtDniCli.getText());
		
		if(txtDniCli.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe ingresar dni cliente");
		}else {			
			if(ObjC !=null){
				txtCli.setText(ObjC.getNombres()+ObjC.getApellidos());				
			}else{	
				int r=JOptionPane.showConfirmDialog(this, "Cliente no encontrado, desea registrar?");
				if(r==0){
					vRegistrarCliente c= new vRegistrarCliente();
					MenuPrincipal.contentPane.add(c);
					c.setVisible(true);
				}
				txtDniCli.setText("");
				txtDniCli.requestFocus();
			}
		}	
	}
	
	protected void BotonBuscarProducto(ActionEvent e) {
		eProducto ObjP = new eProducto();
		ObjP=ObjNP.Buscar(txtIdP.getText());
		
		if(txtIdP.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe ingresar codigo de producto");
		}else{
			if(ObjP !=null){
				txtProd.setText(ObjP.getDescripcion());
				txtPrecioP.setText(""+ObjP.getPrecio());
				txtStock.setText(""+ObjP.getStock());
			}else{
				JOptionPane.showMessageDialog(null, "Producto no registrado");
				txtIdP.setText("");
				txtIdP.requestFocus();
			}
		}
	}
	
	//BOTON AGREGAR PRODUCTO 
	protected void BotonAgregar(ActionEvent e) {
		agregarProducto();
	}
	
	//METODO CARGAR PRODUCTO
	void agregarProducto(){
		eDetalleVenta ObjDV = new eDetalleVenta();
		eProducto ObjP = new eProducto();
		
		double total;
		int item=0;
		modelo=(DefaultTableModel)table.getModel();
		item=item+1;
		
		IdProd=txtIdP.getText();
		String nomProd=txtIdP.getText();
		preProd=Double.parseDouble(txtPrecioP.getText());
		cantProd=Integer.parseInt(txtCantidadP.getValue().toString());
		total=cantProd*preProd;
		int stock= Integer.parseInt(txtStock.getText());
					
		ArrayList listaP = new ArrayList();
		if(stock<=cantProd){	
			JOptionPane.showMessageDialog(null, "Stock no disponible");
						
		}else{
			listaP.add(item);		
			listaP.add(IdProd);
			listaP.add(nomProd);
			listaP.add(cantProd);
			listaP.add(preProd);
			listaP.add(total);
			Object[]ob=new Object[6];
			ob[0]=listaP.get(0);
			ob[1]=listaP.get(1);
			ob[2]=listaP.get(2);
			ob[3]=listaP.get(3);
			ob[4]=listaP.get(4);
			ob[5]=listaP.get(5);
			
			modelo.addRow(ob);
			table.setModel(modelo);
			calcularTotal();
			contar();
			
		}
	}
	//CONTAR
	void contar() {
		totalregistros= table.getRowCount();
		lblRegistros.setText("Cantidad "+Integer.toString(totalregistros));
	}
	
	
	//METODO CALCULAR TOTAL
	void calcularTotal(){
		Tpagar=0;
		for(int i = 0; i<table.getRowCount(); i++){
			cantProd=Integer.parseInt(table.getValueAt(i, 3).toString());
			preProd=Double.parseDouble(table.getValueAt(i,4).toString());
			Tpagar=Tpagar+(cantProd*preProd);
		}
		txtTotalPagar.setText(""+Tpagar);
	}
	
	//BOTON ELIMINAR
	protected void BotonEliminar(ActionEvent e) {

	}
	
	//BOTON CERRAR
	protected void BotonCancelar(ActionEvent e) {
		dispose();
	}
	
	//METODO PARA LIMPIAR DATOS DE LA TABLA
	void limpiarTabla(){
		for(int i=0; i<modelo.getRowCount(); i++){
			modelo.removeRow(i);
			i=i-1;
		}
	}
	
	//METODO PARA LIMPIAR LAS CAJAS DE TEXTO
	void limpiarCajas(){
		txtDniCli.setText("");
		txtCli.setText("");
		txtIdP.setText("");
		txtProd.setText("");
		txtStock.setText("");
		txtPrecioP.setText("");
		txtTotalPagar.setText("");
		txtCBO.setText("");
		txtDniCli.requestFocus();
	}
	
	//METODO PARA ACTULIZAR STOCK DE PRODUCTO
	void actualizarStock(){
		for (int i=0; i<modelo.getRowCount();i++){			
			IdProd=table.getValueAt(i, 1).toString();
			cantProd=Integer.parseInt(table.getValueAt(i, 3).toString());
			ObjP=ObjNP.Buscar(IdProd);
			int sAct=ObjP.getStock()-cantProd;
			ObjNP.actualizarStock(sAct, IdProd);
		}
	}
	
	//METODO PARA REGISTRAR UNA VENTA
	void guardarVenta(){
		eVenta ObjEV = new eVenta();

		String idvt=ObjV.GeneraCodigoVenta();
		String idc=txtDniCli.getText();
		String idv = txtIdV.getText();		
		String serie=txtCBO.getText();	
		String fecha=txtFecha.getText();
		double monto=Tpagar;
		String estado = "1";
		
		ObjEV.setIdVenta(idvt);
		ObjEV.setIdCliente(idc);
		ObjEV.setIdTrabajador(idv);
		ObjEV.setNumeroVenta(serie);
		ObjEV.setFechaVenta(fecha);
		ObjEV.setMonto(monto);
		ObjEV.setEstado(estado);
		dVenta ObjV = new dVenta();
		ObjV.guardarVenta(ObjEV);
	}
	
	void guardarDetalle(){		
		String idv = ObjV.IdVentas();

		for(int i=0; i<table.getRowCount(); i++){
			String idp = table.getValueAt(i, 1).toString();
			int cant = Integer.parseInt(table.getValueAt(i, 3).toString());
			double pre= Double.parseDouble(table.getValueAt(i, 4).toString());
			
			ObjNDV.setIdDetalleVentas(ObjV.GeneraCodigoDetalleVenta());
			ObjNDV.setIdVenta(idv);
			ObjNDV.setIdProducto(idp);
			ObjNDV.setCantidad(cant);
			ObjNDV.setPrecioVenta(pre);
			ObjV.guardarDetalleVenta(ObjNDV);
		}				
	}
	
	//BOTON GENERAR VENTA
	protected void BotonGenerarVenta(ActionEvent e) {
		if(txtTotalPagar.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Debe ingresar datos");
		}else{
			guardarVenta();
			guardarDetalle();
			actualizarStock();
			JOptionPane.showMessageDialog(null, "Registro existoso");		
			//limpiarTabla();
			//limpiarCajas();
			txtCBO.setText(ObjV.GenerarCodigoCBP());			
		}
	}
	
	//BOTON IMPRIMIR REPORTE
	protected void BotonImprimir(ActionEvent e) {
		//Variables para reporte
		
		Map p = new HashMap();
		p.put("cbo", txtCBO.getText());
		JasperReport report;
		JasperPrint print;
		
		try {
			//ruta proyecto
			report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
					"/src/reporte/rVenta.jrxml");
			print=JasperFillManager.fillReport(report, p, connection);
			//visualizar
			JasperViewer view = new JasperViewer(print,false);
			
			view.setTitle("Reporte de Venta");
			view.setVisible(true);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
