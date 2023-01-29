<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kh.s12.calendar.member.model.MemberVO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<% MemberVO vo = (MemberVO) request.getSession().getAttribute("member"); %>
<link rel="shortcut icon" href="#"> <!-- 브라우저 아이콘 -->
<link rel='stylesheet' href='/resources/fullcalendar-5.11.3/lib/main.css'/>
<link rel="stylesheet" href="/resources/css/reset.css"/>
<link  rel='stylesheet' href='/resources/css/header.css'/>
<link  rel='stylesheet' href='/resources/css/index.css'/>

<script src='/resources/js/jquery-3.6.1.js'></script>
<script src='/resources/fullcalendar-5.11.3/lib/main.js'></script>
<script src='/resources/js/index.js'></script>
<script src='/resources/js/header.js'></script>

<title>Calendar</title>
</head>
<body>
	<div id="header">
		<div>
			<h1 onclick="main()">Score My Day</h1>
			<% if(vo == null){ %>
			<div>
				<button type="button" onclick="login()">로그인</button>
			</div>
			<%} else {%>
			<div>
				<button type="button" onclick="logout()">로그아웃</button>
				<button type="button" onclick="mypage()">마이페이지</button>
			</div>
			<%}%>
			<%if(vo != null) {int mid = vo.getMid();%>
			<input type="hidden" id="mid" name="mid" value="<%=mid%>">
			<%}%>
		</div>
	</div>

	<div id="section">
		<div>
			<div>
				<div class="form-inline">
					<div>
						<input type="text" id="searchInput" name="searchInput" placeholder="검색할 일정을 입력해주세요.">
						<button type="button" id="searchBtn" onclick="searchSchedule()">검색</button>
					</div>
				</div>
			</div>
			<div>
				<div id='calendar'></div>
			</div>
		</div>
	</div>
</body>
</html>