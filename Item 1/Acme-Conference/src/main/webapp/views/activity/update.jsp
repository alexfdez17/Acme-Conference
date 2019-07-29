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

<form:form action="${type}/administrator/edit.do"
	modelAttribute="activity">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="comments" />
	<jstl:if test="${type == 'tutorial'}">
	<form:hidden path="sections" />
	</jstl:if>
	
	<acme:textbox code="activity.title" path="title"/>
	<acme:textbox code="activity.speakers" path="speakers"/>
	<acme:textbox code="activity.startMoment" path="startMoment"/>
	<acme:textbox code="activity.duration" path="duration"/>
	<acme:textbox code="activity.room" path="room"/>
	<acme:textarea code="activity.summary" path="summary"/>
	<acme:textarea code="activity.attachments" path="attachments"/>
	<br />
	
	<jstl:if test="${type == 'presentation'}">
	<b><spring:message code="paper.cameraReadyYes" /></b>
	<br />
	<acme:textbox code="paper.title" path="cameraReadyPaper.title"/>
	<acme:textbox code="paper.authors" path="cameraReadyPaper.authors"/>
	<acme:textbox code="paper.summary" path="cameraReadyPaper.summary"/>
	<acme:textbox code="paper.document" path="cameraReadyPaper.document"/>
	</jstl:if>
	
	
	<acme:submit name="save" code="activity.save"/>
	<acme:submit name="delete" code="activity.delete" />
	<acme:cancel url="activity/administrator/display.do?activityId=${activity.id}" code="activity.cancel"/>
	
</form:form>
