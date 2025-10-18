package com.donner.controller;

import com.donner.model.Association;
import com.donner.service.AssociationService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssociationServlet extends HttpServlet {
    
    private AssociationService associationService = new AssociationService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        if (action.equals("list")) {
            listAssociations(request, response);
        } else if (action.equals("delete")) {
            deleteAssociation(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action.equals("create")) {
            createAssociation(request, response);
        }
    }
    
    private void listAssociations(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Association> associations = associationService.getAllAssociations();
        request.setAttribute("associations", associations);
        request.getRequestDispatcher("/WEB-INF/views/associations/list.jsp").forward(request, response);
    }
    
    private void createAssociation(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int donneurId = Integer.parseInt(request.getParameter("donneurId"));
        int receveurId = Integer.parseInt(request.getParameter("receveurId"));
        
        associationService.createAssociation(donneurId, receveurId);
        response.sendRedirect(request.getContextPath() + "/association?action=list");
    }
    
    private void deleteAssociation(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        associationService.deleteAssociation(id);
        response.sendRedirect(request.getContextPath() + "/association?action=list");
    }
}