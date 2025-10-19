<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier un Donneur - Système de Gestion des Dons de Sang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <i class="fas fa-heartbeat me-2"></i>Blood Donation System
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Accueil</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/donneur?action=list">Donneurs</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/receveur?action=list">Receveurs</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/association?action=list">Associations</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4><i class="fas fa-edit me-2"></i>Modifier le Donneur</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                <i class="fas fa-exclamation-triangle me-2"></i>${error}
                            </div>
                        </c:if>

                        <form method="post" action="${pageContext.request.contextPath}/donneur">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="${donneur.id}">
                            
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="nom" class="form-label">Nom *</label>
                                    <input type="text" class="form-control" id="nom" name="nom" value="${donneur.nom}" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="prenom" class="form-label">Prénom *</label>
                                    <input type="text" class="form-control" id="prenom" name="prenom" value="${donneur.prenom}" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="telephone" class="form-label">Téléphone *</label>
                                    <input type="tel" class="form-control" id="telephone" name="telephone" value="${donneur.telephone}" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="cin" class="form-label">CIN *</label>
                                    <input type="text" class="form-control" id="cin" name="cin" value="${donneur.cin}" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="dateNaissance" class="form-label">Date de naissance *</label>
                                    <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" 
                                    value="${donneur.dateNaissance}" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="poids" class="form-label">Poids (kg) *</label>
                                    <input type="number" step="0.1" class="form-control" id="poids" name="poids" value="${donneur.poids}" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="sexe" class="form-label">Sexe *</label>
                                    <select class="form-select" id="sexe" name="sexe" required>
                                        <option value="">Sélectionner...</option>
                                        <option value="M" ${donneur.sexe == 'M' ? 'selected' : ''}>Masculin</option>
                                        <option value="F" ${donneur.sexe == 'F' ? 'selected' : ''}>Féminin</option>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="groupeSanguin" class="form-label">Groupe Sanguin *</label>
                                    <select class="form-select" id="groupeSanguin" name="groupeSanguin" required>
                                        <option value="">Sélectionner...</option>
                                        <option value="A+" ${donneur.groupeSanguin == 'A+' ? 'selected' : ''}>A+</option>
                                        <option value="A-" ${donneur.groupeSanguin == 'A-' ? 'selected' : ''}>A-</option>
                                        <option value="B+" ${donneur.groupeSanguin == 'B+' ? 'selected' : ''}>B+</option>
                                        <option value="B-" ${donneur.groupeSanguin == 'B-' ? 'selected' : ''}>B-</option>
                                        <option value="AB+" ${donneur.groupeSanguin == 'AB+' ? 'selected' : ''}>AB+</option>
                                        <option value="AB-" ${donneur.groupeSanguin == 'AB-' ? 'selected' : ''}>AB-</option>
                                        <option value="O+" ${donneur.groupeSanguin == 'O+' ? 'selected' : ''}>O+</option>
                                        <option value="O-" ${donneur.groupeSanguin == 'O-' ? 'selected' : ''}>O-</option>
                                    </select>
                                </div>
                            </div>

                            <div class="mb-4">
                                <h6>Contre-indications médicales</h6>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="hepatiteB" name="hepatiteB" ${donneur.hepatiteB ? 'checked' : ''}>
                                            <label class="form-check-label" for="hepatiteB">Hépatite B</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="hepatiteC" name="hepatiteC" ${donneur.hepatiteC ? 'checked' : ''}>
                                            <label class="form-check-label" for="hepatiteC">Hépatite C</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="hiv" name="hiv" ${donneur.hiv ? 'checked' : ''}>
                                            <label class="form-check-label" for="hiv">HIV</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="diabeteInsulino" name="diabeteInsulino" ${donneur.diabeteInsulino ? 'checked' : ''}>
                                            <label class="form-check-label" for="diabeteInsulino">Diabète insulinodépendant</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="grossesse" name="grossesse" ${donneur.grossesse ? 'checked' : ''}>
                                            <label class="form-check-label" for="grossesse">Grossesse</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="allaitement" name="allaitement" ${donneur.allaitement ? 'checked' : ''}>
                                            <label class="form-check-label" for="allaitement">Allaitement</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/donneur?action=list" class="btn btn-secondary">
                                    <i class="fas fa-arrow-left me-1"></i>Retour
                                </a>
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-save me-1"></i>Mettre à jour
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

