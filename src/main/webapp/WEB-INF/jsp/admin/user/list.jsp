<!-- 此例子是结合bootstrap的Datatables，暂且定位为最基本的例子吧 -->
<!-- 引入必须的css和js文件 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!-- <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css" />  -->
    <script src="js/Hui-js/lib/jquery.js"></script>
    <script src="js/Hui-js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css"
          href="h-ui.admin/css/style.css"/>
    <script type="text/javascript" src="js/Hui-js/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="h-ui.admin/js/H-ui.admin.js"></script>
    <!--/_footer /作为公共模版分离出去-->
    <!--请在下方写此页面业务相关的脚本-->
    <script type="text/javascript" src="js/Hui-js/lib/My97DatePicker/4.8/WdatePicker.js"></script>
    <script type="text/javascript" src="js/Hui-js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/Hui-js/lib/laypage/1.2/laypage.js"></script>
    <script type="text/javascript"></script>
    <!-- HTML代码片段中请勿添加<body>标签 //-->
    <style>
        .sxUl {
            height: 30px;
        }

        .sxUl li {
            float: left;
            height: 30px;
            margin-right: 70px;
        }

        .sxUl li input {
            display: inline-block;
            width: 160px;
            height: 28px;
            padding: 0 15px;
            font-size: 14px;
            color: #fff;
            line-height: 28px;
            border-radius: 5px;
            border: 1px solid #5a98de;
        }

        .sxUl li i {
            display: inline-block;
            width: auto;
            height: 28px;
            padding: 0 15px;
            margin: 1px 0 1px 10px;
            font-size: 14px;
            color: #fff;
            line-height: 28px;
            font-style: normal;
            cursor: pointer;
            border-radius: 5px;
            background-color: #5a98de;
        }
        .btn {
            background-color: #5a98de;
            border-color: #5a98de;
        }
        .btn-success{
            background-color: #5a98de!important;
            border-color: #5a98de!important;
        }
        #types{
            border-color: lightgrey;
            color: black;
        }
        #name{
            border-color: lightgrey;
            background-color: white!important;
        }

    </style>
</head>
<body>
<div id="container">
    <%--<form action="" name="form" method="post" class="form form-horizontal"--%>
    <%--id="form">--%>
    <%--<div class="cl pd-5 bg-1 bk-gray mt-10">--%>
    <%--<span class="l" style="margin-left:5px;">--%>
    <%--<a class="btn btn-primary radius" onclick="open_layer('添加','admUserAdd','900','450')" href="javascript:;">--%>
    <%--<i class="Hui-iconfont">&#xe600;</i>添加 </a>--%>
    <%--</span>--%>
    <%--</div>--%>
    <%--</form>--%>
    <!-- 定义一个表格元素 -->
<%--        <form action="" name="form" method="post" class="form form-horizontal"--%>
<%--              id="form">--%>
<%--            <div class="cl pd-5 bg-1 bk-gray mt-10"style="background-color: #ffffff">--%>
<%--                <span class="l" style="margin-left:5px;">--%>
<%--				 <a class="btn btn-primary radius" onclick="setread()" href="javascript:;">--%>
<%--				 <i class="Hui-iconfont"></i>批量删除</a>--%>
<%--				</span>--%>

<%--                <span class="l" style="margin-left:5px;">--%>
<%--				 <a class="btn btn-primary radius qxitem" onclick="allselect()" href="javascript:;">--%>
<%--				 <i  class="Hui-iconfont  "></i>全选</a>--%>
<%--				</span>--%>
<%--                <div class="panel" style="margin-left: 500px;margin-right: 710px;">--%>
<%--                    <div class="panel-body">--%>
<%--                        <div class="text-c" >--%>
<%--                            <form class="Huiform" method="post" action="" target="_self" >--%>
<%--                                <div class="panel" >--%>
<%--                                    <div class="panel-body">--%>
<%--                                        <div class="text-l">--%>
<%--                                            &lt;%&ndash;<span>分类名称：</span>&ndash;%&gt;--%>
<%--                                            <select id="types">--%>
<%--                                                <option value="0">请选择</option>--%>
<%--                                                <option value="1">用户ID查询</option>--%>
<%--                                                <option value="2">手机号查询</option>--%>
<%--                                            </select>--%>
<%--                                            <input type="text" name="name" id="name" placeholder="搜索" style="width: 250px" class="input-text">--%>
<%--                                            <button name="" id="" class="btn btn-success" type="button" onclick="Inquire();"><i class="Hui-iconfont">&#xe665;</i> 搜索 </button>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </form>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <%--<span class="r">--%>
                <%--<ul class="sxUl">--%>
                <%--<li>--%>
                <%--<input class="sbmc" placeholder="请输入商标名称"/>--%>
                <%--<i onclick="sbmc()">查询</i>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<input class="flhy" placeholder="请输入分类行业"/>--%>
                <%--<i onclick="flhy()">查询</i>--%>
                <%--</li>--%>
                <%--</ul>--%>
                <%--</span>--%>
                <%--</div>--%>
