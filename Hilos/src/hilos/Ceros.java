package hilos;

public class Ceros extends Thread{
	static long startTime = System.nanoTime(); // O puedes usar System.nanoTime();
	static long endTime = System.nanoTime();
	public  void run() {
		long tiempoDeEjecución = endTime - startTime;
		for (int i=0;i<1000;i++){
			System.out.print(0+",");
		}
		System.out.println();
		System.out.println("Tiempo de ejecución de los 0: " + tiempoDeEjecución + " milisegundos"); // O en nanosegundos si usaste System.nanoTime()
	}
}