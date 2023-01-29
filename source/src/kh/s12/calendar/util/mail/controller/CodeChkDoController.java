package kh.s12.calendar.util.mail.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import kh.s12.calendar.util.mail.MailService;


@WebServlet("/codeChk.lo")
public class CodeChkDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CodeChkDoController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String sessionCode = (String) session.getAttribute("sessCode");
		String codeInput = request.getParameter("data");
		
		int cs = MailService.codeChk(codeInput, sessionCode);
		
		if(cs == 1) { // 세션 codeChk가 1이냐 아니냐 여부로 "인증번호 확인" 버튼 활성화 상태 유지, 인증번호 입력란 사라진 후 "인증 완료" 텍스트 출력 여부 결정
			System.out.println("코드 인증 성공");
			out.append("ok");
		} else {
			System.out.println("코드 인증 실패");
			out.append("fail");
		}
	}

}
