package com.example.firstserv.Dao.Impl;

import com.example.firstserv.Dao.DotationDao;

import com.example.firstserv.Models.Dotation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DotationDaoImpl extends HttpServlet implements DotationDao {
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
    public List<Dotation> AllDotations() throws SQLException {
        List<Dotation> AllDotations = new ArrayList<Dotation>();
        ResultSet resultSet = null;

        try {
            requete  = "SELECTE * FROM Dotation";
            resultSet = instructions.executeQuery();
            while (resultSet.next()){

                Dotation dotation = new Dotation(
                        resultSet.getString("date_expiration_dotation"),
                        resultSet.getInt("montant"),
                        resultSet.getInt("plat-fond"),
                        resultSet.getString("type_dotation"),
                        resultSet.getInt("id_carte")
                );

                dotation.setId_dotation(resultSet.getInt("id_dotation"));

                AllDotations.add(dotation);
            }
            return AllDotations;
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            connexion.close();
        }
        return AllDotations;
    }

    @Override
    public boolean addDotation(Dotation dotation) throws SQLException {
        try {
            requete  = "INSERT INTO Dotation(date_expiration_dotation,montant,plat-fond,type_dotation,id_carte) VALUES(?,?,?,?,?)";
            instructions.setString(1, dotation.getDate_expiration_dotation());
            instructions.setFloat(2, dotation.getMontant());
            instructions.setFloat(3,dotation.getPlat_fond());
            instructions.setString(4,dotation.getType_dotation());
            instructions.setInt(5, dotation.getId_carte());
            instructions.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            connexion.close();
        }
    }

    @Override
    public boolean UpdateDotation(Dotation dotation) throws SQLException {
        try {
            requete  = "UPDATE Dotation SET date_expiration_dotation = ? AND montant  = ? AND plat-fond  = ? AND type_dotation  = ? AND id_carte  = ? WHERE id_dotation = ?";
            instructions.setString(1, dotation.getDate_expiration_dotation());
            instructions.setFloat(2, dotation.getMontant());
            instructions.setFloat(3,dotation.getPlat_fond());
            instructions.setString(4,dotation.getType_dotation());
            instructions.setInt(5, dotation.getId_carte());
            instructions.setInt(6,dotation.getId_dotation());
            instructions.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            connexion.close();
        }
    }

    @Override
    public boolean DeleteDotation(Dotation dotation) throws SQLException {
        try {
            requete  = "DELETE Dotation WHERE id_dotation=?";
            instructions.setInt(1, dotation.getId_dotation());
            instructions.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            connexion.close();
        }
    }
}
