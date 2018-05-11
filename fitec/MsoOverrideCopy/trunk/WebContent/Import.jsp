<%@page import="com.ericsson.msoimport.Main"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*"%>
<BODY style="margin-left: 100px">
	<div
		style="height: 600px; width: 800px; background-color: Beige; left: 200px;">
		<div style="background-color: AliceBlue;">
			<a href="<%=request.getContextPath()%>"> <img
				src="<%=request.getContextPath()%>/images/logo.gif" />
			</a>
		</div>
	<div
		style="border-style: double; border-color: Black; margin: 50px; border-width: thin;">
		<table style="margin: 50px; padding: 50px;">
			<tr>

				<td><b> <%
 	String parameter = request.getParameter("file");
 	String siteId = request.getParameter("selSites");
 	out.println("Done! Imported " + parameter + " into Site Id="
 			+ siteId);
 	Main main = new Main();
 	main.msoImportToSite(parameter, Integer.valueOf(siteId));
 %>
				</b></td>
			</tr>
			<tr>
				<td><a href="index.jsp"
					style="font-size: large; text-align: center;">Back</a></td>
			</tr>
		</table>
	</div>
	</div>
</BODY>