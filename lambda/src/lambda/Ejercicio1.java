package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1 {
	public static void main(String[] args) {
		List<Integer> numeros = new ArrayList<>();
		// Agrega tus números enteros a la lista
		numeros.add(2);
		numeros.add(7);
		numeros.add(15);
		numeros.add(11);
		numeros.add(4);
		numeros.add(19);
		numeros.add(23);
		numeros.add(33);
		numeros.add(27);
		numeros.add(57);
		numeros.add(43);
		numeros.add(219);

		// Utiliza una función lambda para filtrar los números primos
		List<Integer> primosFiltrados = numeros.stream()  // Inicia un flujo de datos a partir de la lista de números
				.filter(numero -> esPrimo(numero))  // Filtra los números primos usando una función lambda
				.sorted((num1, num2) -> Integer.compare(num2, num1))  // Ordena de mayor a menor
				.collect(Collectors.toList());  // Recolecta los números primos filtrados en una lista

		// Imprime los números primos resultantes
		primosFiltrados.forEach(System.out::println);  // Imprime cada número primo en la consola
	}

	// Función para verificar si un número es primo
	public static boolean esPrimo(int numero) {
		if (numero <= 1) {
			return false;
		}
		if (numero <= 3) {
			return true;
		}
		if (numero % 2 == 0 || numero % 3 == 0) {
			return false;
		}
		for (int i = 5; i * i <= numero; i += 6) {
			if (numero % i == 0 || numero % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}
}
