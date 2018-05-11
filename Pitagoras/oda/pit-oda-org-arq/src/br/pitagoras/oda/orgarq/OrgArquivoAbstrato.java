package br.pitagoras.oda.orgarq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.pitagoras.oda.orgarq.util.OrgArquivosUtil;

public abstract class OrgArquivoAbstrato {

	private String nomeArquivoDados;
	private String nomeArquivoIndices;

	protected RegistroDeArquivo buscarEmArquivoDeDados(String chave){
		RegistroDeArquivo registro = null;
		
        try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoDados));
			String s = br.readLine();
			String colunas = null;
			if (s != null){
				colunas = s;
			}
			s = br.readLine();
			while (s != null) {
				if (chave.equals(s.split(";")[0])){
					registro = new RegistroDeArquivo(s);
					break;
				}
				s = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.err.println("ERRO ao realizar a busca pela chave " + chave);
			throw new RuntimeException("ERRO ao realizar a busca pela chave ", e);
		} 
		return registro;
	}
	
	public void setNomeArquivoDados(String nomeArquivoDados) {
		this.nomeArquivoDados = nomeArquivoDados;
	}
	
	public void setNomeArquivoIndices(String nomeArquivoIndices) {
		this.nomeArquivoIndices = nomeArquivoIndices;
	}
	
	public String getNomeArquivoDados() {
		return nomeArquivoDados;
	}
	
	public String getNomeArquivoIndices() {
		return nomeArquivoIndices;
	}

	public List<RegistroDeArquivo> listarRegistros() {
		
		List<RegistroDeArquivo> registros = new ArrayList<RegistroDeArquivo>();
		RegistroDeArquivo reg = null;
		
		try {
			// Para cada registro no arquivo de dados
			List<String> listDados = OrgArquivosUtil.obterRegistrosDoArquivo(getNomeArquivoDados());
			for (String s: listDados){
			//   se o registro possuir o valor
					reg = new RegistroDeArquivo(s);
					registros.add(reg);
			}
			// retornar a listagem de resultados
		} catch (Exception e) {
			String msg = "ERRO ao tentar listar registros";
			System.err.println(msg);
			throw new RuntimeException(msg, e);
		}
		
		return registros;
	}
	
	public List<RegistroDeArquivo> pesquisarRegistrosEmArquivoDeDados(String valor) {
		
		List<RegistroDeArquivo> registros = new ArrayList<RegistroDeArquivo>();
		RegistroDeArquivo reg = null;
		
		try {
			// Para cada registro no arquivo de dados
			List<String> listDados = OrgArquivosUtil.obterRegistrosDoArquivo(getNomeArquivoDados());
			for (String s: listDados){
			//   se o registro possuir o valor
					if (s.contains(valor)){
						reg = new RegistroDeArquivo(s);
						registros.add(reg);
					}
			}
			// retornar a listagem de resultados
		} catch (Exception e) {
			String msg = "ERRO ao tentar listar registros";
			System.err.println(msg);
			throw new RuntimeException(msg, e);
		}
		
		return registros;
	}
	
	public List<String> listarIndices(){
		try {
			return OrgArquivosUtil.obterRegistrosDoArquivo(getNomeArquivoIndices());
		} catch (Exception e) {
			String msg = "ERRO ao tentar listar os indices do arquivo";
			System.err.println(msg);
			throw new RuntimeException(msg, e);
		}
	}
	
}
