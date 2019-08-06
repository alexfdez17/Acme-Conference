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

<display:table pagesize="5" class="displaytag" name="sponsorships"
	requestURI="sponsorship/sponsor/list.do" id="row">

	<!-- Attributes -->

	<display:column property="conference.acronym"
		titleKey="sponsorship.conference" sortable="true" />

	<display:column property="banner" titleKey="sponsorship.banner"
		sortable="false">
		<a href="${row.banner}"><jstl:out value="${row.banner}" /></a>
	</display:column>

	<display:column property="targetURL" title="sponsorship.targetURL"
		sortable="false">
		<a href="${row.targetURL}"><jstl:out value="${row.targetURL}" /></a>
	</display:column>

	<!-- Actions -->

	<display:column>
		<a href="sponsorship/sponsor/edit.do?sponsorshipId=${row.id}"> <spring:message
				code="sponsorship.edit" />
		</a>
	</display:column>

</display:table>