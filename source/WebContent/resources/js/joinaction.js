window.onload = function() {
	//메일 값 변경시 중복체크 활성화, 인증코드 영역 삭제
	$('#mail').keyup(function(event) {
		var dupChkBtn = document.getElementById('dupChkButton');
		dupChkBtn.disabled = "";
		$('#mailPopM').remove();
		if($('#codeArea').length != 0) {
			$('#codeArea').remove();
		}
		
		$('#mailOk').val('0');
	});
	
	//비밀번호 체크
	var regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
	$('#pw').keyup(function(event) {
		if($('#pw').val().match(regExp) == null) {
			if($('#pwPopM').length == 0) {
				var pwParent = document.getElementById('pwArea').firstElementChild;
				var pwChild = document.createElement('div');
				pwChild.setAttribute("class", 'popChild');
				var inputMessage = document.createElement('span');
				inputMessage.setAttribute("class", 'popMessage');
				inputMessage.setAttribute("id", 'pwPopM');
				inputMessage.innerText = "영문, 숫자, 특수기호를 포함하여 최소 8자 입력해 주세요.";
				
				pwParent.appendChild(pwChild);
				pwChild.appendChild(inputMessage);
				
			}
			
		} else {
			$('#pwPopM').remove();
		}
	});
	
	//비밀번호 확인 체크
	$('#pw2').keyup(function(event) {
		if($('#pw2').val() != $('#pw').val()) {
			if($('#pw2PopM').length == 0) {
				var pwParent = document.getElementById('pwChkArea').firstElementChild;
				var pwChild = document.createElement('div');
				pwChild.setAttribute("class", 'popChild');
				var inputMessage = document.createElement('span');
				inputMessage.setAttribute("class", 'popMessage');
				inputMessage.setAttribute("id", 'pw2PopM');
				inputMessage.innerText = "비밀번호가 일치하지 않습니다.";
				
				pwParent.appendChild(pwChild);
				pwChild.appendChild(inputMessage);
				
			}
			
		} else {
			$('#pw2PopM').remove();
		}
	});
	
	//메일 중복체크
	$('#dupChkButton').click(function(event) {
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		var chk = 0;
		if ($('#mail').val() == null || $('#mail').val() == "") {
			if($('#mailPopM').length == 0) {
				var mailParent = document.getElementById('mail_input_area').firstElementChild;
				var pwChild = document.createElement('div');
				pwChild.setAttribute("class", 'popChild');
				var inputMessage = document.createElement('span');
				inputMessage.setAttribute("class", 'popMessage');
				inputMessage.setAttribute("id", 'mailPopM');
				inputMessage.innerText = "이메일을 입력해주세요.";
				
				mailParent.appendChild(pwChild);
				pwChild.appendChild(inputMessage);
			}
		} else { //정규표현식 else if 추가 예정
			if($('#mail').val().match(regExp) == null) {
				alert("이메일 형식에 맞게 입력해 주세요.")
			} else {
				$.ajax({
					url: getContextPath()+"/dupchk.lo",
					type: "post",
					async: false,
					data: {
						mail: $('#mail').val()
					},
					dataType: "text",
					success: function(value) {
						if (value === "fail") {
							alert("이미 사용중인 이메일입니다.\n다른 이메일을 사용해 주세요.");
						} else {
							alert("사용 가능한 이메일입니다.");
							chk++;
							
							//인증코드 영역 생성
							var form = document.getElementById('form');
							var pwArea = document.getElementById('pwArea');
							var codeArea = document.createElement('div');
							codeArea.setAttribute("id", 'codeArea');
							codeArea.setAttribute("class", 'inputArea');
							
							form.insertBefore(codeArea, pwArea);
							
							//인증코드 입력란 생성
							var codeParent = document.getElementById('codeArea');
							var inputCode = document.createElement('input');
							inputCode.setAttribute("id", "mailCodeInput");
							inputCode.setAttribute("class", "joinInput inpWthBtn");
							inputCode.setAttribute("type", "text");
							inputCode.setAttribute("name", "codeInput");
							inputCode.setAttribute("placeholder", "인증코드");
							inputCode.setAttribute("maxlength", "6");
							
							codeParent.appendChild(inputCode);
							
							//인증코드 전송 버튼 생성
							var codeBtn = document.createElement('button');
							codeBtn.setAttribute("id", 'mailCodeBtn');
							codeBtn.setAttribute("class", 'inputbtn');
							codeBtn.setAttribute("type", 'button');
							codeBtn.setAttribute("onclick", 'codeBtn()');
							codeBtn.innerText = "인증코드 전송";
							
							codeParent.appendChild(codeBtn);
							
							//중복체크 버튼 비활성화
							var dupChkBtn = document.getElementById('dupChkButton');
							dupChkBtn.disabled = "true";
						}
					},
					error: function(request, status, error) {
						alert("code" + request.status + "\n" + "message : " + request.responseText + "\nerror" + error);
					}
				});
			}
		}

		if (chk == 0) {
			event.preventDefault();
		}
	});
	
	
}

