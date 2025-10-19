package com.donner.model;

public class Association {
    
    private int id;
    private int donneurId;
    private int receveurId;
    private String dateAssociation;
    
    // Constructors
    public Association() {}
    
    public Association(int donneurId, int receveurId) {
        this.donneurId = donneurId;
        this.receveurId = receveurId;
        this.dateAssociation = new java.util.Date().toString();
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getDonneurId() {
        return donneurId;
    }
    
    public void setDonneurId(int donneurId) {
        this.donneurId = donneurId;
    }
    
    public int getReceveurId() {
        return receveurId;
    }
    
    public void setReceveurId(int receveurId) {
        this.receveurId = receveurId;
    }
    
    public String getDateAssociation() {
        return dateAssociation;
    }
    
    public void setDateAssociation(String dateAssociation) {
        this.dateAssociation = dateAssociation;
    }
}