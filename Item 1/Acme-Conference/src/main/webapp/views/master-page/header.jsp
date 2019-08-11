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

<script type="text/javascript">
	function notificationSuccesfull(message) {
		window.alert(message);
	}
</script>


<div>
	<a href="#"><img src="${banner}" alt="${systemName} Co., Inc." /></a>
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
						<li><a href="administrator/display.do"><spring:message
									code="master.page.profile.display" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('AUTHOR')">
						<li><a href="author/edit.do"><spring:message
									code="master.page.profile.edit" /></a></li>
						<li><a href="author/display.do"><spring:message
									code="master.page.profile.display" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('REVIEWER')">
						<li><a href="reviewer/edit.do"><spring:message
									code="master.page.profile.edit" /></a></li>
						<li><a href="reviewer/display.do"><spring:message
									code="master.page.profile.display" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('SPONSOR')">
						<li><a href="sponsor/edit.do"><spring:message
									code="master.page.profile.edit" /></a></li>
						<li><a href="sponsor/display.do"><spring:message
									code="master.page.profile.display" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
			<li><a class="fNiv"> <spring:message
						code="master.page.messaging" />
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/list.do"><spring:message
								code="master.page.messaging.list" /></a></li>
					<li><a href="message/create.do"><spring:message
								code="master.page.messaging.send" /></a></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="message/administrator/create.do"><spring:message
									code="master.page.admin.messaging.broadcast" /></a></li>
					</security:authorize>
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
					<li><a><spring:message
								code="master.page.administrator.submission" /></a>
						<ul>
							<li><a href="submission/administrator/list.do"><spring:message
										code="master.page.administrator.submission.list" /></a></li>
							<li><a href="submission/administrator/autoAssign.do"
								onclick="notificationSuccesfull('<spring:message code='master.page.assignment.successful'/>');"><spring:message
										code="master.page.administrator.submission.auto.assign" /></a></li>
						</ul></li>
					<li><a href="author/administrator/list.do"><spring:message
								code="master.page.administrator.author.scores" /></a>
					<li><a><spring:message
								code="master.page.administrator.system" /></a>
						<ul>
							<li><a href="administrator/dashboard.do"><spring:message
										code="master.page.administrator.dashboard" /></a></li>
							<li><a href="sys-config/administrator/edit.do"><spring:message
										code="master.page.administrator.systemConfiguration" /></a></li>
							<li><a href="topic/administrator/list.do"><spring:message
										code="master.page.administrator.topic.list" /></a></li>
							<li><a href="category/administrator/list.do"><spring:message
										code="master.page.administrator.category.list" /></a></li>
							<li><a href="message/administrator/notify.do"
								onclick="notificationSuccesfull('<spring:message code='master.page.notification.successful'/>');"><spring:message
										code="master.page.administrator.notify" /></a></li>
						</ul></li>

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
					<li><a href="finder/author/edit.do"><spring:message
								code="master.page.author.finder" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('REVIEWER')">
			<li><a class="fNiv"><spring:message
						code="master.page.reviewer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="report/reviewer/list.do"><spring:message
								code="master.page.reviewer.report.list" /></a></li>
					<li><a href="submission/reviewer/list.do"><spring:message
								code="master.page.reviewer.submission.list" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsorship/sponsor/list.do"><spring:message
								code="master.page.sponsor.sponsorships" /></a></li>
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

