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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" name="authors"
	requestURI="${requestURI}" id="row">

	<display:column titleKey="author.userAccount.username"
		property="userAccount.username" sortable="true" />
	<display:column titleKey="author.name" property="name" sortable="true" />
	<display:column titleKey="author.middleName" property="middleName"
		sortable="true" />
	<display:column titleKey="author.surname" property="surname"
		sortable="true" />
	<display:column titleKey="author.email" property="email"
		sortable="true" />
	<display:column titleKey="author.score" sortable="true">
		<jstl:if test="${row.score != null}">
			<jstl:out value="${row.score}" />
		</jstl:if>
		<jstl:if test="${row.score == null}">
			<jstl:out value="N/A" />
		</jstl:if>
	</display:column>

</display:table>

<acme:cancel code="author.compute.scores"
	url="author/administrator/scores.do" />