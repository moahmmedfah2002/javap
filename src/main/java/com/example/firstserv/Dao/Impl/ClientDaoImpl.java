package com.example.firstserv.Dao.Impl;

import com.example.firstserv.Dao.ClientDao;
import com.example.firstserv.Models.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends HttpServlet implements ClientDao {
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
    public List<Client> AllClient() throws SQLException {
        List<Client> AllClient = new ArrayList<Client>(){};
        ResultSet resultSet = null;

        try {
            requete  = "SELECTE * FROM Client";
            resultSet = instructions.executeQuery();
            while (resultSet.next()){

                Client client = new Client(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

                client.setId(resultSet.getInt("id"));

                AllClient.add(client);
            }
            return AllClient;
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            connexion.close();
        }
        return AllClient;
    }

    @Override
    public boolean addClient(Client client) throws SQLException {
        try {
            requete  = "INSERT INTO Client(nom,prenom,email,password) VALUES(?,?,?,?)";
            instructions.setString(1,client.getNom());
            instructions.setString(2, client.getPrenom());
            instructions.setString(3,client.getEmail());
            instructions.setString(4, client.getPassword());
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
    public boolean updateClient(Client client) throws SQLException {
        try {
            requete  = "UPDATE Client SET nom = ? AND prenom = ? AND email = ? AND password = ? WHERE id_client=?";
            instructions.setString(1,client.getNom());
            instructions.setString(2, client.getPrenom());
            instructions.setString(3,client.getEmail());
            instructions.setString(4, client.getPassword());
            instructions.setInt(5, client.getId());
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
    public boolean DeleteClient(Client client) throws SQLException {
        try {
            requete  = "DELETE Client WHERE id_client=?";
            instructions.setInt(1, client.getId());
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
