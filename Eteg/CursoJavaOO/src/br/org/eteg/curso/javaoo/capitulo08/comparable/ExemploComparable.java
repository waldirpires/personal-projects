package br.org.eteg.curso.javaoo.capitulo08.comparable;

public class ExemploComparable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Pessoa p1 = new Pessoa();
		p1.setNome("Waldir");
		p1.setCpf("033.334.433-37");
		p1.setIdade(22);
		
		Pessoa p2 = new Pessoa();
		p2.setNome("Waldir");
		p2.setCpf("033.334.433-37");
		p2.setIdade(22);

		int compareTo = p1.compareTo(p2);
		System.out.println("Comparação: " + compareTo);
		
		Pessoa p3 = new Pessoa();
		p3.setNome("Waldir");
		p3.setCpf("033.334.433-37");
		p3.setIdade(21);

		compareTo = p1.compareTo(p3);
		System.out.println("Comparação 2: " + compareTo);
		
		compareTo = p3.compareTo(p1);
		System.out.println("Comparação 2: " + compareTo);
		
	}

}
