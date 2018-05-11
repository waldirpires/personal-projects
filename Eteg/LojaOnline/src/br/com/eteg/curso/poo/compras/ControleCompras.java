package br.com.eteg.curso.poo.compras;

public class ControleCompras {

	
	public void executarCompra(CarrinhoDeCompras cc)
	{
		// verificar estoque
		double soma = 0;
		try {
			for (Produto p: cc.getProdutos())
			{
				if (!p.isPossuiEstoque())
				{
					throw new ProdutoSemEstoqueException(p);
				}
				// calcular soma total
				soma += p.getValor();
			}
			cc.setTotal(soma);			
			// calcular frete
			soma = SimuladorFrete.calcularFrete(cc.getComprador());
			cc.setFrete(soma);
			// definir forma de pagamento
			FormaDePagamento fp = definirFormaDePagamento("cc", "AMEX");
			// verificar aprovação de compra
			aprovarCompra(cc, fp);
			// finalizar compra
		} catch (ProdutoSemEstoqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CidadeFreteNaoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormaDePagamentoNaoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void aprovarCompra(CarrinhoDeCompras cc, FormaDePagamento fp) {
		// verificar comprador na operadora de crédito
		
		// verificar saldo cartao crédito
		
	}

	private FormaDePagamento definirFormaDePagamento(String forma, Object tipo) throws FormaDePagamentoNaoEncontradaException {
		
		if (forma.equals("cc"))
		{
			FormaDePagamento fp = FormaDePagamento.valueOf(tipo.toString());
			if (fp == null)
			{
				throw new FormaDePagamentoNaoEncontradaException(tipo.toString());
			}
			return fp;
		}
		return null;
		
	}
	
}
