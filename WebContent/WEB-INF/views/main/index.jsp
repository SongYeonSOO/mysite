
<%@ page contentType="text/html;charset=UTF-8"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/main.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="/mysite/assets/images/Pierre_de_Fermat.jpg">
					<h2>안녕하세요. Fermat의 mysite에 오신 것을 환영합니다.</h2>
					<p>
						전 잘났습니다. <br>
						<br> <a href="#">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>