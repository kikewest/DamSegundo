package Hash;

import java.util.HashMap;
import java.util.*;
import java.util.Scanner;

public class HashMapa {

	public static void main(String[] args) {
		Map<Integer, String> hashMap = new HashMap<>(10);
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Menu Hash:");
			System.out.println("1. Insertar dato");
			System.out.println("2. Eliminar dato");
			System.out.println("3. Mostrar colección de datos");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opcion: ");

			int opcion = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcion) {
			case 1:
				if (hashMap.size() >= 10) {
					System.out.println("El HashMap esta lleno. No se pueden insertr mas datos.");
				} else {
					System.out.print("Introduzca la clave: ");
					int clave = scanner.nextInt();
					System.out.print("Introduzca el valor: ");
					String valor = scanner.next();
					hashMap.put(clave, valor);
					System.out.println("Dato insertado.");
					
					
				}
				break;
			case 2:
				System.out.print("Introduzca la clave a eliminar: ");
				int claveEliminar = scanner.nextInt();
				if (hashMap.containsKey(claveEliminar)) {
					hashMap.remove(claveEliminar);
					System.out.println("Dato eliminado.");
				} else {
					System.out.println("La clave no existe en el HashMap.");
				}
				break;
			case 3:
				System.out.println("Coleccion de datos:");
				for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
					System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
				}
				break;
			case 4:
				System.out.println("Saliendo del programa.");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
			}
		}
	}
}