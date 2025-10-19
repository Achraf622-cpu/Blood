package com.donner.service;

import com.donner.dao.ReceveurDao;
import com.donner.model.Receveur;
import java.util.List;

public class ReceveurService {
    
    private ReceveurDao receveurDao = new ReceveurDao();

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
        return receveurDao.findAll();
    }
    
    public Receveur getReceveurById(int id) {
        return receveurDao.findById(id);
    }
    
    public void updateReceveur(Receveur receveur) {
        receveurDao.update(receveur);
    }
    
    public void deleteReceveur(int id) {
        receveurDao.delete(id);
    }
}