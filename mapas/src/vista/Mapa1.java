package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Mapa1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton aniadir, ver, actualizar, eliminar, salir;
	private Map<String, Double> notas = new HashMap<>();


	public Mapa1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		add(contentPane);
		setLayout(null);
		
		aniadir = new JButton("Añadir");
		aniadir.setBounds(10, 339, 101, 46);
		add(aniadir);
		aniadir.addActionListener(this);
		ver = new JButton("Ver");
		ver.setBounds(121, 339, 101, 46);
		add(ver);
		actualizar = new JButton("Actualizar");
		actualizar.setBounds(232, 339, 101, 46);
		add(actualizar);
		eliminar = new JButton("Eliminar");
		eliminar.setBounds(343, 339, 101, 46);
		add(eliminar);
		salir = new JButton("Salir");
		salir.setBounds(454, 339, 101, 46);
		add(salir);
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
