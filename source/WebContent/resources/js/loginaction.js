window.onload = function() {
	$('#submit').click(function(event){
		var mail = $('#mail').val();
		var pw = $('#pw').val();
		var chk = 0;
		$.ajax({
			url: getContextPath()+"/login.do",
			type: "post",
			async: false,
			data: {
				mail : mail,
				pw : pw
			},
			dataType: "text",
			success: function(value) {
				if(value == "fail") {
					alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
				} else {
					chk++;
				}
			},
			error: function(request, status, error) {
				alert("code" + request.status + "\nmessage : " + request.responseText + "\nerror" + error);
			}
		});
		
		if(chk == 1) {
			location.href = getContextPath()+"/main";
		}
		
	});
}

function join() {
	location.href = getContextPath()+"/join";
}