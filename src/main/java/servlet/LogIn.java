package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import BDD.App;
import BDD.MemberDAO;

import java.sql.*;

@WebServlet("/servlet/Login")
public class LogIn extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		// authentifie ?
		HttpSession session = req.getSession(true);
		if(session.getAttribute("pseudo") != null){
			res.sendRedirect("Menu");
		}
		
		MemberDAO m = App.getDbi().open(MemberDAO.class);
		
		if(m.findByPseudo(pseudo) == null){
			
		}
	}
}