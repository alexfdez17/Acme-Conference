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

<display:table pagesize="5" class="displaytag" name="topics"
	requestURI="${requestURI}" id="row">

	<jstl:if test="${lang == 'en'}">
		<acme:column code="topic.name" property="name" />
	</jstl:if>
	<jstl:if test="${lang == 'es'}">
		<acme:column code="topic.nameES" property="nameES" />
	</jstl:if>

	<display:column>
		<a href="topic/administrator/edit.do?topicId=${row.id}"><spring:message
				code="topic.edit" /></a>
	</display:column>

</display:table>

<a href="topic/administrator/create.do"><spring:message
		code="topic.create" /></a>