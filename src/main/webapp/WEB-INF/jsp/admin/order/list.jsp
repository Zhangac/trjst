<!-- 此例子是结合bootstrap的Datatables，暂且定位为最基本的例子吧 -->
<!-- 引入必须的css和js文件 -->
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
</head>
<body>
    <form action="" name="form" method="post" class="form form-horizontal" id="form">
            <div class="panel" style="margin-left: 300px;">
                <div class="panel-body">
                    <div class="text-c" >
                        <form class="Huiform" method="post" action="" target="_self" >
                            <div class="panel" >
                                <div class="panel-body">
                                    <div class="text-l">
                                        <span>状&nbsp;&nbsp;&nbsp;&nbsp;态：</span>
                                        <select id="types">
                                            <option value="-1">请选择</option>
                                            <option value="1">待支付</option>
                                            <option value="2">支付成功/待发货</option>
                                            <option value="7">待送达</option>
                                            <option value="8">已签收</option>
                                            <option value="9">退款中</option>
                                            <option value="10">已退款</option>
                                            <option value="11">拒绝退款</option>
                                        </select>
                                        <br />
                                        <br />
                                        <span>配送员：</span>
                                        <input type="text" name="delivery_name" id="delivery_name" placeholder="配送员姓名" style="width: 250px" class="input-text">
                                        <br />
                                        <br />
                                        <span>手机号：</span>
                                        <input type="text" name="phone" id="phone" placeholder="收货人手机号" style="width: 250px" class="input-text">
                                        <br />
                                        <br />
                                        <span>日&nbsp;&nbsp;&nbsp;期：</span>
                                        <input type="text"
                                               onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
                                               id="logmax" class="input-text Wdate" style="width: 120px;" autocomplete="off">
                                        -
                                        <input type="text"
                                               onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
                                               id="logmin" class="input-text Wdate" style="width: 120px;" autocomplete="off">
                                        <br />
                                        <br />
                                        <span>订单号：</span>
                                        <input type="text" name="order_no" id="order_no" placeholder="订单号" style="width: 250px" class="input-text">
                                        <button name="" id="" style="margin-left: 20px;" class="btn btn-success" type="button" onclick="Inquire();"><i class="Hui-iconfont">&#xe665;</i> 搜索 </button>
                                        <input class="btn btn-danger radius" type="button" value="打印导出" onclick="download()"/>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </form>
