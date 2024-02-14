package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controlador.Biblioteca;
import controlador.Categoria;
import controlador.CrearXml;
import controlador.Libro;
import controlador.Metodos;

public class Interfaz extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Clip audioClip;
    private boolean isPlaying = false;
    private JButton btnMusica;
    private JPanel panelMusicaYBotones;
    private JPanel panelTextoBoton;
    private JPanel panelCategorias;
    private JPanel panelBotonesCategorias;
    private JButton btnAtras;
    private JPanel panelActual;
    private JPanel panelLibro;
    private JPanel panelDatosLibros;
    private JButton btnConsultarXPath;
    private String categoriaSeleccionada;

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
    	panelActual = panelTextoBoton;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 800);
        setResizable(false);
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

        // Configuración del botón de entrada
        JButton entrar = new JButton("Entrar");
        entrar.setPreferredSize(new Dimension(180, 50));
        entrar.addActionListener(e -> mostrarPanelCategorias());
        
        // Crear una fuente personalizada para el botón "Entrar"
        Font fuenteEntrar = null;
        try {
            fuenteEntrar = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"))
                    .deriveFont(Font.BOLD, 20); // Ajustar el tamaño según tus preferencias
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Si hay un error al cargar la fuente, usar la fuente por defecto
            fuenteEntrar = new Font("Arial", Font.BOLD, 20);
        }
        entrar.setFont(fuenteEntrar);
        

        // Configuración del panel de texto y botón de entrada
        panelTextoBoton = new JPanel();
        panelTextoBoton.setOpaque(false);

        // Usar BoxLayout con eje Y para centrar verticalmente
        panelTextoBoton.setLayout(new BoxLayout(panelTextoBoton, BoxLayout.Y_AXIS));

        JLabel labelBienvenida = new JLabel("¡Bienvenido a la Biblioteca!");
        labelBienvenida.setForeground(Color.WHITE); // Color blanco
        try {
            // Cargar la fuente personalizada desde el archivo en la carpeta "fuentes"
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));
            labelBienvenida.setFont(customFont.deriveFont(Font.BOLD, 50)); // Ajustar el tamaño de la letra
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Si hay un error al cargar la fuente, usar la fuente por defecto
            labelBienvenida.setFont(new Font("Arial", Font.BOLD, 50));
        }

        // Centrar horizontalmente
        labelBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        entrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar el texto y el botón al panel
        panelTextoBoton.add(labelBienvenida);
        panelTextoBoton.add(Box.createVerticalStrut(285)); // Espaciado vertical
        panelTextoBoton.add(entrar);

        // Configuración del botón de música
        btnMusica = new JButton("Reproducir Música");
        btnMusica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleMusica();
            }
        });

        // Configuración del botón "Atrás" para volver al panel anterior
        btnAtras = new JButton("Salir");
        btnAtras.addActionListener(e -> volverAlPanelAnterior());
        
        btnConsultarXPath = new JButton("Consultar XPath");
        btnConsultarXPath.addActionListener(e -> manejarConsultasXPath());
        // Configuración del panel de música y botones
        panelMusicaYBotones = new JPanel();
        panelMusicaYBotones.setOpaque(false);
        BoxLayout boxLayout = new BoxLayout(panelMusicaYBotones, BoxLayout.X_AXIS);
        panelMusicaYBotones.setLayout(boxLayout);
        panelMusicaYBotones.add(btnMusica);
        panelMusicaYBotones.add(Box.createHorizontalStrut(10));  
        panelMusicaYBotones.add(btnConsultarXPath);
        btnConsultarXPath.setVisible(false);
        panelMusicaYBotones.add(Box.createHorizontalGlue()); // Espaciado horizontal
        panelMusicaYBotones.add(btnAtras);
        

        // Configuración del contenido general
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panelTextoBoton, BorderLayout.NORTH); // Colocar en la parte superior
        contentPane.add(panelMusicaYBotones, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        try {
            File audioFile = new File("musica/TraverseTown.wav");

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            // Ajustar el volumen al cargar el clip (puedes ajustar el valor según sea necesario)
            float volumenInicial = -30.0f;
            ajustarVolumen(volumenInicial);

            // Reproducir música automáticamente al abrir la aplicación
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;

            // Actualizar el texto del botón después de configurar el Clip
            updateButtonText();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    private void añadirLibro(String categoriaSeleccionada) {
        // Obtener la biblioteca desde el archivo XML
        Biblioteca biblioteca = CrearXml.desmarshalizarBiblioteca("biblioteca.xml");

        // Obtener la categoría actualmente seleccionada
        Categoria categoria = Metodos.obtenerCategoriaPorNombre(categoriaSeleccionada, biblioteca.getCategorias());

        // Verificar si se encontró la categoría
        if (categoria != null) {
            // Crear un cuadro de diálogo para solicitar detalles del nuevo libro al usuario
            JTextField tituloField = new JTextField();
            JTextField autorField = new JTextField();
            JTextField editorialField = new JTextField();
            JTextField fechaPublicacionField = new JTextField();
            JTextField isbnField = new JTextField();
            JTextField numPaginasField = new JTextField();
            JTextField precioField = new JTextField();
            JTextArea sinopsisArea = new JTextArea();

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Título:"));
            panel.add(tituloField);
            panel.add(new JLabel("Autor:"));
            panel.add(autorField);
            panel.add(new JLabel("Editorial:"));
            panel.add(editorialField);
            panel.add(new JLabel("Fecha de Publicación:"));
            panel.add(fechaPublicacionField);
            panel.add(new JLabel("ISBN:"));
            panel.add(isbnField);
            panel.add(new JLabel("Número de Páginas:"));
            panel.add(numPaginasField);
            panel.add(new JLabel("Precio:"));
            panel.add(precioField);
            panel.add(new JLabel("Sinopsis:"));
            panel.add(sinopsisArea);

            int result = JOptionPane.showConfirmDialog(null, panel, "Añadir Nuevo Libro",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Verificar si el usuario presionó "OK"
            if (result == JOptionPane.OK_OPTION) {
                // Crear una nueva instancia de Libro con los detalles proporcionados
                Libro nuevoLibro = new Libro();
                nuevoLibro.setTitulo(tituloField.getText());
                nuevoLibro.setAutor(autorField.getText());
                nuevoLibro.setEditorial(editorialField.getText());
                nuevoLibro.setFechaDePublicacion(fechaPublicacionField.getText());

                nuevoLibro.setNumeroPaginas(numPaginasField.getText());

                nuevoLibro.setSinopsis(sinopsisArea.getText());
                try {
                    long isbn = Long.parseLong(isbnField.getText());
                    nuevoLibro.setIsbn(isbn);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un ISBN válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si el ISBN no es válido
                }
                try {
                    double precio = Double.parseDouble(precioField.getText());
                    nuevoLibro.setPrecio(precio);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si el precio no es válido
                }

                // Añadir el nuevo libro a la lista de libros de la categoría
                categoria.getLibros().add(nuevoLibro);

                // Actualizar y guardar la biblioteca en el archivo XML
                CrearXml.marshalizarBiblioteca(biblioteca, "biblioteca.xml");

                // Mostrar un mensaje de éxito al usuario
                JOptionPane.showMessageDialog(null, "Libro añadido con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                contentPane.remove(panelLibro);
                mostrarPanelLibros(categoriaSeleccionada);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La categoría no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void manejarConsultasXPath() {
        if (panelActual == panelLibro) {
            // Si estamos en el panelLibro, cambiar el texto a "Añadir Libro" y manejar la acción de añadir libro
            btnConsultarXPath.setText("Añadir Libro");
            añadirLibro(categoriaSeleccionada);

        } else {
            mostrarResultadoXPath();
        }
    }
    private void mostrarResultadoXPath() {
        String consultaXPath = JOptionPane.showInputDialog(this, "Introduce la consulta XPath:");
        if (consultaXPath != null && !consultaXPath.trim().isEmpty()) {
            try {
                // Realizar la consulta XPath
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath= xPathFactory.newXPath();
             // Obtener el documento XML desde el archivo
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                org.w3c.dom.Document document = documentBuilder.parse(new File("biblioteca.xml"));

                // Compilar la expresión XPath
                XPathExpression xPathExpression = xPath.compile(consultaXPath);

                // Evaluar la expresión XPath en el documento XML
                Object resultado = xPathExpression.evaluate(document, XPathConstants.NODESET);

                // Procesar y mostrar los resultados en un JOptionPane
                if (resultado instanceof NodeList) {
                    NodeList nodeList = (NodeList) resultado;
                    StringBuilder resultados = new StringBuilder("Resultados de la consulta XPath:\n");

                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        resultados.append(node.getNodeValue()).append("\n");
                    }

                    JOptionPane.showMessageDialog(this, resultados.toString(), "Resultados XPath", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "La consulta XPath no devolvió nodos.", "Error XPath", JOptionPane.ERROR_MESSAGE);
                }

            } catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al ejecutar la consulta XPath.", "Error XPath", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Consulta XPath vacía. Introduce una consulta válida.", "Error XPath", JOptionPane.ERROR_MESSAGE);
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

    private void ajustarVolumen(float volume) {
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);

        if (gainControl != null) {
            float minVolume = gainControl.getMinimum();
            float maxVolume = gainControl.getMaximum();
            float targetVolume = Math.max(minVolume, Math.min(maxVolume, volume));


            gainControl.setValue(targetVolume);
        } else {
            System.out.println("El control de volumen no es compatible");
        }
    }

    private void updateButtonText() {
        String buttonText = isPlaying ? "Pausar Música" : "Reproducir Música";
        btnMusica.setText(buttonText);
    }

    // Método para mostrar el panel de categorías
    private void mostrarPanelCategorias() {
        // Crear panel de categorías
    	btnAtras.setText("Atrás");
    	btnConsultarXPath.setVisible(true);
        panelCategorias = new JPanel(new BorderLayout());
        panelCategorias.setOpaque(false);

        // Crear panel para los botones de categorías
        panelBotonesCategorias = new JPanel();
        panelBotonesCategorias.setOpaque(false);

        // Obtener categorías (debes implementar según tus necesidades)
        List<String> categorias = obtenerCategorias();

        // Establecer el layout como null para controlar manualmente la posición y el tamaño de los botones
        panelBotonesCategorias.setLayout(null);

        Font font = null;

        try {
            // Cargar la fuente personalizada desde el archivo
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));

            // Derivar la fuente con el estilo y tamaño deseados
            font = font.deriveFont(Font.BOLD, 22); // Ajusta según tus preferencias

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Manejar errores al cargar la fuente
        }

        int buttonWidth = 180;
        int buttonHeight = 50;
        int gapBetweenButtons = 120;
        int buttonsPerColumn = 3;
        
        int x = 198; // Coordenada x inicial
        int y = 110; // Coordenada y inicial

        for (int i = 0; i < categorias.size(); i++) {
            String categoria = categorias.get(i);
            JButton btnCategoria = new JButton(categoria);

            // Aplicar la fuente a cada botón
            if (font != null) {
                btnCategoria.setFont(font);
            }

            btnCategoria.setForeground(Color.BLACK);

            // Establecer el tamaño preferido del botón
            btnCategoria.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

            // Establecer la posición y tamaño manualmente
            btnCategoria.setBounds(x, y, buttonWidth, buttonHeight);

            btnCategoria.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener el texto del botón (nombre de la categoría)
                    categoriaSeleccionada = btnCategoria.getText();
                    // Llamar al método para mostrar el panel de libros
                    btnConsultarXPath.setText("Añadir Libro");
                    btnConsultarXPath.setVisible(true);
                    mostrarPanelLibros(categoriaSeleccionada);
                }
            });
            panelBotonesCategorias.add(btnCategoria);

            // Calcular las nuevas coordenadas para el siguiente botón
            if ((i + 1) % buttonsPerColumn == 0) {
                // Ir a la siguiente columna
                x += buttonWidth + gapBetweenButtons+100;
                y = 110; // Reiniciar la coordenada y para la nueva columna
            } else {
                // Mover a la siguiente fila
                y += buttonHeight + gapBetweenButtons;
            }
        }

        try {
            // Cargar la fuente personalizada desde el archivo
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));

            // Derivar la fuente con el estilo y tamaño deseados
            Font fuenteDerivada = customFont.deriveFont(Font.BOLD, 50); // Ajusta según tus preferencias

            // Crear el JLabel para las categorías
            JLabel labelCategorias = new JLabel("Categorías", SwingConstants.CENTER);

            // Establecer la fuente personalizada en el JLabel
            labelCategorias.setFont(fuenteDerivada);

            // Establecer el color del texto
            labelCategorias.setForeground(Color.WHITE);

            // Agregar el JLabel al panel de categorías
            panelCategorias.add(labelCategorias, BorderLayout.NORTH);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Manejar errores al cargar la fuente
        }

        // Remover el panel de texto y botón actual
        contentPane.remove(panelTextoBoton);
        panelActual = panelCategorias;
        // Agregar el panel de categorías al contentPane
        panelCategorias.add(panelBotonesCategorias, BorderLayout.CENTER);
        contentPane.add(panelCategorias, BorderLayout.CENTER);

        // Actualizar la interfaz
        revalidate();
        repaint();
    }
    public static List<Libro> obtenerLibrosPorCategoria(String categoriaNombre) {
        // Obtener la biblioteca deserializada del archivo XML
        Biblioteca biblioteca = CrearXml.desmarshalizarBiblioteca("biblioteca.xml");

        // Buscar la categoría por nombre
        Categoria categoriaSeleccionada = null;
        for (Categoria categoria : biblioteca.getCategorias()) {
            if (categoria.getNombre().toString().equals(categoriaNombre)) {
                categoriaSeleccionada = categoria;
                break;
            }
        }
        // Verificar si se encontró la categoría
        if (categoriaSeleccionada == null) {
            System.out.println("La categoría no existe.");
            return new ArrayList<>(); // Devolver una lista vacía si no se encontró la categoría
        }
        // Devolver la lista de libros de la categoría seleccionada
        return categoriaSeleccionada.getLibros();
    }
    private Font cargarFuente() {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return customFont;
    }
    private void mostrarDatosLibros(Libro libro, String categoriaSeleccionada) {
        // Crear el panelDatosLibros
        panelDatosLibros = new JPanel();
        panelDatosLibros.setOpaque(false);

        Font customFont = cargarFuente();

        // Derivar la fuente con el estilo y tamaño deseados
        Font fuenteDerivada = customFont.deriveFont(Font.BOLD, 40); // Ajusta según tus preferencias

        // Crear JLabels para los atributos del libro específico
        JLabel labelTitulo = crearJLabel("Titulo: " + libro.getTitulo(), fuenteDerivada);
        JLabel labelAutor = crearJLabel("Autor: " + libro.getAutor(), fuenteDerivada);
        JLabel labelEditorial = crearJLabel("Editorial: " + libro.getEditorial(), fuenteDerivada);
        JLabel labelFechaPublicacion = crearJLabel("Fecha de Publicacion: " + libro.getFechaDePublicacion(), fuenteDerivada);
        JLabel labelIsbn = crearJLabel("ISBN: " + libro.getIsbn(), fuenteDerivada);
        JLabel labelNumeroPaginas = crearJLabel("Número de Páginas: " + libro.getNumeroPaginas(), fuenteDerivada);
        JLabel labelPrecio = crearJLabel("Precio: " + libro.getPrecio(), fuenteDerivada);

        // Crear JTextArea para la sinopsis
        JTextArea textAreaSinopsis = new JTextArea("Sinopsis: " + libro.getSinopsis());
        textAreaSinopsis.setEditable(false);
        textAreaSinopsis.setLineWrap(true);
        textAreaSinopsis.setWrapStyleWord(true);
        textAreaSinopsis.setFont(fuenteDerivada);
        textAreaSinopsis.setForeground(Color.WHITE);
        textAreaSinopsis.setOpaque(false);

        // Crear el layout para organizar los componentes verticalmente con margen
        BoxLayout boxLayout = new BoxLayout(panelDatosLibros, BoxLayout.Y_AXIS);
        panelDatosLibros.setLayout(boxLayout);

        // Agregar margen izquierdo

        panelDatosLibros.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));

        // Agregar margen entre cada componente
        int margenEntreComponentes = 10;
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));

        // Alinear a la izquierda
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelAutor.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelEditorial.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelFechaPublicacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelIsbn.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelNumeroPaginas.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);
        textAreaSinopsis.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Agregar cada componente al panelDatosLibros
        panelDatosLibros.add(labelTitulo);
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));
        panelDatosLibros.add(labelAutor);
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));
        panelDatosLibros.add(labelEditorial);
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));
        panelDatosLibros.add(labelFechaPublicacion);
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));
        panelDatosLibros.add(labelIsbn);
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));
        panelDatosLibros.add(labelNumeroPaginas);
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));
        panelDatosLibros.add(labelPrecio);
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));
        panelDatosLibros.add(textAreaSinopsis);

        // Remover el panel actual (ya sea panelCategorias o panelLibro)
        contentPane.remove(panelLibro);
        panelActual = panelDatosLibros;
        // Agregar el panelMostrarDatos al contentPane
        contentPane.add(panelActual, BorderLayout.CENTER);

        // Actualizar la interfaz
        revalidate();
        repaint();
    }

    private JLabel crearJLabel(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(Color.WHITE);
        label.setOpaque(false); // Hacer el JLabel transparente
        label.setBackground(new Color(0, 0, 0, 0)); // Establecer el fondo transparente
        return label;
    }

    private void mostrarPanelLibros(String categoriaSeleccionada) {
        // Crear panel de libros
        btnConsultarXPath.setVisible(true);
        panelLibro = new JPanel(new BorderLayout());
        panelLibro.setOpaque(false);

        Font customFont = null;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch blocks
			e1.printStackTrace();
		}

        // Derivar la fuente con el estilo y tamaño deseados
        Font fuenteDerivada = customFont.deriveFont(Font.BOLD, 22); // Ajusta según tus preferencias
        Font fuenteDerivada1 = customFont.deriveFont(Font.BOLD, 50); // Ajusta según tus preferencias
        // Crear panel para los botones de libros
        JPanel panelBotonesLibros = new JPanel();
        panelBotonesLibros.setOpaque(false);

        // Obtener libros de la categoría seleccionada
        List<Libro> libros = obtenerLibrosPorCategoria(categoriaSeleccionada);

        // Establecer el layout como null para controlar manualmente la posición y el tamaño de los botones
        panelBotonesLibros.setLayout(null);

        Font font = fuenteDerivada;

        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapBetweenButtons = 20;

        int x = 50; // Coordenada x inicial
        int y = 50; // Coordenada y inicial
        int botonesPorColumna = 7;
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            JButton btnLibro = new JButton(libro.getTitulo());

            // Aplicar la fuente a cada botón
            btnLibro.setFont(font);
            btnLibro.setForeground(Color.BLACK);

            // Establecer el tamaño preferido del botón
            btnLibro.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

            // Establecer la posición y tamaño manualmente
            btnLibro.setBounds(x, y, buttonWidth, buttonHeight);

            btnLibro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Llamar al método para mostrar los datos del libro específico
                    Interfaz.this.categoriaSeleccionada = categoriaSeleccionada;
                    panelActual = panelLibro;
                    btnConsultarXPath.setText("Añadir Libro");
                    btnConsultarXPath.setVisible(true);
                    mostrarDatosLibros(libro, categoriaSeleccionada);
                }
            });

            panelBotonesLibros.add(btnLibro);

            // Calcular las nuevas coordenadas para el siguiente botón
            y += buttonHeight + gapBetweenButtons;

            // Si se alcanza el número máximo de botones por columna, pasar a la siguiente columna
            if ((i + 1) % botonesPorColumna == 0) {
                y = 50; // Reiniciar la coordenada y
                x += buttonWidth + gapBetweenButtons; // Mover a la siguiente columna
            }
        }

        // Crear el JLabel para el nombre de la categoría seleccionada
        JLabel labelCategoriaSeleccionada = new JLabel("Categoría: " + categoriaSeleccionada, SwingConstants.CENTER);
        labelCategoriaSeleccionada.setFont(fuenteDerivada1);
        labelCategoriaSeleccionada.setForeground(Color.WHITE);

        // Agregar el JLabel al panelLibro
        panelLibro.add(labelCategoriaSeleccionada, BorderLayout.NORTH);
        // Agregar el panel de botones de libros al centro
        panelLibro.add(panelBotonesLibros, BorderLayout.CENTER);

        // Remover el panel de categorías actual
        contentPane.remove(panelCategorias);
        panelActual = panelLibro;
        // Agregar el panel de libros al contentPane
        contentPane.add(panelLibro, BorderLayout.CENTER);

        // Actualizar la interfaz
        revalidate();
        repaint();
    }

    // Método para volver al panel anterior
    private void volverAlPanelAnterior() {
        if (panelActual == panelTextoBoton || panelActual == null) {
            // Si estamos en el panelTextoBoton, mostrar un diálogo de confirmación y salir si es afirmativo
        	btnConsultarXPath.setVisible(false);
            btnAtras.setText("Salir");
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            if (panelActual != panelCategorias) {
                btnConsultarXPath.setVisible(false);
            }
        } else if (panelActual == panelCategorias) {
            // Si estamos en el panelCategorias, volver al panelTextoBoton
            contentPane.remove(panelCategorias);
            contentPane.add(panelTextoBoton, BorderLayout.NORTH);
            btnAtras.setText("Salir");
            btnConsultarXPath.setVisible(false);
            revalidate();
            repaint();
            // Actualizar la referencia al panel actual
            panelActual = panelTextoBoton;
        } else if (panelActual == panelLibro) {
            // Si estamos en el panelLibro, volver al panelCategorias
            contentPane.remove(panelLibro);
            contentPane.add(panelCategorias, BorderLayout.CENTER);
            btnConsultarXPath.setText("Consultar XPath");
            btnAtras.setText("Atrás");

            btnConsultarXPath.setVisible(true);

            revalidate();
            repaint();
            // Actualizar la referencia al panel actual
            panelActual = panelCategorias;
        } else if (panelActual == panelDatosLibros) {
            // Remover el panelDatosLibros y volver al panelLibro
            contentPane.remove(panelDatosLibros);
            contentPane.add(panelLibro, BorderLayout.CENTER);
            panelActual = panelLibro;
            revalidate();
            repaint();
            
        }
    }
   
    public static List<String> obtenerCategorias() {
        try {
            // Configurar el contexto de JAXB para la clase Biblioteca
            JAXBContext context = JAXBContext.newInstance(Biblioteca.class);

            // Crear el objeto Unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Leer el archivo XML y realizar unmarshalling a la clase Biblioteca
            Biblioteca biblioteca = (Biblioteca) unmarshaller.unmarshal(new File("biblioteca.xml"));

            // Obtener las categorías de la biblioteca
            List<String> nombresCategorias = new ArrayList<>();
            for (Categoria categoria : biblioteca.getCategorias()) {
                nombresCategorias.add(categoria.getNombre().toString());
            }

            return nombresCategorias;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}