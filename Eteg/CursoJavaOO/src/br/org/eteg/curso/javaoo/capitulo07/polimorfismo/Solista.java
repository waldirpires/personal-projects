package br.org.eteg.curso.javaoo.capitulo07.polimorfismo;

public class Solista extends Musico {

	// construtor explicito, porque chama explicitamento contrutor da
	// super classe
	public Solista() {
		super();
	}
	
	@Override
	public void afinarInstrumento() {
		System.out.println("fazer vocaliza");
	}
}