<%--    <div class="panel mt-20" style="margin-left: 1700px;">--%>
<%--        <div class="panel-body">--%>
<%--            <div class="clearfix">--%>
<%--						<span class="f-l">--%>
<%--							<input class="btn btn-danger radius" type="button" value="导出Excel" onclick="download()"/>--%>
<%--						</span>--%>
<%--                <!-- <span class="r">共有数据：<strong>54</strong> 条</span> -->--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <!-- 定义一个表格元素 -->
    <div style="height: 10px"></div>
    <div style="width: 98%; margin-left: 10px">
        <table id="example" class="table table-striped table-bordered" style="width: 2500px;">
            <thead>
            <tr>
                <th style="text-align: center;width: 10%">序号</th>
                <th style="text-align: center;width: 10%">配送员</th>
                <th style="text-align: center;width: 10%">商品</th>
                <th style="text-align: center;width: 10%">收货手机号</th>
                <th style="text-align: center;width: 10%">收获地址</th>
                <th style="text-align: center;width: 10%">详细地址</th>
                <th style="text-align: center;width: 10%">收货姓名</th>
                <th style="text-align: center;width: 5%">斤/件/箱</th>
                <th style="text-align: center;width: 5%">数量</th>
                <th style="text-align: center;width: 5%">单价</th>
                <th style="text-align: center;width: 10%">总价</th>
                <th style="text-align: center;width: 10%">支付金额</th>
                <th style="text-align: center;width: 10%">佣金</th>
                <th style="text-align: center;width: 10%">订单状态</th>
                <th style="text-align: center;width: 10%">支付时间</th>
                <th style="text-align: center;width: 10%">发货时间</th>
                <th style="text-align: center;width: 10%">送达时间</th>
                <th style="text-align: center;width: 10%">创建时间</th>
                <th style="text-align: center;width: 10%">差价状态</th>
                <th style="text-align: center;width: 10%">差价流水</th>
                <%--<th style="text-align: center;width: 10%">收货状态</th>
                <th style="text-align: center;width: 10%">收货时间</th>--%>
                <%--<th style="text-align: center;width: 10%">预付金额</th>--%>
                <th style="text-align: center;width: 10%">差价</th>
                <th style="text-align: center;width: 5%">差价斤/件/箱</th>
                <th style="text-align: center;width: 10%">订单类型</th>
                <%--<th style="text-align: center;width: 10%">订单描述</th>--%>

                <th style="text-align: center;width: 10%">订单号</th>
                <th style="text-align: center;width: 10%">支付流水</th>
                <th style="text-align: center;width: 10%">订单金额</th>
                <th style="text-align: center;width: 10%">配送员id</th>
                <th style="text-align: center;width: 10%">用户id</th>
                <th style="text-align: center;width: 10%">商户id</th>
                <th style="text-align: center;width: 10%">商品id</th>
                <th style="text-align: center;width: 10%">支付类型</th>
                <th style="text-align: center;width: 10%">差价支付类型</th>
                <th style="text-align: center;width: 2%">配送员佣金</th>
                <th style="text-align: center;width: 2%">拒绝退款原因</th>
                <th style="text-align: center;width: 10%">业务员id</th>
                <th style="text-align: center;width: 10%">操作</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function Inquire() {
        datatable.ajax.reload();
    }
    /*Javascript代码片段*/
    $(document).ready(function() {
        $('#example').dataTable().fnDestroy();
        datatable  =  $('#example').DataTable({
            "ajax":{
                "url": "adminorderlistajax",
                "type": "post",
                "data": function(d) {
                    d.order_no = $("#order_no").val();
                    d.pay_status = $('#types option:selected').val();
                    d.delivery_name = $("#delivery_name").val();
                    d.logmax=$("#logmax").val();
                    d.logmin=$("#logmin").val();
                    d.phone=$("#phone").val();
                    d.area_id = ${area};
                }
            },
            // "colReorder": true,//启动列拖动
            // "scrollX": true,//左右滚动条
            "lengthChange": true,//是否允许用户自定义显示数量
            "bPaginate": true, //翻页功能
            "bFilter": false, //列筛序功能
            "searching": true,//本地搜索
            "ordering": false, //排序功能
            "Info": true,//页脚信息
            "autoWidth": true,//自动宽度
            "serverSide":true,
            /* "bLengthChange":false,  */
            "searching" : false,
            "fnDrawCallback" : function(){
                this.api().column(0).nodes().each(function(cell, i) {
                    cell.innerHTML =  i + 1;
                });
            },
            "columns": [
                { "data": null,"targets": 0 },
                { "data": "delivery_name" },
                { "data": "commodity_name" },
                { "data": "phone" },
                { "data": "address" },
                { "data": "detailed_address" },
                { "data": "name" },
                { "data": "jin_num" },
                /*{ "data": function (obj) {
                        if(obj.jin_num==0){
                            return "<span><center>" + obj.jin_num +"</center></span>"
                        }else {
                            return "<span><center>" + obj.jin_num + obj.ass_yuliu+"</center></span>"
                        }
                    }
                },*/
                {"data": "quantity" },
                {"data": "unit_price" },
                { "data": "total_price" },
                { "data": "pay_price" },
                { "data": "commission" },
                { "data":  function(obj){
                        if(obj.pay_status==0){
                            return "<span><center>未支付</center></span>"
                        }else if(obj.pay_status==1){
                            return "<span><center>待支付</center></span>"
                        } else if(obj.pay_status==2){
                            return "<span><center>支付成功/待发货</center></span>"
                        } else if(obj.pay_status==3){
                            return "<span><center>支付失败</center></span>"
                        } else if(obj.pay_status==4){
                            return "<span><center>支付超时</center></span>"
                        } else if(obj.pay_status==5){
                            return "<span><center>处理中</center></span>"
                        } else if(obj.pay_status==6){
                            return "<span><center>取消订单</center></span>"
                        } else if(obj.pay_status==7){
                            return "<span><center>待收货</center></span>"
                        } else if(obj.pay_status==8){
                            return "<span><center>已签收</center></span>"
                        } else if(obj.pay_status==9){
                            return "<span><center>退款中</center></span>"
                        } else if(obj.pay_status==10){
                            return "<span><center>已退款</center></span>"
                        }else if(obj.pay_status==11){
                            return "<span><center>拒绝退款</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": function (obj) {
                        if(obj.pay_time != null){
                            return "<span><center>" + new Date(obj.pay_time).format("yyyy-MM-dd hh:mm:ss")+ "</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": function (obj) {
                        if(obj.fahuo_time != null){
                            return "<span><center>" + new Date(obj.fahuo_time).format("yyyy-MM-dd hh:mm:ss")+ "</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": function (obj) {
                        if(obj.arrived_time != null){
                            return "<span><center>" + new Date(obj.arrived_time).format("yyyy-MM-dd hh:mm:ss")+ "</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": function (obj) {
                        return "<span><center>" + new Date(obj.create_time).format("yyyy-MM-dd hh:mm:ss")+ "</center></span>"
                    }
                },
                { "data":  function(obj){
                        if(obj.spread_status==1){
                            return "<span><center>待支付</center></span>"
                        } else if(obj.spread_status==2){
                            return "<span><center>支付成功</center></span>"
                        } else if(obj.spread_status==9){
                            return "<span><center>退款中</center></span>"
                        } else if(obj.spread_status==10){
                            return "<span><center>已退款</center></span>"
                        } else if(obj.spread_status==11){
                            return "<span><center>拒绝退款</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": "spread_no" },
                { "data": "spread_price" },
                { "data": function (obj) {
                        if(obj.spread_jin_num==0){
                            return "<span><center>" + obj.spread_jin_num +"</center></span>"
                        }else {
                            return "<span><center>" + obj.spread_jin_num + obj.ass_yuliu+"</center></span>"
                        }
                    }
                },
                { "data":  function(obj){
                        if(obj.type==1){
                            return "<span><center>基础订单</center></span>"
                        }else if(obj.type==2){
                            return "<span><center>充值订单</center></span>"
                        }else if(obj.type==3){
                            return "<span><center>入驻押金订单</center></span>"
                        } else if(obj.type==4){
                            return "<span><center>配送员入驻押金订单</center></span>"
                        } else if(obj.type==5){
                            return "<span><center>成为会员充值</center></span>"
                        } else if(obj.type==6){
                            return "<span><center>成为业务员</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": "order_no" },
                { "data": "pay_no" },
                { "data": "order_price" },
                { "data": "delivery_id" },
                { "data": "user_id" },
                { "data": "merchant_id" },
                { "data": "commodity_id" },


                /*{ "data":  function(obj){
                        if(obj.confirm_receipt==0){
                            return "<span><center>出货中</center></span>"
                        }else if(obj.confirm_receipt==1){
                            return "<span><center>配送中</center></span>"
                        } else if(obj.confirm_receipt==2){
                            return "<span><center>已到达</center></span>"
                        } else if(obj.confirm_receipt==3){
                            return "<span><center>已收货</center></span>"
                        } else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": function (obj) {
                        if(obj.confirm_time != null){
                            return "<span><center>" + new Date(obj.confirm_time).format("yyyy-MM-dd hh:mm:ss")+ "</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data": "reserve_price" },
                {"data": "goods_desc" },*/
                { "data":  function(obj){
                        if(obj.pay_type==1){
                            return "<span><center>余额</center></span>"
                        } else if(obj.pay_type==2){
                            return "<span><center>微信</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                { "data":  function(obj){
                        if(obj.spread_pay_type==1){
                            return "<span><center>余额</center></span>"
                        } else if(obj.spread_pay_type==2){
                            return "<span><center>微信</center></span>"
                        }else {
                            return "<span><center></center></span>"
                        }
                    }
                },
                {"data": "order_psy_yongjin" },
                {"data": "tk_reason" },
                {"data": "salesman_id" },
                { "data": function(obj){
                    if(${area==0}) {
                        return "<span><center>" +
                            "<a onclick=\"open_layer('更改配送员','adminggpsy?id=" + obj.id + "','900','450')\">更改配送员</a>" /*+
                            "&nbsp;<a onclick=\"deleteobj(" + obj.id + ")\">删除</a></center></span>"*/
                    }
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

    function  open_layer(title,url,w,h){

        layer_show(title,url,w,h);

    }
    function  img_big() {
        console.log("111")
        var _this = $(this);
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    }

    function imgShow(outerdiv, innerdiv, bigimg, _this){
        console.log("222")
        var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
        console.log(src)
        $(bigimg).attr("src", src);//设置#bigimg元素的src属性
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/
        $("<img/>").attr("src", src).load(function(){
            console.log("333")
            var windowW = $(window).width();//获取当前窗口宽度
            var windowH = $(window).height();//获取当前窗口高度
            var realWidth = this.width;//获取图片真实宽度
            var realHeight = this.height;//获取图片真实高度
            var imgWidth, imgHeight;
            var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
            if(realHeight>windowH*scale) {//判断图片高度
                imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
                imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
                if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                    imgWidth = windowW*scale;//再对宽度进行缩放
                }
            } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
                imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
                imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
            } else {//如果图片真实高度和宽度都符合要求，高宽不变
                imgWidth = realWidth;
                imgHeight = realHeight;
            }
            $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
            var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
            var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
            $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
            $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
        });
        $(outerdiv).click(function(){//再次点击淡出消失弹出层
            $(this).fadeOut("fast");
        });
    }
    function  reload(){
        var index = layer.getFrameIndex(window.name);
        layer.close(index);
        location.reload();
    }
    function deleteobj(id){
        layer.confirm('确定要执行此删除操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'adminorderdelajax',
                data:{id:id},
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

    function startobj(id){
        layer.confirm('确定要执行此启用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'admincommxjajax',
                data:{id:id,status:1},
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
    function endobj(id){
        layer.confirm('确定要执行此停用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'admincommxjajax',
                data:{id:id,status:0},
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

    function startobj2(id){
        layer.confirm('确定要执行此启用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'admincommtjajax',
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
                url:'admincommtjajax',
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

    //图片展示
    function imgcli(obj){
        console.log($(obj).attr("src"))
        layer_show2('图片',$(obj).attr("src"),'1200','800');
    }

    function download(){
        var order_no = $("#order_no").val();
        var pay_status = $('#types option:selected').val();
        var delivery_name = $("#delivery_name").val();
        var logmax = $("#logmax").val();
        var logmin = $("#logmin").val();
        var phone = $("#phone").val();
        var url="adminOrderExcel/download_excel?order_no="
            +order_no+"&pay_status="+pay_status+"&delivery_name="
            +delivery_name +"&logmax="+logmax+"&logmin="+logmin
            +"&phone="+phone;
        window.open(url);
    }
</script>
</body>
</html>
