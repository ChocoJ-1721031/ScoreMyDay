package kh.s12.calendar.servey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/servey")
public class ServeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServeyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = "/WEB-INF/servey.jsp";
		request.getRequestDispatcher(viewPage).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("application/json;charset=UTF-8");
		
		String date = request.getParameter("selecDate");
		System.out.println("/servey 컨트롤러 "+date);
		HttpSession session = request.getSession();
		session.setAttribute("date", date);
	}
}
