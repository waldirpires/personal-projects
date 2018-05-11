package br.pit.sd.rmi.ping;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PingService extends Remote{

	public static final String RMI_PROTOCOL = "rmi://";
	
	public static final String RMI_SERVER_IP = "10.31.6.57";
	
	public static final String RMI_SERVER_PORT = "1099";
	
	public static final String RMI_SERVER_URL = RMI_PROTOCOL + RMI_SERVER_IP + ":" + RMI_SERVER_PORT; 
	
	public static final String RMI_PING_SERVICE = "/PingService";
	
	public static final String RMI_PING_SERVICE_URL = RMI_PROTOCOL + RMI_SERVER_IP + ":" + RMI_SERVER_PORT + RMI_PING_SERVICE; 
	
	public String ping(String msg) throws RemoteException;
	
}
