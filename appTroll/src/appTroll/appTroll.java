package appTroll;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class appTroll {

	private JFrame frame;
	private JButton boton;
	private JButton boton1;
	private JButton boton2;
	private Robot robot;
	private boolean punteroAtrapado = false;
//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					appTroll window = new appTroll();
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
	public appTroll() {
		try {
			robot = new Robot();
		}catch (AWTException e) {
			e.printStackTrace();
		}
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Hola Pedro");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		boton = new JButton("Bien");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "https://www.youtube.com/shorts/16uJ-jxcKHo";
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.browse(new URI(url));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				for (int i = 0; i < 100; i++) {
					abrirVentanaAleatoria(i);
				}
			}
		});
		boton.setBounds(142, 113, 150, 30);
		frame.getContentPane().add(boton);
		boton1= new JButton("Normal");
		boton1.setBounds(142, 154, 150, 30);
		frame.getContentPane().add(boton1);
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirNuevaVentana();
			}
		});
		boton2= new JButton("Mal");
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMensajePopup();
                // Esperar 3 segundos antes de ejecutar el bucle
                Timer timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        abrir20Pestanas();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
    
		boton2.setBounds(142, 195, 150, 30);
		frame.getContentPane().add(boton2);

		JLabel lblNewLabel = new JLabel("¿Como estas hoy?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(114, 48, 201, 30);
		frame.getContentPane().add(lblNewLabel);


	}
	private void abrirVentanaAleatoria(int index) {
		int x = (int) (Math.random() * 800);
		int y = (int) (Math.random() * 600); 
		JFrame ventana = new JFrame();
		ventana.setBounds(x, y, 450, 300);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setVisible(true);
		if (index == 99) {
			JButton botonMondongo = new JButton("mondongo");
			botonMondongo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			botonMondongo.setBounds(10, 10, 200, 50);
			ventana.getContentPane().add(botonMondongo);
			botonMondongo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					;
					String url = "https://www.youtube.com/shorts/16uJ-jxcKHo";
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.browse(new URI(url));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					abrirVentanaAleatoria(index);
				}
			});
		}
	}
	private void abrirNuevaVentana() {
		JDialog dialog = new JDialog();
		dialog.setSize(400, 200);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.getContentPane().setLayout(new BorderLayout());

		JTextArea textArea = new JTextArea("Texto en la nueva ventana");
		dialog.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

		dialog.setUndecorated(true); 
		dialog.setModal(true);
		dialog.setVisible(true);

		// no consigo que el puntero del raton se quede atrapado :(
		while (dialog.isDisplayable()) {
			if (punteroAtrapado) {
				int centerX = dialog.getX() + textArea.getX() + textArea.getWidth() / 2;
				int centerY = dialog.getY() + textArea.getY() + textArea.getHeight() / 2;
				robot.mouseMove(centerX, centerY);
			}
		}
	}
	private void mostrarMensajePopup() {
        String mensaje = "Esto va a afectar a tu memoria RAM.";
        JOptionPane.showMessageDialog(frame, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    private void abrir20Pestanas() {
        String url = "https://www.youtube.com/shorts/16uJ-jxcKHo";
        for (int i = 0; i < 20; i++) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(url));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
