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
        try {
            System.out.println("=== DEBUG: Starting association creation ===");
            System.out.println("Donor ID: " + donneurId);
            System.out.println("Recipient ID: " + receveurId);

            // TEMPORARY: Skip all validations
            System.out.println("Skipping validations for testing...");

            Association association = new Association();
            association.setDonneurId(donneurId);
            association.setReceveurId(receveurId);

            System.out.println("Calling associationDao.save()...");
            associationDao.save(association);
            System.out.println("Association saved successfully!");

        } catch (Exception e) {
            System.out.println("ERROR in createAssociation: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to create association: " + e.getMessage(), e);
        }
    }
    
    public List<Association> getAllAssociations() {
        return associationDao.findAll();
    }
    
    public void deleteAssociation(int id) {
        associationDao.delete(id);
    }
}