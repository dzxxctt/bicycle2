<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${pageContext.request.contextPath}/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l">
  		<a href="WebsiteAction_updatePasswordUI.action" class="button button-little bg-blue" target="right"><span class="icon-wrench"></span> 更改密码</a> &nbsp;&nbsp;
  		<a class="button button-little bg-red" href="WebsiteAction_logout.action"><span class="icon-power-off"></span> 退出登录</a> 
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <%-- <h2><span class="icon-user"></span>基本设置</h2> --%>
  <h2><a href="WebsiteAction_findAllUsers.action" target="right"><span class="icon-user"></span> 用户信息</a></h2>
  <h2><a href="BikeAction_findAllBikes.action" target="right"><span class="icon-pencil-square-o"></span>车锁信息</a></h2>
  <h2><a href="BikeAction_mapUI.action" target="right"><span class="icon-pencil-square-o"></span>地图</a></h2>
</div>
<%-- <script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script> --%>
<ul class="bread">
  <li><div id="show"></div>
  	<script type="text/javascript"> 
  	window.onload = function(){
  		showTime();
  		}
  		function checkTime(i)
  		{
  		if (i<10) 
  		  {i="0" + i}
  		  return i
  		}
  		function showTime()
  		{
  		var now=new Date();
  		var year=now.getFullYear();
  		var month=now.getMonth()+1;
  		var day=now.getDate();
  		var h=now.getHours();
  		var m=now.getMinutes();
  		var s=now.getSeconds();
  		m=checkTime(m)
  		s=checkTime(s)

  		var weekday=new Array(7)
  		weekday[0]="星期日"
  		weekday[1]="星期一"
  		weekday[2]="星期二"
  		weekday[3]="星期三"
  		weekday[4]="星期四"
  		weekday[5]="星期五"
  		weekday[6]="星期六"

  		document.getElementById("show").innerHTML=""+year+"年"+month+"月"+day+"日 "+weekday[now.getDay()]+h+":"+m+":"+s;
  		t=setTimeout('showTime()',500)
  		}
	</script> 
  </li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0"  name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>