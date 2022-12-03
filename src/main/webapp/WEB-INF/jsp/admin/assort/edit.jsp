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
                        <td class="item-name"><label>名称:</label></td>
                        <td><input name="name" placeholder="例：水果" value="${assort.name}"></td>
                        </td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>佣金类型:</label></td>
                        <td><select class="select" id="percent" name="percent" size="1">
                            <option value=0>请选择</option>
                            <option value=1 <c:if test="${assort.percent==1}"> selected="selected"</c:if>>固定金额</option>
                            <option value=2 <c:if test="${assort.percent==2}"> selected="selected"</c:if>>百分比</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>佣金:</label></td>
                        <td><input name="yongjin" placeholder="0.00 百分比的话也是填写数字" value="${assort.yongjin}"></td>
                        </td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>规格:</label></td>
                        <td><input name="yuliu" placeholder="斤/件/箱" value="${assort.yuliu}"></td>
                        </td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>排序:</label></td>
                        <td><input name="sort" placeholder="默认0" value="${assort.sort}"></td>
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

        var params = $("#form").serialize();

        var name= $("[name='name']").val();
        if($.trim(name)==""){
            layer.msg('名称不能为空！');
            return false;
        }

        var sort= $("[name='sort']").val();
        if($.trim(sort)==""){
            layer.msg('排序不能为空！');
            return false;
        }

        var percent = $('#percent option:selected').val();
        if($.trim(percent)=="" || $.trim(percent)==0){
            layer.msg('请选择佣金类型！');
            return false;
        }

        $.ajax( {
            type : 'POST',
            url : 'adminassorteditajax',
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

    }

</script>

</body>
</html>
