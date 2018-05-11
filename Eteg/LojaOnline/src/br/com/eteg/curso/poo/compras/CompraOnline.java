package br.com.eteg.curso.poo.compras;

public class CompraOnline {

	public static void main(String[] args) {
		
		CarrinhoDeCompras cc = new CarrinhoDeCompras();
		Comprador c = new Comprador();
		c.setNome("Waldir");
		c.setCidade("Betim");
		cc.setComprador(c);
		
		Produto p1 = new Produto();
		p1.setDescricao("Berço Burigoto");
		p1.setValor(200);
		p1.setPossuiEstoque(true);
		cc.adicionarProduto(p1);
		
		Produto p2 = new Produto();
		p2.setDescricao("Chupeta A1");
		p2.setValor(10);
		p2.setPossuiEstoque(false);
		cc.adicionarProduto(p2);
		
		ControleCompras controle = new ControleCompras();
		controle.executarCompra(cc);
		
		
	}
}
