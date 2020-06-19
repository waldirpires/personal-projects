package br.org.eteg.curso.javaoo.capitulo07.clone;

public class ExemploClone {

	public static void main(String[] args) {
		
		SerHumano sh1 = new SerHumano();
		sh1.setNome("Joao");
		sh1.setNacionalidade("Brasileira");
		sh1.setIdade("10");
		sh1.setCPF("001.002.003.44");
		
		try {
			SerHumano sh2 = (SerHumano) sh1.clone();
			System.out.println(sh1);
			System.out.println(sh2);
			System.out.println(sh1.equals(sh2));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
}
