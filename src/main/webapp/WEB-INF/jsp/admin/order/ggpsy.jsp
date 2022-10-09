<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/2/19
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <meta name="viewport" content="initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <link rel="stylesheet" type="text/css" href="css/merchant/index.css">
    <link rel="stylesheet" href="css/merchant/style.css" type="text/css"/>
    <!-- <link rel="stylesheet" type="text/css" href="css/merchant/location.css"> -->
    <!-- 文本编辑器 -->
    <link rel="stylesheet" type="text/css" href="https://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link href="js/summernote/dist/summernote.css" rel="stylesheet"/>

    <script type="text/javascript" src="js/merchant/jquery-1.11.1.min.js"></script>

    <script type="text/javascript" src="js/merchant/iscroll-zoom.js"></script>
    <script type="text/javascript" src="js/merchant/hammer.js"></script>
    <script type="text/javascript" src="js/merchant/lrz.all.bundle.js"></script>

    <script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>

    <title>编辑</title>

</head>
<style type="text/css">
    /*浏览器格式化，消除页面预留空间*/
    * {
        margin: 0;
        padding: 0;
    }

    /*适应各种浏览器屏幕尺寸*/
    body {
        width: 100%;
        height: 100%;
        /*设置页面字体大小*/
        font-size: 14px;
    }

    /*列表内元素垂直居中*/
    label, input, a {
        vertical-align: middle;
    }

    /*给选项表示设置内边距*/
    label {
        padding: 0 10px 0 5px;
    }

    /*给列表设置边框*/
    dl {
        width: 120px;
        /*元素水平居中*/
        margin: 10px auto;
        border: 1px solid #666;
        /*设置边框圆角*/
        border-radius: 5px;
        background: #fafafa;
        padding: 10px 5px;
    }

    /*消除链接下划线，设置链接字体颜色*/
    a {
        text-decoration: none;
        color: #09f;
    }

    /*鼠标指针浮动在链接上时，样式改变:字体变红色*/
    a:hover {
        color: red;
    }

    dt {
        /*下边框*/
        border-bottom: 1px solid black;
        /*距离底部内边距*/
        padding-bottom: 10px;
    }

    /*标示字体加粗*/
    dt label {
        font-weight: 700;
    }

    /*各个选项距离顶部外边距*/
    p {
        margin-top: 10px;
    }
</style>
<body>

<form id="form" action="" method="POST">
    <h3>请选择：</h3>
    <dl class="checkBox">

<%--        <dt><input name="checks" type="checkbox" id="all" onclick="cli('check');">  全选--%>
<%--            &lt;%&ndash;<label>全选</label>&ndash;%&gt;--%>
<%--            &lt;%&ndash;<a href="javascript:;">反选</a>&ndash;%&gt;--%>
<%--        </dt>--%>
        <dd>
            <input name="id" placeholder="" value="${ids}" type="hidden">
            <c:forEach items="${deli}" var="type">
                <p><input type="checkbox" name="check" value="${type.id}"><label>${type.name}</label></p>
            </c:forEach>
        </dd>
    </dl>
 <div class="footer">
    <input type="button" onclick="tijiao();" value="设置">
</div>
</form>
<script>
    function tijiao() {
        var id = $("[name='id']").val();
        var checkID = [];
        $("input[name='check']:checked").each(function (i) {
            checkID[i] = $(this).val();
        });
        if($.trim(checkID)==""){
            layer.msg('请选择一个配送员！');
            return false;
        }
        $.ajax({
            type: 'POST',
            url: 'adminggpsyajax',
            data: {
                shids: id,
                checkID: checkID,
            },
            success: function (data) {
                if (data.code == 100) {
                    alert("发送成功");
                    parent.reload();
                } else {
                    alert("发送失败");
                }

            }
        });

    }
    $('input').click(function () {

        var now = this;

        $('input').each(function (i, e) {

            if (now !== e) {

                $(e).attr('checked', false);

            }

        })

    });
    /*function cli(Obj)
    {
        //获取控制其它复选框的对象obj
        var collid = document.getElementById("all")
        //获取需要全选，全不选功能的所有复选框
        var coll = document.getElementsByName(Obj)
        //如果obj被选中，则全选
        if (collid.checked){
            //循环设置所有复选框为选中状态
            for(var i = 0; i < coll.length; i++)
                coll[i].checked = true;
        }else{//取消obj选中状态，则全不选
            //循环设置所有复选框为未选中状态
            for(var i = 0; i < coll.length; i++)
                coll[i].checked = false;
        }
    }*/

    // window.onload = function () {//网页加载完成后调用函数
    //     var oA = document.getElementsByTagName("a")[0];//获取第一个a元素，即链接"反选"
    //     var oInput = document.getElementsByTagName("input");//获取所有的输入选择框
    //     var oLabel = document.getElementsByTagName("label")[0];//获取第一个lable标签，lable标签为input标签定义标记，即"全选"选项。
    //     var isCheckAll = function () {//声明一个全选函数
    //         for (var i = 1, n = 0; i < oInput.length; i++) {//从i=1循环，排除第一个选项"全选“，从选项1开始循环。
    //             oInput[i].checked && n++//没选择一个选项，则n增加1，n=已选的项目数
    //         }
    //         oInput[0].checked = n == oInput.length - 1;//选择第一个选项=选择第一个项目之外的全部选项，所以点击全选的同时，其他所有选项一并选中。
    //         oLabel.innerHTML = oInput[0].checked ? "全不选" : "全选"//innerHtml改变第一个标签"全选'的元素内容，后面为判断语句：选择"全选"时，全选变为全不选，
    //
    //     };
    //     oInput[0].onclick = function () {//给第一个选项"全选"绑定点击函数事件,点击全选时，执行函数
    //         for (var i = 1; i < oInput.length; i++) {
    //             oInput[i].checked = this.checked//当点击第一个"全选"选项时，执行循环函数:遍历选择每个选项，即点击全选时，所有选项都选择，实现全选，同理实现全不选，
    //
    //         }
    //         isCheckAll()
    //     };
    //     oA.onclick = function () {//给"反选"绑定点击事件，当点击反选时，执行相应函数事件
    //         for (var i = 1; i < oInput.length; i++) {
    //             oInput[i].checked = !oInput[i].checked//点击反选，已选变未选，未选变已选，!为否定
    //         }
    //         ;
    //     }
    //     for (var i = 1; i < oInput.length; i++) {//点击改变元素
    //         oInput[i].onclick = function () {
    //             isCheckAll()//oLabel.innerHTML = oInput[0].checked ? "全不选" : "全选"，根据选中个数更新全选框状态，当全选后，全选切换为全不选。
    //         };
    //     }
    //
    // }
</script>

</body>
</html>
