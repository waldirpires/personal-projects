<%@page import="com.ericsson.msoimport.utils.CopyResultStats"%>
<%@page import="com.ericsson.msoimport.Main"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*"%>
<BODY style="margin-left: 100px">
	<div
		style="height: 800px; width: 800px; background-color: Beige; left: 200px;">
		<div style="background-color: AliceBlue;">
			<a href="<%=request.getContextPath()%>"> <img
				src="<%=request.getContextPath()%>/images/logo.gif" />
			</a>
		</div>
<h1><a href="index.jsp">MSO Override Copy Tool</a></h1>		
	<div
		style="border-style: double; border-color: Black; margin: 50px; border-width: thin;">
		<table style="margin: 10px; padding: 10px;">
			<tr><% List<String> msgs = (List<String>)request.getAttribute("msgs");
			if (msgs != null)
			for (String s: msgs){
					%><td><%=s%></td><%
				}%>			
			</tr></table>
			<h2>Results:</h2>

			<table style="margin: 10px; padding: 10px;">			
			<tr>
				<%CopyResultStats stats = (CopyResultStats) request.getAttribute("stats"); 
				if (stats != null)
				{
					%>
					<td>Number of MSOs in Source Site Group: <%= stats.getNumMsosFromSite()%>
					</td></tr><tr>
					<td>Number of MSOs in Destination Site Group: <%= stats.getNumMsosToSite()%>
					</td></tr><tr>
					<td>Number of MSOs copied: <%= stats.getNumMsosCopied()%>
					</td></tr><tr>
					<td>Number of MSO Overrides copied: <%= stats.getNumMsoOverrideCopied()%>
					</td></tr><tr>
					<td>Number of MSOs deleted in destination Site Group: <%= stats.getNumMsosDeletedFromDest()%>
					</td></tr><tr>
					<td>Number of MSOs in Destination Site Group AFTER Copy: <%= stats.getNumMsosToSiteAfterCopy()%>
					</td></tr><tr>
					<td>Number of errors: <%= stats.getNumErrors()%>
					</td>
					<%
				} else {
					%><td>No information is available.</td><%
				}
					%>				
			</tr>

			<tr>
				<td><a href="index.jsp"
					style="font-size: large; text-align: center;">Back</a></td>
			</tr>
		</table>
	</div>
	</div>
</BODY>