import java.util.Hashtable;



public class Calculator {
    public int add(int i1, int i2) {
            return i1 + i2;
    }
    public int subtract(int i1, int i2) {
            return i1 - i2;
    }
    
    public Hashtable sumAndDifference (int x, int y) {
        Hashtable result = new Hashtable();
        result.put("sum", new Integer(x + y));
        result.put("difference", new Integer(x - y));
        return result;
    }    
    
}

