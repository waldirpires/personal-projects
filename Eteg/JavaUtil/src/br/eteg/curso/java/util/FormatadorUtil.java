package br.eteg.curso.java.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatadorUtil {

	
	public static String formatarNumero(double d)
	{
		String formato = "000,000.00";
		DecimalFormat dc = new DecimalFormat(formato);
		return dc.format(d);
	}
	
	public static String formatarDescEValor(String descricao, double valor)
	{
		String formato = "%20s   %10.2f";
		return String.format(formato, descricao, new Double(valor));
	}
	
	public static String formatarDataResumida(Date data)
	{
		String formato = "%1$td %1$tb %1$ty %1$tT";
		return String.format(formato, data);
	}
	
	/**
	 * metodo que formata as movimentacoes do extrato.
	 * @param data a data da movimentacao
	 * @param descricao a descricao
	 * @param valor o valor
	 * @return o String formatado
	 */
	public static String formatarDataDescEValor(Date data, 
			String descricao, double valor)
	{
		String formatoDataExtrato = "%1$td/%1$tm - ";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(formatoDataExtrato, data));
		String formatoValor = "%1$-20s | R$ %2$10.2f";
		sb.append(String.format(formatoValor, descricao, new Double(valor)));
		return sb.toString();
	}
	
	public static String formatarDataDescEValor3(Date data, 
			String descricao, double valor)
	{
		String formatoDataExtrato = "%1$td/%1$tm - %2$-20s | R$ %3$10.2f";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(formatoDataExtrato, data, 
				descricao, new Double(valor)));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		double d = 1.2;
		System.out.println(formatarNumero(d));
		d = 1200.223;
		System.out.println(formatarNumero(d));
		System.out.println(formatarDescEValor("Teste Deposito", 1000.20));
		System.out.println(formatarDataDescEValor(Calendar.getInstance().getTime(), 
				"Teste Deposito", 1000.20));
		System.out.println(formatarDataDescEValor3(Calendar.getInstance().getTime(), 
				"Teste Deposito", 10000.209));
		System.out.println(formatarDataResumida(Calendar.getInstance().getTime()));
	}
}
