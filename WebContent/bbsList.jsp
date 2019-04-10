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
		<table class="table table-striped" style="text-align: center">
			<thead>
				<tr>
					<th style="text-align: center">글 번호</th>
					<th style="text-align: center">글 제목</th>
					<th style="text-align: center">작성자</th>
					<th style="text-align: center">작성일</th>
					<th style="text-align: center">작성시간</th>
					<th style="text-align: center">조회수</th>
					<th style="text-align: center">답글수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bbsList }" var="bbsDTO">
					<tr>
						<td><a href="bbsRead.bbs?num=${bbsDTO.num }">${bbsDTO.num }</a></td>
						<td>
							<c:forEach begin="1" end="${bbsDTO.lev }">
								<%= "&nbsp; &nbsp;" %>
							</c:forEach>
							<a href="bbsRead.bbs?num=${bbsDTO.num }">${bbsDTO.subject }</a>
						</td>
						<td>${bbsDTO.name }</td>
						<td>${bbsDTO.writeDate }</td>
						<td>${bbsDTO.writeTime }</td>
						<td>${bbsDTO.readCnt }</td>
						<td>${bbsDTO.childCnt }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="row" style="width: 100%; dsplay: table;">
			<div class="text-left" style="float: left;">
				<ul class="pagination">
					<li><a href="bbsList.bbs">첫 페이지</a></li>
					<c:forEach var="i" begin="1" end="${pageCnt }">
						<li><a href="bbsList.bbs?curPage=${i }">${i }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div style="text-align: center;">
				<form action="bbsSearch.bbs" method="post" class="form-inline">
					<select class="form-control" name="serachOption" style="width: 100px;">
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="both">제목+내용</option>
						<option value="name">작성자</option>
					</select>
					<input type="text" class="form-control" name="searchWord" placeholder="검색" style="width: 150px;">
					<span class="form-control-btns">
	                   <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
	               </span>
				</form>
			</div>
			<div style="float: right;">
				<a href="bbsWriteForm.bbs" class="btn btn-primary pull-right">글쓰기</a>
			</div>
		</div>
	</div>
	
	<footer style="background-color: #333333; color: #ffffff">
		<div class="container">
			<h4 style="text-align: center;"> 피드백 문의 : cloud_data@naver.com </h4>
		</div>
	</footer>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>