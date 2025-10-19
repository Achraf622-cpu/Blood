package com.donner.model;

public class Receveur {
    
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String cin;
    private String dateNaissance;
    private String sexe;
    private String groupeSanguin;
    private String situationMedicale;
    private String etatReceveur;
    
    // Constructors
    public Receveur() {}
    
    public Receveur(String nom, String prenom, String telephone, String cin, 
                   String dateNaissance, String sexe, String groupeSanguin, String situationMedicale) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.groupeSanguin = groupeSanguin;
        this.situationMedicale = situationMedicale;
        this.etatReceveur = "EN_ATTENTE";
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getCin() {
        return cin;
    }
    
    public void setCin(String cin) {
        this.cin = cin;
    }
    
    public String getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String getSexe() {
        return sexe;
    }
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public String getGroupeSanguin() {
        return groupeSanguin;
    }
    
    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }
    
    public String getSituationMedicale() {
        return situationMedicale;
    }
    
    public void setSituationMedicale(String situationMedicale) {
        this.situationMedicale = situationMedicale;
    }
    
    public String getEtatReceveur() {
        return etatReceveur;
    }
    
    public void setEtatReceveur(String etatReceveur) {
        this.etatReceveur = etatReceveur;
    }
}