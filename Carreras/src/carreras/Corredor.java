package carreras;

public class Corredor implements Runnable {
	private final Testigo testigo;
    private final int numeroCorredor;

    public Corredor(Testigo testigo, int numeroCorredor) {
        this.testigo = testigo;
        this.numeroCorredor = numeroCorredor;
    }

    @Override
    public void run() {
        try {
            synchronized (testigo) {
                testigo.pasarTestigo();
                System.out.println("Corredor " + numeroCorredor + " empieza a correr.");

                // Simulación de la carrera (realizar alguna tarea)
                Thread.sleep(2000);

                System.out.println("Corredor " + numeroCorredor + " ha terminado.");

                // Esperar un poco antes de pasar el testigo al siguiente
                Thread.sleep(1000);
                testigo.soltarTestigo();
                System.out.println("Corredor " + numeroCorredor + " ha pasado el testigo al siguiente corredor.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
