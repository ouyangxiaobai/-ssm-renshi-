<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/login/css/login-1.0.css">
    <script src="<%=path%>/login/js/jquery-1.11.2.min.js"></script>
    <script src="<%=path%>/login/js/layer.js"></script>
    <style>
        #admintips { margin:0 auto; margin-top:13px; text-align:center; font-size:13px; color:#666; border:1px dashed #ddd; padding:4px 15px; display:none; }
        #findpwd{ cursor:pointer; }
    </style>
</head>
<body>
<link rel="stylesheet" type="text/css" href="<%=path%>/login/css/llq.css">
<script>
    $(function(){
        if (!$.support.leadingWhitespace) {
            $("#browser_ie").show();
            $(".logo_box").hide();
        }
    })
</script>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form method="post" action="<%=path%>/registerSub">
            <h1>注册</h1>
            <div class="social-container"> <span>请输入正确信息</span> </div>
            <input name="username" type="text" value="" placeholder="账号">
            <input name="password" type="password" value="" placeholder="密码">
            <input name="phone" type="text" value="" placeholder="手机号">
            <button class="logindo" type="submit">注 册</button>
            <div style="text-align: center;text-align: center;font-size: smaller;">已有账号？去<a href="<%=path%>/gologin" >登录</a></div>
            <span style="margin-top:10px;">${msg}</span>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <h1>欢迎回来<span style="display:none;">ysb_2.3.2</span></h1>
                <p>人事管理系统</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>