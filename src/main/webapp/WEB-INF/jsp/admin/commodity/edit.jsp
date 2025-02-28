<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=10" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta name="viewport" content="initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <link rel="stylesheet" type="text/css" href="css/merchant/index.css">
    <link rel="stylesheet" href="css/merchant/style.css" type="text/css" />
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
<body>
<div id="merchant-main">
    <div id="merchant-info">
        <form  id="form"  action="" method= "post">

            <div class="content">
                <table>
                    <tr style="display:none">
                        <td class="item-name"><label>id:</label></td>
                        <td><input name="id" placeholder="" value="${id}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>分类id:</label></td>
                        <td><input name="assort_id" placeholder="请输入分类id,请到分类管理列表里查看第二列相对应id" value="${mi.assort_id}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>商品名:</label></td>
                        <td><input name="commodity_name" placeholder="商品名" value="${mi.commodity_name}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>区市id:</label></td>
                        <td><input name="area_id" placeholder="请输入区市id,请到区市管理列表里查看第二列相对应id" value="${mi.area_id}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>商户id:</label></td>
                        <td><input name="merchant_id" placeholder="请输入商户id,请到商户管理列表里查看第二列相对应id" value="${mi.merchant_id}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>重量（kg）:</label></td>
                        <td><input name="weight" placeholder="重量（kg）" value="${mi.weight}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>主图:</label></td>
                        <td><input type="file" name="main_pic" id="main_pic" accept="image/*" />
                        </td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>商品详情:</label></td>
                        <td><input name="commodity_detail" placeholder="商品详情" value="${mi.commodity_detail}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>活动价:</label></td>
                        <td><input name="activity_price" placeholder="活动价" value="${mi.activity_price}"></td>
                    </tr>
                    <%--<tr>
                        <td class="item-name"><label>原价:</label></td>
                        <td><input name="original_price" placeholder="原价" value="${mi.original_price}"></td>
                    </tr>--%>
                    <tr>
                        <td class="item-name"><label>库存:</label></td>
                        <td><input name="stock" placeholder="默认0" value="${mi.stock}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>已售:</label></td>
                        <td><input name="sold" placeholder="默认0" value="${mi.sold}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>上下架:</label></td>
                        <td><input name="status" placeholder="请填写0或者1  默认0：下架  1：上架" value="${mi.status}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>排序:</label></td>
                        <td><input name="sort" placeholder="请填写顺序数字1最靠前以此类推  默认0：不排序  1：排序以此类推" value="${mi.sort}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>置顶:</label></td>
                        <td><input name="topPing" placeholder="请填写数字0或者1  默认0：不置顶  1：置顶" value="${mi.topPing}"></td>
                    </tr>
                </table>
                <div class="footer">
                    <input type="button" onclick="tijiao();" value="提交"/>
                </div>
            </div>
        </form>
    </div>
</div>



