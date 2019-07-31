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

<form:form action="sponsorship/sponsor/edit.do"
	modelAttribute="sponsorship">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<jstl:choose>
	<jstl:when test="${id == 0}">
	<form:hidden path="sponsor" />
	<form:hidden path="conference" />
	</jstl:when>
	</jstl:choose>
	
	<acme:textbox code="sponsorship.banner" path="banner"/>
	<br />
	<acme:textbox code="sponsorship.targetURL" path="targetURL"/>
	<br />
	
	<acme:textbox code="creditCard.holder" path="creditCard.holder"/>
	<br />
	<acme:textbox code="creditCard.brand" path="creditCard.brand"/>
	<br />
	<acme:textbox code="creditCard.number" path="creditCard.number"/>
	<br />
	<acme:textbox code="creditCard.expirationMonth" path="creditCard.expirationMonth"/>
	<br />
	<acme:textbox code="creditCard.expirationYear" path="creditCard.expirationYear"/>
	<br />
	<acme:textbox code="creditCard.cvv" path="creditCard.cvv"/>
	<br />
	
	<acme:submit name="save" code="sponsorship.save" />
	<jstl:choose>
	<jstl:when test="${id != 0}">
		<acme:submit name="delete" code="sponsorship.delete" />
	</jstl:when>
	</jstl:choose>
	
	<acme:cancel code="sponsorship.cancel" url="sponsorship/sponsor/list.do" />
	

</form:form>