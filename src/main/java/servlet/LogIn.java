package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BDD.App;
import BDD.MemberDAO;

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

		if(m.getMdp(pseudo).equals(req.getParameter("password"))){
				session.setAttribute("pseudo", pseudo);
				res.sendRedirect("/servlet/WorkPanel");	
		}
		res.sendRedirect("/servlet/Connect");
	}
}