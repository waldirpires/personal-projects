package br.org.eteg.curso.javaoo.capitulo07.polimorfismo.medicos;

public class Dentista extends Medico {

	@Override
	public void atender() {
		System.out.println("Olhar os dentes");
	}
}
