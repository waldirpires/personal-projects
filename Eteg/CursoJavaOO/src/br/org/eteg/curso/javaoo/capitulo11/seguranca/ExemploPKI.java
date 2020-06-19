package br.org.eteg.curso.javaoo.capitulo11.seguranca;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;
import java.util.Calendar;
import java.util.Date;

import br.org.eteg.curso.javaoo.capitulo10.io.serializacao.Pessoa;

public class ExemploPKI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			// obtendo uma instancia do gerador de chaves
			// DSA: Digital Signatura Algorithm
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
			// setando o tamanho da chave para 1 Kb
			keyGen.initialize(1024);
			
			// gera o par de chaves
			KeyPair keyPair = keyGen.generateKeyPair();
			
			// obtem as chaves publica e privada
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			
			// assinando um objeto Java
			Calendar data = Calendar.getInstance();
			data.set(Calendar.YEAR, 1980);
			data.set(Calendar.MONTH, 0);
			data.set(Calendar.DAY_OF_MONTH, 1);
			Pessoa obj = new Pessoa("Waldir", 12, "Rua Jatobás", data.getTime());
			Signature sig = Signature.getInstance(privateKey.getAlgorithm());
			
			// assinando um objeto Java
			SignedObject so = new SignedObject(obj, privateKey, sig);
			
			// gera um outro par de chaves
			KeyPair keyPairFalso = keyGen.generateKeyPair();
			
			// gerar as chaves publica e privada diferentes
			PrivateKey privateKeyFalso = keyPairFalso.getPrivate();
			PublicKey publicKeyFalso = keyPairFalso.getPublic();
			
			// verificando o objeto assinado (chave publica errada)
			System.out.println("Verificacao 1: " + 
				verificarAssinatura(publicKeyFalso, so));
			
			System.out.println("Verificacao 2: " + 
				verificarAssinatura(publicKey, so));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static boolean verificarAssinatura(PublicKey chavePublica, SignedObject so)
	{
		boolean assinauraOk = false;
		try {
			Signature sig = Signature.getInstance(chavePublica.getAlgorithm());
			assinauraOk = so.verify(chavePublica, sig);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} finally {
			return assinauraOk;
		}
	}

}
