package builder;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ventanas {

	private JFrame frmVentana;
	private JFrame frmVentana2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventanas window = new Ventanas();
					window.frmVentana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventanas() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVentana = new JFrame();
		frmVentana.setBounds(100, 100, 456, 245);
		frmVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentana.setTitle("Ventana1");
		frmVentana.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pulsa para abrir otra ventana");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 36, 414, 37);
		frmVentana.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Abrir Ventana");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialize2();
				frmVentana2.setVisible(true);
				frmVentana.setEnabled(false);
			}
		});
		btnNewButton.setBounds(116, 99, 210, 46);
		frmVentana.getContentPane().add(btnNewButton);
		
	}
	private void initialize2() {
		frmVentana2 = new JFrame();
		frmVentana2.setBounds(300, 300, 456, 245);
		frmVentana2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVentana2.setTitle("Ventana2");
		frmVentana2.getContentPane().setLayout(null);
		frmVentana2.setVisible(true);
		frmVentana2.addWindowListener(new WindowAdapter() { /*he tenido que añadir el windowlistener con el metodo windowClosing para
															que no se cierren las dos ventanas al pulsar la "x" de la segunda ventana*/
		    public void windowClosing(WindowEvent e) {
		        frmVentana.setEnabled(true); // Habilita la primera ventana
		        e.getWindow().dispose(); // Cierra la segunda ventana
		    }
		});
		JLabel lblNewLabel2 = new JLabel("Pulsa para cerrar esta ventana");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel2.setBounds(10, 36, 414, 37);
		frmVentana2.getContentPane().add(lblNewLabel2);
		
		JButton btnNewButton2 = new JButton("Cerrar Ventana");
		btnNewButton2.setBounds(116, 99, 210, 46);
		frmVentana2.getContentPane().add(btnNewButton2);
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmVentana.setEnabled(true);
				frmVentana2.setVisible(false);
			}
		});
	}
}
