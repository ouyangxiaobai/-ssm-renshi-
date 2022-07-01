<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>个人信息</title>
    <link rel="icon" href="<%=path%>/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="<%=path%>/css/font.css">
    <link rel="stylesheet" href="<%=path%>/css/xadmin.css">
    <link rel="stylesheet" href="<%=path%>/css/pg_btn.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
    <script src="<%=path%>/lib/layui/layui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
</head>
<body>
<div class="x-body">
    <form class="layui-form"  id="f_auto" onsubmit="return false" action="##" method="post" >
        <input type="hidden" value="${sessionScope.ad.id}" name="id" id="id"/>

        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="">用户名</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="username1" name="username"  disabled
                       autocomplete="off" value="${sessionScope.ad.username}" class="layui-input">
                <input type="hidden" id="username" name="username"  value="${sessionScope.ad.username}" class="layui-input">
            </div>
        </div>

        <c:if test="${sessionScope.type == '03'}">
            <div class="layui-form-item">
                <label for="phone" class="layui-form-label">
                    <span class="">部门</span>
                </label>
                <div class="layui-input-inline">
                    <select name="did" id="did"  lay-filter="demo" disabled>
                        <c:forEach items="${dLists}" var="d">
                            <option value="${d.id}">${d.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="phone" class="layui-form-label">
                    <span class="">岗位</span>
                </label>
                <div class="layui-input-inline">
                    <select name="pid" id="pid"  lay-filter="demo" disabled>
                        <c:forEach items="${pLists}" var="p">
                            <option value="${p.id}">${p.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="">真实姓名</span>
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="realname" name="realname"
                           autocomplete="off" value="${sessionScope.ad.realname}" class="layui-input">
                </div>
            </div>

        <div class="layui-form-item">
            <label for="phone" class="layui-form-label">
                <span class="">性别</span>
            </label>
            <div class="layui-input-inline">
                <select name="sex" id="sex"  lay-verify="required">
                    <option value="" selected></option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="phone" class="layui-form-label">
                <span class="">电话</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="phone" name="phone" lay-verify="phone"
                       autocomplete="off" value="${sessionScope.ad.phone}" class="layui-input">
            </div>
        </div>
        </c:if>


        <div class="layui-form-item">
            <label for="password" class="layui-form-label">
                <span class="">原密码</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="password" name="password" lay-verify="password"
                       autocomplete="off" value="" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="passwords" class="layui-form-label">
                <span class="">修改后密码</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="passwords" name="passwords" lay-verify="password"
                       autocomplete="off" value="" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item" style="margin-left: 131px;" id="btn_xg">
            <button  class="layui-btn" onclick="sub()">修改</button>
            <button  class="layui-btn" onclick="x_admin_close()">关闭</button>
        </div>
    </form>
</div>
<script>
    $('#close').click(function() {
        //window.location.href='/findBook';
        return false;
    });
    function sub() {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "<%=path%>/updateInfoAdmin" ,//url
            data: $('#f_auto').serialize(),
            success: function (result) {
                if (result == 202) {
                    layer.msg('用户身份过期，请重新登录', {icon: 2, time: 3000});
                };
                if (result == 201) {
                    layer.msg('原密码错误', {icon: 2, time: 3000});
                };
                if (result == 200) {
                    layer.msg('修改成功，请退出后重新登录', {icon: 1, time: 3000});
                };
                if (result == 204) {
                    layer.msg('原密码不能为空', {icon: 2, time: 3000});
                };
                if (result == 203) {
                    layer.msg('用户名不能为空', {icon: 2, time: 3000});
                };
                if (result == 205) {
                    layer.msg('手机号格式错误', {icon: 2, time: 3000});
                };
            },
            error : function() {
                alert("异常！");
            }
        });
    }
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var form = layui.form;
        form.verify({
            username: function (value, item) {
                //value：表单的值、item：表单的DOM对象
                if (!new RegExp("^(.+){4,18}$").test(value)) {
                    return '用户名长度必须为4-18位'
                }
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if (/^\d+\d+\d$/.test(value)) {
                    return '用户名不能全为数字';
                }
            },
            password: [
                /^[\S]{6,18}$/,
                '密码必须6到12位，且不能出现空格'
            ],
            confirm_password: function (value) {
                if (!new RegExp("^[\\S]{6,18}$").test(value)) {
                    return '密码长度必须为6-18位'
                }
                if (value !== $('#login-password').val()) {
                    return '两次输入的密码不一致';
                }
            }
        });
    });
</script>
</body>
</html>