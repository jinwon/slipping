<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
<%@page import="java.util.*" %>	
	
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
					<h1>게시판</h1>
				</div>
				
				게시판 목록을 표시합니다.
<%
	//Date date = new Date();
%>				
<c:set var="t_date" value="<%= new Date() %>" />

<%= new Date().toString() %>
				<div>
					오늘 일자 : <fmt:formatDate value="${t_date}" type="both" pattern="yyyy/MM/dd_HH:mm:ss" />
				</div>
				
			</div>
			
			<div>
				<a href="/bbs/form/${user.userId}">새 글쓰기 </a>
			</div>
			
		</div>
	</div>
</body>
</html>