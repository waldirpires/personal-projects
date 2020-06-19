package br.eteg.curso.java.util;

import br.eteg.curso.java.util.ui.TelaDialogo;

public class ConversorUtil {

	public static double converterStringParaDouble(String s)
	{
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			TelaDialogo.mostrarMensagemErro("Convers�o", 
					"N�o foi poss�vel converter o valor "+ s);
		}
		return 0;
	}
	
}
