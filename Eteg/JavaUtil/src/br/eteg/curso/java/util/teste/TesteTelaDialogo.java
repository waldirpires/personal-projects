package br.eteg.curso.java.util.teste;

import br.eteg.curso.java.util.ui.TelaDialogo;

public class TesteTelaDialogo {

	public static void main(String[] args) {
		
//		TelaDialogo.mostrarMensagemErro("Erro", "Erro de leitura dos dados!");
//		
//		String s = TelaDialogo.mostrarTelaEntrada("Entrada", "valor");
//		
//		System.out.println(s);
//		
//		s = TelaDialogo.mostrarTelaConfirmacao("Confirmacao", "Confirma?");
//		
//		System.out.println(s);
//		
		TelaDialogo.mostrarTelaAviso("Empr�stimo", "O seu saldo est� negativo!");
//		
//		String [] opcoes = {"(1) Criar", "(2) Fechar", "(3) Sair"};
//		
//		s = TelaDialogo.mostrarListaOpcoes("Opcoes", opcoes);
		
		String [] opcoes2 = {"(1) Extratos/Saldos", "(2) Dep�sito", "(3) Saque",
				"(4) Pagamentos/D�bitos", "(4) Transfer�ncia/DOC", 
				"(5) Empr�stimos", "(6) Investimentos", "(7) Sair"};
		
//		String s = TelaDialogo.mostrarListaOpcoes("Opcoes", opcoes2);
//		
//		System.out.println(s);
//		
		String s = TelaDialogo.mostrarListaOpcoesListagemCombo("Teste", "Teste2", opcoes2);
		System.out.println(s);

		s = TelaDialogo.mostrarListaOpcoesListagemCombo("Teste", "Teste2", OperacaoContaBancaria.values());
		System.out.println(s);

		//s = JOptionPane.showInputDialog(null, listScroller, "test", 1);
		 s = TelaDialogo.mostrarListaOpcoesListagemScroll("Teste", "teste2", 
				opcoes2);
		System.out.println(s);

		//s = JOptionPane.showInputDialog(null, listScroller, "test", 1);
		s = 
			TelaDialogo.mostrarListaOpcoesListagemPainel("Banco Java SA", "Opera��es dispon�veis:", "Selecione uma op��o do menu", 
				OperacaoContaBancaria.values());
		System.out.println(s);
	}	
	
}
