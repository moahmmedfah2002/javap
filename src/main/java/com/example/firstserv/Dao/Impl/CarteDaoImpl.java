package com.example.firstserv.Dao.Impl;

import com.example.firstserv.Dao.CarteDao;
import com.example.firstserv.Models.Carte;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarteDaoImpl extends HttpServlet implements CarteDao {
    private Connection connexion = null;
    private PreparedStatement instructions = null;
    private String requete = null;
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


    @Override
    public boolean addCarte(Carte carte) throws SQLException {
        try {
            requete  = "INSERT INTO Carte(Num_carte,type_carte,date_expiration,cve,dotation_statuts,id_client) VALUES(?,?,?,?,?,?)";
            instructions.setString(1, carte.getNumber_carte());
            instructions.setString(2, carte.getType_carte());
            instructions.setString(3, carte.getDate_expiration());
            instructions.setInt(4,carte.getCve());
            instructions.setBoolean(5, carte.isDotation_statuts());
            instructions.setInt(6,carte.getId_client());
            instructions.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            connexion.close();
        }
        return false;
    }

    @Override
    public boolean UpdateCarte(Carte carte) throws SQLException {
        try {
            requete  = "UPDATE Carte SET Num_carte = ? AND type_carte = ? AND ,date_expiration = ? AND ,cve = ? AND ,dotation_statuts = ? AND ,id_client = ? AND  WHERE id_carte = ?";
            instructions.setString(1, carte.getNumber_carte());
            instructions.setString(2, carte.getType_carte());
            instructions.setString(3, carte.getDate_expiration());
            instructions.setInt(4,carte.getCve());
            instructions.setBoolean(5, carte.isDotation_statuts());
            instructions.setInt(6,carte.getId_client());
            instructions.setInt(7, carte.getId_carte());
            instructions.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            connexion.close();
            return false;
        }
    }

}
