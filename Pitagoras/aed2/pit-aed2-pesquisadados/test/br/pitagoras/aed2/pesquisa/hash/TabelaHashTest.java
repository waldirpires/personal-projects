package br.pitagoras.aed2.pesquisa.hash;

import org.junit.Assert;
import org.junit.Test;

public class TabelaHashTest {

	// testar a criacao da tabela
	@Test
	public void testCriacaoTabelaHash(){
		// definir os parametros de teste
		int tamanho = 100;
		// criar a tabela com os parametros
		TabelaHash tabela = new TabelaHash(tamanho);
		// verificar se a tabela foi criada com sucesso
		Assert.assertEquals(tamanho, tabela.getTamanho());
		Assert.assertEquals(0, tabela.getOcupacao());
	}
	
	// testar a insercao (put)
	@Test
	public void testPut(){
		// definir os parametros de teste
		int tamanho = 100;
		int chave = 40;
		int valor = 40;
		// criar a tabela hash
		TabelaHash tabela = new TabelaHash(tamanho);
		Assert.assertEquals(0, tabela.getOcupacao());
		// inserir um elemento
		tabela.put(chave, valor);
		// verificar se a ocupacao foi atualizada
		Assert.assertEquals(1, tabela.getOcupacao());
		// verificar se o elemento for inserido na tabela
		Assert.assertEquals(valor, tabela.get(chave));
	}
	
	// testar a busca (get) sem sucesso
	@Test
	public void testGet(){
		// definir os parametros de entrada
		int tamanho = 100;
		int chave = 50;
		// criar a tabela hash
		TabelaHash tabela = new TabelaHash(tamanho);
		Assert.assertEquals(0, tabela.getOcupacao());
		// buscar por um elemento que nao existe
		int valor = tabela.get(chave);
		// verificar se ele nao é encontrado
		Assert.assertEquals(-1, valor);
	}
	
	// testar a insercao com colisoes
	@Test
	public void testPutComColisao(){
		// definir os parametros de teste
		int tamanho = 100;
		int chave = 40;
		int valor1 = 40;
		int valor2 = 80;
		// criar a tabela hash
		TabelaHash tabela = new TabelaHash(tamanho);
		Assert.assertEquals(0, tabela.getOcupacao());
		// inserir o primeiro elemento
		tabela.put(chave, valor1);
		// verificar se ele está na tabela
		Assert.assertEquals(valor1, tabela.get(chave));
		// inserir o segundo elemento com a mesma chave
		tabela.put(chave, valor2);
		// verificar que o segundo elemento nao foi inserido
		Assert.assertTrue(valor2 != tabela.get(chave));
		
	}
	
}
