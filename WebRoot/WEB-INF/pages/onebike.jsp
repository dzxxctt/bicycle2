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
    <div class="panel-head"><strong class="icon-reorder"> 车锁信息</strong></div>
    <div class="padding border-bottom">
        
        	<form name="myForm1" namespace="/">
    		  <ul class="search">
        		<li>
          		<input type="text" placeholder="请输入车锁id" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
         		 <s:submit value="搜搜" onclick="return submitAction()" cssClass="button border-main icon-search" theme="simple"><font color="red"> <s:actionerror /></font></s:submit>
	       	 	</li>
		      </ul>
        	</form>
    </div>
<form method="post" action="BikeAction_findAllBikes.action" name="myForm">
    <table class="table table-hover text-center">
      <tr>
        <th>车锁id</th>       
        <th>是否在用车</th>
        <th>最近一次使用者</th>
        <th>经度</th>
        <th>纬度</th>
        <!--  <th width="120">留言时间</th> -->
        <th>地址</th>       
      </tr>  
      <s:if test="oneBike ! null">
        <tr>
          <td><s:property value="bluetoothId"/></td>
          <td><s:property value="bikeStatus == 1 ? '是' : '否'"/></td>
          <td><s:property value="telphone"/></td>  
           <td><s:property value="longitude"/></td>         
          <td><s:property value="latitude"/></td>
          <td><s:property value="address"/></td>
          <!-- <td>2016-07-01</td> -->
        </tr>
      </s:if>
    </table>
  </div>
</form>
<script type="text/javascript">

function submitAction(){
	document.myForm1.action = "BikeAction_findByBluetoothId.action";
	return ture;
}
/*function del(id){
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