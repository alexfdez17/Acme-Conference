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

<form:form action="${requestURI}" modelAttribute="topic">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<jstl:if test="${lang == 'en'}">
		<acme:textbox code="topic.name" path="name"/>
		<acme:textbox code="topic.nameES" path="nameES"/>
	</jstl:if>
	<jstl:if test="${lang == 'es'}">
		<acme:textbox code="topic.name" path="nameES"/>
		<acme:textbox code="topic.nameEN" path="name"/>
	</jstl:if>
	
	<acme:submit name="save" code="topic.save" />
	<acme:cancel url="topic/administrator/list.do" code="topic.cancel" />

</form:form>
