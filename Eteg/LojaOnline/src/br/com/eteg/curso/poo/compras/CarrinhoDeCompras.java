package br.com.eteg.curso.poo.compras;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {

	private List<Produto> produtos;
	private Comprador comprador;
	private double total;
	private double frete;
	
	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}


	public List<Produto> getProdutos() {
		inicializarProdutos();
		return produtos;
	}

	public void adicionarProduto(Produto produto) {
		inicializarProdutos();		
		produtos.add(produto);
	}

	private void inicializarProdutos() {
		if (produtos == null)
		{
			produtos = new ArrayList<Produto>();
		}
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getFrete() {
		return frete;
	}

	public void setFrete(double frete) {
		this.frete = frete;
	}
	
	
}
