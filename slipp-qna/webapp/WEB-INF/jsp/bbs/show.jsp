<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><%@
taglib prefix="qna" tagdir="/WEB-INF/tags/qnas"%>
<!DOCTYPE html>
<html>
<head>
<title>자료실</title>
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
	
	function show_bbs_list()
	{
		document.location.href = "/bbs/";
	}

	function show_bbs_modify(bbsid)
	{
		document.location.href = "/bbs/" + bbsid + "/form";
	}

	function show_bbs_add()
	{
		document.location.href = "/bbs/form";
	}
	
	function show_bbs_delete(bbsid)
	{
		if (confirm("삭제하시겠습니까?"))
		{
			document.location.href = "/bbs/" + bbsid + "/delete";	
		}	
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
	    
		<div class="ribon_area" style="padding: 5px 0 0 8px; margin: 0px;">
	        <ul class="ribon_menu">
	            <li>&nbsp;</li>
	            <li><div class="btn_mail" onClick="show_bbs_list();"><b>목록</b></div></li>
	            <li><div class="btn_mail" onClick="show_bbs_modify('${bbs.bbsId}');"><b>수정</b></div></li>
	            <li><div class="btn_mail" onClick="show_bbs_add();"><b>신규추가</b></div></li>
	        </ul>
	    </div>	    
	</div>

	<div id="body_data" class="ui-layout-center" style="width:100%;min-width:750px;">	
		<table id="user-th-beauty" class="use-table">	 
			<tr class="styleth conf_row_top" width="80"> 
				<td class="styleth conf_row_top" width="80">제목</td>
				<td class="conf_row_top" width="650"><span style="line-height:1.5;" >${bbs.title}</span></td>
			</tr>
			<tr>
			    <td colspan="2" class="styleth">내용</td>
			</tr>
			<tr>
			    <td colspan="2">
			    	<div style="height:150px;padding:5px;">${sf:br(bbs.contents)}</div>
			    </td>
			</tr>
			<tr>
				<td class="styleth" width="80">파일명</td>
				 <td> <a href="/bbs/${bbs.bbsId}/download">${sf:br(bbs.fileName)}</a> </td>
			</tr>
		</table>

		<!-- 메뉴  -->	

		<div class="ribon_area" style="padding: 5px 0 0 8px; margin: 0px;">
	        <ul class="ribon_menu">
	            <li>&nbsp;</li>
	            <li><div class="btn_mail" onClick="show_bbs_list();"><b>목록</b></div></li>
	            <li><div class="btn_mail" onClick="show_bbs_modify('${bbs.bbsId}');"><b>수정</b></div></li>
	            <li><div class="btn_mail" onClick="show_bbs_delete('${bbs.bbsId}');"><b>삭제</b></div></li>
	            <li>&nbsp;</li>
	            <li><div class="btn_mail" onClick="show_bbs_add();"><b>신규추가</b></div></li>
	        </ul>
	    </div>
	</div>
</body>
</html>