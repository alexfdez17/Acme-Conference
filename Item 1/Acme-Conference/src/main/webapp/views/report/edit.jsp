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

<form:form action="${requestURI}" modelAttribute="report">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="submission" />

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

		<acme:textbox code="report.originality.score" path="originality" />
		<acme:textbox code="report.quality.score" path="quality" />
		<acme:textbox code="report.readability.score" path="readability" />
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="report.decision.comments.info" />
		</legend>

		<acme:select items="${decisions}" code="report.decision"
			path="decision" />
		<acme:textarea code="report.comments" path="comments" />
	</fieldset>

	<acme:submit name="save" code="report.save" />
	<acme:cancel url="report/reviewer/list" code="report.cancel" />

</form:form>