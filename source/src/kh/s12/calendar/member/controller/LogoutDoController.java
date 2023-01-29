package kh.s12.calendar.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/logout.do")
public class LogoutDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutDoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/logout.do 로그아웃");
		String viewPage = "/";
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		response.sendRedirect(viewPage);
	}
}
