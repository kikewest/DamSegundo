package carreras;

public class Relevos {
	public static void main(String[] args) {
        Testigo testigo = new Testigo();

        // Crear 4 corredores
        Thread[] corredores = new Thread[4];
        for (int i = 0; i < 4; i++) {
            corredores[i] = new Thread(new Corredor(testigo, i + 1));
        }

        // Iniciar los hilos de los corredores
        for (Thread corredor : corredores) {
            corredor.start();
        }

        // Esperar a que todos los corredores terminen
        try {
            for (Thread corredor : corredores) {
                corredor.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("La carrera ha terminado.");
    }
}
