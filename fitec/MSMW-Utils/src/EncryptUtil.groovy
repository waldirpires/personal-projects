import java.nio.charset.Charset
import java.security.GeneralSecurityException

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter


//def pw = "admin"

//def key = "ST367R823H8P221L"

//println "Original pw: " + pw

//println encryptDecrypt(key.getBytes(), Cipher.DECRYPT_MODE, pw)
//def encrypted = encryptDecrypt(key.getBytes(), Cipher.ENCRYPT_MODE, pw)
//println "Encrypted: " + encrypted
//def decrypted = encryptDecrypt(key.getBytes(), Cipher.DECRYPT_MODE, encrypted)
//println "Decrypted: " + decrypted

class EncryptUtil {

/**
 * Encrypts/decrypts a password according with the key passed.
 *
 * @param key
 * @param opmode
 * @param value
 * @return returns the decrypted String
 * @throws GeneralSecurityException
 */
def static encryptDecrypt(byte[] key, int opmode, String value) throws GeneralSecurityException {
	int KEY_SIZE = 16
	String AES = "AES"
	String ENCODING = "UTF-8"
	String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding"
	
	
	System.out.println("Using key: " + new String(key));
	if (key.length != KEY_SIZE) {
		println "Invalid key size."
		throw new IllegalArgumentException("Invalid key size." + key.length);
	}
	SecretKeySpec skeySpec = new SecretKeySpec(key, AES);
	Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
	cipher.init(opmode, skeySpec, new IvParameterSpec(new byte[key.length]));

	byte[] input = null;
	if (Cipher.DECRYPT_MODE == opmode) {
		input = //hexStringToByteArray(value) 
		DatatypeConverter.parseHexBinary(value);
	} else {
		input = value.getBytes(Charset.forName(ENCODING));
	}
	byte[] output = cipher.doFinal(input);

	String result = null;
	if (Cipher.DECRYPT_MODE == opmode) {
		result = new String(output, Charset.forName(ENCODING));
	} else {
		result = DatatypeConverter.printHexBinary(output);
	}
	return result;
}

def static byte[] hexStringToByteArray(String s) {
	int len = s.length();
	byte[] data = new byte[len / 2];
	for (int i = 0; i < len; i += 2) {
		data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
							 + Character.digit(s.charAt(i+1), 16));
	}
	return data;
}

}