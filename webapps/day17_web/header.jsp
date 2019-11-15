<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="order_list.jsp">我的订单</a></li>
		</ol>
	</div>
</div>

<script type="text/javascript">
	function search2() {
		var keyword = $("#search").val();
		$.get(
		    "${pageContext.request.contextPath}/search1",
			"keyword="+keyword,
			function (data) {

		        var s = "";
				$.each(data,function (index,element) {
                    //显示proIDdiv
					$("#proID").css("display","block");
                   s+="<div>"+element.pname+"</div>";

                })
                //alert(element.pname);
              $("#proID").html(s);
            },
            "json"
		);
    }
    function out(obj) {
        $(obj).css("background-color","white");
    }

    function over(obj) {
        $(obj).css("background-color","pink");
    }

</script>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="product_list.jsp">手机数码<span class="sr-only">(current)</span></a></li>
					<li><a href="#">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search" id="search" onkeyup="search2()">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					<div id="proID" style="margin-top: 15px;display: none; z-index: 5; position: absolute ;border: 1px solid red;width:180px;">
					</div>
				</form>
			</div>
		</div>
	</nav>
</div>