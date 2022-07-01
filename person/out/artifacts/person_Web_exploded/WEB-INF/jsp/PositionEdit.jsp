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
       <a href="<%=path%>/findPosition">查询列表</a>
       <a href="">修改</a>
      </span>
</div>
<div class="x-body">
    <form class="layui-form"  id="f_auto" action="<%=path%>/updatePosition" method="post" >
        <input type="hidden" value="${position.id}" name="id" id="id"/>

        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
                <span class="">岗位名称</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" lay-verify="required"
                       autocomplete="off" value="${position.name}" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label for="did" class="layui-form-label">
                <span class="">所属部门</span>
            </label>
            <div class="layui-input-inline">
                <select name="did" id="did"  lay-verify="required">
                    <c:forEach items="${dLists}" var="d">
                        <option value="${d.id}">${d.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <label  class="layui-form-label">
                <span class="">备注</span>
            </label>
            <div class="layui-input-inline">
                <textarea name="remark" placeholder="请输入内容"  class="layui-textarea">${position.remark}</textarea>
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
        window.location.href='<%=path%>/findPosition';
        return false;
    });
    $("#did").val('${position.did}')
</script>
</body>
</html>
