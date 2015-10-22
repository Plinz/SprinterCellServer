package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/Connect")
public class Connect extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		HttpSession session = req.getSession(true);
		if (session.getAttribute("pseudo") != null) {
			res.sendRedirect("/servlet/WorkPanel");
		}
		out.println("<!DOCTYPE html>");
		out.println("<html >");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println(" <title>Sign-Up/Login Form</title>");
		out.println("<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>");

		out.println("<link rel=\"stylesheet\" href=\"/css/normalize.css\">");

		out.println("<link rel=\"stylesheet\" href=\"/css/style.css\">");

		out.println("</head>");

		out.println("<body>");

		out.println("<div class=\"form\">");

		out.println("<ul class=\"tab-group\">");
		out.println("<li class=\"tab active\"><a href=\"#signup\">Sign Up</a></li>");
		out.println(" <li class=\"tab\"><a href=\"#login\">Log In</a></li>");
		out.println("</ul>");

		out.println("<div class=\"tab-content\">");
		out.println(" <div id=\"signup\">");
		out.println("<h1>Sign Up for Free</h1>");

		out.println("<form action=\"/servlet/Register\" method=\"get\">");

		
		out.println("<div class=\"field-wrap\">");
		out.println("<label>");
		out.println("E-Mail<span class=\"req\">*</span>");
		out.println("</label>");
		out.println("<input type=\"email\" name=\"email\" required autocomplete=\"off\" />");
		out.println("</div>");
		
		out.println("<div class=\"field-wrap\">");
		out.println("<label>");
		out.println("Nickname<span class=\"req\">*</span>");
		out.println("</label>");
		out.println("<input type=\"text\" name=\"pseudo\" required autocomplete=\"off\" />");
		out.println("</div>");

		out.println("<div class=\"field-wrap\">");
		out.println(" <label>");
		out.println("Set A Password<span class=\"req\">*</span>");
		out.println("</label>");
		out.println("<input type=\"password\" name=\"password\" required autocomplete=\"off\" />");
		out.println("</div>");

		out.println("<button type=\"submit\" class=\"button button-block\" />Get Started</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("<div id=\"login\">");
		out.println("<h1>Welcome Back!</h1>");

		out.println("<form action=\"/servlet/LogIn\" method=\"post\">");

		out.println("<div class=\"field-wrap\">");
		out.println("<label>");
		out.println("Nickname<span class=\"req\">*</span>");
		out.println("</label>");
		out.println("<input type=\"text\" name=\"pseudo\" required autocomplete=\"off\" />");
		out.println("</div>");

		out.println("<div class=\"field-wrap\">");
		out.println("<label>");
		out.println("Password<span class=\"req\">*</span>");
		out.println("</label>");
		out.println("<input type=\"password\" name=\"password\" required autocomplete=\"off\" />");
		out.println("</div>");

		out.println("<p class=\"forgot\"><a href=\"#\">Forgot Password?</a></p>");

		out.println("<button class=\"button button-block\" />Log In</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("</div>");
		out.println("<!-- tab-content -->");

		out.println("</div>");
		out.println("<!-- /form -->");
		out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");

		out.println("<script src=\"/js/index.js\"></script>");

		out.println("</body>");
		out.println("</html>");
	}
}