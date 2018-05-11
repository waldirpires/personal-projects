package br.pit.sd.rpc.ex1.cliente;

  import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.util.ClientFactory;

import br.pit.sd.rpc.ex1.servico.Calculadora;

  public class RpcClient {
      public static void main(String[] args) throws Exception {
          // create configuration
          XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
          config.setServerURL(new URL("http://127.0.0.1:8080/xmlrpc"));
          config.setEnabledForExtensions(true);  
          config.setConnectionTimeout(60 * 1000);
          config.setReplyTimeout(60 * 1000);

          XmlRpcClient client = new XmlRpcClient();
        
          // use Commons HttpClient as transport
          client.setTransportFactory(
              new XmlRpcCommonsTransportFactory(client));
          // set configuration
          client.setConfig(config);

          // make the a regular call
          Object[] params = new Object[]
              { new Integer(2), new Integer(3) };
          Integer result = (Integer) client.execute("Calculadora.add", params);
          System.out.println("2 + 3 = " + result);
        
          // make a call using dynamic proxy
          ClientFactory factory = new ClientFactory(client);
          Calculadora adder = (Calculadora) factory.newInstance(Calculadora.class);
          int sum = adder.somar(2, 4);
          System.out.println("2 + 4 = " + sum);
      }
  }