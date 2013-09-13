<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/tags.jspf"%>
<%@ taglib prefix="qna" tagdir="/WEB-INF/tags/qnas"%>
<!DOCTYPE html>
<html>
<head>
<title> 자료실 </title>
<script>
	function load_layout()
	{
	     innerLayout = jQuery('#content_body').layout({
	        applyDefaultStyles: true,  
	        north__applyDefaultStyles: false, 
	        north__resizable: false,
	        north__closable: false,
	        north__spacing_open: 0,
	        north__size: 87
	    });
	
	    innerLayout.panes.north.css({
	        'margin':'0px',
	        'border':'0px',
	        'spacing':'0px',
	        'padding':'0px'
	    });
	
	    innerLayout.panes.center.css({
	        'margin-left':'0px',
	        'margin-top':'0px',
	        'border':'0px',
	        'spacing':'0px',
	        'padding':'0px'
	    });
	}
	
	function show_bbs_add()
	{
		document.location.href = "/bbs/form";
	}
</script>
</head>
<body>

	<div class="ui-layout-north" onmouseover="innerLayout.allowOverflow('north');" onmouseout="innerLayout.resetOverflow(this)" style="padding:0px;margin:0px;min-width:700px;">
	    <div class="title_box">
	        <div class="mail_list_title">
	            <a href="#">자료실</a>
	        </div>
	    </div>

		<!-- 메뉴  -->	
	    <div class="ribon_area" style="padding: 5px 0 0 8px; margin: 0px;">
	        <ul class="ribon_menu">
	            <li>&nbsp;</li>
	            <li><div class="btn_mail" onClick="show_bbs_add();"><b>신규추가</b></div></li>
	        </ul>
	    </div>
	</div>

	<div id="body_data" class="ui-layout-center" style="width:100%;min-width:750px;">
	    <fieldset style="border: 0;">
	    <div>
			<table cellpadding="0" cellspacing="0" class="mailList_l" style="min-width:700px;">
				<colgroup>
                  <col />
                  <col width="140" />
                  <col width="10" />
              	</colgroup>
              	<thead>	
				<tr>
					<th class="hLine">제목</th>
					<th class="hLine">생성일</th>
					<th class="hLine"></th>
				</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${bbs.content}" var="each">
						<tr>
							<td height="22px" style="padding-right:10px"><a href="/bbs/${each.bbsId}">${each.title}</a></td>
							<td class="nums"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${each.createdDate}" /></td>
							<td class="nums"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div style="text-align:center;width:80%;">
				<table style="width:100%;align:center;margin-top:10px;">
					<tr>
					  <td>
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
			</div>
		</fieldset>
		
		
	    <div class="ribon_area" style="padding: 5px 0 0 8px; margin: 0px;">
	        <ul class="ribon_menu">
	            <li>&nbsp;</li>
	            <li><div class="btn_mail" onClick="show_bbs_add();"><b>신규추가</b></div></li>
	        </ul>
	    </div>
	    					
	</div>
</body>
</html>

<script>
    load_layout();
</script>