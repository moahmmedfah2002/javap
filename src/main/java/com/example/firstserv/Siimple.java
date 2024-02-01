package com.example.firstserv;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.sql.*;
import java.io.*;


public class Siimple extends HttpServlet{
    private HttpSession session;
    private PreparedStatement ins;
    private PreparedStatement ins1;
    private Connection con;
    private String pilot;
    private String email;
    public String pass;
    private  String base;
    public void init()throws  ServletException{
        this.pilot=getInitParameter("jdbc.Driver");
        base=getInitParameter("localisation");

        try{
            Class.forName(this.pilot);
            this.con= DriverManager.getConnection(this.base,"avnadmin","AVNS_hdj58afUSowWP3eu2GF");

            String req="select * from Client";
            String req1="UPDATE Client SET nom = ? , prenom = ? , email = ? , password = ? WHERE id=?";
            ins = con.prepareStatement(req);
            ins1 = con.prepareStatement(req1);


        }catch (ClassNotFoundException e){
            log("Driver not found");throw new ServletException();

        }catch (SQLException e){
            log("Base de donne non trouver");throw new ServletException();
        }
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        session=request.getSession();
        int id = (Integer) session.getAttribute("id");
        try {
            ins1.setString(1,request.getParameter("nom"));
            ins1.setString(2, request.getParameter("prenom"));
            ins1.setString(3,request.getParameter("email"));
            ins1.setString(4, request.getParameter("password"));
            ins1.setInt(5, id);
            ins1.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        this.doGet(request,response);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\" crossorigin=\"anonymous\"></script>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js\" integrity=\"sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r\" crossorigin=\"anonymous\"></script>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js\" integrity=\"sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+\" crossorigin=\"anonymous\"></script>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>");
        out.println("<table class=\"table\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Email</th>\n" +
                "      <th scope=\"col\">Password</th>\n" +
                "      <th scope=\"col\">Modif</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody class=\"table-group-divider\">\n" );
        try {


            ResultSet res = ins.executeQuery();
            session=request.getSession();
            int nb=0;
            while (res.next()) {
                nb+=1;
                email = res.getString("email");
                pass = res.getString("password");

                session.setAttribute("id",res.getInt("id"));
            }
        }catch (SQLException e){

            }

            out.println("    <tr>\n" +

                    " <td>"+email+"</td>\n" +
                    "      <td>"+pass+"</td>\n" +
                    "      <td>" +
                    "<form method='get' action='modif'>" +
                    "<button type=\"submit\" class=\"btn btn-primary\">" +
                    "Edite" +
                    "</button>\n" +
                    "</form> " +
                    "<form method='get' action='supp'>" +
                    "<input type='hidden' name='email' value="+email+">" +
                    "<button type=\"submit\" class=\"btn btn-danger\">" +
                    "supprimer" +
                    "</button>" +
                    "</form>\n" +
                    "</td>\n" +
                    "    </tr>\n" +
                    "</form>");

        out.println(        "  </tbody>\n" +
                "</table>" +
                "</body>" +
                "</html>");
    }
}
