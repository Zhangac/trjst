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
                        <td class="item-name"><label>区id:</label></td>
                        <td><input name="area_id" placeholder="" value="${market.area_id}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>市场名称:</label></td>
                        <td><input name="market_name" placeholder="" value="${market.market_name}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>市场图片:</label></td>
                        <td><input type="file" name="pic" id="pic" accept="image/*" />
                        </td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>省:</label></td>
                        <td><input name="market_province" placeholder="" value="${market.market_province}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>市:</label></td>
                        <td><input name="market_city" placeholder="" value="${market.market_city}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>区:</label></td>
                        <td><input name="market_region" placeholder="" value="${market.market_region}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>地址:</label></td>
                        <td><input name="market_address" placeholder="" value="${market.market_address}"></td>
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
        var files = document.getElementById('pic').files; //files是文件选择框选择的文件对象数组
        var id= $("[name='id']").val();
        var area_id= $("[name='area_id']").val();
        var market_name= $("[name='market_name']").val();
        var market_province= $("[name='market_province']").val();
        var market_city= $("[name='market_city']").val();
        var market_region= $("[name='market_region']").val();
        var market_address= $("[name='market_address']").val();
        if(files.length == 0) {
            $.ajax( {
                type : 'POST',
                url : 'adminmarketeditajax',
                data : {id:id,market_img:null,area_id:area_id,market_name:market_name,
                        market_province:market_province,market_city:market_city,
                        market_region:market_region,market_address:market_address},
                success : function(data) {
                    if(data.code==100){
                        alert("提交成功");
                        parent.reload();
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


                var pic = JSON.parse(result.response).filepath;
                $.ajax( {
                    type : 'POST',
                    url : 'adminmarketeditajax',
                    data : {id:id,market_img:pic,area_id:area_id,market_name:market_name,
                            market_province:market_province,market_city:market_city,
                            market_region:market_region,market_address:market_address},
                    success : function(data) {
                        if(data.code==100){
                            alert("提交成功");
                            parent.reload();
                        }else{
                            alert("提交失败");
                        }

                    }
                });
            }
        });
        xhr.send(form); //开始上传
    }
</script>

</body>
</html>
