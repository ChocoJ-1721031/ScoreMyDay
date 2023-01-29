package kh.s12.calendar.servey.controller;

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

import kh.s12.calendar.member.model.MemberVO;
import kh.s12.calendar.servey.model.ServeyContentVO;
import kh.s12.calendar.servey.model.ServeyService;

@WebServlet("/callAllServey.do")
public class CallAllServeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CallAllServeyController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		ServeyService sservice = new ServeyService();
		PrintWriter out = response.getWriter();
		MemberVO mvo = (MemberVO) session.getAttribute("member");

		if (mvo != null) {
			int mid = mvo.getMid();

			ArrayList<ServeyContentVO> scvoList = new ArrayList<ServeyContentVO>();
			scvoList = sservice.callAllServey(mid);

			System.out.println("/callAllServey.do 컨트롤러");

			if (scvoList != null) {
				System.out.println("/callAllServey.do 설문 호출 성공!");
				map.put("list", scvoList);
			} else {
				System.out.println("/callAllServey.do 설문 호출 실패!");
				map.put("msg", "fail");
			}
			out.println(gson.toJson(map));
		}
		out.flush();
		out.close();
	}

}
