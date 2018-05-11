package br.pit.sd.rmi.ping;

import br.pit.sd.rmi.utils.LogUtils;
import br.pit.sd.rmi.utils.NetUtils;
import br.pit.sd.rmi.utils.RmiUtils;

public class PingServer {

	public PingServer(String ip) {
		try{
			NetUtils.listNetworkInterfaces();
			NetUtils.getIpAddress();
			LogUtils.info("Testing IP and port");
			int port = Integer.parseInt(PingService.RMI_SERVER_PORT);
			NetUtils.isAvailable(ip, port);
			RmiUtils.startRegistry(port);
			LogUtils.info("Instantiating service PING . . .");
			PingService p = new PingServiceImpl();
			RmiUtils.registerService(ip, port, PingService.RMI_PING_SERVICE, p);
			RmiUtils.listRemoteServices(ip, port);
		}catch(Exception e){
			LogUtils.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String ip = null;
		if (args.length == 1){
			ip = args[0];
		} else 
		{
			ip = NetUtils.getIpAddress();
		}
		new PingServer(ip);
	}
	
}
