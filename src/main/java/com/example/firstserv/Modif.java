package com.example.firstserv;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Modif extends HttpServlet {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private HttpSession session;
    private PreparedStatement ins;
    private ResultSet res;
    private Connection con;
    private String pilot;

    private String req;
    private String base;
    public void init() throws ServletException {
        this.pilot=getInitParameter("jdbc.Driver");
        base=getInitParameter("localisation");

        try{
            Class.forName(this.pilot);
            this.con= DriverManager.getConnection(this.base,"avnadmin","AVNS_hdj58afUSowWP3eu2GF");

            req="select * from Client WHERE id = ?";

            ins = con.prepareStatement(req);



        }catch (ClassNotFoundException e){
            log("Driver not found");throw new ServletException();

        }catch (SQLException e){
            log("Base de donne non trouver");throw new ServletException();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        session=request.getSession();
        int id = (Integer) session.getAttribute("id");
        try {
            ins.setInt(1,id);
            res = ins.executeQuery();
            while (res.next()) {
                nom = res.getString("nom");
                prenom = res.getString("prenom");
                email = res.getString("email");
                password = res.getString("password");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Test</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<form method=\"post\" action=\"simple\" style=\"display: grid;width: 300px;margin-left: 45%;\">");
        out.println("<h1> Formulaire d'iscripetion!</h1>");
        out.println(" <LABEL>Nom :</LABEL>");
        out.println("  <input name=\"nom\" type=\"text\" value="+nom+" required>");
        out.println("  <LABEL>Prenom :</LABEL>");
        out.println("  <input name=\"prenom\" type=\"text\" value="+prenom+" required>");
        out.println("  <LABEL>Email :</LABEL>");
        out.println("  <input name=\"email\" type=\"email\" minlength=\"8\" value="+email+" required>");
        out.println("  <LABEL>Password :</LABEL>");
        out.println("  <input name=\"password\" type=\"password\"  value="+password+" required>");
        out.println("  <input type=\"submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");




    }
}
