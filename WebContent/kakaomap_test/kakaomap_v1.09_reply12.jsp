<%@page import="map.bit.kakaomaptest.kakaoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*  ,  java.text.SimpleDateFormat"%>
<%@ page import= "map.bit.*" %>

<!DOCTYPE html>
<html>
<head>
<%-- jQuery CDN --%>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<%-- BootStrap CDN --%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<title>마커 클러스터러 사용하기</title>

<p style="margin-top:25px "  height=150px>

<form action="javascript:searchkeyword()" name="incruitpart" >
<input type="text" width=50px name="searchcontent" id="searchcontent" >
<input type="submit" height=150px value="검색" >

</form>
</p>
 <style>
    .map_wrap {position:relative;width:70%;height:850px;  float:left;}
    .title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;
    background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>

<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <div class="hAddr">
        <span class="title">지도중심기준 행정동 주소정보</span>
        <span id="centerAddr"></span>
    </div>
</div>

</head>

<body>
	<c:url var="board_write" value="board_write.credu"></c:url>
	<c:url var="board_list" value="board_list.credu"></c:url>
	
	<div class="container">

		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Title</th>
					<th>별점</th>
					<th>작성일</th>
				</tr>
			</thead>

			<tbody>
				
				<c:forEach var="BoardVO" items="${requestScope.list}">
				
				<tr class="info">
					<td>${BoardVO.boardnum}</td>
					<td>${BoardVO.id}</td>
					<td><a data-toggle="modal" data-target="#myModal2" onclick="board_read(${BoardVO.boardnum})">${BoardVO.title}</a></td>
					
					
					<%-- 별점 --%>
					<c:choose>
						
						<%-- if(a == 1){ --%>
						<c:when test="${BoardVO.average == 1}">
							<td>★☆☆☆☆</td>
						</c:when>
						<%-- if(a == 2){ --%>
						<c:when test="${BoardVO.average == 2}">
							<td>★★☆☆☆</td>
						</c:when>
						<%-- if(a == 3){ --%>
						<c:when test="${BoardVO.average == 3}">
							<td>★★★☆☆</td>
						</c:when>
						<%-- if(a == 4){ --%>
						<c:when test="${BoardVO.average == 4}">
							<td>★★★★☆</td>
						</c:when>
						<%-- if(a == 5){ --%>
						<c:when test="${BoardVO.average == 5}">
							<td>★★★★★</td>
						</c:when>
						
						
					</c:choose>
					
					<td>${BoardVO.bdate}</td>
				</tr>
				
				</c:forEach>
			
			</tbody>
		</table>

			<div class="col-sm-2"></div>
			<div class="col-sm-4 text-success" style="text-align: right;"> 
			<button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal">Write</button>
			</div>
		</div>
		 
		
		 <!-- Modal -->
  	<div class="modal fade" id="myModal" role="dialog">
    	<div class="modal-dialog modal-lg">
    
      	<!-- Modal content-->
      	<div class="modal-content">
	        <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
        
        <div class="modal-body">
          
			<div class="panel-group">
			<div class="panel panel-success" style="margin-top: 10px;">
				<div class="panel-heading">Goods Review</div>
				<div class="panel-body">
					<%-- form --%>
					<form class="form-horizontal" role="form" action="${board_write}" method="post">
						<div class="form-group">
							<label class="control-label col-sm-2">작성자(ID):</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="id"
									name="id" placeholder="ID">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">제목:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title"
									name="title" placeholder="Title">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">비밀번호:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="pwd"
									name="pwd" placeholder="비밀번호">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">내용:</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="5"	placeholder="content" name="content" id="content"></textarea>
							</div>
							
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="radio">
								
									<label class="radio-inline"> <input type="radio" name="average" id="average" value="1" checked="checked">★☆☆☆☆</label>
									<label class="radio-inline"> <input type="radio" name="average" id="average" value="2">★★☆☆☆</label>
									<label class="radio-inline"> <input type="radio" name="average" id="average" value="3">★★★☆☆</label>
									<label class="radio-inline"> <input type="radio" name="average" id="average" value="4">★★★★☆</label>
									<label class="radio-inline"> <input type="radio" name="average" id="average" value="5">★★★★★</label>
									
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success">작 성</button>
								<button type="reset" class="btn btn-danger">초기화</button>
							</div>
						</div>
					</form>


				</div>

			</div>
		</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  
  <!-- Modal2 _ read review -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Review Detail</h4>
        </div>
        <div class="modal-body">
                   	
          <textarea rows="10" class="form-control" readonly="readonly" id="content"></textarea>	
                    
        </div>
        <div class="modal-footer">
        						
          <button type="button" class="btn btn-danger" onclick="board_delete()">Delete</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	
        </div>
      </div>
      
    </div>
  </div>
		
	</div>

	<div id="map" style="width: 70%; height: 850px; float: left;"></div>


	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf17b15a111ecb427c26da3a08661ee9&libraries=services,clusterer">
</script>
	<script>
	    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	        center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 lat이 위도 36  lng이 경도 127
	        level : 10 // 지도의 확대 레벨 
	    });
	    
	  kakao.maps.event.addListener(map, 'zoom_changed', function(){ change2.call() });
    
      kakao.maps.event.addListener(map, 'dragend', function(){ change2.call() });
      
      function change2(){
			var bounds = map.getBounds();
	        
	        //그냥 정보가져오기.
	        var latlng= bounds.toString();
	        //console.log(test);
	        var level = map.getLevel();
	        //if(level>10){
	        gotoservletlatlng(latlng);
      		//}
	    } 
      
      function gotoservletlatlng(latlng){
      	//var string = [lng,lat,addr]; //이건 사실의미없음
      	//var vparam = "test";
      	var latlng = latlng;
      	
      		$.ajax(
      				{      					
      					url : "<%=request.getContextPath()%>/ArrayServlet.do",      					
      					type: "get",
      					dataType: "json",
      					data:{"latlng":latlng}, //여기서 데이터를 바로보내준다ㅋㅋㅋ
      					header:{
      						"Content-Type":"application/json",	//Content-Type 설정
      						"X-HTTP-Method-Override":"POST"},
      					success:function(data){      						
      						var as = eval(data);//객체 변환
      						
      						gotogetmarker(data);
      						
      					},
      					error:function(msg,error){
      						alert(error);
      					}
      				}
      			  );
      };	      
      
      //var clusterer;
      
      function gotogetmarker(data){
    	  // 마커 클러스터러를 생성합니다 
    	    var clusterer = new kakao.maps.MarkerClusterer({
    	        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    	        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    	        minLevel: 10 // 클러스터 할 최소 지도 레벨 
    	    });
    	    
    	    //console.log("123"); 
    	  
    	    var markerjson=new Array();
    	    $.each(data, function(index, list){
    	    	//alert("here 168!");
					
	              var data2 = new Object();
	              data2.lat=parseFloat(list.position.lat);
	              data2.lng=parseFloat(list.position.lng);
	        
              	var data1 = new Object() ;            	
           		              
            	data1.position = data2;
            	
            	data1.text = list.text ;
            	
            	markerjson.push(data1);
				});
    	    alert(JSON.stringify(markerjson));
    	    
    	
    	    	var markers = markerjson.map(function(each) {
    	    	var position = each.position;
    	    	var marker = new kakao.maps.Marker({
    	    	    position : new kakao.maps.LatLng(position.lat, position.lng)
    	    	  });
    	    	
    	    	kakao.maps.event.addListener(marker, 'click', function() {
    	    	    var info = new kakao.maps.InfoWindow({
    	    	      position: marker.getPosition(),
    	    	      disableAutoPan:true,
    	    	      content: each.text
    	    	    });
    	    	    //info.setMap(map);
    	    	    
    	    	    //alert("클러스터 클릭 \n주소 : "+each.text);
    	    	    var text1 = URIEncoder.encode(decodeURIComponent( each.text ), "UTF-8");
    	    	    document.getElementById('markid').innerHTML="마커클러스터 지번주소 "+text1;
    	    	    
    	    	  });
    	    	
    	    	 
    	    	  return marker;
    	    	});
    	    	
    	    	// 클러스터러에 마커들을 추가합니다
    	    	clusterer.clear();
    	        clusterer.addMarkers(markers);
      };
  
        /* 저장할객체생성 */
        var lng="";
        var lat="";
        var addr="";


        $(function(){
        	var string = [lng,lat,addr]; //이건 사실의미없음
        	var vparam = "test";
        	$('#latlngid').click(function(){
        		$.ajax(
        				{
        					
        					url : "<%=request.getContextPath()%>/ArrayServlet.do",
        					type: "get",
        					data:{"string":[lng,lat,addr]}, //여기서 데이터를 바로보내준다ㅋㅋㅋ
        					success:function(data){
        						var as = eval(data);//객체 변환
        						alert("데이터 저장성공 :\n"
        								+"지번주소 : "+ as[0] + "\n " + as[1]+" 개 데이터 저장");
        					},
        					error:function(msg,error){
        						alert(error);
        					}
        				}
        			  );
        	});
        		
        });	
        
        
     // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
            infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

        // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
            

     // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
     kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
         searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
             if (status === kakao.maps.services.Status.OK) {
                 var detailAddr = !!result[0].road_address ? 
                 		'<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                 detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                 
                 var content = '<div class="bAddr">' +
                                 '<span class="title">주소정보</span>' + 
                                 detailAddr + 
                             '</div>';
                             //+'<input type="text">'

                 // 마커를 클릭한 위치에 표시합니다 
                 marker.setPosition(mouseEvent.latLng);
                 
                 marker.setMap(map);
                 
                 document.getElementById('latid').innerHTML="위도는"+mouseEvent.latLng.getLat();
                 document.getElementById('lngid').innerHTML="경도는"+mouseEvent.latLng.getLng();
                 document.getElementById('addrid').innerHTML="지번주소 "+result[0].address.address_name;
                 
                 
                 lng=mouseEvent.latLng.getLng();
                 lat=mouseEvent.latLng.getLat();
                 addr=result[0].address.address_name; 

                 // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                 infowindow.setContent(content);
                 infowindow.open(map, marker);
             }   
         });
     });     
            
            
     // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'idle', function() {
            searchAddrFromCoords(map.getCenter(), displayCenterInfo);
         
        });
     
        function searchAddrFromCoords(coords, callback) {
            // 좌표로 행정동 주소 정보를 요청합니다
            geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
          
        }

        function searchDetailAddrFromCoords(coords, callback) {
            // 좌표로 법정동 상세 주소 정보를 요청합니다
            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
            //alert("123");
        }
        
        
        function displayCenterInfo(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var infoDiv = document.getElementById('centerAddr');

                for(var i = 0; i < result.length; i++) {
                    // 행정동의 region_type 값은 'H' 이므로
                    if (result[i].region_type === 'H') {
                        infoDiv.innerHTML = result[i].address_name;
                        break;
                    }
                }
            }    
        }
        
        function searchkeyword(){
        	//장소 검색 객체를 생성합니다
        	var ps = new kakao.maps.services.Places(); 

        	var cont = document.incruitpart.searchcontent.value;
        	// 키워드로 장소를 검색합니다
        	ps.keywordSearch(cont, placesSearchCB); 

        	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
        	function placesSearchCB (data, status, pagination) {
        	    if (status === kakao.maps.services.Status.OK) {

        	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        	        // LatLngBounds 객체에 좌표를 추가합니다
        	        var bounds = new kakao.maps.LatLngBounds();

        	        for (var i=0; i<data.length; i++) {
        	            //displayMarker(data[i]);
        	            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        	        }

        	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        	        map.setBounds(bounds);
        	    }
        	}
        }
</script>
</body>
</html>