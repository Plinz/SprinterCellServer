package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.sessions.Project;

import BDD.App;
import BDD.MemberDAO;
import BDD.ProjectDAO;

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
		String pseudo = (String)session.getAttribute("pseudo");
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
			
		}
		finally{
			rest.Project p = new rest.Project(nameProject,null);
			p.setId(pdao.insert(p.getName(), p.getDescription()));
			p.addMember(m.findByPseudo(pseudo));
			List<rest.Project> pro = m.getProjects(pseudo);
			for (int i = 0; i < pro.size(); i++) {
				out.println("pro : "+pro.get(i).getName());				
			}
			res.sendRedirect("/servlet/WorkPanel");
		}
	}
}