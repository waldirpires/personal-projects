import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.nio.charset.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

import org.junit.Test;

public class Main {

    // private static final byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    static byte[] salt = { 1, 2, 3, 5, 8, 13, 21, 34 };
    static String password = "1cc772a6-0c6d-4b33-a2d6-ffc96d6a109a";
    static String cleartext = "12345678901";

    public static void main(String [] args) throws Exception{

        byte [] passwordHash = sha256(password);

            System.out.println("Hash Bytes: " + Arrays.toString(passwordHash));
            //System.out.println(new String(encodedhash, "UTF-8"));
            System.out.println("Hash UTF-8: " + new String(passwordHash, "UTF-8"));
            System.out.println("Hash Hex: " + encodeHexString(passwordHash));

        String base64Password = Base64.getEncoder().encodeToString(passwordHash);
        System.out.println("Hash Base64: " + base64Password);

        System.out.println("");

        String text = "12345678901";
        System.out.println("plain text: " + text);
        String encrypted = encrypt(text, base64Password);
        System.out.println("Encrypted: "+ encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted, base64Password));

        System.out.println("");

        text = "Pedido 1";
        System.out.println("plain text: " + text);
        encrypted = encrypt(text, base64Password);
        System.out.println("Encrypted: "+ encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted, base64Password));

        System.out.println("");

        text = "10/08/1979";
        System.out.println("plain text: " + text);
        encrypted = encrypt(text, base64Password);
        System.out.println("Encrypted: "+ encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted, base64Password));

    }

    private static byte[] sha256(String src) throws Exception{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(src.getBytes("UTF-8"));

            // System.out.println("Bytes: " + Arrays.toString(encodedhash));
            // //System.out.println(new String(encodedhash, "UTF-8"));
            // System.out.println("Hex: " + encodeHexString(encodedhash));

            return encodedhash;    
    }

public static String encodeHexString(byte[] byteArray) {
    StringBuffer hexStringBuffer = new StringBuffer();
    for (int i = 0; i < byteArray.length; i++) {
        hexStringBuffer.append(byteToHex(byteArray[i]));
    }
    return hexStringBuffer.toString();
}

public static String byteToHex(byte num) {
    char[] hexDigits = new char[2];
    hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
    hexDigits[1] = Character.forDigit((num & 0xF), 16);
    return new String(hexDigits);
}

private static char[] bytesToChars(byte []b){
      char [] c = new char[b.length];

      for (int i = 0; i < b.length; i++){
        c[i] = (char) b[i];
      }
      return c;
    }

    private static String encrypt(String plainText, String passwordHash) throws Exception {
        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec pbeKeySpec = new PBEKeySpec(passwordHash.toCharArray(), salt, 1000, 384); //384
            Key secretKey = factory.generateSecret(pbeKeySpec);
            byte[] key = new byte[32]; // 32 bits = 256 bits
            byte[] iv = new byte[16];
            System.arraycopy(secretKey.getEncoded(), 0, key, 0, 32);
            System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);

            SecretKeySpec secret = new SecretKeySpec(key, "AES");
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret, ivSpec);
            byte[] ciphertext = cipher.doFinal(plainText.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(ciphertext);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private static String decrypt(String encryptedText, String passwordHash) throws Exception {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec pbeKeySpec = new PBEKeySpec(passwordHash.toCharArray(), salt, 1000, 384);
            Key secretKey = factory.generateSecret(pbeKeySpec);
            byte[] key = new byte[32]; // 32 bits = 256 bits
            byte[] iv = new byte[16];

            System.arraycopy(secretKey.getEncoded(), 0, key, 0, 32);
            System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);

            SecretKeySpec secret = new SecretKeySpec(key, "AES");
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, ivSpec);

            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)), "UTF-8");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
