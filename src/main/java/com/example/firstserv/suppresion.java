package com.example.firstserv;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

public class suppresion extends HttpServlet {
        private HttpSession session;
        private PreparedStatement ins;
        private Connection con;
        private String pilot;
        private String base;
        public void init() throws ServletException {
                this.pilot=getInitParameter("jdbc.Driver");
                base=getInitParameter("localisation");

                try{
                        Class.forName(this.pilot);
                        this.con= DriverManager.getConnection(this.base,"avnadmin","AVNS_hdj58afUSowWP3eu2GF");

                        String req="DELETE  from Client WHERE id = ?";
                        ins = con.prepareStatement(req);



                }catch (ClassNotFoundException e){
                        log("Driver not found");throw new ServletException();

                }catch (SQLException e){
                        log("Base de donne non trouver");throw new ServletException();
                }
        }
        public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{



                        PrintWriter out =response.getWriter();
                        response.setContentType("text/html");

                        try {

                            session=request.getSession();
                            int id = (Integer) session.getAttribute("id");
                            ins.setInt(1, id);

                            ins.execute();

                        }catch (SQLException e)
                        {
                               // throw new ServletException();
                        }

                        new Siimple().doGet(request, response);


        }
//        public void doDelete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
//                doGet(request,response);
//        }
}
