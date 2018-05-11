package br.pit.sd.rmi.utils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RmiUtils {

	public static void startRegistry(int port) throws RemoteException {
		LogUtils.info("Starting RMI registry at port " + port);
		java.rmi.registry.LocateRegistry.createRegistry(port);
	}

	public static void registerService(String ip, int port, String serviceUrl, Remote r)
			throws RemoteException, MalformedURLException {
		String rmiPath = "rmi://"+ip+":"+port+serviceUrl;
		LogUtils.info(String.format("Registering %s for RMI",
				rmiPath));
		Naming.rebind(rmiPath, r);
		LogUtils.info("Service registered!");
	}

	public static void listRemoteServices(String ip, int port) throws RemoteException,
			MalformedURLException {
		String rmiPath = "rmi://"+ip+":"+port;
		LogUtils.info("Listing binded services: " + rmiPath);
		String[] list = Naming.list(rmiPath);
		for (String s : list) {
			LogUtils.info(s);
		}
	}

}
