package br.org.eteg.curso.javaoo.capitulo11.seguranca;

import java.security.DigestException;
import java.security.MessageDigest;

public class ExemploMessageDigest {

	/**
	 * @param args
	 * @throws DigestException
	 */
	public static void main(String[] args){
		
		try {
			// criptografando a primeira mensagem
			String mensagem = "The book is on the table";
			String mensagemCriptografada = gerarDigest(mensagem);
			System.out.println("Mensagem criptografada: " + 
					mensagemCriptografada);

			// criptografando a mesma mensagem
			String mensagem2 = "The book is on the table";
			String mensagemCriptografada2 = gerarDigest(mensagem2);
			System.out.println("Mensagem criptografada: " + 
					mensagemCriptografada);

			// comparando as mensagens
			System.out.println("Comparacao: " + 
					mensagemCriptografada.equals(mensagemCriptografada2));
			
			// criptografando uma mensagem diferente
			String mensagem3 = "The book is on the table2";
			String mensagemCriptografada3 = gerarDigest(mensagem3);
			System.out.println("Mensagem criptografada: " + 
					mensagemCriptografada3);
			
			// comparando as duas mensagens
			// garantindo se houve ou nao uma modificacao
			System.out.println("Comparacao: " + 
					mensagemCriptografada.equals(mensagemCriptografada3));
			
		}			
		catch (Exception e) {
			e.printStackTrace();
		}		

	}

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
