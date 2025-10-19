package com.donner.dao;

import com.donner.model.Association;
import com.donner.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociationDao {

    public void save(Association association) {
        String sql = "INSERT INTO associations (donneur_id, receveur_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, association.getDonneurId());
            stmt.setInt(2, association.getReceveurId());

            int rows = stmt.executeUpdate();
            System.out.println("DAO: Association saved successfully. Rows affected: " + rows);

        } catch (SQLException e) {
            System.out.println("DAO: Error saving association - " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error while saving association", e);
        }
    }

    public List<Association> findAll() {
        List<Association> associations = new ArrayList<>();
        String sql = "SELECT * FROM associations ORDER BY date_association DESC";

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("DB not configured; returning empty association list.");
                return associations;
            }
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Association association = new Association();
                    association.setId(rs.getInt("id"));
                    association.setDonneurId(rs.getInt("donneur_id"));
                    association.setReceveurId(rs.getInt("receveur_id"));

                    // Handle the timestamp properly
                    Timestamp timestamp = rs.getTimestamp("date_association");
                    if (timestamp != null) {
                        association.setDateAssociation(timestamp.toString());
                    }

                    associations.add(association);
                    System.out.println("DAO: Found association - ID: " + association.getId() +
                            ", Donor: " + association.getDonneurId() +
                            ", Recipient: " + association.getReceveurId());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding associations: " + e.getMessage());
            e.printStackTrace();
        }

        return associations;
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM associations WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("DB not configured; skipping association delete.");
                return;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Association deleted successfully!");
            }
            
        } catch (SQLException e) {
            System.out.println("Error deleting association: " + e.getMessage());
        }
    }
}