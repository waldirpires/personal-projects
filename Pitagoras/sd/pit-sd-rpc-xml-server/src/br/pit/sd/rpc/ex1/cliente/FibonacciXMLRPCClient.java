package br.pit.sd.rpc.ex1.cliente;

import java.net.*;
import java.io.*;


public class FibonacciXMLRPCClient {
  
  private static String defaultServer 
   = "http://www.elharo.com/fibonacci/XML-RPC";
   
  public static void main(String[] args) {

//    if (args.length == 0) {
//      System.out.println(
//       "Usage: java FibonacciXMLRPCClient index serverURL"); 
//      return;
//    }
    
    String index = "20"; 
    		//args[0];
    
    String server;
    if (args.length <= 1) server = defaultServer;
    else server = args[1];
    
    try {
      URL u = new URL(server);
      URLConnection uc = u.openConnection();
      HttpURLConnection connection = (HttpURLConnection) uc;
      connection.setDoOutput(true);
      connection.setDoInput(true); 
      connection.setRequestMethod("POST");
      OutputStream out = connection.getOutputStream();
      OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");
      
      wout.write("<?xml version=\"1.0\"?>\r\n");  
      wout.write("<methodCall>\r\n"); 
      wout.write(
       "  <methodName>calculateFibonacci</methodName>\r\n");
      wout.write("  <params>\r\n"); 
      wout.write("    <param>\r\n"); 
      wout.write("      <value><int>"); 
      wout.write(index); 
      wout.write("</int></value>\r\n"); 
      wout.write("    </param>\r\n"); 
      wout.write("  </params>\r\n"); 
      wout.write("</methodCall>\r\n"); 
      
      wout.flush();
      out.close();
      
      InputStream in = connection.getInputStream();
      int c;
      while ((c = in.read()) != -1) System.out.write(c);
      System.out.println();
      in.close();
      out.close();
      connection.disconnect();
    }
    catch (IOException e) {
      System.err.println(e); 
      e.printStackTrace();
    }
  
  }  // end main

}