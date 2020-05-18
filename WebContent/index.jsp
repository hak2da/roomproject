<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OneRoom Planet</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="gettemplates.co" />

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- Theme style  -->
<link rel="stylesheet" href="css/style.css">

<!-- Theme style  -->
<link rel="stylesheet" href="css/bootstrapmin.css">


<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
   <script src="js/respond.min.js"></script>
   <![endif] -->

	
   
   </head>
   <body>
      
   <div class="fh5co-loader"></div>
   
  <nav class="fh5co-nav" role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-xs-2">
					<div id="fh5co-logo"><a href="index.jsp">2조</a></div>
				</div>
				<div class="col-xs-10 text-right menu-1">
					<ul>
						<li class="active"><a href="index.jsp">Home</a></li>
						
						<li class="has-dropdown">
							<a href="">원룸</a>
							<ul class="dropdown">
								<li><a href="jsp_yeonghak/map_finalV1.04.jsp" >원룸 검색</a></li>
								<li><a href="#">상세 검색</a></li>
							</ul>
						</li>
						
						<li class="has-dropdown">
							<a href="">방 내놓기</a>
							<ul class="dropdown">
								<li><a href="#">매물 보기</a></li>
								<li><a href="out.do">내 방 내놓기</a></li>
							</ul>
						</li>
						
						<li>
						<a onclick="modalshow()" style="cursor:pointer;">
							이용안내
						</a>
						</li>
						
                  
               <c:if test="${sessionScope.sessionID==null}">
                    <li class="btn-cta"><a href="login.to"><span>로그인</span></a></li>
               		<li class="btn-cta"><a href="search.to"><span>아이디/비밀번호 찾기</span></a></li> 
                    <li class="btn-cta"><a href="register.to"><span>회원가입</span></a></li> 
                         
               </c:if>
                    
                    <c:if test="${sessionScope.sessionID!=null}">
                         
                  <li class="btn-cta"><span style="font-size: 30px">${sessionScope.sessionID }님</span></li> 
                  <li class="btn-cta"><a href="logout.to"><span>로그아웃</span></a></li>
                  <li class="btn-cta"><a href="registerDelete.to"><span>회원 탈퇴</span></a></li>
               
                    </c:if>
                     
               </ul>
            </div>
         </div>
         
      </div>
   </nav>

   <header id="fh5co-header" class="fh5co-cover" role="banner" style="background-image:url(images/img_bg_2.jpg);">
      <div class="overlay"></div>
      <div class="container">
         <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
               <div class="display-t">
                  <div class="display-tc animate-box" data-animate-effect="fadeIn">
                     <h1 onclick="setCookie('notice', '', -1)">OneRoom Planet</h1>
                     <div class="row">
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </header>

   <div class="gototop js-top">
      <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
   </div>
   
   <!-- Modal -->
  <div  class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div style="width:800px;" class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h3 style="font-weight: bold;text-align:center;" class="modal-title">방문해주셔서 감사합니다!</h3>
        </div>
        <div class="modal-body">
        <p style="font-size:20px;">처음 방문하시는 거면 한번 읽어주세요</p>
        <ol style="margin:2% 0 0 5%;font-size:15px;">
        	<li><p>회원가입을 안하신 손님이라면 편의를 위해 ID는 '<font style="color:red;font-weight:bold;">customer1</font>' pwd는 '<font style="color:red;font-weight:bold;">room</font>'으로 로그인 해주세요.</p></li>
        	<li><p>회원가입,카카오맵을 통한 리뷰작성,주소에 대한 상세정보남기기 기능을 사용하실수 있습니다.</p></li>
        	<li><p>이 창은 최초 접속시 한번만 뜨며 화면 상단 가운데 <font style="font-weight:bold;">이용 안내</font>를 클릭하면 볼수있습니다.</p></li>
        </ol>
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
   
   <!-- jQuery -->
   <script src="js/jquery.min.js"></script>
   <!-- jQuery Easing -->
   <script src="js/jquery.easing.1.3.js"></script>
   <!-- Bootstrap -->
   <script src="js/bootstrap.min.js"></script>
   <!-- Waypoints -->
   <script src="js/jquery.waypoints.min.js"></script>
   <!-- Main -->	
   <script src="js/main.js"></script>
	
   
   </body>

</html>

<script type="text/javascript">
   
	window.onload = test;

	function test(){
		
		if(getCookie('notice') != 'forbeginer'){
			
			modalshow();
			setCookie('notice','forbeginer',1);
			
		}
	}
   
	//   	쿠키영역 시작
	
   function setCookie(cName, cValue, cDay){
        var expire = new Date();
        expire.setDate(expire.getDate() + cDay);
        cookies = cName + '=' + escape(cValue) + '; path=/ '; // 한글 깨짐을 막기위해 escape(cValue)를 합니다.
        if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
        document.cookie = cookies;
    }
 
    // 쿠키 가져오기
    function getCookie(cName) {
        cName = cName + '=';
        var cookieData = document.cookie;
        var start = cookieData.indexOf(cName);
        var cValue = '';
        if(start != -1){
            start += cName.length;
            var end = cookieData.indexOf(';', start);
            if(end == -1)end = cookieData.length;
            cValue = cookieData.substring(start, end);
        }
        return unescape(cValue);
    }    
	//   	쿠키영역 끝
	
	function modalshow(){
		 $("#myModal").modal();
	}
	
</script>

