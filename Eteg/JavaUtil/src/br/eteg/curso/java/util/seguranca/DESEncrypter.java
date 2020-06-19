package br.eteg.curso.java.util.seguranca;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class DESEncrypter {
    Cipher ecipher;
    Cipher dcipher;

 // 8-byte Salt
    byte[] salt = {
        (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
        (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
    };

    // Iteration count
    int iterationCount = 19;

    DESEncrypter(String passPhrase) {
        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(
            		passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance(
                "PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = 
            	new PBEParameterSpec(salt, iterationCount);

            // Create the ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }    
    DESEncrypter(SecretKey key) {
        try {
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }
    
 // Buffer used to transport the bytes from one stream to another
    byte[] buf = new byte[1024];

    public void encrypt(InputStream in, OutputStream out) {
        try {
            // Bytes written to out will be encrypted
            out = new CipherOutputStream(out, ecipher);

            // Read in the cleartext bytes and write to out to encrypt
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }
    }

    public void decrypt(InputStream in, OutputStream out) {
        try {
            // Bytes read from in will be decrypted
            in = new CipherInputStream(in, dcipher);

            // Read in the decrypted bytes and write the cleartext to out
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }
    }    

    public static void main(String[] args) {
        try {
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        
            // Create encrypter/decrypter class
            DESEncrypter encrypter = new DESEncrypter(key);
        
            // Encrypt
            String encrypted = encrypter.encrypt("Don't tell anybody!");
        
            // Decrypt
            String decrypted = encrypter.decrypt(encrypted);
        } catch (Exception e) {
        }    
        
        try {
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        
            // Create encrypter/decrypter class
            DESEncrypter encrypter = new DESEncrypter(key);
        
            // Encrypt
            encrypter.encrypt(new FileInputStream("cleartext1"),
                new FileOutputStream("ciphertext"));
        
            // Decrypt
            encrypter.decrypt(new FileInputStream("ciphertext"),
                new FileOutputStream("cleartext2"));
        } catch (Exception e) {
        }        
		
        try {
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        
            // Prepare the encrypter
            Cipher ecipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
        
            // Seal (encrypt) the object
            SealedObject so = new SealedObject(new MySecretClass(), ecipher);
        
            // Get the algorithm used to seal the object
            String algoName = so.getAlgorithm();  // DES
        
            // Prepare the decrypter
            Cipher dcipher = Cipher.getInstance("DES");
            dcipher.init(Cipher.DECRYPT_MODE, key);
        
            // Unseal (decrypt) the class
            MySecretClass o = (MySecretClass)so.getObject(dcipher);
        } catch (java.io.IOException e) {
        } catch (ClassNotFoundException e) {
        } catch (javax.crypto.IllegalBlockSizeException e) {
        } catch (javax.crypto.BadPaddingException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
        
        try {
            // Create encrypter/decrypter class
            DESEncrypter encrypter = new DESEncrypter("My Pass Phrase!");
        
            // Encrypt
            String encrypted = encrypter.encrypt("Don't tell anybody!");
        
            // Decrypt
            String decrypted = encrypter.decrypt(encrypted);
        } catch (Exception e) {
        }        
	}


}