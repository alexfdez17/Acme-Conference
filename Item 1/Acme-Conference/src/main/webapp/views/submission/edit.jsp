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

<form:form action="submission/author/${action}"
	modelAttribute="submissionForm">

	
	<form:hidden path="submission" />
	
	<acme:textbox code="paper.title" path="title"/>
	<acme:textbox code="paper.authors" path="authors"/>
	<acme:textbox code="paper.summary" path="summary"/>
	<acme:textbox code="paper.document" path="document"/>
	
	<acme:submit name="save" code="submission.save"/>
		
	<acme:cancel url="submission/author/list.do" code="submission.cancel"/>
	

</form:form>