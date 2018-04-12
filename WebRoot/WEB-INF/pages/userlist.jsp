<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/pintuer.js"></script>  
</head>
<body>
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 用户信息</strong></div>
    <div class="padding border-bottom">
      <form name="myForm1" namespace="/">
    		  <ul class="search">
        		<li>
          		<input type="text" placeholder="请输入用户电话" name="keyword" class="input" style="width:250px; line-height:17px;display:inline-block" />
         		 <s:submit value="搜搜" onclick="return submitAction()" cssClass="button border-main icon-search" theme="simple">
         		 </s:submit>
	       	 	</li>
		      </ul>
        	</form>
    </div>
<form method="post" action="WebsiteAction_findAllUsers.action" >
<%--  隐藏域 存放当前页 --%>
<s:hidden id="pageNum" name="pageNum"></s:hidden>
    <table class="table table-hover text-center">
      <tr>
        <th>用户电话</th>       
        <th>是否在用车</th>
        <th>最近用车费用</th>
        <th>最近使用哪辆车</th>
        <th width="25%">最后一次用车时间</th>
        <!--  <th width="120">留言时间</th> -->
      </tr>  
      	<s:iterator value="#pageBeanUsers.data" var="user">
      	<s:if test="#user.type == 0">
        <tr>
          <td><s:property value="#user.telphone"/></td>
          <td><s:property value="#user.userStatus == 1? '是' : '否'"/></td>
          <td><s:property value="#user.cost"/>元</td>  
           <td><s:property value="#user.bluetoothId"/></td>         
          <td><s:property value="#user.endTime"/></td>
        </tr>
      	</s:if>
      	</s:iterator>    
      <tr>
        <td colspan="8"><div class="pagelist"> 
        	<span>第<s:property value="#pageBeanUsers.pageNum"/>/<s:property value="#pageBeanUsers.totalPage"/>页</span>
        	<s:if test="#pageBeanUsers.pageNum > 1">
        		<a href="javascript:void(0)" onclick="showPage(1)">首页</a>
        		<a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBeanUsers.pageNum - 1"/>)">上一页</a>
        	</s:if>
        	<!-- 动态显示条 -->
        	<s:iterator begin="#pageBeanUsers.start" end="#pageBeanUsers.end" var="num">
        		<a href="javascript:void(0)" onclick="showPage(<s:property value="#num"/>)"><s:property value="#num"/></a>
        	</s:iterator>
        	<s:if test="#pageBeanUsers.pageNum lt #pageBeanUsers.totalPage">
        		<a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBeanUsers.pageNum + 1"/>)">下一页</a>
        		<a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBeanUsers.totalPage"/>)">尾页</a> 
        	</s:if>
        	</div>
        </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

function showPage(num){
	//1修改隐藏域的值
	document.getElementById("pageNum").value = num;
	//2提交表单
	document.forms[1].submit();
}
function submitAction(){
	document.myForm1.action = "WebsiteAction_findByTelphone.action";
	return ture;
}
/* function del(id){
	if(confirm("您确定要删除吗?")){
		
	}
}

$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false; 		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
} */
</script>
</body></html>