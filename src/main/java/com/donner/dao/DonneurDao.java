package com.donner.dao;

import com.donner.model.Donneur;
import com.donner.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonneurDao {

    public void save(Donneur donneur) {
        String sql = "INSERT INTO donneurs (nom, prenom, telephone, cin, date_naissance, poids, sexe, groupe_sanguin, statut_disponibilite, hepatiteB, hepatiteC, hiv, syphilis, malaria, autres_maladies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donneur.getNom());
            stmt.setString(2, donneur.getPrenom());
            stmt.setString(3, donneur.getTelephone());
            stmt.setString(4, donneur.getCin());

            // Date handling
            if (donneur.getDateNaissance() != null && !donneur.getDateNaissance().isEmpty()) {
                stmt.setDate(5, java.sql.Date.valueOf(donneur.getDateNaissance()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }

            stmt.setDouble(6, donneur.getPoids());
            stmt.setString(7, donneur.getSexe());
            stmt.setString(8, donneur.getGroupeSanguin());
            stmt.setString(9, donneur.getStatutDisponibilite());

            // Medical fields
            stmt.setString(10, donneur.getHepatiteB());
            stmt.setString(11, donneur.getHepatiteC());
            stmt.setString(12, donneur.getHiv());
            stmt.setString(13, donneur.getSyphilis());
            stmt.setString(14, donneur.getMalaria());
            stmt.setString(15, donneur.getAutresMaladies());

            stmt.executeUpdate();
            System.out.println("Donneur saved successfully!");

        } catch (SQLException e) {
            System.out.println("Error saving donneur: " + e.getMessage());
            throw new RuntimeException(e);
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
                donneur.setHepatiteB(rs.getString("hepatiteb"));
                donneur.setHepatiteC(rs.getString("hepatitec"));
                donneur.setHiv(rs.getString("hiv"));
                donneur.setSyphilis(rs.getString("syphilis"));
                donneur.setMalaria(rs.getString("malaria"));
                donneur.setDiabeteInsulino(rs.getString("diabete_insulino"));
                donneur.setGrossesse(rs.getString("grossesse"));
                donneur.setAllaitement(rs.getString("allaitement"));
                donneur.setAutresMaladies(rs.getString("autres_maladies"));
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
            if (donneur.getDateNaissance() != null && !donneur.getDateNaissance().isEmpty()) {
                stmt.setDate(5, java.sql.Date.valueOf(donneur.getDateNaissance()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
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