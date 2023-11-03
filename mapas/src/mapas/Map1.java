package mapas;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Map1 {

	private JFrame frame;
	private Map<String, Double> notas = new HashMap<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map1 window = new Map1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Map1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAniadir = new JButton("Añadir");
		btnAniadir.setBounds(10, 339, 101, 46);
		frame.getContentPane().add(btnAniadir);
		
		JButton btnVer = new JButton("Ver");
		btnVer.setBounds(121, 339, 101, 46);
		frame.getContentPane().add(btnVer);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(232, 339, 101, 46);
		frame.getContentPane().add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(343, 339, 101, 46);
		frame.getContentPane().add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(454, 339, 101, 46);
		frame.getContentPane().add(btnSalir);
	}
}
