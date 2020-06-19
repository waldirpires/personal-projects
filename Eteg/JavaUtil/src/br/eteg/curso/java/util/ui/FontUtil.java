package br.eteg.curso.java.util.ui;

import java.awt.Font;

public class FontUtil {

	public static final Font FONTE_MONOSPACED = 
		new Font("Courier", Font.BOLD, 16);	
	
	public static Font criarFonte(String nome, int tipo, int tamanho)
	{
		return new Font(nome, tipo, tamanho);
	}
}
