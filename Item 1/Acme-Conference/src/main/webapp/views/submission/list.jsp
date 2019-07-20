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

<display:table pagesize="5" class="displaytag" name="submissions"
	requestURI="submission/author/list.do" id="row">

	<!-- Attributes -->
	<security:authorize access="hasRole('AUTHOR')">
	<spring:message code="submission.conference" var="conferenceHeader" />
	<display:column property="conference.acronym" title="${conferenceHeader}"
		sortable="false" />
	</security:authorize>
	
	<security:authorize access="hasAnyRole('ADMIN','AUTHOR')">
	<spring:message code="submission.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}"
		sortable="false" />
	
	<spring:message code="submission.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}"
		sortable="false" />
	</security:authorize>
	
	<security:authorize access="hasRole('AUTHOR')">
	<spring:message code="submission.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		sortable="false" />
		
	<!-- Actions -->
		
	<display:column>
		<a href="submission/author/display.do?submissionId=${row.id}"> <spring:message
				code="submission.display" />
		</a>
	</display:column>
	
	<display:column>
	<jstl:if test="${row.status == 'ACCEPTED' && row.conference.cameraReadyDeadline > today && row.cameraReadyPaper == null}">
		<a href="submission/author/upload.do?submissionId=${row.id}"> <spring:message
				code="submission.upload" />
		</a>
	</jstl:if>
	</display:column>
	</security:authorize>
	
</display:table>
<br/>
