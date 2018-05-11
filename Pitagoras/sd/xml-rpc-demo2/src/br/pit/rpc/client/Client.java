package br.pit.rpc.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class Client {

	private static final String SERVER_URL = "http://localhost:8080/RPC2";

	static void sumAndDifference() throws XmlRpcException, IOException{
        // Build our parameter list.
        Vector params = new Vector();
        params.addElement(new Integer(5));
        params.addElement(new Integer(3));

        Hashtable result = (Hashtable) invoke(SERVER_URL, params, "calc.sumAndDifference");
        
        // Call the server, and get our result.
        int sum = ((Integer) result.get("sum")).intValue();
        int difference = ((Integer) result.get("difference")).intValue();

        // Print out our result.
        System.out.println("Sum: " + Integer.toString(sum) +
                           ", Difference: " +
                           Integer.toString(difference));		
	}
	
	public static void main(String[] args) throws XmlRpcException, IOException {
		add();
		sumAndDifference();
	}

	private static void add() throws MalformedURLException, XmlRpcException,
			IOException {
		Vector params = new Vector ();
		params.addElement (2);
		params.addElement (3);
		Object result = invoke(SERVER_URL, params, "calc.add");
		System.out.println(result);
	}
	
	static Object invoke(String serverUrl, Vector params, String operation){
		XmlRpcClient xmlrpc;
		Object result = null;
		try {
			xmlrpc = new XmlRpcClient (serverUrl);
			result = xmlrpc.execute (operation, params);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
