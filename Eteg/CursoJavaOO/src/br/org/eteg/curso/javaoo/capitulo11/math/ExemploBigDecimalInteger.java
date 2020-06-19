package br.org.eteg.curso.javaoo.capitulo11.math;

import java.math.BigDecimal;

public class ExemploBigDecimalInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BigDecimal bd1 = new BigDecimal("123456789012.0123456789");
		BigDecimal bd2 = new BigDecimal("9876543210987654.4567890");
		
		bd1 = bd1.add(bd2);
		bd1 = bd1.multiply(bd2);
		bd1 = bd1.subtract(bd2);
		bd1 = bd1.divide(bd2);
		bd1 = bd1.negate();
		
		int casasDecimais = 4;
		
		bd2 = bd2.setScale(casasDecimais, BigDecimal.ROUND_DOWN);
		System.out.println(bd1.toEngineeringString());
		System.out.println(bd1.toPlainString());
		System.out.println(bd1.toString());
		System.out.println(bd2.toEngineeringString());
	}

}
