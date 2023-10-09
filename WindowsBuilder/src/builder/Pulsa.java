package builder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Pulsa {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pulsa window = new Pulsa();
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
	public Pulsa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 300, 400, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("EVENTOS");
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(Color.GREEN);
		textField.setBackground(new Color(0, 0, 0));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla !='1' && tecla !='2' && tecla !='3') {
					e.consume();
				}
			}
		});
		textField.setBounds(10, 50, 364, 63);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("EVENTOS");
		lblNewLabel.setBounds(10, 11, 364, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("PULSA 1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"1");
				textField.requestFocusInWindow();
			}
		});
		btnNewButton.setBounds(10, 166, 364, 56);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("PULSA 2");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"2");
				textField.requestFocusInWindow();
			}
		});
		btnNewButton_1.setBounds(10, 233, 364, 56);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("PULSA 3");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"3");
				textField.requestFocusInWindow();
			}
		});
		btnNewButton_2.setBounds(10, 300, 364, 56);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("LIMPIAR");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField.requestFocusInWindow();
			}
		});
		btnNewButton_3.setBounds(10, 367, 364, 56);
		panel.add(btnNewButton_3);
		
		
	}
}
