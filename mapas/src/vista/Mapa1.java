package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import controlador.Controlador1;

public class Mapa1 extends JFrame{

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JButton[] botones;
    private String[] etiquetas = {"Añadir", "Ver", "Actualizar", "Eliminar", "Salir"};
    private Controlador1 contr;
    private JScrollPane ver;
    public JTable tabla;
    public Mapa1() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 432);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        add(contentPane);
        setLayout(null);
        try {
            UIManager.setLookAndFeel(new HiFiLookAndFeel());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
        setTitle("mapa1");
        setResizable(false);
        botones = new JButton[etiquetas.length];
        /* El número 10 es la coordenada x inicial, y (i * 111) agrega un espacio fijo (111 unidades) entre cada botón.
           De esta manera, los botones se colocan uno al lado del otro con un espacio fijo.
		   339: Esta es la coordenada vertical (coordenada y) del botón. En este caso, todos los botones se colocan
		   a la misma altura en la coordenada y, 101 y 46: Estos son los valores que establecen el ancho y el alto del botón,
		   respectivamente. En este caso, los botones tienen un ancho de 101 unidades y una altura de 46 unidades.*/
        for (int i = 0; i < etiquetas.length; i++) {
            botones[i] = new JButton(etiquetas[i]);
            botones[i].setBounds(10 + (i * 111), 339, 101, 46);

            add(botones[i]);

        }
        tabla = new JTable();
        ver = new JScrollPane(tabla);
        ver.setBounds(10, 10, 546, 320);
        add(ver);
        ejecutar();
    }

    public void ejecutar() {
    	contr = new Controlador1(this);
    	contr.escuchar();
    }

}

