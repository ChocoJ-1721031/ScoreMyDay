package kh.s12.calendar.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s12.calendar.member.model.MemberService;

@WebServlet("/dupchk.lo")
public class MailDupChkDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MailDupChkDoController() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json;charset=UTF-8");
    	MemberService mservice = new MemberService();
    	PrintWriter out = response.getWriter();
    	
    	int result = mservice.dupChk(request.getParameter("mail"));
		
		if(result > 0) {			
			System.out.println("/dupchk.do 메일 사용 불가");
			out.append("fail");
		} else {
			System.out.println("/dupchk.do 메일 사용 가능");
			out.append("ok");
		}
	}

}
