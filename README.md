# RedingRecord-Project
이름 : 이재진

이메일 :  wowls09@naver.com

# 목차
 * 프로젝트 소개
 
-프로젝트에 대한 설명

-개발환경

-구현 내용

* 마무리하며

# 프로젝트 소개

**1. 프로젝트 설명**


![서재메인2](https://user-images.githubusercontent.com/74645087/105450967-fd692480-5cbe-11eb-91e1-5effdef86b03.PNG)

제가 구현한 프로젝트의 이름은 Reding Record 입니다. Reading Record는 책을 읽은 후 그에 대한 리뷰를 남길 수 있는 사이트로, 자신이 읽은 도서와 그에 대한 감상을 한 눈에 모아 볼 수 있도록 하였습니다. 뿐만 아니라 다른 사람이 남긴 독서 기록을 구경하고 댓글을 남기거나 친구 추가를 하는 등, 이용자 간의 상호 소통이 가능한 사이트를 구현하고자 하였습니다. 주요 기능은 다음과 같습니다.

- 주요 기능

1. 리뷰 작성, 수정, 삭제 기능
2. 댓글 작성, 삭제 기능
3. 도서 검색 기능
4. 리뷰 검색 기능
5. 친구 추가 기능
6. 서재 통계 기능


---


**2. 개발환경**

운영 체제: window10

IDE: Eclipse

프로그래밍 언어: JAVA, JavaScript, Jquery

Back-end: Spring Framework ,myBatis, Tomcat

Front-end: HTML5, CSS3

Data-Base: Oracle


---

 **3. 구현 내용**

- **인터셉터를 통한 로그인 확인**

 글 작성, 수정, 삭제 등과 같은 기능은 로그인 상태에서만 사용 가능합니다. 로그인 여부를 확인해야 하는 기능의 경우 Interceptor를 통해 Controller의 로직 수행 전 로그인 상태를 확인하여 로그인이 되어 있으면 로직을 수행하고, 되어있지 않다면 로그인 화면으로 redirect 처리를 해주었습니다.

- presentation-layer.xml
```
<!-- 로그인 확인 인터셉터 -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/*.do"/>
			<bean id="loginInterceptor" class="com.book.record.utils.LoginInterceptor"/>
		</mvc:interceptor>
	</mvc:interceptors>
```

- LoginInterceptor.java
```
package com.book.record.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
//로그인 되어 있지 않은 경우
		if(session.getAttribute("loginUser") == null) {
			response.sendRedirect("login_form"); //로그인 페이지로 이동
			return false;
		}
		return true;
	}
	
}
```
---

- **Ajax를 이용한 아이디 중복 체크**

![중복체크](https://user-images.githubusercontent.com/74645087/105443879-1fa77600-5cb0-11eb-8abd-0e08cdd0c6c6.PNG)

아이디 중복 체크 기능에 Ajax를 사용하였습니다. 사용자가 원하는 아이디를 입력하면 Controller에서 해당 아이디를 사용하는 사용자 수를 찾아 json 형식으로 반환해줍니다. member.js에서는 반환된 데이터에 따라 메시지를 출력해줍니다.

- MemberController.java
```
/*
	 * 아이디 중복 확인
	 */
	@ResponseBody
	@RequestMapping(value="/id_check", method=RequestMethod.POST)
	public int idCheck(@RequestBody String id) throws Exception{
		
		int result = memberService.findMemberById(id); //해당 아이디를 사용하는 사용자 수
		
		return result;
	}
```

- member-mapping.xml
```
<select id="findMemberById" parameterType="String" resultType="int">
		SELECT COUNT(*) FROM member
		WHERE id=#{id}
	</select>
```

- member.js
```
//아이디 중복 확인
function id_check(){
	
	var input_id = $('#id').val();
	$.ajax({
		url:'id_check',
		type:'post',
		data:{id:input_id},
		success:function(data){
			if(data == '1'){ //회원이 존재하면
				$('.msg').css("color","red");
				$('.msg').text("이미 사용중인 아이디입니다");
			}else{ //존재하지 않으면
				$('.msg').css("color","green");
				$('.msg').text("사용가능한 아이디입니다");
				$('#checked_id').val(input_id);
			}
		},
		error:function(){
			alert("에러가 발생했습니다");
		}
	})
}
```

---

- **알라딘api를 통한 도서 검색**

![검색](https://user-images.githubusercontent.com/74645087/105445886-5c756c00-5cb4-11eb-8628-98be4c0cac26.PNG)

알라딘 검색 api를 통해 사용자가 도서를 검색할 수 있도록 하였습니다. 사용자가 리뷰를 남기고자 하는 도서의 제목이나 저자의 이름을 검색하면 입력된 값이 sessionStorage에 저장되고, ajax 비동기 통신을 통해 해당 요청이 전달되어 조건에 부합하는 도서 목록이 호출됩니다.
도서 정보는 페이징 처리를 통해 한 페이지에 5개까지만 출력되도록 하였습니다. 사용자가 이동하고자 하는 페이지 버튼을 클릭하면 sessionStorage에 저장된 이전의 검색 값을 가져와 해당하는 데이터를 호출하게 됩니다.

- write_form.js
```
//도서검색
function search_book(option,keyword){
	var startPage
	if(arguments.length == 2){//검색 버튼을 눌러서 검색할 경우
		startPage = 1; 
		sessionStorage.setItem("option",option); //새로운 option과 keyword를 저장
		sessionStorage.setItem("keyword",keyword);
		
	}else{ 
        /*arguments.length ==1 
         페이지 버튼을 눌렀을 경우 페이지 번호만 option 매개변수의 인자로 전달됨 
       */
		
		startPage = option;//전달된 시작페이지 인자를 저장
		
		var option = sessionStorage.getItem("option"); //이전 조건을 변수에 저장
		var keyword = sessionStorage.getItem("keyword");
	}
		//조건으로 데이터 받아오기
	var url = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=ttblebon2348001&Query="+ keyword +"&QueryType="+ option +"&Cover=Big&MaxResults=5&start="+startPage+"&output=js&callback=bookDisplay";
	
	$.ajax({
		url:url,
		dataType:"jsonp",
		jsonp:"bookDisplay"
	});
}


//받아온 도서 목록을 출력
function bookDisplay(success,data){

	$("#result *").remove();
	var html = "";
	
	//받아온 도서 정보 출력
	for(var i = 0; i < data.item.length; i++){
		html += "<div id='b_wrap' onclick='add_book("+i+")'>";
		html += "<table id='book_result'>";
		html += "<tr><th rowspan='4'><img id='img"+i+"' src="+data.item[i].cover+"></th>";
		html += "<td id='title"+i+"'>"+data.item[i].title+"</td>" +
				"</tr><tr><td id='author"+i+"'>"+data.item[i].author+"</td></tr>" +
				"<tr><td id='pubDate"+i+"'>"+data.item[i].pubDate+"</td></tr>" +
				"<tr><td id='publisher"+i+"'>"+data.item[i].publisher+"</td>";
		html += "</table>";
		html += "<input type='hidden' id='isbn"+i+"' value="+data.item[i].isbn+">";
		html += "</div>";
		
	}
	$("#result").append(html).trigger("create");

	//페이징 처리
	var totalCount = data.totalResults; //총 검색결과 개수
	var pageNum = data.startIndex; //현재 페이지
	var numPerPage = 5; //페이지 당 출력 항목 개수
	var displayPageNum = 10; //화면에 보이는 페이지 수
	var startPage; //시작 페이지 번호
	var endPage; //끝 페이지 번호
	var realEndPage = Math.ceil(totalCount/5) //실제 필요 페이지 수
	
	//멤버 변수 초기값 설정
	//끝 페이지
	endPage = Math.ceil(pageNum/displayPageNum)*displayPageNum;
	startPage = endPage - displayPageNum + 1;
	realEndPage = Math.ceil(totalCount/numPerPage);
	
	if(endPage > realEndPage){
		endPage = realEndPage;
	}

	/*
        페이징 처리
        */
	$(".pagination *").remove();
	html = "";
	if(startPage != 1){ //현재페이지가 첫 페이지가 아닐 경우에만 표시
		html = "<li class='page-item' id='search_book_pag'><a class='page-link' href='javascript:search_book("+(startPage-1)+")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
	}
	
	var i = startPage;
	  for(i ; i <= endPage; i++){
		  if(i == pageNum){ //사용자가 클릭한 현재 페이지는 비활성화
			  html+= "<li class='page-item active' aria-current='page'><span class='page-link'>"+i+"</span></li>";
		  }else{
			  html += "<li class='page-item'><a class='page-link' href='#' onclick='javascript:search_book("+i+")'>"+i+"</a></li>";
		  }
	  }
	 
	if(endPage != realEndPage){ //현재페이지가 마지막 페이지가 아닐 경우에만 표시
	html += "<li class='page-item'><a class='page-link' href='javascript:search_book("+(endPage+1)+")' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
	}
	
	$(".pagination").append(html).trigger("create");
	
	
}
```
---

- **부모창에 선택한 도서 정보 출력**

사용자가 출력된 도서 목록 중 원하는 도서를 선택하면 리뷰 작성 페이지에 사용자가 선택한 도서의 데이터가  출력됩니다.

![독서기록](https://user-images.githubusercontent.com/74645087/105446227-0654f880-5cb5-11eb-824a-4ee2e6b6e949.PNG)

- write_form.js
```
function add_book(num){
	var cover = $("#img"+num).attr("src");
	var title = $("#title"+num).text();
	var author = $("#author"+num).text();
	var pubDate = $("#pubDate"+num).text();
	var publisher = $("#publisher"+num).text();
	var isbn = $("#isbn"+num).val();
		
	$("#cover",parent.opener.document).val(cover);
	$("#title",parent.opener.document).val(title);
	$("#author",parent.opener.document).val(author);
	$("#pubDate",parent.opener.document).val(pubDate);
	$("#publisher",parent.opener.document).val(publisher);
	$("#isbn",parent.opener.document).val(isbn);
	
	$("#book_img",parent.opener.document).attr("src",cover);
	$("#book_title",parent.opener.document).text(title);
	$("#book_author",parent.opener.document).text(author);
	$("#book_pubDate",parent.opener.document).text(pubDate);
	$("#book_publisher",parent.opener.document).text(publisher);
	
	self.close();
}
```
---

- **구글 차트를 이용한 통계**

구글 차트를 이용하여 사용자가 한 해 동안 남긴 리뷰의 개수와 완독,미완독 책의 비율을 확인할 수 있도록 하였습니다. 사용자가 버튼을 클릭하면 ajax를 통해 통계에 필요한 정보를 가져오는 Controller가 호출되고, 해당 Controller에서 데이터베이스에서 가져온 데이터를 json타입으로 반환해주게 됩니다.

![통계](https://user-images.githubusercontent.com/74645087/105446500-95faa700-5cb5-11eb-8250-0f668f64e49d.PNG)

- PostController.java
```
/*
	 * 리뷰 통계 조회
	 */
	@RequestMapping(value="/library_statistics",
					produces="application/json; charset=utf-8")
	@ResponseBody
	public List<LibraryStatistics> salesRecordChart(@RequestParam(value="id",defaultValue="abc",required=true)String id,
													@RequestParam(value="year",defaultValue="2020",required=true)String year){ 
	
		
		List<LibraryStatistics> list = postService.getLibraryStatistics(id,year);
		
		
		return list;
	}
	
	/*
	 * 완,미완독 도서 통계 조회
	 */
	@RequestMapping(value="/readyn_statistics",
					produces="application/json; charset=utf-8")
	@ResponseBody
	public List<ReadynStatistics> readynChart(@RequestParam(value="id",defaultValue="abc",required=true)String id,
											@RequestParam(value="year",defaultValue="2020",required=true)String year){
		
		List<ReadynStatistics> list = postService.getReadynStatistics(id,year);
		
		return list;
	}
	
}
```

- library.js
```
//월별 독서 리뷰 통계 불러오기
function library_statistics(year){
	var id = $("#id").val();
	var year = year;
	
	readyn(id,year);
	
	$.ajax({
		type: 'GET',
		headers: { 
			Accept: "application/json; charset=utf-8",
			"Content-type": "application/json; charset=utf-8"
		},
		url: 'library_statistics',
		data:{
			id:id,
			year:year
		},
		success: function(result) {
			google.charts.load('current', {'packages' : ['corechart']});
			google.charts.setOnLoadCallback(function() {
				drawChart(result);
			})
		},
		error: function() {
			alert("error");
		}
	});
}
	
function drawChart(result) {//리스트로 넘어오면 datatable에 넣음
	var data = new google.visualization.DataTable(); 
 	data.addColumn('string', 'month');
	data.addColumn('number', 'count');
	var dataArray = [];
	$.each(result, function(i, obj) {
		dataArray.push([obj.month, obj.count]);//list방식으로 오기 때문에 그걸 dataArray에 넣어서 준비
	});
	
	data.addRows(dataArray);
	
	// 바차트 그리기
	var barchart_options = {
		title: '월별 리뷰 수',
		width: 350,
		height: 300,
		legend: 'none'
	};
	
	var barchart = new google.visualization.ColumnChart(document.getElementById('barchart_div'));

	barchart.draw(data, barchart_options);
}
```

```
//완,미완독 도서 통계 불러오기
function readyn(id,year){
	
	$.ajax({
		type: 'GET',
		headers: { 
			Accept: "application/json; charset=utf-8",
			"Content-type": "application/json; charset=utf-8"
		},
		url: 'readyn_statistics',
		data:{
			id:id,
			year:year
		},
		success: function(result) {
			google.charts.load('current', {packages : ['corechart']});
			google.charts.setOnLoadCallback(function() {
				drawPieChart(result);
			})
		},
		error: function() {
			alert("error");
		}
	});
}

//파이 차트 그리기
function drawPieChart(result) {
	var data = new google.visualization.DataTable(); 
 	data.addColumn('string', 'readyn');
	data.addColumn('number', 'count');
	var dataArray = [];
	
	$.each(result, function(i, obj) {
		dataArray.push([obj.month, obj.count]);
	});
	
	data.addRows(dataArray);
	

	var piechart_options = {
		title: '완독&미완독',
		width: 350,
		height: 300
	};
	
	var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));
	
	piechart.draw(data, piechart_options);
}
```

# 마무리하며

- 보완점

 도서 검색 기능의 경우 input type을 submit이 아닌 onclick으로 설정하였습니다. 그러다보니 사용자가 엔터를 누를 경우에는 실행되지 않고 꼭 검색 버튼을 직접 클릭해야 하는 불편함이 있었습니다. javascript를 통해 엔터를 눌렀을 때도 기능이 실행될 수 있도록 보완이 필요할 것 같습니다. 또한 데이터베이스에 추가, 수정, 삭제 등이 이루어질 때 예외가 발생했을 시 해당 사항을 commit 하지 않고 rollback하도록 트랜잭션 처리를 한다면 더욱 프로젝트의 완성도를 높일 수 있을 것 같습니다.


- 소감

 누군가의 도움 없이 혼자서 진행해야하다보니 걱정되는 마음이 컸던 프로젝트였습니다. 웹개발에 필요한 지식들을 공부하긴 했지만 막상 혼자 구현을 하려 하니 지금까지 공부했던 내용만으로는 부족하다는 것을 느낄 수 있었습니다. 이미 배웠던 기술임에도 문제가 발생했던 적도 여러 번 있었습니다. 하지만 그럴 때마다 혼자서 해결 방법을 찾고, 새로운 기술을 찾아보며 수업에서 배웠던 것보다 더 많은 것을 경험하고 공부할 수 있었습니다.
 이번 프로젝트를 통해 저의 부족한 점을 많이 알게 되었고, 부족한만큼 더더욱 노력하자는 마음가짐 또한 가지게 되었습니다. 시간과 역량이 부족하여 구현해내지 못한 기능과 미흡한 부분들은 꾸준히 공부하여 앞으로 더욱 보완해나가고 싶습니다.

 
 

