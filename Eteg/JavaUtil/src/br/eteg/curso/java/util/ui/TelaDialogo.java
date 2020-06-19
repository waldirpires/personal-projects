package br.eteg.curso.java.util.ui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import br.eteg.curso.java.util.datatype.StringUtil;

public class TelaDialogo{

	public static String OPCAO_CANCELAR = "CANCELAR";
	public static String OPCAO_SIM = "SIM";
	public static String OPCAO_NAO = "NAO";
	public static String RETORNO_SEM_DADO = "SEM_DADO";
	public static String value = "";
	
	public static final Font font = new Font("Arial", Font.BOLD, 16);
	
	private static JLabel gerarLabelDeMensagem(String mensagem)
	{
		JLabel label = new JLabel(mensagem);
		label.setFont(font);
		return label;
	}
	
	/**
	 * mostra uma mensagem de erro
	 * @param titulo o titulo da tela
	 * @param mensagem a mensagem
	 */
	public static void mostrarMensagemErro(
			String titulo, Object mensagem)
	{
		JOptionPane.showMessageDialog(null, 
				mensagem, titulo, 
				JOptionPane.ERROR_MESSAGE);
		LogUtil.debug(mensagem.toString());
	}
	
	/**
	 * mostra uma mensagem de erro
	 * @param titulo o titulo da tela
	 * @param mensagem a mensagem
	 */
	public static void mostrarMensagemErro(
			String titulo, Object mensagem, boolean usarFonte)
	{
		mostrarMensagemErro(titulo, 
				usarFonte?gerarLabelDeMensagem(mensagem.toString()):mensagem);
	}
	
	/**
	 * mostra a tela de entrada de dados
	 * @param titulo o titulo da tela
	 * @param mensagem a mensagem
	 * @return o valor entrado
	 */
	public static String mostrarTelaEntrada(
			String titulo, Object mensagem)
	{
		String s = JOptionPane.showInputDialog(null, 
				mensagem, titulo, 
				JOptionPane.INFORMATION_MESSAGE);
		return tratarRespostaTela(s);		
	}

	private static String tratarRespostaTela(Object s) {
		if (s != null)
		{
			String resp = s instanceof Enum?((Enum)s).name():s.toString();
			if ("".equals(resp))
			{
				return RETORNO_SEM_DADO;
			}
			return StringUtil.isEmpty(resp)?OPCAO_CANCELAR:resp;
		} else {
			return RETORNO_SEM_DADO;
		}
	}
	
	/**
	 * mostra uma tela de confirmacao
	 * @param titulo o título
	 * @param mensagem a mensagem
	 * @return resultado da confirmacao
	 */
	public static String mostrarTelaConfirmacao(
			String titulo, Object mensagem)
	{
		int i = JOptionPane.showConfirmDialog(null,
				mensagem, titulo, 
	            JOptionPane.YES_NO_OPTION);
		return i == 0?OPCAO_SIM:OPCAO_NAO;
	}
	
	/**
	 * mostra uma tela de informação
	 * @param titulo o titulo da tela
	 * @param mensagem a mensagem
	 */
	public static void mostrarTelaInformacao(
			String titulo, Object mensagem)
	{
		JOptionPane.showMessageDialog(null,
				mensagem, titulo, 
	            JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * mostra uma tela de informação
	 * @param titulo o titulo da tela
	 * @param mensagem a mensagem
	 */
	public static void mostrarTelaAviso(
			String titulo, Object mensagem)
	{
		JOptionPane.showMessageDialog(null,
				mensagem, titulo, 
	            JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * mostra a lista de opções na tela de diálogo.
	 * @param titulo o título da tela
	 * @param opcoes as opcoes
	 * @return a opção selecionada
	 */
	public static String mostrarListaOpcoes(String titulo, 
			Object [] opcoes)
	{
		StringBuffer buffer = new StringBuffer();
		
		for (Object s: opcoes)
		{
			buffer.append(s).append("\n");
		}		
		buffer.append("\n").append("Escolha uma opção:").append("\n");
		return mostrarTelaEntrada(titulo, buffer.toString());		
	}
	
	/**
	 * mostra a lista de opções na tela de diálogo.
	 * @param titulo o título da tela
	 * @param opcoes as opcoes
	 * @return a opção selecionada
	 */
	public static String mostrarListaOpcoesListagemCombo(String titulo,
			String mensagem, Object [] opcoes)
	{
		Object s = JOptionPane.showInputDialog(
                null, mensagem, titulo, JOptionPane.OK_CANCEL_OPTION,
                null, opcoes, "");
		return tratarRespostaTela(s);		
	}
	
	/**
	 * mostra a lista de opções na tela de diálogo.
	 * @param titulo o título da tela
	 * @param opcoes as opcoes
	 * @return a opção selecionada
	 */
	public static String mostrarListaOpcoesListagemScroll(String titulo,
			String mensagem, Object [] opcoes)
	{
		JList list = new JList(opcoes); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(opcoes.length);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		JOptionPane.showConfirmDialog(
                null, listScroller, titulo, JOptionPane.OK_CANCEL_OPTION);
		return tratarRespostaTela(list.getSelectedValue());		
	}

	/**
	 * mostra a lista de opções na tela de diálogo usando uma lista
	 * @param titulo o título da tela
	 * @param mensagemTopo a mensagem no topo
	 * @param mensagemBaixo a mensagem embaixo
	 * @param opcoes as opcoes
	 * @return o indice da opção selecionada
	 */
	public static String mostrarListaOpcoesListagemPainel(String titulo,
			String mensagemTopo, String mensagemBaixo, Object [] opcoes)
	{
		JList list = new JList(opcoes); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		//list.setVisibleRowCount(opcoes.length);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 200));
		list.setFont(font);
		list.setSelectedIndex(0);
		
		JPanel painel2 = new JPanel(new BorderLayout());
		painel2.add(new JPanel(), BorderLayout.NORTH);
		painel2.add(gerarLabelDeMensagem(mensagemTopo), BorderLayout.CENTER);
		painel2.add(new JPanel(), BorderLayout.SOUTH);

		JPanel painel3 = new JPanel(new BorderLayout());
		painel3.add(new JPanel(), BorderLayout.NORTH);
		painel3.add(gerarLabelDeMensagem(mensagemBaixo), BorderLayout.CENTER);
		painel3.add(new JPanel(), BorderLayout.SOUTH);

		JPanel painel = new JPanel(new BorderLayout());
		painel.add(painel2, BorderLayout.NORTH);
		painel.add(listScroller, BorderLayout.CENTER);
		painel.add(painel3, BorderLayout.SOUTH);

		JOptionPane.showConfirmDialog(
                null, painel, titulo, JOptionPane.OK_CANCEL_OPTION);
		return tratarRespostaTela(list.getSelectedValue());		
	}

	
}
