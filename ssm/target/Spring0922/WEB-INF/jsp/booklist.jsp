<%--
  Created by IntelliJ IDEA.
  User: je
  Date: 2020/9/21
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/layui.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .layui-form-label {
            width: 100px;
        }
    </style>
</head>
<body style="margin: 0;padding: 0">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form action="" class="layui-form" method="post" id="searchForm">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">图书名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="title" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">上传人:</label>
            <div class="layui-input-inline">
                <input type="text" name="uploadUser" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">上传时间:</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="creatDate" id="date">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">图书类别:</label>
            <div class="layui-input-inline">
                <select name="category" lay-filter="selectCategory" id="selectCategory" lay-search="">
                    <%--                    <option value="-1">请选择</option>--%>
                    <%--                    <option value="1">玄幻</option>--%>
                    <%--                    <option value="2">言情</option>--%>
                    <%--                    <option value="3">武侠</option>--%>
                    <%--                    <option value="4">童话</option>--%>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: left;">
        <div class="layui-input-inline" style="margin-left: 20px">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-search"
                    id="doSearch">查询
            </button>
            <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->
<!-- 内容主体区域 -->
<table class="layui-hide" id="bookTable" lay-filter="bookTable"></table>
<%--        表头工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add" id="tes">新增图书</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>
<%--        表头工具栏结束--%>
<%--        行工具栏开始--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<%--        行工具栏结束--%>
<%--        弹出层开始--%>
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form " lay-filter="dataFrm" id="dataForm">
        <div class="layui-form-item layui-hide">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="id" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">图书名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="图书名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">上传人:</label>
                <div class="layui-input-inline">
                    <input type="text" name="uploadUser" autocomplete="off" lay-verify="required"
                           placeholder="上传人" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">图书类别:</label>
                <div class="layui-input-inline">
                    <select name="category" lay-filter="category" id="category" lay-search="" lay-verify="required">
                        <%--                        <option value="-1">请选择</option>--%>
                        <%--                        <option value="1">玄幻</option>--%>
                        <%--                        <option value="2">言情</option>--%>
                        <%--                        <option value="3">武侠</option>--%>
                        <%--                        <option value="4">童话</option>--%>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">上传时间:</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="creatDate" id="creatDate" lay-verify="required"
                           placeholder="yyyy-MM-dd">
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">图书简介:</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" name="summary"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否可用:</label>
            <div class="layui-input-block">
                <input type="checkbox" name="ishere" lay-filter="ishere"
                       lay-skin="switch" lay-text="是|否" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;margin:30px 0 0 0">
                <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>
<%--        弹出层結束--%>

