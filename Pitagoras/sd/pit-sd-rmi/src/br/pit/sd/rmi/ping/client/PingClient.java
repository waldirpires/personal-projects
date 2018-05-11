package br.pit.sd.rmi.ping.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.pit.sd.rmi.ping.PingService;
import br.pit.sd.rmi.utils.LogUtils;
import br.pit.sd.rmi.utils.NetUtils;

public class PingClient {

	private String ip;
	private String port;
	
	public PingClient(String ip, String port){
		this.ip = ip;
		this.port = port;
	}
	
	public void listServices() throws RemoteException, MalformedURLException{
		String msg = "rmi://" + this.ip + ":" + this.port;
		LogUtils.info("Listing binded services: " + msg);
		String[] list = Naming.list(msg);
		for (String s: list){
			LogUtils.info(s);
		}		
	}

	public void pingServer(){
		try {
			String msg = "rmi://" + this.ip + ":" + this.port + PingService.RMI_PING_SERVICE;
			LogUtils.info("Looking for PING service: " + msg);
			PingService p = (PingService) Naming.lookup(msg);
			LogUtils.info("Calling PING service");
			String s = p.ping("Hello from client: PING");
			LogUtils.info("Response from Server: " + s);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		String ip = null;
		String port = null;
		if (args.length == 2){
			ip = args[0];
			port = args[1];
		} else {
			ip = PingService.RMI_SERVER_IP;
			port = PingService.RMI_SERVER_PORT;			
		}
		PingClient client = new PingClient(ip, port);
 	    NetUtils.isAvailable(ip, Integer.parseInt(port));
		client.listServices();
		client.pingServer();
	}
}
