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

<!-- 内容主体区域 -->
<table class="layui-hide" id="categoryTable" lay-filter="categoryTable"></table>
<%--        表头工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add" id="tes">新增分类</button>
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
                    <input type="text" name="id" autocomplete="off" class="layui-input" id="categortId">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">分类名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="图书名称"
                           class="layui-input">
                </div>
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

    //渲染表格
    var categoryTable = table.render({
        elem: '#categoryTable'
        , id: 'categoryList'
        , url: 'all'
        , cellMinWidth: "80" //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , page: true
        , toolbar: "#toolbarDemo"
        , defaultToolbar: ['filter', 'print']
        , cols: [[
            {type: "checkbox", align: "center", width: "90", id: 'checkTest'}
            , {field: 'id', title: 'ID', sort: true, align: 'center', width: "100"}
            , {field: 'name', title: '分类名称', align: 'center', width: '10%'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
            , {
                field: 'delFlag', title: '状态', align: "center", width: "20%", templet: function (d) {
                    if (d.delFlag == "0") {
                        return "<input type='checkbox' value='" + d.id + "' id='status' lay-filter='stat' checked='checked' name='status' lay-skin='switch' lay-text='已启用|已禁用' >";
                    } else {
                        return "<input type='checkbox' value='" + d.id + "' id='status' lay-filter='stat'  name='status' lay-skin='switch' lay-text='已启用|已禁用' >";
                    }
                }
            }
            , {title: "操作", width: "20%", align: 'center', toolbar: '#barDemo'}
        ]]

    });
    //监听指定开关
    form.on('switch(stat)', function (data) {
        var x = data.elem.checked;
        layer.open({
            content: x == false ? '确定禁用此分类吗' : '确定启用此分类吗'
            , icon: 3
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //按钮【按钮一】的回调
                if (x) {
                    $.ajax({
                        "url": "${pageContext.request.contextPath}/category/reuse/"+data.elem.value,
                        "type": "post",
                        "dataType": "json",
                        "success": function (result) {
                            if (result) {
                                layer.msg('操作成功', {icon: 1, time: 2000})
                            }else {
                                layer.msg('操作失败', {icon: 2, time: 2000})
                            }
                        }
                    })
                    data.elem.checked = x;
                    form.render();
                    layer.close(index);
                }

            }
            , btn2: function (index, layero) {
                //按钮【按钮二】的回调
                data.elem.checked = !x;
                form.render();
                layer.close(index);
                //return false 开启该代码可禁止点击该按钮关闭
            }
            , cancel: function () {
                //右上角关闭回调
                data.elem.checked = !x;
                form.render();
                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
        return false;
    });

    //验证指定分类下是否有图书
    function checkBook(id) {
        ;
    }

    //监听表头工具栏事件
    table.on('toolbar(categoryTable)', function (obj) {
        var event = obj.event;
        switch (event) {
            case 'add':
                openCategoryInfo();
                break;
            case 'getCheckLength':
                var checkStatus = table.checkStatus("categoryList"), data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 条');
                break;
            case 'isAll':
                var checkStatus = table.checkStatus('categoryList');
                layer.msg(checkStatus.isAll ? '已全选' : '未全选');
                break;
        }
    })


    //    监听行工具栏单击事件
    table.on('tool(categoryTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        if (layEvent === 'detail') { //查看
            //do somehing
            openUpdateCategory(data);
        } else if (layEvent === 'del') { //删除
            deleteCategory(data);
        } else if (layEvent === 'edit') { //编辑
            //do something
            //同步更新缓存对应的值
            openUpdateCategory(data);
        } else if (layEvent === 'LAYTABLE_TIPS') {
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });
    var url;//表单提交地址
    var layerIndex; //弹出层index值
    //打开添加页面
    function openCategoryInfo() {
        layerIndex = layer.open({
            type: 1,
            // id: 'LAY_layuipro',
            title: '新增信息',
            content: $("#saveOrUpdateDiv"),
            btnAlign: 'c',
            area: ['400px', '230px'],
            success: function (layero, index) {
                //重置表单信息
                $("#dataForm")[0].reset();
                url = "${pageContext.request.contextPath}/category/add";
            }
        });
    }

    //打开修改页面
    function openUpdateCategory(data) {
        layerIndex = layer.open({
            type: 1,
            title: '修改图书信息',
            content: $("#saveOrUpdateDiv"),
            area: ['400px', '230px'],
            success: function (index) {
                form.val("dataFrm", data);
                url = "${pageContext.request.contextPath}/category/update";
                layui.form.render();
            }
        });
    }

    //删除图书信息
    function deleteCategory(data) {
        layer.confirm('真的删除行么', {
            icon: 3
            , yes: function () {
                //先验证分类下是否有图书信息
                $.ajax({
                    "url": "${pageContext.request.contextPath}/book/isHave/" + data.id,
                    "type": "post",
                    "dataType": "json",
                    "success": function (result) {
                        if (result.length != 0) {
                            layer.msg('该分类下还有图书信息，请先删除图书信息', {icon: 2, time: 2000})
                        } else {
                            $.ajax({
                                "url": "${pageContext.request.contextPath}/category/delete/" + data.id,
                                "type": "post",
                                "dataType": "json",
                                "success": function (result) {
                                    if (result) {
                                        categoryTable.reload({page: {curr: 1}});
                                        layer.msg("删除成功", {icon: 1, time: 2000})
                                    } else {
                                        layer.msg("删除失败", {icon: 2, time: 2000})
                                    }
                                }
                            })
                        }
                    }
                })
                return false;
            }
        });
    }

    //删除分类信息回调


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
                    categoryTable.reload({page: {curr: 1}});
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
        categoryTable.reload({
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
