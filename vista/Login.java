package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datos.dTrabajador;
import entidad.eTrabajador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JTable miTabla;
	private JButton btnIngresar;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/User.png")));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 91, 116, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/Contrase\u00F1a.png")));
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 146, 127, 29);
		contentPane.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtUsuario.setBounds(136, 95, 103, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtClave.setBounds(136, 150, 103, 20);
		contentPane.add(txtClave);
		
		miTabla = new JTable();
		miTabla.setBounds(318, 214, 27, 14);
		contentPane.add(miTabla);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setIcon(new ImageIcon(Login.class.getResource("/img/Ingresar (1).png")));
		btnIngresar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedIngresar(e);
			}
		});
		btnIngresar.setBounds(90, 206, 119, 29);
		contentPane.add(btnIngresar);
		
		txtUsuario.setText("Admin");
		txtClave.setText("12345678");
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/img/login2020 (1).png")));
		lblNewLabel_2.setBounds(269, 58, 140, 149);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("INICIAR SESI\u00D3N");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_3.setBounds(74, 29, 168, 29);
		contentPane.add(lblNewLabel_3);
	}
	
	//BOTON INGRESAR
	protected void actionPerformedIngresar(ActionEvent e) {
		try {
			DefaultTableModel modelo;
			dTrabajador dt = new dTrabajador();
			eTrabajador et = new eTrabajador();
	
			et.setUsuario(txtUsuario.getText());
			et.setDni(txtClave.getText());
			
			modelo=dt.login(et.getUsuario(), et.getDni());
			miTabla.setModel(modelo);
			
			//Si tr > 1 = existe empieza
			if(dt.totalregistros >0) {
				this.dispose();
				MenuPrincipal mp = new MenuPrincipal();
				mp.toFront();
				mp.setVisible(true);
				
				mp.setLocationRelativeTo(null);
				
				MenuPrincipal.lblIdT.setText(miTabla.getValueAt(0, 0).toString());
				MenuPrincipal.lblAcceso.setText(miTabla.getValueAt(0, 5).toString());
				MenuPrincipal.lblNombre.setText(miTabla.getValueAt(0, 2).toString());
				MenuPrincipal.lblApellido.setText(miTabla.getValueAt(0, 3).toString());
				
				
				
				if(!MenuPrincipal.lblAcceso.getText().equals("Administrador")) {
					MenuPrincipal.mnMantenimiento.setEnabled(false);
					MenuPrincipal.mnConsultas.setEnabled(false);
				}
				
			}else {
				
				JOptionPane.showMessageDialog(rootPane, "Accesso Denegado","Acceso al Siste",JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception ex) {
			
		}
	}
}
