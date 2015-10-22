package servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import BDD.App;
import BDD.MemberDAO;


@WebServlet("/servlet/Register")
public class Register extends HttpServlet {
	private MemberDAO daoMember;
	
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		daoMember = App.getDbi().open(MemberDAO.class);
		try {
			daoMember.createMemberTable();
		} catch (Exception e) {}
		String pseudo = req.getParameter("pseudo");
		System.out.println(pseudo);
		HttpSession session = req.getSession(true);
		if (session.getAttribute("pseudo") != null) {
			res.sendRedirect("/servlet/Menu");
		}
		daoMember.insert(pseudo);
		daoMember.update(pseudo, req.getParameter("password"), req.getParameter("email"));
		
		
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html >");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("</head>");
		out.println("<body>");
		out.println(daoMember.all());
		out.println("</body>");
		out.println("</html>");
	}
}
