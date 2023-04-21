<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ///////////////////////////// 로그인시 Forward  /////////////////////////////////////// -->
 <c:if test="${ ! empty user }">
 	<jsp:forward page="main.jsp"/>
 </c:if>
 <!-- //////////////////////////////////////////////////////////////////////////////////////////////////// -->


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&family=Gowun+Batang&family=Nanum+Brush+Script&display=swap" rel="stylesheet">
	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style type="text/css">	
	
	p{font-family: 'Nanum Brush Script', cursive; font-size: 35px;}
	</style>
   	<!--p{font-family: 'Gowun Batang', serif; font-size: 20px;}-->
   	<!--  ///////////////////////// JavaScript ////////////////////////// -->
   	
	<script type="text/javascript">
		
		//============= 회원원가입 화면이동 =============
		$( function() {
			//==> 추가된부분 : "addUser"  Event 연결
			$("a[href='#' ]:contains('회원가입')").on("click" , function() {
				self.location = "/user/addUser"
			});
		});
		
		//============= 로그인 화면이동 =============
		$( function() {
			
			$("#userId").focus();
			
			///==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]:contains('로 그 인')").on("click" , function() {
			
				var id=$("input:text").val();
				var pw=$("input:password").val();
				
				if(id == null || id.length <1) {s
					alert('ID 를 입력하지 않으셨습니다.');
					$("#userId").focus();
					return;
				}
				
				if(pw == null || pw.length <1) {
					alert('패스워드를 입력하지 않으셨습니다.');
					$("#password").focus();
					return;
				}
				
				$("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
			});
		});	
		
		$(function () {
			$("#First").on("click",function (){
				
				self.location="/product/getProduct?prodNo=10200&menu=search";
			});
			$("#Second").on("click",function (){
				
				self.location="/product/getProduct?prodNo=10199&menu=search";
			});
			$("#Third").on("click",function (){
				
				self.location="/product/getProduct?prodNo=10197&menu=search";
			});
			$("#Forth").on("click",function (){
				
				self.location="/product/getProduct?prodNo=10198&menu=search";
			});
			$("#Fifth").on("click",function (){
			
				self.location="/product/getProduct?prodNo=10196&menu=search";
			});
			
		})
		
	</script>	
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<div class="navbar navbar-default">
		
        <div class="container">
        
        	<a class="navbar-brand" href="#">Art Shop</a>
			
			<!-- toolBar Button Start //////////////////////// -->
			<div class="navbar-header">
			    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#target">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			    </button>
			</div>
			<!-- toolBar Button End //////////////////////// -->
			
		 
			<div class="collapse navbar-collapse"  id="target">
			
			
	             <form class="nav navbar-nav navbar-right">
	               
	                <li class="form-group">		    
					     <input type="text" class="form-control" name="userId" id="userId"  placeholder="아이디" >
					    
					 </li>
					  
					  <li class="form-group">
					    
					   
					      <input type="password" class="form-control" name="password" id="password" placeholder="패스워드" >
					  
					  </li>
					  
					  
			                        
	                 <li><a href="#">로 그 인</a></li>
	                 <li><a href="#">회원가입</a></li>
	                 
	           	</form>
	       </div>
   			  
   		</div>
   	</div>
   	<!-- ToolBar End /////////////////////////////////////-->
   	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		
		<!-- 다단레이아웃  Start /////////////////////////////////////-->
		<div class="row">
		
		 <div class="well">
		 
        <p>남의 뒤를 따라가는 것은 결코 전진하고 있는 것이 아니다. 그리고 자기 자신의 마음속에서 창조할 줄 모르는 사람은 남의 작품에서 어떠한 유익도 끌어낼 줄 모른다.
        	뛰어난 예술가의 의지와 상상력으로 한 개의 대리석 속에 집어넣지 못하는 것은 없다. 미루어 보건대 다만 뛰어난 재능에 따르는 손만이 그것을 돌에 형성시킬 수 있다.
        	천가지의 기쁨이 한 가지의 괴로움만큼도 값이 없다. 지성을 따르는 손이 성취할 수 있다. 나는 아직 배우고 있다.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- 미켈란젤로</p>
      	</div>
      	
      	</div>
      </div>
      
		
	<div class="container-fluid">
	
		<div class="row">
		<!-- carousel  Start /////////////////////////////////////-->
 	 	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	        <ol class="carousel-indicators">
	          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	          <li data-target="#carousel-example-generic" data-slide-to="3"></li>
	          <li data-target="#carousel-example-generic" data-slide-to="4"></li>
	       
	        </ol>
	        <div class="carousel-inner" role="listbox" >
	          <div class="item active" id="First" >
	            <img src="https://mdl.artvee.com/sftb/402674mt.jpg" class="img-responsive center-block" alt="First slide">
	          </div>
	          <div class="item" id="Second">
	            <img src="https://mdl.artvee.com/sftb/401950mt.jpg" class="img-responsive center-block" alt="Second slide">
	          </div>
	          <div class="item" id="Third">
	            <img src="https://mdl.artvee.com/sftb/523045ld.jpg" class="img-responsive center-block" alt="Third slide">
	          </div>
	          
	           <div class="item" id="Forth">
	            <img src="https://mdl.artvee.com/sftb/518497ld.jpg" class="img-responsive center-block" alt="Forth slide">
	          </div>
	          
	           <div class="item" id="Fifth">
	            <img src="https://mdl.artvee.com/sftb/529990ld.jpg"  class="img-responsive center-block" alt="Fifth slide">
	          </div>
	          
	           <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
	          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	          <span class="sr-only">Previous</span>
	        </a>
	        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
	          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	          <simpan class="sr-only">Next</span>
	        </a>
			      
				
			</div>
			<!-- carousel  End /////////////////////////////////////-->
			
		</div>
	
		<!-- 다단레이아웃  end /////////////////////////////////////-->
		</div>

	</div>
	<!--  화면구성 div end /////////////////////////////////////-->
	
	

</body>

</html>