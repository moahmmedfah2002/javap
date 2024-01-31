package com.example.firstserv;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Serv extends HttpServlet{
    public HelloServlet hi=new HelloServlet() ;
    public void init()throws ServletException{

    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        String name=request.getParameter("name");
        response.setContentType("text/html");
        PrintWriter out =response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println("hi");
        out.println("</title>");
        out.println("<h1>hi </h1>"+name);
        out.println("</body>");
        out.println("</html>");

    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doPost(request,response);
    }

}
