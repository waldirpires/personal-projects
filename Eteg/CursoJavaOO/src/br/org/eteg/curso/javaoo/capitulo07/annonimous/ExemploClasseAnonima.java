package br.org.eteg.curso.javaoo.capitulo07.annonimous;

import br.org.eteg.curso.javaoo.capitulo07.interf.tv.TV;

public class ExemploClasseAnonima {

	public void receberTV(TV tv)
	{
		tv.setup();
	}
	
	public static void main(String[] args) {
		
		ExemploClasseAnonima ex = new ExemploClasseAnonima();
		
		ex.receberTV(
		
				new MinhaTV()
				{
					public void getControle()
					{					
					}
				}
		
		);
		
	}
}
