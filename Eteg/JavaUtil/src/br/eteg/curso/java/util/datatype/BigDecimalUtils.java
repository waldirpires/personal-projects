package br.eteg.curso.java.util.datatype;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalUtils
{
  private static final BigDecimal MARGEM_ERRO = BigDecimal.valueOf(0.02);
  
  /**
   * Default Rouding mode = default bank's rouding mode. So it is!
   */
  private static final RoundingMode DEFAULT_ROUNDING_MODE = 
    RoundingMode.HALF_EVEN;
  
  /**
   * N�mero de d�gitos ilimitado.
   * TODO: Verificar se ser� necess�rio lidar com o n�mero m�ximo de d�gitos.
   */
  private static final int DEFAULT_PRECISION = 0;
  
  private static final int DEFAULT_SCALE = 2;
  
  /**
   * 
   * @param a a
   * @param b b
   * @return BigDecimal
   */
  public static BigDecimal adicionar(BigDecimal a, BigDecimal b)
  {
    return adicionar(a, b, new MathContext(DEFAULT_PRECISION, 
      DEFAULT_ROUNDING_MODE));
  }
  
  /**
   * 
   * @param a a
   * @param b b
   * @param mathContext mathContext
   * @return BigDecimal
   */
  public static BigDecimal adicionar(BigDecimal a, BigDecimal b, 
    MathContext mathContext)
  {
    int scale = getScale(a, b);
    return a.add(b, mathContext).setScale(scale, DEFAULT_ROUNDING_MODE);
  }
  
  /**
   * M�todo de compara��o entre dois valores monet�rios segundo a Restri��o
   * ao desenho Compara��o entre valores monet�rios. 
   * @param valA valA
   * @param valB valB
   * @return int
   */
  public static int comparar(BigDecimal valA, BigDecimal valB) 
  {
    BigDecimal dif = subtrair(valA, valB, 
      new MathContext(DEFAULT_PRECISION, DEFAULT_ROUNDING_MODE));
    dif = dif.setScale(MARGEM_ERRO.scale(), DEFAULT_ROUNDING_MODE);
    if (dif.abs().equals(MARGEM_ERRO))
    {
      return 0;
    }
    else
    {
      return valA.compareTo(valB);
    }
  }
  
  /**
   * Esse m�todo compara dois BigDecimal, desconsiderando a escala.
   * Assim, 2.00 � igual a 2.0, diferentemente do m�todo equals padr�o
   * do BigDecimal.
   * 
   * @param valA valA
   * @param valB valB
   * @return true se os dois valores s�o num�ricamente iguais, ou false
   * caso contr�rio.
   */
  public static boolean equals(BigDecimal valA, BigDecimal valB)
  {
    return valA.compareTo(valB) == 0;
  }
  
  /**
   * M�todo de compara��o de BigDecimal desconsiderando a Restri��o ao
   * desenho Compara��o entre valores monet�rios.
   * @param valA valA
   * @param valB valB
   * @return boolean
   */
  public static int compareTo(BigDecimal valA, BigDecimal valB)
  {
    return valA.compareTo(valB);
  }
  
  /**
   * M�todo de compara��o de BigDecimal desconsiderando a Restri��o ao
   * desenho Compara��o entre valores monet�rios.
   * @param valA valA
   * @param valB valB
   * @param margemErro margem de erro a ser aplicada
   * @return boolean
   */
  public static int comparar(BigDecimal valA, BigDecimal valB, 
    BigDecimal margemErro)
  {
    BigDecimal dif = subtrair(valA, valB, 
      new MathContext(DEFAULT_PRECISION, DEFAULT_ROUNDING_MODE));
    dif = dif.setScale(margemErro.scale(), DEFAULT_ROUNDING_MODE);
    if (dif.abs().compareTo(margemErro) < 0)
    {
      return 0;
    }
    else
    {
      return valA.compareTo(valB);
    }
  }
  
  /**
   * 
   * @param a a
   * @param b b
   * @return BigDecimal
   */
  public static BigDecimal dividir(BigDecimal a, BigDecimal b)
  {
    return dividir(a, b, new MathContext(DEFAULT_PRECISION, 
      DEFAULT_ROUNDING_MODE));
  }
  
  /**
   * 
   * @param a a
   * @param b b
   * @param mathContext mathContext
   * @return BigDecimal
   */
  public static BigDecimal dividir(BigDecimal a, BigDecimal b, 
    MathContext mathContext)
  {
    int scale = getScale(a, b);
    return a.divide(b, mathContext.getRoundingMode()).setScale(
      scale, DEFAULT_ROUNDING_MODE);
  }
  
  /**
   * 
   * @param a a
   * @param b b
   * @return BigDecimal
   */
  public static BigDecimal multiplicar(BigDecimal a, BigDecimal b)
  {
    return multiplicar(a, b, new MathContext(DEFAULT_PRECISION, 
      DEFAULT_ROUNDING_MODE));
  }
  
  /**
   * 
   * @param a a
   * @param b b
   * @param mathContext mathContext
   * @return BigDecimal
   */
  public static BigDecimal multiplicar(BigDecimal a, BigDecimal b, 
    MathContext mathContext)
  {
    int scale = getScale(a, b);
    return a.multiply(b, mathContext).setScale(scale, DEFAULT_ROUNDING_MODE);
  }
  
  /**
   * 
   * @param a a
   * @param b b
   * @return BigDecimal
   */
  public static BigDecimal subtrair(BigDecimal a, BigDecimal b)
  {
    return subtrair(a, b, new MathContext(DEFAULT_PRECISION, 
      DEFAULT_ROUNDING_MODE));
  }
  
  /**
   * 
   * @param a a
   * @param b b
   * @param mathContext mathContext
   * @return BigDecimal
   */
  public static BigDecimal subtrair(BigDecimal a, BigDecimal b, 
    MathContext mathContext)
  {
    int scale = getScale(a, b);
    return a.add(b.negate(), mathContext).setScale(scale, DEFAULT_ROUNDING_MODE);
  }
  
  /**
   * M�todo respons�vel por obter a escala usada em opera��es envolvendo dois
   * operandos. A escala retornada ser� a menor dentre as escalas dos
   * operandos. Isso assim ficou definido pois campos de valores monet�rios
   * no Portal possuem escala menor que outros tipos de campos, como campos
   * de quantidade e cota��o de moeda estrangeira.
   * @param a a
   * @param b b
   * @return int
   */
  private static int getScale(BigDecimal a, BigDecimal b)
  {
    int scaleA = a.scale() < DEFAULT_SCALE ? DEFAULT_SCALE : a.scale();
    int scaleB = b.scale() < DEFAULT_SCALE ? DEFAULT_SCALE : b.scale();
    return scaleA < scaleB ? 
      scaleA : scaleB;
  }
}
