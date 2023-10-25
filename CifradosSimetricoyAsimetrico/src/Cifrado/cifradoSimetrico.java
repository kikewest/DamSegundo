package Cifrado;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class cifradoSimetrico {

	public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		Scanner leer = new Scanner(System.in);
		System.out.println("Tiene clave Secreta?");
		String respuesta = leer.nextLine();
		String textoSinCifrar="";
		SecretKey secretKey = null;
		try {		
			if(respuesta.equalsIgnoreCase("si")) {
				System.out.println("Introduzca su clave por favor: ");
				String clave= leer.nextLine();
				while (clave.getBytes().length<16 || clave.getBytes().length>16) {
					System.out.println("Por favor su clave tiene que tener 16 caracteres");
					clave=leer.nextLine();
				}
					secretKey = new SecretKeySpec(clave.getBytes(),"AES");	
			}else if (respuesta.equalsIgnoreCase("no")) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				secretKey = keyGen.generateKey();
				System.out.println("Se ha generado su clave: "+secretKey);			
			}else {
				System.out.println("Por favor introduzca: si/no");
			}
			System.out.println("Introduzca un mensaje para cifrar");
			textoSinCifrar=leer.nextLine();
			leer.close();
			System.out.println("su mensaje cifrado es: "+cifrarSimetrico(secretKey,textoSinCifrar));
			System.out.println("su mensaje descifrado es: "+descifrarSimetrico(secretKey,cifrarSimetrico(secretKey,textoSinCifrar)));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String cifrarSimetrico(SecretKey secretKey,String textoSinCifrar) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[]mensajeCifrado= cipher.doFinal(textoSinCifrar.getBytes());
		return Base64.getEncoder().encodeToString(mensajeCifrado);
		
	}
	
	public static String descifrarSimetrico(SecretKey secretKey,String textoCifrado) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[]mensajeDescrifrado= cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
		return new String (mensajeDescrifrado);
	}
	
}
