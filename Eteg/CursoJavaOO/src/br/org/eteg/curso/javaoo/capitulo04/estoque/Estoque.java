/**
 * 
 */
package br.org.eteg.curso.javaoo.capitulo04.estoque;


/**
 * @author Waldir Pires
 *
 */
public class Estoque {

	private int quantidateItens;
	private final int MAXIMO_ITENS = 10;
	
	public void inserirItensNoEstoque(int quantidade) 
	  throws ArithmeticException, Exception
	{
		assert quantidade > 0;
		// verificacao no limite de itens
		if (quantidateItens + quantidade > MAXIMO_ITENS)
		{
			throw new EstoqueInvalidoException(quantidateItens);
		} else 
		{
			quantidateItens += quantidade;
		}
	}
	
	public void removerItensDoEstoque(int quantidade)
	{
		if (quantidateItens < quantidade)
		{
			System.out.println("Quantidade de itens existente menor");
		} else
		{
			quantidateItens -= quantidade;
		}
	}
	
	public int getQuantidade()
	{
		return quantidateItens;
	}
	
	public static void main(String[] args) throws Exception{

		Estoque estoque = new Estoque();
		
		
			int a = 4 / 0;
			estoque.inserirItensNoEstoque(7);
			System.out.println(estoque.getQuantidade());
			
			estoque.inserirItensNoEstoque(4);
			System.out.println(estoque.getQuantidade());
			
			estoque.removerItensDoEstoque(8);
			System.out.println(estoque.getQuantidade());

		
		
	}

}
