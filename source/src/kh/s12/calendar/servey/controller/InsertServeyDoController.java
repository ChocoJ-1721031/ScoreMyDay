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
import kh.s12.calendar.servey.model.ServeyContentVO;
import kh.s12.calendar.servey.model.ServeyService;
import kh.s12.calendar.servey.model.ServeyVO;

@WebServlet("/insertServey.do")
public class InsertServeyDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InsertServeyDoController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServeyService sservice = new ServeyService();
		ServeyVO svo = new ServeyVO();
		ServeyContentVO scvo = new ServeyContentVO();
		
		svo.setMid(Integer.parseInt(request.getParameter("mid")));
		svo.setdDate(request.getParameter("date"));
		
		scvo.setdDate(request.getParameter("date"));
		scvo.setMid(Integer.parseInt(request.getParameter("mid")));
		scvo.setSer_1(Integer.parseInt(request.getParameter("q_1")));
		scvo.setSer_2(Integer.parseInt(request.getParameter("q_2")));
		scvo.setSer_3(Integer.parseInt(request.getParameter("q_3")));
		scvo.setSer_4(Integer.parseInt(request.getParameter("q_4")));
		scvo.setSer_5(Integer.parseInt(request.getParameter("q_5")));
		scvo.setScore_1(Integer.parseInt(request.getParameter("score")));
		scvo.setSer_diary(request.getParameter("diary"));

		System.out.println(svo);
		System.out.println(scvo);
		int result = sservice.insertServey(scvo, svo);
		
		if(result > 0) {
			System.out.println("설문 insert 성공");
			out.append("ok");
		}else {
			System.out.println("설문 insert 실패");
			out.append("fail");
		}
		
	}

}
