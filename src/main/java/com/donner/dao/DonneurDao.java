package com.donner.dao;

import com.donner.model.Donneur;
import com.donner.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonneurDao {
    
    public void save(Donneur donneur) {
        String sql = "INSERT INTO donneurs (nom, prenom, telephone, cin, date_naissance, poids, sexe, groupe_sanguin, statut_disponibilite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, donneur.getNom());
            stmt.setString(2, donneur.getPrenom());
            stmt.setString(3, donneur.getTelephone());
            stmt.setString(4, donneur.getCin());
            stmt.setString(5, donneur.getDateNaissance());
            stmt.setDouble(6, donneur.getPoids());
            stmt.setString(7, donneur.getSexe());
            stmt.setString(8, donneur.getGroupeSanguin());
            stmt.setString(9, donneur.getStatutDisponibilite());
            
            stmt.executeUpdate();
            System.out.println("Donneur saved successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error saving donneur: " + e.getMessage());
        }
    }
    
    public List<Donneur> findAll() {
        List<Donneur> donneurs = new ArrayList<>();
        String sql = "SELECT * FROM donneurs ORDER BY nom, prenom";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Donneur donneur = new Donneur();
                donneur.setId(rs.getInt("id"));
                donneur.setNom(rs.getString("nom"));
                donneur.setPrenom(rs.getString("prenom"));
                donneur.setTelephone(rs.getString("telephone"));
                donneur.setCin(rs.getString("cin"));
                donneur.setDateNaissance(rs.getString("date_naissance"));
                donneur.setPoids(rs.getDouble("poids"));
                donneur.setSexe(rs.getString("sexe"));
                donneur.setGroupeSanguin(rs.getString("groupe_sanguin"));
                donneur.setStatutDisponibilite(rs.getString("statut_disponibilite"));
                donneurs.add(donneur);
            }
            
        } catch (SQLException e) {
            System.out.println("Error finding donneurs: " + e.getMessage());
        }
        
        return donneurs;
    }
    
    public Donneur findById(int id) {
        String sql = "SELECT * FROM donneurs WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Donneur donneur = new Donneur();
                donneur.setId(rs.getInt("id"));
                donneur.setNom(rs.getString("nom"));
                donneur.setPrenom(rs.getString("prenom"));
                donneur.setTelephone(rs.getString("telephone"));
                donneur.setCin(rs.getString("cin"));
                donneur.setDateNaissance(rs.getString("date_naissance"));
                donneur.setPoids(rs.getDouble("poids"));
                donneur.setSexe(rs.getString("sexe"));
                donneur.setGroupeSanguin(rs.getString("groupe_sanguin"));
                donneur.setStatutDisponibilite(rs.getString("statut_disponibilite"));
                return donneur;
            }
            
        } catch (SQLException e) {
            System.out.println("Error finding donneur: " + e.getMessage());
        }
        
        return null;
    }
    
    public void update(Donneur donneur) {
        String sql = "UPDATE donneurs SET nom=?, prenom=?, telephone=?, cin=?, date_naissance=?, poids=?, sexe=?, groupe_sanguin=?, statut_disponibilite=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, donneur.getNom());
            stmt.setString(2, donneur.getPrenom());
            stmt.setString(3, donneur.getTelephone());
            stmt.setString(4, donneur.getCin());
            stmt.setString(5, donneur.getDateNaissance());
            stmt.setDouble(6, donneur.getPoids());
            stmt.setString(7, donneur.getSexe());
            stmt.setString(8, donneur.getGroupeSanguin());
            stmt.setString(9, donneur.getStatutDisponibilite());
            stmt.setInt(10, donneur.getId());
            
            stmt.executeUpdate();
            System.out.println("Donneur updated successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error updating donneur: " + e.getMessage());
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM donneurs WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Donneur deleted successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error deleting donneur: " + e.getMessage());
        }
    }
}