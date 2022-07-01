<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>person</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="icon" href="<%=path%>/images/favicon.ico" sizes="32x32" />
    <link rel="stylesheet" href="<%=path%>/css/font.css">
    <link rel="stylesheet" href="<%=path%>/css/xadmin.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
    <script src="<%=path%>/lib/layui/layui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
    <script src="<%=path%>/layui_exts/excel.js"></script>
    <style type="text/css">
        .layui-table{
            text-align: center;
        }
        .layui-table th{
            text-align: center;
        }
    </style>
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
       <a href="">首页</a>
        <a href="<%=path%>/findLeaves">查询列表</a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="<%=path%>/findLeaves" title="刷新">
        <i class="layui-icon" style="line-height:30px">&#xe669;</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="<%=path%>/findLeaves" >
            <input class="layui-input" type="hidden" name="pageIndex" value="1">
            <input class="layui-input" type="hidden" name="pageSize" value="5">
            <div class="layui-inline layui-show-xs-block">
                <input type="text" name="username"  placeholder="请输入用户名" class="layui-input" placeholder="">
            </div>
            <div class="layui-inline layui-show-xs-block">
                <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
            </div>
        </form>
    </div>
    <c:if test="${sessionScope.type == '03'}">
    <xblock>
        <button id="addLeavesBtn" class="layui-btn layui-btn-sm"> <i class="layui-icon">&#xe654;</i>请假 </button>
        <span class="x-right" style="line-height:40px">共有数据：${pageList.totalCount} 条</span>
    </xblock>
    </c:if>

    <%--表格数据--%>
    <table class="layui-table">
        <thead>
        <tr>
            <c:if test="${sessionScope.type != '03'}">
             <th>员工</th>
            </c:if>
             <th>请假天数</th>
             <th>请假时间</th>
             <th>状态</th>
             <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageList.list}" var="di">
            <tr>
                <c:if test="${sessionScope.type != '03'}">
                  <th>${di.uname}</th>
                </c:if>
                  <th>${di.day}</th>
                  <th>${di.ptime}</th>
                  <th>
                      <c:if test="${di.status == '01'}">
                          <button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger">待审批</button>
                      </c:if>
                      <c:if test="${di.status == '02'}">
                          <button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger">已审核</button>
                      </c:if>
                      <c:if test="${di.status == '03'}">
                          <button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger">已拒绝</button>
                      </c:if>
                  </th>
                <td>
                    <c:if test="${sessionScope.type == '03'}">
                        <a title="编辑" id= "updateEdit" href="<%=path%>/findLeavesById?id=${di.id}">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" onclick="member_del(this,'${di.id}')" href="javascript:;">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                    </c:if>
                <c:if test="${sessionScope.type == '01'}">
                    <c:if test="${di.status == '01'}">

                        <a title="同意" onclick="member_yes(this,'${di.id}','02')" href="javascript:;">
                            <i class="layui-icon">&#xe605;</i>
                        </a>
                        <a title="拒绝" onclick="member_no(this,'${di.id}','03')" href="javascript:;">
                            <i class="layui-icon">&#x1006;</i>
                        </a>
                    </c:if>
                </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="" >
        <input type="hidden" id="totalPageCount" value="${pageList.pageTotalCount}"/>
        <c:import url="pageBtn.jsp">
            <c:param name="totalCount" value="${pageList.totalCount}"/>
            <c:param name="currentPageNo" value="${pageList.pageIndex}"/>
            <c:param name="totalPageCount" value="${pageList.pageTotalCount}"/>
        </c:import>
    </div>

    <%--添加模态框--%>
    <div class="layui-row" id="test" style="display: none;">
        <div class="layui-col-md10">
            <form class="layui-form" id="addLeavesForm">
                <div style="margin-top: 20px;"></div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请假天数：</label>
                    <div class="layui-input-block">
                        <input type="text" name="day" lay-verify="required" class="layui-input" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请假时间：</label>
                    <div class="layui-input-block">
                        <input type="text" name="ptime" id="start" lay-verify="required" class="layui-input" placeholder="">
                    </div>
                </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
             </form>
        </div>
    </div>

    <script>
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

            /*添加弹出框*/
            $("#addLeavesBtn").click(function () {
                layer.open({
                    type:1,
                    title:"添加",
                    skin:"myclass",
                    area:["50%"],
                    anim:2,
                    content:$("#test"),
                    success: function(layero, index) { laydate.render({ elem: '#start' }); }
                });
                $("#addLeavesForm")[0].reset();
                form.on('submit(formDemo)', function(data) {
                    var param=data.field;
                    $.ajax({
                        url: '<%=path%>/addLeaves',
                        type: "post",
                        data:JSON.stringify(param),
                        contentType: "application/json; charset=utf-8",
                        success:function(){
                            layer.msg('添加成功', {icon: 1, time: 3000});
                            setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);

                        },
                        error:function(){
                            layer.msg('添加失败',{icon:0,time:3000});
                            setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);
                        }
                    });
                    // return false;
                });
            });

        });


        /*删除*/
        function member_del(obj,id){
            layer.confirm('确认要删除吗？',function(index){
                //发异步删除数据
                $.get("<%=path%>/deleteLeaves",{"id":id},function (data) {
                    if(data =true){
                        layer.msg('删除成功!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);

                    }else {
                        layer.msg('删除失败!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);
                    }
                });
            });
        }


        /*更新状态*/
        function member_yes(obj,id,status){
            layer.confirm('是否确认同意申请？',function(index){
                //发异步删除数据
                $.get("<%=path%>/updateStatus",{"id":id,"status":status},function (data) {
                    if(data =="success"){
                        layer.msg('操作成功!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);

                    }else {
                        layer.msg('操作失败!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);
                    }
                });
            });
        }

        /*更新状态*/
        function member_no(obj,id,status){
            layer.confirm('是否确认拒绝申请？',function(index){
                //发异步删除数据
                $.get("<%=path%>/updateStatus",{"id":id,"status":status},function (data) {
                    if(data =="success"){
                        layer.msg('操作成功!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);

                    }else {
                        layer.msg('操作失败!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findLeaves';},2000);
                    }
                });
            });
        }
    </script>
</body>
</html>
