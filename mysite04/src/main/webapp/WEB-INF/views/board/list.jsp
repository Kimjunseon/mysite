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
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board">
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
					<c:if test="${vo.depth == 0  }">				
						<tr>
							<td>${vo.groupNo }</td>
							<td><a href="${pageContext.request.contextPath }/board/view/${vo.no}">${vo.title }</a></td>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test="${authUser.name eq vo.name }">
									<a href="${pageContext.request.contextPath }/board/delete/${vo.no }" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
						</c:if>
						<tr>
						<c:if test="${vo.depth == 1  }">
							<td>
								<img src='${pageContext.request.contextPath }/assets/images/reply.png' />
							</td>
							<td>
								<a href="board/view/${vo.no }">re: ${vo.title }</a>
							</td>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>	
							<td>
								<c:if test="${authUser.name eq vo.name }">
									<a href="${pageContext.request.contextPath }/board/delete/${vo.no }" class="del">삭제</a>
								</c:if>
							</td>
							</c:if>			
						</tr>
						<tr>
						<c:if test="${vo.depth > 1  }">
							<td style="text-align: right; padding=left: 20px">
								<img src='${pageContext.request.contextPath }/assets/images/reply.png' />
							</td>
							<td>
								<a href="board/view/${vo.no }">re: ${vo.title }</a>
							</td>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>	
							<td>
								<c:if test="${authUser.name eq vo.name }">
									<a href="${pageContext.request.contextPath }/board/delete/${vo.no }" class="del">삭제</a>
								</c:if>
							</td>
						</c:if>
						</tr>
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
							<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
						</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>