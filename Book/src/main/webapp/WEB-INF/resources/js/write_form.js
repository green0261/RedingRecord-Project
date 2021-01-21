/**
 * 
 */
//책 검색 화면으로 이동
function search_book_form(){
	var url = "search_book_form.do";
	
	var popupWidth = 450;
	var popupHeight = 790;
	var popupX = (window.screen.width/2)-(popupWidth/2); //팝업창이 화면 가운데 위치하도록 지정
	var popupY = (window.screen.height/2)-(popupHeight/2);
	window.open(url, "_blank_1",
	"toolbar=no, menubar=no, scrollbars=no,status=no,resizable=no,width=450,height=790,left="+popupX+",top="+popupY);
	
	
}

//책 검색 값 입력 확인
function search_check(){
	var option = $("#option").val();
	var keyword = $("#keyword").val();
	
	if(keyword == ""){
		alert("검색어를 입력해주세요");
		$("#keyword").focus();
		return;
	}
	if(option=="title"){
		option = "Title";
	}else{
		option = "Author";
	}
	
	search_book(option,keyword);
}


//도서검색
function search_book(option,keyword){
	var startPage
	if(arguments.length == 2){//검색 버튼을 눌러서 검색
		startPage = 1; //시작 페이지를 1로 고정
		sessionStorage.setItem("option",option); //새로운 option과 keyword를 저장
		sessionStorage.setItem("keyword",keyword);
		
	}else{ //arguments.length ==1 페이지 버튼을 눌렀을 경우 페이지 번호만 option 매개변수의 인자로 전달됨
		
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

//	//화면에 페이지 표시해주기
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


function write_check(){
	
	var isbn = $("#isbn").val();
	var post_title = $("#post_title").val();
	var p_content = $("#p_content").val();
	
	if(isbn == ""){
		alert("도서를 선택해주세요");
		$("#isbn").focus();
		return;
	}else if(post_title == ""){
		alert("제목을 입력해주세요");
		$("#post_title").focus();
		return;
	}else if(p_content == ""){
		alert("내용을 입력해주세요");
		$("#p_content").focus();
		return;
	}
	
	document.getElementById("frm").action = "write_post.do";
	document.getElementById("frm").submit();
}

/*리뷰 수정*/
function update_p_check(){
	
	var isbn = $("#isbn").val();
	var post_title = $("#post_title").val();
	var p_content = $("#p_content").val();
	
	if(isbn == ""){
		alert("도서를 선택해주세요");
		$("#isbn").focus();
		return;
	}else if(post_title == ""){
		alert("제목을 입력해주세요");
		$("#post_title").focus();
		return;
	}else if(p_content == ""){
		alert("내용을 입력해주세요");
		$("#p_content").focus();
		return;
	}
	
	document.getElementById("frm").action = "update_post.do";
	document.getElementById("frm").submit();
}

//책갈피 추가

function add_record_form(){
	var state = document.getElementById("btn_state").value;
	
	if(state == "open"){
		$("#record_form").css("display","block");
		document.getElementById("add_btn").value = "창 닫기";
		document.getElementById("btn_state").value = "close";
	}else{
		$("#record_form").css("display","none");
		document.getElementById("add_btn").value = "기록 추가";
		document.getElementById("btn_state").value = "open";
	}
}

//책갈피 입력 확인
function add_record_check(){
	var pseq = $("#pseq").val();
	var page = $("#page").val();
	page = Number(page);
	var content = $("#r_content").val();
	
	
	if(isNaN(page)){
		alert("page는 숫자만 입력 가능합니다");
		$("#frm").page.focus();
		return;
	}else if(page==0){
		alert("페이지를 입력해주세요");
		$("#frm").page.focus();
		return;
	}else if(content == ""){
		alert("내용을 입력해주세요");
		$("#frm").r_content.focus();
		return;
	}
	insertRecord(pseq,page,content);
	
}

function insertRecord(pseq,page,content){
	$.ajax({
		url: "insert_record.do",
		type:"GET",
		data:{
			pseq: pseq,
			page: page,
			content: content
		},
		success:function(data){
			if(data == 1){
				alert("등록되었습니다");
				document.frm.action="post_view";
				document.frm.submit();
				
				$("#record_list").append(html).trigger("create");
				
			}else{
				alert("등록 실패");
			}
		},error:function(){
			console.log("에러 발생");
		}
	})
}