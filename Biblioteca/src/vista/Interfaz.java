package vista;

import controlador.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Clip audioClip;
	private boolean isPlaying = false; 
	private JButton btnMusica;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
					Metodos.verificarYCrearXML();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				ImageIcon backgroundImage = new ImageIcon("img/portadabiblioteca.jpg");
				Image img = backgroundImage.getImage();
				Image scaledImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				backgroundImage = new ImageIcon(scaledImg);
				contentPane.setOpaque(false);
				backgroundImage.paintIcon(this, g, 0, 0);
				super.paintComponent(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel label = new JLabel("¡Bienvenido a la Biblioteca!");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		try {
			// Cargar la fuente desde el archivo descargado
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));
			label.setFont(customFont.deriveFont(Font.BOLD, 40)); // Aplicar la fuente y ajustar el tamaño
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			// Si hay un error al cargar la fuente, usar la fuente por defecto
			label.setFont(label.getFont().deriveFont(Font.BOLD, 24));
		}

		JButton entrar = new JButton("Entrar");
		entrar.addActionListener(e -> Metodos.verificarYCrearXML());
		entrar.setPreferredSize(new Dimension(100, 30)); // Ajustar el tamaño del botón

		JPanel panelTextoBoton = new JPanel();
		panelTextoBoton.setOpaque(false);
		panelTextoBoton.setLayout(new GridBagLayout());
		panelTextoBoton.add(label, gbc);
		gbc.gridy = 1;
		panelTextoBoton.add(entrar, gbc);

		contentPane.add(panelTextoBoton);
		btnMusica = new JButton("Reproducir Música");
        btnMusica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleMusica();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(0, 10, 10, 10);
        contentPane.add(btnMusica, gbc);
		setLocationRelativeTo(null);
		try {
	        File audioFile = new File("musica/TraverseTown.wav");

	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	        audioClip = AudioSystem.getClip();
	        audioClip.open(audioStream);

	        // Reproducir música automáticamente al abrir la aplicación
	        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
	        isPlaying = true;

	        // Actualizar el texto del botón después de configurar el Clip
	        updateButtonText();

	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	        ex.printStackTrace();
	        // Si hay un error al cargar el archivo de audio, puedes mostrar un mensaje de error o manejarlo de otra manera
	    }
	}
	// Método para cambiar el estado de reproducción de música
	private void toggleMusica() {
	    if (isPlaying) {
	        audioClip.stop();
	    } else {
	        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
	    }

	    // Cambiar el estado de reproducción
	    isPlaying = !isPlaying;

	    // Actualizar el texto del botón
	    updateButtonText();
	}
	private void updateButtonText() {
	    String buttonText = isPlaying ? "Pausar Música" : "Reproducir Música";
	    btnMusica.setText(buttonText);
	}

}
