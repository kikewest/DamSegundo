package messageDigest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class cifradoMessageDigest {
	// La sal es una cadena que se usa para fortalecer el hash de las contraseñas.
    // En una aplicación real, deberías usar una sal diferente y secreta para cada usuario.
    private static final String SALT = "MiValorDeSal";

    // Método para hashear una contraseña utilizando el algoritmo SHA-256 con una sal.
    public static String hashearContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT.getBytes()); // Se agrega la sal a la contraseña
            byte[] bytes = md.digest(contrasena.getBytes());

            // Convertir el hash resultante en una representación hexadecimal
            StringBuilder builder = new StringBuilder();
            for (byte aByte : bytes) {
                builder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para establecer una contraseña
    public static void establecerContrasena() {
        try {
            FileWriter writer = new FileWriter("ContrasenaHasheada.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            System.out.println("Establece una contraseña: ");
            String contrasena = new Scanner(System.in).nextLine();
            String contrasenaHasheada = hashearContrasena(contrasena);

            // Escribe la contraseña hasheada en un archivo
            bufferedWriter.write(contrasenaHasheada);
            bufferedWriter.close();

            System.out.println("Contraseña almacenada con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
        }
    }

    // Método para verificar si la contraseña ingresada coincide con la almacenada
    public static boolean verificarContrasena(String contrasenaIngresada, String contrasenaHasheadaAlmacenada) {
        String contrasenaHasheadaIngresada = hashearContrasena(contrasenaIngresada);
        return contrasenaHasheadaAlmacenada.equals(contrasenaHasheadaIngresada);
    }

    // Método para comprobar la contraseña
    public static void comprobarContrasena() {
        try {
            FileReader fileReader = new FileReader("ContrasenaHasheada.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Ingresa la contraseña: ");
            String contrasenaIngresada = new Scanner(System.in).nextLine();
            String contrasenaHasheadaAlmacenada = bufferedReader.readLine();

            bufferedReader.close();

            // Compara la contraseña ingresada con la almacenada
            if (verificarContrasena(contrasenaIngresada, contrasenaHasheadaAlmacenada)) {
                System.out.println("Contraseña correcta. Acceso concedido.");
            } else {
                System.out.println("Contraseña incorrecta. Acceso denegado.");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo.");
        }
    }

    public static void main(String[] args) {
        // Comprobar si el archivo de contraseñas existe y crearlo si no existe
        File archivo = new File("ContrasenaHasheada.txt");
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            System.out.println("El archivo existe");

            while (true) {
                System.out.println("1. Establecer contraseña");
                System.out.println("2. Verificar contraseña");
                System.out.println("3. Salir");

                try {
                    String opcion = new Scanner(System.in).nextLine();

                    if ("1".equals(opcion)) {
                        establecerContrasena();
                    } else if ("2".equals(opcion)) {
                        comprobarContrasena();
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