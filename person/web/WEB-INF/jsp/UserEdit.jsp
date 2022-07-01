<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>修改信息</title>
    <link rel="icon" href="<%=path%>/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="<%=path%>/css/font.css">
    <link rel="stylesheet" href="<%=path%>/css/xadmin.css">
    <link rel="stylesheet" href="<%=path%>/css/pg_btn.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
    <script src="<%=path%>/lib/layui/layui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
    <style>
        .layui-form-item .layui-input-inline {
            width: 400px !important;
        }
    </style>
</head>
<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
       <a href="">首页</a>
       <a href="<%=path%>/findUser">查询列表</a>
       <a href="">修改</a>
      </span>
</div>
<div class="x-body">
    <form class="layui-form"  id="f_auto" action="<%=path%>/updateUser" method="post" >
        <input type="hidden" value="${user.id}" name="id" id="id"/>

        <div class="layui-form-item">
            <label for="realname" class="layui-form-label">
                <span class="">选择部门</span>
            </label>
            <div class="layui-input-inline">
                <select name="did" id="did"  lay-verify="required" disabled>
                    <c:forEach items="${dLists}" var="d">
                        <option value="${d.id}">${d.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="realname" class="layui-form-label">
                <span class="">选择岗位</span>
            </label>
            <div class="layui-input-inline">
                <select name="pid" id="pid"  lay-verify="required" disabled>
                    <c:forEach items="${pLists}" var="p">
                        <option value="${p.id}">${p.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="">用户名</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="username" lay-verify="required" disabled
                       autocomplete="off" value="${user.username}" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="realname" class="layui-form-label">
                <span class="">真实姓名</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="realname" name="realname" lay-verify="required"
                       autocomplete="off" value="${user.realname}" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="password" class="layui-form-label">
                <span class="">密码</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="password" name="password" lay-verify="password"
                       autocomplete="off" value="${user.password}" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="sex" class="layui-form-label">
                <span class="">性别</span>
            </label>
            <div class="layui-input-inline">
                <select name="sex" id="sex"  lay-verify="required">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <label for="phone" class="layui-form-label">
                <span class="">手机号</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="phone" name="phone" lay-verify="phone"
                       autocomplete="off" value="${user.phone}" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" id="btn_xg">
            <button  class="layui-btn layui-btn-sm" id="btn_on"  lay-submit="" lay-filter="updateClass">修改</button>
            <button  class="layui-btn layui-btn-sm" id="close">返回</button>
        </div>
    </form>
</div>
<script>
    $('#close').click(function() {
        window.location.href='<%=path%>/findUser';
        return false;
    });
    layui.config({
        base: 'layui_exts/',
    }).extend({
        excel: 'excel',
    });
    $("#sex").val('${user.sex}');
    $("#did").val('${user.did}');
    $("#pid").val('${user.pid}');
    $("#type").val('${user.type}');
    layui.use(['jquery', 'excel','form','layer','laydate'], function(){
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate;
        form.verify({
            password: function(val, item) {
                if(!/^.{6,16}$/.test(val)){
                    return '密码6到16位'
                }
            }
        });
    });

</script>
</body>
</html>
