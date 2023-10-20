package xml;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultCaret;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

public class Interfaz {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
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
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		setNimbusLookAndFeel();
		JLabel lblNewLabel = new JLabel("Bienvenido a crunchyroll");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(169, 11, 286, 54);
		frame.getContentPane().add(lblNewLabel);
		frame.setResizable(false);
		ImageIcon backgroundImage = new ImageIcon("animes.jpg"); // Reemplaza con la ubicación real de tu imagen.
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, -90, 700, 500);
        frame.getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setOpaque(false);
		JButton btnNewButton = new JButton("Crear XML");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearXML.crearXML();
					abrirNuevoFrame();
					frame.setVisible(false);
				} catch (ParserConfigurationException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(237, 147, 154, 60);
		frame.getContentPane().add(btnNewButton);
	}
	private void abrirNuevoFrame() {
		// Crea un nuevo JFrame para los botones
		JFrame menuFrame = new JFrame();
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setSize(660, 450);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            int respuesta = JOptionPane.showConfirmDialog(menuFrame, "¿Seguro que te quieres ir? (Me merezco un 10, Soraya)", "Confirmación", JOptionPane.YES_NO_OPTION);
	            if (respuesta == JOptionPane.YES_OPTION) {
	                menuFrame.dispose(); // Cierra la ventana si el usuario confirma
	            }
	        }
	    });
		// Crea un panel con un GridLayout para centrar los botones
		JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 0, 10));
		menuFrame.getContentPane().add(buttonPanel);

		// Crea botones con las opciones
		JButton btnMostrarContenido = createStyledButton("Mostrar Contenido del XML");
        JButton btnNumeroEspectadores = createStyledButton("Número de Espectadores");
        JButton btnModificarTitulo = createStyledButton("Modificar Título del Anime");
        JButton btnMeterObjetos = createStyledButton("Meter Objetos");
        JButton btnPorcentajeAudiencia = createStyledButton("Porcentaje de Audiencia (mes)");

		// Agrega los botones al panel
		buttonPanel.add(btnMostrarContenido);
		buttonPanel.add(btnNumeroEspectadores);

		buttonPanel.add(btnModificarTitulo);
		buttonPanel.add(btnMeterObjetos);
		buttonPanel.add(btnPorcentajeAudiencia);
		
		addClickAnimation(btnMostrarContenido);
	    addClickAnimation(btnNumeroEspectadores);
	    addClickAnimation(btnModificarTitulo);
	    addClickAnimation(btnMeterObjetos);
	    addClickAnimation(btnPorcentajeAudiencia);
		
		// Muestra el nuevo frame
		menuFrame.setVisible(true);

		btnMostrarContenido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarContenidoXML();
				menuFrame.setEnabled(true);
				
			}
		});


		btnNumeroEspectadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarNumeroEspectadores();
				menuFrame.setEnabled(true);
			}
		});
		
		btnModificarTitulo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            modificarTituloAnime();
	            menuFrame.setEnabled(true);
	        }
	    });
	
		btnMeterObjetos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					agregarAnime();
				} catch (JAXBException e1) {
					e1.printStackTrace();
				}
            }
        });
		btnPorcentajeAudiencia.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        calcularPorcentajeAudienciaMes();
		        menuFrame.setEnabled(true);
		    }
		});
	}
		private void calcularPorcentajeAudienciaMes() {
		    String mesSeleccionado = JOptionPane.showInputDialog("Introduce el mes para calcular el porcentaje de audiencia (1-12):");
		    int mes = Integer.parseInt(mesSeleccionado);

		    if (mes < 1 || mes > 12) {
		        JOptionPane.showMessageDialog(null, "Mes no válido. Debe ser un número entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    Crunchyroll archivoExistente = MeterObjeto.leerArchivo();
		    Categoria categoria = archivoExistente.getCategoria();
		    String categoriaElegida = JOptionPane.showInputDialog("¿Qué categoría es el anime (shonen/gore)?").toLowerCase();

		    if (!categoriaElegida.equals("shonen") && !categoriaElegida.equals("gore")) {
		        JOptionPane.showMessageDialog(null, "Categoría no válida.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    String resultado = "Porcentaje de Audiencia para el mes " + mes + ":\n\n";

		    if (categoriaElegida.equals("shonen")) {
		        Shonen shonen = categoria.getShonen();
		        double audienciaTotal = 0.0;

		        // Calcula la audiencia total en el mes seleccionado
		        for (Anime anime : shonen.getAnimeList()) {
		            Meses meses = anime.getEspectadores().getMeses();
		            switch (mes) {
		                case 1:
		                    audienciaTotal += meses.getEnero();
		                    break;
		                case 2:
		                    audienciaTotal += meses.getFebrero();
		                    break;
		                case 3:
		                    audienciaTotal += meses.getMarzo();
		                    break;
		                case 4:
		                    audienciaTotal += meses.getAbril();
		                    break;
		                case 5:
		                    audienciaTotal += meses.getMayo();
		                    break;
		                case 6:
		                    audienciaTotal += meses.getJunio();
		                    break;
		                case 7:
		                    audienciaTotal += meses.getJulio();
		                    break;
		                case 8:
		                    audienciaTotal += meses.getAgosto();
		                    break;
		                case 9:
		                    audienciaTotal += meses.getSeptiembre();
		                    break;
		                case 10:
		                    audienciaTotal += meses.getOctubre();
		                    break;
		                case 11:
		                    audienciaTotal += meses.getNoviembre();
		                    break;
		                case 12:
		                    audienciaTotal += meses.getDiciembre();
		                    break;
		                default:
		                    break;
		            }
		        }

		        // Calcula el porcentaje para cada anime y lo agrega al resultado
		        for (Anime anime : shonen.getAnimeList()) {
		            Meses meses = anime.getEspectadores().getMeses();
		            double audienciaAnime = 0.0;

		            switch (mes) {
		                case 1:
		                    audienciaAnime = meses.getEnero();
		                    break;
		                case 2:
		                    audienciaAnime = meses.getFebrero();
		                    break;
		                case 3:
		                	audienciaAnime += meses.getMarzo();
		                    break;
		                case 4:
		                	audienciaAnime += meses.getAbril();
		                    break;
		                case 5:
		                	audienciaAnime += meses.getMayo();
		                    break;
		                case 6:
		                	audienciaAnime += meses.getJunio();
		                    break;
		                case 7:
		                	audienciaAnime += meses.getJulio();
		                    break;
		                case 8:
		                	audienciaAnime += meses.getAgosto();
		                    break;
		                case 9:
		                	audienciaAnime += meses.getSeptiembre();
		                    break;
		                case 10:
		                	audienciaAnime += meses.getOctubre();
		                    break;
		                case 11:
		                	audienciaAnime += meses.getNoviembre();
		                    break;
		                case 12:
		                	audienciaAnime += meses.getDiciembre();
		                    break;
		                default:
		                    break;
		            }

		            double porcentaje = (audienciaAnime / audienciaTotal) * 100;
		            resultado += anime.getTitulo() + ": " + porcentaje + "%\n";
		        }
		    } else if (categoriaElegida.equals("gore")) {
		        Gore gore = categoria.getGore();
		        double audienciaTotal = 0.0;

		        for (Anime anime : gore.getAnimeList()) {
		            Meses meses = anime.getEspectadores().getMeses();
		            switch (mes) {
		                case 1:
		                    audienciaTotal += meses.getEnero();
		                    break;
		                case 2:
		                    audienciaTotal += meses.getFebrero();
		                    break;
		                case 3:
		                    audienciaTotal += meses.getMarzo();
		                    break;
		                case 4:
		                    audienciaTotal += meses.getAbril();
		                    break;
		                case 5:
		                    audienciaTotal += meses.getMayo();
		                    break;
		                case 6:
		                    audienciaTotal += meses.getJunio();
		                    break;
		                case 7:
		                    audienciaTotal += meses.getJulio();
		                    break;
		                case 8:
		                    audienciaTotal += meses.getAgosto();
		                    break;
		                case 9:
		                    audienciaTotal += meses.getSeptiembre();
		                    break;
		                case 10:
		                    audienciaTotal += meses.getOctubre();
		                    break;
		                case 11:
		                    audienciaTotal += meses.getNoviembre();
		                    break;
		                case 12:
		                    audienciaTotal += meses.getDiciembre();
		                    break;
		                default:
		                    break;
		            }
		        }

		        // Calcula el porcentaje para cada anime y lo agrega al resultado
		        for (Anime anime : gore.getAnimeList()) {
		            Meses meses = anime.getEspectadores().getMeses();
		            double audienciaAnime = 0.0;

		            switch (mes) {
		                case 1:
		                    audienciaAnime = meses.getEnero();
		                    break;
		                case 2:
		                    audienciaAnime = meses.getFebrero();
		                    break;
		                case 3:
		                	audienciaAnime += meses.getMarzo();
		                    break;
		                case 4:
		                	audienciaAnime += meses.getAbril();
		                    break;
		                case 5:
		                	audienciaAnime += meses.getMayo();
		                    break;
		                case 6:
		                	audienciaAnime += meses.getJunio();
		                    break;
		                case 7:
		                	audienciaAnime += meses.getJulio();
		                    break;
		                case 8:
		                	audienciaAnime += meses.getAgosto();
		                    break;
		                case 9:
		                	audienciaAnime += meses.getSeptiembre();
		                    break;
		                case 10:
		                	audienciaAnime += meses.getOctubre();
		                    break;
		                case 11:
		                	audienciaAnime += meses.getNoviembre();
		                    break;
		                case 12:
		                	audienciaAnime += meses.getDiciembre();
		                    break;
		                default:
		                    break;
		            }

		            double porcentaje = (audienciaAnime / audienciaTotal) * 100;
		            resultado += anime.getTitulo() + ": " + porcentaje + "%\n";
		        }
		    }

		    // Muestra el resultado al usuario
		    JTextArea textArea = new JTextArea(resultado);
		    textArea.setEditable(false);
		    JScrollPane scrollPane = new JScrollPane(textArea);
		    JFrame porcentajeFrame = new JFrame("Porcentaje de Audiencia (mes)");
		    porcentajeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    porcentajeFrame.setSize(400, 300);
		    porcentajeFrame.getContentPane().add(scrollPane);
		    porcentajeFrame.setVisible(true);
		    porcentajeFrame.setLocationRelativeTo(null);
    }
	public static void agregarAnime() throws JAXBException {
	    Crunchyroll archivoExistente = MeterObjeto.leerArchivo();
	    Categoria categoria = archivoExistente.getCategoria();

	    String categoriaElegida = JOptionPane.showInputDialog("¿Qué categoría es el anime (shonen/gore)?").toLowerCase();

	    Anime nuevoAnime = new Anime();
	    nuevoAnime.setTitulo(JOptionPane.showInputDialog("Introduce el título del anime:"));
	    nuevoAnime.setAutor(JOptionPane.showInputDialog("Introduce el autor del anime:"));
	    nuevoAnime.setCodigo(JOptionPane.showInputDialog("Introduce el código del anime:"));

	    Meses meses = new Meses();
	    Espectadores espectadores = new Espectadores();

	    for (int i = 1; i <= 12; i++) {
	        int espectadoresMes = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de espectadores para el mes " + i + ":"));
	        switch (i) {
	            case 1:
	                meses.setEnero(espectadoresMes);
	                break;
	            case 2:
	                meses.setFebrero(espectadoresMes);
	                break;
	            case 3:
	                meses.setMarzo(espectadoresMes);
	                break;
	            case 4:
	                meses.setAbril(espectadoresMes);
	                break;
	            case 5:
	                meses.setMayo(espectadoresMes);
	                break;
	            case 6:
	                meses.setJunio(espectadoresMes);
	                break;
	            case 7:
	                meses.setJulio(espectadoresMes);
	                break;
	            case 8:
	                meses.setAgosto(espectadoresMes);
	                break;
	            case 9:
	                meses.setSeptiembre(espectadoresMes);
	                break;
	            case 10:
	                meses.setOctubre(espectadoresMes);
	                break;
	            case 11:
	                meses.setNoviembre(espectadoresMes);
	                break;
	            case 12:
	                meses.setDiciembre(espectadoresMes);
	                break;
	            default:
	                break;
	        }
	    }

	    // Luego, estableces los espectadores en la clase Espectadores.
	    espectadores.setMeses(meses);

	    // Luego, estableces el objeto Espectadores en el objeto Anime.
	    nuevoAnime.setEspectadores(espectadores);

	    // Luego, agrega el nuevo Anime a la lista correspondiente.
	    if ("shonen".equals(categoriaElegida)) {
	        categoria.getShonen().getAnimeList().add(nuevoAnime);
	    } else if ("gore".equals(categoriaElegida)) {
	        categoria.getGore().getAnimeList().add(nuevoAnime);
	    } else {
	        JOptionPane.showMessageDialog(null, "Categoría no válida.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Luego, escribe el objeto Crunchyroll actualizado nuevamente en el archivo XML
	    MeterObjeto.escribirArchivo(archivoExistente);

	    JOptionPane.showMessageDialog(null, "Anime añadido y almacenado en el archivo XML.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void modificarTituloAnime() {
	    try {
	        String codigo = JOptionPane.showInputDialog("Introduce el código del anime que deseas modificar:");
	        String nuevoTitulo = JOptionPane.showInputDialog("Introduce el nuevo título del anime:");

	        if (codigo != null && nuevoTitulo != null) {
	            ModificarTitulo.modificarNombreAnimePorCodigo(codigo, nuevoTitulo);

	            JOptionPane.showMessageDialog(null, "Nombre del anime modificado correctamente en el archivo XML.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void mostrarNumeroEspectadores() {
		JFrame espectadoresFrame = new JFrame("Número de Espectadores");
		espectadoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		espectadoresFrame.setSize(660, 450);
		espectadoresFrame.setLocationRelativeTo(null);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		espectadoresFrame.getContentPane().add(scrollPane);

		// Llama al método NumEspectadores.NumEspec para obtener el contenido de espectadores
		String espectadoresContent = NumEspectadores.NumEspec();

		// Establece el contenido del JTextArea con la información de espectadores
		textArea.setText(espectadoresContent);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		espectadoresFrame.setVisible(true);
	}

	private void mostrarContenidoXML() {
		JFrame contentFrame = new JFrame("Contenido del Archivo XML");
		contentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentFrame.setSize(660, 450);
		contentFrame.setLocationRelativeTo(null);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentFrame.getContentPane().add(scrollPane);

		// Llama al método LeerXML2.Leer y obtiene el contenido del archivo XML
		String xmlContent = LeerXML2.Leer();

		// Establece el contenido del JTextArea con el contenido XML
		textArea.setText(xmlContent);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		contentFrame.setVisible(true);
	}
	
	private void setNimbusLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
           
        }
    }
	
	private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setBorder(new BevelBorder(BevelBorder.RAISED));
        button.setBackground(new java.awt.Color(255, 165, 0)); // Color de fondo personalizado
        button.setForeground(new java.awt.Color(255, 255, 255)); // Color del texto
        return button;
    }
	
	private void addClickAnimation(final JButton button) {
	    final Color originalColor = button.getBackground();
	    final Color clickColor = new Color(255, 0, 0); // Color de fondo al hacer clic

	    button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            animateButtonOnClick(button, originalColor, clickColor);
	        }
	    });
	}

	private void animateButtonOnClick(final JButton button, final Color originalColor, final Color targetColor) {
	    final int animationDuration = 2000; // Duración de la animación en milisegundos
	    final int steps = 500; // Número de pasos de animación
	    final long delay = animationDuration / steps;
	    final int numCycles = 5; // Número de ciclos de animación

	    new Thread(new Runnable() {
	        public void run() {
	            for (int cycle = 0; cycle < numCycles; cycle++) {
	                for (int i = 1; i <= steps; i++) {
	                    final float alpha = (float) i / steps;
	                    final Color interpolatedColor = new Color(
	                    	(int) (originalColor.getBlue() * (1 - alpha) + targetColor.getBlue() * alpha),
	                        (int) (originalColor.getRed() * (1 - alpha) + targetColor.getRed() * alpha),
	                        (int) (originalColor.getGreen() * (1 - alpha) + targetColor.getGreen() * alpha)
	                    );

	                    // Actualiza el color del botón en el hilo de la interfaz de usuario
	                    SwingUtilities.invokeLater(new Runnable() {
	                        public void run() {
	                            button.setBackground(interpolatedColor);
	                        }
	                    });

	                    try {
	                        Thread.sleep(delay);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }

	                // Restaurar el color original al final de la animación
	                SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                        button.setBackground(originalColor);
	                    }
	                });
	            }
	        }
	    }).start();
	}
}