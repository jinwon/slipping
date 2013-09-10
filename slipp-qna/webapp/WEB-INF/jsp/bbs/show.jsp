<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><%@
taglib prefix="qna" tagdir="/WEB-INF/tags/qnas"%>
<!DOCTYPE html>
<html>
<head>
<title>자료실</title>
</head>
<body>
	<div id="main">
		<table>
            <tr>
                <td height="400" valign="top">
                    <table border="1">		      
			            <tr>
			                <td>제목</td>
			                <td width="650">${bbs.title}</td>
			            </tr>
			            <tr>
			                <td>내용</td>
			                <td>${sf:br(bbs.contents)}</td>
			            </tr>
                    </table>
                </td>
            </tr>		
		</table>

		<br /> <a href="/bbs">목록으로</a>&nbsp;&nbsp;<a href="/bbs/${bbs.bbsId}/form">수정하기</a>
	</div>
</body>
</html>