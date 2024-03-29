<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	  body {
            padding-top : 50px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
     <script type="text/javascript">
    // 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetList(currentPage) {
		
    	$("#currentPage").val(currentPage);
		
		$("form").attr("method", "POST"). attr("action" , "/product/listProduct?menu=${param.menu}").submit();
		
	}
	
	$(function () {
		
		$("#search").on("click", function () {
			fncGetList('1');
		});
		
		
		<c:choose>
			<c:when test="${param.menu eq 'manage'}">
			
				$("td:nth-child(2)").on("click", function () {
					var prodNo = $(this).children("input:hidden").val();

					self.location = "/product/updateProduct?prodNo="+prodNo;
					
				});
				
				$("td:nth-child(6) > i").on("click", function () {
					
					
					var prodNo = $(this).children("input:hidden").val();

					
					$.ajax(
							{
								url : "/product/json/getProduct/"+prodNo ,
								method : "GET",
								dataType : "json",
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function (JSONData, status) {
									
									//Debug...
									//alert(status);
									//Debug...
									//alert("JSONData : \n"+JSONData);
									
									var displayValue = "<h6>"
																+"상품번호 : "+JSONData.prodNo+"<br/>"
																+"상품명 : "+JSONData.prodName+"<br/>"
																+"상품상세정보 : "+JSONData.prodDetail+"<br/>"
																+"제조일자 : "+JSONData.manuDate+"<br/>"
																+"가격 : "+JSONData.price+"<br/>"
																+"등록일자 : "+JSONData.regDateString+"<br/>"
																+"</h6>";
								$("h6").remove();
								$("#"+prodNo+"").html(displayValue);
								}
						});
					
				});
			
			</c:when>
			
			<c:when test="${param.menu eq 'search'}">
			
				$("td:nth-child(2)").on("click", function () {
					var prodNo = $(this).children("input:hidden").val();
					
					self.location = "/product/getProduct?prodNo="+prodNo+"&menu=search";
					
				});
			
				$("td:nth-child(6) > i").on("click", function () {
					
					
					var prodNo = $(this).children("input:hidden").val();

					
					$.ajax(
							{
								url : "/product/json/getProduct/"+prodNo ,
								method : "GET",
								dataType : "json",
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function (JSONData, status) {
									
									//Debug...
									//alert(status);
									//Debug...
									//alert("JSONData : \n"+JSONData);
									
									var displayValue = "<h6>"
																+"상품번호 : "+JSONData.prodNo+"<br/>"
																+"상품명 : "+JSONData.prodName+"<br/>"
																+"상품상세정보 : "+JSONData.prodDetail+"<br/>"
																+"제조일자 : "+JSONData.manuDate+"<br/>"
																+"가격 : "+JSONData.price+"<br/>"
																+"등록일자 : "+JSONData.regDateString+"<br/>"
																+"</h6>";
								$("h6").remove();
								$("#"+prodNo+"").html(displayValue);
								}
						});
					
				});
			
			</c:when>
		</c:choose>
		
		$("td:nth-child(2)" ).css("color" , "red");
		$("h7").css("color" , "red");
		
		
		$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
	});	
		
	</script>
	
			<!-- autocomplete을 위해 추가 -->
	<script type="text/javascript">
  $( function() {
	  
	  $.ajax( 
				{
					url : "/product/json/autocomplete" ,
					method : "GET" ,
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(JSONData , status) {

						//Debug...
						//alert(status);
						//Debug...
						//alert("JSONData : \n"+JSONData);
						
						var availableTags = JSONData;
						
						$( "#searchKeyword" ).autocomplete({
						      source: availableTags
						});
						
					}
				});

  } );
  </script>	
</head>
<body bgcolor="#ffffff" text="#000000">

<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->

<div style="width:98%; margin-left:10px;">




<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
					
				<c:if test="${!empty param.menu}">
					<c:choose>
						<c:when test="${param.menu eq 'manage'}">
						
						<div class="page-header text-info">
	       					<h3>상품 관리</h3>
	   					</div>
							
						</c:when>
						<c:when test="${param.menu eq 'search'}">
							<div class="page-header text-info">
	       						<h3>상품 목록조회</h3>
	    					</div>
													
						</c:when>
					</c:choose>
				</c:if>	
				
	
	<!-- table 위쪽 검색 Start /////////////////////////////////////-->

 <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
						<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" id=search class="btn btn-default">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div>
<!-- table 위쪽 검색 End /////////////////////////////////////-->


<!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th align="center">No</th>
            <th align="left" >상품명</th>
            <th align="left">가격</th>
            <th align="left">등록일</th>
            <th align="left">현재상태</th>
            <th align="left">간략정보</th>
         </tr>
        </thead>
        
        <tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="product" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center">${ i }</td>
			  <td align="left"  title="Click : 상품정보 확인" >
			  		<input type=hidden value=${product.prodNo} >
			 		${product.prodName}</td>
			  <td align="left">${product.price}</td>
			  <td align="left">${product.regDate}</td>
			  <td align="left">
			  	<c:if test="${product.proTranCode eq null || product.proTranCode.trim() eq '1' }">
			  			판매중</td>
			  	</c:if>
		
		<c:choose>
			<c:when test="${user.role eq 'user'}">
				<c:choose>
					<c:when test="${product.proTranCode.trim() eq '2'|| product.proTranCode.trim() eq '3' || product.proTranCode.trim() eq '4'}">
						<td align="left">재고없음</td>
					</c:when>
				</c:choose>	
			</c:when>
			<c:when test="${user.role eq 'admin'}">
				<c:choose>
					<c:when test="${product.proTranCode.trim() eq '2' }">
						<td align="left">			
							판매완료 <a href="/product/updateTranCodeByProd?prodNo=${product.prodNo }&tranCode=${product.proTranCode.trim()}&currentPage=${resultPage.currentPage}"> 배송하기		
						</td>
					</c:when>
					<c:when test="${product.proTranCode.trim() eq '3'}">
						<td align="left">배송중</td>
					</c:when>
					<c:when test="${product.proTranCode.trim() eq '4'}">
						<td align="left">배송완료</td>
					</c:when>
				</c:choose>	
			
			</c:when>
		
		</c:choose>
		
		<td align="left">
			  	<i class="glyphicon glyphicon-ok" id ="${product.prodNo }" >
			  	<input type="hidden" value="${product.prodNo }"></i>
		</td>
		</tr>
	<!--	<tr>
		  <td colspan="11" bgcolor="D6D7D6" height="1"></td>
		<td id ="${product.prodNo }" colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>-->
          </c:forEach>
        
        </tbody>
</table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	
</body>

</html>