package br.org.eteg.curso.javaoo.capitulo09.datas;

import java.util.Calendar;
import java.util.Date;

public class ExemploDatas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Date data = new Date();
		System.out.println(data);

		Calendar calendario = Calendar.getInstance();
		System.out.println("Ano: " + calendario.get(Calendar.YEAR));
		System.out.println("Mes: " + calendario.get(Calendar.MONTH));
		System.out.println("Dia: " + calendario.get(Calendar.DAY_OF_MONTH));
		
		System.out.println("Semana do ano: " + calendario.get(Calendar.WEEK_OF_YEAR));
		
	}

}
