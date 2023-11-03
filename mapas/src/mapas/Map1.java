package mapas;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		frame.setBounds(100, 100, 580, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAniadir = new JButton("Añadir");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog(frame, "Por favor introduzca el nombre del alumno:");
				if (nombre != null && !nombre.isEmpty()) {
					String nota = JOptionPane.showInputDialog(frame, "Por favor introduzca la nota del alumno");
					try {
						double notad = Double.parseDouble(nota);
						notas.put(nombre, notad);
						JOptionPane.showMessageDialog(frame, "Alumno añadido con éxito.");
					}catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frame, "Error: Ingrese una nota válida (número)."+e);
					}
				}
			}
		});
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
