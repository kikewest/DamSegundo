package lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio5SinMapa2 {
	public static void main(String[] args) {
		long startTime = System.nanoTime(); // O puedes usar System.nanoTime();
        List<Integer> numeros = IntStream.range(1, 100001).boxed().collect(Collectors.toList());

        int tamañoParte = numeros.size() / 4;
        List<Integer> parte1 = numeros.stream()//significa que el procesamiento de los elementos en el flujo se puede realizar en paralelo,
        		//utilizando múltiples núcleos de CPU si están disponibles.
                .limit(tamañoParte)
                .collect(Collectors.toList());

        List<Integer> parte2 = numeros.stream()
                .skip(tamañoParte)
                .limit(tamañoParte)
                .collect(Collectors.toList());

        List<Integer> parte3 = numeros.stream()
                .skip(2 * tamañoParte)
                .limit(tamañoParte)
                .collect(Collectors.toList());

        List<Integer> parte4 = numeros.stream()
                .skip(3 * tamañoParte)
                .collect(Collectors.toList());

        int sumaParte1 = parte1.stream()
                .mapToInt(Integer::intValue)
                .sum();

        int sumaParte2 = parte2.stream()
                .mapToInt(Integer::intValue)
                .sum();

        int sumaParte3 = parte3.stream()
                .mapToInt(Integer::intValue)
                .sum();

        int sumaParte4 = parte4.stream()
                .mapToInt(Integer::intValue)
                .sum();
        
        System.out.println("Parte 1: " + parte1);
        System.out.println("Parte 2: " + parte2);
        System.out.println("Parte 3: " + parte3);
        System.out.println("Parte 4: " + parte4);
        
        System.out.println("Suma de la Parte 1: " + sumaParte1);
        System.out.println("Suma de la Parte 2: " + sumaParte2);
        System.out.println("Suma de la Parte 3: " + sumaParte3);
        System.out.println("Suma de la Parte 4: " + sumaParte4);

        int sumaTotal = sumaParte1 + sumaParte2 + sumaParte3 + sumaParte4;
        System.out.println("Suma Total: " + sumaTotal);
        

        // Tu código a medir

        long endTime = System.nanoTime(); // O puedes usar System.nanoTime();

        long tiempoDeEjecución = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + tiempoDeEjecución + " milisegundos"); // O en nanosegundos si usaste System.nanoTime()
    }
}

