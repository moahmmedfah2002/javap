package com.example.firstserv.Dao;

import com.example.firstserv.Models.Dotation;

import java.sql.SQLException;

public interface DotationDao {
    boolean addDotation(Dotation dotation) throws SQLException;
    boolean UpdateDotation(Dotation dotation) throws SQLException;
}
