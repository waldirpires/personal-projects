package br.org.eteg.curso.javaoo.capitulo09.i18n;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExemploLocale {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Locale br = new Locale("pt", "BR");
		Locale us = new Locale("en", "US");
		Locale ar = new Locale("es", "AR");
		
		System.out.println("Localização padrão: " + 
				System.getProperty("user.language") + " - " + 
				System.getProperty("user.region"));
		
		Date data = new Date(100, 12, 1);
		SimpleDateFormat def = (SimpleDateFormat) 
		  DateFormat.getDateInstance(DateFormat.FULL);

		SimpleDateFormat brf = (SimpleDateFormat) 
		  DateFormat.getDateInstance(DateFormat.FULL, br);
		SimpleDateFormat usf = (SimpleDateFormat) 
		  DateFormat.getDateInstance(DateFormat.FULL, us);
		SimpleDateFormat arf = (SimpleDateFormat) 
		  DateFormat.getDateInstance(DateFormat.FULL, ar);
		
		System.out.println(def.format(data));
		System.out.println(brf.format(data));
		System.out.println(usf.format(data));
		System.out.println(arf.format(data));
		
		Locale localizacaoFranca = Locale.FRANCE;
		
		localizacaoFranca.setDefault(Locale.FRANCE);
		SimpleDateFormat deff = (SimpleDateFormat) 
		  DateFormat.getDateInstance(DateFormat.FULL, localizacaoFranca);
		
		System.out.println(deff.format(data));
	}

}

