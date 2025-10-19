package com.donner.controller;

import com.donner.model.Association;
import com.donner.model.Donneur;
import com.donner.model.Receveur;
import com.donner.service.AssociationService;
import com.donner.service.DonneurService;
import com.donner.service.ReceveurService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssociationServlet extends HttpServlet {

    private AssociationService associationService = new AssociationService();
    private DonneurService donneurService = new DonneurService();
    private ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listAssociations(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "delete":
                deleteAssociation(request, response);
                break;
            default:
                listAssociations(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            createAssociation(request, response);
        }
    }

    private void listAssociations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Association> associations = associationService.getAllAssociations();
        request.setAttribute("associations", associations);
        request.getRequestDispatcher("/WEB-INF/views/associations/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Load donors and recipients for the dropdowns
        List<Donneur> donneurs = donneurService.getAllDonneurs();
        List<Receveur> receveurs = receveurService.getAllReceveurs();

        request.setAttribute("donneurs", donneurs);
        request.setAttribute("receveurs", receveurs);
        request.getRequestDispatcher("/WEB-INF/views/associations/add.jsp").forward(request, response);
    }

    private void createAssociation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int donneurId = Integer.parseInt(request.getParameter("donneurId"));
            int receveurId = Integer.parseInt(request.getParameter("receveurId"));

            System.out.println("Servlet: Creating association - Donor: " + donneurId + ", Recipient: " + receveurId);

            associationService.createAssociation(donneurId, receveurId);

            System.out.println("Servlet: Association created successfully, redirecting...");
            response.sendRedirect(request.getContextPath() + "/association?action=list");

        } catch (Exception e) {
            System.out.println("Servlet: ERROR - " + e.getMessage());
            e.printStackTrace();

            // Re-load the form with error message
            List<Donneur> donneurs = donneurService.getAllDonneurs();
            List<Receveur> receveurs = receveurService.getAllReceveurs();

            request.setAttribute("donneurs", donneurs);
            request.setAttribute("receveurs", receveurs);
            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/WEB-INF/views/associations/add.jsp").forward(request, response);
        }
    }

    private void deleteAssociation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        associationService.deleteAssociation(id);
        response.sendRedirect(request.getContextPath() + "/association?action=list");
    }
}