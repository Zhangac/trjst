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
                        <td class="item-name"><label>fk_id:</label></td>
                        <td><input name="fk_id" placeholder="" value="${fk_id}"></td>
                    </tr>
                    <tr style="display:none">
                        <td class="item-name"><label>type:</label></td>
                        <td><input name="type" placeholder="" value="${type}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>图片:</label></td>
                        <td><input type="file" name="pic" id="pic" accept="image/*" />
                        </td>
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
        var fk_id= $("[name='fk_id']").val();
        var type= $("[name='type']").val();
        if(files.length == 0) {
            $.ajax( {
                type : 'POST',
                url : 'adminimageaddajax',
                data : {fk_id:fk_id,pic:null,type:type},
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
                    url : 'adminimageaddajax',
                    data : {fk_id:fk_id,pic:pic,type:type},
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
