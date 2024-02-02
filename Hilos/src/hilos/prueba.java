package hilos;

public class prueba {
	static long startTime = System.nanoTime(); // O puedes usar System.nanoTime();
	static long endTime = System.nanoTime();
	public static void Ceros() {
		long tiempoDeEjecución = endTime - startTime;
		for (int i=0;i<1000;i++){
			System.out.print(0+",");
		}
		System.out.println();
		System.out.println("Tiempo de ejecución de los 0: " + tiempoDeEjecución + " milisegundos"); // O en nanosegundos si usaste System.nanoTime()
	}

	public static void Unos() {
		long tiempoDeEjecución = endTime - startTime;
		for (int i=0;i<1000;i++){
			System.out.print(1+","); 
		}
		System.out.println();
		System.out.println("Tiempo de ejecución de los 1: " + tiempoDeEjecución + " milisegundos"); // O en nanosegundos si usaste System.nanoTime()
	}
	public static void main(String[] args) {
		prueba.Ceros();
		prueba.Unos();
	}

}
