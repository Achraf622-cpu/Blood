package com.donner.service;

import com.donner.dao.DonneurDao;
import com.donner.model.Donneur;
import java.util.List;

public class DonneurService {
    
    private DonneurDao donneurDao = new DonneurDao();
    // Simple in-memory fallback (used only if DB returns nothing)
    private static final java.util.List<Donneur> memoryDonneurs = new java.util.ArrayList<>();
    private static int memoryIdSequence = 1;
    
    public void saveDonneur(Donneur donneur) {
        // Simple validation
        if (donneur.getNom() == null || donneur.getNom().isEmpty()) {
            System.out.println("Error: Nom is required");
            return;
        }
        if (donneur.getPrenom() == null || donneur.getPrenom().isEmpty()) {
            System.out.println("Error: Prenom is required");
            return;
        }
        if (donneur.getPoids() < 50) {
            System.out.println("Error: Poids must be at least 50kg");
            return;
        }
        
        // Set status based on simple rules
        if (donneur.getPoids() >= 50) {
            donneur.setStatutDisponibilite("DISPONIBLE");
        } else {
            donneur.setStatutDisponibilite("NON_ELIGIBLE");
        }
        
        // Persist to database (surface errors instead of swallowing)
        donneurDao.save(donneur);
    }
    
    public List<Donneur> getAllDonneurs() {
        java.util.List<Donneur> fromDb = donneurDao.findAll();
        if (fromDb == null || fromDb.isEmpty()) {
            return new java.util.ArrayList<>(memoryDonneurs);
        }
        return fromDb;
    }
    
    public Donneur getDonneurById(int id) {
        Donneur found = donneurDao.findById(id);
        if (found != null) return found;
        for (Donneur mem : memoryDonneurs) {
            if (mem.getId() == id) return mem;
        }
        return null;
    }
    
    public void updateDonneur(Donneur donneur) {
        donneurDao.update(donneur);
    }
    
    public void deleteDonneur(int id) {
        donneurDao.delete(id);
        memoryDonneurs.removeIf(d2 -> d2.getId() == id);
    }
}