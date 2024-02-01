package com.example.firstserv.Dao;

import com.example.firstserv.Models.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {

    List<Client> AllClient() throws SQLException;
    boolean addClient(Client client) throws SQLException;
    boolean updateClient(Client client) throws SQLException;
    boolean DeleteClient(Client client) throws SQLException;
}
