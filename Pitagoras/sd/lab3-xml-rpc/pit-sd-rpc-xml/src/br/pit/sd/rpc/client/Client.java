package br.pit.sd.rpc.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class Client {
	private static final String SERVER_URL = "http://localhost:8080/RPC2";

	public static void main(String[] args) throws 
	  MalformedURLException, XmlRpcException, IOException {
		System.out.println("Chamando PING remoto no servidor:");
		chamarPingRemoto();
	}

	private static void chamarPingRemoto() throws 
	  MalformedURLException, XmlRpcException,
			IOException {
		Vector params = new Vector();
		params.addElement("Ping");
		Object result = invoke(SERVER_URL, params, "ping.responder");
		System.out.println("Do Servidor: " + result);
	}

	static Object invoke(String serverUrl, Vector params, String operation) {
		XmlRpcClient xmlrpc;
		Object result = null;
		try {
			xmlrpc = new XmlRpcClient(serverUrl);
			result = xmlrpc.execute(operation, params);
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
