  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<c:set var="ctx" value="/spring3"/>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="h-ui.admin/css/H-ui.login.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>系统登录-腾润集市通</title>
<meta name="keywords" content="中知信">
<meta name="description" content="中知信">
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"><p class="p"><p></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" >
      <div class="row cl" style="margin-top: 1px">
        <label class="form-label col-xs-3"><i class="Hui-iconfont" style="color:#FFFFFF">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="account" name="" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl" >
        <label class="form-label col-xs-3"><i class="Hui-iconfont" style="color:#FFFFFF">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
          <label class="form-label col-xs-3"><i class="Hui-iconfont" style="color:#FFFFFF">&#xe615;</i></label>
        <div class="formControls col-xs-8">
          <input class="input-text size-s"  id="code" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img id="imgObj" src="logincode"  onclick="changeImg()"width="125" height="25" /> </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="button" onclick = "logindo();" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>

<div class="footer"><a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=37040302000202" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="h-ui/beian.png"/>鲁公网安备 37040302000202号</a>
  &nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="https://beian.miit.gov.cn" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">鲁ICP备2021035435号</a>
</div>
<script type="text/javascript" src="js/Hui-js/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/H-ui.min.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
function logindo(){
	/* var helfheight = document.documentElement.clientHeight/2;
	layer.msg('正在登录系统');
	 layer.load(0, {
		  shade: [0.3,'#000'], //0.1透明度的白色背景
		  offset: helfheight+30
		}); */
	var account = $('#account').val();
	var password = $('#password').val();
	var code = $('#code').val();
 	 $.ajax({
	    cache: true,
	    type: "POST",
	    url:'adminloginajax',
	    data:{account:account,password:password,code:code},
	    async: false,
		error: function(request) {
			layer.msg('网络连接错误，请联系管理员')
		    },
		success: function(data) {
			if(data.rcode==100){
				setTimeout("window.location.href='adminindex'",100);
			}else{
				//layer.closeAll();
				layer.msg(data.rinfo);
			}
       }
		});
}
function changeImg() {
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
  }
  //时间戳
  //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    url = url.substring(0, 17);
    if ((url.indexOf("&") >= 0)) {
      url = url + "×tamp=" + timestamp;
    } else {
      url = url + "?timestamp=" + timestamp;
    }
    return url;
  }

</script>
<script type="text/javascript">  
</script>
</body>
</html>