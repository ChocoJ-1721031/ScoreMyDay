<%@page import="kh.s12.calendar.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/header.css">
<link rel="stylesheet" href="/resources/css/join.css">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-3.6.1.js"></script>
<script type="text/javascript" src="/resources/js/joinaction.js"></script>
<script type="text/javascript" src="/resources/js/header.js"></script>

</head>
<body>
	<div id="header">
		<div>
			<h1 onclick="main()">Score My Date</h1>
			<div>
				<button type="button" onclick="login()">로그인</button>
				<button type="button" onclick="main()">홈</button>
			</div>
		</div>
		
	</div>
	
	<div id="section">
		<div>
			<span id="form_title">회원가입</span>
			<form id="form">
				<div id="name_input_area" class="inputArea">
					<div><input type="text" class="joinInput" id="mname" name="mname" placeholder="닉네임을 입력해주세요" autocomplete="off" value=""></div>
				</div>
				<div id="mail_input_area" class="inputArea">
					<div><input type="text" class="joinInput" id="mail" name="mail" placeholder="이메일을 입력해주세요" autocomplete="off" value=""></div>
					<button type="button" id="dupChkButton" class="inputbtn">이메일 인증</button>
				</div>
				<div id="pwArea" class="inputArea">
					<div><input type="password" class="joinInput" id="pw" name="pw" placeholder="비밀번호를 입력해주세요" autocomplete="off" value=""></div>
				</div>
				<div id="pwChkArea" class="inputArea">
					<div><input type="password" class="joinInput" id="pw2" name="pw2" placeholder="비밀번호를 한번 더 입력해주세요" autocomplete="off" value=""></div>
				</div>
				<input type="hidden" id="mailOk" value="0">
				<button type="button" id="submit" onclick="join()">가입하기</button>
			</form>
			
		</div>
	</div>
</body>
</html>