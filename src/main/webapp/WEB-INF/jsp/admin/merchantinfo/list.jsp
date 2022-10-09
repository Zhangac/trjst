<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!-- <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css" />  -->
    <script src="js/Hui-js/lib/jquery.js"></script>
    <script src="js/Hui-js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css"
          href="h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css"
          href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css"
          href="h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css"
          href="h-ui.admin/css/style.css" />
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
    <%--<style>
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

    </style>--%>
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
        <form action="" name="form" method="post" class="form form-horizontal"
              id="form">
            <div class="cl pd-5 bg-1 bk-gray mt-10"style="background-color: #ffffff">
                <%--<span class="l" style="margin-left:1600px;">
				 <a class="btn btn-primary radius" onclick="setread()" href="javascript:;">
				 <i class="Hui-iconfont"></i>设置配送员</a>
				</span>

                <span class="l" style="margin-left:1600px;margin-top: 10px;">
				 <a class="btn btn-primary radius qxitem" onclick="allselect()" href="javascript:;">
				 <i  class="Hui-iconfont  "></i>全选</a>
				</span>--%>
                <div class="panel" style="margin-left: 300px;">
                    <div class="panel-body">
                        <div class="text-c" >
                            <form class="Huiform" method="post" action="" target="_self" >
                                <div class="panel" >
                                    <div class="panel-body">
                                        <div class="text-l">
                                            <span>状&nbsp;&nbsp;&nbsp;&nbsp;态：</span>
                                            <select id="types">
                                                <option value="">请选择</option>
                                                <option value="0">下架</option>
                                                <option value="1">上架</option>
                                            </select>
                                            <br />
                                            <br />
                                            <span>商户名：</span>
                                            <input type="text" name="merchant_name" id="merchant_name" placeholder="商户名" style="width: 250px" class="input-text">
                                            <button name="" id="" style="margin-left: 20px;" class="btn btn-success" type="button" onclick="Inquire();"><i class="Hui-iconfont">&#xe665;</i> 搜索 </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
<%--                <span class="r">--%>
<%--                <ul class="sxUl">--%>
<%--                <li>--%>
<%--                <input class="sbmc" placeholder="请输入商标名称"/>--%>
<%--                <i onclick="sbmc()">查询</i>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                <input class="flhy" placeholder="请输入分类行业"/>--%>
<%--                <i onclick="flhy()">查询</i>--%>
<%--                </li>--%>
<%--                </ul>--%>
<%--                </span>--%>
                </div>
        </form>
        <span class="l" style="margin-left:1620px;position:absolute">
            <a style="position:absolute;" class="btn btn-primary radius" onclick="setread()" href="javascript:;">
                <i class="Hui-iconfont"></i>设置配送员</a>
            <a style="margin-left:100px;" class="btn btn-primary radius qxitem" onclick="allselect()" href="javascript:;">
                <i  class="Hui-iconfont  "></i>全选</a>
        </span>
    <div style="height: 10px"></div>
    <div style="width: 98%; margin-left: 10px;">
        <table id="example" class="table table-striped table-bordered" style="width: 1800px;">
            <thead>
            <tr>
                <th style="text-align: center;width: 2%">序号</th>
                <th style="text-align: center;width: 3%">id</th>
                <th style="text-align: center;width: 3%">用户id</th>
                <th style="text-align: center;width: 3%">区市id</th>
