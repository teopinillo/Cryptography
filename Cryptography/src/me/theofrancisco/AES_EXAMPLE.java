package me.theofrancisco;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
//Actually, you should not be using that class, it is one of Sun's internal unsupported classes.
//If you need a base64 codec, I recommend the Jakarta Commons Codec API (http://jakarta.apache.org/commons/codec/).
//reference: https://coderanch.com/t/273842/java/download-jar-sun-misc-BASE
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class AES_EXAMPLE {
	
	private static final String ALGO = "AES";
	private byte[] keyValue;

	public AES_EXAMPLE (String key){
		keyValue = key.getBytes();
	}
	
	public String encrypt (String data) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = cipher.doFinal(data.getBytes());
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedValue = encoder.encodeToString(encVal);
		return encryptedValue;
	}
	
	public String decrypt (String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.DECRYPT_MODE, key);
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedValue = decoder.decode(encryptedData.getBytes());
		byte[] decValue = cipher.doFinal(decodedValue);
		String decryptedValue = new String (decValue);
		return decryptedValue;
	}
	
	private Key generateKey() throws Exception {
		Key key = new SecretKeySpec (keyValue, ALGO);
		return key;
	}
	public static void main(String[] args) {
		AES_EXAMPLE myApp = new AES_EXAMPLE ("QwbyU85LymnrQ307"); //should be 16, 24 or 32 bytes
		String text = "www.theofrancisco.me";
		try{
		String encText = myApp.encrypt(text);
		System.out.println("data: "+ text);
		System.out.println("Encrypted data: "+ encText);
		
		}catch (Exception e){
			Logger.getLogger(AES_EXAMPLE.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
