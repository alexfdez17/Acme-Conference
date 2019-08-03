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

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" name="comments"
	requestURI="${requestURI}" id="row">

	<!-- Attributes -->
	
	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="false" />
		
	<spring:message code="comment.author" var="authorHeader" />
	<display:column property="author" title="${authorHeader}"
		sortable="false" />
	
	<spring:message code="comment.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		sortable="false" format="{0,date,yy/MM/dd HH:mm}" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}"
		sortable="false" />
		
	<!-- Actions -->
	
</display:table>
<br/>
