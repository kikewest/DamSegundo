package dardos;
import java.util.ArrayList;

public class Dardos {
	public static void main(String[] args) {
		// Definición de las regiones y otros parámetros del problema
	    int[] regiones = {10, 25, 50, 75};
	    int puntuacionTotal = 100;
	    int numDardos = 5;

	    // Genera e imprime las combinaciones con 1, 2, 3, 4, y 5 dardos
	    for (int i = 1; i <= numDardos; i++) {
	        // Llama a la función obtenerCombinacionesDardos para obtener las combinaciones con i dardos
	        ArrayList<ArrayList<Integer>> combinaciones = obtenerCombinacionesDardos(regiones, puntuacionTotal, i);

	        // Imprime el número de dardos y las combinaciones resultantes
	        System.out.println("Combinaciones con " + i + " dardos:");
	        for (ArrayList<Integer> combinacion : combinaciones) {
	            System.out.println(combinacion);
	        }
	        System.out.println();
	    }
	}

	public static ArrayList<ArrayList<Integer>> obtenerCombinacionesDardos(int[] regiones, int puntuacionTotal, int numDardos) {
	    // Lista para almacenar todas las combinaciones resultantes
	    ArrayList<ArrayList<Integer>> combinaciones = new ArrayList<>();
	    // Lista para almacenar temporalmente una combinación parcial
	    ArrayList<Integer> combinacionParcial = new ArrayList<>();

	    // Llama a la función generarCombinaciones para obtener las combinaciones recursivamente
	    generarCombinaciones(regiones, puntuacionTotal, numDardos, 0, combinacionParcial, combinaciones);

	    // Retorna la lista de combinaciones resultantes
	    return combinaciones;
	}

	public static void generarCombinaciones(int[] regiones, int puntuacionRestante, int numDardos, int indice, ArrayList<Integer> combinacionParcial, ArrayList<ArrayList<Integer>> combinaciones) {
	    // Caso base: hemos alcanzado el número deseado de dardos
	    if (numDardos == 0) {
	        // Verifica si la combinación es válida (puntuaciónRestante no es negativa)
	        if (puntuacionRestante >= 0) {
	            // La combinación es válida, la almacenamos en la lista de combinaciones
	            combinaciones.add(new ArrayList<>(combinacionParcial));
	        }
	        return;
	    }

	    // Bucle para explorar todas las regiones posibles
	    for (int i = 0; i < regiones.length; i++) {
	        // Intenta agregar la región actual al conjunto de dardos
	        combinacionParcial.add(regiones[i]);

	        // Llamada recursiva para generar combinaciones con las regiones restantes
	        generarCombinaciones(regiones, puntuacionRestante - regiones[i], numDardos - 1, i, combinacionParcial, combinaciones);

	        // Retrocede eliminando el último dardo para probar con otra región en el siguiente bucle
	        combinacionParcial.remove(combinacionParcial.size() - 1);
	    }
	}
}