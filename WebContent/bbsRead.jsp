<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "bbs.*" %>
<%@ page import = "java.util.*" %>
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
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4" style="background-color: #eeeeee; color: #000000; text-align: center;">게시판 글 열람</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>글 제목</td>
						<td colspan="3"><input type="text" class="form-control" placeholder="글 제목" name="subject" maxlength="50" value="${bbsRead.subject }" readonly></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="text" class="form-control" placeholder="작성자" name="userID" maxlength="50" value="${bbsRead.name }" readonly></td>
						<td>조회수 : ${bbsRead.readCnt }</td>
						<td>답글수 : ${bbsRead.childCnt }</td>
					</tr>
					<tr>
						<td>글 내용</td>
						<td colspan="3"><textarea placeholder="글 내용" class="form-control" name="content" maxlength="2048" style="height: 350px;" readonly>${bbsRead.content }</textarea></td>
					</tr>
					<tr>
						<td colspan="4" align="right">
							<a href="bbsList.bbs" class="btn btn-primary pull-left">목록으로</a>
							<a href="bbsUpdateForm.bbs?num=${bbsRead.num }" class="btn btn-primary">수정</a>
							<a href="bbsDeleteCheck.bbs?num=${bbsRead.num }" class="btn btn-primary">삭제</a>
							<a href="bbsReplyForm.bbs?num=${bbsRead.num }" class="btn btn-primary">답글</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<footer style="background-color: #333333; color: #ffffff margin-top: 20px;">
		<div class="container">
			<h4 style="text-align: center;"> 피드백 문의 : cloud_data@naver.com </h4>
		</div>
	</footer>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>