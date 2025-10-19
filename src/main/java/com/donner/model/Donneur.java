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
    private String hepatiteB;
    private String hepatiteC;
    private String hiv;
    private String syphilis;
    private String malaria;
    private String autresMaladies;
    private String diabeteInsulino;
    private String grossesse;
    private String allaitement;
    
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

    public String getHepatiteB() { return hepatiteB; }
    public void setHepatiteB(String hepatiteB) { this.hepatiteB = hepatiteB; }

    public String getHepatiteC() { return hepatiteC; }
    public void setHepatiteC(String hepatiteC) { this.hepatiteC = hepatiteC; }

    public String getHiv() { return hiv; }
    public void setHiv(String hiv) { this.hiv = hiv; }

    public String getSyphilis() { return syphilis; }
    public void setSyphilis(String syphilis) { this.syphilis = syphilis; }

    public String getMalaria() { return malaria; }
    public void setMalaria(String malaria) { this.malaria = malaria; }

    public String getAutresMaladies() { return autresMaladies; }
    public void setAutresMaladies(String autresMaladies) { this.autresMaladies = autresMaladies; }

    public String getDiabeteInsulino() { return diabeteInsulino; }
    public void setDiabeteInsulino(String diabeteInsulino) { this.diabeteInsulino = diabeteInsulino;}

    public String getGrossesse() { return grossesse; }
    public void setGrossesse(String grossesse) { this.grossesse = grossesse; }

    public String getAllaitement() { return allaitement; }
    public void setAllaitement(String allaitement) { this.allaitement = allaitement; }
}