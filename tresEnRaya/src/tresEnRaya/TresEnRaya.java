package tresEnRaya;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
	private boolean modoDosJugadores;

	public TresEnRaya(boolean modoDosJugadores) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setTitle("Tres En Raya Gana!'si Puedes :P'");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1, 2));
		this.modoDosJugadores = modoDosJugadores;

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

		// Panel para el control (reinicio, puntuaciones, nueva partida y cargar partida)
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(4, 1));

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

		JButton nuevaPartidaButton = new JButton("Nueva Partida");
		nuevaPartidaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(TresEnRaya.this, "¿Deseas comenzar una nueva partida?\n La partida actual se guardara",
						"Nueva Partida", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					guardarPartida();
					nuevaPartida();
				}
			}
		});

		JButton cargarPartidaButton = new JButton("Cargar Partida");
		cargarPartidaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				cargarPartida();
			}
		});

		puntuacionesField = new JTextField();
		puntuacionesField.setFont(new Font("Tahoma", Font.BOLD, 10));
		puntuacionesField.setHorizontalAlignment(SwingConstants.CENTER);
		puntuacionesField.setEditable(false);
		actualizarPuntuaciones();

		controlPanel.add(reiniciarButton);
		controlPanel.add(reiniciarPuntuacionesButton);
		controlPanel.add(nuevaPartidaButton);
		controlPanel.add(cargarPartidaButton);
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
                    turnoJugador = !modoDosJugadores; // Si es modo dos jugadores, no cambia el turno
                    if (tableroCompleto()) {
                        JOptionPane.showMessageDialog(null, "¡Empate!");
                        reiniciarJuego();
                    } else if (!modoDosJugadores) {
                        realizarMovimientoComputadora();
                        if (verificarGanador("O")) {
                            partidasGanadasComputadora++;
                            actualizarPuntuaciones();
                            JOptionPane.showMessageDialog(null, "¡La computadora ha ganado!");
                            reiniciarJuego();
                        } else {
                        	turnoJugador = !turnoJugador;
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

	private void nuevaPartida() {
		int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas comenzar una nueva partida?",
				"Nueva Partida", JOptionPane.YES_NO_OPTION);
		if (confirmacion == JOptionPane.YES_OPTION) {
			reiniciarJuego();
		}
	}

	private void cargarPartida() {
		int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas cargar una partida existente?", "Cargar Partida", JOptionPane.YES_NO_OPTION);
		if (confirmacion == JOptionPane.YES_OPTION) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("partida_guardada.dat"))) {
				// Lee el estado guardado del juego desde el archivo
				JuegoGuardado juegoGuardado = (JuegoGuardado) ois.readObject();

				// Actualiza el juego con la información cargada
				partidasGanadasJugador = juegoGuardado.partidasGanadasJugador;
				partidasGanadasComputadora = juegoGuardado.partidasGanadasComputadora;
				actualizarPuntuaciones();

				// Actualiza el tablero
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setText(juegoGuardado.tablero[i][j]);
					}
				}

				JOptionPane.showMessageDialog(this, "Partida cargada exitosamente.", "Cargar Partida", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "No se encontró el archivo de la partida guardada.", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al cargar la partida.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void guardarPartida() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("partida_guardada.dat"))) {
			// Guarda el estado actual del juego en un archivo
			JuegoGuardado juegoGuardado = new JuegoGuardado(partidasGanadasJugador, partidasGanadasComputadora);

			// Guarda el tablero actual
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					juegoGuardado.tablero[i][j] = buttons[i][j].getText();
				}
			}

			oos.writeObject(juegoGuardado);

			JOptionPane.showMessageDialog(this, "Partida guardada exitosamente.", "Guardar Partida", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar la partida.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static int preguntarModo() {
		String[] opciones = {"Dos Jugadores", "Jugar contra la Máquina"};
		int respuesta = JOptionPane.showOptionDialog(
				null,
				"Selecciona el modo de juego:",
				"Modo de Juego",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opciones,
				opciones[0]
				);

		// Ajusta la respuesta del usuario a la forma en que quieres representar los modos
		// Devuelve 1 para Dos Jugadores y 2 para Jugar contra la Máquina
		return respuesta + 1;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Pregunta al usuario por el modo de juego
					int modo = preguntarModo();

					TresEnRaya frame;
					if (modo == 1) {
						// Modo 1: Dos jugadores
						frame = new TresEnRaya(true);
					} else {
						// Modo 2: Jugar contra la máquina
						frame = new TresEnRaya(false);
					}

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
