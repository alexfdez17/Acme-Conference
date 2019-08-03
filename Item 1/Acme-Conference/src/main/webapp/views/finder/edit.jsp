
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

<form:form action="finder/author/edit.do" modelAttribute="finder">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="author" />
	<form:hidden path="conferences" />

	<acme:textbox code="finder.keyword" path="keyword" />
	<acme:textbox code="finder.maximumFee" path="maximumFee" />
	<acme:textbox code="finder.category" path="category" />

	<form:label path="startDate">
		<spring:message code="finder.startDate" />:
	</form:label>
	<form:input type="date" path="startDate"
		format="{0,date,dd/MM/yyyy}" placeholder="dd/MM/yyy" />
		
	<form:label path="endDate">
		<spring:message code="finder.endDate" />:
	</form:label>
	<form:input type="date" path="endDate"
		format="{0,date,dd/MM/yyyy}" placeholder="dd/MM/yyy" />
	<br />

	<acme:submit name="save" code="finder.save" />
	<br />

	<a href="finder/author/clear.do"><spring:message
			code="finder.clear" /></a>

	<!--  Listing grid -->

	<display:table pagesize="10" class="displaytag" name="conferences"
		requestURI="${requestURI}" id="row">

		<!-- Attributes -->
		
		<spring:message code="conference.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}"
			sortable="true" />

		<spring:message code="conference.acronym" var="acronymHeader" />
		<display:column property="acronym" title="${acronymHeader}"
			sortable="false" />		

		<spring:message code="conference.venue" var="venueHeader" />
		<display:column property="venue" title="${venueHeader}"
			sortable="true" />
		
		<!-- Actions -->

		<display:column>
			<a href="activity/list.do?conferenceId=${row.id}"> <spring:message
					code="conference.activity.list" />
			</a>
		</display:column>

		<display:column>
			<a href="comment/listFromConference.do?conferenceId=${row.id}"> <spring:message
					code="conference.comment.list" />
			</a>
		</display:column>
	
		<display:column>
			<a href="comment/create.do?commentableId=${row.id}"> <spring:message
					code="comment.create" />
			</a>
		</display:column>
	
		<display:column>
			<jstl:if test="${row.submissionDeadline > today }">
				<a href="submission/author/create.do?conferenceId=${row.id}"> <spring:message
						code="conference.submission" />
				</a>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${row.submissionDeadline > today }">
				<a href="registration/author/create.do?conferenceId=${row.id}">
					<spring:message code="conference.registration" />
				</a>
			</jstl:if>
		</display:column>

		<display:column>
			<a href="conference/display.do?conferenceId=${row.id}"> <spring:message
					code="conference.display" />
			</a>
		</display:column>

		
	</display:table>

</form:form>