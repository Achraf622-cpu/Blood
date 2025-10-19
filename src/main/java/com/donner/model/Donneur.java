package com.donner.model;

public class Donneur {
    
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String cin;
    private String dateNaissance;
    private double poids;
    private String sexe;
    private String groupeSanguin;
    private String statutDisponibilite;
    
    // Constructors
    public Donneur() {}
    
    public Donneur(String nom, String prenom, String telephone, String cin, 
                   String dateNaissance, double poids, String sexe, String groupeSanguin) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.poids = poids;
        this.sexe = sexe;
        this.groupeSanguin = groupeSanguin;
        this.statutDisponibilite = "DISPONIBLE";
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
    
    public double getPoids() {
        return poids;
    }
    
    public void setPoids(double poids) {
        this.poids = poids;
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
    
    public String getStatutDisponibilite() {
        return statutDisponibilite;
    }
    
    public void setStatutDisponibilite(String statutDisponibilite) {
        this.statutDisponibilite = statutDisponibilite;
    }
}