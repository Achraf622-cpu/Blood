package com.donner.controller;

import com.donner.model.Donneur;
import com.donner.service.DonneurService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DonneurServlet extends HttpServlet {
    
    private DonneurService donneurService = new DonneurService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        if (action.equals("list")) {
            listDonneurs(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteDonneur(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            addDonneur(request, response);
        } else if (action.equals("edit")) {
            updateDonneur(request, response);
        }
    }
    
    private void listDonneurs(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Donneur> donneurs = donneurService.getAllDonneurs();
        request.setAttribute("donneurs", donneurs);
        request.getRequestDispatcher("/WEB-INF/views/donneurs/list.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/views/donneurs/add.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Donneur donneur = donneurService.getDonneurById(id);
        request.setAttribute("donneur", donneur);
        request.getRequestDispatcher("/WEB-INF/views/donneurs/edit.jsp").forward(request, response);
    }

    private void addDonneur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Donneur donneur = new Donneur();
            donneur.setNom(request.getParameter("nom"));
            donneur.setPrenom(request.getParameter("prenom"));
            donneur.setTelephone(request.getParameter("telephone"));
            donneur.setCin(request.getParameter("cin"));

            // Date handling
            String dateNaissanceStr = request.getParameter("dateNaissance");
            if (dateNaissanceStr != null && !dateNaissanceStr.isEmpty()) {
                donneur.setDateNaissance(dateNaissanceStr);
            }

            donneur.setPoids(Double.parseDouble(request.getParameter("poids")));
            donneur.setSexe(request.getParameter("sexe"));
            donneur.setGroupeSanguin(request.getParameter("groupeSanguin"));

            // Medical screening fields
            donneur.setHepatiteB(request.getParameter("hepatiteB"));
            donneur.setHepatiteC(request.getParameter("hepatiteC"));
            donneur.setHiv(request.getParameter("hiv"));
            donneur.setSyphilis(request.getParameter("syphilis"));
            donneur.setMalaria(request.getParameter("malaria"));
            donneur.setAutresMaladies(request.getParameter("autresMaladies"));

            // Auto-set status based on test results
            String status = "DISPONIBLE";
            if ("POSITIF".equals(donneur.getHepatiteB()) || "POSITIF".equals(donneur.getHepatiteC()) ||
                    "POSITIF".equals(donneur.getHiv()) || "POSITIF".equals(donneur.getSyphilis()) ||
                    "POSITIF".equals(donneur.getMalaria())) {
                status = "NOT_AVAILABLE";
            }
            donneur.setStatutDisponibilite(status);

            donneurService.saveDonneur(donneur);
            response.sendRedirect(request.getContextPath() + "/donneur?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error adding donor: " + e.getMessage());
            showAddForm(request, response);
        }
    }

    private void updateDonneur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Donneur donneur = new Donneur();
            donneur.setId(Integer.parseInt(request.getParameter("id")));
            donneur.setNom(request.getParameter("nom"));
            donneur.setPrenom(request.getParameter("prenom"));
            donneur.setTelephone(request.getParameter("telephone"));
            donneur.setCin(request.getParameter("cin"));

            // FIX: Convert date from HTML format (yyyy-MM-dd) to database format
            String dateNaissanceStr = request.getParameter("dateNaissance");
            if (dateNaissanceStr != null && !dateNaissanceStr.isEmpty()) {
                donneur.setDateNaissance(dateNaissanceStr);
            }

            donneur.setPoids(Double.parseDouble(request.getParameter("poids")));
            donneur.setSexe(request.getParameter("sexe"));
            donneur.setGroupeSanguin(request.getParameter("groupeSanguin"));
            donneur.setStatutDisponibilite(request.getParameter("statutDisponibilite"));

            donneurService.updateDonneur(donneur);
            response.sendRedirect(request.getContextPath() + "/donneur?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating donor: " + e.getMessage());
            showEditForm(request, response);
        }
    }
    
    private void deleteDonneur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        donneurService.deleteDonneur(id);
        response.sendRedirect(request.getContextPath() + "/donneur?action=list");
    }
}