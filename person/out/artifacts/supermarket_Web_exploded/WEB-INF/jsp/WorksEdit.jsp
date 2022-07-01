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
       <a href="<%=path%>/findWorks">查询列表</a>
       <a href="">修改</a>
      </span>
</div>
<div class="x-body">
    <form class="layui-form"  id="f_auto" action="<%=path%>/updateWorks" method="post" >
        <input type="hidden" value="${works.id}" name="id" id="id"/>

        <div class="layui-form-item">
            <label for="uid" class="layui-form-label">
                <span class="">员工</span>
            </label>
            <div class="layui-input-inline">
                <select name="uid" id="uid"  lay-verify="required">
                    <c:forEach items="${uLists}" var="u">
                        <option value="${u.id}">${u.username}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="">加班日期</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="start" name="wtime" lay-verify="required"
                       autocomplete="off" value="${works.wtime}" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="day" class="layui-form-label">
                <span class="">加班天数</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="day" name="day" lay-verify="required"
                       autocomplete="off" value="${works.day}" class="layui-input"  onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="money" class="layui-form-label">
                <span class="">加班费用</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="money" name="money" lay-verify="money"
                       autocomplete="off" value="${works.money}" class="layui-input">
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
        window.location.href='<%=path%>/findWorks';
        return false;
    });

    $("#uid").val('${works.uid}');

    layui.config({
        base: 'layui_exts/',
    }).extend({
        excel: 'excel',
    });

    layui.use(['jquery', 'excel','form','layer','laydate'], function(){
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate;
        var excel = parent.layui.excel;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });
        form.verify({
            money: function(val, item) {
                if(!/^\d+(\.\d{0,2})?$/.test(val)){
                    return '请输入正确的金额格式'
                }
            }
        });

    });
</script>
</body>
</html>
