
int startPortRange = 1
int endPortRange = 65554

def ipAddress = "10.116.47.34"

for (int i = startPortRange; i < endPortRange; i++)
{
	try{
		println "scanning port " + i
		def serverSocket = new Socket(ipAddress, i);
		
		println "Port in use: " + i
		
		serverSocket.close()
		
	}catch (Exception e)
	{
		println "Port not available: " + i
	}
}