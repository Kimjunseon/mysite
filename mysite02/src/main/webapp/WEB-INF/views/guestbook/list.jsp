<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.request.contextPath }/user?a=loginform">로그인</a><li>
						<li><a href="${pageContext.request.contextPath }/user?a=joinform">회원가입</a><li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath }/user?a=updateform">회원정보수정</a><li>
						<li><a href="${pageContext.request.contextPath }/user?a=logout">로그아웃</a><li>
						<li>${authUser.name } 님 안녕하세요 ^^;</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/gb" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<table>
						    <c:set var='count' value='${fn:length(list) }' />
							<c:forEach items='${list }' var='vo' varStatus='status'>
								<tr>
									<td>${count-status.index }</td>
									<td>${vo.name }</td>
									<td>${vo.regDate } </td>
									<td><a href="${pageContext.request.contextPath }/gb?a=deleteform&no= ${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${vo.contents }</td>
								</tr>
							</c:forEach>
						</table>
						<br>
					</li>
				</ul>
			</div>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath }">김준선</a></li>
				<li><a href="${pageContext.request.contextPath }/gb">방명록</a></li>
				<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
			</ul>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022</p>
		</div>
	</div>
</body>
</html>