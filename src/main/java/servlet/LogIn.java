package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import BDD.App;
import BDD.MemberDAO;

import java.sql.*;

@WebServlet("/servlet/LogIn")
public class LogIn extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		// authentifie ?
		HttpSession session = req.getSession(true);
		String pseudo = req.getParameter("pseudo");
		if(pseudo == null){
			res.sendRedirect("/servlet/Connect");
		}

		MemberDAO m = App.getDbi().open(MemberDAO.class);

		if(m.findByPseudo(pseudo) == null){
			res.sendRedirect("/servlet/Connect");
		}
		else if(m.getMdp(pseudo).equals(req.getParameter("password"))){
				session.setAttribute("pseudo", pseudo);
				res.sendRedirect("/servlet/WorkPanel");
			
		}
		res.sendRedirect("/servlet/WorkPanel");
	}
}