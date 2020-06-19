package br.eteg.curso.java.util.seguranca;

import java.security.MessageDigest;

public class SegurancaUtil {

	public static String gerarDigest(String mensagem) {
		String mensagemCriptografada = null;
		try {
			// criar o message digest
			MessageDigest md = MessageDigest.getInstance("SHA");
			// a mensagem
			System.out.println("Mensagem original: " + mensagem);
			// insere a mensagem no digest
			md.update(mensagem.getBytes());
			// gerando o digest
			byte[] digest = md.digest();
			// obtendo a mensagem encriptografada
			mensagemCriptografada = converterBytesEmHexa(digest);
				//new String(digest);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensagemCriptografada;
	}
	
	public static String converterBytesEmHexa(byte [] bytes)
	{
		StringBuilder builder = new StringBuilder();
		for (byte b: bytes)
		{
			builder.append(Integer.toHexString(b));
		}
		return builder.toString();
	}
	
}
