<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html style="overflow-y: hidden !important">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>King &mdash; Free Website Template, Free HTML5 Template
	by gettemplates.co</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by gettemplates.co" />
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="gettemplates.co" />

<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<!-- <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'> -->

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">

<!-- Theme style  -->
<link rel="stylesheet" href="css/style.css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
<!-- 구글 맵 api (사용안함) -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAPiMMsJJAPPXRXq7jFuXCfn_WXbGdsIgw&libraries=places&callback=initMap"
    async defer></script>
<!-- 우편번호 및 주소 받아오기 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- 입주 가능 입력 캘린더 (아직 미사용) -->
<link rel="stylesheet" href="./jquery-ui-1.12.1/jquery-ui.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="./jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="./jquery-ui-1.12.1/datepicker-ko.js"></script>

<script type="text/javascript">
 //MPAY 없음 선택
function checkDisable(form)
{
    if( form.MPAY_none.checked == true ){
    	form.MPAY.value ="없음";
    	form.MPAY.disabled = true;
} else {
	form.MPAY.value ="";
	form.MPAY.disabled = false;
	}
}
//이미지
$(function () {
	$('#btn-upload').click(function(e) {
			e.preventDefault();
			$('#file').click();
		});
	});
$(function () {
	$('#btn-upload2').click(function(e) {
			e.preventDefault();
			$('#file2').click();
		});
	});
$(function () {
	$('#btn-upload3').click(function(e) {
			e.preventDefault();
			$('#file3').click();
		});
	});
$(function () {
	$('#btn-upload4').click(function(e) {
			e.preventDefault();
			$('#file4').click();
		});
	});
$(function () {
	$('#btn-upload5').click(function(e) {
			e.preventDefault();
			$('#file5').click();
		});
	});
//주소정보
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
	
//달력
$(function(){
	$("#date1").datepicker();
	
	$("#date2").datepicker({
		showOn: "button",
        buttonImage: "images/calendar.gif",
	    buttonImageOnly: false,
		buttonText: "Select date"
	});
	
	$("#date3").datepicker({
		onSelect:function(dateText, inst) {
			console.log(dateText);
			console.log(inst);
		}
	});
});
</script>

