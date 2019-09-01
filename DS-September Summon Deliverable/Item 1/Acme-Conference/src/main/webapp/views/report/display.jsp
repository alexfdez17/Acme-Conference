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
		<spring:message code="report.submission.info" />
	</legend>

	<acme:out code="report.submission.ticker"
		value="${report.submission.ticker}" />

	<acme:out code="report.submission.moment"
		value="${report.submission.moment}" />

	<fieldset>
		<legend>
			<spring:message code="report.submission.paper.info" />
		</legend>

		<acme:out code="report.submission.paper.title"
			value="${report.submission.paper.title}" />

		<acme:out code="report.submission.paper.authors"
			value="${report.submission.paper.authors}" />

		<acme:out code="report.submission.paper.summary"
			value="${report.submission.paper.summary}" />

	</fieldset>
	
	<jstl:if test="${report.submission.cameraReadyPaper != null}">
		<fieldset>
			<legend>
				<spring:message code="report.submission.cameraReadyPaper.info" />
			</legend>
	
			<acme:out code="report.submission.paper.title"
				value="${report.submission.cameraReadyPaper.title}" />
	
			<acme:out code="report.submission.paper.authors"
				value="${report.submission.cameraReadyPaper.authors}" />
	
			<acme:out code="report.submission.paper.summary"
				value="${report.submission.cameraReadyPaper.summary}" />
	
			<acme:url url="${report.submission.cameraReadyPaper.document}"
				code="report.submission.cameraReadyPaper.document" />
		</fieldset>
	</jstl:if>
</fieldset>

<fieldset>
	<legend>
		<spring:message code="report.score.info" />
	</legend>

	<acme:out code="report.originality.score"
		value="${report.originality}" />
	<acme:out code="report.quality.score" value="${report.quality}" />
	<acme:out code="report.readability.score"
		value="${report.readability}" />
</fieldset>

<fieldset>
	<legend>
		<spring:message code="report.decision.comments.info" />
	</legend>

	<acme:out code="report.decision" value="${report.decision}" />
	<acme:out code="report.comments" value="${report.comments}" />
</fieldset>