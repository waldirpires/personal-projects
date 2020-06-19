package br.pit.sd.ws.helloworld;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import br.pit.sd.ws.helloworld.HelloWorld;

public class HelloWorldClient{
	public static void main(String[] args) throws Exception {
       // parâmetros
       String myName = args[0];
       String serviceName = "hello";
       String serviceLocation = "http://localhost:9999";
       String qName = "http://helloworld.ws.sd.pit.br/";
       String qServiceName = "HelloWorldImplService";
       // definição da URL para acesso ao serviço
       URL url = new URL(serviceLocation + "/ws/"+serviceName+"?wsdl");
       // criação do qname para acessar o serviço
       QName qname = new QName(qName, qServiceName);
       // criação do serviço
       Service service = Service.create(url, qname);
       // acesso à interface do serviço
       HelloWorld hello = service.getPort(HelloWorld.class);
       // execução do serviço
       System.out.println(hello.getHelloWorldAsString(myName));
    }
}
