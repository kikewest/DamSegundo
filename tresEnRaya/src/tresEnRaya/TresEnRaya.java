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
/**
 * La clase TresEnRaya representa el juego del Tres en Raya.
 * Permite jugar en modo de dos jugadores o contra la computadora.
 * El juego incluye funciones para reiniciar, cargar y guardar partidas, así como
 * mostrar puntuaciones y proporcionar ayuda.
 *
 * @author ebellidoperejil2@gmail.com
 * @version 1.0
 */
public class TresEnRaya extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton[][] buttons;
	private boolean turnoJugador; // true: jugador, false: computadora
	private int partidasGanadasJugador;
	private int partidasGanadasComputadora;
	private JTextField puntuacionesField;
	private boolean modoDosJugadores;
	/**
     * Constructor de la clase TresEnRaya.
     *
     * @param modoDosJugadores Indica si el juego empieza en modo de dos jugadores o contra la computadora.
     */
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
		
		JButton ayudaButton = new JButton("Ayuda");
		ayudaButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        mostrarAyuda();
		    }
		});
		JButton cambiarModoButton = new JButton("Cambiar Modo");
		cambiarModoButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        cambiarModoJuego();
		    }
		});
		
		controlPanel.add(reiniciarButton);
		controlPanel.add(reiniciarPuntuacionesButton);
		controlPanel.add(nuevaPartidaButton);
		controlPanel.add(cargarPartidaButton);
		controlPanel.add(ayudaButton);
		controlPanel.add(puntuacionesField);
		controlPanel.add(cambiarModoButton);

		// Agregar paneles al contenido principal
		contentPane.add(tableroPanel);
		contentPane.add(controlPanel);

		setContentPane(contentPane);
	}
	/**
	 * Clase interna que implementa la interfaz ActionListener para manejar los eventos de clic en los botones del tablero.
	 * Esta clase se utiliza para gestionar los clics en las celdas del tablero y realizar las acciones correspondientes.
	 * @param fila La fila de la celda del botón.
     * @param columna La columna de la celda del botón.
     * @param e El evento de acción generado por el clic en el botón.
	 */
	private class BotonClickListener implements ActionListener {
		private int fila;
		private int columna;

		public BotonClickListener(int fila, int columna) {
			this.fila = fila;
			this.columna = columna;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
		    JButton clickedButton = (JButton) e.getSource();

		    // Verificar si el botón ya está ocupado
		    if (!clickedButton.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Celda ocupada. Elige otra.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    if (modoDosJugadores) {
		        // Modo dos jugadores
		        if (turnoJugador) {
		            clickedButton.setText("X");
		            if (verificarGanador("X")) {
		                partidasGanadasJugador++;
		                actualizarPuntuaciones();
		                reiniciarJuego();
		            } else if (tableroCompleto()) {
		                JOptionPane.showMessageDialog(null, "¡Empate!");
		                reiniciarJuego();
		            } else {
		                turnoJugador = false;
		            }
		        } else {
		            // En el modo dos jugadores, permite que el segundo jugador marque con "O"
		            clickedButton.setText("O");
		            if (verificarGanador("O")) {
		                partidasGanadasComputadora++;
		                actualizarPuntuaciones();
		                JOptionPane.showMessageDialog(null, "¡Jugador 2 ha ganado!");
		                reiniciarJuego();
		            } else if (tableroCompleto()) {
		                JOptionPane.showMessageDialog(null, "¡Empate!");
		                reiniciarJuego();
		            } else {
		                turnoJugador = true;
		            }
		        }
		    } else {
		        // Modo contra la computadora (código existente)
		        clickedButton.setText("X");
		        if (verificarGanador("X")) {
		            partidasGanadasJugador++;
		            actualizarPuntuaciones();
		            reiniciarJuego();
		        } else if (tableroCompleto()) {
		            JOptionPane.showMessageDialog(null, "¡Empate!");
		            reiniciarJuego();
		        } else {
		            // Solo realiza el movimiento de la computadora si el juego no ha terminado
		            realizarMovimientoComputadora();
		            if (verificarGanador("O")) {
		                partidasGanadasComputadora++;
		                actualizarPuntuaciones();
		                JOptionPane.showMessageDialog(null, "¡La computadora ha ganado!");
		                reiniciarJuego();
		            } else if (tableroCompleto()) {
		                JOptionPane.showMessageDialog(null, "¡Empate!");
		                reiniciarJuego();
		            }
		        }
		    }
		}
    }
	/**
	 * Cambia el modo de juego entre Dos Jugadores y Jugar contra la Máquina.
	 *
	 * <p>La función pregunta al usuario el nuevo modo de juego mediante un cuadro de diálogo.
	 * Solo se realiza el cambio si el nuevo modo es diferente del modo actual.</p>
	 *
	 * <p><strong>Precondición:</strong> Ninguna.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se actualiza el modo de juego y se reinicia el juego si el modo cambia.</p>
	 */
	private void cambiarModoJuego() {
	    int nuevoModo = preguntarModo();
	    if ((modoDosJugadores && nuevoModo == 2) || (!modoDosJugadores && nuevoModo == 1)) {
	        // Solo cambia el modo si es diferente del modo actual
	        modoDosJugadores = !modoDosJugadores;
	        reiniciarJuego();
	    }
	}
	/**
	 * Muestra un cuadro de diálogo de ayuda que proporciona información sobre cómo jugar y preguntas frecuentes.
	 *
	 * <p>El cuadro de diálogo incluye detalles sobre los modos de juego, las reglas y cómo gestionar partidas.</p>
	 *
	 * <p><strong>Precondición:</strong> Ninguna.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se muestra un cuadro de diálogo de ayuda en la interfaz gráfica del juego.</p>
	 */
	private void mostrarAyuda() {
	    String ayudaMensaje = "Bienvenido a Tres en Raya - Gana si Puedes!\n\n" +
	            "Cómo jugar:\n" +
	            "1. Selecciona un modo de juego: Dos Jugadores o Jugar contra la Máquina.\n" +
	            "2. En el modo Dos Jugadores, los jugadores se turnan para hacer clic en las celd0.as.\n" +
	            "3. En el modo Jugar contra la Máquina, haces clic y la máquina hará su movimiento.\n" +
	            "4. Gana el jugador que complete primero una fila, columna o diagonal con su símbolo.\n" +
	            "5. Puedes reiniciar el juego, las puntuaciones o cargar/guardar partidas.\n\n" +
	            "Preguntas Frecuentes (FAQs):\n" +
	            "Q: ¿Cómo se juega contra la Máquina?\n" +
	            "A: En el modo Jugar contra la Máquina, haces clic y la máquina hará su mejor movimiento.\n\n" +
	            "Q: ¿Cómo guardo y cargo partidas?\n" +
	            "A: Utiliza los botones de Nueva Partida y Cargar Partida, para gestionar partidas.\n\n" +
	            "¡Diviértete jugando Tres en Raya!";

	    JOptionPane.showMessageDialog(this, ayudaMensaje, "Ayuda y FAQs", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Realiza el movimiento de la computadora en el juego contra la máquina.
	 *
	 * <p>La computadora utiliza el algoritmo minimax para determinar el mejor movimiento posible.</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en progreso y ser el turno de la computadora.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se realiza el mejor movimiento posible por parte de la computadora.</p>
	 */
	private void realizarMovimientoComputadora() {
		int[] mejorMovimiento = encontrarMejorMovimiento();
		buttons[mejorMovimiento[0]][mejorMovimiento[1]].setText("O");
	}
	/**
	 * Verifica si un jugador ha ganado el juego.
	 *
	 * <p>Comprueba las filas, columnas y diagonales del tablero para determinar si un jugador
	 * ha completado una línea con su símbolo.</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en progreso.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se devuelve true si el jugador especificado ha ganado, de lo contrario, se devuelve false.</p>
	 *
	 * @param jugador El símbolo del jugador a verificar ("X" o "O").
	 * @return true si el jugador ha ganado, false de lo contrario.
	 */
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
	/**
	 * Encuentra el mejor movimiento posible para la computadora utilizando el algoritmo minimax.
	 *
	 * <p>Itera sobre todas las posiciones vacías en el tablero, realiza un movimiento simulado
	 * y utiliza el algoritmo minimax para evaluar la puntuación resultante. Selecciona el
	 * movimiento que maximiza la puntuación para la computadora "O".</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en progreso y tener al menos una celda vacía.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se devuelve un array con las coordenadas [fila, columna] del mejor movimiento.</p>
	 *
	 * @return Un array con las coordenadas [fila, columna] del mejor movimiento para la computadora.
	 */
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
	/**
	 * Implementa el algoritmo minimax para determinar la mejor puntuación posible en un estado dado del juego.
	 *
	 * <p>El algoritmo minimax es utilizado para evaluar todos los posibles movimientos en el juego y seleccionar
	 * el movimiento que maximiza la puntuación para el jugador "O" (esMaximizador = true) o minimiza la puntuación
	 * para el jugador "X" (esMaximizador = false).</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en progreso.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se devuelve la mejor puntuación posible en el estado actual del juego.</p>
	 *
	 * @param profundidad La profundidad actual en el árbol de búsqueda del algoritmo minimax.
	 * @param esMaximizador Indica si se está maximizando (true) o minimizando (false) la puntuación.
	 * @return La mejor puntuación posible en el estado actual del juego.
	 */
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
	/**
	 * Verifica el resultado del juego, determinando si hay un ganador o un empate.
	 *
	 * <p>El método examina todas las filas, columnas y diagonales del tablero para identificar si algún jugador
	 * ha completado una línea con su símbolo ("O" para la computadora, "X" para el jugador) y, por lo tanto, ha ganado.
	 * Si se encuentra un ganador, se devuelve el símbolo del jugador ganador ("O" o "X"). Si no hay ganador y todas
	 * las celdas están ocupadas, se devuelve "Empate". En caso contrario, se devuelve una cadena vacía, indicando
	 * que el juego aún está en progreso.</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en progreso.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se devuelve el símbolo del jugador ganador ("O" o "X"), "Empate" o una cadena
	 * vacía según el estado del juego.</p>
	 *
	 * @return El símbolo del jugador ganador ("O" o "X"), "Empate" o una cadena vacía.
	 */
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
	/**
	 * Reinicia el juego limpiando el tablero y restableciendo el turno del jugador.
	 *
	 * <p>El método recorre todas las celdas del tablero, estableciendo el texto en cada botón a una cadena vacía.
	 * Además, restablece el turno del jugador a la posición inicial, permitiendo que el jugador que comienza
	 * la nueva partida sea el mismo que inició la anterior.</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en progreso.</p>
	 *
	 * <p><strong>Postcondición:</strong> El tablero se limpia, y el turno del jugador se restablece al valor inicial.</p>
	 */
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
	/**
	 * Inicia una nueva partida, reiniciando el tablero y restableciendo el turno del jugador.
	 * Solicita confirmación al usuario mediante un cuadro de diálogo antes de reiniciar el juego.
	 *
	 * <p>El método muestra un cuadro de diálogo de confirmación preguntando al usuario si desea comenzar
	 * una nueva partida. Si la respuesta es afirmativa, se reinicia el tablero y se restablece el turno
	 * del jugador a la posición inicial.</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en progreso.</p>
	 *
	 * <p><strong>Postcondición:</strong> El tablero se reinicia y el turno del jugador se restablece.</p>
	 */
	private void nuevaPartida() {
		int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas comenzar una nueva partida?",
				"Nueva Partida", JOptionPane.YES_NO_OPTION);
		if (confirmacion == JOptionPane.YES_OPTION) {
			reiniciarJuego();
		}
	}
	/**
	 * Carga una partida previamente guardada desde un archivo binario.
	 * Utiliza la deserialización de objetos para recuperar la información del juego.
	 * Muestra mensajes de éxito o error mediante cuadros de diálogo.
	 *
	 * <p>El método muestra un cuadro de diálogo de confirmación para que el usuario decida si desea
	 * cargar una partida existente. Si la respuesta es afirmativa, el método intenta leer un objeto de
	 * tipo {@code JuegoGuardado} desde el archivo "partida_guardada.dat" mediante un flujo de objetos.
	 * Luego, actualiza el estado del juego con la información cargada, incluyendo las puntuaciones y el
	 * tablero. Finalmente, muestra un cuadro de diálogo informativo indicando si la carga fue exitosa o
	 * si se produjo algún error.</p>
	 *
	 * <p><strong>Precondición:</strong> Debe existir un archivo "partida_guardada.dat" con una partida
	 * guardada previamente.</p>
	 *
	 * <p><strong>Postcondición:</strong> El juego se restaura al estado en el que se guardó la partida
	 * previamente, incluidas las puntuaciones y el tablero.</p>
	 *
	 * @see JuegoGuardado
	 */
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
	/**
	 * Guarda el estado actual del juego, incluidas las puntuaciones y el tablero, en un archivo.
	 * Utiliza la serialización de objetos para almacenar la información en un archivo binario.
	 * Muestra mensajes de éxito o error mediante cuadros de diálogo.
	 *
	 * <p>El método crea una instancia de la clase {@code JuegoGuardado}, que encapsula la información
	 * necesaria para restaurar el juego en un estado previo. Luego, guarda este objeto en un archivo
	 * binario utilizando un flujo de objetos.</p>
	 *
	 * <p>El tablero actual y las puntuaciones de los jugadores se capturan y almacenan en la instancia
	 * de {@code JuegoGuardado} antes de la serialización.</p>
	 *
	 * <p><strong>Precondición:</strong> El juego debe estar en un estado válido para ser guardado.</p>
	 *
	 * <p><strong>Postcondición:</strong> Se crea un archivo "partida_guardada.dat" que contiene el
	 * estado actual del juego.</p>
	 *
	 * @see JuegoGuardado
	 */
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
	/**
     * Método principal que inicia la aplicación.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
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
