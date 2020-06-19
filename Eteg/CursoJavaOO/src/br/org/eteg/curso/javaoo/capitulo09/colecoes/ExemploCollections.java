package br.org.eteg.curso.javaoo.capitulo09.colecoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExemploCollections {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> pessoas = new ArrayList<String>();
		pessoas.add("Joao");
		pessoas.add("Maria");
		pessoas.add("Adriana");
		pessoas.add("Ricardo");
		pessoas.add("Bernardo");
		pessoas.add("Waldir");
		pessoas.add("Valdo");
		System.out.println("mostrando a lista: " + pessoas);
		
		//Copiando elementos de uma lista para outra
		List<String> novaLista = new ArrayList<String>(pessoas.size()+ 5);
		novaLista.add("Jorge");
		novaLista.add("Jorge");
		novaLista.add("Jorge");
		novaLista.add("Jorge");
		novaLista.add("Jorge");
		novaLista.add("Jorge");
		novaLista.add("Jorge");

		Collections.copy(novaLista, pessoas);
		System.out.println("Lista copiada: " + novaLista);
		
		// substituindo todos os valores da lista por felipe
		Collections.fill(novaLista, "Felipe");
		System.out.println("Lista preenchida: " + novaLista);
		
		// pegando o maximo
		String max = Collections.max(pessoas);
		System.out.println("Maior: " + max);
		
		// pegando o mínimo
		String min = Collections.min(pessoas);
		System.out.println("Menor: " + min);
		
		// invertendo a lista
		Collections.reverse(pessoas);
		System.out.println("Lista invertida: " + pessoas);
		
		//reorganiza ou embaralha
		Collections.shuffle(pessoas);
		System.out.println("Lista embaralhada: " + pessoas);
		Collections.shuffle(pessoas);
		System.out.println("Lista embaralhada de novo: " + pessoas);
		
		// ordenando a lista
		Collections.sort(pessoas);
		System.out.println("Lista ordenada: " + pessoas);
	}

}
