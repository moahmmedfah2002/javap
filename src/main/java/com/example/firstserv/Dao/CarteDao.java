package com.example.firstserv.Dao;

import com.example.firstserv.Models.Carte;

import java.sql.SQLException;
import java.util.List;

public interface CarteDao {
    List<Carte> AllCarte() throws SQLException;
    boolean addCarte(Carte carte) throws SQLException;
    boolean UpdateCarte(Carte carte) throws SQLException;
    boolean DeleteCarte(Carte carte) throws SQLException;
}
