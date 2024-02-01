package com.example.firstserv.Dao;

import com.example.firstserv.Models.Dotation;

import java.sql.SQLException;
import java.util.List;

public interface DotationDao {
    List<Dotation> AllDotations() throws SQLException;
    boolean addDotation(Dotation dotation) throws SQLException;
    boolean UpdateDotation(Dotation dotation) throws SQLException;
    boolean DeleteDotation(Dotation dotation) throws SQLException;
}
