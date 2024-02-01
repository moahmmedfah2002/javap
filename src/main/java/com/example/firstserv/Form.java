package com.example.firstserv;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Form extends HttpServlet{
    private  Connection connexion = null;
    private PreparedStatement instructions = null;
    private String requete =  "INSERT INTO Client(nom,prenom,email,password) VALUES(?,?,?,?)";

    public Form(){
        super();
    }
    public void init() throws ServletException {

        String Pilote = getInitParameter("jdbc.Driver");
        String BaseDonnees = getInitParameter("localisation");

        try {
            Class.forName(Pilote);
            connexion = DriverManager.getConnection(BaseDonnees,"avnadmin","AVNS_hdj58afUSowWP3eu2GF");
            instructions = connexion.prepareStatement(requete);


        }catch (ClassNotFoundException e){
            log("Driver DB non trouvé");
            throw new ServletException();
        }catch (SQLException e){
            log("Base de Donnees non trouvee");
            throw new ServletException();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Test</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<form method=\"post\" action=\"bonjour\" style=\"display: grid;width: 300px;margin-left: 45%;\">");
        out.println("<h1> Formulaire d'iscripetion!</h1>");
        out.println(" <LABEL>Nom :</LABEL>");
        out.println("  <input name=\"nom\" type=\"text\" required>");
        out.println("  <LABEL>Prenom :</LABEL>");
        out.println("  <input name=\"prenom\" type=\"text\" required>");
        out.println("  <LABEL>Email :</LABEL>");
        out.println("  <input name=\"email\" type=\"email\" minlength=\"8\" required>");
        out.println("  <LABEL>Password :</LABEL>");
        out.println("  <input name=\"password\" type=\"password\" required>");
        out.println("  <input type=\"submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");


        //this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 /*
        //this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //request.setAttribute("var",message);


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Test</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Bonjour !</p>");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        out.println("<p>Nom : " + nom + "</p>");
        out.println("<p>Prenom : "+ " " + prenom + "</p>");
        out.println("<p>email : "+ " " + email + "</p>");
        out.println("<p>Password : "+ " " + password+ "</p>");
        out.println("</body>");
        out.println("</html>");
*/

    }

    public void destroy() {
        if (connexion != null) {
            try {
                connexion.close();
            }catch (SQLException e){
                log("Erreur fermeteur DB");
            }

        }
    }
}
