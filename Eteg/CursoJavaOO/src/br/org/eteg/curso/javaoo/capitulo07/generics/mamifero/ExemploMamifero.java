package br.org.eteg.curso.javaoo.capitulo07.generics.mamifero;

import java.util.Vector;

public class ExemploMamifero {

	public static void main(String[] args) {
		Vector<Cachorro> cachorros = new Vector<Cachorro>();
		cachorros.add(new Cachorro());
		cachorros.add(new Cachorro());
		
		Vector<Gato> gatos = new Vector<Gato>();
		gatos.add(new Gato());
		gatos.add(new Gato());
		gatos.add(new Gato());
		
		//Vector<Mamifero> mamiferos = cachorros;
		Vector<? extends Mamifero> mamiferos = cachorros;
		for (Mamifero m:mamiferos)
		{
			m.fazerSom();
		}
		mamiferos = gatos;
		
		for (Mamifero m:mamiferos)
		{
			m.fazerSom();
		}
		
	}
}