<script src="${pageContext.request.contextPath}/statics/layui/layui.all.js"></script>
<script>
    //渲染table数据表格
    var form = layui.form;
    var $ = layui.jquery;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    //渲染日期
    laydate.render({
        elem: '#creatDate'
        , lang: 'en'
    })
    laydate.render({
        elem: '#date'
        , lang: 'en'
    })
    //渲染表格
    var bookTable = table.render({
        elem: '#bookTable'
        , id: 'bookList'
        , url: 'list'
        , cellMinWidth: "80" //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , page: true
        , toolbar: "#toolbarDemo"
        , defaultToolbar: ['filter', 'print']
        , cols: [[
            {type: "checkbox", align: "center", width: "90", id: 'checkTest'}
            , {field: 'id', title: 'ID', sort: true, align: 'center', width: "100"}
            , {field: 'title', title: '图书名称', align: 'center', width: '10%'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
            , {
                field: 'category', title: '图书分类', align: 'center', width: '10%', templet: function (res) {
                    return res.category.name;
                }
            }
            , {
                field: 'summary', title: '图书简介', align: "center", width: "20%"
            }
            , {field: 'uploadUser', title: '上传人', align: 'center', width: "10%"} //单元格内容水平居中
            //单元格内容水平居中
            , {
                field: 'creatDate', title: '上传时间', align: 'center', width: "15%"
            } //单元格内容水平居中
            , {title: "操作", width: "20%", align: 'center', toolbar: '#barDemo'}
        ]]

    });
    //监听表头工具栏事件
    table.on('toolbar(bookTable)', function (obj) {
        var event = obj.event;
        switch (event) {
            case 'add':
                openBookInfo();
                break;
            case 'getCheckLength':
                var checkStatus = table.checkStatus("bookList"), data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 条');
                break;
            case 'isAll':
                var checkStatus = table.checkStatus('bookList');
                layer.msg(checkStatus.isAll ? '已全选' : '未全选');
                break;
        }
    })


    //    监听行工具栏单击事件
    table.on('tool(bookTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        if (layEvent === 'detail') { //查看
            //do somehing
            openUpdateBook(data);
        } else if (layEvent === 'del') { //删除
            deleteBook(data);
        } else if (layEvent === 'edit') { //编辑
            //do something
            //同步更新缓存对应的值
            openUpdateBook(data);
        } else if (layEvent === 'LAYTABLE_TIPS') {
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });

    //动态加载分类拉下框内容
    function initCategory() {
        $.ajax({
            "url": "${pageContext.request.contextPath}/category/all",
            "type": "post",
            "dataType": "json",
            "success": function (result) {
                var selectCategory = $("select[name='category']").empty();
                selectCategory.each(function () {
                    $(this).append("<option value='-1'>请选择</option>");
                    for (var i = 0; i < result.data.length; i++) {
                        $(this).append("<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>");
                    }
                });
                //重新渲染表格
                layui.form.render();
            }
        })
    }

    //页面加载调用
    $.ready(initCategory());
    var url;//表单提交地址
    var layerIndex; //弹出层index值
    //打开添加页面
    function openBookInfo() {
        layerIndex = layer.open({
            type: 1,
            // id: 'LAY_layuipro',
            title: '新增信息',
            content: $("#saveOrUpdateDiv"),
            btnAlign: 'c',
            area: ['800px', '400px'],
            success: function (layero, index) {
                //重置表单信息
                $("#dataForm")[0].reset();
                url = "${pageContext.request.contextPath}/book/add";
                initCategory();
            }
        });
    }

    //打开修改页面
    function openUpdateBook(data) {
        layerIndex = layer.open({
            type: 1,
            title: '修改图书信息',
            content: $("#saveOrUpdateDiv"),
            area: ['800px', '400px'],
            success: function (index) {
                form.val("dataFrm", data);
                $("#category").val(data.category.id);
                url = "${pageContext.request.contextPath}/book/update";
                initCategory();
                layui.form.render();
            }
        });
    }

    //删除图书信息
    function deleteBook(data) {
        layer.confirm('真的删除行么', {
            icon: 3
            , yes: function () {
                $.ajax({
                    "url": "${pageContext.request.contextPath}/book/delete/" + data.id,
                    "type": "post",
                    "dataType": "json",
                    "success": function (result) {
                        if (result) {
                            bookTable.reload({page: {curr: 1}});
                            layer.msg("删除成功", {icon: 1, time: 2000})
                        } else {
                            layer.msg("删除失败", {icon: 2, time: 2000})
                        }
                    }
                })
                return false;
            }
        });
    }

    //    监听业务表单提交事件
    form.on('submit(submit)', function (obj) {
        var param = $("#dataForm").serialize();
        $.ajax({
            "url": url,
            "type": "post",
            "data": param,
            "dataType": "json",
            "success": function (data) {
                if (data) {
                    layer.close(layerIndex);
                    bookTable.reload({page: {curr: 1}});
                    layer.msg("操作成功!", {icon: 1, time: 2000});

                } else {
                    layer.msg('操作失败！', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }
            }
        })
        return false;
    })
    //    监听业务表单提交事件
    $("#doSearch").click(function () {
        var title = $("#searchForm input[name='title']").val();
        var categoryId = $("#searchForm select option:selected").val();
        var uploadUser = $("#searchForm input[name='uploadUser']").val();
        var creatDate = $("#searchForm input[name='creatDate']").val();
        // if (title == "" && categoryId == -1 && uploadUser == "" && creatDate == "") {
        //     layer.msg('请选择查询条件',{
        //         icon: 5,
        //         time: 2000,
        //         offset: '200px'
        //     })
        //     return false;
        // }
        bookTable.reload({
            url: 'searchList',
            where: {
                "title": title,
                "categoryId": categoryId,
                "uploadUser": uploadUser,
                "creatDate": creatDate
            }
        });
    })

</script>
</body>
</html>
