package br.eteg.curso.java.util;

public class FormulaUtil {

	/**
	 * m�todo que calcula a prestacao (PMT) de um empr�stimo.
	 * @param valorEmprestimo o valor do empr�stimo
	 * @param taxaJuros a taxa de juros
	 * @param meses quantidade de meses para pagar
	 * @return o valor da presta��o
	 */
	public static double calcularValorPrestacao(double valorEmprestimo,
			double taxaJuros, short meses) {
		double pmt = (valorEmprestimo * taxaJuros) 
		/ (1 - Math.pow(1 + taxaJuros, -meses));
		return pmt;
	}
	
	
}
