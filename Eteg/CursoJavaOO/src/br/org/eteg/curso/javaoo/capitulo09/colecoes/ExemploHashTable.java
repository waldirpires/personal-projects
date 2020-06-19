package br.org.eteg.curso.javaoo.capitulo09.colecoes;

import java.util.Enumeration;
import java.util.Hashtable;

import br.org.eteg.curso.javaoo.capitulo07.polimorfismo.Musico;
import br.org.eteg.curso.javaoo.capitulo08.comparable.Pessoa;

public class ExemploHashTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Hashtable<String, Object> hash = new Hashtable<String, Object>();
		
		hash.put("pessoa", new Pessoa());
		
		hash.put("inteiro", new Integer(10));
		
		hash.put("musico", new Musico());
		
		Pessoa p1 = (Pessoa) hash.get("pessoa");
		
		Integer num = (Integer) hash.get("inteiro");
		
		iterarHashPorElemento(hash);

		iterarHashPorChave(hash);
	}

	private static void iterarHashPorElemento(Hashtable<String, Object> hash) {
		Enumeration<Object> enumeracao = hash.elements();
		while (enumeracao.hasMoreElements())
		{
			Object obj = enumeracao.nextElement();
			System.out.println(obj);
		}
	}

	private static void iterarHashPorChave(Hashtable<String, Object> hash) {
		Enumeration<String> enumeracaoChaves = hash.keys();
		while (enumeracaoChaves.hasMoreElements())
		{
			String chave = enumeracaoChaves.nextElement();
			System.out.println(hash.get(chave));
		}
	}

}
