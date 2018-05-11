package com.ericsson.msoimport.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import com.ericsson.msoimport.utils.HttpLogAppender;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/Log")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LogServlet.class);	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		File f = new File("/var/log/mso-tool/mso-override-copy.log");
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
		
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>MsoOverrideCopy Tool Console</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=\"white\">");
	    out.println("<h1>MsoOverrideCopy Tool Console</h1>");
	    BufferedReader br = new BufferedReader(new FileReader(f));
	    String line = null;
	    out.println("<table>");
	    while ((line = br.readLine()) != null)
	    {
	    	String style = line.toLowerCase().contains("error")?" style=\"color: red\"":"";
	    	out.println("<tr><td"+style+">" + line + "</td></tr>");	    	
	    }
	    br.close();
	    out.println("</table>");
	    out.println("</body>");
	    out.println("</html>");

	}

	private void configureLogForCurrentRequest(ServletOutputStream outstream) {

        HttpLogAppender appender = (HttpLogAppender) LOG.getAppender("HA");
        while (appender == null) {
            Category parent = LOG.getParent();
            if (parent == null) {
                break; //This ideally shouldn't happen. Navigated all the way to root logger and still did not find appender !!..something wrong with log4j configuration setup
            }
            appender = (HttpLogAppender) parent.getAppender("HA");

        }
        appender.setCurrentHttpStream(outstream);
    }	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
