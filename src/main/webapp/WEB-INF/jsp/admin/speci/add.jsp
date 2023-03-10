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
                        <td class="item-name"><label>speci_id:</label></td>
                        <td><input name="speci_id" placeholder="" value="${speci_id}"></td>
                    </tr>
                    <tr style="display:none">
                        <td class="item-name"><label>commodity_info_id:</label></td>
                        <td><input name="commodity_info_id" placeholder="" value="${commodity_info_id}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>说明:</label></td>
                        <td><input name="speci_name" placeholder="请填写说明" value="${speci.speci_name}"></td>
                    </tr>
                   <%-- <tr>
                        <td class="item-name"><label>价格:</label></td>
                        <td><input name="speci_price" placeholder="只能填数字" value="${speci.speci_price}"></td>
                    </tr>--%>
                    <tr>
                        <td class="item-name"><label>会员价格:</label></td>
                        <td><input name="vip_price" placeholder="只能填数字" value="${speci.vip_price}"></td>
                    </tr>
                    <tr>
                        <td class="item-name"><label>规格:</label></td>
                        <td><input name="speci_regu" placeholder="请填写规格" value="${speci.speci_regu}"></td>
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
        $.ajax( {
            type : 'POST',
            url : 'adminspecieditajax',
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
