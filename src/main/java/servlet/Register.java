package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.core.Application;

import rest.Member;

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
		String pseudo = req.getParameter("pseudo");
		HttpSession session = req.getSession(true);
		if (session.getAttribute("pseudo") != null) {
			res.sendRedirect("/servlet/Menu");
		}
		daoMember.insert(pseudo);
		Member m = new Member(pseudo);
		m.setMdp(req.getParameter("password"));
		m.setEmail(req.getParameter("email"));
		daoMember.update(m);
		
	}
}
