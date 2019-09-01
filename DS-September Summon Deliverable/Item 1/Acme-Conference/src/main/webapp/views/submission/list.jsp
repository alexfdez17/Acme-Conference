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

<script type="text/javascript">
	function listByStatus(event) {
		if (event.keyCode == 13) {
			var status = document.getElementById("status").value;

			if (status != "ALL")
				window.location.assign("submission/administrator/list.do?status=" + status);
			else
				window.location.assign("submission/administrator/list.do");

			return false;
		}
	}
</script>

<security:authorize access="hasRole('ADMIN')">
	<spring:message code="submission.list.by.status" />
	<select id="status" onkeypress="listByStatus(event)"
		onclick="listByStatus(event)">
		<option value="ALL">ALL</option>
		<option value="ACCEPTED">ACCEPTED</option>
		<option value="REJECTED">REJECTED</option>
		<option value="UNDER-REVIEW">UNDER-REVIEW</option>
	</select>
	<p>
		<spring:message code="submission.status.placeholder" />
	</p>
</security:authorize>
<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" name="submissions"
	requestURI="${requestURI}" id="row">

	<!-- Attributes -->

	<acme:column code="submission.conference.acronym"
		property="conference.acronym" sortable="true" />

	<acme:column code="submission.ticker" property="ticker" sortable="true" />

	<display:column property="moment" titleKey="comment.moment"
		sortable="true" format="{0,date,yy/MM/dd HH:mm}" />

	<acme:column code="submission.status" property="status" />

	<display:column>
		<a href="submission/display.do?submissionId=${row.id}"> <spring:message
				code="submission.display" />
		</a>
	</display:column>

	<security:authorize access="hasRole('AUTHOR')">
		<display:column>
			<jstl:if test="${row.reportsAvailable == true}">
				<a href="report/author/list.do?submissionId=${row.id}"> <spring:message
						code="submission.report.list" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if test="${row.status == 'UNDER-REVIEW'}">
				<a
					href="submission/administrator/assignment.do?submissionId=${row.id}"><spring:message
						code="submission.assign" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('AUTHOR')">
		<!-- Actions -->
		<display:column>
			<jstl:if
				test="${row.status == 'ACCEPTED' && row.conference.cameraReadyDeadline > today && row.cameraReadyPaper == null}">
				<a href="submission/author/upload.do?submissionId=${row.id}"> <spring:message
						code="submission.upload" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('REVIEWER')">
		<display:column>
			<jstl:if
				test="${fn:contains(row.reviewers, principal) and not fn:contains(submissionsWithReportWritten, row)}">
				<a href="report/reviewer/create.do?submissionId=${row.id}"> <spring:message
						code="submission.report.create" />
				</a>
			</jstl:if>
			<jstl:if test="${fn:contains(submissionsWithReportWritten, row)}">
				<spring:message code="submission.report.already.written"/>
			</jstl:if>
		</display:column>
	</security:authorize>

</display:table>
<br />
