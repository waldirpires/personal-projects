package br.org.eteg.curso.javaoo.capitulo03.banco;

public class TesteBanco {

	
	public void testar()
	{
		int i = 0;
		while (i < 10) {
			i++;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Banco banco = Banco.getInstance("Itau");
		
		System.out.println(banco + " | " + banco.nome);
		
		Banco banco2 = Banco.getInstance("Bradesco");
		
		System.out.println(banco2 +  " | " + banco2.nome);
	}

}
