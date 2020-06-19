package br.org.eteg.curso.javaoo.capitulo11.text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleDateFormatExemplo {

	private static final String COMPLETO = "G dd/MM/yy HH:mm:ss.SSSS a";
	private static final String DIA_SEMANA = "EEEEEEEEEE, wwwwwww";
	private static final String PADRAO = "yyyy.MM.dd G 'at' hh:mm:ss z";
	private static final String DATA = "EEE, MMM d, ''yy";
	private static final String HORA = "h:mm a";
	private static final String HORA_EXTENDIDO = "hh 'o''clock' a, zzzz";
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Calendar data = Calendar.getInstance();
		
		mostrarFormato(DIA_SEMANA, data);
		
		mostrarFormato(PADRAO, data);

		mostrarFormato(DATA, data);
		
		mostrarFormato(HORA, data);

		mostrarFormato(HORA_EXTENDIDO, data);
		
		mostrarFormato(COMPLETO, data);
	}
	
	private static void mostrarFormato(String formato, Calendar data)
	{
		SimpleDateFormat format = new SimpleDateFormat(formato);
		System.out.println(formato + " = " + format.format(data.getTime()));
		
	}

}
