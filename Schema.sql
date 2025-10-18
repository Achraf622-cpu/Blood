-- Simple PostgreSQL database setup for Blood Donation System
-- Run this script in PostgreSQL before starting the application

-- Create database
CREATE DATABASE blood_donation;

-- Connect to the database
\c blood_donation;

-- Create tables
CREATE TABLE donneurs (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    cin VARCHAR(20) UNIQUE NOT NULL,
    date_naissance DATE NOT NULL,
    poids DECIMAL(5,2) NOT NULL,
    sexe VARCHAR(10) NOT NULL,
    groupe_sanguin VARCHAR(5) NOT NULL,
    statut_disponibilite VARCHAR(20) DEFAULT 'DISPONIBLE'
);

CREATE TABLE receveurs (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    cin VARCHAR(20) UNIQUE NOT NULL,
    date_naissance DATE NOT NULL,
    sexe VARCHAR(10) NOT NULL,
    groupe_sanguin VARCHAR(5) NOT NULL,
    situation_medicale VARCHAR(20) NOT NULL,
    etat_receveur VARCHAR(20) DEFAULT 'EN_ATTENTE'
);

CREATE TABLE associations (
    id SERIAL PRIMARY KEY,
    donneur_id INTEGER REFERENCES donneurs(id),
    receveur_id INTEGER REFERENCES receveurs(id),
    date_association TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert some sample data
INSERT INTO donneurs (nom, prenom, telephone, cin, date_naissance, poids, sexe, groupe_sanguin) VALUES
('Dupont', 'Jean', '0123456789', 'CIN001', '1990-05-15', 70.5, 'M', 'A+'),
('Martin', 'Marie', '0987654321', 'CIN002', '1985-08-22', 65.0, 'F', 'O+');

INSERT INTO receveurs (nom, prenom, telephone, cin, date_naissance, sexe, groupe_sanguin, situation_medicale) VALUES
('Bernard', 'Pierre', '0111222333', 'CIN003', '1975-03-10', 'M', 'A+', 'URGENT'),
('Leroy', 'Sophie', '0444555666', 'CIN004', '1988-12-05', 'F', 'O+', 'NORMAL');

-- Show tables
\dt