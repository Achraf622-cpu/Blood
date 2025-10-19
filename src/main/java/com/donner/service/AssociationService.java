package com.donner.service;

import com.donner.dao.AssociationDao;
import com.donner.dao.DonneurDao;
import com.donner.dao.ReceveurDao;
import com.donner.model.Association;
import com.donner.model.Donneur;
import com.donner.model.Receveur;
import java.util.List;

public class AssociationService {
    
    private AssociationDao associationDao = new AssociationDao();
    private DonneurDao donneurDao = new DonneurDao();
    private ReceveurDao receveurDao = new ReceveurDao();
    
    public void createAssociation(int donneurId, int receveurId) {
        // Check if donneur and receveur exist
        Donneur donneur = donneurDao.findById(donneurId);
        Receveur receveur = receveurDao.findById(receveurId);
        
        if (donneur == null) {
            System.out.println("Error: Donneur not found");
            return;
        }
        if (receveur == null) {
            System.out.println("Error: Receveur not found");
            return;
        }
        
        // Simple compatibility check - just check if groups are the same
        if (!donneur.getGroupeSanguin().equals(receveur.getGroupeSanguin())) {
            System.out.println("Error: Blood groups not compatible");
            return;
        }
        
        // Check if donneur is available
        if (!donneur.getStatutDisponibilite().equals("DISPONIBLE")) {
            System.out.println("Error: Donneur not available");
            return;
        }
        
        // Create association
        Association association = new Association(donneurId, receveurId);
        associationDao.save(association);
        
        // Update donneur status
        donneur.setStatutDisponibilite("NON_DISPONIBLE");
        donneurDao.update(donneur);
        
        System.out.println("Association created successfully!");
    }
    
    public List<Association> getAllAssociations() {
        return associationDao.findAll();
    }
    
    public void deleteAssociation(int id) {
        associationDao.delete(id);
    }
}