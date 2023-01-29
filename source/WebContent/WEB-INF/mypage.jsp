<%@page import="kh.s12.calendar.servey.model.ServeyVO"%>
<%@page import="kh.s12.calendar.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<% MemberVO mvo = (MemberVO) request.getSession().getAttribute("member"); %>
<% ServeyVO svo = (ServeyVO) request.getSession().getAttribute("servey"); %>
<link rel="shortcut icon" href="#"> <!-- 브라우저 아이콘 -->
<link rel='stylesheet' href='/resources/fullcalendar-5.11.3/lib/main.css'/>
<link rel="stylesheet" href="/resources/css/reset.css"/>
<link  rel='stylesheet' href='/resources/css/header.css'/>
<link  rel='stylesheet' href='/resources/css/mypage.css'/>

<script src='/resources/js/jquery-3.6.1.js'></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="/resources/js/chart.js"></script>
<script src='/resources/fullcalendar-5.11.3/lib/main.js'></script>
<script src='/resources/js/mypage.js'></script>
<script src='/resources/js/header.js'></script>

<title>Score My Day | 마이페이지</title>
</head>
<body>
	<div id="header">
		<div>
			<h1 onclick="main()">Score My Day</h1>
			<% if(mvo == null){ %>
			<div>
				<button type="button" onclick="login()">로그인</button>
			</div>
			<%} else {%>
			<div>
				<button type="button" onclick="logout()">로그아웃</button>
				<button type="button" onclick="main()">홈</button>
			</div>
			<%}%>
			<%if(mvo != null) {int mid = mvo.getMid();%>
			<input type="hidden" id="mid" name="mid" value="<%=mid%>">
			<%}%>
		</div>
	</div>
	
	<div id="section">
		<div>
			<div id="profile_area">
				<span><%=mvo.getMname()%>님, 안녕하세요</span>
				<div id="nameArea">
					<button type="button" onclick="changeName()">닉네임 변경</button>
				</div>
				<div id="pwdArea">
					<button type="button" onclick="changePwd()">비밀번호 변경</button>
				</div>
			</div>
			
			<div id="serveyData_area">
				<div id="score_area">
					<div>
						<div id="servey_avg">
							<div id="servey_avg_title">설문 평균</div>
							<div id="servey_avg_data"></div>
						</div>
						<div id="score_avg">
							<div id="score_avg_title">점수 평균</div>
							<div id="score_avg_data"></div>
						</div>
					</div>
					<select name="range">
						<option value="week" selected>1주</option>
						<option value="month">1개월</option>
						<option value="year">1년</option>
					</select>
				</div>
				
				<div id="graph_area">
					<canvas id="myChart"></canvas>
				</div>
			</div>
		</div>
	
		
	</div>
	
	
	
</body>
</html>