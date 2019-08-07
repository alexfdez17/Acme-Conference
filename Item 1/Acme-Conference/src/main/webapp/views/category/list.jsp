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

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" name="categories"
	requestURI="${requestURI}" id="row">

	<display:column titleKey="category.parent" sortable="true">
		<jstl:out value="${row.parent.title}" />
	</display:column>

	<display:column property="title" titleKey="category.title"
		sortable="true" />

	<display:column>
		<a href="category/administrator/list.do?parentId=${row.id}"> <spring:message
				code="category.children" /></a>
	</display:column>
	
	<display:column>
		<a href="category/administrator/edit.do?categoryId=${row.id}"> <spring:message
				code="category.edit" /></a>
	</display:column>
	
	<display:column>
		<a href="category/administrator/create.do?parentId=${row.id}"><spring:message
				code="category.add.child" /></a>
	</display:column>

</display:table>

<acme:cancel code="category.back"
	url="category/administrator/list.do?parentId=${row.parent.parent.id}" />
<acme:cancel code="category.create"
	url="category/administrator/create.do" />