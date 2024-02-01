package com.example.firstserv.Models;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Carte {

    private String number_carte;
    private int id_carte;
    private String type_carte;
    private String date_expiration;
    private int cve;
    private boolean dotation_statuts;
    private int id_client;
    public Carte(String number_carte,String type_carte,String date_expiration,int cve,boolean dotation_statuts,int id_client){
        this.number_carte = number_carte;
        this.type_carte = type_carte;
        this.date_expiration = date_expiration;
        this.cve = cve;
        this.dotation_statuts = dotation_statuts;
        this.id_client = id_client;
    }
}
