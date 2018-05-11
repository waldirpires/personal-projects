package br.pitagoras.oda.orgarq;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcessadorDeArquivoIndexadoTest {

	private ProcessadorDeArquivoIndexado arquivoIndexado;
	private String nomeArquivoDeDados;
	private String nomeArquivoDeIndices;
	
	@Before
	public void setup(){
		nomeArquivoDeDados = "dados.txt";
		nomeArquivoDeIndices = "indices.txt";
		
		arquivoIndexado = new ProcessadorDeArquivoIndexado(nomeArquivoDeIndices, nomeArquivoDeDados);
	}
	
	@Test
	public void testbuscar() throws IOException
	{
		// id;nome;cidade;salario\r\n
		FileUtils.writeStringToFile(new File(nomeArquivoDeDados), "2;João da Silva;Belo Horizonte;1800\r\n5;Maria das Dores;Contagem;1550\r\n");
		FileUtils.writeStringToFile(new File(nomeArquivoDeIndices), "2;0\r\n5;1\r\n");
		
		String chave = "5";
		
		RegistroDeArquivo registro = arquivoIndexado.buscar(chave);
		Assert.assertNotNull(registro);
		System.out.println(registro);
		
		chave = "2";
		registro = arquivoIndexado.buscar(chave);
		Assert.assertNotNull(registro);
		System.out.println(registro);
		
		chave = "1";
		registro = arquivoIndexado.buscar(chave);
		Assert.assertNull(registro);
		
	}
	
	@Test
	public void testinserir() throws IOException{

		imprimirArquivoDeIndices();

		imprimirArquivoDeDados();
		
//		String arquivoDeDadosOriginal = FileUtils.readFileToString(new File(nomeArquivoDeDados));
//		String arquivoDeIndicesOriginal = FileUtils.readFileToString(new File(nomeArquivoDeIndices));
//		
		FileUtils.writeStringToFile(new File(nomeArquivoDeDados), "");
		FileUtils.writeStringToFile(new File(nomeArquivoDeIndices), "");
		
		//List<String> listaDados = OrgArquivosUtil.obterRegistrosDoArquivo(nomeArquivoDeDados);
		
//		String colunas = "id;nome;cidade;salario";
		int id = 20;
		String campos = id+";João Da Silva; Belo Horizonte; 1500";
		RegistroDeArquivo registro = new RegistroDeArquivo(campos);
		arquivoIndexado.inserir(registro);
		
		RegistroDeArquivo reg = arquivoIndexado.buscar(String.valueOf(id));
		Assert.assertNotNull(reg);
		Assert.assertEquals(registro, reg);

		try {
			id = 20;
			campos = id+";João Da Silva; Belo Horizonte; 1500";
			registro = new RegistroDeArquivo(campos);
			arquivoIndexado.inserir(registro);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof RuntimeException);
		}

		
		arquivoIndexado.excluir(String.valueOf(id));
		reg = arquivoIndexado.buscar(String.valueOf(id));
		Assert.assertNull(reg);
		
//		FileUtils.writeStringToFile(new File(nomeArquivoDeDados), arquivoDeDadosOriginal);
//		FileUtils.writeStringToFile(new File(nomeArquivoDeIndices), arquivoDeIndicesOriginal);		

		id = 20;
		campos = id+";João Da Silva; Belo Horizonte; 1500";
		registro = new RegistroDeArquivo(campos);
		arquivoIndexado.inserir(registro);
		
		imprimirArquivoDeIndices();

		imprimirArquivoDeDados();
	}


	@Test
	public void testinserirVarios() throws IOException{

		FileUtils.writeStringToFile(new File(nomeArquivoDeDados), "");
		FileUtils.writeStringToFile(new File(nomeArquivoDeIndices), "");
		
		String campos = "020;João Da Silva; Belo Horizonte; 1500";
		RegistroDeArquivo registro = new RegistroDeArquivo(campos);
		arquivoIndexado.inserir(registro);

		imprimirArquivoDeIndices();

		campos = "021;João Da Silva; Belo Horizonte; 1500";
		registro = new RegistroDeArquivo(campos);
		arquivoIndexado.inserir(registro);
		
		imprimirArquivoDeIndices();
		
		campos = "005;João Da Silva; Belo Horizonte; 1500";
		registro = new RegistroDeArquivo(campos);
		arquivoIndexado.inserir(registro);
		
		imprimirArquivoDeIndices();
		
		campos = "001;João Da Silva; Belo Horizonte; 1500";
		registro = new RegistroDeArquivo(campos);
		arquivoIndexado.inserir(registro);
		
		imprimirArquivoDeIndices();
		
		campos = "003;João Da Silva; Belo Horizonte; 1500";
		registro = new RegistroDeArquivo(campos);
		arquivoIndexado.inserir(registro);

		imprimirArquivoDeIndices();

		
	}


	
	@Test
	public void testarAtualizar() throws IOException{

		FileUtils.writeStringToFile(new File(nomeArquivoDeDados), "2;João da Silva;Belo Horizonte;1800\r\n5;Maria das Dores;Contagem;1550\r\n");
		FileUtils.writeStringToFile(new File(nomeArquivoDeIndices), "2;0\r\n5;1\r\n");
		
		String chave = "5";
		
		RegistroDeArquivo registro = arquivoIndexado.buscar(chave);
		Assert.assertNotNull(registro);
		
		arquivoIndexado.atualizar(chave, "Contagem", "Betim");
		
		chave = "5";
		registro = arquivoIndexado.buscar(chave);
		Assert.assertNotNull(registro);
		Assert.assertTrue(registro.toString().contains("Betim"));

		
		
	}
	
	private void imprimirArquivoDeDados() throws IOException {
		System.out.println("Arquivo de dados: ");
		System.out.println(FileUtils.readFileToString(new File(nomeArquivoDeDados)));
	}

	private void imprimirArquivoDeIndices() throws IOException {
		System.out.println("Arquivo de indices: ");
		System.out.println(FileUtils.readFileToString(new File(nomeArquivoDeIndices)));
	}
}
