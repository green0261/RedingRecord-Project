/**
 * 
 */
//문의글 삭제
function delete_qna(){
	var form = document.getElementById("qna_form");
	form.action = "qna_delete.do";
	form.submit();
}

/*
 * 문의글 페이지에서 문의글 리스트로 이동
 */
function go_list(pageNum, numPerPage) {
	var theForm = document.qna_form;
	//qna 리스트로 이동하되 현재 페이지를 쿼리 스트링으로 넘긴다.
	theForm.action = "qna_list?pageNum="+pageNum+"&numPerPage="+numPerPage;
	theForm.submit();
}

//문의글 쓰기 check
function qna_write_check(){
	var theForm = document.getElementById("form");
	if(theForm.qna_title.value == ""){
		alert("제목을 입력해주세요");
		theForm.qna_title.focus();
		return;
	}else if(theForm.qna_content.value == ""){
		alert("내용을 입력해주세요");
		theForm.qna_content.focus();
		return;
	}
	theForm.action = "qna_write.do";
	theForm.submit();
}