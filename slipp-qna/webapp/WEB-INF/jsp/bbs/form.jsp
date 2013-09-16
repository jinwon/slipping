<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<title>BBS 자료실</title>
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
	
	function Check_Form()
	{
		document.insert_frm.submit();
	}
	
	function Reset_form()
	{
		document.insert_frm.reload();
	}
	
	function show_bbs_list()
	{
		document.location.href = "/bbs/";
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
	        </ul>
	    </div>	    
	</div>
	
	<div id="body_data" class="ui-layout-center" style="width:100%;min-width:750px;">
		<c:if test="${empty bbs.bbsId}">
			<c:set var="method" value="post" />
			<c:set var="taction" value="/bbs/" />
		</c:if>
			
		<c:if test="${not empty bbs.bbsId}">
			<c:set var="method" value="put" />
			<c:set var="taction" value="/bbs/${bbs.bbsId}/update" />
		</c:if>
		
		<c:if test="${param.upload == 'fail' }">
			파일 업로드 오류.
		</c:if>
		
		<form:form name="insert_frm" modelAttribute="bbs" action="${taction}" method="${method}" enctype="multipart/form-data">
			<form:hidden path="bbsId"/>
			<table id="user-th-beauty" class="use-table">
				<tr>
					<td class="styleth conf_row_top" width="80">제목</td>
					<td class="conf_row_top"><form:input path="title" size="70" style="line-height:1.5;" /></td>
				</tr>
				<tr>
					<td class="styleth" width="80">내용</td>
					<td style="line-height:1.5;"><form:textarea path="contents" rows="5" cols="100"></form:textarea></td>
				</tr>
			<c:if test="${empty bbs.bbsId}">
				<tr>
					<td class="styleth">파일</td>
					<td style="line-height:1.5;"><input name="file" type="file"></td>
				</tr>
			</c:if>
			
			<c:if test="${not empty bbs.bbsId}">
				<tr>
					<td class="styleth">파일</td>
					<td style="line-height:1.5;">${bbs.fileName} <a href="#" onClick="">파일 삭제</a></td>
				</tr>
			</c:if>
			
			</table>
			
			<div class="ribon_area" style="float:left; width:100%; padding: 5px 0 0 8px; margin: 0px;">
			    <ul class="ribon_menu">
			<c:if test="${not empty bbs.bbsId}">
			        <li><div class="btn_address" id="btn_update" name="btn_update" onClick="Check_Form();" style="margin-left:180px;"><b>수정</b></div></li>
			</c:if>

			<c:if test="${empty bbs.bbsId}">
			        <li><div class="btn_address" id="btn_update" name="btn_update" onClick="Check_Form();" style="margin-left:180px;"><b>추가</b></div></li>
			</c:if>
			
			        <li>&nbsp;</li>
			        <li><div class="btn_mail" id="btn_cancel" name="btn_cancel" onClick="Reset_form();"><b>취소</b></div></li>
			    </ul>
			</div>	
		</form:form>
	</div>
</body>
</html>

<script>
    load_layout();
</script>