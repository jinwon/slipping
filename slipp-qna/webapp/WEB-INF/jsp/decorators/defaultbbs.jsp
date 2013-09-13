<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"
%><%@ include file="/WEB-INF/jsp/include/tags.jspf" 
%><!DOCTYPE html>
<html lang="ko">
  <head>
    <title><decorator:title default="게시판" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<script type="text/javascript" src="/resources/javascripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="/resources/javascripts/jquery-ui-1.8.11.custom.min.js"></script>
	<script type="text/javascript" src="/resources/javascripts/jquery.layout-latest.min.js"></script>
	
	<link rel="stylesheet" media="screen"  href="/resources/css/mailnara.css">
	<link rel="stylesheet" media="screen"  href="/resources/css/template_blue.css">
	<link rel="stylesheet" media="screen"  href="/resources/css/smoothness/jquery-ui-1.8.16.custom.css">

	<script language="JavaScript">
        jQuery.noConflict();

        jQuery(document).ready(function(){
	
            outerLayout = jQuery('body').layout({ 
                applyDefaultStyles: true,  
                north__applyDefaultStyles: false, 
                north__resizable: false,
                north__closable: false,
                north__spacing_open: 0,
                north__size: 43,
                south__applyDefaultStyles: false, 
                south__closable: false,
                south__spacing_open: 0,
                south__resizable: false,
                south__size: 28,

                //고정시 사용
                west__applyDefaultStyles: false, 
                west__resizable: false,
                west__spacing_open: 0,
                west__spacing_closed: 10,
                west__size: 220,
                west__resizerTip: "왼쪽 메뉴의 위치를 조절합니다",
                west__togglerTip_open:	"왼쪽 메뉴를 감춥니다",
                west__togglerTip_closed: "왼쪽 메뉴를 펼칩니다.",
                west__sliderTip : "왼쪽 메뉴를 상단으로 보여줍니다.",
                south__togglerTip_open: "하단 메뉴를 감춥니다.",
                south__togglerTip_closed: "하단 메뉴를 펼칩니다.",
                south__sliderTip : "하단 메뉴를 상단으로 보여줍니다."
            });			
        });
 	</script>
 	
	<style>
	.list_menu .menu_top h3 { cursor: default; font-size: 14px; height: 30px; padding:11px 0 0 0px; position: relative; vertical-align:middle; line-height:24px;}
	.list_menu .menu_top ul li { vertical-align: middle; line-height:24px; padding:6px 0 4px 13px;}
	.list_menu .menu_top ul li:hover { background-color: #F1F1F1; }
	.list_menu .menu_top span.icon { background-image: url("/images/2013/config_icons.png"); background-repeat: no-repeat;display:inline-block; float: left; margin: 0px 5px 5px 8px; width:20px; height:24px;}
	.list_menu .config_icon { background-position: 0px 0px; }
	.list_menu .userconfig_icon { background-position: -30px 0px; }
	.list_menu .mailconfig_list_icon { background-position: 0 -30px; }
	.list_menu .spamconfig_list_icon { background-position: -30px -30px; }
	.list_menu .menu_top ul li a {background-image:none;padding-left:0px;}
	</style>
 	
    <decorator:head />
  </head>
  <body style="padding:0px;margin-top:0px;">
        <div class="ui-layout-north" onmouseover="outerLayout.allowOverflow('north');" onmouseout="outerLayout.resetOverflow(this);" style="padding:0px;margin-top:0px;">
        	<div id ="di-top" style="margin-top:0px;padding-top:0px;">
            <div id="header">
                <a href="/" style="color:white;font-size:14px;">게시판</a>
                
	            <div id="login_area" style="float:right;margin-right:10px;">
                    <sec:authorize access="!hasRole('ROLE_USER')">
                    <a href="/security/form" style="color:white;">로그인</a>
                    <a href="/user/form" style="color:white;">회원가입</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                    <a href="/security/logout" style="color:white;">로그아웃</a>
                    </sec:authorize>                    
	            </div>
			</div>
			</div>
        </div>
            	
        <div id="left_menu" class="ui-layout-west" style="width:100%;height:100%;display:inline-block;background-color:#f2f2f2;overflow:hidden;overflow-x:hidden;overflow-y:hidden;">
        	<div id="mail_nav" style="left:0px; margin-left:0px;">
        		
        		<div stype="margin-top:10px;text-align:center;width:100%;height:80px;">
        			&nbsp;
        		</div>
        		
        		<div style="width:100%; height:10px; border-bottom: 1px solid #c2c2c2"></div>
        		
        		<div class="list_menu" style="padding-bottom:5px;">
	        		<div class="menu_top">
	        			<h3>
							<span class="icon"></span> <a href="/bbs/">자료실</a>
						</h3>
						<div style="width:100%; height:10px; border-bottom: 1px solid #c2c2c2"></div>
	        		</div>
	        		<ul class="menu_list">
	        			<li><span class="icon userconfig_icon"></span><a href="#">list 1</a></li>
	        			<li><span class="icon userconfig_icon"></span><a href="#">list 2</a></li>
	        			<li><span class="icon userconfig_icon"></span><a href="#">list 3</a></li>
	        		</ul>
	        	</div>
        	</div>
        </div>
       
       <div id="content-body" class="ui-layout-center">     
 	        <decorator:body/>
        </div>
        
		<div id="di_footer" class="ui-layout-south">
        	<%@ include file="/WEB-INF/jsp/include/footerbbs.jspf" %> 
		</div> 
  </body>
</html>