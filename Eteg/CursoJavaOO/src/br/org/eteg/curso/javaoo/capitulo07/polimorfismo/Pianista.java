package br.org.eteg.curso.javaoo.capitulo07.polimorfismo;

public class Pianista extends Musico {

	// construtor implicito, que chama o construtor da super classe
	public Pianista() {
	}
	
	@Override
	public void afinarInstrumento() {
		System.out.println("Afinar piano");
	}
}
