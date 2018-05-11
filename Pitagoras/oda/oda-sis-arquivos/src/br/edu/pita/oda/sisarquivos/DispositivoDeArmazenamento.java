package br.edu.pita.oda.sisarquivos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispositivoDeArmazenamento {

	Map<String, List<String>> tabelaDeArquivos;
	Map<String, List<Integer>> tabelaDeAlocacao;
	List<Boolean> vetorAlocacaoLivre;
	int tamanho;
	
	public DispositivoDeArmazenamento(int tamanho){
		tabelaDeArquivos = new HashMap<String, List<String>>();
		tabelaDeArquivos.put("/", new ArrayList<String>());
		tabelaDeAlocacao = new HashMap<String, List<Integer>>();
		this.tamanho = tamanho;
		vetorAlocacaoLivre = new ArrayList<Boolean>();
	}
	
	public boolean criarNovoRecurso(String pai, String recurso, boolean ehArquivo){
		boolean sucesso = false;
		
		// 1) obter o diretorio pai
		List<String> diretorioList = tabelaDeArquivos.get(pai);
		// 2) se o diretório NAO existir
		if (diretorioList == null){
		// 2.1) ERRO: diretorio pai nao existe
			System.out.println("ERRO: diretório nao existe!");
		} else {		// 3) Senão
		// 3.1) obter o diretório pai
			if (diretorioList.contains(recurso)){		// 3.2) verificar se o recurso jah existe dentro do diretório
		// 3.3) se o recurso já existir:
		// 3.1.1) ERRO: recurso já existe
				System.out.println("ERRO: recurso já existe na tabela!");
			} else {// 3.4) senao
		// 3.4.1) adicionar o novo recurso no diretório
				diretorioList.add(recurso);
		// 3.4.2) se o recurso for um diretório
				if (!ehArquivo){
		// 3.4.2.1) adicionar o novo diretório na tabela
					tabelaDeArquivos.put(recurso, new ArrayList<String>());
				}
			}
		}
		return sucesso;
	}
	
	public void listarRecursos(String pai, String recurso){
		List<String> list = tabelaDeArquivos.get(recurso);
		System.out.println();
		String fullPath = pai + "\\" + recurso;
		System.out.println("Diretório: " + fullPath + " (" + list.size() + ")");
		String prefix = "";
		for (String s: list){
			if (tabelaDeArquivos.containsKey(s)){
				prefix = "D - ";
			} else {
				prefix = "F - ";
			}
			System.out.println(prefix + s);
		}
		for (String s: list){
			if (tabelaDeArquivos.containsKey(s)){
				listarRecursos(fullPath, s);
			}
		}

	}
	
	public boolean excluirRecurso(String nome){
		boolean sucesso = false;
		
		return sucesso;
	}
	
	public boolean alocarUnidadesDeDisco(String recurso, int bytes){
		boolean sucesso = false;
		int numUnidades = bytes/512;
		return sucesso;
	}
	
	public static void main(String[] args) {
		DispositivoDeArmazenamento da = new DispositivoDeArmazenamento(20);
		da.criarNovoRecurso("/", "windows", false);
		da.listarRecursos("", "/");
		da.criarNovoRecurso("windows", "notepad.exe", true);
		da.criarNovoRecurso("windows", "test.txt", true);
		System.out.println();
		da.listarRecursos("", "/");
	}
	
	
}
