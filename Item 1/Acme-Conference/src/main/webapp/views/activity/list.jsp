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

<display:table pagesize="5" class="displaytag" name="activities"
	requestURI="activity/list.do?conferenceId=${conferenceId}" id="row">

	<!-- Attributes -->
	
	<spring:message code="activity.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="false" />
		
	<spring:message code="activity.speakers" var="speakersHeader" />
	<display:column property="speakers" title="${speakersHeader}"
		sortable="false" />
	
	<spring:message code="activity.startMoment" var="startMomentHeader" />
	<display:column property="startMoment" title="${startMomentHeader}"
		sortable="false" format="{0,date,${datePattern}}" />

	<spring:message code="activity.duration" var="durationHeader" />
	<display:column property="duration" title="${durationHeader}"
		sortable="false" />
		
	<!-- Actions -->
	
	<display:column>
		<a href="activity/display.do?activityId=${row.id}"> <spring:message
				code="activity.display" />
		</a>
	</display:column>
	
	<display:column>
			<a href="comment/listFromActivity.do?activityId=${row.id}">
				<spring:message code="conference.comment.list" />
			</a>
		</display:column>
	
</display:table>
<br/>

<security:authorize access="hasRole('ADMIN')">

<jstl:if test="${conference.isFinal == false}">
<a href="tutorial/administrator/create.do?conferenceId=${conferenceId}"> <spring:message
				code="activity.createTutorial" />
</a>
<br/>	
<a href="panel/administrator/create.do?conferenceId=${conferenceId}"> <spring:message
				code="activity.createPanel" />
</a>
<br/>		
<a href="presentation/administrator/create.do?conferenceId=${conferenceId}"> <spring:message
				code="activity.createPresentation" />
</a>
</jstl:if>

</security:authorize>
