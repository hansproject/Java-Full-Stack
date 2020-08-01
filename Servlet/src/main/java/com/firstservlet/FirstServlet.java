package com.firstservlet;

//import javax.Servlet.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginpage")
public class FirstServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");

        ServletContext context = getServletContext();
        String str = context.getInitParameter("username");
        res.sendRedirect("registration?username= "+ str);
    }
}
