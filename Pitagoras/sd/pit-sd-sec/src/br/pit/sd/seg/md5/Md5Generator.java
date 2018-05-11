package br.pit.sd.seg.md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Generator {

	public static void main(String[] args) {
		String string = "a";
		MessageDigest m;

		try {
			m = MessageDigest.getInstance("MD5");
			m.update(string.getBytes(), 0, string.length());
			BigInteger i = new BigInteger(1, m.digest());

			// Formatando o resuldado em uma cadeia de 32 caracteres,
			// completando com 0 caso falte
			string = String.format("%1$032X", i);

			System.out.println("MD5: " + string);
		}

		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
