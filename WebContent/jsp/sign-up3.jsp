
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
	<title>Wifi Analysis System Sign up / Login</title>
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
	<script src="/wifi1/jsp/jquery-1.7.2.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
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
 <script>
            $(function(){
                //失去焦点blur 
                var username_result = false;
                $("#username").blur(function(){
                    if($("#username").val() == ""){
                        $("#span1").html("用户名不能为空!");
                        username_result = false;
                    }
                    else if(/^\w{6,30}$/.test($("#username").val()) ==false){
                        $("#span1").html("用户名格式错误!!");
                        username_result = false;
                    }
                    else{
                    	var username_val = $("#username").val();
              		  /* alert(username_val); */
              		 $.ajax({
                         async:true,
                         data:{"username":username_val},
                         dataType:"text",
                         url:"/wifi1/user/checkName.action",
                         error:function(xhr,status){
                       	  console.log(status);
                         },
                         success:function(data){
                         //$("#span1").html(data);
                         if(data=="success"){
                         username_result=true;
                         $("#span1").html("用户名可用");
                         }
                         else{
                         username_result=false;
                         $("#span1").html("用户名已存在");
                         }
                         }                  
                         });
                    }
                });

                
                var password_result = false;
                $("#password").blur(function(){
                    if($("#password").val() == ""){
                        $("#span2").html("密码不能为空!");
                        password_result = false;
                    }
                    else if(/^\w{6,30}$/.test($("#password").val()) ==false){
                        $("#span2").html("密码格式错误!!");
                        password_result = false;
                    }
                    else{
                    	
                    	$("#span2").html("密码可用!!");
                    	password_result = true;
                    }
                });
                
                
                //表单提交的时候
                $("#myform").submit(function(){
                    if(username_result && password_result == true){
                        return true;
                    }
                    else{
                    	alert("请输入正确的用户名或密码");
                        return false;
                        
                    }
                });
            });
        </script> 
        
        
                 <!-- <script>
                 
                           $(function(){
                        	  
                        	  $("#username").blur(function(){
                        		  var username_val = $("#username").val();
                        		  /* alert(username_val); */
                        		  $.ajax({
                        			  async:true,
                        			  data:{"username":username_val},
                        			  dataType:"text",
                        			  url:"/wifi1/user/checkName.action",
                        			  error:function(xhr,status){
                        				  console.log(status);
                        			  },
                        			  success:function(data){
                        				  $("#span1").html(data);
                        			  }
                        		  });
                        	  });
                           });
                 </script> -->
                 
                 
                 <style>
                  .zzz{
                    color:red;
                  }
                  
                 </style>
	</head>
	<body class="style-3">

		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<ul class="menu">
						<!--<li><a href="index.html">Style 1</a></li>
						<li class="active"><a href="index2.html">Style 2</a></li>-->
						<li><a>Wifi Analysis System</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-md-push-8">
					

					<!-- Start Sign In Form -->
					<form id="myform" action="/wifi1/user/register.action" class="fh5co-form animate-box" data-animate-effect="fadeInRight"  method="post">
						<h2>注册</h2>
						<!--<div class="form-group">
							<div class="alert alert-success" role="alert">注册成功！</div>
						</div>-->
						
						<div class="form-group">
							<!-- <label for="name" class="sr-only">Name</label> -->
							<input type="text" class="form-control" name="userName" id="username" placeholder="用户名" autocomplete="off">
						<span class="zzz" id="span1"></span>
						</div>
						
						<!-- <div class="form-group">
							<label for="email" class="sr-only">Email</label>
							<input type="email" class="form-control" id="email" placeholder="Email" autocomplete="off">
						</div> -->
						<div class="form-group">
							<label for="password" class="sr-only">Password</label>
							<input type="password" class="form-control" name="userPassWord" id="password" placeholder="密码" autocomplete="off">
						<span class="zzz" id="span2"></span>
						</div>
						
						<!-- <div class="form-group">
							<label for="re-password" class="sr-only">Re-type Password</label>
							<input type="password" class="form-control" id="re-password" placeholder="确认密码" autocomplete="off">
						</div> -->
						<!--<div class="form-group">
							<label for="remember"><input type="checkbox" id="remember"> Remember Me</label>
						</div>-->
						<div class="form-group">
							<p>注册完成?&nbsp&nbsp&nbsp <a href="login.jsp">去登录</a></p>
						</div>
						<div class="form-group">
							<input type="submit" value="注册" class="btn btn-primary">
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


  <div>${requestScope.message}</div>
	</body>
</html>

