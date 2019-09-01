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




<b><spring:message code="actor.name" /></b>
<jstl:out value="${actor.name}" />
<br />

<b><spring:message code="actor.middleName" /></b>
<jstl:out value="${actor.middleName}" />
<br />

<b><spring:message code="actor.surname" /></b>
<jstl:out value="${actor.surname}" />
<br />

<b><spring:message code="actor.photo" /></b>
<jstl:out value="${actor.photo}" />
<br />

<b><spring:message code="actor.email" /></b>
<jstl:out value="${actor.email}" />
<br />

<b><spring:message code="actor.phone" /></b>
<jstl:out value="${actor.phone}" />
<br />

<b><spring:message code="actor.address" /></b>
<jstl:out value="${actor.address}" />
<br />


<security:authorize access="hasRole('AUTHOR')">
	<b><spring:message code="actor.author.score" /></b>
<jstl:out value="${actor.score}" />
<br />
</security:authorize>

<security:authorize access="hasRole('REVIEWER')">
	<b><spring:message code="actor.reviewer.keywords" /></b>
<jstl:out value="${actor.keywords}" />
<br />
</security:authorize>

