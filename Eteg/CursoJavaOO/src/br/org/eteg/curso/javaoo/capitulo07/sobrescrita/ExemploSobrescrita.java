package br.org.eteg.curso.javaoo.capitulo07.sobrescrita;

import br.org.eteg.curso.javaoo.capitulo07.polimorfismo.Musico;
import br.org.eteg.curso.javaoo.capitulo07.polimorfismo.Pianista;
import br.org.eteg.curso.javaoo.capitulo07.polimorfismo.Solista;

public class ExemploSobrescrita {

	public static void main(String[] args) {
		
		Musico m = new Musico();
		m.afinarInstrumento();
		
		Pianista p = new Pianista();
		p.afinarInstrumento();
		
		Solista s = new Solista();
		s.afinarInstrumento();
	}
}
