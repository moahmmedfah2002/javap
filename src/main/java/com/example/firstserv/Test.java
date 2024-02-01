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

public class Test extends HttpServlet {
    public Test(){
        super();
    }
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Connection connexion = null;
    private PreparedStatement instructions = null;
    private String requete =  "INSERT INTO Client(nom,prenom,email,password) VALUES(?,?,?,?)";

    public void init() throws ServletException {
        String Pilote = getServletContext().getInitParameter("jdbc.Driver");
        String BaseDonnees = getServletContext().getInitParameter("localisation");
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

        //request.setAttribute("var",message);


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Test</title>");
        out.println("</head>");
        out.println("<body style=\"display: grid;width: 300px;text-alignt: center;color: blue;margin-left: 45%;\">");
        out.println("<h1 style=\"color: red;\">Bonjour !</h1>");
        nom = request.getParameter("nom");
        prenom = request.getParameter("prenom");
        email = request.getParameter("email");
        password = request.getParameter("password");

        out.println("<p>Nom : " + nom + "</p>");
        out.println("<p>Prenom : "+ " " + prenom + "</p>");
        out.println("<p>email : "+ " " + email + "</p>");
        out.println("<p>Password : "+ " " + password+ "</p>");
        out.println("</body>");
        out.println("</html>");



    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

        try {
            instructions.setString(1,nom);
            instructions.setString(2,prenom);
            instructions.setString(3,email);
            instructions.setString(4,password);
            instructions.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        //this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);


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
