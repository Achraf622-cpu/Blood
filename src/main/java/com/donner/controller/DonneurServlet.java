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
        
        Donneur donneur = new Donneur();
        donneur.setNom(request.getParameter("nom"));
        donneur.setPrenom(request.getParameter("prenom"));
        donneur.setTelephone(request.getParameter("telephone"));
        donneur.setCin(request.getParameter("cin"));
        donneur.setDateNaissance(request.getParameter("dateNaissance"));
        donneur.setPoids(Double.parseDouble(request.getParameter("poids")));
        donneur.setSexe(request.getParameter("sexe"));
        donneur.setGroupeSanguin(request.getParameter("groupeSanguin"));
        
        donneurService.saveDonneur(donneur);
        response.sendRedirect(request.getContextPath() + "/donneur?action=list");
    }
    
    private void updateDonneur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Donneur donneur = new Donneur();
        donneur.setId(Integer.parseInt(request.getParameter("id")));
        donneur.setNom(request.getParameter("nom"));
        donneur.setPrenom(request.getParameter("prenom"));
        donneur.setTelephone(request.getParameter("telephone"));
        donneur.setCin(request.getParameter("cin"));
        donneur.setDateNaissance(request.getParameter("dateNaissance"));
        donneur.setPoids(Double.parseDouble(request.getParameter("poids")));
        donneur.setSexe(request.getParameter("sexe"));
        donneur.setGroupeSanguin(request.getParameter("groupeSanguin"));
        donneur.setStatutDisponibilite(request.getParameter("statutDisponibilite"));
        
        donneurService.updateDonneur(donneur);
        response.sendRedirect(request.getContextPath() + "/donneur?action=list");
    }
    
    private void deleteDonneur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        donneurService.deleteDonneur(id);
        response.sendRedirect(request.getContextPath() + "/donneur?action=list");
    }
}