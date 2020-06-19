package br.org.eteg.curso.javaoo.capitulo07.generics;

import br.org.eteg.curso.javaoo.capitulo07.generics.mamifero.Cachorro;
import br.org.eteg.curso.javaoo.capitulo07.generics.mamifero.Gato;

public class ExemploMinhaLista {

	public static void main(String[] args) {
		
		MinhaLista<Gato> minhaLista = new MinhaLista<Gato>();
		
		System.out.println(minhaLista.getTamanho());
		
		Gato gato = new Gato();
		
		minhaLista.inserir(gato);
		
		Cachorro c = new Cachorro();
		
		// Tipo generico foi definido diferente do tipo sendo passado
		minhaLista.inserir(c);
		
	}
}
