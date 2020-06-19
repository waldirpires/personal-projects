package br.org.eteg.curso.javaoo.capitulo07.polimorfismo;

public class ExemploCoercao {

	
	public void exemplo1()
	{
		// comversão implícita
		// soma dois valores do tipo float, nao existe coercao
		float a = 1.0F + 1.5F;
		// antes de somar 1, converte para float e depois soma
		float b = 2.0F + 1;
		// transforma 2.0 na representacao String e concatena
		String c = "1" + 2.0;
		// exemplo tb de sobrecarga no operador "+" - operador sobrecarregado
		double d = 1 + 2.0;
	}
	
	public void exemplo2()
	{
		// tocando um concerto
		Concerto c = new Concerto();
		Pianista p = new Pianista();
		// pianista tocando no concerto
		c.play(p);
		// musico generico tocando no concerto
		Musico m = new Musico();
		c.play(m);
		// COERCAO = conversao implicita (cast)
	}
	

}
