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

<fieldset>
	<legend>
		<spring:message code="registration.creditCard.info" />
	</legend>
	<acme:out code="registration.creditCard.number"
		value="${registration.creditCard.number}" />
	<acme:out code="registration.creditCard.holder"
		value="${registration.creditCard.holder}" />
	<acme:out code="registration.creditCard.brand"
		value="${registration.creditCard.brand}" />
	<acme:out code="registration.creditCard.expiration"
		value="${registration.creditCard.expirationMonth}/${registration.creditCard.expirationYear}" />
	<acme:out code="registration.creditCard.cvv"
		value="${registration.creditCard.cvv}" />
</fieldset>

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

	<a
		href="conference/display.do?conferenceId=${registration.conference.id}"><spring:message
			code="registration.conference.display" /></a>
</fieldset>