<%--        </form>--%>
    <div style="height: 10px"></div>
    <div style="width: 98%; margin-left: 10px;">
        <table id="example" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th style="text-align: center;width: 1%">序号</th>
                <th style="text-align: center;width: 2%">用户ID</th>
                <th style="text-align: center;width: 2%">OPENID</th>
                <th style="text-align: center;width: 2%">昵称</th>
                <th style="text-align: center;width: 4%">头像</th>
<%--                <th style="text-align: center;width: 2%">性别</th>--%>
<%--                <th style="text-align: center;width: 2%">地址</th>--%>
                <th style="text-align: center;width: 3%">手机号</th>
                <th style="text-align: center;width: 3%">类型</th>
                <th style="text-align: center;width: 4%">余额</th>
                <th style="text-align: center;width: 4%">是否会员</th>
                <th style="text-align: center;width: 4%">身份证号</th>
                <th style="text-align: center;width: 4%">姓名</th>
                <th style="text-align: center;width: 4%">身份证正面</th>
                <th style="text-align: center;width: 4%">身份证反面</th>
                <%--<th style="text-align: center;width: 4%">发展人id</th>--%>
                <th style="text-align: center;width: 4%">创建时间</th>
                <%--<th style="text-align: center;width: 4%">操作</th>--%>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    var datatable;
    var isall= 0;//默认多选为初始状态
    var dic = {};
    var arrayId=new Array()
    function Inquire() {
        datatable.ajax.reload();
    }
    /*Javascript代码片段*/
    $(document).ready(function () {
        $('#example').dataTable().fnDestroy();
        datatable  =  $('#example').DataTable({
            "ajax": {
                "url": "adminuserlistajax",
                "type": "post",
                "data": function(d) {
                    d.classify=$("#name").val();
                    d.types = $('#types option:selected').val();
                }
            },
            "lengthChange": true,//是否允许用户自定义显示数量
            "bPaginate": true, //翻页功能
            "bFilter": false, //列筛序功能
            "searching": true,//本地搜索
            "ordering": false, //排序功能
            "Info": true,//页脚信息
            "autoWidth": true,//自动宽度
            "serverSide": true,
            /* "bLengthChange":false,  */
            "searching": false,
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "columns": [
                {"data": null, "targets": 0},
                {"data": "id"},
                {"data": "open_id"},
                {"data": "nick_name"},
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='"+obj.head+"' onclick=\"open_layer('查看','"+obj.head+"','600','450')\"/></center></span>"
                    }
                },
                // {"data":function (obj) {
                //         if (obj.sex==0){
                //             return "<span><center>女</center></span>"
                //         }else if (obj.sex==1){
                //             return "<span><center>男</center></span>"
                //         }else {
                //             return "<span><center></center></span>"
                //         }
                //     }},
                // {"data": "adderss"},
                {"data": "phone"},
                {
                    "data": function (obj) {
                        if (obj.is_mech == 1) {
                            return "<span><center>商户</center></span>"
                        } else if (obj.is_mech == 2) {
                            return "<span><center>配送员</center></span>"
                        } else {
                            return "<span><center>用户</center></span>"
                        }
                    }
                },
                {"data": "amount"},
                {
                    "data": function (obj) {
                        if (obj.is_vip == 1) {
                            return "<span><center>是</center></span>"
                        }else {
                            return "<span><center>不是</center></span>"
                        }
                    }
                },
                {"data": "id_card"},
                {"data": "user_name"},
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='"+obj.just_imge+"' onclick=\"open_layer('查看','"+obj.just_imge+"','600','450')\"/></center></span>"
                    }
                },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='"+obj.back_imge+"' onclick=\"open_layer('查看','"+obj.back_imge+"','600','450')\"/></center></span>"
                    }
                },
                /*{"data": "dpeople"},*/
                {
                    "data": function (obj) {
                        var date = new Date(obj.create_time);
                        var time = date.format("yyyy-MM-dd hh:mm:ss");
                        return "<span><center>" + time + "</center></span>"
                    }
                },
                /*{"data": function (obj) {
                        return "<span><a onclick=\"deleteobj(" + obj.id + ")\">删除</a></center></span>"
                    }
                },*/
            ],
            "columnDefs": [
                {
                    "defaultContent": "",
                    "targets": "_all"
                }
            ],
            "oLanguage": {    // 语言设置
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                }
            }
        });


    });

    function open_layer(title, url, w, h) {
          //  alert(url)
        layer_show(title, url, w, h);

    }

    function setread(){
        if(arrayId.length===0){
            layer.msg('请选择删除的用户');
            return false;
        }
        dic[arrayId] = arrayId;
        var  readmessvodic = {"readmessvo":dic};
        console.log(readmessvodic);

        layer.confirm('确定要执行批量已读操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url:'adminUserDelJ',
                data:JSON.stringify(arrayId),
                async: false,
                error: function(request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function(data) {
                    if(data.code==100){
                        layer.msg('操作成功..');
                        location.reload();
                    }else{
                        layer.msg('系统错误，请联系后台管理员');
                    }
                }
            });
            layer.close(index);
        });
        return false;
    }
    //全选
    function allselect(){

        if(arrayId.length>0&&isall==0){
            //多选转为批量选择初始化CheckBox
            $("input[type='checkbox'][name='wd']").prop("checked",false);
        }else if (arrayId.length>0&&isall==1) {
            $("input[type='checkbox'][name='wd']").prop("checked",true);
        }
        isall=1;//初始状态转为全选状态

        if ($("input[type='checkbox'][name='wd']").prop("checked")) {

            $("input[type='checkbox'][name='wd']").prop("checked",false);  //取消全选
            //全部取消
            $("input[type='checkbox'][name='wd']").each(function(){
                //由于复选框一般选中的是多个,所以可以循环输出
                arrayId.splice(arrayId.contains(parseInt($(this).val())),1);//清空
            });
            // console.log(arrayId);
            $(".qxitem").text("全选");

            console.log(isall);
        }else {
            //先清空
            arrayId.splice(0);
            //全选
            $("input[type='checkbox'][name='wd']").prop("checked",true);//全选

            $("input[type='checkbox'][name='wd']").each(function(){
                //由于复选框一般选中的是多个,所以可以循环输出
                // console.log($(this).val());
                arrayId.push(parseInt($(this).val()));//追加
            });
            $(".qxitem").text("取消选择");


        }
    }
    //选择控件
    function checkboxOnclick(checkbox) {
        if ( checkbox.checked == true){
            //Action for checked
            isall=0;//默认多选设为初始状态
            arrayId.push(parseInt($(checkbox).val()));
            $(".qxitem").text("全选");

        }else{
            //Action for unchecked
            arrayId.splice(arrayId.contains(parseInt($(checkbox).val())),1);
        }

    }

    Array.prototype.contains = function(obj) {
        var i = this.length;
        while (i--) {
            if (this[i] === obj) {
                return i;  // 返回的这个 i 就是元素的索引下标，   
            }
        }
        return false;
    }

    function reload() {
        var index = layer.getFrameIndex(window.name);
        layer.close(index);
        location.reload();
    }

    function startobj(id) {
        layer.confirm('确定要执行此启用操作吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                cache: true,
                type: "POST",
                url: 'admUserEditAjax',
                data: {id: id, status: 1},
                async: false,
                error: function (request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function (data) {

                    if (data.code == 100) {
                        layer.msg('启用成功..');
                        location.reload();
                    } else {
                        layer.msg('系统错误，请联系后台管理员');
                    }

                }
            });
            layer.close(index);
        });
        return false;
    }
