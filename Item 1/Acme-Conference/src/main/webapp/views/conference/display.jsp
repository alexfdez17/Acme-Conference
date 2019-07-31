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

<jstl:if test="${hasSponsorships == true }">
	<fieldset>
		<legend>
			<spring:message code="conference.sponsorship.info" />
		</legend>
		<img src="${sponsorship.banner}" alt="${sponsorship.banner}" /> <br />
		<acme:url code="sponsorship.targetURL"
			url="${sponsorship.targetURL}" />
	</fieldset>
</jstl:if>

<fieldset>
	<legend>
		<spring:message code="conference.basic.info" />
	</legend>
	<acme:out code="conference.title" value="${conference.title}" />
	<acme:out code="conference.acronym" value="${conference.acronym}" />
	<acme:out code="conference.summary" value="${conference.summary}" />
	<acme:out code="conference.venue" value="${conference.venue}" />
	<acme:out code="conference.fee" value="${conference.fee}" />
	<acme:out code="conference.category"
		value="${conference.category.title}" />
</fieldset>

<fieldset>
	<legend>
		<spring:message code="conference.dates.info" />
	</legend>

	<security:authorize access="hasAnyRole('ADMIN', 'AUTHOR')">
		<acme:out code="conference.submissionDeadline"
			value="${conference.submissionDeadline}" />
		<acme:out code="conference.notificationDeadline"
			value="${conference.notificationDeadline}" />
		<acme:out code="conference.cameraReadyDeadline"
			value="${conference.cameraReadyDeadline}" />
	</security:authorize>

	<acme:out code="conference.startDate" value="${conference.startDate}" />
	<acme:out code="conference.endDate" value="${conference.endDate}" />
</fieldset>

<fieldset>
	<legend>
		<spring:message code="conference.activity.list" />
	</legend>

	<display:table pagesize="5" class="displaytag" name="activities"
		requestURI="${requestURI}" id="row">

		<!-- Attributes -->

		<acme:column code="activity.title" property="title" sortable="true" />

		<acme:column code="activity.speakers" property="speakers" />

		<acme:column code="activity.startMoment" property="startMoment"
			sortable="true" />

		<acme:column code="activity.duration" property="duration"
			sortable="true" />

		<!-- Actions -->

		<display:column>
			<a href="activity/display.do?activityId=${row.id}"> <spring:message
					code="activity.display" />
			</a>
		</display:column>

		<display:column>
			<a href="comment/listFromActivity.do?activityId=${row.id}"> <spring:message
					code="conference.comment.list" />
			</a>
		</display:column>

	</display:table>
</fieldset>