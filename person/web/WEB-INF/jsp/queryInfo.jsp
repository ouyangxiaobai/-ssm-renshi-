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
    <form class="layui-form"  id="f_auto" action="<%=path%>/updateAdmin" method="post" >
        <input type="hidden" value="${sessionScope.ad.id}" name="id" id="id"/>

        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="">用户名</span>
            </label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="username" disabled
                       autocomplete="off" value="${sessionScope.ad.username}" class="layui-input">
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
                            <c:if test="${d.id == sessionScope.ad.did}">
                                <option value="${d.id}">${d.name}</option>
                            </c:if>
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
                            <c:if test="${p.id == sessionScope.ad.pid}">
                                <option value="${p.id}">${p.name}</option>
                            </c:if>
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
                    <input type="text" id="sex" name="sex" disabled
                           autocomplete="off" value="${sessionScope.ad.sex}" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="phone" class="layui-form-label">
                    <span class="">电话</span>
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="phone" name="phone" disabled
                           autocomplete="off" value="${sessionScope.ad.phone}" class="layui-input">
                </div>
            </div>
        </c:if>

    </form>
</div>
<script>
    $('#close').click(function() {
        //window.location.href='/findBook';
        return false;
    });

</script>
</body>
</html>