<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<b><spring:message code="activity.title" /></b>:
<jstl:out value="${activity.title}" />
<br />

<b><spring:message code="activity.speakers" /></b>:
<jstl:out value="${activity.speakers}" />
<br />

<b><spring:message code="activity.startMoment" /></b>:
<jstl:out value="${activity.startMoment}" />
<br />

<b><spring:message code="activity.duration" /></b>:
<jstl:out value="${activity.duration}" />
<br />

<b><spring:message code="activity.room" /></b>:
<jstl:out value="${activity.room}" />
<br />

<b><spring:message code="activity.summary" /></b>:
<jstl:out value="${activity.summary}" />
<br />

<b><spring:message code="activity.attachments" /></b>:
<jstl:out value="${activity.attachments}" />
<br />
<br />

<jstl:if test="${type == 'tutorial'}">

<b><spring:message code="activity.sections" /></b>
<br />

<display:table pagesize="5" class="displaytag" name="sections"
	requestURI="activity/administrator/display.do?${activity.id}" id="row">

	<!-- Attributes -->
	
	<spring:message code="activity.section.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="false" />
		
	<spring:message code="activity.section.summary" var="summaryHeader" />
	<display:column property="summary" title="${summaryHeader}"
		sortable="false" />
	
	<spring:message code="activity.section.pictures" var="picturesHeader" />
	<display:column property="pictures" title="${picturesHeader}"
		sortable="false" />
		
	<!-- Actions -->
		
	<display:column>
		<a href="section/administrator/edit.do?sectionId=${row.id}"> <spring:message
				code="activity.edit" />
		</a>
	</display:column>
	
</display:table>
<br />
<br />
<a href="section/administrator/create.do?tutorialId=${activity.id}"> <spring:message
				code="activity.addSection" />
</a>
<a href="tutorial/administrator/edit.do?tutorialId=${activity.id}"> <spring:message
				code="activity.edit" />
</a>
</jstl:if>

<jstl:if test="${type == 'presentation'}">

<b><spring:message code="paper.cameraReadyYes" /></b>
<br />

<b><spring:message code="paper.title" /></b>:
<jstl:out value="${activity.cameraReadypaper.title}" />
<br />

<b><spring:message code="paper.authors" /></b>:
<jstl:out value="${activity.cameraReadypaper.authors}" />
<br />

<b><spring:message code="paper.summary" /></b>:
<jstl:out value="${activity.cameraReadyPaper.summary}" />
<br />

<b><spring:message code="paper.document" /></b>:
<jstl:out value="${activity.cameraReadyPaper.document}" />
<br />
<br />
<a href="presentation/administrator/edit.do?presentationId=${activity.id}"> <spring:message
				code="activity.edit" />
</a>
</jstl:if>

<jstl:if test="${type == 'panel'}">
<a href="panel/administrator/edit.do?panelId=${activity.id}"> <spring:message
				code="activity.edit" />
</a>
</jstl:if>



