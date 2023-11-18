package tresEnRaya;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TresEnRaya extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton[][] buttons;
    private boolean turnoJugador; // true: jugador, false: computadora
    private int partidasGanadasJugador;
    private int partidasGanadasComputadora;
    private JTextField puntuacionesField;

    public TresEnRaya() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setTitle("Tres En Raya Gana!'si Puedes :P'");
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(1, 2));

        // Panel para el tablero del juego
        JPanel tableroPanel = new JPanel();
        tableroPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        turnoJugador = true;

        // Inicializar los botones y agregarlos al panel
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].addActionListener(new BotonClickListener(i, j));
                tableroPanel.add(buttons[i][j]);
            }
        }

        // Panel para el control (reinicio y puntuaciones)
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));

        JButton reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });

        JButton reiniciarPuntuacionesButton = new JButton("Reiniciar Puntuaciones");
        reiniciarPuntuacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarPuntuaciones();
            }
        });

        puntuacionesField = new JTextField();
        puntuacionesField.setFont(new Font("Tahoma", Font.BOLD, 10));
        puntuacionesField.setHorizontalAlignment(SwingConstants.CENTER);
        puntuacionesField.setEditable(false);
        actualizarPuntuaciones();

        controlPanel.add(reiniciarButton);
        controlPanel.add(reiniciarPuntuacionesButton);
        controlPanel.add(puntuacionesField);

        // Agregar paneles al contenido principal
        contentPane.add(tableroPanel);
        contentPane.add(controlPanel);

        setContentPane(contentPane);
    }

    private class BotonClickListener implements ActionListener {
        private int fila;
        private int columna;

        public BotonClickListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (turnoJugador && buttons[fila][columna].getText().isEmpty()) {
                buttons[fila][columna].setText("X");
                if (verificarGanador("X")) {
                    partidasGanadasJugador++;
                    actualizarPuntuaciones();
                    reiniciarJuego();
                } else {
                    turnoJugador = false;
                    if (tableroCompleto()) {
                        JOptionPane.showMessageDialog(null, "¡Empate!");
                        reiniciarJuego();
                    } else {
                        realizarMovimientoComputadora();
                        if (verificarGanador("O")) {
                            partidasGanadasComputadora++;
                            actualizarPuntuaciones();
                            JOptionPane.showMessageDialog(null, "¡La computadora ha ganado!");
                            reiniciarJuego();
                        } else {
                            turnoJugador = true;
                        }
                    }
                }
            }
        }
    }

    private void realizarMovimientoComputadora() {
        int[] mejorMovimiento = encontrarMejorMovimiento();
        buttons[mejorMovimiento[0]][mejorMovimiento[1]].setText("O");
    }

    private boolean verificarGanador(String jugador) {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(jugador) && buttons[i][1].getText().equals(jugador)
                    && buttons[i][2].getText().equals(jugador))
                    || (buttons[0][i].getText().equals(jugador) && buttons[1][i].getText().equals(jugador)
                            && buttons[2][i].getText().equals(jugador))) {
                return true;
            }
        }

        if ((buttons[0][0].getText().equals(jugador) && buttons[1][1].getText().equals(jugador)
                && buttons[2][2].getText().equals(jugador))
                || (buttons[0][2].getText().equals(jugador) && buttons[1][1].getText().equals(jugador)
                        && buttons[2][0].getText().equals(jugador))) {
            return true;
        }

        return false;
    }

    private boolean tableroCompleto() {
        // Verificar si todas las celdas del tablero están ocupadas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false; // Hay al menos una celda vacía
                }
            }
        }
        return true; // Todas las celdas están ocupadas
    }

    private int[] encontrarMejorMovimiento() {
        int[] mejorMovimiento = {-1, -1};
        int mejorPuntuacion = Integer.MIN_VALUE;

        // Iterar sobre todas las posiciones vacías y evaluar el movimiento
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    buttons[i][j].setText("O");
                    int puntuacion = minimax(0, false);
                    buttons[i][j].setText("");

                    if (puntuacion > mejorPuntuacion) {
                        mejorPuntuacion = puntuacion;
                        mejorMovimiento[0] = i;
                        mejorMovimiento[1] = j;
                    }
                }
            }
        }

        return mejorMovimiento;
    }

    private int minimax(int profundidad, boolean esMaximizador) {
        String resultado = verificarResultado();

        if (resultado.equals("O")) {
            return 1;
        } else if (resultado.equals("X")) {
            return -1;
        } else if (resultado.equals("Empate")) {
            return 0;
        }

        if (esMaximizador) {
            int mejorPuntuacion = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().isEmpty()) {
                        buttons[i][j].setText("O");
                        mejorPuntuacion = Math.max(mejorPuntuacion, minimax(profundidad + 1, !esMaximizador));
                        buttons[i][j].setText("");
                    }
                }
            }
            return mejorPuntuacion;
        } else {
            int mejorPuntuacion = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().isEmpty()) {
                        buttons[i][j].setText("X");
                        mejorPuntuacion = Math.min(mejorPuntuacion, minimax(profundidad + 1, !esMaximizador));
                        buttons[i][j].setText("");
                    }
                }
            }
            return mejorPuntuacion;
        }
    }

    private String verificarResultado() {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals("O") && buttons[i][1].getText().equals("O")
                    && buttons[i][2].getText().equals("O"))
                    || (buttons[0][i].getText().equals("O") && buttons[1][i].getText().equals("O")
                            && buttons[2][i].getText().equals("O"))
                    || (buttons[0][0].getText().equals("O") && buttons[1][1].getText().equals("O")
                            && buttons[2][2].getText().equals("O"))
                    || (buttons[0][2].getText().equals("O") && buttons[1][1].getText().equals("O")
                            && buttons[2][0].getText().equals("O"))) {
                return "O";
            } else if ((buttons[i][0].getText().equals("X") && buttons[i][1].getText().equals("X")
                    && buttons[i][2].getText().equals("X"))
                    || (buttons[0][i].getText().equals("X") && buttons[1][i].getText().equals("X")
                            && buttons[2][i].getText().equals("X"))
                    || (buttons[0][0].getText().equals("X") && buttons[1][1].getText().equals("X")
                            && buttons[2][2].getText().equals("X"))
                    || (buttons[0][2].getText().equals("X") && buttons[1][1].getText().equals("X")
                            && buttons[2][0].getText().equals("X"))) {
                return "X";
            }
        }

        // Verificar empate
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return ""; // Todavía hay movimientos posibles
                }
            }
        }

        return "Empate"; // Todos los espacios están ocupados y no hay un ganador
    }

    private void reiniciarJuego() {
        // Limpiar el tablero y reiniciar el turno
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        turnoJugador = true;
    }

    private void reiniciarPuntuaciones() {
        partidasGanadasJugador = 0;
        partidasGanadasComputadora = 0;
        actualizarPuntuaciones();
    }

    private void actualizarPuntuaciones() {
        puntuacionesField.setText("Partidas Ganadas:\nJugador: " + partidasGanadasJugador + "\nComputadora: "
                + partidasGanadasComputadora);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TresEnRaya frame = new TresEnRaya();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}