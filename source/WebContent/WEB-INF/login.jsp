<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/resources/css/reset.css"/>
<link  rel='stylesheet' href='/resources/css/header.css'/>
<link  rel='stylesheet' href='/resources/css/login.css'/>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-3.6.1.js"></script>
<script type="text/javascript" src="/resources/js/loginaction.js"></script>
<script type="text/javascript" src="/resources/js/header.js"></script>
<script type="text/javascript">
var path="<%=request.getContextPath()%>";
</script>
</head>
<body>
	<div id="header">
		<div>
			<h1 onclick="main()">Score My Day</h1>
			<div>
				<button type="button" onclick="join()">회원가입</button>
				<button type="button" onclick="main()">홈</button>
			</div>
		</div>
	</div>
	
	<div id="section">
		<div>
			<form>
				<div><h1>로그인</h1></div>
				<div>
					<input class="" type="text" id="mail" name="mail" placeholder="이메일">
					<input class="" type="password" id="pw" name="pw" placeholder="비밀번호">
				</div>
				<div><a href="#">비밀번호 찾기</a></div>
				<div>
					<button type="button" id="submit">로그인</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>