<%--                <th style="text-align: center;width: 3%">市场id</th>--%>
                <th style="text-align: center;width: 5%">商户名</th>
                <th style="text-align: center;width: 5%">地址</th>
                <th style="text-align: center;width: 5%">负责人名</th>
                <th style="text-align: center;width: 5%">描述</th>
                <th style="text-align: center;width: 5%">商户头像</th>
                <th style="text-align: center;width: 5%">身份证</th>
                <th style="text-align: center;width: 5%">营业执照</th>
                <th style="text-align: center;width: 5%">手机号</th>
                <th style="text-align: center;width: 3%">审核状态</th>
                <th style="text-align: center;width: 3%">上架状态</th>
                <%--<th style="text-align: center;width: 3%">推荐状态</th>--%>
                <th style="text-align: center;width: 3%">配送员id</th>
                <th style="text-align: center;width: 3%">配送员</th>

                <th style="text-align: center;width: 3%">类型</th>
                <th style="text-align: center;width: 3%">合同图片</th>
                <th style="text-align: center;width: 3%">是否审核</th>
                <th style="text-align: center;width: 3%">是否缴纳</th>
                <th style="text-align: center;width: 3%">缴纳金额</th>
                <th style="text-align: center;width: 5%">创建时间</th>
                <th style="text-align: center;width: 10%">操作</th>
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
            "ajax":{
                "url": "adminmilistajax",
                "type": "post",
                "data": function(d) {
                    d.merchant_name = $("#merchant_name").val();
                    d.status = $('#types option:selected').val();
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
                { "data": null,"targets": 0 },
                { "data": "id" },
                { "data": "user_id" },
                { "data": "area_id" },
                // { "data": "market_id" },
                { "data": "merchant_name" },
                { "data": "address" },
                { "data": "name" },
                { "data": "des" },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='../upload/"+obj.operate_head+"' onclick=\"open_layer('查看','../upload/"+obj.operate_head+"','600','450')\"/></center></span>"
                    }
                },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='../upload/"+obj.card+"' onclick=\"open_layer('查看','../upload/"+obj.card+"','600','450')\"/></center></span>"
                    }
                },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='../upload/"+obj.company_name+"' onclick=\"open_layer('查看','../upload/"+obj.company_name+"','600','450')\"/></center></span>"
                    }
                },
                { "data": "tel" },
                { "data":  function(obj){
                        if(obj.audit_status==0){
                            return "<span><center>待审核</center></span>"
                        }else if(obj.audit_status==1){
                            return "<span><center>已通过</center></span>"
                        }
                    }
                },
                { "data":  function(obj){
                        if(obj.status==0){
                            return "<span><center><a onclick=\"startobj("+obj.id+")\">上架</a></center></span>"
                        }else if(obj.status==1){
                            return "<span><center><a onclick=\"endobj("+obj.id+")\">下架</a></center></span>"
                        }
                    }
                },
                /*{ "data":  function(obj){
                        if(obj.is_hot==0){
                            return "<span><center><a onclick=\"startobj2("+obj.id+")\">不推荐</a></center></span>"
                        }else if(obj.is_hot==1){
                            return "<span><center><a onclick=\"endobj2("+obj.id+")\">推荐</a></center></span>"
                        }
                    }
                },*/
                { "data": "delivery_id" },
                { "data": "delivery_name" },

                { "data":  function(obj){
                        if(obj.type==1){
                            return "<span><center>普通商户</center></span>"
                        }else if(obj.type==2){
                            return "<span><center>配送员</center></span>"
                        }
                        else if(obj.type==3){
                            return "<span><center>酒店</center></span>"
                        }
                        else if(obj.type==4){
                            return "<span><center>药店</center></span>"
                        }
                        else if(obj.type==5){
                            return "<span><center>超市</center></span>"
                        }
                        else {
                            return "<span><center>其他</center></span>"
                        }
                    }
                },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='../upload/"+obj.contract+"' onclick=\"open_layer('查看','../upload/"+obj.contract+"','600','450')\"/></center></span>"
                    }
                },
                { "data":  function(obj){
                        if(obj.is_show==0){
                            return "<span><center><a onclick=\"startobj3("+obj.id+")\">不是</a></center></span>"
                        }else if(obj.is_show==1){
                            return "<span><center><a onclick=\"endobj3("+obj.id+")\">是</a></center></span>"
                        }
                    }
                },
                { "data":  function(obj){
                        if(obj.is_ruzhu==0){
                            return "<span><center>否</center></span>"
                        }else if(obj.is_ruzhu==1){
                            return "<span><center>是</center></span>"
                        }
                    }
                },
                { "data": "ruzhu_amount" },
                { "data": function (obj) {
                        return "<span><center>" + new Date(obj.create_time).format("yyyy-MM-dd hh:mm:ss")+ "</center></span>"
                    }
                },
                { "data": function(obj){
                        return "<span><center><input name=\"wd\" type=\"checkbox\" onclick=\"checkboxOnclick(this)\" value="+obj.id+"></span>&nbsp;&nbsp;"
                            /*+ "<a onclick=\"deleteobj(" + obj.id + ")\">删除</a></center></span>"*/
                    }
                }
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
    function startobj2(id){
        layer.confirm('确定要执行此启用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'adminmitjajax',
                data:{id:id,is_hot:1},
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
    function endobj2(id){
        layer.confirm('确定要执行此停用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'adminmitjajax',
                data:{id:id,is_hot:0},
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


    function startobj3(id){
        layer.confirm('确定要执行此启用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'adminshowajax',
                data:{id:id,is_show:1},
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
    function endobj3(id){
        layer.confirm('确定要执行此停用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'adminshowajax',
                data:{id:id,is_show:0},
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
    function open_layer(title, url, w, h) {
          //  alert(url)
        layer_show(title, url, w, h);

    }

    function setread(){
        if(arrayId.length===0){
            layer.msg('请选择商户');
            return false;
        }
        dic[arrayId] = arrayId;
        var  readmessvodic = {"readmessvo":dic};
        console.log(readmessvodic);
        layer_show("设置配送员","adminpsyadd?ids="+arrayId,"900","450");
        //window.location.href="adminareaadd";
        // layer.confirm('确定要执行批量已读操作吗?', {icon: 3, title:'提示'}, function(index){
        //     $.ajax({
        //         cache: true,
        //         type: "POST",
        //         contentType: "application/json; charset=utf-8",
        //         url:'adminUserDelJ',
        //         data:JSON.stringify(arrayId),
        //         async: false,
        //         error: function(request) {
        //             layer.msg("连接错误，请联系后台管理员");
        //         },
        //         success: function(data) {
        //             if(data.code==100){
        //                 layer.msg('操作成功..');
        //                 location.reload();
        //             }else{
        //                 layer.msg('系统错误，请联系后台管理员');
        //             }
        //         }
        //     });
        //     layer.close(index);
        // });
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
        layer.confirm('确定要上架吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                cache: true,
                type: "POST",
                url: 'adminmixjajax',
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
        layer.confirm('确定要下架吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                cache: true,
                type: "POST",
                url: 'adminmixjajax',
                data: {id: id,status:0},
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
                url: 'adminmidelajax',
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
</script>
</body>
</html>
