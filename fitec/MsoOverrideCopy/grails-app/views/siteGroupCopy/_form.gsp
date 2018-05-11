<%@ page import="com.ericsson.cms.espn.domain.SiteGroupCopy" %>



<div class="fieldcontain ${hasErrorsSrcSiteId} ">
	<label for="srcSiteId">
		<g:message code="siteGroupCopy.srcSiteId.label" default="Source Site Group" />
		
	</label>
	<g:select name="srcSiteId" from="${sitesList}"  optionKey="ID" value="${siteGroupCopyInstance?.srcSiteId}" class="many-to-many"/>	
</div>
<div class="fieldcontain ${hasErrorsDestSiteId} ">
	<label for="destSiteId">
		<g:message code="siteGroupCopy.destSiteId.label" default="Destination Site Group" />
		
	</label>
	<g:select name="destSiteId" from="${sitesList}"  optionKey="ID" value="${siteGroupCopyInstance?.destSiteId}" class="many-to-many"/>	
</div>

