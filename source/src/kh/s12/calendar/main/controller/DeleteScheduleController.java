package kh.s12.calendar.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s12.calendar.main.model.CalendarService;
import kh.s12.calendar.main.model.CalendarVO;

@WebServlet("/delSchedule.do")
public class DeleteScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteScheduleController() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		CalendarService cservice = new CalendarService();
		CalendarVO cvo = new CalendarVO(); 
		
		int snum = Integer.parseInt(request.getParameter("snum")) ;
		int mid = Integer.parseInt(request.getParameter("mid"));
		
		cvo.setSnum(snum);
		cvo.setMid(mid);
		
		int result = cservice.deleteSchedule(cvo);
		
		if(result > 0) {
			System.out.println("스케줄 delete 성공");
			out.append("ok");
		}else {
			System.out.println("스케줄 delete 실패");
			out.append("fail");
		}
	}

}
