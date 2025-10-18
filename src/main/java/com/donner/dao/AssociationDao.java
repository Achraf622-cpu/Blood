package com.donner.dao;

import com.donner.model.Association;
import com.donner.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociationDao {
    
    public void save(Association association) {
        String sql = "INSERT INTO associations (donneur_id, receveur_id, date_association) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, association.getDonneurId());
            stmt.setInt(2, association.getReceveurId());
            stmt.setString(3, association.getDateAssociation());
            
            stmt.executeUpdate();
            System.out.println("Association saved successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error saving association: " + e.getMessage());
        }
    }
    
    public List<Association> findAll() {
        List<Association> associations = new ArrayList<>();
        String sql = "SELECT a.*, d.nom as donneur_nom, d.prenom as donneur_prenom, r.nom as receveur_nom, r.prenom as receveur_prenom FROM associations a " +
                    "JOIN donneurs d ON a.donneur_id = d.id " +
                    "JOIN receveurs r ON a.receveur_id = r.id " +
                    "ORDER BY a.date_association DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Association association = new Association();
                association.setId(rs.getInt("id"));
                association.setDonneurId(rs.getInt("donneur_id"));
                association.setReceveurId(rs.getInt("receveur_id"));
                association.setDateAssociation(rs.getString("date_association"));
                associations.add(association);
            }
            
        } catch (SQLException e) {
            System.out.println("Error finding associations: " + e.getMessage());
        }
        
        return associations;
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM associations WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Association deleted successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error deleting association: " + e.getMessage());
        }
    }
}