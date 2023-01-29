package kh.s12.calendar.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import kh.s12.calendar.member.model.MemberService;
import kh.s12.calendar.member.model.MemberVO;

@WebServlet("/login.do")
public class LoginDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginDoController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("application/json;charset=UTF-8");
		MemberService mservice = new MemberService();
		PrintWriter out = response.getWriter();
		System.out.println("/login.do 컨트롤러");
		
		String mail = request.getParameter("mail");
		String pw = request.getParameter("pw");
		
		MemberVO vo = mservice.login(mail, pw); 
		
		if(vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", vo);
			System.out.println("/login 로그인 성공!");
			System.out.println(vo.getMemail() + "님이 접속하였습니다.");
			out.append("ok");
		} else {
			System.out.println("/login 로그인 실패!");
			out.append("fail");
		}
	}
}
