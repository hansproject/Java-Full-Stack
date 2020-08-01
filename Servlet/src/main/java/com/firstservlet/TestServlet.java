package com.firstservlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registration")
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        String username = (String) req.getParameter("username");
        PrintWriter pw = res.getWriter();

        pw.println("From TestServlet username: "+ username);
        System.out.println("in service");
    }

}