//인증코드 메일 전송
function codeBtn() {
	var btn = document.getElementById('mailCodeBtn');
	var chk = 0;
	var mailCode;
	var url = getContextPath()+"/codeSend.lo";
	var data;
	function mailCode1(value) {
		if (value === "fail") {
			alert("이메일 전송 실패!");
		} else {
			alert("인증번호가 전송됐습니다. 이메일을 확인해 주세요.");
			chk++;
			btn.innerText = "확인";
		}
	}
	function mailCode2(value) {
		if (value === "fail") {
			alert("코드 인증 실패!");
		} else {
			alert("코드가 일치합니다.");
			chk++;
			$('#codeArea').remove();
			$('#mailOk').val('1');
		}
	}
	
	
	if(btn.innerText == "인증코드 전송") {
		mailCode = mailCode1;
		url = "/codeSend.lo";
		data = $('#mail').val();
	} else {
		url = "/codeChk.lo";
		mailCode = mailCode2;
		data = $('#mailCodeInput').val();
	}
	
	$.ajax({
		url: getContextPath()+url,
		type: "post",
		async: false,
		data: {
			data : data
		},
		dataType: "text",
		success: function(value) {
			mailCode(value);
		},
		error: function(request, status, error) {
			alert("code" + request.status + "\nmessage : " + request.responseText + "\nerror" + error);
		}
	});

	if (chk == 0) {
		event.preventDefault();
	}
}


function main() {
	location.href = getContextPath()+"/main";
}
function login() {
	location.href = getContextPath()+"/login";
}

function join() {
	var chk = 0;
	if($('#mailOk').val() == 1 && $('#pw').val() != "" && $('#pw').val() == $('#pw2').val() && $('#mname').val() != null) {
		$.ajax({
			url: "/join.lo",
			type: "post",
			async: false,
			data: {
				mname : $('#mname').val(),
				mail : $('#mail').val(),
				pw : $('#pw').val()
			},
			dataType: "text",
			success: function(value) {
				if (value === "fail") {
					alert("회원가입 실패! 재시도 해주세요.");
				}
				else {
					alert("회원가입 되었습니다. 로그인 해주세요.");
					chk++;
				}
			},
			error: function(request, status, error) {
				alert("code" + request.status + "\nmessage : " + request.responseText + "\nerror" + error);
			}
		});	
	} else {
		if($('#mname').val() == "") {
			alert("닉네임을 입력해 주세요.")
		} else if($('#mailOk').val() == 0) {
			alert("이메일을 인증해 주세요.")
		} else if($('#pw').val() == "") {
			alert("비밀번호를 입력해 주세요.")
		} else if($('#pw2').val() == "") {
			alert("비밀번호를 확인해 주세요.")
		}
	}
	
	if (chk == 0) {
		event.preventDefault();
	} else if(chk == 1) {
		location.href = getContextPath()+"/login";
	}
	
}













