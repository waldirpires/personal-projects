package br.org.eteg.curso.javaoo.capitulo11.text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class DecimalFormatExemplo {

	private static final String GERAL = "###,###.###";
	private static final String GERAL_SEM_VIRGULA = "###.##";
	private static final String GERAL_ZERO = "000000.000";
	private static final String GERAL_TRALHA = "$###,###.###";
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DecimalFormat format = new DecimalFormat();
		double d = 12345.56;
		System.out.println(format.format(d));
		
		mostrarFormatacao(GERAL, d);

		mostrarFormatacao(GERAL_SEM_VIRGULA, d);

		mostrarFormatacao(GERAL_TRALHA, d);

		mostrarFormatacao(GERAL_ZERO, d);

		Locale loc = new Locale("en", "US");
		NumberFormat nf = NumberFormat.getNumberInstance(loc);
		DecimalFormat df = (DecimalFormat)nf;
		df.applyPattern(GERAL_ZERO);
		String output = df.format(d);
		System.out.println(GERAL_ZERO + " " + output + " " + 
			           loc.toString());		
		
		df.applyPattern(GERAL);
		output = df.format(d);
		System.out.println(GERAL + " " + output + " " + 
			           loc.toString());		
		
	}

	private static void mostrarFormatacao(String formato, double valor)
	{
		DecimalFormat format = new DecimalFormat(formato);
		System.out.println(formato + " = " + format.format(valor));
	}
	
	public static void mostrarExemploAlteracaoFormatacao()
	{
		DecimalFormatSymbols unusualSymbols =
		    new DecimalFormatSymbols(Locale.getDefault());
		unusualSymbols.setDecimalSeparator('|');
		unusualSymbols.setGroupingSeparator('^');

		String strange = "#,##0.###";
		DecimalFormat weirdFormatter = 
		               new DecimalFormat(strange, unusualSymbols);
		weirdFormatter.setGroupingSize(4);

		String bizarre = weirdFormatter.format(12345.678);
		System.out.println(bizarre);		
	}
}
