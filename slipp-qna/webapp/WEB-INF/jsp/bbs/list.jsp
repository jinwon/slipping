<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tags.jspf"%>
<%@ taglib prefix="qna" tagdir="/WEB-INF/tags/qnas"%>
<!DOCTYPE html>
<html>
<head>
<title> 자료실 </title>
</head>
<body>
	<div id="main">
		<table>
			<tr>
				<td height="400" valign="top">
					<table>
						<tr>
							<td width="400">제목</td>
							<td width="150">생성일</td>
						</tr>
						<c:forEach items="${bbs.content}" var="each">
							<tr>
								<td width="400"><a href="/bbs/${each.bbsId}">${each.title}</a></td>
								<td width="150"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${each.createdDate}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td style="height:30px;" colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td style="height:30px;" colspan="3">&nbsp;페이지1 : </td>
						</tr>
						<tr>
						  <td colspan="3">
					        <!--페이지영역 -->
					            <sl:pager
					                prefixURI="/bbs"
					                pageHtml='<a href="{url}">{page}</a>'
					                currentPageHtml='<a href="#" class="active">{page}</a>'
					                pageSeparator=" "
					                pagesWrapHtmlHead='<span class="paging">'
					                pagesWrapHtmlTail='</span>'
					                prevGroupHtml=' <a href="{url}" class="page_prev">이전</a> '
					                nextGroupHtml=' <a href="{url}" class="page_next">다음</a> '
					                pageGroupSize="10"
					                page="${bbs}"
					                suffixURI="&sort=1" />
					        <!--페이지영역 -->						  
						  </td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<br /> <a href="/bbs/form">질문하기</a>
	</div>
</body>
</html>