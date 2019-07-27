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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="conference">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<fieldset>
		<legend><spring:message code="conference.basic.info"/></legend>
		<acme:textbox code="conference.title" path="title" />
		<acme:textbox code="conference.acronym" path="acronym" />
		<acme:textarea code="conference.summary" path="summary" />
		<acme:textbox code="conference.venue" path="venue" />
		<acme:textbox code="conference.fee" path="fee" />
		<acme:select items="${categories}" itemLabel="title"
			code="conference.category" path="category" />
	</fieldset>

	<fieldset>
		<legend><spring:message code="conference.dates.info"/></legend>
		<acme:textbox code="conference.submissionDeadline"
			path="submissionDeadline" />
		<acme:textbox code="conference.notificationDeadline"
			path="notificationDeadline" />
		<acme:textbox code="conference.cameraReadyDeadline"
			path="cameraReadyDeadline" />
		<acme:textbox code="conference.startDate" path="startDate" />
		<acme:textbox code="conference.endDate" path="endDate" />
	</fieldset>
	
	

	<acme:submit name="save" code="conference.save" />
	<acme:cancel url="conference/administrator/list.do?keyword=all" code="conference.cancel" />

</form:form>