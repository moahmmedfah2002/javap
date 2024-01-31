package com.example.firstserv;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private  Connection con;
    private String pilot;
    private  String base;

    private   int id;

    private PreparedStatement ins;

    public void init() throws ServletException{
            this.pilot=getInitParameter("jdbc.Driver");
            base=getInitParameter("localisation");

            try{
                Class.forName(this.pilot);
                this.con=DriverManager.getConnection(this.base,"avnadmin","AVNS_hdj58afUSowWP3eu2GF");

                    String req="select * from Carte";
                    ins = con.prepareStatement(req);
                    ResultSet res= ins.executeQuery();

                    while(res.next()) {
                        id = res.getInt("Num_carte");

                    }
            }catch (ClassNotFoundException e){
                log("Driver not found");throw new ServletException();

            }catch (SQLException e){
                log("Base de donne non trouver");throw new ServletException();
            }
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {

//        response.setContentType("text/html");
        request.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
//        PrintWriter out =response.getWriter();
//        out.println("<!DOCTYPE html>");
//        out.println("<html>");
//        out.println("<head>");
//        out.println("<title>");
//
//        out.println("hi");
//        out.println("</title>");
//        out.println("</head>");
//        out.println("<body>");
//        out.println(id);
//
//
//
//        out.println("<form method='get' action='/firstServ/affiche'>" +
//                "<label>name</label><input type='text' name='name'>" +
//                "<input type='submit' value='send'>"+
//                "</form>");
//
//        out.println("</body>");
//        out.println("</html>");
//        out.println();


    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    public void destroy() {
        if(this.con!=null){
            try {
                con.close();
            }catch(SQLException e){
                log("Filde to close it");

            }
        }
    }
}