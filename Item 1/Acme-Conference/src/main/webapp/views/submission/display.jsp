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

<b><spring:message code="submission.conference" /></b>:
<jstl:out value="${submission.conference.ticker}" />
<br />

<jstl:choose>
	<jstl:when test="${role == 'none'}">
		<b><spring:message code="submission.author" /></b>:
		<jstl:out value="${submission.author.name}" />
		<br />
	</jstl:when>
</jstl:choose>

<b><spring:message code="submission.ticker" /></b>:
<jstl:out value="${submission.ticker}" />
<br />

<b><spring:message code="submission.status" /></b>:
<jstl:out value="${submission.status}" />
<br />

<b><spring:message code="submission.moment" /></b>:
<jstl:out value="${submission.moment}" />
<br />

<!-- Paper -->

<b><spring:message code="paper.details" /></b>

<b><spring:message code="paper.title" /></b>:
<jstl:out value="${submission.paper.title}" />
<br />

<b><spring:message code="paper.authors" /></b>:
<jstl:out value="${submission.paper.authors}" />
<br />

<b><spring:message code="paper.summary" /></b>:
<jstl:out value="${submission.paper.summary}" />
<br />

<b><spring:message code="paper.document" /></b>:
<jstl:out value="${submission.paper.document}" />
<br />

<!-- Camera-Ready Paper -->

<jstl:if test="${cameraReady == true}">
<b><spring:message code="paper.cameraReadyYes" /></b>

<b><spring:message code="paper.title" /></b>:
<jstl:out value="${submission.paper.title}" />
<br />

<b><spring:message code="paper.authors" /></b>:
<jstl:out value="${submission.paper.authors}" />
<br />

<b><spring:message code="paper.summary" /></b>:
<jstl:out value="${submission.paper.summary}" />
<br />

<b><spring:message code="paper.document" /></b>:
<jstl:out value="${submission.paper.document}" />
<br />
</jstl:if>

<jstl:if test="${cameraReady == false}">
<b><spring:message code="paper.cameraReadyNo" /></b>
</jstl:if>

<jstl:if test="${role == 'author'}">
	<acme:cancel code="submission.cancel" url="submission/author/list.do" />
</jstl:if>
