<%@page import="com.estsoft.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%UserVo userVo = (UserVo)session.getAttribute("authUser");
System.out.println("12313213" +session.getAttribute("authUser"));
	System.out.println("ddddd" +userVo);
%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div id="content">
			<div id="user">

				<form id="modify-form" name="modifyForm" method="post" action="/mysite/user?a=modify">
				<input type='hidden' name="no" value=<%=userVo.getNo()%>>
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=<%=userVo.getName()%>> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<%if("F".equals(userVo.getGender())){ %>
						<label>여</label> <input type="radio" name="gender" value="F"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="M">
					<%}else{ %><label>여</label> <input type="radio" name="gender"
						value="F"> <label>남</label> <input
						type="radio" name="gender" value="M" checked="checked">

					<%} %>
					</fieldset>
					<input type="submit" value="수정하기">

				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>