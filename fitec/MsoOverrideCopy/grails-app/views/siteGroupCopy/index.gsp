
<%@ page import="com.ericsson.cms.espn.domain.SiteGroupCopy" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'siteGroupCopy.label', default: 'Site Groups')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<div>
		<h1 style="font-size: xx-large;">CMS - MSO Export Copy Tool</h1>
		</div>
		<a href="#list-siteGroupCopy" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-siteGroups" class="content scaffold-list" role="main">
			<h1><g:message code="siteGroupCopy.label"/>&nbsp;List</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Number of MSOs</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${sitesList}" status="i" var="siteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: siteInstance, field: "id")}</td>
						<td>${fieldValue(bean: siteInstance, field: "siteName")}</td>
						<td>${fieldValue(bean: siteInstance, field: "description")}</td>
						<td>${fieldValue(bean: siteInstance, field: "numberOfMsos")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${siteGroupsInstanceCount ?: 0}" />
			</div>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create">Create Site Group Copy</g:link></li>
			</ul>
		</div>
		</div>
	</body>
</html>
