package builder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;


public class PruebaInterfaz {

	private JFrame frmAdivinaElNumer;
	private JTextField textField;
	private Random random = new Random();
	private int num = random.nextInt(101),intentos=7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaInterfaz window = new PruebaInterfaz();
					window.frmAdivinaElNumer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public PruebaInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdivinaElNumer = new JFrame();
		frmAdivinaElNumer.setTitle("Adivina el numeró!!");
		frmAdivinaElNumer.setBounds(700, 300, 450, 400);
		frmAdivinaElNumer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton Breiniciar = new JButton("Reiniciar");
		Breiniciar.setBounds(160, 290, 109, 26);
		JButton Badivinar =new JButton("Adivinar");
		Badivinar.setBounds(160, 253, 109, 26);
		JPanel panel = new JPanel();
		Breiniciar.setEnabled(true);
		Badivinar.setVisible(true);
		Breiniciar.setVisible(false);
		Badivinar.setEnabled(true);
		JLabel lblTexto = new JLabel("Escribe el numero que crees que es, entre 0 y 100! :");
		lblTexto.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblTexto.setBounds(10, 32, 414, 37);
		panel.add(lblTexto);
		textField= new JTextField();
		textField.setBounds(10, 80, 414, 37);
		panel.add(textField);
		textField.setColumns(10);
		JLabel lblResultado = new JLabel("");
		lblResultado.setOpaque(true);
		lblResultado.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblResultado.setBounds(10, 128, 414, 37);
		panel.add(lblResultado);
		JLabel lblResultado_1 = new JLabel("");
		lblResultado_1.setOpaque(true);
		lblResultado_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblResultado_1.setBounds(10, 190, 414, 37);
		panel.add(lblResultado_1);
		Badivinar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.print(num);
				int numero= Integer.parseInt(textField.getText());
				textField.requestFocusInWindow();

				if (numero<num & intentos>0) {
					intentos=intentos-1;
					lblResultado.setForeground(Color.red);
					lblResultado.setBackground(Color.black);
					lblResultado.setText("Lo siento Su numero es menor, Intentelo de nuevo");
					textField.setText(null);
					
				}
				if (numero>num & intentos>0) {
					intentos=intentos-1;
					lblResultado.setForeground(Color.red);
					lblResultado.setBackground(Color.black);
					lblResultado.setText("Lo siento Su numero es mayor, Intentelo de nuevo");
					textField.setText(null);
				}
				if (numero==num & intentos>0) {
					lblResultado.setForeground(Color.green);
					lblResultado.setBackground(Color.black);
					lblResultado.setText("Enorabuena ereh un crah! "+numero+" Es el numerito");
					Badivinar.setText(":D");
					Badivinar.setEnabled(false);
					Breiniciar.setVisible(true);
					lblResultado_1.setText("Ganaste!!");
					lblResultado_1.setBackground(Color.green);
				}
				if (intentos==0) {
					Badivinar.setEnabled(false);
					Breiniciar.setVisible(true);
					lblTexto.setText("Te has quedado sin intentos paquete");
					lblResultado_1.setText("Que malo eres cacho de trozo de carne");
				}
				if (numero!=num) {
					lblResultado_1.setText("Le quedan :"+intentos+" intentos.");
				}
				if (intentos<4 & numero!=num) {
					lblResultado_1.setForeground(Color.black);
					lblResultado_1.setBackground(Color.red);
				}
			}
		});



		Breiniciar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				lblResultado.setForeground(null);
				lblResultado.setBackground(null);
				lblResultado_1.setBackground(null);
				lblResultado_1.setForeground(null);
				lblResultado_1.setText(null);
				lblTexto.setText("Escribe el numero que crees que es, entre 0 y 100! :");
				lblResultado.setText("");
				intentos=7;
				textField.setText(null);
				num = random.nextInt(101);
				Badivinar.setText("Adivinar");
				Badivinar.setEnabled(true);
				Breiniciar.setVisible(false);
				textField.requestFocusInWindow();

			}
		});

		frmAdivinaElNumer.getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(Badivinar);
		panel.add(Breiniciar);
	}
}
