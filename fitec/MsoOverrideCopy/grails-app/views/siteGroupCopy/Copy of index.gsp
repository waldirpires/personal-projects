
<%@ page import="com.ericsson.cms.espn.domain.SiteGroupCopy" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'siteGroupCopy.label', default: 'SiteGroupCopy')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-siteGroupCopy" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-siteGroupCopy" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="destSiteId" title="${message(code: 'siteGroupCopy.destSiteId.label', default: 'Dest Site Id')}" />
					
						<g:sortableColumn property="srsSiteId" title="${message(code: 'siteGroupCopy.srsSiteId.label', default: 'Srs Site Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${siteGroupCopyInstanceList}" status="i" var="siteGroupCopyInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${siteGroupCopyInstance.id}">${fieldValue(bean: siteGroupCopyInstance, field: "destSiteId")}</g:link></td>
					
						<td>${fieldValue(bean: siteGroupCopyInstance, field: "srsSiteId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${siteGroupCopyInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
