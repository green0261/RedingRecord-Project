/**
 * 
 */

$(document).ready(function(){
	sessionStorage.setItem("startNum",3);
	sessionStorage.setItem("endNum",6);
		
	$(document).scroll(function(){
		
		var maxHeight = $(document).height();
		var currentScroll = $(window).scrollTop()+$(window).height();
		var id = document.frm.id.value;
		
		
		if(maxHeight <= currentScroll){
			
			var startNum = sessionStorage.getItem("startNum");
			var endNum = sessionStorage.getItem("endNum");
			
			$.ajax({
				url:"get_friend_post.do",
				type:"POST",
				dataType:"json",
				data:{
					id:id,
					startNum:startNum,
					endNum:endNum
				},
				success:function(data){
					
					var html = "";
					$.each(data,function(idx,val){
						html += "<li><article><header><h3><a href='post_view?pseq="+val.pseq+"&id="+val.id+"'>";
						html += "<span class='bold'>"+val.nick_name+"</span>님이 <span class='bold'>"+val.days+"</span>일 전 <span class='bold'>&lt;"+val.post_title+"&gt;</span> 리뷰를 작성했습니다</a></h3></header>";
						html += "<a class='image' href='post_view?pseq="+val.pseq+"&id="+val.id+"'><img src='"+val.cover+"'/></a>";
						html += "</article></li>";
							
					})
					$(".posts").append(html).trigger("create");
					
				
					sessionStorage.setItem("startNum",Number(startNum)+3);
					sessionStorage.setItem("endNum",Number(endNum)+3);
				},
				error:function(){
					alert("error");
				}
			})
		}
	})	
})