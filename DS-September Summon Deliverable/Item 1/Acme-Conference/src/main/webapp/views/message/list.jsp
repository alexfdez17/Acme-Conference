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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
	function listByTopic(event) {
		if (event.keyCode == 13) {
			var topic = document.getElementById("topic").value;
			window.location.assign("message/list.do?topic=" + topic);
		}
	}
</script>

<spring:message code="message.list.by.topic" />
<input type="text" id="topic" onkeypress="listByTopic(event)"
	onclick="listByTopic(event)"
	placeholder="<spring:message code="message.topic.placeholder" />" />
<br />
<!--  Listing grid -->
<br/>
<spring:message code="message.listing.info" />
<display:table pagesize="5" class="displaytag" name="mezzages"
	requestURI="${requestURI}" id="row">

	<display:column titleKey="message.sender">
		<a href="message/list.do?senderId=${row.sender.id}"> <jstl:out
				value="${row.sender.userAccount.username}" />
		</a>
	</display:column>

	<display:column titleKey="message.recipient">
		<jstl:if test="${not row.isBroadcast}">
			<jstl:set var="size" value="${fn:length(row.recipients)}" />
			<jstl:set var="cont" value="0" />

			<jstl:forEach items="${row.recipients}" var="recipient">
				<a href="message/list.do?recipientId=${recipient.id}"> <jstl:out
						value="${recipient.userAccount.username}" />
				</a>

				<jstl:set var="cont" value="${cont + 1}" />
				<jstl:if test="${cont < size}">
					<b><jstl:out value=";" /></b>
				</jstl:if>
			</jstl:forEach>
		</jstl:if>
		<jstl:if test="${row.isBroadcast}">
			<spring:message code="message.isBroadcast" />
		</jstl:if>
	</display:column>

	<display:column property="subject" titleKey="message.subject"
		sortable="true" />

	<display:column titleKey="message.topic" sortable="true">
		<jstl:if test="${lang == 'en'}">
			<jstl:out value="${row.topic.name}" />
		</jstl:if>
		<jstl:if test="${lang == 'es'}">
			<jstl:out value="${row.topic.nameES}" />
		</jstl:if>
	</display:column>

	<display:column property="moment" titleKey="message.moment"
		format="{0, date, ${dateFormatter}}" sortable="true" />

	<display:column>
		<a href="message/display.do?messageId=${row.id}"> <spring:message
				code="message.display" />
		</a>
	</display:column>

</display:table>