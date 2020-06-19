package br.org.eteg.curso.javaoo.capitulo07.polimorfismo.frutas;

import java.util.ArrayList;
import java.util.List;

public class ExemploParametrico {

	public void exemplo1()
	{
		List<? extends Fruta> lista = new ArrayList();
		lista.add(new Abacate());
	}
}
