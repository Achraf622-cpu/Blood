package com.donner.dao;

import com.donner.model.Receveur;
import com.donner.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceveurDao {
    
    public void save(Receveur receveur) {
        String sql = "INSERT INTO receveurs (nom, prenom, telephone, cin, date_naissance, sexe, groupe_sanguin, situation_medicale, etat_receveur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, receveur.getNom());
            stmt.setString(2, receveur.getPrenom());
            stmt.setString(3, receveur.getTelephone());
            stmt.setString(4, receveur.getCin());
            stmt.setString(5, receveur.getDateNaissance());
            stmt.setString(6, receveur.getSexe());
            stmt.setString(7, receveur.getGroupeSanguin());
            stmt.setString(8, receveur.getSituationMedicale());
            stmt.setString(9, receveur.getEtatReceveur());
            
            stmt.executeUpdate();
            System.out.println("Receveur saved successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error saving receveur: " + e.getMessage());
        }
    }
    
    public List<Receveur> findAll() {
        List<Receveur> receveurs = new ArrayList<>();
        String sql = "SELECT * FROM receveurs ORDER BY CASE situation_medicale WHEN 'CRITIQUE' THEN 1 WHEN 'URGENT' THEN 2 WHEN 'NORMAL' THEN 3 END, nom, prenom";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Receveur receveur = new Receveur();
                receveur.setId(rs.getInt("id"));
                receveur.setNom(rs.getString("nom"));
                receveur.setPrenom(rs.getString("prenom"));
                receveur.setTelephone(rs.getString("telephone"));
                receveur.setCin(rs.getString("cin"));
                receveur.setDateNaissance(rs.getString("date_naissance"));
                receveur.setSexe(rs.getString("sexe"));
                receveur.setGroupeSanguin(rs.getString("groupe_sanguin"));
                receveur.setSituationMedicale(rs.getString("situation_medicale"));
                receveur.setEtatReceveur(rs.getString("etat_receveur"));
                receveurs.add(receveur);
            }
            
        } catch (SQLException e) {
            System.out.println("Error finding receveurs: " + e.getMessage());
        }
        
        return receveurs;
    }
    
    public Receveur findById(int id) {
        String sql = "SELECT * FROM receveurs WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Receveur receveur = new Receveur();
                receveur.setId(rs.getInt("id"));
                receveur.setNom(rs.getString("nom"));
                receveur.setPrenom(rs.getString("prenom"));
                receveur.setTelephone(rs.getString("telephone"));
                receveur.setCin(rs.getString("cin"));
                receveur.setDateNaissance(rs.getString("date_naissance"));
                receveur.setSexe(rs.getString("sexe"));
                receveur.setGroupeSanguin(rs.getString("groupe_sanguin"));
                receveur.setSituationMedicale(rs.getString("situation_medicale"));
                receveur.setEtatReceveur(rs.getString("etat_receveur"));
                return receveur;
            }
            
        } catch (SQLException e) {
            System.out.println("Error finding receveur: " + e.getMessage());
        }
        
        return null;
    }
    
    public void update(Receveur receveur) {
        String sql = "UPDATE receveurs SET nom=?, prenom=?, telephone=?, cin=?, date_naissance=?, sexe=?, groupe_sanguin=?, situation_medicale=?, etat_receveur=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, receveur.getNom());
            stmt.setString(2, receveur.getPrenom());
            stmt.setString(3, receveur.getTelephone());
            stmt.setString(4, receveur.getCin());
            stmt.setString(5, receveur.getDateNaissance());
            stmt.setString(6, receveur.getSexe());
            stmt.setString(7, receveur.getGroupeSanguin());
            stmt.setString(8, receveur.getSituationMedicale());
            stmt.setString(9, receveur.getEtatReceveur());
            stmt.setInt(10, receveur.getId());
            
            stmt.executeUpdate();
            System.out.println("Receveur updated successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error updating receveur: " + e.getMessage());
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM receveurs WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Receveur deleted successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error deleting receveur: " + e.getMessage());
        }
    }
}