<script>
    function tijiao(){
        var files = document.getElementById('main_pic').files; //files是文件选择框选择的文件对象数组
        var id= $("[name='id']").val();
        var assort_id= $("[name='assort_id']").val();
        var sort= $("[name='sort']").val();
        var topPing= $("[name='topPing']").val();
        if($.trim(assort_id)==""){
            layer.msg('分类id不能为空！');
            return false;
        }
        var commodity_name= $("[name='commodity_name']").val();
        if($.trim(commodity_name)==""){
            layer.msg('商品名不能为空！');
            return false;
        }
        var area_id= $("[name='area_id']").val();
        if($.trim(area_id)==""){
            layer.msg('区市id不能为空！');
            return false;
        }
        var merchant_id= $("[name='merchant_id']").val();
        if($.trim(merchant_id)==""){
            layer.msg('商户id不能为空！');
            return false;
        }
        var weight= $("[name='weight']").val();
        /*if($.trim(weight)==""){
            layer.msg('重量不能为空！');
            return false;
        }*/
        var commodity_detail= $("[name='commodity_detail']").val();
        if($.trim(commodity_detail)==""){
            layer.msg('商品详情不能为空！');
            return false;
        }
        var activity_price= $("[name='activity_price']").val();
        if($.trim(activity_price)==""){
            layer.msg('价格不能为空！');
            return false;
        }
        /*var original_price= $("[name='original_price']").val();
        if($.trim(original_price)==""){
            layer.msg('原价不能为空！');
            return false;
        }*/
        var stock= $("[name='stock']").val();
        if($.trim(stock)==""){
            layer.msg('库存不能为空！');
            return false;
        }
        var status= $("[name='status']").val();
        if($.trim(status)==""){
            layer.msg('上下架状态不能为空！');
            return false;
        }
        var params = $("#form").serialize();
        if(files.length == 0) {
            $.ajax( {
                type : 'POST',
                url : 'admincommeditAjax',
                dataType:"JSON",
                data :params,
                /*data : {
                    id:id,
                    assort_id:assort_id,
                    commodity_name:commodity_name,
                    area_id:area_id,
                    merchant_id:merchant_id,
                    weight:weight,
                    commodity_detail:commodity_detail,
                    activity_price:activity_price,
                    original_price:original_price,
                    stock:stock,
                    status:status,
                    main_pic:null
                },*/
                success : function(data) {
                    if(data.code==100){
                        alert("提交成功");
                        parent.reload();
                    }else if(data.code==201){
                        alert("没有此分类，请重新输入分类id");
                    }else{
                        alert("提交失败");
                    }

                }
            });
        }
        var form = new FormData(),
            url = 'uploadfile', //服务器上传地址
            file = files[0];
        form.append('file', file);

        var xhr = new XMLHttpRequest();
        xhr.open("post", url, true);

        //上传进度事件
        xhr.upload.addEventListener("progress", function(result) {
            if (result.lengthComputable) {
                //上传进度
                var percent = (result.loaded / result.total * 100).toFixed(2);
            }
        }, 	false);

        xhr.addEventListener("readystatechange", function() {
            var result = xhr;
            if (result.status != 200) { //error
                console.log('上传失败', result.status, result.statusText, result.response);
                //alert("上传失败");
            }else if (result.readyState == 4) { //finished
                console.log('上传成功', result);
                console.log(JSON.parse(result.response).key);


                var main_pic = JSON.parse(result.response).filepath;
                var urlParams = new URLSearchParams(params); // 将序列化的字符串转换为 URLSearchParams 对象
                urlParams.set("main_pic", main_pic); // 设置新的键值对
                params = urlParams.toString();
                $.ajax( {
                    type : 'POST',
                    url : 'admincommeditAjax',
                    data :params,
                    /*data : {
                        id:id,
                        assort_id:assort_id,
                        commodity_name:commodity_name,
                        area_id:area_id,
                        merchant_id:merchant_id,
                        weight:weight,
                        commodity_detail:commodity_detail,
                        activity_price:activity_price,
                        original_price:original_price,
                        stock:stock,
                        status:status,
                        main_pic:main_pic
                    },*/
                    success : function(data) {
                        if(data.code==100){
                            alert("提交成功");
                            parent.reload();
                        }else if(data.code==201){
                            alert("没有此分类，请重新输入分类id");
                        }else{
                            alert("提交失败");
                        }

                    }
                });
            }
        });
        xhr.send(form); //开始上传

    }
    /*function tijiao(){

        var params = $("#form").serialize();

        var pic= $("[name='pic']").val();
        if($.trim(pic)==""){
            layer.msg('图片不能为空！');
            return false;
        }

        var sort= $("[name='sort']").val();
        if($.trim(sort)==""){
            layer.msg('排序不能为空！');
            return false;
        }

        $.ajax( {
            type : 'POST',
            url : 'admbannereditajax',
            data : params,
            success : function(data) {
                if(data.code==100){
                    alert("提交成功");
                    parent.reload();
                }else{
                    alert("提交失败");
                }

            }
        });

    }*/

</script>

</body>
</html>
