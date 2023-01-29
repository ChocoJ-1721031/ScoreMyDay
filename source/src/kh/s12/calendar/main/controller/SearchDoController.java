package kh.s12.calendar.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import kh.s12.calendar.main.model.CalendarService;
import kh.s12.calendar.main.model.CalendarVO;
import kh.s12.calendar.member.model.MemberVO;

@WebServlet("/searchSchedule.do")
public class SearchDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchDoController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		Gson gson = new Gson();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		CalendarService cservice = new CalendarService();
		PrintWriter out = response.getWriter();
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		
		
		
		if(mvo != null) {
			int mid = mvo.getMid();
			String searchInput = request.getParameter("searchInput");
			
			ArrayList<CalendarVO> searchList = new ArrayList<CalendarVO>();
			searchList = cservice.searchSchedule(mid, searchInput);
			
			System.out.println("/searchsShedule.do 컨트롤러");
			
			if(searchList != null) {
				System.out.println("/searchsShedule.do 스케줄 검색 성공!");
				searchMap.put("searchList", searchList);
			} else {
				System.out.println("/search 스케줄 호출 실패!");
				searchMap.put("msg", "fail");
			}
			out.println(gson.toJson(searchMap));
		}
		out.flush();
		out.close();
	}

}
