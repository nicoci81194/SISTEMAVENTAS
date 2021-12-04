package vista;

import negocio.nTrabajador;
import entidad.eTrabajador;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class mTrabajador extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	nTrabajador ObjNT = new nTrabajador();
	int totalregistros=0;
	
	private JTextField txtUser;
	private JTextField txtApellidos;
	private JTextField txtNombres;
	private JTextField txtDni;
	private JTextField txtId;
	private JTextField txtCell;
	private JComboBox cboAcceso;
	private JComboBox cboEstado;
	private JButton btnAgregar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnActualizar;
	private JPanel panel_1;
	private JLabel lblNewLabel_10;
	private JTextField txtBuscar;
	private JLabel lblNewLabel_11;
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JLabel lblRegistros;
	private JButton btnSalir;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mTrabajador frame = new mTrabajador();
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
	String Columnas[] = {"IdTrabajador","Dni","Nombres","Apellidos","Celular","Acceso","Usuario","Estado"};
	Object Filas[][];
	ArrayList<eTrabajador> miLista;	
	
	//METODO PARA CARGAR EL CONTROL JTABLE
	private void CargarTabla() {
		tabla = new DefaultTableModel();
		miLista = new ArrayList<eTrabajador>();
		miLista = ObjNT.Listar();
		Filas = new Object[miLista.size()][8];
		for(int i = 0; i < Columnas.length; i++)tabla.addColumn(Columnas[i]);
		for(int i = 0; i < miLista.size(); i++) {
			Filas[i][0] = miLista.get(i).getIdTrabajador();
			Filas[i][1] = miLista.get(i).getDni();
			Filas[i][2] = miLista.get(i).getNombres();
			Filas[i][3] = miLista.get(i).getApellidos();
			Filas[i][4] = miLista.get(i).getCelular();				
			Filas[i][5] = miLista.get(i).getAcceso();
			Filas[i][6] = miLista.get(i).getUsuario();
			Filas[i][7] = miLista.get(i).getEstado();
			tabla.addRow(Filas[i]);
		}
		table.setModel(tabla);
	}
	
	//CONTRAR REGISTROS
	public void contar() {
		totalregistros= tabla.getRowCount();
		lblRegistros.setText("Registros "+Integer.toString(totalregistros));
	}
	
	//METODO LIMPIAR CAJAS
	private void LimpiarCajas() {
		txtId.setText("");
		txtDni.setText("");
		txtNombres.setText("");
		txtApellidos.setText("");
		txtCell.setText("");
		txtUser.setText("");
		txtId.requestFocus();
		cboAcceso.setSelectedItem("Seleccionar");
		cboEstado.setSelectedItem("Seleccionar");
	}
	
	
	public mTrabajador() {
		getContentPane().setBackground(Color.WHITE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 885, 495);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANTENIMIENTO TRABAJADORES");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 296, 16);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 36, 345, 416);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DATOS");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 11, 89, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IdTrabajador");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 37, 89, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Dni");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 67, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nombres");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 98, 89, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Apellidos");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 129, 89, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Celular");
		lblNewLabel_7.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 160, 89, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Estado");
		lblNewLabel_8.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 256, 46, 14);
		panel.add(lblNewLabel_8);
		
		cboEstado = new JComboBox();
		cboEstado.setFont(new Font("Verdana", Font.PLAIN, 12));
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "1", "2"}));
		cboEstado.setBounds(109, 248, 120, 22);
		panel.add(cboEstado);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtUser.setColumns(10);
		txtUser.setBounds(109, 191, 107, 20);
		panel.add(txtUser);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(109, 129, 224, 20);
		panel.add(txtApellidos);
		
		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombres.setColumns(10);
		txtNombres.setBounds(109, 98, 224, 20);
		panel.add(txtNombres);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDni.setColumns(10);
		txtDni.setBounds(109, 67, 107, 20);
		panel.add(txtDni);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtId.setColumns(10);
		txtId.setBounds(109, 36, 107, 20);
		panel.add(txtId);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(mTrabajador.class.getResource("/img/agregar.png")));
		btnAgregar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAgregar(e);
			}
		});
		btnAgregar.setBounds(109, 301, 122, 27);
		panel.add(btnAgregar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(mTrabajador.class.getResource("/img/aceptarN.png")));
		btnAceptar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAceptar(e);
			}
		});
		btnAceptar.setBounds(226, 34, 107, 27);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(mTrabajador.class.getResource("/img/cancelarN.png")));
		btnCancelar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonCancelar(e);
			}
		});
		btnCancelar.setBounds(109, 370, 122, 27);
		panel.add(btnCancelar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(mTrabajador.class.getResource("/img/modificar.png")));
		btnActualizar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnActualizar.setBackground(Color.WHITE);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonActualizar(e);
			}
		});
		btnActualizar.setBounds(109, 335, 122, 27);
		panel.add(btnActualizar);
		
		txtCell = new JTextField();
		txtCell.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCell.setBounds(109, 160, 107, 20);
		panel.add(txtCell);
		txtCell.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Usuario");
		lblNewLabel_6.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 191, 89, 14);
		panel.add(lblNewLabel_6);
		
		cboAcceso = new JComboBox();
		cboAcceso.setFont(new Font("Verdana", Font.PLAIN, 12));
		cboAcceso.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Administrador", "Vendedor"}));
		cboAcceso.setBounds(109, 219, 120, 22);
		panel.add(cboAcceso);
		
		JLabel lblNewLabel_9 = new JLabel("Acceso");
		lblNewLabel_9.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_9.setBounds(10, 223, 46, 14);
		panel.add(lblNewLabel_9);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(365, 36, 494, 416);
		getContentPane().add(panel_1);
		
		lblNewLabel_10 = new JLabel("LISTADO TRABAJADORES");
		lblNewLabel_10.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_10.setBounds(10, 10, 202, 15);
		panel_1.add(lblNewLabel_10);
		
		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(69, 37, 128, 20);
		panel_1.add(txtBuscar);
		
		lblNewLabel_11 = new JLabel("Buscar");
		lblNewLabel_11.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_11.setBounds(10, 39, 46, 14);
		panel_1.add(lblNewLabel_11);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(mTrabajador.class.getResource("/img/buscarCliente.png")));
		btnBuscar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonBuscar(e);
			}
		});
		btnBuscar.setBounds(243, 30, 108, 27);
		panel_1.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(mTrabajador.class.getResource("/img/suprimir.png")));
		btnEliminar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonEliminar(e);
			}
		});
		btnEliminar.setBounds(361, 30, 123, 27);
		panel_1.add(btnEliminar);
		
		lblRegistros = new JLabel("Registros");
		lblRegistros.setFont(new Font("Verdana", Font.BOLD, 12));
		lblRegistros.setBounds(10, 355, 156, 14);
		panel_1.add(lblRegistros);
		
		btnSalir = new JButton("Cerrar");
		btnSalir.setIcon(new ImageIcon(mTrabajador.class.getResource("/img/cerrarN.png")));
		btnSalir.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonSalir(e);
			}
		});
		btnSalir.setBounds(385, 349, 99, 27);
		panel_1.add(btnSalir);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 474, 274);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMiTabla(e);
			}
		});
		scrollPane.setViewportView(table);
		
		//CARGAR LA TABLA
		CargarTabla();
		//CONTAR REGISTROS DE LA TABLA
		contar();
		//BOTON ACEPTAR EMPIZA EN FALSE
		btnAceptar.setEnabled(false);

	}
	
	//BOTON AGREGAR
	protected void BotonAgregar(ActionEvent e) {
		txtId.setText(ObjNT.GeneraCodigoVendedor());
		txtId.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnAceptar.setEnabled(true);
	}
	
	//BOTON ACEPTAR
	protected void BotonAceptar(ActionEvent e) {
		if(btnAgregar.isEnabled()==false){
			String id=txtId.getText();
			String dn=txtDni.getText();
			String no=txtNombres.getText();
			String ap=txtApellidos.getText();
			String te=txtCell.getText();
			String acc=cboAcceso.getSelectedItem().toString();
			String usu=txtUser.getText();
			String est=cboEstado.getSelectedItem().toString();
			
			eTrabajador ObjT = new eTrabajador(id,dn,no,ap,te,acc,usu,est);
			ObjNT.Insertar(ObjT);
			CargarTabla();
			contar();
			LimpiarCajas();
			JOptionPane.showMessageDialog(null, "REGISTRO VENDEDOR EXITOSO");
			
			btnAgregar.setEnabled(true);
			txtId.setEnabled(true);
			btnAceptar.setEnabled(false);
			}
			
			if(btnActualizar.isEnabled()==false){
				eTrabajador ObjT = new eTrabajador(txtId.getText(),
						txtDni.getText(),
						txtNombres.getText(),
						txtApellidos.getText(),
						txtCell.getText(),
						cboAcceso.getSelectedItem().toString(),
						txtUser.getText(),
						cboEstado.getSelectedItem().toString()
						);
				ObjNT.Modificar(ObjT);
				LimpiarCajas();
				CargarTabla();	
				JOptionPane.showMessageDialog(null, "SE MODIFICO VENDEDOR");
				
				btnActualizar.setEnabled(true);
				txtId.setEnabled(true);
				btnAceptar.setEnabled(false);
			}
	}
	
	//BOTON ACTUALIZAR
	protected void BotonActualizar(ActionEvent e) {
		int fila=table.getSelectedRow();
		
		if(fila==-1){
			
			JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
			txtId.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnAceptar.setEnabled(true);
		}
	}
	
	//BOTON CANCELAR
	protected void BotonCancelar(ActionEvent e) {
		btnAceptar.setEnabled(false);
		btnActualizar.setEnabled(true);
		btnAgregar.setEnabled(true);
		txtId.setEnabled(true);
		LimpiarCajas();
		JOptionPane.showMessageDialog(null, "SE CANCECLO ACCION");
	}
	
	//BOTON BUSCAR
	protected void BotonBuscar(ActionEvent e) {
		eTrabajador ObjV = new eTrabajador();
		ObjV = ObjNT.Buscar(txtBuscar.getText());

		if(ObjV !=null){
			txtId.setText(ObjV.getIdTrabajador());
			txtDni.setText(ObjV.getDni());
			txtNombres.setText(ObjV.getNombres()+"");
			txtApellidos.setText(ObjV.getApellidos()+"");
			txtCell.setText(ObjV.getCelular()+"");	
			txtUser.setText(ObjV.getUsuario());
				
		}else{
			JOptionPane.showMessageDialog(null, "ERROR, CODIGO VENDEDOR NO EXISTE");
		}
	}
	
	//BOTON ELIMINAR
	protected void BotonEliminar(ActionEvent e) {
		int fila=table.getSelectedRow();
		if(fila==-1){
			JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
		}else{
			ObjNT.Suprimir(txtId.getText());
			LimpiarCajas();
			CargarTabla();
			JOptionPane.showMessageDialog(null, "TRABAJADOR ELIMINADO CORRECTAMENTE");
		}
	}
	
	
	//BOTON CERRAR
	protected void BotonSalir(ActionEvent e) {
		dispose();
	}
	
	// METODO MOUSE CLICKED EN MI TABLA
	protected void mouseClickedMiTabla(MouseEvent e) {
		int f = table.getSelectedRow();
		txtId.setText(table.getValueAt(f, 0).toString());
		txtDni.setText(table.getValueAt(f, 1).toString());
		txtNombres.setText(table.getValueAt(f, 2).toString());
		txtApellidos.setText(table.getValueAt(f, 3).toString());
		txtCell.setText(table.getValueAt(f, 4).toString());
		txtUser.setText(table.getValueAt(f, 6).toString());					
	}

}
