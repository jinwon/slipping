<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<title>BBS 자료실</title>
</head>
<body>
	<div id="main">
		<c:set var="method" value="post" />
		<c:if test="${not empty bbs.bbsId}">
		<c:set var="method" value="put" />
		</c:if>
		
		<c:if test="${param.upload == 'fail' }">
			파일 업로드 오류.
		</c:if>
		
		<form:form modelAttribute="bbs" action="/bbs" method="${method}" enctype="multipart/form-data">
			<form:hidden path="bbsId"/>
			<table>
				<tr>
					<td width="80">제목</td>
					<td><form:input path="title" size="70"/></td>
				</tr>
				<tr>
					<td width="80">내용</td>
					<td><form:textarea path="contents" rows="5" cols="130"></form:textarea></td>
				</tr>
				<tr>
					<td>파일</td>
					<td><input name="file" type="file"></td>
				</tr> 
			</table>
			<input type="submit" value="질문하기" />		
		</form:form>
	</div>
</body>
</html>