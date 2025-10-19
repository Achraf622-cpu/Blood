package com.donner.service;

import com.donner.dao.ReceveurDao;
import com.donner.model.Receveur;
import java.util.List;

public class ReceveurService {
    
    private ReceveurDao receveurDao = new ReceveurDao();
    // In-memory fallback (used only if DB returns nothing)
    private static final java.util.List<Receveur> memoryReceveurs = new java.util.ArrayList<>();
    private static int memoryIdSequence = 1;

    public void saveReceveur(Receveur receveur) {
        // Simple validation
        if (receveur.getNom() == null || receveur.getNom().isEmpty()) {
            System.out.println("Error: Nom is required");
            return;
        }
        if (receveur.getPrenom() == null || receveur.getPrenom().isEmpty()) {
            System.out.println("Error: Prenom is required");
            return;
        }
        
        // Set default status
        receveur.setEtatReceveur("EN_ATTENTE");
        
        receveurDao.save(receveur);
    }
    
    public List<Receveur> getAllReceveurs() {
        java.util.List<Receveur> fromDb = receveurDao.findAll();
        if (fromDb == null || fromDb.isEmpty()) {
            return new java.util.ArrayList<>(memoryReceveurs);
        }
        return fromDb;
    }
    
    public Receveur getReceveurById(int id) {
        Receveur r = receveurDao.findById(id);
        if (r != null) return r;
        for (Receveur r2 : memoryReceveurs) {
            if (r2.getId() == id) return r2;
        }
        return null;
    }
    
    public void updateReceveur(Receveur receveur) {
        receveurDao.update(receveur);
    }
    
    public void deleteReceveur(int id) {
        receveurDao.delete(id);
        memoryReceveurs.removeIf(r -> r.getId() == id);
    }
}