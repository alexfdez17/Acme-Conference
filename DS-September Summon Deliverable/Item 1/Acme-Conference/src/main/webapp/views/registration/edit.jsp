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

<form:form action="${requestURI}" modelAttribute="registration">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="conference"/>

	<fieldset>
		<legend>
			<spring:message code="registration.conference.info" />
		</legend>

		<acme:out code="registration.conference.title"
			value="${registration.conference.title}" />
		<acme:out code="registration.conference.venue"
			value="${registration.conference.venue}" />
		<acme:out code="registration.conference.fee"
			value="${registration.conference.fee}" />
		<acme:out code="registration.conference.startDate"
			value="${registration.conference.startDate}" />
		<acme:out code="registration.conference.endDate"
			value="${registration.conference.endDate}" />
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="registration.creditCard.info" />
		</legend>
		<acme:textbox code="registration.creditCard.number"
			path="creditCard.number" />
		<acme:textbox code="registration.creditCard.holder"
			path="creditCard.holder" />
		<acme:select items="${creditCardMakes}"
			code="registration.creditCard.brand" path="creditCard.brand" />
		<acme:textbox code="registration.creditCard.expirationMonth"
			path="creditCard.expirationMonth" />
		<acme:textbox code="registration.creditCard.expirationYear"
			path="creditCard.expirationYear" />
		<acme:textbox code="registration.creditCard.cvv" path="creditCard.cvv" />
	</fieldset>

	<acme:submit name="save" code="registration.save" />
	<acme:cancel url="conference/list.do?keyword=final"
		code="registration.cancel" />

</form:form>