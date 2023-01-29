package kh.s12.calendar.servey.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kh.s12.calendar.member.model.MemberVO;
import kh.s12.calendar.servey.model.ServeyService;
import kh.s12.calendar.servey.model.ServeyVO;

@WebServlet("/checkServey.do")
public class CheckServeyDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CheckServeyDoController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		ServeyService sservice = new ServeyService();
		ServeyVO svo = new ServeyVO();
		
		svo.setdDate(request.getParameter("date"));
		svo.setMid(mvo.getMid());
		
		session.setAttribute("servey", svo);
		
		int result =  sservice.checkServey(svo);
		System.out.println(result);
		if(result > 0) {
			out.append("yes");
		} else {
			out.append("no");
		}
		
	}

}
