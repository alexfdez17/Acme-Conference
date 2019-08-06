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

<form:form action="${requestURI}" modelAttribute="mezzage">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<jstl:if test="${requestURI != 'message/administrator/broadcast.do'}">
		<acme:select items="${recipients}" code="message.recipient"
			path="recipients" itemLabel="userAccount.username" />
	</jstl:if>

	<acme:textbox code="message.subject" path="subject" />
	<acme:textarea code="message.body" path="body" />
	<acme:select items="${topics}" code="message.topic" path="topic"
		itemLabel="${topicName}" />
	
	<jstl:if test="${requestURI != 'message/administrator/broadcast.do'}">
		<acme:submit name="send" code="message.save" />
	</jstl:if>
	<jstl:if test="${requestURI == 'message/administrator/broadcast.do'}">
		<acme:submit name="broadcastAll" code="message.broadcast.all" /><br/>
		<acme:submit name="broadcastAllAuthors" code="message.broadcast.all.authors" /><br/>
		<acme:submit name="broadcastAuthorRegistration" code="message.broadcast.authors.registration" /><br/>
		<acme:submit name="broadcastAuthorSubmission" code="message.broadcast.authors.submission" /><br/>
	</jstl:if>
	<acme:cancel url="message/list.do" code="message.cancel" />

</form:form>