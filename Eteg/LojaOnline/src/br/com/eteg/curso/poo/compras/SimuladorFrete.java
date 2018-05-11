package br.com.eteg.curso.poo.compras;

public class SimuladorFrete {

	private static String [] cidades = {"Belo Horizonte", "Contagem"};
	private static double [] valores = {22.50, 30.00};
	
	
	public static double calcularFrete(Comprador c) throws CidadeFreteNaoEncontradaException
	{
		boolean achouCidade = false;
		double valor = 0;
		for (int i = 0; i < cidades.length; i++)
		{
			String s = cidades[i];
			if (s.equals(c.getCidade()))
			{
				achouCidade = true;
				valor = valores[i];
				break;
			}
		}
		if (!achouCidade)
		{
			throw new CidadeFreteNaoEncontradaException(c);
		}
		return valor;
	}
	
}
