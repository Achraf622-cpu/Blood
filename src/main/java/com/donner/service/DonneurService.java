package com.donner.service;

import com.donner.dao.DonneurDao;
import com.donner.model.Donneur;
import java.util.List;

public class DonneurService {
    
    private DonneurDao donneurDao = new DonneurDao();
    
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
        
        donneurDao.save(donneur);
    }
    
    public List<Donneur> getAllDonneurs() {
        return donneurDao.findAll();
    }
    
    public Donneur getDonneurById(int id) {
        return donneurDao.findById(id);
    }
    
    public void updateDonneur(Donneur donneur) {
        donneurDao.update(donneur);
    }
    
    public void deleteDonneur(int id) {
        donneurDao.delete(id);
    }
}