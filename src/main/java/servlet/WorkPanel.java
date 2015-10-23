package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rest.Member;
import rest.Project;
import rest.Task;
import BDD.App;
import BDD.MemberDAO;
import BDD.ProjectToMemberDao;
import BDD.ProjectToTaskDao;
import BDD.TaskDao;


@WebServlet("servlet/WorkPanel")
public class WorkPanel extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");


		TaskDao dt = App.getDbi().open(TaskDao.class);
		MemberDAO md = App.getDbi().open(MemberDAO.class);
		ProjectToTaskDao pttd = App.getDbi().open(ProjectToTaskDao.class);
		ProjectToMemberDao ptmd = App.getDbi().open(ProjectToMemberDao.class);
		HttpSession session = req.getSession(true);
		
		try {
			md.createMemberTable();
		} catch (Exception e) {}
		try {
			dt.createTaskTable();
		} catch (Exception e) {}
		try {
			pttd.createProjectTask();
		} catch (Exception e) {}
		try {
			ptmd.createProjectMember();
		} catch (Exception e) {}

		try{

			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"utf-8\"/>");
			out.println("<title>ACCUEIL</title>");
			out.println("<link href=\"/css/bootstrap.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\"https://code.jquery.com/jquery-1.11.2.min.js\"></script>");
			out.println("<script src=\"/js/jquery.js\"></script>");
			out.println("<script src=\"/js/bootstrap.min.js\"></script>");
			out.println("");
			out.println("</head>");

			out.println("<body>");

			out.println("<nav class=\"navbar navbar-inverse\">");
			out.println("<div class=\"container-fluid\">");
			out.println("<!-- Brand and toggle get grouped for better mobile display -->");
			out.println("<div class=\"navbar-header\">");
			out.println("<ul class=\"nav navbar-nav\">");
			out.println("<li class=\"dropdown\">");
			out.println("<a href=\"#\" class=\"dropdown-toggle navbar-brand\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">My projects<span class=\"caret\"></span></a>");
			out.println("<ul class=\"dropdown-menu\">");
			try{
				List<Project> listProject = md.getProjects((String) session.getAttribute("pseudo"));

				for (int j = 0; j < listProject.size(); j++) {

					listProject.get(j).getName() ;
				}
			}
			catch(Exception e){
				e.printStackTrace();
				res.sendRedirect("/servlet/FormProject");
			}
			out.println("<li><a href=\"#\">Project 1</a></li>");
			out.println("<li><a href=\"#\">Project 2</a></li>");
			out.println("<li><a href=\"#\">Project 3</a></li>");
			out.println("</ul>");
			out.println("</li>");
			out.println("<li><a href=\"/servlet/FormProject\">New Project</a></li>");
			out.println("<li><a href=\"/servlet/FormTask\">Add Task</a></li>");
			out.println("<li><a href=\"#\">Add contributor</a></li>");
			out.println("</ul>");
			out.println("</div>");

			out.println("<!-- Collect the nav links, forms, and other content for toggling -->");
			out.println("<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
			out.println("<ul class=\"nav navbar-nav navbar-right\">");
			out.println("<form class=\"navbar-form navbar-left\" role=\"search\">");
			out.println("<div class=\"form-group\">");
			out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Search\">");
			out.println("</div>");
			out.println("<button type=\"submit\" class=\"btn btn-default\">Submit</button>");
			out.println("</form>");
			out.println("</ul>");
			out.println("</div><!-- /.navbar-collapse -->");

			out.println("</div><!-- /.container-fluid -->");
			out.println("</nav>");
			out.println("</div>");

			out.println("<!-- Barre de Progression -->");
			out.println("<div class=\"progress\">");
			out.println("<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 60%;\">");
			out.println("60%");
			out.println("</div>");
			out.println("</div>");

			out.println("<!-- Corps de la page avec 3 div To Do , Doing, Done -->");
			out.println("<div class=\"row\">");

			//TO DO
			out.println("<!-- TO DO -->");
			out.println("<div class=\"col-sm-6 col-md-4\">");
			out.println("<h2 style=\"text-align:center\"><span class=\" label label-primary\">TO DO</h2>");
			out.println("<div class=\"thumbnail\">");

			// TACHE
			List<rest.Project> projects = md.getProjects((String)(session.getAttribute("pseudo")));
			List<Task> tasks = projects.get(0).getTasks();
			for(int i=0;i<tasks.size();i++){
				if(tasks.get(i).getState().equals("TO DO")){
					out.println("<!--UNE TACHE -->");
					out.println("<div class=\"panel panel-info\">");
					out.println("<div class=\"panel-heading\">");
					out.println("<div class=\"row\">");
					out.println("<div class=\"col-lg-9\"><h4>"+tasks.get(i).getTitle()+"</h4></div>");
					out.println("<div class=\"col-lg-1\"><h4>"+tasks.get(i).getValue()+"</h4></div>");
					out.println("<div class=\"col-lg-2\">");	

					out.println("<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\">");
					out.println("<a href=\"/servlet/ModifState\"><span class=\"glyphicon glyphicon-triangle-right\" aria-hidden=\"true\"></span>");
					out.println("</button>");

					out.println("</div>");
					out.println("</div>");
					out.println("<div class=\"panel\">");
					out.println("<div>"+tasks.get(i).getDescription()+"</div>");
					out.println("</div>");
					out.println("<div>");
					out.println("<div class=\"row\">");
					out.println("<div class=\"col-lg-3\">contributor:</div>");
					out.println("<div class=\"col-lg-9\">");
					/*List<Member> tasksMember = tasks.getMembers();
					for (int j = 0; j < tasksMember.length; j++) {
						tasksMember.get(i).getPseudo() ;
					}*/
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
				}
				if(tasks.get(i).getState().equals("DOING")){


				}
				if(tasks.get(i).getState().equals("DONE")){


				}
			}


			//DOING
			out.println("<!-- DOING -->");
			out.println("<div class=\"col-sm-6 col-md-4\">");
			out.println("<h2 style=\"text-align:center\"><span class=\" label label-warning\">DOING</h2>");
			out.println("<div class=\"thumbnail\">");
			// UNE TACHE
			out.println("<!--UNE TACHE -->");
			out.println("<div class=\"panel panel-info\">");
			out.println("<div class=\"panel-heading\">");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-lg-2\">");
			out.println("<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\">");
			out.println("<span class=\"glyphicon glyphicon-triangle-left\" aria-hidden=\"true\"></span>");
			out.println("</button>");
			out.println("</div>");
			out.println("<div class=\"col-lg-7\"><h4>Nom</h4></div>");
			out.println("<div class=\"col-lg-1\"><h4>13</h4></div>");
			out.println("<div class=\"col-lg-2\">");	
			out.println("<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\">");
			out.println("<span class=\"glyphicon glyphicon-triangle-right\" aria-hidden=\"true\"></span>");
			out.println("</button>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class=\"panel\">");
			out.println("<div>sfbkjgh rgkergjepr erng;rgdjgkfjhdoifofpof posrjgpodfgpdofigpfdfgfpg pfgjdpfjgrijgdjmrfhod</div>");
			out.println("</div>");
			out.println("<div>");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-lg-3\">contributor:</div>");
			out.println("<div class=\"col-lg-9\">regdfoi, godfigodfi, ugoidfugo, idfugo</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			//Fin D'UNE TACHE
			// UNE TACHE
			out.println("<!--UNE TACHE -->");
			out.println("<div class=\"panel panel-warning\">");
			out.println("<div class=\"panel-heading\">");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-lg-2\">");
			out.println("<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\">");
			out.println("<span class=\"glyphicon glyphicon-triangle-left\" aria-hidden=\"true\"></span>");
			out.println("</button>");
			out.println("</div>");
			out.println("<div class=\"col-lg-7\"><h4>Nom</h4></div>");
			out.println("<div class=\"col-lg-1\"><h4>13</h4></div>");
			out.println("<div class=\"col-lg-2\">");	
			out.println("<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\">");
			out.println("<span class=\"glyphicon glyphicon-triangle-right\" aria-hidden=\"true\"></span>");
			out.println("</button>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class=\"panel\">");
			out.println("<div>sfbkjgh rgkergjepr erng;rgdjgkfjhdoifofpof posrjgpodfgpdofigpfdfgfpg pfgjdpfjgrijgdjmrfhod</div>");
			out.println("</div>");
			out.println("<div>");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-lg-3\">contributor:</div>");
			out.println("<div class=\"col-lg-9\">regdfoi, godfigodfi, ugoidfugo, idfugo</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			//Fin D'UNE TACHE
			out.println("</div>");
			out.println("</div> ");


			// DONE
			out.println("<!-- DONE -->");
			out.println("<div class=\"col-sm-6 col-md-4\">");
			out.println("<h2 style=\"text-align:center\"><span class=\" label label-success\">DONE</h2>");
			out.println("<div class=\"thumbnail\">");
			// UNE TACHE
			out.println("<!--UNE TACHE -->");
			out.println("<div class=\"panel panel-success\">");
			out.println("<div class=\"panel-heading\">");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-lg-2\">");
			out.println("<button type=\"button\" class=\"btn btn-default\" aria-label=\"Left Align\">");
			out.println("<span class=\"glyphicon glyphicon-triangle-left\" aria-hidden=\"true\"></span>");
			out.println("</button>");
			out.println("</div>");
			out.println("<div class=\"col-lg-7\"><h4>Nom</h4></div>");
			out.println("<div class=\"col-lg-3\"><h4>13</h4></div>");
			out.println("</div>");
			out.println("<div class=\"panel\">");
			out.println("<div>sfbkjgh rgkergjepr erng;rgdjgkfjhdoifofpof posrjgpodfgpdofigpfdfgfpg pfgjdpfjgrijgdjmrfhod</div>");
			out.println("</div>");
			out.println("<div>");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col-lg-3\">contributor:</div>");
			out.println("<div class=\"col-lg-9\">regdfoi, godfigodfi, ugoidfugo, idfugo</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			//Fin D'UNE TACHE
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");

			out.println("</body>");

			out.println("</html>");




		}catch(Exception e){
			out.println(e.getMessage());
		}
	}


}
