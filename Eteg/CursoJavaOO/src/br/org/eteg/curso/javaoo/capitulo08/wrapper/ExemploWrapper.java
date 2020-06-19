package br.org.eteg.curso.javaoo.capitulo08.wrapper;

public class ExemploWrapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Integer integer = new Integer(12);
		
		// recuperando a primitiva empacotada na classe wrapper
		int i = integer.intValue();
		
		Double d = new Double(12.3);
		
		boolean teste = d < Double.POSITIVE_INFINITY;
		boolean teste2 = d > Double.NEGATIVE_INFINITY;
		
		double c = 2 / 0.0;
		
		boolean teste3 = (c == Double.NaN);
		
		

	}

}
