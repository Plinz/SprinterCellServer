package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.eclipse.persistence.sessions.Project;

import BDD.App;
import BDD.MemberDAO;
import BDD.ProjectDAO;

import java.sql.*;

@WebServlet("/servlet/CreateProject")
public class CreateProject extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4423368632340059072L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		// authentifie ?
		HttpSession session = req.getSession(true);
		String pseudo = req.getParameter("pseudo");
		String nameProject = req.getParameter("nameProject");
		if(pseudo == null){
			res.sendRedirect("/servlet/Connect");
		}

		MemberDAO m = App.getDbi().open(MemberDAO.class);

		ProjectDAO pdao = App.getDbi().open(ProjectDAO.class);

		try{
			if(m.findByPseudo(pseudo) == null){
				res.sendRedirect("/servlet/Connect");
			}
			pdao.createProjectTable();
			
		}
		catch(Exception e){
			out.println(e.getMessage());
		}
		rest.Project p = new rest.Project(nameProject,null);
		pdao.insert(p);
		res.sendRedirect("/servlet/WorkPanel");

	}
}