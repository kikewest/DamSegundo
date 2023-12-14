package btr;

import java.util.ArrayList;
import java.util.Random;

public class elementos {
	public static void main(String[] args) {
        // Generamos un array de 25 pesos aleatorios no mayores que 50
        int[] pesos = generarPesos(25, 50);
        int capacidadMochila = 15;
        //int[] pesos = {
          //      3, 5, 7, 2, 10, 15, 30, 8, 12, 9, 6, 18, 25, 17, 11, 22, 4, 16, 20, 13, 14, 1, 19, 27, 35
            //};
            //int capacidadMochila = 15;
        /*La razón para utilizar ArrayList<ArrayList<Integer>> soluciones es que necesitamos almacenar múltiples soluciones válidas,
         *y cada solución es una lista de enteros que representa los pesos de los elementos seleccionados en la mochila.
         *es un array list que contiene otro arraylist de integer*/
        ArrayList<ArrayList<Integer>> soluciones = new ArrayList<>();
        ArrayList<Integer> solucionParcial = new ArrayList<>();
        mochilaBacktracking(pesos, capacidadMochila, 0, 0, solucionParcial, soluciones);

        // Imprimimos todas las soluciones válidas
        for (ArrayList<Integer> solucion : soluciones) {
            System.out.println("Solución válida: " + solucion);
        }
    }
	//metodo que genera un array de int haciendo referencia a los pesos que vamos a meter en nuestra mochila
    public static int[] generarPesos(int cantidad, int maxValor) {
        int[] pesos = new int[cantidad];
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            pesos[i] = random.nextInt(maxValor + 1); // Genera un número aleatorio entre 0 y maxValor
        }

        return pesos;
    }

    public static void mochilaBacktracking(int[] pesos, int capacidadMochila, int indice, int pesoActual, ArrayList<Integer> solucionParcial, ArrayList<ArrayList<Integer>> soluciones) {
        if (indice == pesos.length) {//si tenemos los pesos disponibles para nuestra mochila que empieza en 0
            // Hemos llegado al final de la lista de elementos
            if (pesoActual <= capacidadMochila) {
                // La solución es válida, la almacenamos en la lista de soluciones
                soluciones.add(new ArrayList<>(solucionParcial));
            }
            return;
        }

        // Intenta agregar el elemento actual a la mochila
        if (pesoActual + pesos[indice] <= capacidadMochila) {
            solucionParcial.add(pesos[indice]);
            mochilaBacktracking(pesos, capacidadMochila, indice + 1, pesoActual + pesos[indice], solucionParcial, soluciones);
            solucionParcial.remove(solucionParcial.size() - 1); // Retrocede eliminando el último elemento
        }

        // Prueba sin agregar el elemento actual
        mochilaBacktracking(pesos, capacidadMochila, indice + 1, pesoActual, solucionParcial, soluciones);
    }
}
/*package dardos;
import java.util.ArrayList;
import java.util.List;

public class Dardos {
	 public static void main(String[] args) {
	        int[] zonas = {10, 25, 50, 75};
	        int dardos = 5;

	        List<List<List<Integer>>> combinaciones = obtenerCombinacionesDardos(zonas, dardos);

	        // Imprimir las combinaciones según la cantidad de dardos
	        for (int i = 0; i < combinaciones.size(); i++) {
	            System.out.println("Combinaciones con " + (i + 1) + " dardos:");
	            for (List<Integer> lanzamientos : combinaciones.get(i)) {
	                System.out.print(lanzamientos + " ");
	            }
	            System.out.println("\n");
	        }
	    }

	    public static List<List<List<Integer>>> obtenerCombinacionesDardos(int[] zonas, int dardos) {
	        List<List<List<Integer>>> result = new ArrayList<>();
	        for (int i = 0; i <= dardos; i++) {
	            result.add(new ArrayList<>());
	        }
	        List<Integer> combinacionActual = new ArrayList<>();
	        dardosV3(result, zonas, dardos, 0, combinacionActual);
	        return result;
	    }

	    public static void dardosV3(List<List<List<Integer>>> result, int[] zonas, int dardos,
	                                int etapa, List<Integer> combinacionActual) {
	        if (etapa == zonas.length) {
	            if (dardos == 0) {
	                result.get(combinacionActual.size() - 1).add(new ArrayList<>(combinacionActual));
	            }
	        } else {
	            for (int d = 0; d <= dardos; d++) {
	                combinacionActual.add(zonas[etapa]);
	                dardosV3(result, zonas, dardos - d, etapa + 1, combinacionActual);
	                combinacionActual.remove(combinacionActual.size() - 1);
	            }
	            // Agregamos el caso donde no se lanza ningún dardo en esta zona
	            dardosV3(result, zonas, dardos, etapa + 1, combinacionActual);
	        }
	    }
	}*/