/*重置密码*/
    function resetobj(id){
        layer.confirm('确定要重置密码为111111吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'userresetpasswordajax',
                data:{id:id},
                async: false,
                error: function(request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function(data) {

                    if(data.code==100){
                        layer.msg('重置成功..');
                        location.reload();
                    }else{
                        layer.msg('系统错误，请联系后台管理员');
                    }

                }
            });
            layer.close(index);
        });
        return false;
    }

    function endobj(id) {
        layer.confirm('确定要执行此停用操作吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                cache: true,
                type: "POST",
                url: 'admUserEditAjax',
                data: {id: id},
                async: false,
                error: function (request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function (data) {

                    if (data.code == 100) {
                        layer.msg('停用成功..');
                        location.reload();
                    } else {
                        layer.msg('系统错误，请联系后台管理员');
                    }

                }
            });
            layer.close(index);
        });
        return false;
    }
/*修改可用状态*/
    function deleteobj(id) {
        layer.confirm('确定要执行此删除操作吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                cache: true,
                type: "POST",
                url: 'userDeleteAjax',
                data: {id: id},
                async: false,
                error: function (request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function (data) {

                    if (data.code == 100) {
                        layer.msg('删除成功..');
                        location.reload();
                    } else {
                        layer.msg('系统错误，请联系后台管理员');
                    }

                }
            });
            layer.close(index);
        });
        return false;
    }

    Date.prototype.format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "h+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }

    function deleteobj(id){
        layer.confirm('确定要执行此删除操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'admindelajax',
                data:{id:id},
                async: false,
                error: function(request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function(data) {

                    if(data.code==100){
                        layer.msg('删除成功..');
                        location.reload();
                    }else{
                        layer.msg('系统错误，请联系后台管理员');
                    }

                }
            });
            layer.close(index);
        });
        return false;
    }
</script>
</body>
</html>
