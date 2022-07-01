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
       <a href="<%=path%>/findClock">查询列表</a>
       <a href="">修改</a>
      </span>
</div>
<div class="x-body">
    <form class="layui-form"  id="f_auto" action="<%=path%>/updateClock" method="post" >
        <input type="hidden" value="${clock.id}" name="id" id="id"/>

        <div class="layui-form-item">
            <label for="uid" class="layui-form-label">
                <span class="">员工</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="uid" name="uid" lay-verify="required"
                       autocomplete="off" value="${clock.uid}" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="ptime" class="layui-form-label">
                <span class="">打卡时间</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="ptime" name="ptime" lay-verify="required"
                       autocomplete="off" value="${clock.ptime}" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="status" class="layui-form-label">
                <span class="">状态</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="status" name="status" lay-verify="required"
                       autocomplete="off" value="${clock.status}" class="layui-input">
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
        window.location.href='<%=path%>/findClock';
        return false;
    });
</script>
</body>
</html>
