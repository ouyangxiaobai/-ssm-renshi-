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
        <a href="<%=path%>/findRecruit">查询列表</a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="<%=path%>/findRecruit" title="刷新">
        <i class="layui-icon" style="line-height:30px">&#xe669;</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="<%=path%>/findRecruit" >
            <input class="layui-input" type="hidden" name="pageIndex" value="1">
            <input class="layui-input" type="hidden" name="pageSize" value="5">
            <%--<button class="layui-btn layui-btn-sm"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>--%>
        </form>
    </div>
    <xblock>
        <button id="addRecruitBtn" class="layui-btn layui-btn-sm"> <i class="layui-icon">&#xe654;</i>添加职位 </button>
        <span class="x-right" style="line-height:40px">共有数据：${pageList.totalCount} 条</span>
    </xblock>


    <%--表格数据--%>
    <table class="layui-table">
        <thead>
        <tr>
             <th>职位</th>
             <th>职位要求</th>
             <th>企业</th>
             <th>招聘人数</th>
             <th>月薪</th>
             <th>发布时间</th>
             <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageList.list}" var="di">
            <tr>
                  <th>${di.post}</th>
                  <th>${di.demand.length()>10?di.demand.substring(0,10):di.demand}</th>
                  <th>${di.enterprise}</th>
                  <th>${di.num}</th>
                  <th>${di.money}</th>
                  <th>${di.ptime}</th>
                <td>
                    <a title="编辑" id= "updateEdit" href="<%=path%>/findRecruitById?id=${di.id}">
                        <i class="layui-icon">&#xe642;</i>
                    </a>
                    <a title="删除" onclick="member_del(this,'${di.id}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
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
            <form class="layui-form" id="addRecruitForm">
                <div style="margin-top: 20px;"></div>
                <div class="layui-form-item">
                    <label class="layui-form-label">职位：</label>
                    <div class="layui-input-block">
                        <input type="text" name="post" lay-verify="required" class="layui-input" placeholder="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">职位要求：</label>
                    <div class="layui-input-block">
                        <textarea name="demand" placeholder="请输入内容"  class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">企业：</label>
                    <div class="layui-input-block">
                        <input type="text" name="enterprise" lay-verify="required" class="layui-input" placeholder="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">招聘人数：</label>
                    <div class="layui-input-block">
                        <input type="text" name="num" lay-verify="required" class="layui-input" placeholder="" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">月薪：</label>
                    <div class="layui-input-block">
                        <input type="text" name="money" lay-verify="money" class="layui-input" placeholder="">
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
            form.verify({
                money: function(val, item) {
                    if(!/^\d+(\.\d{0,2})?$/.test(val)){
                        return '请输入正确的金额格式'
                    }
                }
            });
            /*添加弹出框*/
            $("#addRecruitBtn").click(function () {
                layer.open({
                    type:1,
                    title:"添加",
                    skin:"myclass",
                    area:["50%"],
                    anim:2,
                    content:$("#test").html()
                });
                $("#addRecruitForm")[0].reset();
                form.on('submit(formDemo)', function(data) {
                    var param=data.field;
                    $.ajax({
                        url: '<%=path%>/addRecruit',
                        type: "post",
                        data:JSON.stringify(param),
                        contentType: "application/json; charset=utf-8",
                        success:function(){
                            layer.msg('添加成功', {icon: 1, time: 3000});
                            setTimeout(function () {window.location.href='<%=path%>/findRecruit';},2000);

                        },
                        error:function(){
                            layer.msg('添加失败',{icon:0,time:3000});
                            setTimeout(function () {window.location.href='<%=path%>/findRecruit';},2000);
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
                $.get("<%=path%>/deleteRecruit",{"id":id},function (data) {
                    if(data =true){
                        layer.msg('删除成功!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findRecruit';},2000);

                    }else {
                        layer.msg('删除失败!',{icon:1,time:2000});
                        setTimeout(function () {window.location.href='<%=path%>/findRecruit';},2000);
                    }
                });
            });
        }

    </script>
</body>
</html>
