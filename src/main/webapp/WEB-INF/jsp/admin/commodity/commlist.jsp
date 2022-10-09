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
                                        <%--<span>分类名称：</span>
                                        <select id="types">
                                            <option value="0">请选择</option>
                                            <option value="1">用户ID查询</option>
                                            <option value="2">手机号查询</option>
                                        </select>--%>

                                            <input type="text" name="commodity_name" id="commodity_name" placeholder="商品名" style="width: 250px" class="input-text">
                                        <button name="" id="" style="margin-left: 20px;" class="btn btn-success" type="button" onclick="Inquire();"><i class="Hui-iconfont">&#xe665;</i> 搜索 </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </form>
    <!-- 定义一个表格元素 -->
    <div style="height: 10px"></div>
    <div style="width: 98%; margin-left: 10px">
        <table id="example" class="table table-striped table-bordered" style="width: 1800px;">
            <thead>
            <tr>
                <th style="text-align: center;width: 2%">序号</th>
                <th style="text-align: center;width: 2%">商品id</th>
                <th style="text-align: center;width: 2%">分类id</th>
                <th style="text-align: center;width: 5%">商品名</th>
                <th style="text-align: center;width: 2%">市场id</th>
                <th style="text-align: center;width: 2%">商户id</th>
                <th style="text-align: center;width: 5%">重量（kg）</th>
                <th style="text-align: center;width: 5%">规格型号</th>
                <th style="text-align: center;width: 5%">主图</th>
                <th style="text-align: center;width: 5%">列表图</th>
                <th style="text-align: center;width: 5%">商品详情</th>
                <th style="text-align: center;width: 2%">活动价</th>
                <th style="text-align: center;width: 2%">原价</th>
                <th style="text-align: center;width: 2%">审核状态</th>
                <th style="text-align: center;width: 2%">上架状态</th>
                <%--<th style="text-align: center;width: 2%">推荐状态</th>--%>
                <th style="text-align: center;width: 2%">库存</th>
                <th style="text-align: center;width: 5%">商品描述</th>
                <th style="text-align: center;width: 2%">已售数量</th>
                <th style="text-align: center;width: 7%">创建时间</th>
                <th style="text-align: center;width: 5%">操作</th>
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
                "url": "admincommlistajax",
                "type": "post",
                "data": function(d) {
                    d.audit_status=${audit_status};
                    d.commodity_name=$("#commodity_name").val();
                }
            },
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
                { "data": "id" },
                { "data": "assort_id" },
                { "data": "commodity_name" },
                { "data": "market_id" },
                { "data": "merchant_id" },
                { "data": "weight" },
                { "data": "specifications" },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='../upload/"+obj.main_pic+"' onclick=\"open_layer('查看','../upload/"+obj.main_pic+"','600','450')\"/></center></span>"
                    }
                },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='../upload/"+obj.list_pic+"' onclick=\"open_layer('查看','../upload/"+obj.list_pic+"','600','450')\"/></center></span>"
                    }
                },
                { "data": function(obj){
                        return "<span><center><img style='max-width:80px' src='../upload/"+obj.commodity_detail+"' onclick=\"open_layer('查看','../upload/"+obj.commodity_detail+"','600','450')\"</span>"
                    }
                },
                { "data": "activity_price" },
                { "data": "original_price" },
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
                            return "<span><center>下架</center></span>"
                        }else if(obj.status==1){
                            return "<span><center>上架</center></span>"
                        }
                    }
                },
                /*{ "data":  function(obj){
                        if(obj.is_hot==0){
                            return "<span><center>不推荐</center></span>"
                        }else if(obj.is_hot==1){
                            return "<span><center>推荐</center></span>"
                        }
                    }
                },*/
                { "data": "stock" },
                { "data": "des" },
                { "data": "sold" },
                { "data": function (obj) {
                        return "<span><center>" + new Date(obj.create_time).format("yyyy-MM-dd hh:mm:ss")+ "</center></span>"
                    }
                },
                { "data": function(obj){
                        return "<span><center><a onclick=\"endobj("+obj.id+")\">审核</a></center></span>"
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
                url:'admdeletebannerajax',
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

    function startobj(id){
        layer.confirm('确定要执行此启用操作吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                cache: true,
                type: "POST",
                url:'setbanneronajax',
                data:{id:id},
                async: false,
                error: function(request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function(data) {

                    if(data.code==100){
                        layer.msg('启用成功..');
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
                url:'admincommeditajax',
                data:{id:id},
                async: false,
                error: function(request) {
                    layer.msg("连接错误，请联系后台管理员");
                },
                success: function(data) {

                    if(data.code==100){
                        layer.msg('停用成功..');
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
</script>
</body>
</html>
