
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Wifi Analysis System Sign up / Login </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	

  

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='/wifi1/jsp/css/fontface.css' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="/wifi1/jsp/css/bootstrap.min.css">
	<link rel="stylesheet" href="/wifi1/jsp/css/animate.css">
	<link rel="stylesheet" href="/wifi1/jsp/css/style.css">


	<!-- Modernizr JS -->
	<script src="/wifi1/jsp/js/modernizr-2.6.2.min.js"></script>
	<script type="text/javascript">
window.onload=function(){
	change();
}
function change(){
	      if("${requestScope.str}"=="defeated")
	    { 
	    	 alert("登录失败!");
	    }
	      if("${requestScope.str1}"=="success")
	    	  {
	    	  alert("注册成功，请登录!");
	    	  }
}
</script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->


           <style> 
           
            .success{
                font-size:18px;
                color:red;
                text-align:center;
                
            }
           </style>
	</head>
	<body class="style-3">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<ul class="menu">
						<!--<li><a href="index.html">Style 1</a></li>
						<li><a href="index2.html">Style 2</a></li>-->
						<li class="active"><a>Wifi Analysis System</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-md-push-8">
					

					<!-- Start Sign In Form -->
					<form action="/wifi1/user/login.action" class="fh5co-form animate-box" data-animate-effect="fadeInRight" method="post">
						<h2>登录</h2>
						   
						<%-- <div class="alert alert-success" class="form-group">
							<div class="alert alert-success" role="alert">${requestScope.message }</div>
						</div> --%>
						<div class="form-group">
							<label for="username" class="sr-only">Username</label>
							<input type="text" class="form-control" id="username" name="username" placeholder="用户名" value="${requestScope.user.userName }" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">Password</label>
							<input type="password" class="form-control" id="password" name="password" placeholder="密码" autocomplete="off">
						</div>
						<!-- <div class="form-group">
							<label for="remember"><input type="checkbox" id="remember"> 记住密码 </label>
						</div> -->
						
						<div class="form-group">
							<p>无法登录?&nbsp&nbsp&nbsp <a href="/wifi1/jsp/sign-up3.jsp">注册&nbsp&nbsp</a> &nbsp</p>
						</div>
						<div class="success" role="alert">${requestScope.message }</div>
						<div class="form-group">
							<input type="submit" value="登录" class="btn btn-primary">
						</div>
					</form>
					<!-- END Sign In Form -->


				</div>
			</div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved. More Templates <a href="http://www.ysu.edu.cn/" target="_blank" title="燕山大学">燕山大学</a> - Collect from <a href="http://www.oracle.com" title="Oracle" target="_blank">Oracle</a></small></p></div>
			</div>
		</div>
	
	<!-- jQuery -->
	<script src="/wifi1/jsp/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="/wifi1/jsp/js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="/wifi1/jsp/js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="/wifi1/jsp/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="/wifi1/jsp/js/main.js"></script>

	</body>
</html>

