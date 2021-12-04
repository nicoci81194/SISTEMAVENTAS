package vista;

import negocio.nCliente;
import entidad.eCliente;
import entidad.eProducto;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.ImageIcon;

public class vRegistrarCliente extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	nCliente ObjNC = new nCliente();
	int totalregistros = 0;
	
	private JPanel panel;
	private JTextField txtIdC;
	private JTextField txtDni;
	private JTextField txtNom;
	private JTextField txtApe;
	private JTextField txtDireccion;
	private JTextField txtCelular;
	private JComboBox cboEstado;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnActualizar;
	private JPanel panel_1;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JTable table;
	private JLabel lblRegistros;
	private JButton btnSalir;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vRegistrarCliente frame = new vRegistrarCliente();
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
	String Columnas[] = {"IdCliente","Dni","Nombres","Apellidos","Telefono","Direccion","Estado"};
	Object Filas[][];
	ArrayList<eCliente>miLista;
	
	//METODO PARA CARGAR EL CONTROL JTABLE
	private void CargarTabla() {
		tabla = new DefaultTableModel();
		miLista = new ArrayList<eCliente>();
		miLista = ObjNC.Listar();
		Filas = new Object[miLista.size()][7];
		for(int i = 0; i < Columnas.length; i++)tabla.addColumn(Columnas[i]);
		for(int i = 0; i < miLista.size(); i++) {
			Filas[i][0] = miLista.get(i).getIdCliente();			
			Filas[i][1] = miLista.get(i).getDni();
			Filas[i][2] = miLista.get(i).getNombres();
			Filas[i][3] = miLista.get(i).getApellidos();
			Filas[i][4] = miLista.get(i).getTelefono();
			Filas[i][5] = miLista.get(i).getDireccion();
			Filas[i][6] = miLista.get(i).getEstado();
			tabla.addRow(Filas[i]);
		}
		table.setModel(tabla);
	}	
	
	//METODO CONTAR
	public void contar() {
		totalregistros= tabla.getRowCount();
		lblRegistros.setText("Registros "+Integer.toString(totalregistros));
	}
	
	//METODO LIMPIAR CAJAS
	private void LimpiarCajas() {
		txtIdC.setText("");
		txtDni.setText("");
		txtNom.setText("");
		txtApe.setText("");
		txtCelular.setText("");
		txtDireccion.setText("");
		txtIdC.requestFocus();
		cboEstado.setSelectedItem("Seleccionar");
	}
	
	public vRegistrarCliente() {
		setTitle("Registro");
		getContentPane().setBackground(Color.WHITE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 845, 449);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO CLIENTE");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 178, 17);
		getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(10, 36, 318, 360);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DATOS");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 11, 160, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IdCliente");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 36, 76, 14);
		panel.add(lblNewLabel_2);
		
		txtIdC = new JTextField();
		txtIdC.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtIdC.setBounds(96, 33, 90, 20);
		panel.add(txtIdC);
		txtIdC.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDni.setBounds(96, 64, 90, 20);
		panel.add(txtDni);
		txtDni.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNom.setBounds(96, 95, 212, 20);
		panel.add(txtNom);
		txtNom.setColumns(10);
		
		txtApe = new JTextField();
		txtApe.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtApe.setBounds(96, 126, 212, 20);
		panel.add(txtApe);
		txtApe.setColumns(10);
		
		cboEstado = new JComboBox();
		cboEstado.setFont(new Font("Verdana", Font.PLAIN, 12));
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "1", "2"}));
		cboEstado.setBounds(96, 219, 99, 22);
		panel.add(cboEstado);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDireccion.setBounds(96, 157, 212, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCelular.setBounds(96, 188, 99, 20);
		panel.add(txtCelular);
		txtCelular.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(vRegistrarCliente.class.getResource("/img/agregar.png")));
		btnAgregar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAgregar(e);
				
			}
		});
		btnAgregar.setBounds(106, 252, 129, 27);
		panel.add(btnAgregar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(vRegistrarCliente.class.getResource("/img/aceptarN.png")));
		btnAceptar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAceptar(e);
			}
		});
		btnAceptar.setBounds(194, 32, 114, 27);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(vRegistrarCliente.class.getResource("/img/cancelarN.png")));
		btnCancelar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCancelar.setBounds(106, 322, 129, 27);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonCancelar(e);
			}
		});
		panel.add(btnCancelar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(vRegistrarCliente.class.getResource("/img/modificar.png")));
		btnActualizar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnActualizar.setBounds(106, 288, 129, 27);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonActualizar(e);
			}
		});
		panel.add(btnActualizar);
		
		JLabel lblNewLabel_3 = new JLabel("Dni");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 67, 76, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nombres");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 98, 76, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Apellidos");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 129, 76, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Direcci\u00F3n");
		lblNewLabel_6.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 160, 76, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Celular");
		lblNewLabel_7.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 191, 76, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Estado");
		lblNewLabel_8.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 223, 46, 14);
		panel.add(lblNewLabel_8);
		
		panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		panel_1.setBounds(338, 36, 481, 360);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("LISTADO CLIENTES");
		lblNewLabel_9.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_9.setBounds(10, 11, 160, 15);
		panel_1.add(lblNewLabel_9);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtBuscar.setBounds(92, 37, 128, 20);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Buscar");
		lblNewLabel_10.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_10.setBounds(10, 39, 46, 14);
		panel_1.add(lblNewLabel_10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(vRegistrarCliente.class.getResource("/img/buscarCliente.png")));
		btnBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonBuscar(e);
			}
		});
		btnBuscar.setBounds(230, 30, 107, 27);
		panel_1.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(vRegistrarCliente.class.getResource("/img/suprimir.png")));
		btnEliminar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonEliminar(e);
			}
		});
		btnEliminar.setBounds(347, 30, 112, 27);
		panel_1.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 449, 240);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMiTabla(e);
			}
		});
		scrollPane.setViewportView(table);
		
		lblRegistros = new JLabel("Registros");
		lblRegistros.setFont(new Font("Verdana", Font.BOLD, 12));
		lblRegistros.setBounds(10, 320, 156, 14);
		panel_1.add(lblRegistros);
		
		btnSalir = new JButton("Cerrar");
		btnSalir.setIcon(new ImageIcon(vRegistrarCliente.class.getResource("/img/cerrarN.png")));
		btnSalir.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonSalir(e);
			}
		});
		btnSalir.setBounds(360, 315, 99, 27);
		panel_1.add(btnSalir);
		
		//CARGAR TABLA
		CargarTabla();
		//CONTAR REGISTROS DE LA TABLA
		contar();
		//BOTON ACEPTAR EMPIZA EN FALSE
		btnAceptar.setEnabled(false);
	}
	
	//BOTON AGREGAR
	protected void BotonAgregar(ActionEvent e) {
		txtIdC.setText(ObjNC.GeneraCodigoCliente());
		txtIdC.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnAceptar.setEnabled(true);
	}
	
	//BOTON ACEPTAR
	protected void BotonAceptar(ActionEvent e) {
		if(btnAgregar.isEnabled()==false){
			String id=txtIdC.getText();
			String dn=txtDni.getText();
			String no=txtNom.getText();
			String ap=txtApe.getText();
			String te=txtCelular.getText();
			String di=txtDireccion.getText();
			String est=cboEstado.getSelectedItem().toString();
			
			eCliente ObjC = new eCliente(id,dn,no,ap,te,di,est);
			ObjNC.Insertar(ObjC);
			CargarTabla();
			LimpiarCajas();
			contar();
			JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
			btnAgregar.setEnabled(true);
			txtIdC.setEnabled(true);
			btnAceptar.setEnabled(false);
			}
			
			if(btnActualizar.isEnabled()==false){
				eCliente ObjC = new eCliente(txtIdC.getText(),
						txtDni.getText(),
						txtNom.getText(),
						txtApe.getText(),
						txtCelular.getText(),
						txtDireccion.getText(),
						cboEstado.getSelectedItem().toString()
						);
				ObjNC.Modificar(ObjC);
				LimpiarCajas();
				CargarTabla();
				JOptionPane.showMessageDialog(null, "SE MODIFICO CLIENTE");				
				btnActualizar.setEnabled(true);
				txtIdC.setEnabled(true);
				btnAceptar.setEnabled(false);
			}
	}
	
	//BOTON ACTUALIZAR
	protected void BotonActualizar(ActionEvent e) {
		int fila=table.getSelectedRow();
		
		if(fila==-1){
			
			JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
			txtIdC.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnAceptar.setEnabled(true);
		}	
	}
	
	//BOTON CANCELAR
	protected void BotonCancelar(ActionEvent e) {
		btnAceptar.setEnabled(false);
		btnActualizar.setEnabled(true);
		btnAgregar.setEnabled(true);
		txtIdC.setEnabled(true);
		LimpiarCajas();

		JOptionPane.showMessageDialog(null, "SE CANCECLO ACCION");
	}
	
	// NO BUSCA 
	protected void BotonBuscar(ActionEvent e) {
		eCliente ObjC = new eCliente();
		ObjC = ObjNC.Buscar(txtBuscar.getText());

		if(ObjC !=null){
			txtIdC.setText(ObjC.getIdCliente());
			txtDni.setText(ObjC.getDni());
			txtNom.setText(ObjC.getNombres()+"");
			txtApe.setText(ObjC.getApellidos()+"");
			txtCelular.setText(ObjC.getTelefono()+"");	
			txtDireccion.setText(ObjC.getDireccion());
				
		}else{
			JOptionPane.showMessageDialog(null, "ERROR, CODIGO CLIENTE NO EXISTE");
		}
	}
	
	//BOTON ELIMINAR 
	protected void BotonEliminar(ActionEvent e) {
		int fila=table.getSelectedRow();
		if(fila==-1){
			JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
		}else{
			ObjNC.Suprimir(txtIdC.getText());
			LimpiarCajas();

			JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO CORRECTAMENTE");
		}
	}
	
	//BOTON CERRAR
	protected void BotonSalir(ActionEvent e) {
		dispose();
	}
	
	// METODO MOUSE CLICKED EN MI TABLA
	protected void mouseClickedMiTabla(MouseEvent e) {
		int f = table.getSelectedRow();
		txtIdC.setText(table.getValueAt(f, 0).toString());
		txtDni.setText(table.getValueAt(f, 1).toString());
		txtNom.setText(table.getValueAt(f, 2).toString());
		txtApe.setText(table.getValueAt(f, 3).toString());
		txtCelular.setText(table.getValueAt(f, 4).toString());
		txtDireccion.setText(table.getValueAt(f, 5).toString());
				
	}	

}