</head>
<body>
<!-- 달력 테스트 사용 코드 -->
<!-- <input type="text" name="date" id="date1" size="12" />
<br/>
<input type="text" name="date" id="date2" size="12" />
<br/>
<input type="text" name="date" id="date3" size="12" />
<input type="button" value="달력" onclick="$('#date3').datepicker('show');" /> -->

		<div class="fh5co-loader"></div>
	
	<div id="page">
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
								<li><a href="#">원룸 검색</a></li>
								<li><a href="#">상세 검색</a></li>
							</ul>
						</li>
						<li class="has-dropdown">
							<a href="#">방 내놓기</a>
							<ul class="dropdown">
								<li><a href="#">매물 보기</a></li>
								<li><a href="out.html">내 방 내놓기</a></li>
							</ul>
						</li>
					      
               <c:if test="${sessionScope.sessionID==null}">
                  <li class="btn-cta"><a href="login.to"><span>로그인</span></a></li>
               
                        <li class="btn-cta"><a href="register.to"><span>회원가입</span></a></li> 
                    </c:if>
                    
                    <c:if test="${sessionScope.sessionID!=null}">
                         
                  <li class="btn-cta"><span style="font-size: 30px">${sessionScope.sessionID }님</span></li> 
                  <li class="btn-cta"><a href="logout.to"><span>로그아웃</span></a></li>
               
                    </c:if>
					
					</ul>
					
					
				</div>
			</div>
			
		</div>
	</nav>

	
	
		<header id="fh5co-header" class="fh5co-cover fh5co-cover-sm"
			role="banner" style="background-image: url(images/img_bg_2.jpg);">
			<div class="overlay"></div>
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center">
						<div class="display-t">
							<div class="display-tc animate-box" data-animate-effect="fadeIn">
								<h1>Contact Us</h1>
								<h2>
									Free html5 templates Made by <a href="http://freehtml5.co"
										target="_blank">freehtml5.co</a>
								</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>


		<form action="RoomOutAction.do" method="post" name="boardform">

			<div class="wrap-950 add-container">
				<div class="content left-content">

					<h4>사진 등록</h4>
					<div class="add-photo">
						<p class="item-txt ischrome">
							· 사진 최대 5장 까지 등록할 수 있습니다.<br> · 아래에 등록 버튼을 클릭하여 등록할 수 있습니다.<br>
							· 한꺼번에 여러 장 등록도 가능합니다.<br> <span class="fc-red1">· 직접
								찍은 실제 방 사진의 원본을 등록해야 합니다.</span><br>
						</p>
						<div class="item-photo" id="add-photo-box">
							<ul class="ui-sortable">
								<li id="li1" class="ui-sortable-handle"><span class="i-count">1</span>
									<div>
									<input class="i-btn" type="file" id="file" name="file"/>
									<button class="i-btn" type="button" id="btn-upload">Image</button>
									</div>
									<em class="i-tit">대표사진</em>
									</li>
								<li id="li2" class="ui-sortable-handle"><span class="i-count">2</span>
									<div>
									<input class="i-btn" type="file" id="file2" name="file"/>
									<button class="i-btn" type="button" id="btn-upload2">Image</button>
									</div>
									<em class="i-tit">화장실</em></li>
								<li id="li3" class="ui-sortable-handle"><span class="i-count">3</span>
									<div>
									<input class="i-btn" type="file" id="file3" name="file"/>
									<button class="i-btn" type="button" id="btn-upload3">Image</button>
									</div>
									<em class="i-tit">주방</em></li>
								<li id="li4" class="ui-sortable-handle"><span class="i-count">4</span>
									<div>
									<input class="i-btn" type="file" id="file4" name="file"/>
									<button class="i-btn" type="button" id="btn-upload4">Image</button>
									</div>
									<em class="i-tit">방사진</em></li>
								<li id="li5" class="ui-sortable-handle"><span class="i-count">5</span>
									<div>
									<input class="i-btn" type="file" id="file5" name="file"/>
									<button class="i-btn" type="button" id="btn-upload5">Image</button>
									</div>
									<em class="i-tit">방사진</em></li>
							</ul>
						</div>
					</div>


					<h4>정보 입력</h4>
					<table class="add-table">
						<tbody>
							<tr>
								<th>주소</th>
								<td colspan="3">
									<input type="text" id="sample4_postcode" placeholder="우편번호">
									<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
									<input type="text" id="sample4_roadAddress" name = "RADDRESS" placeholder="도로명주소"><br>
									<input type="text" id="sample4_jibunAddress" name = "NADDRESS" placeholder="지번주소">
									<span id="guide" style="color:#999;display:none"></span>
									<input type="text" id="sample4_detailAddress" name = "ADDRESS2" placeholder="상세주소">
									<input type="text" id="sample4_extraAddress" name = "ADDRESS3" placeholder="참고항목">
								</td>
							</tr>
							<tr>
								<th>보증금</th>
								<td><input type="text" class="text" name="DEPOSIT"> 만원 <span class="fc-red1">※무보증일 경우, 한 달 월세를 입력하세요</span></td>
							</tr>
							<tr>
								<th>월세</th>
								<td><input type="text" class="text" name="RENT"> 만원 <span class="fc-red1">※전세일 경우, 0을 입력 하세요</span></td>
							</tr>
							<tr>
								<th>방구조</th>
								<td><select style="width: 180px" name="ROOMTYPE">
										<option value="">선택하세요</option>
										<option value="오픈형 원룸 (방1)">오픈형 원룸 (방1)</option>
										<option value="분리형 원룸 (방1, 거실1)">분리형 원룸 (방1, 거실1)</option>
										<option value="복층형 원룸">복층형 원룸</option>
										<option value="투룸 (방2, 거실1)">투룸 (방2, 거실1)</option>
								</select></td>
							</tr>

							<tr>
								<th>관리비</th>
								<td><input type="text" class="text" name="MPAY" id="MPAY"> 만원
									&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp; 
									<label><input type="checkbox" id="MPAY_none" name="MPAY_none" onClick="checkDisable(this.form)"> 없음</label>
									<p class="i-gray">
										<strong>관리비 포함 항목</strong> <label><input
											name="MPAY2" type="checkbox" value="전기세"> 전기세</label> <label><input
											name="MPAY2" type="checkbox" value="가스"> 가스</label> <label><input
											name="MPAY2" type="checkbox" value="수도"> 수도</label> <label><input
											name="MPAY2" type="checkbox" value="인터넷"> 인터넷</label> <label><input
											name="MPAY2" type="checkbox" value="TV"> TV</label>
									</p></td>
							</tr>
							
							<tr>
								<th>크기</th>
								<td>
										전용면적 : <input type="text" style="width:50px; margin-right:5px;"name="RSIZE" />평
								</td>
							</tr>
							<tr>
								<th>층수</th>
								<td>해당 층 : <select name="FLOOR">
										<option value="">선택하세요</option>
										<option value="반지하">반지하</option>
										<option value="옥탑방">옥탑방</option>
										<option value="1">1층</option>
										<option value="2">2층</option>
										<option value="3">3층</option>
										<option value="4">4층</option>
										<option value="5">5층</option>
										<option value="6">6층</option>
										<option value="7">7층</option>
										<option value="8">8층</option>
										<option value="9">9층</option>
										<option value="10">10층</option>
										<option value="11">11층</option>
										<option value="12">12층</option>
										<option value="13">13층</option>
										<option value="14">14층</option>
										<option value="15">15층</option>
										<option value="16">16층</option>
										<option value="17">17층</option>
										<option value="18">18층</option>
										<option value="19">19층</option>
										<option value="20">20층</option>
										<option value="21">21층</option>
										<option value="22">22층</option>
										<option value="23">23층</option>
										<option value="24">24층</option>
										<option value="25">25층</option>
										<option value="26">26층</option>
										<option value="27">27층</option>
										<option value="28">28층</option>
										<option value="29">29층</option>
										<option value="30">30층</option>
										<option value="31">31층</option>
										<option value="32">32층</option>
										<option value="33">33층</option>
										<option value="34">34층</option>
										<option value="35">35층</option>
										<option value="36">36층</option>
										<option value="37">37층</option>
										<option value="38">38층</option>
										<option value="39">39층</option>
										<option value="40">40층</option>
								</select>
								</td>
							</tr>
							<tr>
								<th>주차</th>
								<td class="has-col"><label><input type="radio"
										name="PARKING" value="가능"> 가능</label> <label><input
										type="radio" name="PARKING" value="없음"> 없음</label>
									<div class="i-col">
										<strong>엘리베이터</strong> <label><input type="radio"
											name="ELVE" value="있음"> 있음</label> <label><input
											type="radio" name="ELVE" value="없음">
											없음</label>
									</div></td>
							</tr>
							<tr></tr>
							<tr>
								<th>입주가능일</th>
								<td><input type="text" class="text max" name="RDATE">
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" class="text max" name="TITLE">
								</td>
							</tr>
							<tr>
								<th>상세설명</th>
								<td><textarea name="CONTENT" class="description"
										placeholder="해당 방에 대한 특징과 소개를 최소 50자 이상 입력해야 합니다.
														방의 위치와 교통, 주변 편의시설, 방의 특징과 장점, 보안시설, 옵션, 주차, 전체적인
														방의 느낌 등을 작성해 주세요.      
														다른 방에 대한 설명, 연락처, 홍보 메시지 등 해당 방과 관련없는 내용을 입력하거나 
														해당 방에 대한 설명이 부적절할 경우 중개가 종료될 수 있습니다."></textarea>

								</td>
							</tr>
						</tbody>
					</table>

					<div class="add-btn m-bottom-20">
						<button type="submit" class="btn btn-orange" id="add_room">
							<span id="btn_complete">방 내놓기</span>
						</button>
					</div>
				</div>
			</div>
		</form>
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