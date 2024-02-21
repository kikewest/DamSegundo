package carreras;

public class Testigo {
	private boolean testigoDisponible = true;

    public synchronized void pasarTestigo() {
        while (!testigoDisponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        testigoDisponible = false;
        notify();
    }

    public synchronized void soltarTestigo() {
        testigoDisponible = true;
        notify();
    }
}
