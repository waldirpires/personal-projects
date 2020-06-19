package br.org.eteg.curso.javaoo.capitulo11.sql;

import java.util.Collection;

public class DatabaseExemplo {

	private static final String CRIAR_TABELA_CLIENTES = 
		"CREATE TABLE Clientes (codCliente INTEGER NOT NULL," + 
		"empresa VARCHAR(255) NOT NULL," + 
		"  contato VARCHAR(255) NULL," +
		"  cargo VARCHAR(255) NULL," +
		"  cidade VARCHAR(100) NULL," +
		"  cep VARCHAR(10) NULL," +
		"  PRIMARY KEY(codCliente))";
	
	private static final String SELECT_CLIENTES = 
		"select * from CLIENTES";
	
	private static final String INSERT_CLIENTES = 
		"insert into clientes " +
		"(codcliente, empresa, contato, cargo, cidade, cep)" +
		" values " +
		"(1, 'Siemens', 'Waldir Pires', 'Diretor', 'Belo Horizonte', '32000')";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// prepara os dados para a conexao
		String dbUrl = DatabaseManager.URL_MEMORIA_VM;
		String user = "sa";
		String pw = "";
		
		// instancia o database manager
		DatabaseManager manager = new DatabaseManager(dbUrl, user, pw);
		
		// inicia a conexao
		manager.iniciarConexao();
		
		// verifica se a conexao foi estabelecida com sucesso
		System.out.println(manager.isConectado());
		
		// cria a tabela de clientes
		manager.executar(CRIAR_TABELA_CLIENTES);
		// insere dados na tabela clientes
		manager.executar(INSERT_CLIENTES);
		
		// executa um select
		Collection<EntidadeBanco> entidades = 
			manager.executarSelect(SELECT_CLIENTES);
		
		// imprime os resultados
		for (EntidadeBanco entidade: entidades)
		{
			Collection<String> colunas = entidade.getListaPropriedades();
			for (String coluna:colunas)
			{
				System.out.print("(" + coluna + " = " + 
						entidade.getValor(coluna) + ")");
			}
			System.out.println();
		}
		
		// fecha a conexao
		manager.fecharConexao();
		
	}

}
