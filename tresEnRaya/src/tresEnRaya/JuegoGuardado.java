package tresEnRaya;
import java.io.Serializable;
public class JuegoGuardado implements Serializable {
	private static final long serialVersionUID = 1L;

    public int partidasGanadasJugador;
    public int partidasGanadasComputadora;
    public String[][] tablero;

    public JuegoGuardado(int partidasGanadasJugador, int partidasGanadasComputadora) {
        this.partidasGanadasJugador = partidasGanadasJugador;
        this.partidasGanadasComputadora = partidasGanadasComputadora;
        this.tablero = new String[3][3];
    }
}
