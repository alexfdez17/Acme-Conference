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

<form:form action="${type}/administrator/create.do"
	modelAttribute="activityForm">
	
	<form:hidden path="conference" />
	
	<acme:textbox code="activity.title" path="title"/>
	<acme:textbox code="activity.speakers" path="speakers"/>
	
	<form:label path="startMoment">
		<spring:message code="activity.startMoment" />:
	</form:label>
	<form:input type="date" path="startMoment"
		format="{0,date,MM/dd/yyyy HH:mm}" placeholder="MM/dd/yyyy HH:mm" />
		
	<acme:textbox code="activity.duration" path="duration"/>
	<acme:textbox code="activity.room" path="room"/>
	<acme:textarea code="activity.summary" path="summary"/>
	<acme:textarea code="activity.attachments" path="attachments"/>
	<br />
	
	<jstl:if test="${type == 'tutorial'}">
	<b><spring:message code="activity.section.first" /></b>
	<br />
	<acme:textbox code="activity.section.title" path="sectionTitle"/>
	<acme:textarea code="activity.section.summary" path="sectionSummary"/>
	<acme:textarea code="activity.section.pictures" path="sectionPictures"/>
	</jstl:if>
	
	<jstl:if test="${type == 'presentation'}">
	<b><spring:message code="paper.cameraReadyYes" /></b>
	<br />
	<acme:textbox code="paper.title" path="paperTitle"/>
	<acme:textbox code="paper.authors" path="paperAuthors"/>
	<acme:textarea code="paper.summary" path="paperSummary"/>
	<acme:textbox code="paper.document" path="paperDocument"/>
	</jstl:if>
	
	
	<acme:submit name="save" code="activity.save"/>
	

</form:form>