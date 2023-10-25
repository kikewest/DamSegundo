package Cifrado;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;

public class cifradoAsimetrico {
	public static PrivateKey privateKey;
	public static PublicKey publicKey;
	public static void main(String[] args) {
		regenerarKey();
		menu();
	}

	public static void menu() {
		boolean salir = false;
        Scanner leer = new Scanner(System.in);

        while (!salir) {
            System.out.println("------Menu------");
            System.out.println("1. Mensaje para Cifrar");
            System.out.println("2. Mensaje para Descifrar");
            System.out.println("3. Regenerar Clave");
            System.out.println("4. Salir");
            System.out.print("Por favor Introduzca una opcion: ");
            int opcion = leer.nextInt();        
            switch (opcion) {
                case 1:
                    System.out.print("Introduzca su mensaje para cifrar: ");
                    leer.nextLine(); 
                    String mensajeCifrado = cifrarAsimetrico(leer.nextLine());
                    System.out.println("Mensaje cifrado: " + mensajeCifrado);
                    break;
                case 2:
                    System.out.print("Introduzca su mensaje para descifrar: ");
                    leer.nextLine();
                    String mensajeDescifrado = descifrarAsimetrico(leer.nextLine());
                    System.out.println("Mensaje descifrado: " + mensajeDescifrado);
                    break;
                case 3:
                    regenerarKey();
                    System.out.println("Sus Claves se han regenerado.");
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Introduzca una opcion valida.");
                    break;
            }
        }
        leer.close();
    }

	public static String cifrarAsimetrico(String mensajeSinCifrar) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] mensajeCifrado = cipher.doFinal(mensajeSinCifrar.getBytes());
			return Base64.getEncoder().encodeToString(mensajeCifrado);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String descifrarAsimetrico(String mensajeCifrado) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado)));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void regenerarKey() {
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			System.out.println("Sus Claves se han regenerado");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

	}

}
