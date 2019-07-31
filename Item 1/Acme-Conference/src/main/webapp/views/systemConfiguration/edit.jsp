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

<form:form action="${requestURI}" modelAttribute="systemConfiguration">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<fieldset>
		<legend>
			<spring:message code="systemConfiguration.basic.info" />
		</legend>

		<acme:textbox code="systemConfiguration.systemName" path="systemName" />
		<acme:textbox code="systemConfiguration.banner" path="banner" />
		<acme:textbox code="systemConfiguration.welcomeMessage"
			path="welcomeMessage" />
		<acme:textbox code="systemConfiguration.welcomeMessageES"
			path="welcomeMessageES" />
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="systemConfiguration.other.info" />
		</legend>

		<acme:textbox code="systemConfiguration.countryCode"
			path="countryCode" />

		<acme:textarea code="systemConfiguration.creditCardMakes"
			path="creditCardMakes" />

		<acme:textarea code="systemConfiguration.voidWords" path="voidWords" />
	</fieldset>

	<acme:submit name="save" code="systemConfiguration.save" />
	<acme:cancel url="/welcome/index.do" code="systemConfiguration.cancel" />

</form:form>