package br.eteg.curso.java.util.tela;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.eteg.curso.java.util.ui.FontUtil;

public class TelaMenuOpcoes extends JFrame{

	private String [] opcoes;
	private JButton [] botoes;
	private ActionListener listener;
	private int tipo;
	
	public TelaMenuOpcoes(String titulo, String [] ops, ActionListener listener)
	{
		setTitle(titulo);
		opcoes = ops;
		this.listener = listener;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		init();
		setVisible(true);
	}
	
	private void init()
	{
		JPanel painelTopo = new JPanel();
		JPanel painelCentro = new JPanel();
		JPanel painelAbaixo = new JPanel();
		
		GridLayout grid = new GridLayout();
		grid.setRows(opcoes.length/2);
		grid.setColumns(2);
		painelCentro.setLayout(grid);
		
		botoes = new JButton[opcoes.length];
		//criarMenuComum(painelCentro);
		criarMenuColunas(painelCentro);
		getContentPane().add(painelCentro, BorderLayout.CENTER);
		getContentPane().add(painelTopo, BorderLayout.NORTH);
		getContentPane().add(painelAbaixo, BorderLayout.SOUTH);
	}

	private void criarMenuColunas(JPanel painelCentro) {
		for (int i = 0; i < opcoes.length; i++)
		{
			Icon icon = new ImageIcon("res/Print24.gif", 
					opcoes[i]);
			botoes[i] = new JButton(opcoes[i], icon);
			botoes[i].addActionListener(listener);
			botoes[i].setFont(FontUtil.FONTE_MONOSPACED);
			botoes[i].setHorizontalAlignment(SwingConstants.LEFT);
			painelCentro.add(botoes[i], null);
		}
	}

	private void criarMenuComum(JPanel painelCentro) {
		for (int i = 0; i < opcoes.length; i++)
		{
			botoes[i] = new JButton(opcoes[i]);
			botoes[i].addActionListener(listener);
			botoes[i].setFont(FontUtil.FONTE_MONOSPACED);
			botoes[i].setHorizontalAlignment(SwingConstants.LEFT);
			painelCentro.add(botoes[i]);
		}
	}
	
	public static void main(String[] args) {
		
		String [] opcoes = {"Extrato/Saldo", "Depósitos", "Saques", "Sair"};
		TelaMenuOpcoes tela = new TelaMenuOpcoes("Banco Java SA", opcoes, 
				new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());				
			}});
	}
}
