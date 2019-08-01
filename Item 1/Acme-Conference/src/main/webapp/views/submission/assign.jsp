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

<form:form action="${requestURI}" modelAttribute="submission">

	<fieldset>
		<legend>
			<spring:message code="submission.conference.info" />
		</legend>
		<acme:out code="submission.conference.title"
			value="${submission.conference.title}" />
		<acme:out code="submission.conference.acronym"
			value="${submission.conference.acronym}" />
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="submission.basic.info" />
		</legend>
		<acme:out code="submission.ticker" value="${submission.ticker}" />
		<acme:out code="submission.moment" value="${submission.moment}" />
		<acme:out code="submission.author"
			value="${submission.author.userAccount.username}" />
		<acme:out code="submission.ticker" value="${submission.ticker}" />
	</fieldset>

	<acme:select items="${reviewers}" code="submission.reviewers"
		path="reviewers" itemLabel="userAccount.username" />
		
	<acme:submit name="assign" code="submission.assign"/>
	<acme:cancel url="submission/administrator/list.do" code="submission.cancel"/>

</form:form>