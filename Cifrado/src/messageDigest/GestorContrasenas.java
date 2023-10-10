package messageDigest;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class GestorContrasenas {

    // Método para establecer una contraseña y almacenarla en un archivo
    public static void establecerContrasena() {
        try {
            FileWriter writer = new FileWriter("CifradoMD.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            System.out.println("Establece una contraseña: ");
            String contrasena = new Scanner(System.in).nextLine();

            // Hashear la contraseña antes de almacenarla
            String contrasenaHasheada = hashContrasena(contrasena);

            // Escribir la contraseña hasheada en el archivo
            bufferedWriter.write(contrasenaHasheada);
            bufferedWriter.close();

            System.out.println("Contraseña almacenada con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
        }
    }

    // Método para verificar si la contraseña ingresada coincide con la almacenada
    public static void verificarContrasena() {
        try {
            FileReader fileReader = new FileReader("CifradoMD.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("Ingresa la contraseña: ");
            String contrasenaIngresada = new Scanner(System.in).nextLine();
            String contrasenaAlmacenada = bufferedReader.readLine();

            bufferedReader.close();

            if (contrasenaAlmacenada != null && contrasenaAlmacenada.equals(hashContrasena(contrasenaIngresada))) {
                System.out.println("Contraseña correcta. Acceso concedido.");
            } else {
                System.out.println("Contraseña incorrecta. Acceso denegado.");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo.");
        }
    }

    // Método para hashear una contraseña utilizando SHA-256
    public static String hashContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(contrasena.getBytes());

            // Convertir el hash resultante en una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte aByte : bytes) {
                String hex = Integer.toHexString(0xff & aByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        File archivo = new File("CifradoMD.txt");
        try {
            if (!archivo.exists()) {
                archivo.createNewFile(); // Crear el archivo si no existe
            }

            System.out.println("El archivo existe");

            while (true) {
                System.out.println("1. Establecer contraseña");
                System.out.println("2. Verificar contraseña");
                System.out.println("3. Salir");

                String opcion = new Scanner(System.in).nextLine();

                if ("1".equals(opcion)) {
                    establecerContrasena();
                } else if ("2".equals(opcion)) {
                    verificarContrasena();
                } else if ("3".equals(opcion)) {
                    break;
                } else {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo.");
        }
    }
}
