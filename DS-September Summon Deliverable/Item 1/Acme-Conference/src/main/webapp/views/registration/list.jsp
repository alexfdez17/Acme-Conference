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

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" name="registrations"
	requestURI="${requestURI}" id="row">

	<!-- Attributes -->

	<acme:column code="registration.creditCard.number"
		property="creditCard.number" sortable="true" />

	<acme:column code="registration.creditCard.holder"
		property="creditCard.holder" sortable="true" />

	<acme:column code="registration.creditCard.brand"
		property="creditCard.brand" sortable="true" />

	<!-- Actions -->

	<display:column>
		<a href="conference/display.do?conferenceId=${row.conference.id}">
			<spring:message code="registration.conference.display" />
		</a>
	</display:column>

	<display:column>
		<a href="registration/author/display.do?registrationId=${row.id}">
			<spring:message code="registration.display" />
		</a>
	</display:column>

</display:table>

<acme:cancel code="registration.create"
	url="conference/list.do?keyword=final" />