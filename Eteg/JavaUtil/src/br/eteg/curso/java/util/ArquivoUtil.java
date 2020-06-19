package br.eteg.curso.java.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import javax.swing.ImageIcon;

public class ArquivoUtil {

	public static final String CAMINHO_DIR = 
		"c:/cursojavaoo/";
	
	private static void criarDirSeNecessario()
	{
		File f = new File(CAMINHO_DIR);
		if (!f.exists())
		{
			f.mkdir();			
		}
	}
	
	public static void criarDirSeNecessario(String dir)
	{
		File f = new File(dir);
		if (!f.exists())
		{
			f.mkdir();			
		}
	}
	
	/**
	 * metodo estático que cria um arquivo contendo dados para 
	 * extratos em HTML e TXT
	 * @param nome nome do arquivo
	 * @param dados os dados a serem escritos
	 * @throws IOException
	 */
	public static void criarArquivoDados(String nome, StringBuilder dados) 
	throws IOException
	{
		// criar arquivo para dados
		criarDirSeNecessario();
		FileOutputStream fos = 
			new FileOutputStream(CAMINHO_DIR + 
					nome);
		
		fos.write(dados.toString().getBytes());
		fos.flush();
		fos.close();
	}
	
	public static void salvarAtributosEmArquivo(String nome, 
			String [] atributos) throws IOException
	{
		criarDirSeNecessario();
		FileOutputStream fw = new FileOutputStream(
				CAMINHO_DIR + nome);
		DataOutputStream dos = new 
			DataOutputStream(fw);
		
		dos.writeInt(atributos.length);
		for (String s :atributos)
		{
			dos.writeUTF(s);
		}
		dos.flush();
		dos.close();
		fw.close();
	}

	public static String [] lerAtributosDeArquivo(String nome) throws IOException
	{
		FileInputStream fr = new FileInputStream(
				CAMINHO_DIR + nome);
		DataInputStream dis = new 
			DataInputStream(fr);
		
		int tamanho = dis.readInt();
		String [] v = new String[tamanho];
		for (int i = 0; i < tamanho; i++)
		{
			v[i] = dis.readUTF();
		}
		return v;
	}
	
	/**
	 * metodo estatico que salva os dados de uma conta bancaria em um arquivo.
	 * @param cb a conta bancaria a se salva
	 * @throws IOException
	 */
	public static void salvarObjetoEmArquivo(String numero,
			Object cb) throws IOException
	{
		// salvar a conta bancária em arquivo
		String nomeArquivo = 
			  "c:/cursojava/cc"+ numero +".cc";
			FileOutputStream fos = new FileOutputStream(nomeArquivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(cb);

			oos.flush();
			oos.close();
			fos.close();
	}
	
	/**
	 * metodo estático que lêe os dados de uma conta bancaria de um arquivo
	 * @return a conta bancária
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object lerObjetoDeArquivo(String numero) throws 
	IOException, ClassNotFoundException
	{
		// ler a conta bancária de um arquivo
		String nomeArquivo = "c:/cursojava/cc"+ numero +".cc";
		FileInputStream fis = new FileInputStream(nomeArquivo);
		ObjectInputStream ois = new ObjectInputStream(fis);

		Object cb = ois.readObject();

		ois.close();
		fis.close();
		return cb;
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = path.getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}	
	
    /**
     * método que carrega um arquivo de propriedades.
     * @param fileName o nome do arquivo
     * @return o objeto Properties
     * @throws IOException
     * @throws FileNotFoundException
     */
	public static Properties carregarArquivoPropriedades(String fileName)
			throws IOException, FileNotFoundException {

		return loadPropertiesFromFile(new File(fileName));

	}

    /**
     * método que carrega um arquivo de propriedades.
     * @param path o nome do caminho do arquivo
     * @param fileName o nome do arquivo
     * @return o objeto Properties
     * @throws IOException
     * @throws FileNotFoundException
     */
	public static Properties carregarArquivoPropriedades(String path, String fileName)
			throws IOException, FileNotFoundException {

		return loadPropertiesFromFile(new File(path+fileName));

	}

	private static Properties loadPropertiesFromFile(File file)
			throws IOException, FileNotFoundException {

		Properties properties = new Properties();

		properties.load(new FileInputStream(file));

		return properties;

	}

	/**
	 * lista os elementos e sub elementos de um diretório
	 * @param local
	 */
	public static void listarDiretorios(String local)
	{
        File directory = new File(local);
        System.out.println("Arquivos em " + local);
        listElements(directory);
	}
	
	private static void listElements(File directory) {
        File [] lista = directory.listFiles();
        for (File f:lista)
        {
            System.out.println(f.getAbsoluteFile());
            if (f.isDirectory())
            {
                listElements(f);
            }
        }
    }	
}
