<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
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
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board?a=executeModify">
				<input type = "hidden" name = "a" value="modify">
				<input type = "hidden" name = "no" value="${param.no}">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${boardVo.title }"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content">${boardVo.content }</textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=view&no=${param.no }">취소</a>
						<input type="submit" value="수정">
					</div>
				</form>				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>