package lambda;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class Ejercicio4 {
	public static void main(String[] args) {
        // Crear un archivo CSV y escribir datos de productos
        String archivoCSV = "productos.csv"; // Nombre del archivo CSV a crear

        try (FileWriter writer = new FileWriter(archivoCSV);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            // Definir los encabezados del CSV
            String[] encabezados = {"ID", "Nombre", "Precio"};
            csvWriter.writeNext(encabezados);

            // Agregar datos de productos
            String[] producto1 = {"1", "Peras", "10.00"};
            String[] producto2 = {"2", "Manzanas", "15.50"};
            String[] producto3 = {"3", "Uvas", "8.75"};

            // Escribir los datos de productos en el archivo CSV
            csvWriter.writeNext(producto1);
            csvWriter.writeNext(producto2);
            csvWriter.writeNext(producto3);

            System.out.println("Archivo CSV creado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo CSV y calcular el precio promedio
        try (FileReader reader = new FileReader(archivoCSV);
             CSVReader csvReader = new CSVReader(reader)) {

            // Usar una función lambda para calcular el precio promedio
            double precioPromedio = 0;
			try {
				precioPromedio = csvReader.readAll().stream()
				        .skip(1) // Saltar la primera fila de encabezados
				        .mapToDouble(row -> Double.parseDouble(row[2])) // Suponiendo que la columna 2 contiene precios
				        .average()//calcular el valor promedio de elementos en un flujo de datos numérico
				        .orElse(0);//si no se calcula ningun promedio sea por lo que sea el resultado sera 0
			} catch (CsvException e) {
				e.printStackTrace();
			}

            System.out.println("Precio promedio de los productos: " + String.format("%.2f", precioPromedio));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}