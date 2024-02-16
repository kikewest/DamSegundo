package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

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
    private Libro libroActual;
    private JButton btnEliminar;
    private JButton btnXsl;
    
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
        setTitle("Biblioteca de Ilerna");
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

        JButton entrar = new JButton("Entrar");
        entrar.setPreferredSize(new Dimension(180, 50));
        entrar.addActionListener(e -> mostrarPanelCategorias());
        
        Font fuenteEntrar = null;
        try {
            fuenteEntrar = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"))
                    .deriveFont(Font.BOLD, 20);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            fuenteEntrar = new Font("Arial", Font.BOLD, 20);
        }
        entrar.setFont(fuenteEntrar);
        
        panelTextoBoton = new JPanel();
        panelTextoBoton.setOpaque(false);

        panelTextoBoton.setLayout(new BoxLayout(panelTextoBoton, BoxLayout.Y_AXIS));

        JLabel labelBienvenida = new JLabel("¡Bienvenido a la Biblioteca de Ilerna!");
        labelBienvenida.setForeground(Color.WHITE);
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));
            labelBienvenida.setFont(customFont.deriveFont(Font.BOLD, 50));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            labelBienvenida.setFont(new Font("Arial", Font.BOLD, 50));
        }

        labelBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        entrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTextoBoton.add(labelBienvenida);
        panelTextoBoton.add(Box.createVerticalStrut(285));
        panelTextoBoton.add(entrar);

        btnMusica = new JButton("Reproducir Música");
        btnMusica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleMusica();
            }
        });

        btnAtras = new JButton("Salir");
        btnAtras.addActionListener(e -> volverAlPanelAnterior());
        
        btnConsultarXPath = new JButton("Consultar XPath");
        btnConsultarXPath.addActionListener(e -> manejarConsultasXPath());
        btnEliminar = new JButton("Eliminar Libro");
        btnEliminar.addActionListener(e -> eliminarLibro(categoriaSeleccionada,libroActual));
        btnXsl =new JButton("Ver archivo XSL");
        btnXsl.addActionListener(e -> btnXslActionPerformed(e));
        panelMusicaYBotones = new JPanel();
        panelMusicaYBotones.setOpaque(false);
        BoxLayout boxLayout = new BoxLayout(panelMusicaYBotones, BoxLayout.X_AXIS);
        panelMusicaYBotones.setLayout(boxLayout);
        panelMusicaYBotones.add(btnMusica);
        panelMusicaYBotones.add(Box.createHorizontalStrut(10));  
        panelMusicaYBotones.add(btnConsultarXPath);
        panelMusicaYBotones.add(Box.createHorizontalStrut(10)); 
        panelMusicaYBotones.add(btnEliminar);
        panelMusicaYBotones.add(btnXsl);
        btnXsl.setVisible(false);
        btnEliminar.setVisible(false);
        btnConsultarXPath.setVisible(false);
        panelMusicaYBotones.add(Box.createHorizontalGlue());
        panelMusicaYBotones.add(btnAtras);
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panelTextoBoton, BorderLayout.NORTH);
        contentPane.add(panelMusicaYBotones, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        try {
            File audioFile = new File("musica/TraverseTown.wav");

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            float volumenInicial = -30.0f;
            ajustarVolumen(volumenInicial);

            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;

            updateButtonText();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    private void btnXslActionPerformed(ActionEvent e) {
        // Ruta del archivo XML
        String xmlFilePath = "biblioteca.xml";

        // Ruta del archivo XSL
        String xsltFilePath = "bibliotecaXsl.xsl";

        // Ruta para guardar el archivo HTML en la carpeta del proyecto
        String htmlFilePath = "biblioteca.html";

        // Realizar la transformación y obtener el HTML resultante
        String html = transformToHTML(xmlFilePath, xsltFilePath);

        // Guardar el HTML en un archivo
        saveHTMLToFile(html, htmlFilePath);

        // Mostrar el HTML y abrir el navegador
        abrirEnNavegador(htmlFilePath);

    }
    private void saveHTMLToFile(String htmlContent, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String transformToHTML(String xmlFilePath, String xsltFilePath) {
        try {
            File xmlFile = new File(xmlFilePath);
            File xsltFile = new File(xsltFilePath);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltFile));

            StringWriter writer = new StringWriter();
            transformer.transform(new StreamSource(xmlFile), new StreamResult(writer));

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void abrirEnNavegador(String filePath) {
        try {
            Desktop.getDesktop().browse(new File(filePath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarLibro(String categoriaSeleccionada, Libro libroSeleccionado) {
        try {
            Biblioteca biblioteca = CrearXml.desmarshalizarBiblioteca("biblioteca.xml");
            List<Categoria> categorias = biblioteca.getCategorias();

            Categoria categoria = Metodos.obtenerCategoriaPorNombre(categoriaSeleccionada, categorias);

            if (categoria != null) {
                List<Libro> libros = categoria.getLibros();
                for (Libro libro : libros) {
                    if (libro.getTitulo().equals(libroSeleccionado.getTitulo())) {
                        int confirmacion = JOptionPane.showConfirmDialog(
                                this,
                                "¿Estás seguro de que quieres eliminar el libro?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            libros.remove(libro);

                            CrearXml.marshalizarBiblioteca(biblioteca, "biblioteca.xml");

                            contentPane.remove(panelDatosLibros);
                            mostrarPanelLibros(categoriaSeleccionada);
                            btnEliminar.setVisible(false);
                            return;
                        } else {
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "El libro no existe en la categoría seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "La categoría no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void añadirLibro(String categoriaSeleccionada) {
        Biblioteca biblioteca = CrearXml.desmarshalizarBiblioteca("biblioteca.xml");

        Categoria categoria = Metodos.obtenerCategoriaPorNombre(categoriaSeleccionada, biblioteca.getCategorias());

        if (categoria != null) {
            JTextField tituloField = new JTextField();
            JTextField autorField = new JTextField();
            JTextField editorialField = new JTextField();
            JTextField fechaPublicacionField = new JTextField();
            JTextField isbnField = new JTextField();
            JTextField numPaginasField = new JTextField();
            JTextField precioField = new JTextField();
            JTextArea sinopsisArea = new JTextArea();
            
            sinopsisArea.setLineWrap(true);
            sinopsisArea.setWrapStyleWord(true); 

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            agregarComponenteConAlineacion(panel, new JLabel("Título:"), Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, tituloField, Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, new JLabel("Autor:"), Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, autorField, Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, new JLabel("Editorial:"), Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, editorialField, Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, new JLabel("Fecha de Publicación:"), Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, fechaPublicacionField, Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, new JLabel("ISBN:"), Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, isbnField, Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, new JLabel("Número de Páginas:"), Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, numPaginasField, Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, new JLabel("Precio:"), Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, precioField, Component.LEFT_ALIGNMENT);
            agregarComponenteConAlineacion(panel, new JLabel("Sinopsis:"), Component.LEFT_ALIGNMENT);
            JScrollPane scrollPane = new JScrollPane(sinopsisArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            agregarComponenteConAlineacion(panel, scrollPane, Component.LEFT_ALIGNMENT);
            
            panel.setPreferredSize(new Dimension(400, 300));


            int result = JOptionPane.showConfirmDialog(null, panel, "Añadir Nuevo Libro",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
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
                    return;
                }
                try {
                    double precio = Double.parseDouble(precioField.getText());
                    nuevoLibro.setPrecio(precio);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                categoria.getLibros().add(nuevoLibro);

                CrearXml.marshalizarBiblioteca(biblioteca, "biblioteca.xml");

                JOptionPane.showMessageDialog(null, "Libro añadido con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                contentPane.remove(panelLibro);
                mostrarPanelLibros(categoriaSeleccionada);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La categoría no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void agregarComponenteConAlineacion(JPanel panel, JComponent componente, float alineacion) {
        componente.setAlignmentX(alineacion);
        panel.add(componente);
    }
    private void manejarConsultasXPath() {
        if (panelActual == panelLibro) {
            btnConsultarXPath.setText("Añadir Libro");
            añadirLibro(categoriaSeleccionada);

        } else if (panelActual == panelDatosLibros) {
            modificarLibroEnXML(libroActual);
        } else {
        	mostrarResultadoXPath();
        }
    }
    
    private void modificarLibroEnXML(Libro libro) {
        try {
            Biblioteca biblioteca = CrearXml.desmarshalizarBiblioteca("biblioteca.xml");
            List<Categoria> categorias = biblioteca.getCategorias();
            String nombreCategoria = categoriaSeleccionada;

            Categoria categoriaSeleccionada = Metodos.obtenerCategoriaPorNombre(nombreCategoria, categorias);
            if (categoriaSeleccionada != null) {
                // Solicitar los nuevos datos del libro al usuario
                Libro nuevoLibro = obtenerDatosLibroDesdeUsuario(libro);

                // Buscar el libro por título dentro de la categoría
                for (Libro libroActual : categoriaSeleccionada.getLibros()) {
                    if (libroActual.getTitulo().equals(libro.getTitulo())) {
                        // Modificar la información del libro
                    	libroActual.setTitulo(nuevoLibro.getTitulo());
                        libroActual.setAutor(nuevoLibro.getAutor());
                        libroActual.setEditorial(nuevoLibro.getEditorial());
                        libroActual.setFechaDePublicacion(nuevoLibro.getFechaDePublicacion());
                        libroActual.setIsbn(nuevoLibro.getIsbn());
                        libroActual.setNumeroPaginas(nuevoLibro.getNumeroPaginas());
                        libroActual.setPrecio(nuevoLibro.getPrecio());
                        libroActual.setSinopsis(nuevoLibro.getSinopsis());

                        // Guardar la biblioteca actualizada en el archivo XML
                        CrearXml.marshalizarBiblioteca(biblioteca, "biblioteca.xml");

                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(this, "Libro modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        contentPane.removeAll();
                        contentPane.add(panelMusicaYBotones);
                        mostrarDatosLibros(libroActual,this.categoriaSeleccionada);
                        return;
                    }
                }
                // Mostrar mensaje si el libro no se encuentra en la categoría
                JOptionPane.showMessageDialog(this, "El libro no existe en la categoría seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "La categoría no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al modificar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Libro obtenerDatosLibroDesdeUsuario(Libro libroExistente) {
        JTextField tituloField = new JTextField(libroExistente.getTitulo());
        JTextField autorField = new JTextField(libroExistente.getAutor());
        JTextField editorialField = new JTextField(libroExistente.getEditorial());
        JTextField fechaPublicacionField = new JTextField(libroExistente.getFechaDePublicacion());
        JTextField isbnField = new JTextField(String.valueOf(libroExistente.getIsbn()));
        JTextField numPaginasField = new JTextField(libroExistente.getNumeroPaginas());
        JTextField precioField = new JTextField(String.valueOf(libroExistente.getPrecio()));
        JTextArea sinopsisArea = new JTextArea(libroExistente.getSinopsis());

        sinopsisArea.setLineWrap(true);
        sinopsisArea.setWrapStyleWord(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        agregarComponenteConAlineacion(panel, new JLabel("Título:"), Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, tituloField, Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, new JLabel("Autor:"), Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, autorField, Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, new JLabel("Editorial:"), Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, editorialField, Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, new JLabel("Fecha de Publicación:"), Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, fechaPublicacionField, Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, new JLabel("ISBN:"), Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, isbnField, Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, new JLabel("Número de Páginas:"), Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, numPaginasField, Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, new JLabel("Precio:"), Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, precioField, Component.LEFT_ALIGNMENT);
        agregarComponenteConAlineacion(panel, new JLabel("Sinopsis:"), Component.LEFT_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(sinopsisArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        agregarComponenteConAlineacion(panel, scrollPane, Component.LEFT_ALIGNMENT);

        panel.setPreferredSize(new Dimension(400, 300));

        int result = JOptionPane.showConfirmDialog(null, panel, "Modificar Libro",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
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
                return null;
            }

            try {
                double precio = Double.parseDouble(precioField.getText());
                nuevoLibro.setPrecio(precio);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            System.out.println(nuevoLibro.toString());
            return nuevoLibro;
        }else {
        	System.out.println("cancelo la operacion");
        }
        return null;
    }
    
    private void mostrarResultadoXPath() {
        String consultaXPath = JOptionPane.showInputDialog(this, "Introduce la consulta XPath:");
        if (consultaXPath != null && !consultaXPath.trim().isEmpty()) {
            try {
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath= xPathFactory.newXPath();
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                org.w3c.dom.Document document = documentBuilder.parse(new File("biblioteca.xml"));

                XPathExpression xPathExpression = xPath.compile(consultaXPath);

                Object resultado = xPathExpression.evaluate(document, XPathConstants.NODESET);

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
    private void toggleMusica() {
        if (isPlaying) {
            audioClip.stop();
        } else {
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        isPlaying = !isPlaying;
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
    private void mostrarPanelCategorias() {
    	btnAtras.setText("Atrás");
    	btnXsl.setVisible(true);
    	btnConsultarXPath.setVisible(true);
        panelCategorias = new JPanel(new BorderLayout());
        panelCategorias.setOpaque(false);

        panelBotonesCategorias = new JPanel();
        panelBotonesCategorias.setOpaque(false);

        List<String> categorias = obtenerCategorias();

        panelBotonesCategorias.setLayout(null);

        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));

            font = font.deriveFont(Font.BOLD, 22);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        int buttonWidth = 180;
        int buttonHeight = 50;
        int gapBetweenButtons = 120;
        int buttonsPerColumn = 3;
        
        int x = 198; 
        int y = 110; 

        for (int i = 0; i < categorias.size(); i++) {
            String categoria = categorias.get(i);
            JButton btnCategoria = new JButton(categoria);

            if (font != null) {
                btnCategoria.setFont(font);
            }

            btnCategoria.setForeground(Color.BLACK);

            btnCategoria.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

            btnCategoria.setBounds(x, y, buttonWidth, buttonHeight);

            btnCategoria.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	btnXsl.setVisible(false);
                    categoriaSeleccionada = btnCategoria.getText();
                    btnConsultarXPath.setText("Añadir Libro");
                    btnConsultarXPath.setVisible(true);
                    mostrarPanelLibros(categoriaSeleccionada);
                }
            });
            panelBotonesCategorias.add(btnCategoria);

            if ((i + 1) % buttonsPerColumn == 0) {
                x += buttonWidth + gapBetweenButtons+100;
                y = 110;
            } else {
                y += buttonHeight + gapBetweenButtons;
            }
        }

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));

            Font fuenteDerivada = customFont.deriveFont(Font.BOLD, 50);

            JLabel labelCategorias = new JLabel("Categorías", SwingConstants.CENTER);

            labelCategorias.setFont(fuenteDerivada);

            labelCategorias.setForeground(Color.WHITE);

            panelCategorias.add(labelCategorias, BorderLayout.NORTH);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        contentPane.remove(panelTextoBoton);
        panelActual = panelCategorias;
        panelCategorias.add(panelBotonesCategorias, BorderLayout.CENTER);
        contentPane.add(panelCategorias, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
    public static List<Libro> obtenerLibrosPorCategoria(String categoriaNombre) {
        Biblioteca biblioteca = CrearXml.desmarshalizarBiblioteca("biblioteca.xml");

        Categoria categoriaSeleccionada = null;
        for (Categoria categoria : biblioteca.getCategorias()) {
            if (categoria.getNombre().toString().equals(categoriaNombre)) {
                categoriaSeleccionada = categoria;
                break;
            }
        }
        if (categoriaSeleccionada == null) {
            System.out.println("La categoría no existe.");
            return new ArrayList<>();
        }
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
        panelDatosLibros = new JPanel();
        panelDatosLibros.setOpaque(false);
        this.libroActual = libro;
        Font customFont = cargarFuente();

        Font fuenteDerivada = customFont.deriveFont(Font.BOLD, 40);

        JLabel labelTitulo = crearJLabel("Titulo: " + libro.getTitulo(), fuenteDerivada);
        JLabel labelAutor = crearJLabel("Autor: " + libro.getAutor(), fuenteDerivada);
        JLabel labelEditorial = crearJLabel("Editorial: " + libro.getEditorial(), fuenteDerivada);
        JLabel labelFechaPublicacion = crearJLabel("Fecha de Publicacion: " + libro.getFechaDePublicacion(), fuenteDerivada);
        JLabel labelIsbn = crearJLabel("ISBN: " + libro.getIsbn(), fuenteDerivada);
        JLabel labelNumeroPaginas = crearJLabel("Número de Páginas: " + libro.getNumeroPaginas(), fuenteDerivada);
        JLabel labelPrecio = crearJLabel("Precio: " + libro.getPrecio(), fuenteDerivada);

        JTextArea textAreaSinopsis = new JTextArea("Sinopsis: " + libro.getSinopsis());
        textAreaSinopsis.setEditable(false);
        textAreaSinopsis.setLineWrap(true);
        textAreaSinopsis.setWrapStyleWord(true);
        textAreaSinopsis.setFont(fuenteDerivada);
        textAreaSinopsis.setForeground(Color.WHITE);
        textAreaSinopsis.setOpaque(false);

        BoxLayout boxLayout = new BoxLayout(panelDatosLibros, BoxLayout.Y_AXIS);
        panelDatosLibros.setLayout(boxLayout);

        panelDatosLibros.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));

        int margenEntreComponentes = 10;
        panelDatosLibros.add(Box.createVerticalStrut(margenEntreComponentes));

        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelAutor.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelEditorial.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelFechaPublicacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelIsbn.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelNumeroPaginas.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);
        textAreaSinopsis.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        contentPane.remove(panelLibro);
        panelActual = panelDatosLibros;
        btnConsultarXPath.setText("Modificar Libro");
        contentPane.add(panelActual, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private JLabel crearJLabel(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(Color.WHITE);
        label.setOpaque(false); 
        label.setBackground(new Color(0, 0, 0, 0));
        return label;
    }

    private void mostrarPanelLibros(String categoriaSeleccionada) {
        btnConsultarXPath.setVisible(true);
        panelLibro = new JPanel(new BorderLayout());
        panelLibro.setOpaque(false);

        Font customFont = null;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/AlexBrush-Regular.ttf"));
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        Font fuenteDerivada = customFont.deriveFont(Font.BOLD, 22);
        Font fuenteDerivada1 = customFont.deriveFont(Font.BOLD, 50);
        JPanel panelBotonesLibros = new JPanel();
        panelBotonesLibros.setOpaque(false);

        List<Libro> libros = obtenerLibrosPorCategoria(categoriaSeleccionada);

        panelBotonesLibros.setLayout(null);

        Font font = fuenteDerivada;

        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapBetweenButtons = 20;

        int x = 50;
        int y = 50;
        int botonesPorColumna = 7;
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            JButton btnLibro = new JButton(libro.getTitulo());

            btnLibro.setFont(font);
            btnLibro.setForeground(Color.BLACK);

            btnLibro.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

            btnLibro.setBounds(x, y, buttonWidth, buttonHeight);

            btnLibro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Interfaz.this.categoriaSeleccionada = categoriaSeleccionada;
                    panelActual = panelLibro;
                    btnConsultarXPath.setText("Añadir Libro");
                    btnConsultarXPath.setVisible(true);
                    mostrarDatosLibros(libro, categoriaSeleccionada);
                    btnEliminar.setVisible(true);
                }
            });

            panelBotonesLibros.add(btnLibro);

            y += buttonHeight + gapBetweenButtons;

            if ((i + 1) % botonesPorColumna == 0) {
                y = 50; 
                x += buttonWidth + gapBetweenButtons;
            }
        }

        JLabel labelCategoriaSeleccionada = new JLabel("Categoría: " + categoriaSeleccionada, SwingConstants.CENTER);
        labelCategoriaSeleccionada.setFont(fuenteDerivada1);
        labelCategoriaSeleccionada.setForeground(Color.WHITE);

        panelLibro.add(labelCategoriaSeleccionada, BorderLayout.NORTH);

        panelLibro.add(panelBotonesLibros, BorderLayout.CENTER);

        contentPane.remove(panelCategorias);
        panelActual = panelLibro;
        contentPane.add(panelLibro, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void volverAlPanelAnterior() {
        if (panelActual == panelTextoBoton || panelActual == null) {
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
        	btnXsl.setVisible(false);
            contentPane.remove(panelCategorias);
            contentPane.add(panelTextoBoton, BorderLayout.NORTH);
            btnAtras.setText("Salir");
            btnConsultarXPath.setVisible(false);
            revalidate();
            repaint();
            panelActual = panelTextoBoton;
        } else if (panelActual == panelLibro) {
        	btnXsl.setVisible(true);
            contentPane.remove(panelLibro);
            contentPane.add(panelCategorias, BorderLayout.CENTER);
            btnConsultarXPath.setText("Consultar XPath");
            btnAtras.setText("Atrás");

            btnConsultarXPath.setVisible(true);

            revalidate();
            repaint();
            panelActual = panelCategorias;
        } else if (panelActual == panelDatosLibros) {
        	btnConsultarXPath.setText("Añadir Libro");
        	btnEliminar.setVisible(false);

            contentPane.remove(panelDatosLibros);
            mostrarPanelLibros(categoriaSeleccionada);
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