<%--
 * dashboard.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2>
	<spring:message code="administrator.submissionsPerConference" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumspc}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumspc}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagespc}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevspc}
</p>

<h2>
	<spring:message code="administrator.registrationsPerConference" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumrpc}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumrpc}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagerpc}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevrpc}
</p>

<h2>
	<spring:message code="administrator.conferenceFees" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumcf}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumcf}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagecf}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevcf}
</p>

<h2>
	<spring:message code="administrator.daysPerConference" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumdpc}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumdpc}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagedpc}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevdpc}
</p>

<h2>
	<spring:message code="administrator.conferencesPerCategory" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumcpc}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumcpc}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagecpc}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevcpc}
</p>

<h2>
	<spring:message code="administrator.commentsPerConference" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumcmpc}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumcmpc}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagecmpc}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevcmpc}
</p>

<h2>
	<spring:message code="administrator.commentsPerActivity" />
</h2>

<p>
	<b><spring:message code="administrator.maximum" /> :</b> ${maximumcmpa}
</p>
<p>
	<b><spring:message code="administrator.minimum" /> :</b> ${minimumcmpa}
</p>
<p>
	<b><spring:message code="administrator.average" /> :</b> ${averagecmpa}
</p>
<p>
	<b><spring:message code="administrator.stdev" /> :</b> ${stdevcmpa}
</p>