package br.org.eteg.curso.javaoo.capitulo05;

public class BufferBuilderExemplo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = "Eu adoro aprender Java aos sábados, sem lanche . . .";
		StringBuffer buffer = new StringBuffer(s);
		StringBuilder builder = new StringBuilder(s);
		
		s = s + " adeus";
		System.out.println(buffer.toString());
		System.out.println(builder.toString());
		
		//		charAt() e setCharAt()
		char c = buffer.charAt(2);
		System.out.println(c);

		buffer.setCharAt(2, '-');
		System.out.println(buffer.toString());
		
		//		append()
		buffer.append(" boa noite");
		System.out.println(buffer.toString());

		//		insert()
		buffer.insert(10, " hello");
		System.out.println(buffer.toString());

		//		toString()
		
		//		delete() e deleteCharAt()
		String f = buffer.substring(5, 10);
		buffer.delete(5, 10);
		// verificando se o string ainda existe
		int pos = buffer.indexOf(f);
		System.out.println(buffer.toString());
		
		char d = buffer.charAt(15);
		buffer.deleteCharAt(15);
		System.out.println(buffer.toString());
		
		//		length() e setLength()
		System.out.println(buffer.length());
		buffer.setLength(50);
		System.out.println(buffer.length());
		
		//		substring()
		
	}

}
