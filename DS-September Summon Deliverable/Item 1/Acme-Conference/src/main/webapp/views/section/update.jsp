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

<form:form action="section/administrator/edit.do"
	modelAttribute="section">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="section.title" path="title"/>
	<acme:textarea code="section.summary" path="summary"/>
	<acme:textarea code="section.pictures" path="pictures"/>
	<br />	
	
	<acme:submit name="save" code="section.save"/>
	<acme:submit name="delete" code="section.delete"/>

</form:form>
