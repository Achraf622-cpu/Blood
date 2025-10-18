package com.donner.controller;

import com.donner.model.Receveur;
import com.donner.service.ReceveurService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReceveurServlet extends HttpServlet {
    
    private ReceveurService receveurService = new ReceveurService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        if (action.equals("list")) {
            listReceveurs(request, response);
        } else if (action.equals("add")) {
            showAddForm(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteReceveur(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            addReceveur(request, response);
        } else if (action.equals("edit")) {
            updateReceveur(request, response);
        }
    }
    
    private void listReceveurs(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Receveur> receveurs = receveurService.getAllReceveurs();
        request.setAttribute("receveurs", receveurs);
        request.getRequestDispatcher("/WEB-INF/views/receveurs/list.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/views/receveurs/add.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Receveur receveur = receveurService.getReceveurById(id);
        request.setAttribute("receveur", receveur);
        request.getRequestDispatcher("/WEB-INF/views/receveurs/edit.jsp").forward(request, response);
    }
    
    private void addReceveur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Receveur receveur = new Receveur();
        receveur.setNom(request.getParameter("nom"));
        receveur.setPrenom(request.getParameter("prenom"));
        receveur.setTelephone(request.getParameter("telephone"));
        receveur.setCin(request.getParameter("cin"));
        receveur.setDateNaissance(request.getParameter("dateNaissance"));
        receveur.setSexe(request.getParameter("sexe"));
        receveur.setGroupeSanguin(request.getParameter("groupeSanguin"));
        receveur.setSituationMedicale(request.getParameter("situationMedicale"));
        
        receveurService.saveReceveur(receveur);
        response.sendRedirect(request.getContextPath() + "/receveur?action=list");
    }
    
    private void updateReceveur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Receveur receveur = new Receveur();
        receveur.setId(Integer.parseInt(request.getParameter("id")));
        receveur.setNom(request.getParameter("nom"));
        receveur.setPrenom(request.getParameter("prenom"));
        receveur.setTelephone(request.getParameter("telephone"));
        receveur.setCin(request.getParameter("cin"));
        receveur.setDateNaissance(request.getParameter("dateNaissance"));
        receveur.setSexe(request.getParameter("sexe"));
        receveur.setGroupeSanguin(request.getParameter("groupeSanguin"));
        receveur.setSituationMedicale(request.getParameter("situationMedicale"));
        receveur.setEtatReceveur(request.getParameter("etatReceveur"));
        
        receveurService.updateReceveur(receveur);
        response.sendRedirect(request.getContextPath() + "/receveur?action=list");
    }
    
    private void deleteReceveur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        receveurService.deleteReceveur(id);
        response.sendRedirect(request.getContextPath() + "/receveur?action=list");
    }
}