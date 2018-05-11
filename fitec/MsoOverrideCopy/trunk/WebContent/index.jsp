<%@page import="java.util.List"%>
<%@page import="com.ericsson.msoimport.domain.Site"%>
<%@page import="com.ericsson.msoimport.ReadSites"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MSO Overrides Import</title>
</head>
<script src="js/jquery-2.1.0.min.js"></script>
<script language="JavaScript" type="text/javascript">
	function selfSubmit() {
		document.frmIndex.action = "/index.jsp";
		document.frmIndex.submit();
	}
	function checkProgress(id) {
	    $.getJSON('progressServlet?processId='+id, function(progress) {
	        $('#progress').text(progress + "%");
	        $('#progress .bar').width(progress);

	        if (parseInt(progress) < 100) {
	            setTimeout(checkProgress, 1000); // Checks again after one second.
	        }
	    });
	}	
</script>

<BODY style="margin-left: 100px;font-size: large;">
	<div
		style="height: 600px; width: 800px; background-color: Beige; left: 200px;">
		<div style="background-color: AliceBlue;">
			<a href="<%=request.getContextPath()%>"> <img
				src="<%=request.getContextPath()%>/images/logo.gif" />
			</a>
		</div>
<h1><a href="index.jsp">MSO Override Copy Tool</a></h1>
		<div
			style="border-style: double; border-color: Black; margin: 50px; border-width: thin;">
			<table>
			<tr><% List<String> msgs = (List)request.getAttribute("msgs");
			if (msgs != null)
			for (String s: msgs){
					%><td><%=s%></td><%
				}%>			
			</tr>
			
			</table>
			<form method="post" action="Copy"
				style="border-style: solid; border-color: AliceBlue; border-width: thin; padding: 50px;">

				<div>
					<h2>Instructions:</h2>
					<ol>
						<li>Select the options below: </li>
						<ol>
						<li style="width: 500px; color: <%=request.getAttribute("color")%>">
						<label style="min-width: 200px">From GROUP:</label>
						<Select name="srcSiteId"
							id="srcSiteId">
								<%
									ReadSites readSites = new ReadSites();
									List<Site> sitesList = readSites.getSites();
									for (Site site : sitesList) {
										out.println("<option value=\"" + site.Id + "\">" + site.toString()
												+ "</option>");
									}
								%>
						</select>
						<li style="width: 500px; color: <%=request.getAttribute("color")%>">
						<label style="min-width: 200px">To GROUP:</label>
						<Select name="destSiteId"
							id="destSiteId">
								<%
									for (Site site : sitesList) {
										out.println("<option value=\"" + site.Id + "\">" + site.toString()
												+ "</option>");
									}
								%>
						</select>
						</li></ol>
						<%if (sitesList.isEmpty()) {%>
							<li> List of Groups is empty.
							</li>
						<%} %>
						<li>Click here <input type="submit" value="Copy" onclick="checkProgress(1);">
							and wait some minutes for completion (***);
						</li>
						<li>Check log file /opt/tandbergtv/cms/log/msooverridecopy.log.
						</li>
					</ol>
				</div>
			</form>
		</div>
	</div>
	Progress:
	<div id="progress"></div>
</BODY>
</html>