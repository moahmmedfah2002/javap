package com.example.firstserv;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Serv extends HttpServlet{
    private String email;
    private String pass;
    public void init()throws ServletException{


    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        email=getInitParameter("email");
        pass=getInitParameter("pass");
        String emailF =request.getParameter("emailF");
        String passF =request.getParameter("passF");
        request.setAttribute("email",email);
        request.setAttribute("pass",pass);
        request.getServletContext().getRequestDispatcher("/WEB-INF/dotation.jsp").forward(request, response);

    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }

}
