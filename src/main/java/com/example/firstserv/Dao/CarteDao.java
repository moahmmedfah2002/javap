package com.example.firstserv.Dao;

import com.example.firstserv.Models.Carte;

import java.sql.SQLException;

public interface CarteDao {
    boolean addCarte(Carte carte) throws SQLException;
    boolean UpdateCarte(Carte carte) throws SQLException;
}
