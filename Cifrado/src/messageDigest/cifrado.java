package messageDigest;

import java.io.*;

public class cifrado {

    public static void establecerContraseña() {
    	//Scanner sc = new Scanner(System.in);
        try {
            FileWriter writer = new FileWriter("Cifrado.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            System.out.println("Establece una contraseña: ");
            String contraseña = new BufferedReader(new InputStreamReader(System.in)).readLine();

            bufferedWriter.write(contraseña);
            bufferedWriter.close();

            System.out.println("Contraseña almacenada con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
        }
    }

    public static void verificarContraseña() {
        try {
            FileReader fileReader = new FileReader("Cifrado.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Ingresa la contraseña: ");
            String contraseñaIngresada = new BufferedReader(new InputStreamReader(System.in)).readLine();
            String contraseñaAlmacenada = bufferedReader.readLine();

            bufferedReader.close();

            if (contraseñaAlmacenada != null && contraseñaAlmacenada.equals(contraseñaIngresada)) {
                System.out.println("Contraseña correcta. Acceso concedido.");
            } else {
                System.out.println("Contraseña incorrecta. Acceso denegado.");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo.");
        }
    }

    public static void main(String[] args) {
        File archivo = new File("Cifrado.txt");
        try {
            if (!archivo.exists()) {
                archivo.createNewFile(); // Crear el archivo si no existe
            }

            System.out.println("El archivo existe");


            while (true) {
                System.out.println("1. Establecer contraseña");
                System.out.println("2. Verificar contraseña");
                System.out.println("3. Salir");

                try {
                    String opcion = new BufferedReader(new InputStreamReader(System.in)).readLine();

                    if ("1".equals(opcion)) {
                        establecerContraseña();
                    } else if ("2".equals(opcion)) {
                        verificarContraseña();
                    } else if ("3".equals(opcion)) {
                        break;
                    } else {
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                    }
                } catch (IOException e) {
                    System.err.println("Error de entrada/salida.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo.");
        }
    }
}