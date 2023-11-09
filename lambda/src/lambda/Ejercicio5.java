package lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio5 {
	public static void main(String[] args) {
		/*IntStream.range(1, 1000000) crea una secuencia que incluye números desde 1 hasta 1000.
		 * .boxed() convierte los int en Integer, que es la versión envuelta (wrapper) de los enteros primitivos en Java.
		 * .collect(Collectors.toList()) colecciona los elementos del Stream<Integer> en una lista (List<Integer>).
		 */
		List<Integer> numeros = IntStream.range(1, 1001).boxed().collect(Collectors.toList());

		int tamañoParte = numeros.size() / 4;//dividimos el tamaño de nuestra lista entre 4 para dar tamaño a las 4 listas que contiene los datos de nuestra lista numeros
		//hacemos un mapa que contiene de key un integer y de valor las 4 listas 
		Map<Integer, List<Integer>> partes = IntStream.range(0, 4)//establecemos el rango inicial en 0 y final en 4
				.boxed()
				.collect(Collectors.toMap(
						parte -> parte,
						/*
						 * subList(parte * tamañoParte, (parte + 1) * tamañoParte): subList es un método de la clase List, y se utiliza para obtener una
						 *  vista de sublista de la lista original. En este caso, se está dividiendo la lista numeros en cuatro sublistas
						 */
						parte -> numeros.subList(parte * tamañoParte, (parte + 1) * tamañoParte)
						));

		// Imprimir cada parte
		partes.forEach((parte, lista) -> {
			System.out.println("Parte " + parte + ": " + lista);
		});

		partes.forEach((parte, lista) -> {
			int sumaParte = lista.parallelStream()
					.mapToInt(Integer::intValue)//está convirtiendo cada elemento del Stream de Integer a int utilizando la referencia al método Integer::intValue.
					.sum();//calcula la suma de los elementos en el Stream. 

			System.out.println("Suma de la Parte " + parte + ": " + sumaParte);
		});

		// Calcular la suma total de todas las partes
		int sumaTotal = partes.values().parallelStream()
				.flatMap(List::stream)//flatmap combina todas las sublistas en un solo Stream de Integer para calcular la suma total de todas las partes.
				.mapToInt(Integer::intValue)
				.sum();

		System.out.println("Suma Total: " + sumaTotal);
	}
}