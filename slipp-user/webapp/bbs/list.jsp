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

<style>
  a {text-decoration:none;}
  a:hover {text-decoration:none;}
  
</style>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>게시판</h1>
				</div>
				
				<div style="font-size:12px;">
					게시판 목록을 표시합니다.
				</div>
				
				<div style="font-size:12px;">
				
				<c:if test="${lists == null}">
					게시된 글이 없습니다.
				</c:if>
				
				전체 글 갯수 :  ${count} <br>

				<c:if test="${count > 0}">

				<table style="width:100%; border:1px solid #999999;">
					<tr style="background:#EEEEEE;border:1px solid #999999;">
						<th style="width:100px;">작성자</th>
						<th style="width:300px;">제목</th>
						<th >내용</th>
						<th style="width:100px;">작성일 </th>
					</tr>

				<c:forEach var="bbs"  items="${lists}">
					<tr style="background:white;border:1px solid #cccccc;">
						<td style="text-align:center;"><c:out value="${bbs.userId}" /></td>
						<td><a href="/bbs/${bbs.bbsIdx}"><c:out value="${bbs.subject}" /></a></td>
						<td><c:out value="${bbs.content}" /></td>
						<td style="text-align:center;"><c:out value="${bbs.writeDate}" /></td>
					</tr>	
				</c:forEach>
				
				</table>
				</c:if>				
								
				</div>


			<div style="margin-top:10px; font-size:12px;">
				<a href="/bbs/form">새 글쓰기 </a>
			</div>
				
			</div>
			

			
		</div>
	</div>
</body>
</html>