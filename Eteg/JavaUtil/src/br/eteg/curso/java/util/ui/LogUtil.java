package br.eteg.curso.java.util.ui;

import java.util.Calendar;

import br.eteg.curso.java.util.datatype.DataUtil;

public class LogUtil {

	public static void debug(String mensagem)
	{
		mostrarMensagemNoConsole(mensagem);
	}
	
	private static void mostrarMensagemNoConsole(String m)
	{
		System.out.println(DataUtil.getDataFormatada(
				Calendar.getInstance(), DataUtil.YYYY_MM_DD_HH_MM_SS) + " : "
				+ m);
	}
	
}
