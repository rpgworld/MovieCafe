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
				<li class="active"><a href="bbsList.bbs">게시판</a></li>
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
						<li><a href="userUpdateForm.user">회원 정보 수정</a></li>
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
		<div class="row">
			<form method="post" action="bbsReply.bbs">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; color: #000000; text-align: center;">게시판 답글 쓰기
							<input type="hidden" name="num" value="${bbsReplyForm.num }">
							<input type="hidden" name="ref" value="${bbsReplyForm.ref }">
							<input type="hidden" name="step" value="${bbsReplyForm.step }">
							<input type="hidden" name="lev" value="${bbsReplyForm.lev }">
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="subject" maxlength="50" value="${bbsReplyForm.subject }"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height: 350px;">${bbsReplyForm.content }</textarea></td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<a href="bbsList.bbs" class="btn btn-primary pull-left">목록으로</a>
								<input type="submit" class="btn btn-primary" value="답글 쓰기">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	<footer style="background-color: #333333; color: #ffffff; margin-top: 20px;">
		<div class="container">
			<h4 style="text-align: center;"> 피드백 문의 : cloud_data@naver.com </h4>
		</div>
	</footer>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>