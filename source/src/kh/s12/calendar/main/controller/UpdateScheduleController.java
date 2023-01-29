package kh.s12.calendar.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kh.s12.calendar.main.model.CalendarService;
import kh.s12.calendar.main.model.CalendarVO;
import kh.s12.calendar.member.model.MemberVO;

@WebServlet("/updateSchedule.do")
public class UpdateScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UpdateScheduleController() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		CalendarService cservice = new CalendarService();
		CalendarVO cvo = new CalendarVO(); 
		
		int snum = Integer.parseInt(request.getParameter("snum")) ;
		int mid = mvo.getMid();
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String title = request.getParameter("title");
		
		cvo.setSnum(snum);
		cvo.setMid(mid);
		cvo.setTitle(title);
		cvo.setStart(start);
		cvo.setEnd(end);
		
		int result = cservice.updateSchedule(cvo);
		
		if(result > 0) {
			System.out.println("스케줄 update 성공");
			out.append("ok");
		}else {
			System.out.println("스케줄 update 실패");
			out.append("fail");
		}
	}

}
