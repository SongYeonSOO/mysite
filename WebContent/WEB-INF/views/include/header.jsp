<%@page import="com.estsoft.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%UserVo authoVo = (UserVo) session.getAttribute("authUser"); %>
<div id="header">
	
	<h1>
		<a href="/mysite/main">MySite</a>
	</h1>
	<ul>
	<%if(authoVo == null){ %>
		<li><a href="/mysite/user?a=loginform">로그인</a>
		<li>
		<li><a href="/mysite/user?a=joinform">회원가입</a>
		<li>
			<%} else{%>
		
		<li><a href="/mysite/user?a=modifyform">회원정보수정</a>
		<li>
		<li><a href="/mysite/user?a=logout">로그아웃</a>
		<li>
		<li>님 안녕하세요 ^^;</li>
		<%} %>
	</ul>
</div>