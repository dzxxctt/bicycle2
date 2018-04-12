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
    <title>登录</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/pintuer.js"></script>  
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <s:form action="WebsiteAction_login.action" method="post" namespace="/">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
                
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                        	<s:textfield cssClass="input input-big" name="telphone" placeholder="登录账号" data-validate="required:请填写账号"></s:textfield>
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                        	<s:password name="password" cssClass="input input-big" placeholder="登录密码" data-validate="required:请填写密码"></s:password>
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input input-big" name="code" placeholder="填写右侧的验证码" />
<!--                            <img src="images/passcode.jpg" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">  
 -->                            <img src="${pageContext.request.contextPath}/WebsiteAction_valideCode.action"  
                                    alt="" width="100" height="32" class="passcode" name="checkImg" id="checkImg"  
                                    style="height:43px;cursor:pointer;"  
                                    onClick="document.getElementById('checkImg').src='${pageContext.request.contextPath}/WebsiteAction_valideCode.action?timestamp='+ (new Date().getTime().toString(36)); return false" />                         
                        </div>
                    </div>
                </div>
                <div style="padding-left:10px;">
                	<font color="red">
                     	<s:actionerror/>
                	</font>
                 </div>
                <div style="padding:30px;">
                	<s:submit value="登录" cssClass="button button-block bg-main text-big input-big"></s:submit>
                </div>
            </div>
            </s:form>          
        </div>
    </div>
</div>

</body>
</html>