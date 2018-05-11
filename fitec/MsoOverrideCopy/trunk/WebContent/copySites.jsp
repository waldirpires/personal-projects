<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ericsson.msoimport.utils.StringUtils"%>
<%@page import="com.ericsson.msoimport.Main"%>
<%@page import="com.ericsson.msoimport.ReadSites"%>
<%@page import="com.ericsson.msoimport.domain.Site"%>
<%@page import="com.ericsson.msoimport.utils.CopyResultStats"%>
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
				String srcSiteId = request.getParameter("srcSiteId");
				String destSiteId = request.getParameter("destSiteId");
				List<String> msgs = new ArrayList<String>();

				boolean containsError = false;

				if (StringUtils.isEmpty(srcSiteId)) {
					msgs.add(String
							.format("<font size=\"3\" color=\"red\">ERROR: Invalid SRC Site Group ID!</font>"));
					containsError = true;
				} else if (StringUtils.isEmpty(destSiteId)) {
					msgs.add(String
							.format("<font size=\"3\" color=\"red\">ERROR: Invalid DEST Site Group ID!</font>"));
					containsError = true;
				} else if (srcSiteId.equals(destSiteId)) {
					msgs.add(String
							.format("<font size=\"3\" color=\"red\">ERROR: Cannot copy from SAME Site Group ID. Please change SOURCE or DESTINATION Site Group.</font>"));
					containsError = true;
				}

				%>
				</b></td>
			</tr>
			<%
			if (containsError) {
				request.setAttribute("msgs", msgs);
				request.setAttribute("color", "red");
			}
			%>
			<tr><% for (String s: msgs){
					%><td><%=msgs%></td><%
				}%>			
			</tr>			
			<%
			if (!containsError) {
				try {
					ReadSites rs = new ReadSites();
					Map<String, Site> sitesMap = rs.getSitesMap();
					String msg = String
							.format("Copying <br/>FROM Site Group ID <b>[%s]: %s</b> <br/>TO Site Group ID <b>[%s]: %s</b> . . .",
									srcSiteId, sitesMap.get(srcSiteId), destSiteId, sitesMap.get(destSiteId));
					msgs.add(msg);
					Main main = new Main();
					CopyResultStats stats = main.msoCopyToSite(srcSiteId, destSiteId);
					request.setAttribute("msgs", msgs);
					request.setAttribute("color", "black");
					request.setAttribute("stats", stats);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			%>
			<tr>
				<td><a href="index.jsp"
					style="font-size: large; text-align: center;">Back</a></td>
			</tr>
		</table>
	</div>
	</div>
</BODY>