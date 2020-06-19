package br.org.eteg.curso.javaoo.capitulo10.io.serializacao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SerializacaoExemplo {

	private static final String C_WALDIR_THE_TIME_TXT = "c:\\waldir\\theTime.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Pessoa pes = new Pessoa("Waldir", 29, "Rua Bilie Holiday, 36", 
	            new Date(2,9,1976));
	        
		serializar(pes, C_WALDIR_THE_TIME_TXT);
		deserializar(C_WALDIR_THE_TIME_TXT);		
	}

	public static void serializar(Pessoa pes, String arquivo)
	{
		try {
            FileOutputStream out = new FileOutputStream(arquivo);
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject("Today");
            os.writeObject(new Date());
            os.writeObject("Pessoa");
            os.writeObject(pes);
            os.flush();             
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deserializar(String arquivo)
	{
		try {
			Pessoa pes = null;
            FileInputStream in = new FileInputStream(arquivo);
            ObjectInputStream is = new ObjectInputStream(in);
            String today = (String)is.readObject();
            Date date = (Date)is.readObject();             
            String pessoa = (String) is.readObject();
            pes = (Pessoa) is.readObject();
            System.out.println(date);
            System.out.println(pes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
}
