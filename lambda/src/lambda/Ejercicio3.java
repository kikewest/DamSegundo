package lambda;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Ejercicio3 {
	public static void main(String[] args) {
		try {// estilo del frame
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.put("Panel.background", Color.WHITE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame("Fecha Actual");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 200);
		frame.setLocationRelativeTo(null);
		JPanel pane = new JPanel();
		pane.setLayout(null);

		JButton boton = new JButton("Mostrar Fecha");
		boton.setBounds(10, 55, 120, 46);
		frame.add(pane);
		pane.add(boton);

		JLabel textField = new JLabel(); // Campo de texto para mostrar la hora
		textField.setBounds(150, 53, 700, 50);
		pane.add(textField);

		JFrame.setDefaultLookAndFeelDecorated(true);
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener la fecha y hora actual
				LocalDateTime fechaActual = LocalDateTime.now();
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("'Las' HH:mm:ss 'del' dd 'de' MMMM 'del' yyyy");
				String fechaFormateada = fechaActual.format(formato);
				textField.setText(fechaFormateada);
			}
		});

		boton.addActionListener(e -> {
			timer.start();
		});

		frame.setVisible(true);
	}
}
