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
import BDD.ProjectToMemberDao;
import BDD.ProjectToTaskDao;
import BDD.TaskDao;

@WebServlet("servlet/ModifState" +
		"")
public class ModifState extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		TaskDao dt = App.getDbi().open(TaskDao.class);
		MemberDAO md = App.getDbi().open(MemberDAO.class);

		HttpSession session = req.getSession(true);
		try {
			md.createMemberTable();
		} catch (Exception e) {}
		try {
			dt.createTaskTable();
		} catch (Exception e) {}

		try{

			
			

		}
		catch(Exception e){
			out.println(e.getMessage());
		}
	}
}