static<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit/">
	<link rel="Bookmark" href="/favicon.ico" >
	<link rel="Shortcut Icon" href="/favicon.ico" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="js/html5shiv.js"></script>
	<script type="text/javascript" src="js/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>腾润集市通管理系统</title>

</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="adminindex">腾润集市通管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="#"></a>
			<span class="logo navbar-slogan f-l mr-10 hidden-xs"></span>
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li class="dropDown dropDown_hover">
						<input id="uname" value="${name}" style="display:none">
						<a href="#" class="dropDown_A" >${name} <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<!-- <li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li> -->
							<li><a href="adminlogin">切换账户</a></li>
							<li><a href="adminlogin">退出</a></li>
						</ul>
					</li>
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
							<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<c:if test="${admin.level==1}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminlist" data-title="管理员列表" href="javascript:void(0)">- 管理员列表</a></li>
					</ul>
				</dd>
			</dl>
		</c:if>
		<c:if test="${admin.level==1}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe60d;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminuserlist" data-title="用户列表" href="javascript:void(0)">- 用户列表</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1 || admin.level==2}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 轮播管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="admhomebannerlist" data-title="主页轮播图" href="javascript:void(0)">- 主页轮播图</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1 || admin.level==2}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 区市管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminarealist" data-title="区市管理" href="javascript:void(0)">- 区市管理</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1 || admin.level==2}">
			<%--<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 市场管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminmarketlist" data-title="市场管理" href="javascript:void(0)">- 市场管理</a></li>
					</ul>
				</dd>
			</dl>--%>
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 商户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminmilist" data-title="商户管理" href="javascript:void(0)">- 商户管理</a></li>
					</ul>
					<ul>
						<li><a data-href="adminmiaslist?audit_status=0" data-title="待审核" href="javascript:void(0)">- 待审核</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1 || admin.level==2}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 分类管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminassortlist" data-title="分类管理" href="javascript:void(0)">- 分类管理</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1 || admin.level==2}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 商品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="admincommlist" data-title="商品管理" href="javascript:void(0)">- 商品管理</a></li>
					</ul>
					<%--<ul>
						<li><a data-href="admincommaslist?audit_status=0" data-title="待审核" href="javascript:void(0)">- 待审核</a></li>
					</ul>--%>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1 || admin.level==2}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 订单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminorderlist" data-title="订单管理" href="javascript:void(0)">- 订单管理</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1 || admin.level==2}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 配送员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="admindeliverylist" data-title="配送员管理" href="javascript:void(0)">- 配送员管理</a></li>
					</ul>
					<ul>
						<li><a data-href="admindeliverydslist?status=0" data-title="待审核" href="javascript:void(0)">- 待审核</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 提现管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminwithlist" data-title="提现管理" href="javascript:void(0)">- 提现管理</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<%--<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 充值管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminrechlist" data-title="充值管理" href="javascript:void(0)">- 充值管理</a></li>
					</ul>
				</dd>
			</dl>--%>
			<c:if test="${admin.level==1}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 入驻金额管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminBrokeragelist" data-title="入驻金额管理" href="javascript:void(0)">- 入驻金额管理</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
			<c:if test="${admin.level==1}">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe625;</i> 商品操作日志<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="adminoperationRecordlist" data-title="商品操作日志" href="javascript:void(0)">- 商品操作日志</a></li>
					</ul>
				</dd>
			</dl>
			</c:if>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" href="#" onclick="getgg();">我的桌面</span>
					<em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="adminwelcome"></iframe>
		</div>
	</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
	</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="js/Hui-js/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/Hui-js/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
	// function reloadpage(){
	// 	var a = $('#name').val();
	// 	alert(a);
	// 	window.location.href="adminlogin";
	// }
	$(document).ready(function() {
		var a = $('#uname').val();
		// alert(a);
		if(a==""){
			 window.location.href="adminlogin";
		}
	});
</script>
</body>
</html>