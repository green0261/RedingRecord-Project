
/*검색 기능*/
function search_keyword(){

	if(document.search_form.keyword == ""){
		alert("검색어를 입력해주세요");
		return false;
	}
	return true;
}