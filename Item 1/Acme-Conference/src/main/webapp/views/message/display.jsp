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

<acme:url code="message.sender"
	url="message/list.do?senderId=${mezzage.sender.id}"
	value="${mezzage.sender.userAccount.username}" />

<b><spring:message code="message.recipient" />:</b>

<jstl:set var="size" value="${fn:length(mezzage.recipients)}" />
<jstl:set var="cont" value="0" />

<jstl:forEach items="${mezzage.recipients}" var="recipient">
	<a href="message/list.do?recipientId=${recipient.id}"><jstl:out
			value="${recipient.userAccount.username}" /></a>

	<jstl:set var="cont" value="${cont + 1}" />
	<jstl:if test="${cont != size}">
		<jstl:out value=";" />
	</jstl:if>
</jstl:forEach>
<br />

<acme:out code="message.moment" value="${moment}" />
<acme:out code="message.subject" value="${mezzage.subject}" />
<acme:out code="message.topic" value="${topic}" />
<acme:out code="message.body" value="${mezzage.body}" />

<form:form action="message/edit.do" modelAttribute="mezzage">
	<form:hidden path="id" />
	
	<acme:submit code="message.delete" name="delete" />
	<acme:cancel url="message/list.do" code="message.back"/>
</form:form>