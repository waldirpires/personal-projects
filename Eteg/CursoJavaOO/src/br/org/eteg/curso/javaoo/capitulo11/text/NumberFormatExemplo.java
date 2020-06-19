package br.org.eteg.curso.javaoo.capitulo11.text;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatExemplo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Locale us = Locale.UK;
		
		NumberFormat formatBR = NumberFormat.getInstance();
		NumberFormat formatUS = NumberFormat.getInstance(us);
		double d = 1200.3;
		
		mostrarDadosFormatacao(formatBR, d);
		mostrarDadosFormatacao(formatUS, d);
	}
	
	public static void mostrarDadosFormatacao(NumberFormat format, double d)
	{
		System.out.println("Formatacao simples: " + format.format(d));
		System.out.println("Monetaria: " + format.getCurrencyInstance().format(d));
		//System.out.println("Modo de arredondamento:" + format.getRoundingMode());
		System.out.println("Inteiro: " + format.getIntegerInstance().format(d));
		System.out.println(format.getPercentInstance().format(d));
		System.out.println("Moeda Simbolo: " + format.getCurrency().getSymbol());
		System.out.println("Moeda Código: " + format.getCurrency().getCurrencyCode());
		System.out.println("Numero de digitos na fração: " + 
				format.getCurrency().getDefaultFractionDigits());
		
	}

}
