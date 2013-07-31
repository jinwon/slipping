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
  
  table {font-family:"굴림", Guilm; "돋음", Dotum;font-size:12px; }
  tr { padding:3px; }
  td { padding-left:5px;}

</style>
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>게시판</h1>
				</div>
				
				
				<div style="font-size:12px;">
				
				<table style="width:100%; border:1px solid #999999;padding:2px;">
					<tr style="background:#EEEEEE;border:1px solid #999999;">
						<th style="width:150px;">작성자</th>
						<td style="background:white;"> <c:out value="${bbs.userId}" /></td>
					</tr>

					<tr style="background:#EEEEEE;border:1px solid #999999;">
						<th>작성일</th>
						<td style="background:white;"> <c:out value="${bbs.writeDate}" /></td>
					</tr>
					
					<tr style="background:#EEEEEE;border:1px solid #999999;">	
						<th>제목</th>
						<td style="background:white;"><c:out value="${bbs.subject}" /></td>
					</tr>

					<tr style="background:#EEEEEE;border:1px solid #999999;">	
						<th colspan="2">내용</th>
					</tr>

					<tr style="background:#EEEEEE;border:1px solid #999999;">
						<td style="background:white;heigth:200px;line-height:1.5;padding-top:5px;padding-left:10px;" colspan="2">
							${bbs.content}
						</td>											
					</tr>
				
				</table>
								
				</div>


			<div style="margin-top:10px; font-size:12px;">
				<div style="float:left;display:inline-block;">
	 				<div class="controls">
						<a href="/bbs/form"><button type="submit" class="btn btn-primary">새 글쓰기 </button></a>
						
						<a href="/bbs/${bbs.bbsIdx}/form"><button type="submit" class="btn btn-primary">수정하기</button></a>
					</div>
					
	
				</div>

			</div>
				
			</div>
			

			
		</div>
	</div>
</body>
</html>