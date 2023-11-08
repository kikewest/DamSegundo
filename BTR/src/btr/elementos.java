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