package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JProgressBar;

public class Bienvenidos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPorcentaje;
	private JProgressBar JPbarra;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenidos frame = new Bienvenidos();
					frame.setVisible(true);
					//CENTRADO
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int seg;
	
	Thread tiempo = new Thread() {
		public void run() {
			try {
				for(seg=1; seg<=100; seg++) {
					JPbarra.setValue(seg);
					lblPorcentaje.setText(""+seg+"%CARGANDO");
					tiempo.sleep(20);
					
					if(seg==100) {
						JOptionPane.showMessageDialog(null, "Terminó el proceso","Mensaje", JOptionPane.INFORMATION_MESSAGE);
						Login p = new Login();
						p.setLocationRelativeTo(null);
						p.setVisible(true);
						dispose();
					}
				}
		
			}catch(Exception e) {
				System.out.println("ERROR TIEMPO:" + e.getMessage());
			}
		}
	};
	
	
	public Bienvenidos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Bienvenidos.class.getResource("/img/logo100p.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 407);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Bienvenidos.class.getResource("/img/logo100p.jpg")));
		lblNewLabel.setBounds(139, 147, 100, 115);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BIENVENIDO");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 34));
		lblNewLabel_1.setBounds(59, 32, 255, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A LA JUGUETERIA");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 23));
		lblNewLabel_2.setBounds(73, 87, 234, 33);
		contentPane.add(lblNewLabel_2);
		
		JPbarra = new JProgressBar();
		JPbarra.setForeground(new Color(112, 128, 144));
		JPbarra.setBounds(28, 282, 310, 33);
		contentPane.add(JPbarra);
		
		lblPorcentaje = new JLabel("New label");
		lblPorcentaje.setFont(new Font("Verdana", Font.BOLD, 15));
		lblPorcentaje.setBounds(115, 326, 149, 14);
		contentPane.add(lblPorcentaje);
		
		tiempo.start();
	}
}
