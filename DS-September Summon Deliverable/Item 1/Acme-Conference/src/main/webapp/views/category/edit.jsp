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

<form:form action="${requestURI}" modelAttribute="category">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<jstl:if test="${category.parent != null and category.id == 0}">
		<form:hidden path="parent" />
	</jstl:if>

	<jstl:if test="${category.parent == null or category.id != 0}">
		<acme:select items="${categories}" code="category.parent"
			path="parent" itemLabel="title"/>
	</jstl:if>

	<acme:textbox code="category.title" path="title" />

	<acme:submit name="save" code="category.save" />

	<jstl:if test="${category.id != 0}">
		<acme:submit name="delete" code="category.delete" />
	</jstl:if>

	<jstl:if test="${category.parent != null }">
		<acme:cancel
			url="category/administrator/list.do?parentId=${category.parent.id}"
			code="category.cancel" />
	</jstl:if>

	<jstl:if test="${category.parent == null }">
		<acme:cancel
			url="category/administrator/list.do?parentId=${category.parent.id}"
			code="category.cancel" />
	</jstl:if>

</form:form>