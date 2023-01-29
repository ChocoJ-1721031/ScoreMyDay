package kh.s12.calendar.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kh.s12.calendar.member.model.MemberService;
import kh.s12.calendar.member.model.MemberVO;

@WebServlet("/join.lo")
public class JoinDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public JoinDoController() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		MemberService mservice = new MemberService();
		MemberVO vo = new MemberVO();
		PrintWriter out = response.getWriter();
		
		String mname = request.getParameter("mname");
		String mail = request.getParameter("mail");
		String pw = request.getParameter("pw");
		String ip = "000.000.000.000";
		
		System.out.println(mname);
		System.out.println(mail);
		System.out.println(pw);
		System.out.println(ip);
		
		vo.setMname(mname);
		vo.setMemail(mail);
		vo.setMpwd(pw);
		vo.setMip(ip);
		
		
		int result = mservice.insertMember(vo);
		
		if(result > 0) {
			System.out.println("회원가입 성공");
			out.append("ok");
		} else {
			System.out.println("회원가입 실패");
			out.append("fail");
		}
	}
	
	

}
