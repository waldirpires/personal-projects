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
		TelaDialogo.mostrarTelaAviso("Empréstimo", "O seu saldo está negativo!");
//		
//		String [] opcoes = {"(1) Criar", "(2) Fechar", "(3) Sair"};
//		
//		s = TelaDialogo.mostrarListaOpcoes("Opcoes", opcoes);
		
		String [] opcoes2 = {"(1) Extratos/Saldos", "(2) Depósito", "(3) Saque",
				"(4) Pagamentos/Débitos", "(4) Transferência/DOC", 
				"(5) Empréstimos", "(6) Investimentos", "(7) Sair"};
		
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
			TelaDialogo.mostrarListaOpcoesListagemPainel("Banco Java SA", "Operações disponíveis:", "Selecione uma opção do menu", 
				OperacaoContaBancaria.values());
		System.out.println(s);
	}	
	
}
