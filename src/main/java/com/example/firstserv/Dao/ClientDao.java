package com.example.firstserv.Dao;

import com.example.firstserv.Models.Client;

import java.sql.SQLException;

public interface ClientDao {
    boolean addClient(Client client) throws SQLException;
    boolean updateClient(Client client) throws SQLException;
}
