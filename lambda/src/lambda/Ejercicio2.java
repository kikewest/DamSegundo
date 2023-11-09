package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio2 {
	public static void main(String[] args) {
		List<String> nombres = new ArrayList<>();
		// Agrega nombres a la lista
		nombres.add("Ana");
		nombres.add("kike");
		nombres.add("Jesus");
		nombres.add("Alberto");
		nombres.add("Jose Ramon");

		// Utiliza una función lambda para mapear la longitud de los nombres
		List<Integer> longitudes = nombres.stream()// Inicia un flujo de datos a partir de la lista de números
				.map(nombre -> nombre.length()) // .map transforma cada elemento de la coleccion en otro elemento de la coleccion resultante
				//en este caso transforma la lista de nombres en una nueva lista que contiene las longitudes de los nombres en int
				.collect(Collectors.toList());// Recolecta los números primos filtrados en una lista

		// Muestra la lista de longitudes
		System.out.println("Longitudes de los nombres: " + longitudes);


		int sumaLongitudes = longitudes.stream().mapToInt(Integer::intValue).sum();// Calcula la suma

		// Muestra la suma de las longitudes
		System.out.println("Suma de las longitudes: " + sumaLongitudes);
	}
}
