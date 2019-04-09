<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Movie Cafe</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#bs-nav-collapse" aria-expanded="false">
				<span class="icon-bar"></span>	
				<span class="icon-bar"></span>	
				<span class="icon-bar"></span>	
			</button>
			<a href="index.jsp" class="navbar-brand">Movie Cafe</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-nav-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">메인</a></li>
				<li><a href="bbsList.jsp">게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<%
						if(userID == null) { 
					%>
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
					
					<%
					} else {	
					%>
					
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">회원관리<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="logout.jsp">로그아웃</a></li>
					</ul>
					
					<%
					}
					%>
				</li>
			</ul>
		</div>
	</nav>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>