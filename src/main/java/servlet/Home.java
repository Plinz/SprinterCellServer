package servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/Home")
public class Home extends HttpServlet 
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	PrintWriter out = res.getWriter();
    	res.setContentType("text/html");
    	
    	HttpSession session = req.getSession(true);
    	if(((ServletRequest) session).getParameter("pseudo") == null){
    		res.sendRedirect("accueil.html");
    	}
    	out.println("<html>");
    	out.println("<head>");
    	out.println("<meta charset=\"UTF-8\">");
    	out.println("<title>Sign-Up/Login Form</title>");
    	out.println("<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>");

    	out.println("<link rel=\"stylesheet\" href=\"css/normalize.css\">");


    	out.println("<link rel=\"stylesheet\" href=\"css/style.css\">");




    	out.println("</head>");

    	out.println("<body>");

    	out.println("<div class=\"form\">");

    	out.println("<ul class=\"tab-group\">");
    	out.println("<li class=\"tab active\"><a href=\"#signup\">Sign Up</a></li>");
    	out.println("<li class=\"tab\"><a href=\"#login\">Log In</a></li>");
    	out.println("</ul>");

    	out.println("<div class=\"tab-content\">");
    	out.println("<div id=\"signup\">");
    	out.println("<h1>Sign Up for Free</h1>");

    	out.println("<form action=\"/\" method=\"post\">");

    	out.println("<div class=\"top-row\">");
    	out.println("<div class=\"field-wrap\">");
    	out.println("<label>First Name<span class=\"req\">*</span></label>");
    	out.println("<input type=\"text\" required autocomplete=\"off\" />");
    	out.println("</div>");

    	out.println("<div class=\"field-wrap\">");
    	out.println("<label>Last Name<span class=\"req\">*</span></label>");
    	out.println("<input type=\"text\" required autocomplete=\"off\" />");
    	out.println("</div>");
    	out.println("</div>");

    	out.println("<div class=\"field-wrap\">");
    	out.println("<label>Nickname<span class=\"req\">*</span></label>");
    	out.println("<input type=\"text\" required autocomplete=\"off\" />");
    	out.println("</div>");

    	out.println("<div class=\"field-wrap\">");
    	out.println("<label>Set A Password<span class=\"req\">*</span></label>");
    	out.println("<input type=\"password\" required autocomplete=\"off\" />");
    	out.println("</div>");

    	out.println("<button type=\"submit\" class=\"button button-block\" />Get Started</button>");

    	out.println("</form>");

    	out.println("</div>");

    	out.println("<div id=\"login\">");
    	out.println("<h1>Welcome Back!</h1>");

    	out.println("<form action=\"/\" method=\"post\">");

    	out.println("<div class=\"field-wrap\">");
    	out.println("<label>Nickname<span class=\"req\">*</span></label>");
    	out.println("<input type=\"text\" required autocomplete=\"off\" />");
    	out.println("</div>");

    	out.println("<div class=\"field-wrap\">");
    	out.println("<label>Password<span class=\"req\">*</span></label>");
    	out.println("<input type=\"password\" required autocomplete=\"off\" />");
    	out.println("</div>");

    	out.println("<p class=\"forgot\"><a href=\"#\">Forgot Password?</a></p>");

    	out.println("<button class=\"button button-block\" />Log In</button>");

    	out.println("</form>");

    	out.println("</div>");

    	out.println("</div>");

    	out.println("</div>");
    	out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");

    	out.println("<script src=\"js/index.js\"></script>");
    	
    	
    	
    	out.println("<html>");
    	out.println("<head>");
    }
}