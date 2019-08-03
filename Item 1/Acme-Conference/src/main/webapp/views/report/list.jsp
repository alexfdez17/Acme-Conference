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

<display:table pagesize="5" class="displaytag" name="reports"
	requestURI="${requestURI}" id="row">

	<acme:column code="report.submission" property="submission.ticker"
		sortable="true" />

	<acme:column code="report.originality.score" property="originality"
		sortable="true" />
	<acme:column code="report.readability.score" property="readability"
		sortable="true" />
	<acme:column code="report.quality.score" property="quality"
		sortable="true" />

	<security:authorize access="hasRole('AUTHOR')">
		<acme:column code="report.reviewer"
			property="reviewer.userAccount.username" sortable="true" />
	</security:authorize>

	<security:authorize access="hasRole('REVIEWER')">
		<acme:column code="report.submission.author"
			property="submission.author.userAccount.username" sortable="true" />
	</security:authorize>

	<display:column>
		<a href="report/display.do?reportId=${row.id}"><spring:message
				code="report.display" /></a>
	</display:column>

</display:table>