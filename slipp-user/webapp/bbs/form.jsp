<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 게시판</title>

<%@ include file="../commons/_header.jspf"%>

</head>
<body>
	<%@ include file="../commons/_top.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
				
				<c:if test="${ empty bbs.bbsIdx}">		
					<h1>새 글쓰기</h1>
					<c:set var="forwardUrl" value="/bbs/insert" />
				</c:if>	

				<c:if test="${not empty bbs.bbsIdx}">		
					<h1>수정하기</h1>
					<c:set var="forwardUrl" value="/bbs/${bbs.bbsIdx}" />
				</c:if>	
				
				</div>
				
				<form class="form-horizontal" action="${forwardUrl}" method="post">
				
				<c:if test="${not empty bbs.bbsIdx}">		
					<input type="hidden" name="bbsIdx" id="bbsIdx" value="${bbs.bbsIdx}">
				</c:if>	

					<div class="control-group">
						<label class="control-label" for="userId">아이디</label>
						<div class="controls">
							<c:choose>
							<c:when test="${empty user.userId}">
							<input type="text" id="userId" name="userId" value="${user.userId}" placeholder="">
							</c:when>
							<c:otherwise>
							<input type="hidden" name="userId" value="${user.userId}" />
							${user.userId}
							</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<input type="password" id="bbsPassword" name="bbsPassword" placeholder="">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="subject">제목</label>
						<div class="controls">
							<input type="text" id="subject" name="subject" value="${bbs.subject}" placeholder="">
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="content">내용</label>
						<div class="controls">
							<textarea id="content" name="content" rows="10" cols="300" >${bbs.content}</textarea>
						</div>
					</div>
					
					
					<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<div class="controls">
							${errorMessage}
						</div>
					</div>
					</c:if>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">글쓰기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>