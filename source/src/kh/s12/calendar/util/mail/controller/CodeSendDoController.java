package kh.s12.calendar.util.mail.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import kh.s12.calendar.util.mail.MailService;
import kh.s12.calendar.util.mail.RandCode;

@WebServlet("/codeSend.lo")
public class CodeSendDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CodeSendDoController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("/codeSend.lo 호출 성공");
    	response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String memail = request.getParameter("data");
		String rndCode = RandCode.numberGen(6,2); //인증코드
		
		int cs = MailService.codeSend(memail, rndCode);
		
		if(cs == 1) { //세션 codeSend 값이 1이냐 아니냐로 "인증번호 전송", "인즌번호 확인" 버튼 활성화 여부 결정
			System.out.println("메일 전송 성공");
			session.setAttribute("sessCode", rndCode);
			out.append("ok");
		} else {
			System.out.println("메일 전송 실패");
			out.append("fail");
		}
	}

}
