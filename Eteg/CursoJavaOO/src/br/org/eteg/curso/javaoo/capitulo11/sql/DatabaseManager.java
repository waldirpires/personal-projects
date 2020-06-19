package br.org.eteg.curso.javaoo.capitulo11.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DatabaseManager {

	private static final String DRIVER = 
		"org.hsqldb.jdbcDriver";

	public static final String URL_MEMORIA_VM = 
		"jdbc:hsqldb:mem:memoria";
	
	public static final String URL_SERVIDOR = 
		"jdbc:hsqldb:hsql://localhost/xdb";
	
	public static final String URL_ARQUIVO = 
		"jdbc:hsqldb:file:/opt/db/testdb";
	
	private String dbUrl;
	private String user;
	private String pw;
	private Connection con;
	private String fileName;
	
	/**
	 * o construtor padrao
	 */
	public DatabaseManager()
	{
		
	}
	
	/**
	 * construtor com parametros
	 * @param dbUrl a URL de conexao
	 * @param user o usuario
	 * @param pw a senha
	 */
	public DatabaseManager(String dbUrl, String user, String pw)
	{
		this.dbUrl = dbUrl;
		this.user = user;
		this.pw = pw;
	}
	
	/**
	 * metodo que inicia a conexao
	 */
	public void iniciarConexao()
	{
		try {
			// 1: buscar a classe DRIVER para a conexao
			Class.forName(DRIVER);
			
			// 2: conectar
			conectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void conectar()
	{
		try {
			con = DriverManager.getConnection(dbUrl, user, pw);
			System.out.println("Conexao em " + dbUrl + " efetuada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo que fecha a conexao com o banco
	 */
	public void fecharConexao()
	{
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo que verifica se a aplicacao estã conectada com o banco
	 * @return true se estiver conectada, false o contrario
	 */
	public boolean isConectado()
	{
		boolean isConnected = false;
		try {
			if (con != null)
			{
				isConnected = !con.isClosed();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			return isConnected;
		}
	}
	
	/**
	 * executa um SQL de select
	 * @param sql o SQL a ser executado
	 * @return a lista de resultados
	 */
	public Collection<EntidadeBanco> executarSelect(String sql)
	{
		Collection<EntidadeBanco> entidades = new ArrayList<EntidadeBanco>();
		try {
			
			// verifica se a conexao continua ativa
			if (con == null || con.isClosed())
			{
				new Exception("Conexao está fechada");
			}
			
			// prepara o statement
			Statement stmt = con.createStatement();
			
			// executa a query a recebe um resultset 
			ResultSet rs = stmt.executeQuery(sql);
			
			// obtem o numero de colunas retornadas
			int colunas = rs.getMetaData().getColumnCount();
			
			String [] nomesColunas = new String[colunas];
			
			// obtem as colunas retornadas
			for (int i = 1; i < colunas; i++)
			{
				nomesColunas[i-1] = rs.getMetaData().getColumnName(i);
			}
			
			// popula uma lista de entidades (propriedade, valor)
			EntidadeBanco entidade = null;
			while (rs.next())
			{
				entidade = new EntidadeBanco();
				for (int i = 1; i < colunas; i++)
				{
					entidade.adicionarPropriedade(nomesColunas[i-1], rs.getString(i));
				}
				entidades.add(entidade);
			}
			// fecha o statement
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			System.out.println("Foi retornado " + entidades.size() + " clientes");
			return entidades;
		}
	}
	
	/**
	 * executa uma query de insert, create e update
	 * @param sql o comando SQL
	 */
	public void executar(String sql)
	{
		try {
			
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			con.commit();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
