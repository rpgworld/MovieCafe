<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<li><a href="index.jsp">메인</a></li>
				<li><a href="bbsList.bbs">게시판</a></li>
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
						<li class="active"><a href="userUpdateForm.user">회원 정보 수정</a></li>
						<li><a href="userLogout.user">로그아웃</a></li>
					</ul>
					
					<%
					}
					%>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
			<div class="jumbotron">
				<form action="UserUpdate.user" method="post">
					<h3 style="text-align: center;"> 회원 정보 수정</h3>
					<div class="form-group">
						<input type="text" class="form-control" name="userID" placeholder="이이디" maxlength="20" value="${userUpdate.userID }" readonly>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="userPW" placeholder="비밀번호" maxlength="20" value="${userUpdate.userPW }">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="userName" placeholder="이름" maxlength="20" value="${userUpdate.userName }">
					</div>
					<div class="form-group" style="text-align: center">
						<div class="btn-group" data-toggle="buttons">
						<c:choose>
							<c:when test="${userUpdate.userGender eq '남자'}">
								<label class="btn btn-primary active">
									<input type="radio" autocomplete="off" name="userGender" value="남자" checked> 남자
								</label>
								<label class="btn btn-primary">
									<input type="radio" autocomplete="off" name="userGender" value="여자"> 여자
								</label>
							</c:when>
							<c:when test="${userUpdate.userGender eq '여자'}">
								<label class="btn btn-primary">
									<input type="radio" autocomplete="off" name="userGender" value="남자"> 남자
								</label>
								<label class="btn btn-primary active">
									<input type="radio" autocomplete="off" name="userGender" value="여자" checked> 여자
								</label>
							</c:when>
						</c:choose>
						
						</div>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" name="userEmail" placeholder="이메일" maxlength="50" value="${userUpdate.userEmail }">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="회원 정보 수정">
				</form>
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>