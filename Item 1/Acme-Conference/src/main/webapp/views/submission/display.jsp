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

<acme:out code="submission.conference.title"
	value="${submission.conference.title}" />
<acme:out code="submission.conference.acronym"
	value="${submission.conference.acronym}" />

<jstl:choose>
	<jstl:when test="${role == 'none'}">
		<b><spring:message code="submission.author" /></b>:
		<jstl:out value="${submission.author.name}" />
		<br />
	</jstl:when>
</jstl:choose>

<b><spring:message code="submission.ticker" /></b>
:
<jstl:out value="${submission.ticker}" />
<br />

<b><spring:message code="submission.status" /></b>
:
<jstl:out value="${submission.status}" />
<br />

<b><spring:message code="submission.moment" /></b>
:
<jstl:out value="${submission.moment}" />
<br />
<br />

<!-- Paper -->

<b><spring:message code="paper.details" /></b>
<br />

<b><spring:message code="paper.title" /></b>
:
<jstl:out value="${submission.paper.title}" />
<br />

<b><spring:message code="paper.authors" /></b>
:
<jstl:out value="${submission.paper.authors}" />
<br />

<b><spring:message code="paper.summary" /></b>
:
<jstl:out value="${submission.paper.summary}" />
<br />

<acme:url code="paper.document" url="${submission.paper.document}" />
<br/>
<!-- Camera-Ready Paper -->

<jstl:if test="${cameraReady == true}">
	<b><spring:message code="paper.cameraReadyYes" /></b>
	<br />

	<b><spring:message code="paper.title" /></b>:
<jstl:out value="${submission.cameraReadyPaper.title}" />
	<br />

	<b><spring:message code="paper.authors" /></b>:
<jstl:out value="${submission.cameraReadyPaper.authors}" />
	<br />

	<b><spring:message code="paper.summary" /></b>:
<jstl:out value="${submission.cameraReadyPaper.summary}" />
	<br />

	<acme:url code="paper.document"
		url="${submission.cameraReadyPaper.document}" />
</jstl:if>

<jstl:if test="${cameraReady == false}">
	<b><spring:message code="paper.cameraReadyNo" /></b>
</jstl:if>
<br />

<security:authorize access="hasRole('AUTHOR')">
	<acme:cancel code="submission.back" url="submission/author/list.do" />
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
	<acme:cancel code="submission.back" url="submission/administrator/list.do" />
</security:authorize>