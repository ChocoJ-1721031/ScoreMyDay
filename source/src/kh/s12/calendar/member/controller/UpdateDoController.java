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

@WebServlet("/updateMember.do")
public class UpdateDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateDoController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		MemberService mservice = new MemberService();
		MemberVO vo = new MemberVO();
		PrintWriter out = response.getWriter();
		int result = 0;
		
		String inputPwd = request.getParameter("pwChange_val");
		String inputMname = request.getParameter("nameChange_val");
		
		result = mservice.updateMember(vo, inputPwd, inputMname);
		
		if(result > 0) {
			out.append("ok");
		} else {
			out.append("fail");
		}
	}

}
