package br.eteg.curso.java.util.datatype;

public class HexadecimalUtil {

	// http://www.rgagnon.com/javadetails/java-0004.html
	
	public static String converterIntEmHexa(int num)
	{
		return Integer.toHexString(num);
	}
	
	public static int converterHexaEmInt(String hexa)
	{
		// "B8DA3"
		return Integer.valueOf(hexa, 16).intValue();		
	}

	public static String converterDoubleEmHexa(double num)
	{
		return Double.toHexString(num);
	}
	
}
