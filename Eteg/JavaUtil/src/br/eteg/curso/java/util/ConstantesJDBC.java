package br.eteg.curso.java.util;

public interface ConstantesJDBC {

	// DRIVER DE CONEXAO
	public static final String HSQLDB_DRIVER = 
		"org.hsqldb.jdbcDriver";
	
	public static final String MYSQL_DRIVER = 
		"com.mysql.jdbc.Driver";
	
	// URL
	
	public static final String MYSQL_URL = 
		"jdbc:mysql://localhost:3306/contas?user=root&password=";
	
	public static final String HSQLDB_URL_MEMORIA_VM = 
		"jdbc:hsqldb:mem:memoria";
	
	public static final String HSQLDB_URL_SERVIDOR = 
		"jdbc:hsqldb:hsql://localhost/xdb";
	
	public static final String HSQLDB_URL_ARQUIVO = 
		"jdbc:hsqldb:file:/opt/db/testdb";
	
	// PARAMETROS
	
	public static final String HSQLDB_USER = "su";
	
	public static final String HSQLDB_PW = "";

	public static final String MYSQL_USER = "root";
	
	public static final String MYSQL_PW = "";

}
