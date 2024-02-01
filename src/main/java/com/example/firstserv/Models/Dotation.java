package com.example.firstserv.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dotation {

    private int id_dotation;
    private String date_expiration_dotation;
    private float montant;
    private float plat_fond;
    private String type_dotation;
    private int id_carte;
    public Dotation(String date_expiration_dotation,float montant,float plat_fond,String type_dotation,int id_carte){
        this.date_expiration_dotation = date_expiration_dotation;
        this.montant = montant;
        this.plat_fond = plat_fond;
        this.type_dotation = type_dotation;
        this.id_carte = id_carte;
    }

}
