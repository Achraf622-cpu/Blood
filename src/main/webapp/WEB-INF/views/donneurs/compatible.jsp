<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donneurs Compatibles - Système de Gestion des Dons de Sang</title>
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
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="fas fa-search me-2"></i>Rechercher des Donneurs Compatibles</h2>
            <a href="${pageContext.request.contextPath}/donneur?action=list" class="btn btn-secondary">
                <i class="fas fa-arrow-left me-1"></i>Retour à la liste
            </a>
        </div>

        <div class="card mb-4">
            <div class="card-body">
                <form method="get" action="${pageContext.request.contextPath}/donneur">
                    <input type="hidden" name="action" value="compatible">
                    <div class="row">
                        <div class="col-md-8">
                            <label for="groupeSanguin" class="form-label">Sélectionner un groupe sanguin pour voir les donneurs compatibles :</label>
                            <select class="form-select" id="groupeSanguin" name="groupeSanguin" required>
                                <option value="">Sélectionner un groupe sanguin...</option>
                                <c:forEach var="groupe" items="${groupesSanguins}">
                                    <option value="${groupe.name()}" ${groupeSanguin == groupe ? 'selected' : ''}>${groupe.displayName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-4 d-flex align-items-end">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="fas fa-search me-1"></i>Rechercher
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <c:if test="${not empty groupeSanguin}">
            <div class="alert alert-info">
                <i class="fas fa-info-circle me-2"></i>
                Donneurs compatibles avec le groupe sanguin <strong>${groupeSanguin.displayName}</strong>
            </div>
        </c:if>

        <c:choose>
            <c:when test="${empty donneurs}">
                <div class="text-center py-5">
                    <i class="fas fa-search fa-5x text-muted mb-3"></i>
                    <h4 class="text-muted">
                        <c:choose>
                            <c:when test="${empty groupeSanguin}">
                                Sélectionnez un groupe sanguin pour rechercher des donneurs compatibles
                            </c:when>
                            <c:otherwise>
                                Aucun donneur compatible trouvé pour le groupe ${groupeSanguin.displayName}
                            </c:otherwise>
                        </c:choose>
                    </h4>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Téléphone</th>
                                <th>CIN</th>
                                <th>Âge</th>
                                <th>Poids</th>
                                <th>Sexe</th>
                                <th>Groupe Sanguin</th>
                                <th>Statut</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="donneur" items="${donneurs}">
                                <tr>
                                    <td>${donneur.id}</td>
                                    <td>${donneur.nom}</td>
                                    <td>${donneur.prenom}</td>
                                    <td>${donneur.telephone}</td>
                                    <td>${donneur.cin}</td>
                                    <td>${donneur.age} ans</td>
                                    <td>${donneur.poids} kg</td>
                                    <td>${donneur.sexe}</td>
                                    <td>
                                        <span class="badge bg-primary">${donneur.groupeSanguin.displayName}</span>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${donneur.statutDisponibilite.name() == 'DISPONIBLE'}">
                                                <span class="badge bg-success">${donneur.statutDisponibilite.displayName}</span>
                                            </c:when>
                                            <c:when test="${donneur.statutDisponibilite.name() == 'NON_DISPONIBLE'}">
                                                <span class="badge bg-warning">${donneur.statutDisponibilite.displayName}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">${donneur.statutDisponibilite.displayName}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="${pageContext.request.contextPath}/association?action=compatible-receveurs&donneurId=${donneur.id}" 
                                               class="btn btn-sm btn-outline-info" title="Voir les receveurs compatibles">
                                                <i class="fas fa-link"></i>
                                            </a>
                                            <a href="${pageContext.request.contextPath}/donneur?action=edit&id=${donneur.id}" 
                                               class="btn btn-sm btn-outline-primary" title="Modifier">
                                                <i class="fas fa-edit"></i>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


