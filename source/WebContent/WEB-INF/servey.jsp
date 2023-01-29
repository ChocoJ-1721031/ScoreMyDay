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
<link  rel='stylesheet' href='/resources/css/servey.css'/>

<script src='/resources/js/jquery-3.6.1.js'></script>
<script src='/resources/fullcalendar-5.11.3/lib/main.js'></script>
<script src='/resources/js/servey.js'></script>
<script src='/resources/js/header.js'></script>

<title>하루의 평가</title>
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
				<button type="button" onclick="myPage()">마이페이지</button>
			</div>
			<%}%>
			<%if(mvo != null) {int mid = mvo.getMid();%>
			<input type="hidden" id="mid" name="mid" value="<%=mid%>">
			<%}%>
		</div>
	</div>
	
	<div id="section">
		<div>
			<div id="title"><%=svo.getdDate()%> 일자 데일리 평가</div>
			<input type="hidden" name="date" value="<%=svo.getdDate()%>">
			<div id="">
				<table>
					<tr>
						<td><span>설문 내용</span></td>
						<td>
							<span>전혀 그렇지 않다 1 ~ 5 매우 그렇다</span>
						</td>
					</tr>
					<tr>
						<td><span>대충 설문 내용 이러쿵 저러쿵</span></td>
						<td>
							<input type="radio" name="q_1" value="1">
							<input type="radio" name="q_1" value="2">
							<input type="radio" name="q_1" value="3">
							<input type="radio" name="q_1" value="4">
							<input type="radio" name="q_1" value="5">
						</td>
					</tr>
					<tr>
						<td><span>대충 설문 내용 이러쿵 저러쿵</span></td>
						<td>
							<input type="radio" name="q_2" value="1">
							<input type="radio" name="q_2" value="2">
							<input type="radio" name="q_2" value="3">
							<input type="radio" name="q_2" value="4">
							<input type="radio" name="q_2" value="5">
						</td>
					</tr>
					<tr>
						<td><span>대충 설문 내용 이러쿵 저러쿵</span></td>
						<td>
							<input type="radio" name="q_3" value="1">
							<input type="radio" name="q_3" value="2">
							<input type="radio" name="q_3" value="3">
							<input type="radio" name="q_3" value="4">
							<input type="radio" name="q_3" value="5">
						</td>
					</tr>
					<tr>
						<td><span>대충 설문 내용 이러쿵 저러쿵</span></td>
						<td>
							<input type="radio" name="q_4" value="1">
							<input type="radio" name="q_4" value="2">
							<input type="radio" name="q_4" value="3">
							<input type="radio" name="q_4" value="4">
							<input type="radio" name="q_4" value="5">
						</td>
					</tr>
					<tr>
						<td><span>대충 설문 내용 이러쿵 저러쿵</span></td>
						<td>
							<input type="radio" name="q_5" value="1">
							<input type="radio" name="q_5" value="2">
							<input type="radio" name="q_5" value="3">
							<input type="radio" name="q_5" value="4">
							<input type="radio" name="q_5" value="5">
						</td>
					</tr>
				</table>
			</div>
			<div>
				<span></span>
				<input type="text" name="score"><p>점</p>
			</div>
			<div>
				<span></span>
				<textarea name="diary"></textarea>
			</div>
			
			<div>
				<button type="button" onclick="addServey()">제출하기</button>
			</div>
		</div>
	</div>
	
</body>
</html>