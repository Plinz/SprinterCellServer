package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.core.Application;

import BDD.App;
import BDD.MemberDAO;

import java.sql.*;

@WebServlet("/servlet/Register")
public class Register extends HttpServlet {
	private MemberDAO daoMember;
	protected Application configure(){
		daoMember = App.getDbi().open(MemberDAO.class);
		daoMember.createMemberTable();
		return new App();
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html >");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("</head>");
		out.println("<body>");

		String pseudo = req.getParameter("pseudo");
		out.println(pseudo+ " TON PSEUDO KONAR ");
		out.println(" TdsdNAR ");
		out.println("</body>");
		out.println("</html>");
		HttpSession session = req.getSession(true);
		if (session.getAttribute("pseudo") != null) {
			res.sendRedirect("Menu");
		}
	}
}
