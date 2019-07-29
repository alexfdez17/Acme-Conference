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


<script type="text/javascript">
	function listByKeyword(event) {
		if (event.keyCode == 13) {
			var keyword = document.getElementById("keyword").value;
			window.location.assign("conference/list.do?keyword=" + keyword);

			return false;
		}
	}
</script>

<spring:message code="conference.list.by.keyword" />
<input type="text" id="keyword" onkeypress="listByKeyword(event)"
	onclick="listByKeyword(event)"
	placeholder="<spring:message code="conference.keyword.placeholder" />"
	value="${keyword}" />
<br />
<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" name="conferences"
	requestURI="${requestURI}" id="row">

	<!-- Attributes -->

	<acme:column code="conference.title" property="title" sortable="true" />

	<acme:column code="conference.acronym" property="acronym"
		sortable="true" />

	<acme:column code="conference.venue" property="venue" sortable="true" />

	<acme:column code="conference.startDate" property="startDate"
		sortable="true" />

	<acme:column code="conference.endDate" property="endDate"
		sortable="true" />

	<security:authorize access="hasAnyRole('ADMIN', 'AUTHOR')">
		<acme:column code="conference.submissionDeadline"
			property="submissionDeadline" sortable="true" />

		<acme:column code="conference.notificationDeadline"
			property="notificationDeadline" sortable="true" />

		<acme:column code="conference.cameraReadyDeadline"
			property="cameraReadyDeadline" sortable="true" />
	</security:authorize>

	<acme:column code="conference.fee" property="fee" sortable="true" />

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

	<security:authorize access="hasRole('AUTHOR')">
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
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if
				test="${row.submissionDeadline < today && row.startDate > today && row.isFinal == true}">
				<a href="administrator/decide.do?conferenceId=${row.id}"> <spring:message
						code="conference.decide" />
				</a>
			</jstl:if>
		</display:column>

		<display:column>
			<jstl:if test="${row.isFinal == false}">
				<a href="conference/administrator/edit.do?conferenceId=${row.id}">
					<spring:message code="conference.edit" />
				</a>
			</jstl:if>
			<jstl:if test="${row.isFinal == true}">
				<i> <spring:message code="conference.is.published" />
				</i>
			</jstl:if>
		</display:column>
	</security:authorize>

	<display:column>
		<a href="conference/display.do?conferenceId=${row.id}"> <spring:message
				code="conference.display" />
		</a>
	</display:column>

</display:table>

<security:authorize access="hasRole('ADMIN')">
	<a href="conference/administrator/create.do"> <spring:message
			code="conference.create" />
	</a>
</security:authorize>
