
function login_check(){
	if(document.frm.id.value==""){
		alert("아이디를 입력해주세요");
		document.frm.id.focus();
		return;
		
	}else if(document.frm.password.value==""){
		alert("비밀번호를 입력해주세요");
		document.frm.password.focus();
		return;
	}
		document.frm.action = "login";
		document.frm.submit();
	}



function join_check(){
	if(document.frm.id.value==""){
		alert("아이디를 입력해주세요");
		document.frm.id.focus();
		return false;
	}else if(document.frm.name.value==""){
		alert("이름을 입력해주세요");
		document.frm.name.focus();
		return false;
	}else if(document.frm.nick_name.value==""){
		alert("별명을 입력해주세요");
		document.frm.nick_name.focus();
		return false;
	}else if(document.frm.email.value==""){
		alert("이메일을 입력해주세요");
		document.frm.email.focus();
		return false;
	}else if(document.frm.password.value==""){
		alert("비밀번호를 입력해주세요");
		document.frm.password.focus();
		return false;
	}else if(document.frm.password.value != document.frm.repass.value){
		alert("비밀번호가 일치하지 않습니다");
		document.frm.repass.focus();
		return false;
	}else if(document.frm.checked_id.value != document.frm.id.value){
		alert("다른 아이디를 입력해주세요");
		document.frm.id.focus();
		return false;
	}
	return true;

}


//아이디 중복 확인
function id_check(){
	
	var input_id = $('#id').val();
	$.ajax({
		url:'id_check',
		type:'post',
		data:{id:input_id},
		success:function(data){
			if(data == '1'){
				$('.msg').css("color","red");
				$('.msg').text("이미 사용중인 아이디입니다");
			}else{
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

//정보 수정 확인
function update_member(){
	if(document.frm.nick_name.value==""){
		alert("별명을 입력해주세요");
		document.frm.nick_name.focus();
		return false;
	}else if(document.frm.email.value==""){
		alert("이메일을 입력해주세요");
		document.frm.email.focus();
		return false;
	}else if(document.frm.password.value==""){
		alert("비밀번호를 입력해주세요");
		document.frm.password.focus();
		alert
		return false;
	}else if(document.frm.repass.value==""){
		alert("비밀번호를 확인해주세요");
		document.frm.repass.focus();
		return false;
	}else if(document.frm.password.value != document.frm.repass.value){
		alert("비밀번호가 일치하지 않습니다");
		document.frm.repass.focus();
		return false;
	}else{
		document.frm.encoding="multipart/form-data";
		document.frm.action = "update_member.do";
		document.frm.submit();
	}
}

//비밀번호 입력 확인
function pass_check(){
	if(document.frm.pass_check.value==""){
		alert("비밀번호를 입력해주세요");
		return false;
	}
	return true;
}

//아이디 찾기
function find_id(){
	if(document.frm.name.value == ""){
		alert("이름을 입력해주세요");
		document.frm.name.focus();
		return;
	}else if(document.frm.email.value == ""){
		alert("이메일을 입력해주세요");
		document.frm.email.focus();
		return;
	}
	document.frm.action = "find_id";
	document.frm.submit();
}

//비밀번호 찾기
function find_pwd(){
	if(document.p_frm.id.value == ""){
		alert("아이디를 입력해주세요");
		document.p_frm.id.focus();
		return;
	}else if(document.p_frm.name.value == ""){
		alert("이름을 입력해주세요");
		document.p_frm.name.focus();
		return;
	}else if(document.p_frm.email.value == ""){
		alert("이메일을 입력해주세요");
		document.p_frm.email.focus();
		return;
	}
	document.p_frm.action = "find_password";
	document.p_frm.submit();
}

//내 서재 이동
function go_library(){
	$('#frm').action = "library";
	alert("go_library() 작동");
	$('#frm').submit();
	alert("go_library submit()");
}

//친구 추가
function add_friend(f_id){
	
	if(confirm("친구추가 하시겠습니까?")){

		$.ajax({
			url:"add_friend.do",
			type:"POST",
			data:{f_id:f_id},
			success:function(data){
				if(data == 1){
					alert("친구추가 완료");
				}else{
					alert("이미 추가된 친구입니다");
				}
			},error:function(){
				console.log("error");
			}
			
		})

	}	
}