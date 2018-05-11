<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'siteGroupCopy.label', default: 'SiteGroupCopy')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<script type="text/javascript">

			function confirmSiteMsoCopy()
			{
				var response = false;
				if (confirm('${message(code: 'default.button.copy.confirm.message', default: 'Are you sure?')}'))
				{
					response = true;
					document.getElementById('status').innerHTML = '<br/>- Site MSO Copy in progress . . .'
				}
				return response;
			}
		</script>
	</head>
	<body>
		<h1 style="font-size: xx-large;">CMS - MSO Export Copy Tool</h1>
		<a href="#create-siteGroupCopy" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index">Site MSO Copy List</g:link></li>
			</ul>
		</div>
		<div id="create-siteGroupCopy" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${siteGroupCopyInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${siteGroupCopyInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:siteGroupCopyInstance, action:'executeCopy']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="copy" class="save" value="Copy Site MSOs" 
					onclick="return confirmSiteMsoCopy();"/>
					<g:link class="list" action="index">Cancel</g:link>					
				</fieldset>
			</g:form>
		</div>
		<div id="status">			
		</div>
	</body>
</html>
