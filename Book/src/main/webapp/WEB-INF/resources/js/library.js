/**
 * 
 */
//리뷰 삭제
function delete_post(){
	document.frm.action = "delete_post.do";
	document.frm.submit();
}

//리뷰 수정
function update_post_form(){
	document.form.action = "update_post_form.do";
	document.form.submit();
}

//책갈피 수정 화면으로 이동
function update_record_form(rseq){

	var url = "update_record_form.do?rseq=" + rseq;
	window.open(url, "_blank_1",
			"toolbar=no, menubar=no, scrollbars=no,resizable=no,width=300,height=230");
}

//책갈피 수정 체크
function update_check(){
	
	var rseq = document.frm.rseq.value;
	var pseq = document.frm.pseq.value;
	var page = document.frm.page.value;
	var content = document.frm.content.value;
	
	if(page == "" || page == 0){
		alert("페이지수를 입력하세요");
		document.frm.page.focus();
		return;
	}else if(isNaN(page)){
		alert("페이지는 숫자만 입력 가능합니다");
		document.frm.page.focus();
		return;
	}else if(content == ""){
		alert("내용을 입력해주세요");
		document.frm.content.focus();
		return;
	}else{
		update_record(rseq,pseq,page,content);
		
	}
}

//책갈피 수정
function update_record(rseq,pseq,page,content){

	$.ajax({
		url:"update_record.do",
		type:"GET",
		data:{
			rseq:rseq,
			page:page,
			content:content
		},
		success:function(data){
			if(data == 1){
				alert("수정되었습니다");
				opener.document.frm.action ="post_view";
				opener.document.frm.submit();
				self.close();

			}else{
				alert("수정 실패");
				self.close();
			}
		
		},error:function(){
			console.log("error");
			self.close();
		}
	})
}

//책갈피 삭제
function delete_record(){
	var rseq = document.frm.rseq.value;
	
	if(confirm("삭제하시겠습니까?")){
		$.ajax({
			url:"delete_record.do",
			data:{rseq:rseq},
			type:"GET",
			success:function(data){
				if(data==1){
					alert("삭제되었습니다");
					opener.document.frm.action = "post_view";
					opener.document.frm.submit();
					self.close();
				}else{
					alert("삭제 실패하였습니다");
					self.close();
					
				}
			},error:function(){
				console.log("에러 발생");
			}
		})
	}
	
}

//댓글 내용이 입력되었는지 체크
function cmt_check(){
	
	var content = $("#content").val();
	var pseq = $("#pseq").val();
	
	if(!content){
		alert("댓글을 입력해주세요");
		$("#content").focus();
	}else{
		write_cmt(content,pseq);
	}
}

//댓글 등록
function write_cmt(ctt,pseq){
	
	$.ajax({
		url:"write_comment.do",
		type:"POST",
		data:{
			content:ctt,
			pseq:pseq
		},
		success:function(data){
			if(data == 1){
				document.frm.action = "post_view";
				document.frm.submit();
			}else{
				alert("댓글 등록 실패");
			}
		},error:function(){
			console.log("error");		}
	})
}

//댓글 삭제
function delete_cmt(cseq){
	if(confirm("삭제하시겠습니까?")){
		$.ajax({
			url:"delete_comment.do",
			type:"GET",
			data:{
				cseq:cseq
			},
			success:function(data){
				if(data == 1){
					alert("삭제되었습니다");
					document.frm.action = "post_view";
					document.frm.submit();
				}else{
					alert("삭제 실패");
				}
			},error:function(){
				console.log("error");
			}
		})
	}
}

//post_view에서 목록 화면으로 이동(나중에 수정)
function go_list(pageNum,numPerPage){
	var form = document.form;
	frm.action = "get_books?pageNum="+pageNum+"&numPerPage="+numPerPage;
	frm.submit();
}

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
