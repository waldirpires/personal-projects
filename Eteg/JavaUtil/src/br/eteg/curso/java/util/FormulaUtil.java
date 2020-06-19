package br.eteg.curso.java.util;

public class FormulaUtil {

	/**
	 * método que calcula a prestacao (PMT) de um empréstimo.
	 * @param valorEmprestimo o valor do empréstimo
	 * @param taxaJuros a taxa de juros
	 * @param meses quantidade de meses para pagar
	 * @return o valor da prestação
	 */
	public static double calcularValorPrestacao(double valorEmprestimo,
			double taxaJuros, short meses) {
		double pmt = (valorEmprestimo * taxaJuros) 
		/ (1 - Math.pow(1 + taxaJuros, -meses));
		return pmt;
	}
	
	
}
