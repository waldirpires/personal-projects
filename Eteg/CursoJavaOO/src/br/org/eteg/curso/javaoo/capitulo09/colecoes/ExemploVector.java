package br.org.eteg.curso.javaoo.capitulo09.colecoes;

import java.util.Vector;

public class ExemploVector {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// criando um vetor de planetas
		Vector<String> planetas = new Vector<String>();
		
		// adicionando planetas
		planetas.add("Mercurio");
		planetas.add("Venus");
		planetas.add("Terra");
		planetas.add("Marte");
		
		String [] outrosPlanetas = {"Jupiter", "Saturno", 
				"Urano", "Netuno", "Plutão"};
		
		// acrescentando outros planetas
		for (String s:outrosPlanetas)
		{
			planetas.addElement(s);
		}
		
		int tamanho = planetas.size();
		System.out.println("Tamanho: " + tamanho);
		boolean vazio = planetas.isEmpty();
		System.out.println("Está vazio: " + vazio);
		
		for (String s:planetas)
		{
			System.out.println(s);
		}
		
	}

}
