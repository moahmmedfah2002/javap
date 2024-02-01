package com.example.firstserv.Models;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    public Client(String nom, String prenom, String email, String password){
        this.nom = nom;
        this.prenom = prenom;
        this.email  = email;
        this.password = password;
    }
}
