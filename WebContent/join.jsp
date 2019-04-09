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
				<li><a href="bbsList.jsp">게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li class="active"><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
			<div class="jumbotron">
				<form action="UserRegister.user" method="post">
					<h3 style="text-align: center;"> 회원 가입 양식</h3>
					<div class="form-group">
						<input type="text" class="form-control" name="userID" placeholder="이이디" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="userPW" placeholder="비밀번호" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="userName" placeholder="이름" maxlength="20">
					</div>
					<div class="form-group" style="text-align: center">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active">
								<input type="radio" autocomplete="off" name="userGender" value="남자" checked> 남자
							</label>
							<label class="btn btn-primary">
								<input type="radio" autocomplete="off" name="userGender" value="여자"> 여자
							</label>
						</div>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" name="userEmail" placeholder="이메일" maxlength="50">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="회원가입">
				</form>
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>