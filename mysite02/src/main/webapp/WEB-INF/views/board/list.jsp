<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a
							href="${pageContext.request.contextPath }/user?a=loginform">로그인</a>
						<li>
						<li><a
							href="${pageContext.request.contextPath }/user?a=joinform">회원가입</a>
						<li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${pageContext.request.contextPath }/user?a=updateform">회원정보수정</a>
						<li>
						<li><a
							href="${pageContext.request.contextPath }/user?a=logout">로그아웃</a>
						<li>
						<li>${authUser.name }님 안녕하세요 ^^;</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:set var='count' value='${fn:length(list) }' />
					<c:forEach items='${list }' var='vo' varStatus='status'>

							<tr>
								<td>${count-status.index }</td>
								<td><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.contents }</a></td>
								<td>${vo.name }</td>
								<td>${vo.hit }</td>
								<td>${vo.regDate }</td>
								<c:if test="${authUser.name eq vo.name }">
									<a
										href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }"
										class="del">삭제</a>
						
							</tr>
							<tr>
								<td><img
									src='${pageContext.request.contextPath }/assets/images/reply.png' /></td>
							</tr>
						</c:if>


						<c:if test="${vo.depth == 1 } ">
							<tr>
								<td><img
									src='${pageContext.request.contextPath }/assets/images/reply.png' /></td>
								<td>${vo.contents }</td>
								<td></td>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<c:if test="${authUser.name eq vo.name }">
									<a
										href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }"
										class="del">삭제</a>
								</c:if>
							</tr>
						</c:if>

						<c:if test="${vo.depth == 2 } ">
							<tr>
								<td></td>
								<td><img
									src='${pageContext.request.contextPath }/assets/images/reply.png' /></td>
								<td>${vo.contents }</td>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<c:if test="${authUser.name eq vo.name }">
									<a
										href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }"
										class="del">삭제</a>
								</c:if>
							</tr>
						</c:if>
						</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->
				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=writeform"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>