package lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio5SinMapa {
	public static void main(String[] args) {
        List<Integer> numeros = IntStream.range(1, 1001).boxed().collect(Collectors.toList());

        int tamañoParte = numeros.size() / 4;
        List<Integer> parte1 = numeros.parallelStream()//significa que el procesamiento de los elementos en el flujo se puede realizar en paralelo,
        		//utilizando múltiples núcleos de CPU si están disponibles.
                .limit(tamañoParte)
                .collect(Collectors.toList());

        List<Integer> parte2 = numeros.parallelStream()
                .skip(tamañoParte)
                .limit(tamañoParte)
                .collect(Collectors.toList());

        List<Integer> parte3 = numeros.parallelStream()
                .skip(2 * tamañoParte)
                .limit(tamañoParte)
                .collect(Collectors.toList());

        List<Integer> parte4 = numeros.parallelStream()
                .skip(3 * tamañoParte)
                .collect(Collectors.toList());

        int sumaParte1 = parte1.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();

        int sumaParte2 = parte2.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();

        int sumaParte3 = parte3.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();

        int sumaParte4 = parte4.parallelStream()
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
    }
}

