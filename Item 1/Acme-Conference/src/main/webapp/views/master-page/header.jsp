<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png"
		alt="Acme Conference Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="administrator/edit.do"><spring:message
									code="master.page.profile.edit" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('AUTHOR')">
						<li><a href="author/edit.do"><spring:message
									code="master.page.profile.edit" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('REVIEWER')">
						<li><a href="reviewer/edit.do"><spring:message
									code="master.page.profile.edit" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('SPONSOR')">
						<li><a href="sponsor/edit.do"><spring:message
									code="master.page.profile.edit" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="author/register.do"><spring:message
								code="master.page.register.author" /></a></li>
					<li><a href="reviewer/register.do"><spring:message
								code="master.page.register.reviewer" /></a></li>
					<li><a href="sponsor/register.do"><spring:message
								code="master.page.register.sponsor" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a><spring:message
								code="master.page.administrator.conference.list" /></a>
						<ul>
							<li><a href="conference/administrator/list.do?keyword=all"><spring:message
										code="master.page.administrator.conference.list.all" /></a></li>
							<li><a
								href="conference/administrator/list.do?keyword=cameraReadyElapses"><spring:message
										code="master.page.administrator.conference.list.cameraReadyElapses" /></a></li>
							<li><a
								href="conference/administrator/list.do?keyword=notificationDeadlineElapses"><spring:message
										code="master.page.administrator.conference.list.notificationDeadlineElapses" /></a></li>
							<li><a
								href="conference/administrator/list.do?keyword=organised"><spring:message
										code="master.page.administrator.conference.list.organised" /></a></li>
							<li><a
								href="conference/administrator/list.do?keyword=submissionDeadlineElapsed"><spring:message
										code="master.page.administrator.conference.list.submissionDeadlineElapsed" /></a></li>
						</ul></li>
					<li><a href="administrator/dashboard.do"><spring:message
								code="master.page.administrator.dashboard" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('AUTHOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.author" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="submission/author/list.do"><spring:message
								code="master.page.author.submission" /></a></li>
					<li><a href="registration/author/list.do"><spring:message
								code="master.page.author.registration.list" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="permitAll">
			<li><a class="fNiv"><spring:message
						code="master.page.conference.list" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/list.do?keyword=final"><spring:message
								code="master.page.conference.list.final" /></a></li>
					<li><a href="conference/list.do?keyword=forthcoming"><spring:message
								code="master.page.conference.list.forthcoming" /></a></li>
					<li><a href="conference/list.do?keyword=running"><spring:message
								code="master.page.conference.list.running" /></a></li>
					<li><a href="conference/list.do?keyword=past"><spring:message
								code="master.page.conference.list.past" /></a></li>
				</ul></li>
		</security:authorize>

	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

