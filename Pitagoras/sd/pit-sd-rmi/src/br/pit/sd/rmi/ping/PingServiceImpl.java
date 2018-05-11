package br.pit.sd.rmi.ping;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import br.pit.sd.rmi.utils.LogUtils;

public class PingServiceImpl extends UnicastRemoteObject implements PingService {

	private static final long serialVersionUID = 1L;

	public PingServiceImpl() throws RemoteException {
		super();
	}
	
	protected PingServiceImpl(int arg0, RMIClientSocketFactory arg1,
			RMIServerSocketFactory arg2) throws RemoteException {
		super(arg0, arg1, arg2);
	}

	@Override
	public String ping(String msg) throws RemoteException{
		LogUtils.info("Received from client: " + msg);
		return "Pong";
	}

}
